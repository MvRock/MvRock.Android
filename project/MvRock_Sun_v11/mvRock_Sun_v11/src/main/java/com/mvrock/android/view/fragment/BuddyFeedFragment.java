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
import com.mvrock.android.uicomponent.socialstuff.BuddyFeedListView;

/**
 * Created by Xuer on 8/11/15.
 */
public class BuddyFeedFragment extends Fragment {
    private static final String TAG = "View.BuddyFeedFrag";


    public BuddyFeedFragment() {
        Log.i(TAG, "BuddyFeedFragment()");

        MvRockUiComponent.BuddyFeedListView = new BuddyFeedListView();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView()");

        View rightDrawerView = inflater.inflate(R.layout.fragment_right_drawer_play_list, container, false);

        MvRockUiComponent.BuddyFeedListView.buddyFeedListView = (ListView) rightDrawerView.findViewById(R.id.right_drawer_listview);
        MvRockUiComponent.BuddyFeedListView.Init();


        update();

        return rightDrawerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
        MvRockUiComponent.YouMayLikePlayListView.RefreshListView();
    }

    public void update() {

    }
}
