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
import com.mvrock.android.uicomponent.playlist.YouLikedPlayListView;

/**
 * Created by Xuer on 6/2/15.
 */
public class YouLikedPlayListFragment extends Fragment{
    private static final String TAG = "View.YLPlayListFrag";
    public YouLikedPlayListFragment() {
        Log.i(TAG, "YouLikedPlayListFragment()");
        MvRockUiComponent.YouLikedPlayListView = new YouLikedPlayListView();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView()");
        View rightDrawerView = inflater.inflate(R.layout.fragment_right_drawer_play_list,container,false);
       MvRockUiComponent.YouLikedPlayListView.playListview=(ListView) rightDrawerView.findViewById(R.id.right_drawer_item);
        MvRockUiComponent.YouLikedPlayListView.Init();
        return rightDrawerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
        MvRockUiComponent.YouLikedPlayListView.RefreshListView();
    }
}
