package com.mvrock.android.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.playlist.YouMayLikePlayListView;

/**
 * Created by Tianhao on 15/6/1.
 */
public class YouMayLikePlayListFragment extends Fragment {
    private static final String TAG = "View.YMLPlayListFrag";
    public YouMayLikePlayListFragment() {
        Log.i(TAG,"YouMayLikePlayListFragment()");
        MvRockUiComponent.YouMayLikePlayListView = new YouMayLikePlayListView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView()");
        View rightDrawerView = inflater.inflate(R.layout.fragment_right_drawer_play_list,container,false);
        MvRockUiComponent.YouMayLikePlayListView.playListview=(ListView) rightDrawerView.findViewById(R.id.right_drawer_play_list);
        MvRockUiComponent.YouMayLikePlayListView.Init();
        return rightDrawerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
        MvRockUiComponent.YouMayLikePlayListView.RefreshListView();
    }
}
