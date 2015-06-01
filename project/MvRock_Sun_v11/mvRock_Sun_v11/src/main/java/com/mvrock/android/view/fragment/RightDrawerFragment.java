package com.mvrock.android.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.playlist.YouMayLikePlayListView;
import com.mvrock.android.view.MvRockView;

/**
 * Created by Tianhao on 15/6/1.
 */
public class RightDrawerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rightDrawerView = inflater.inflate(R.layout.right_drawer,container,false);
        MvRockUiComponent.YouMayLikePlayListView = new YouMayLikePlayListView(MvRockView.MainActivity);
        MvRockUiComponent.YouMayLikePlayListView.playListview=(ListView) rightDrawerView.findViewById(R.id.play_list_11);
        MvRockUiComponent.YouMayLikePlayListView.Init();
        return rightDrawerView;
    }
}
