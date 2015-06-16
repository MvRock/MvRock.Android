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
import com.mvrock.android.uicomponent.station.StationListView;
import com.mvrock.android.uicomponent.station.fadeStationSearchview;

/**
 * Created by Xuer on 6/8/15.
 */
public class  StationListFragment extends Fragment {
    private static final String TAG = "View.StationListFrag";
    public StationListFragment() {

        MvRockUiComponent.StationListView = new StationListView();
        MvRockUiComponent.fadeStationSearchview=new fadeStationSearchview();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rightDrawerView = inflater.inflate(R.layout.fragment_right_drawer_station_list,container,false);
        MvRockUiComponent.StationListView.StationListview=(ListView) rightDrawerView.findViewById(R.id.station_list);
        MvRockUiComponent.StationListView.Init();

        MvRockUiComponent.noResultTextViewOnStationFrag=(TextView)rightDrawerView.findViewById(R.id.no_result);

        MvRockUiComponent.fadeStationSearchview.topSearchView = (SearchView)rightDrawerView.findViewById(R.id.search_stations);
        MvRockUiComponent.fadeStationSearchview.Init();
        return rightDrawerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
        MvRockUiComponent.StationListView.RefreshListView();
    }

}
