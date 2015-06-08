package com.mvrock.android.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.playlist.StationPlayListView;
import com.mvrock.android.uicomponent.station.StationListView;

/**
 * Created by Xuer on 6/8/15.
 */
public class  StationListFragment extends Fragment {
    public StationListFragment() {
        MvRockUiComponent.StationListView = new StationListView();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rightDrawerView = inflater.inflate(R.layout.fragment_right_drawer_play_list,container,false);
        MvRockUiComponent.StationPlayListView.playListview=(ListView) rightDrawerView.findViewById(R.id.right_drawer_item);
        MvRockUiComponent.StationPlayListView.Init();
        return rightDrawerView;
    }

}
