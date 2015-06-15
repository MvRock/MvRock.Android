package com.mvrock.android.uicomponent.playlist;

import android.widget.ImageView;
import android.widget.TextView;

import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;

/**
 * Created by Kenneth on 6/15/2015.
 */
public class ArtistView extends MvRockUiComponentObject {

    public TextView artistNameView;
    public ImageView artistImageView;

    @Override
    public void Init() {}

    public void update() {
        artistImageView.setImageDrawable(MvRockModel.CurrentSong.artistImage);
        artistNameView.setText(MvRockModel.CurrentSong.artistName);
    }
}
