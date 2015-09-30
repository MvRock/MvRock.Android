package com.mvrock.android.uicomponent.player;

import android.util.Log;

import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.thread.SetRatingThread;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;

/**
 * Created by Xuer on 5/19/15.
 */
public abstract class PlayerControlButton extends MvRockUiComponentObject {

    public void PostRatingByThread(int flag) {
        Log.i(TAG, "PostRatingByThread(" + flag + ")");

        SetRatingThread setRatingThread = new SetRatingThread(MvRockModel.User.User_Id, MvRockModel.CurrentSong.url, flag);
        setRatingThread.start();
    }

    public void playNextSongAfterRemovedASongFromYoulikedList() {
        Log.i(TAG, "playNextSongAfterRemovedASongFromYoulikedList()");

//        if(MvRockModel.YouLikedSongList.songArrayList.size() == 0){
//            //load the first video of the YouMayLikedSongList
//        }else if(MvRockModel.CurrentSong.currentMVIndex == MvRockModel.YouLikedSongList.songArrayList.size()){
//            String first = MvRockModel.YouLikedSongList.songArrayList.get(0).get("url");
//            MvRockModel.CurrentSong.currentMVIndex = 0;
//            MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.loadVideo(first);
//        }else{
//            String next = MvRockModel.YouLikedSongList.songArrayList.get(MvRockModel.CurrentSong.currentMVIndex).get("url");
//            MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.loadVideo(next);
//        }

        if (MvRockModel.YouLikedSongList.songArrayList.size() > 0) {
            if (MvRockModel.CurrentSong.currentMVIndex < MvRockModel.YouLikedSongList.songArrayList.size()) {
                String next = MvRockModel.YouLikedSongList.songArrayList.get(MvRockModel.CurrentSong.currentMVIndex).get("url");
                MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.loadVideo(next);
            } else {
                String first = MvRockModel.YouLikedSongList.songArrayList.get(0).get("url");
                MvRockModel.CurrentSong.currentMVIndex = 0;
                MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.loadVideo(first);
            }
        }
    }
}
