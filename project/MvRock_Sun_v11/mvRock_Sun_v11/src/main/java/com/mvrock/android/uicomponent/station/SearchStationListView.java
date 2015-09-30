package com.mvrock.android.uicomponent.station;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;
import com.mvrock.android.view.MvRockView;

import static android.util.Log.i;

/**
 * Created by Xuer on 6/11/15.
 */
public class SearchStationListView extends MvRockUiComponentObject {
    public ListView SearchStationListView;
    public TextView noSearchResults;
    public boolean hasResults;

    public SearchStationListView() {
        TAG += "SearchStationListView";
    }

    public void Init() {
        i(TAG, "Init()");

        // invisible at first
        SearchStationListView.setVisibility(View.GONE);
    }

    public void RefreshListView() {
        Log.i(TAG, "RefreshListView()");

        if (MvRockModel.SearchStationList.searchStationArrayList != null && hasResults) {
            SearchStationListAdapter SearchStationListAdapter = new SearchStationListAdapter(MvRockView.MainActivity, MvRockModel.SearchStationList.searchStationArrayList,
                    new String[]{"station_name"},
                    new int[]{R.id.station_name});
            SearchStationListView.setAdapter(SearchStationListAdapter);

            SearchStationListView.setVisibility(View.VISIBLE);
            noSearchResults.setVisibility(View.GONE);
        } else {
            SearchStationListView.setVisibility(View.GONE);
            noSearchResults.setVisibility(View.VISIBLE);
        }
    }
}