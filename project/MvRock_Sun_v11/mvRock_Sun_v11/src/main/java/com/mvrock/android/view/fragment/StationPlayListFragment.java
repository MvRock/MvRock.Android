package com.mvrock.android.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.playlist.StationPlayListView;

/**
 * Created by Xuer on 6/2/15.
 */
public class StationPlayListFragment extends Fragment {

    private TextView title;

    public StationPlayListFragment() {
        MvRockUiComponent.StationPlayListView = new StationPlayListView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rightDrawerView = inflater.inflate(R.layout.fragment_right_drawer_play_list, container, false);

        MvRockUiComponent.StationPlayListView.playListview = (ListView) rightDrawerView.findViewById(R.id.right_drawer_listview);
        MvRockUiComponent.StationPlayListView.Init();

        title = (TextView) rightDrawerView.findViewById(R.id.right_drawer_title);
        title.setText(MvRockModel.CurrentStation);

        return rightDrawerView;
    }
}
