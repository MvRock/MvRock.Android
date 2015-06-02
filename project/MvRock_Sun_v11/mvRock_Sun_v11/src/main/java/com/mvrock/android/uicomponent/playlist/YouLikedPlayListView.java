package com.mvrock.android.uicomponent.playlist;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.model.PlayListOption;
import com.mvrock.android.thread.GetYouLikedSongAndUserDataThread;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.view.MvRockView;

/**
 * Created by Xuer on 5/5/15.
 */
public class YouLikedPlayListView extends PlayListView {
  public YouLikedPlayListView(){
        super(MvRockView.MainActivity);
        TAG += "YouLikedListView";
    }
    public boolean isAvailable(){return MvRockModel.playListOption== PlayListOption.YOU_LIKED_LIST;}

    public void setAvailable(){
        MvRockModel.playListOption=PlayListOption.YOU_LIKED_LIST;
    }

    public void RequestPlayListByThread(){
        Log.i(TAG, "RequestPlayListByThread()");
        GetYouLikedSongAndUserDataThread getYouLikedSongAndUserDataThread = new GetYouLikedSongAndUserDataThread(MvRockModel.User.User_Id,"");
        getYouLikedSongAndUserDataThread.start();
        try {
            getYouLikedSongAndUserDataThread.join();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        getYouLikedSongAndUserDataThread.setResponse();

    }

    public void RefreshListView(){
        Log.i(TAG, "RefreshListView()");
        MvRockModel.YouLikedSongList.convertData();
        YouLikedPlayListAdapter playListAdapter = new YouLikedPlayListAdapter(context,
                new String[] { "song_name","artist_name" },
                new int[] { R.id.song_name, R.id.artist_name });
        this.playListview.setAdapter(playListAdapter);
    }

    public void Init(){
        Log.i(TAG, "Init()");
        this.RefreshListView();
        MvRockModel.currentMVIndex = 0;
        this.playListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3) {
                Log.i(TAG, "onItemClick(" + arg0 + ", " + arg1 + ", " + position + ", " + arg3 + ")");
                setAvailable();
                MvRockModel.currentMVIndex = position;
                String selectedId = MvRockModel.YouLikedSongList.songArrayList.get(MvRockModel.currentMVIndex).get("url");
                MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.loadVideo(selectedId);
            }
        });
    }

}
