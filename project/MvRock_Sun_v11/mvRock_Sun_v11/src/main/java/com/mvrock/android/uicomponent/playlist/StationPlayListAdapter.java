package com.mvrock.android.uicomponent.playlist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;

/**
 * Created by Xuer on 5/10/15.
 */
public class StationPlayListAdapter extends PlaylistAdapter {

    public StationPlayListAdapter(Context context, String[] from, int[] to) {
        super(context, MvRockModel.StationSongList.songArrayList, from, to);
        TAG += "StationPlayListAdapter";
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);

        ((ImageView) convertView.findViewById(R.id.song_image)).setBackgroundDrawable(MvRockModel.StationSongList.imageViewList.get(position));

        return convertView;
    }
}