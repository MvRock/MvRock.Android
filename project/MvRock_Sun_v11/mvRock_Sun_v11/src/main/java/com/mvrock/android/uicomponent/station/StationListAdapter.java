package com.mvrock.android.uicomponent.station;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Xuer on 6/11/15.
 */
public class StationListAdapter extends SimpleAdapter {

    protected String TAG;

    public StationListAdapter(Context context, ArrayList<Map<String, String>> station_info, String[] from, int[] to) {
        super(context, station_info, R.layout.station_list_adapter, from, to);
        TAG = "UIComponent.StationListAdapter";
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getView(" + position + ")");

        convertView = super.getView(position, convertView, parent);

        ((ImageView) convertView.findViewById(R.id.station_image)).setImageDrawable(MvRockModel.StationList.stationImageArrayList.get(position));

        return convertView;
    }
}

