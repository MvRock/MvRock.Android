package com.mvrock.android.uicomponent.station;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.examples.youtubeapidemo.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Xuer on 6/11/15.
 */
public class StationListAdapter extends SimpleAdapter {

    protected String TAG;
    protected ViewHolder holder= new ViewHolder();
    public Context context;
    @SuppressLint("UseSparseArrays")
    public StationListAdapter( Context context, ArrayList<Map<String, String>> station_info,
                            String[] from, int[] to) {
        super(context,station_info , R.layout.station_list_adapter, from, to);
        this.TAG="UIComponent.";
        TAG+="StationListAdapter";
        this.context = context;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getView(" + position + ")");
        holder= new ViewHolder();
        if (convertView == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.station_list_adapter, parent,false);
            convertView = super.getView(position, convertView, parent);
            holder = new ViewHolder();
            holder.stationname_textview = (TextView) convertView.findViewById(R.id.station_name);
            holder.station_imageview = (ImageView) convertView.findViewById(R.id.station_image);
            convertView.setTag(holder);
        } else {
            holder =  (ViewHolder)convertView.getTag();
        }
        return convertView;
    }

    protected static class ViewHolder {
        public TextView stationname_textview;
        public ImageView station_imageview;
    }




}

