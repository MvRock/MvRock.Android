package com.mvrock.android.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.station.SearchStationListView;
import com.mvrock.android.uicomponent.station.StationSearchView;

/**
 * Created by Xuer on 6/10/15.
 */
public class SearchStationFragment extends Fragment {
    private static final String TAG = "View.SearchStationLFrag";
    public SearchStationFragment() {
        MvRockUiComponent.SearchStationListView = new SearchStationListView();
        MvRockUiComponent.StationSearchView=new StationSearchView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rightDrawerView = inflater.inflate(R.layout.fragment_right_drawer_station_list,container,false);
        MvRockUiComponent.SearchStationListView.SearchStationListview=(ListView) rightDrawerView.findViewById(R.id.station_list);
        MvRockUiComponent.SearchStationListView.Init();


        MvRockUiComponent.noResultTextViewOnSearchTextView =(TextView)rightDrawerView.findViewById(R.id.no_result);

        MvRockUiComponent.StationSearchView.topSearchView = (SearchView)rightDrawerView.findViewById(R.id.search_stations);
        MvRockUiComponent.StationSearchView.Init();
        return rightDrawerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
        MvRockUiComponent.SearchStationListView.RefreshListView();
    }

}
