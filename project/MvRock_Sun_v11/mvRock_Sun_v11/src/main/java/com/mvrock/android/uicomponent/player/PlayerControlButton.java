package com.mvrock.android.uicomponent.player;

import android.util.Log;

import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.thread.SetRatingThread_old;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;

/**
 * Created by Xuer on 5/19/15.
 */
public abstract class PlayerControlButton extends MvRockUiComponentObject {
    public void PostRatingByThread(int flag){
        Log.i(TAG, "PostRatingByThread(" + flag + ")");
        String url;
        if ( MvRockUiComponent.YouMayLikePlayListView.isAvailable()) {
            url= MvRockModel.YouMayLikeSongList.songArrayList.get(MvRockModel.currentMVIndex).get("url");
        } else {
            url= MvRockModel.YouLikedSongList.songArrayList.get(MvRockModel.currentMVIndex).get("url");
        }

        Thread setRatingThread = new Thread(new SetRatingThread_old(MvRockModel.User.User_Id,url,flag));
        setRatingThread.start();
    }

    public void playNextSongAfterRemovedASongFromYoulikedList(){
        Log.i(TAG, "playNextSongAfterRemovedASongFromYoulikedList()");
        if(MvRockModel.YouLikedSongList.songArrayList.size()>0)
        {
            if (MvRockModel.currentMVIndex <= MvRockModel.YouLikedSongList.songArrayList.size() - 1) {
                String next = MvRockModel.YouLikedSongList.songArrayList.get(MvRockModel.currentMVIndex).get("url");
                MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.loadVideo(next);

            } else {
                String first = MvRockModel.YouLikedSongList.songArrayList.get(0).get("url");
                MvRockModel.currentMVIndex = 0;
                MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.loadVideo(first);
            }
        }

    }

}
