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
public class YouMayLikePlayListAdapter extends PlaylistAdapter {
    public YouMayLikePlayListAdapter(Context context, String[] from, int[] to) {
        super(context, MvRockModel.YouMayLikeSongList.songArrayList, from, to);
        TAG += "YouMayLikePlayListAdapter";
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);

        ((ImageView) convertView.findViewById(R.id.song_image)).setImageDrawable(MvRockModel.YouMayLikeSongList.imageViewList.get(position));

        return convertView;
    }

}
