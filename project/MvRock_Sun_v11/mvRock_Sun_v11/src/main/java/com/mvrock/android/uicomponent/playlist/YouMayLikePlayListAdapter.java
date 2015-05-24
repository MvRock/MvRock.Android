package com.mvrock.android.uicomponent.playlist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;

import java.util.Map;

/**
 * Created by Xuer on 5/10/15.
 */
public class YouMayLikePlayListAdapter extends PlaylistAdapter {
    public YouMayLikePlayListAdapter( Context context,String[] from, int[] to) {
        super(context,MvRockModel.YouMayLikeSongList.songArrayList , from, to);
        TAG+="YouMayLikePlayListAdapter";
        MvRockModel.YouMayLikeSongList.imageViewList=RequestImageListByThread(MvRockModel.YouMayLikeSongList.songArrayList);
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=super.getView(position, convertView, parent);
        Map<String, String> map = MvRockModel.YouMayLikeSongList.songArrayList.get(position);
        holder.songname_textview.setText(map.get("song_name"));
        holder.artistname_textview.setText(map.get("artist_name"));
        holder.songname_imageview.setImageDrawable(((Drawable)MvRockModel.YouMayLikeSongList.imageViewList.get(position)) );
        return convertView;
    }

}
