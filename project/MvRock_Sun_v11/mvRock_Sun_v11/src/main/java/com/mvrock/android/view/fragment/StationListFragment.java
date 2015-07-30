package com.mvrock.android.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.station.SearchStationListView;
import com.mvrock.android.uicomponent.station.StationListView;
import com.mvrock.android.uicomponent.station.StationSearchView;
import com.mvrock.android.view.MvRockView;

/**
 * Created by Xuer on 6/8/15.
 */
public class StationListFragment extends Fragment {

    private static final String TAG = "View.StationListFrag";

    public TextView title;
    public ImageView refreshButton;

    public StationListFragment() {
        MvRockUiComponent.StationListView = new StationListView();
        MvRockUiComponent.SearchStationListView = new SearchStationListView();
        MvRockUiComponent.StationSearchView = new StationSearchView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rightDrawerView = inflater.inflate(R.layout.fragment_right_drawer_station_list, container, false);

        MvRockUiComponent.StationListView.StationListview = (ListView) rightDrawerView.findViewById(R.id.station_list);
        MvRockUiComponent.StationListView.noStations = (TextView) rightDrawerView.findViewById(R.id.no_stations);
        MvRockUiComponent.StationListView.Init();

        MvRockUiComponent.SearchStationListView.SearchStationListView = (ListView) rightDrawerView.findViewById(R.id.station_search_list);
        MvRockUiComponent.SearchStationListView.noSearchResults = (TextView) rightDrawerView.findViewById(R.id.no_result);
        MvRockUiComponent.SearchStationListView.Init();

        MvRockUiComponent.StationSearchView.topSearchView = (SearchView) rightDrawerView.findViewById(R.id.search_stations);
        MvRockUiComponent.StationSearchView.Init();

        title = (TextView) rightDrawerView.findViewById(R.id.right_drawer_title);
        title.setText("Stations");

        refreshButton = (ImageView) rightDrawerView.findViewById(R.id.right_drawer_button);
        refreshButton.setImageResource(R.drawable.ic_menu_refresh);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MvRockView.MainActivity, "Refreshing list", Toast.LENGTH_SHORT).show();

                MvRockUiComponent.StationListView.RequestStationByThread();
                MvRockUiComponent.StationListView.RefreshListView();
            }
        });

        MvRockUiComponent.StationListView.RefreshListView();

        return rightDrawerView;
    }
}
