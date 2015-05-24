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
    public NextSongButton(){TAG+="NextSongButton";}
    public void Init(){
        Log.i(TAG,"Init()");
        nextSongImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("nextSongImage", "onClick()");
                if ( MvRockUiComponent.YouMayLikePlayListView.isAvailable()) {
                    if (MvRockModel.currentMVIndex < 14) {
                        MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.loadVideo(MvRockModel.YouLikedSongList.songArrayList.get(MvRockModel.currentMVIndex + 1).get("url"));
                        MvRockModel.currentMVIndex++;
                    } else {
                        MvRockUiComponent.YouMayLikePlayListView.RequestPlayListByThread();
                        MvRockModel.currentMVIndex = 0;
                        MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.loadVideo(MvRockModel.YouLikedSongList.songArrayList.get(0).get("url"));
                    }
                } else {
                    if(MvRockModel.YouLikedSongList.songArrayList.size()>0)
                    {
                        if (MvRockModel.currentMVIndex < MvRockModel.YouLikedSongList.songArrayList.size() - 1) {
                            String next = MvRockModel.YouLikedSongList.songArrayList.get(MvRockModel.currentMVIndex + 1).get("url");
                            MvRockModel.currentMVIndex++;
                            MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.loadVideo(next);

                        } else {
                            String first = MvRockModel.YouLikedSongList.songArrayList.get(0).get("url");
                            MvRockModel.currentMVIndex = 0;
                            MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.loadVideo(first);
                        }
                    }
                }
            }
        });
    }

}
