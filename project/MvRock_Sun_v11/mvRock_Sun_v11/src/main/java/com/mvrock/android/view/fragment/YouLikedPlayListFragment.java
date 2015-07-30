package com.mvrock.android.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.playlist.YouLikedPlayListView;
import com.mvrock.android.view.MvRockView;

/**
 * Created by Xuer on 6/2/15.
 */
public class YouLikedPlayListFragment extends Fragment {

    private static final String TAG = "View.YLPlayListFrag";

    private TextView title;
    private ImageView refreshButton;

    public YouLikedPlayListFragment() {
        Log.i(TAG, "YouLikedPlayListFragment()");

        MvRockUiComponent.YouLikedPlayListView = new YouLikedPlayListView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView()");

        View rightDrawerView = inflater.inflate(R.layout.fragment_right_drawer_play_list, container, false);

        MvRockUiComponent.YouLikedPlayListView.playListview = (ListView) rightDrawerView.findViewById(R.id.right_drawer_listview);
        MvRockUiComponent.YouLikedPlayListView.playListview.setEmptyView(rightDrawerView.findViewById(R.id.you_like_empty));
        MvRockUiComponent.YouLikedPlayListView.Init();

        title = (TextView) rightDrawerView.findViewById(R.id.right_drawer_title);
        title.setText("You Liked");

        refreshButton = (ImageView) rightDrawerView.findViewById(R.id.right_drawer_button);
        refreshButton.setImageResource(R.drawable.ic_menu_refresh);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MvRockView.MainActivity, "Refreshing list", Toast.LENGTH_SHORT).show();

                MvRockUiComponent.YouLikedPlayListView.RequestPlayListByThread();
                MvRockUiComponent.YouLikedPlayListView.RefreshListView();
            }
        });

        return rightDrawerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
        // MvRockUiComponent.YouLikedPlayListView.RequestPlayListByThread();
        MvRockUiComponent.YouLikedPlayListView.RefreshListView();
    }
}
