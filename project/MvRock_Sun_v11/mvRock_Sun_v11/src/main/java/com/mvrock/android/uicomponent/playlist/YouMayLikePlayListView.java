package com.mvrock.android.uicomponent.playlist;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.model.PlayListOption;
import com.mvrock.android.thread.GetYoumaylikePlayListThread;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.view.fragment.MvRockFragment;
import com.mvrock.android.view.MvRockView;


/**
 * Created by Xuer on 5/5/15.
 */
public class YouMayLikePlayListView extends PlayListView {


    public YouMayLikePlayListView() {
        super(MvRockView.MainActivity);
        TAG += "YouMayLikeListView";
    }

    public boolean isAvailable(){return MvRockModel.playListOption== PlayListOption.YOU_MAY_LIKE_LIST;}

    public void setAvailable(){MvRockModel.playListOption=PlayListOption.YOU_MAY_LIKE_LIST; }

    public void RequestPlayListByThread(){
        Log.i(TAG, "RequestPlayListByThread()");
        GetYoumaylikePlayListThread getYoumaylikeSongDataThread = new GetYoumaylikePlayListThread(MvRockModel.User.User_Id,"");
        getYoumaylikeSongDataThread.start();
        try {
            getYoumaylikeSongDataThread.join();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        getYoumaylikeSongDataThread.setResponse();
        MvRockModel.YouMayLikeSongList.convertData();
    }

    public void RefreshListView(){
        Log.i(TAG, "RefreshListView()");

        YouMayLikePlayListAdapter playListAdapter = new YouMayLikePlayListAdapter(context,
                new String[] { "song_name","artist_name" },
                new int[] { R.id.song_name, R.id.artist_name });
        this.playListview.setAdapter(playListAdapter);
    }

    public void Init(){
        Log.i(TAG, "Init()");
        this.RefreshListView();
        MvRockModel.CurrentSong.currentMVIndex = 0;
        this.playListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3) {
                Log.i(TAG, "onItemClick(" + arg0 + ", " + arg1 + ", " + position + ", " + arg3 + ")");
                setAvailable();
                MvRockModel.CurrentSong.isChanged=true;
                MvRockModel.CurrentSong.currentMVIndex = position;
                MvRockModel.CurrentSong.url = MvRockModel.YouMayLikeSongList.songArrayList.get(MvRockModel.CurrentSong.currentMVIndex).get("url");
                MvRockModel.CurrentSong.currentTime=0;
                MvRockUiComponent.MvRockDrawer.mDrawerLayout.closeDrawer(Gravity.RIGHT);
                MvRockUiComponent.RightFloatingMenu.actionMenu.close(true);
                MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer
                        .loadVideo(MvRockModel.CurrentSong.url, MvRockModel.CurrentSong.currentTime);

            }
        });
    }
}
