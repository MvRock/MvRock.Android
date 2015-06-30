package com.mvrock.android.uicomponent.playlist;

import android.view.View;
import android.widget.TextView;

import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;

/**
 * Created by Kenneth on 6/15/2015.
 */
public class SongView extends MvRockUiComponentObject {

    public TextView songNameView;
    public TextView recommendationTitleView;
    public TextView recommendationReasonView;

    @Override
    public void Init() {}

    public void update() {
        songNameView.setText(MvRockModel.CurrentSong.songName);
        recommendationReasonView.setText(MvRockModel.CurrentSong.reason.toString());
    }

    public void hideRecommendation() {
        recommendationTitleView.setVisibility(View.GONE);
        recommendationReasonView.setVisibility(View.GONE);
    }

    public void showRecommendation() {
        recommendationTitleView.setVisibility(View.VISIBLE);
        recommendationReasonView.setVisibility(View.VISIBLE);
    }
}
