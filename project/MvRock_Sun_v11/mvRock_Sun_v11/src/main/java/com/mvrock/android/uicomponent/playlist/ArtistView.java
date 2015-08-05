package com.mvrock.android.uicomponent.playlist;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;
import com.mvrock.android.view.MvRockView;

/**
 * Created by Kenneth on 6/15/2015.
 */
public class ArtistView extends MvRockUiComponentObject {

    public TextView artistNameView;
    public ImageView artistImageView;
    public Button subscribeButton;

    @Override
    public void Init() {
        subscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MvRockModel.CurrentSong.isArtistSubscribed) {
                    MvRockModel.CurrentSong.isArtistSubscribed = !MvRockModel.CurrentSong.isArtistSubscribed;
                    MvRockModel.StationList.removeStationByThread(MvRockModel.CurrentSong.artistName);

                    if (MvRockUiComponent.StationPlayListView.isAvailable()) {
                        // station is playing, we should switch back to you may like since
                        // the user just unsubscribed from the station

                        MvRockView.MainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.right_drawer, MvRockView.StationListFragment).commit();
                        MvRockModel.CurrentSong.currentMVIndex = 0;
                        MvRockUiComponent.YouMayLikePlayListView.setAvailable();
                        MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.loadVideo(MvRockModel.YouMayLikeSongList.songArrayList.get(0).get("url"));
                    }
                } else {
                    MvRockModel.CurrentSong.isArtistSubscribed = !MvRockModel.CurrentSong.isArtistSubscribed;
                    MvRockModel.StationList.createStationByThread(MvRockModel.CurrentSong.artistName);
                }

                update();
                MvRockUiComponent.StationListView.RequestStationByThread();
                MvRockUiComponent.StationListView.RefreshListView();
            }
        });
    }

    public void update() {
        artistImageView.setImageDrawable(MvRockModel.CurrentSong.artistImage);
        artistNameView.setText(MvRockModel.CurrentSong.artistName);

        if (MvRockModel.CurrentSong.isArtistSubscribed) {
            subscribeButton.setText("Unsubscribe");
            subscribeButton.setBackgroundColor(Color.argb(191, 255, 0, 0)); // red
        } else {
            subscribeButton.setText("Subscribe");
            subscribeButton.setBackgroundColor(Color.argb(191, 0, 255, 0)); // green
        }
    }
}
