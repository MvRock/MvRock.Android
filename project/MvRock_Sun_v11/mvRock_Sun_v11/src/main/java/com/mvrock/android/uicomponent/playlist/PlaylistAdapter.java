package com.mvrock.android.uicomponent.playlist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.thread.GetImageListThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class PlaylistAdapter extends SimpleAdapter {
	protected String TAG;
    protected ViewHolder holder= new ViewHolder();
    public Context context;
	@SuppressLint("UseSparseArrays")
	public PlaylistAdapter( Context context, ArrayList<Map<String, String>> song_info,
			String[] from, int[] to) {
        super(context,song_info , R.layout.play_list_adapter, from, to);
        this.TAG="UIComponent.";
        this.context = context;
    }
	
	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getView("+position+")");
		 holder= new ViewHolder();
		 if (convertView == null) {
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                convertView = inflater.inflate(R.layout.play_list_adapter, parent,false);
		        convertView = super.getView(position, convertView, parent);
		        holder = new ViewHolder();
		        holder.songname_textview = (TextView) convertView.findViewById(R.id.song_name);
		        holder.artistname_textview = (TextView) convertView.findViewById(R.id.artist_name);
		        holder.songname_imageview = (ImageView) convertView.findViewById(R.id.song_image);
		        convertView.setTag(holder);
		    } else {
		        holder =  (ViewHolder)convertView.getTag();
		    }
		return convertView;
	}

	protected static class ViewHolder {
	    public TextView songname_textview;
	    public TextView artistname_textview;
	    public ImageView songname_imageview;
	}
	

	
	
}