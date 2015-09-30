package com.mvrock.android.uicomponent.player;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;

/**
 * Created by Xuer on 5/5/15.
 */
public class NextSongButton extends MvRockUiComponentObject {

    public ImageView nextSongImage;

    public NextSongButton() {
        TAG += "NextSongButton";
    }

    public void Init() {
        Log.i(TAG, "Init()");

        nextSongImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("nextSongImage", "onClick()");

                if (MvRockUiComponent.YouMayLikePlayListView.isAvailable()) {
                    if (MvRockModel.CurrentSong.currentMVIndex < MvRockModel.YouMayLikeSongList.songArrayList.size() - 1) {
                        MvRockModel.CurrentSong.currentMVIndex++;
                        String next = MvRockModel.YouMayLikeSongList.songArrayList.get(MvRockModel.CurrentSong.currentMVIndex).get("url");
                        MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.loadVideo(next);
                    } else {
                        MvRockUiComponent.YouMayLikePlayListView.RequestPlayListByThread();
                        MvRockUiComponent.YouMayLikePlayListView.RefreshListView();
                        MvRockModel.CurrentSong.currentMVIndex = 0;
                        MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.loadVideo(MvRockModel.YouMayLikeSongList.songArrayList.get(0).get("url"));
                    }
                } else if (MvRockUiComponent.YouLikedPlayListView.isAvailable()) {
                    if (MvRockModel.YouLikedSongList.songArrayList.size() > 0) {
                        if (MvRockModel.CurrentSong.currentMVIndex < MvRockModel.YouLikedSongList.songArrayList.size() - 1) { // not the last song, play next
                            MvRockModel.CurrentSong.currentMVIndex++;
                            String next = MvRockModel.YouLikedSongList.songArrayList.get(MvRockModel.CurrentSong.currentMVIndex).get("url");
                            MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.loadVideo(next);
                        } else { // last song, start from the top
                            String first = MvRockModel.YouLikedSongList.songArrayList.get(0).get("url");
                            MvRockModel.CurrentSong.currentMVIndex = 0;
                            MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.loadVideo(first);
                        }
                    }
                } else if (MvRockUiComponent.StationPlayListView.isAvailable()) {
                    if (MvRockModel.CurrentSong.currentMVIndex < MvRockModel.StationSongList.songArrayList.size() - 1) {
                        MvRockModel.CurrentSong.currentMVIndex++;
                        String next = MvRockModel.StationSongList.songArrayList.get(MvRockModel.CurrentSong.currentMVIndex).get("url");
                        MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.loadVideo(next);
                    } else { // begin at top of station list
                        String first = MvRockModel.StationSongList.songArrayList.get(0).get("url");
                        MvRockModel.CurrentSong.currentMVIndex = 0;
                        MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.loadVideo(first);
                    }
                }
            }
        });
    }
}
