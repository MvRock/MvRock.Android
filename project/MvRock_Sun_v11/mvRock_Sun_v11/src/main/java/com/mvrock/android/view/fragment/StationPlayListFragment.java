package com.mvrock.android.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.playlist.StationPlayListView;
import com.mvrock.android.view.MvRockView;

/**
 * Created by Xuer on 6/2/15.
 */
public class StationPlayListFragment extends Fragment {

    private TextView title;
    private ImageView cancelButton;

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

        cancelButton = (ImageView) rightDrawerView.findViewById(R.id.right_drawer_button);
        cancelButton.setImageResource(R.drawable.red_close);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MvRockView.MainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.right_drawer, MvRockView.StationListFragment).commit();
                MvRockModel.CurrentSong.currentMVIndex = 0;
                MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.loadVideo(MvRockModel.YouMayLikeSongList.songArrayList.get(0).get("url"));
            }
        });

        return rightDrawerView;
    }
}
