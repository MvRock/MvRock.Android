package com.mvrock.android.uicomponent.playlist;

import android.util.Log;
import android.view.Gravity;
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
    public YouLikedPlayListView() {
        super();
        TAG += "YouLikedListView";
    }

    public boolean isAvailable() {
        return MvRockModel.playListOption == PlayListOption.YOU_LIKED_LIST;
    }

    public void setAvailable() {
        MvRockModel.playListOption = PlayListOption.YOU_LIKED_LIST;
    }

    public void RequestPlayListByThread() {
        Log.i(TAG, "RequestBuddyFeedByThread()");
        GetYouLikedSongAndUserDataThread getYouLikedSongAndUserDataThread = new GetYouLikedSongAndUserDataThread(MvRockModel.User.User_Id, null);
        getYouLikedSongAndUserDataThread.start();
        try {
            getYouLikedSongAndUserDataThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getYouLikedSongAndUserDataThread.setResponse();
        MvRockModel.YouLikedSongList.convertData();
    }

    public void RefreshListView() {
        Log.i(TAG, "RefreshListView()");
        if (MvRockModel.YouLikedSongList.songArrayList != null)
            MvRockModel.YouLikedSongList.imageViewList = RequestImageListByThread(MvRockModel.YouLikedSongList.songArrayList);
        YouLikedPlayListAdapter playListAdapter = new YouLikedPlayListAdapter(MvRockView.MainActivity,
                new String[]{"song_name", "artist_name"},
                new int[]{R.id.song_name, R.id.artist_name});
        this.playListview.setAdapter(playListAdapter);
    }

    public void Init() {
        Log.i(TAG, "Init()");
        this.RefreshListView();
        MvRockModel.CurrentSong.currentMVIndex = 0;
        this.playListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Log.i(TAG, "onItemClick(" + arg0 + ", " + arg1 + ", " + position + ", " + arg3 + ")");
                setAvailable();
                MvRockModel.CurrentSong.currentMVIndex = position;
                MvRockModel.CurrentSong.url = MvRockModel.YouLikedSongList.songArrayList.get(MvRockModel.CurrentSong.currentMVIndex).get("url");
                MvRockModel.CurrentSong.currentTime = 0;
                MvRockUiComponent.MvRockDrawer.mDrawerLayout.closeDrawer(Gravity.RIGHT);
                MvRockUiComponent.RightFloatingMenu.actionMenu.close(true);
                MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer
                        .loadVideo(MvRockModel.CurrentSong.url, MvRockModel.CurrentSong.currentTime);
            }
        });
    }
}
