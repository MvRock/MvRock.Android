package com.mvrock.android.uicomponent.playlist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import com.mvrock.android.model.MvRockModel;

import java.util.Map;

/**
 * Created by Xuer on 5/10/15.
 */
public class StationPlayListAdapter extends PlaylistAdapter{
    public StationPlayListAdapter( Context context,String[] from, int[] to) {
        super(context, MvRockModel.StationSongList.songArrayList , from, to);
        TAG+="StationPlayListAdapter";

    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=super.getView(position,convertView,parent);
        Map<String, String> map = MvRockModel.StationSongList.songArrayList.get(position);
        holder.songname_textview.setText(map.get("song_name"));
        holder.artistname_textview.setText(map.get("artist_name"));
        holder.songname_imageview.setImageDrawable(((Drawable)MvRockModel.StationSongList.imageViewList.get(position)));
        return convertView;
    }
}