package com.mvrock.android.uicomponent.playlist;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.model.PlayListOption;
import com.mvrock.android.thread.GetStationSongsThread;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.view.MvRockView;

/**
 * Created by Xuer on 5/5/15.
 */
public class StationPlayListView extends PlayListView {
public StationPlayListView(){
        super(MvRockView.MainActivity);
        TAG += "StationPlayListView";
    }
    public boolean isAvailable(){
        return MvRockModel.playListOption== PlayListOption.STATION_LIST;
    }

    public void setAvailable(){
        MvRockModel.playListOption=PlayListOption.STATION_LIST;
    }

    public void RequestPlayListByThread(){
        Log.i(TAG, "RequestPlayListByThread()");
        GetStationSongsThread getStationSongsThread = new GetStationSongsThread(MvRockModel.User.User_Id,MvRockModel.CurrentStation);
        getStationSongsThread.start();
        try {
            getStationSongsThread.join();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        getStationSongsThread.setResponse();
    }

    public void RefreshListView(){
        Log.i(TAG, "RefreshListView()");
        MvRockModel.StationSongList.convertData();
        StationPlayListAdapter playListAdapter = new StationPlayListAdapter(context,
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
                MvRockModel.CurrentSong.currentMVIndex = position;
                MvRockModel.CurrentSong.url = MvRockModel.StationSongList.songArrayList.get(MvRockModel.CurrentSong.currentMVIndex).get("url");
                MvRockModel.CurrentSong.currentTime=0;
                MvRockUiComponent.MvRockDrawer.mDrawerLayout.closeDrawer(Gravity.RIGHT);
                MvRockUiComponent.RightFloatingMenu.actionMenu.close(true);
                MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer
                        .loadVideo(MvRockModel.CurrentSong.url, MvRockModel.CurrentSong.currentTime);
            }
        });
    }
}
