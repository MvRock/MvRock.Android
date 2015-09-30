package com.mvrock.android.uicomponent.station;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleAdapter;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.uicomponent.MvRockUiComponent;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Xuer on 6/11/15.
 */
public class SearchStationListAdapter extends SimpleAdapter {

    protected String TAG;

    public SearchStationListAdapter(Context context, ArrayList<Map<String, String>> station_info, String[] from, int[] to) {
        super(context, station_info, R.layout.search_station_list_adapter, from, to);

        TAG = "UIComponent.SearchStationListAdapter";
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getView(" + position + ")");

        convertView = super.getView(position, convertView, parent);

        final Button subscribeButton = (Button) convertView.findViewById(R.id.sub_or_unsub);

        if (MvRockModel.SearchStationList.subscribeList[position] == 0) {
            subscribeButton.setText("Subscribe");
            subscribeButton.setBackgroundColor(Color.argb(191, 0, 255, 0)); // green
        } else {
            subscribeButton.setText("Unsubscribe");
            subscribeButton.setBackgroundColor(Color.argb(191, 255, 0, 0)); // red
        }

        subscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, Integer.toString(position));

                if (MvRockModel.SearchStationList.subscribeList[position] == 0) {
                    MvRockModel.StationList.createStationByThread(MvRockModel.SearchStationList.searchStationArrayList.get(position).get("station_name"));

                    subscribeButton.setText("Unsubscribe");
                    subscribeButton.setBackgroundColor(Color.argb(191, 255, 0, 0)); // red
                } else {
                    MvRockModel.StationList.removeStationByThread(MvRockModel.SearchStationList.searchStationArrayList.get(position).get("station_name"));

                    subscribeButton.setText("Subscribe");
                    subscribeButton.setBackgroundColor(Color.argb(191, 0, 255, 0)); // green
                }

                MvRockModel.SearchStationList.subscribeList[position] = 1 - MvRockModel.SearchStationList.subscribeList[position];

                MvRockUiComponent.StationListView.RequestStationByThread();
                MvRockUiComponent.StationListView.RefreshListView();
                MvRockUiComponent.StationListView.StationListview.setVisibility(View.GONE);
                MvRockUiComponent.StationListView.noStations.setVisibility(View.GONE);
            }
        });

        return convertView;
    }
}
