package com.mvrock.android.uicomponent.station;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.thread.CreateStationThread;
import com.mvrock.android.thread.GetStationImageListThread;
import com.mvrock.android.thread.GetStationThread;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;
import com.mvrock.android.view.MvRockView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.util.Log.i;

/**
 * Created by Xuer on 5/24/15.
 */
public class StationListView extends MvRockUiComponentObject {

    public ListView StationListview;
    public TextView noStations;

    public StationListView() {
        TAG += "StationListView";
//        RequestStationByThread();
    }

    public void Init() {
        i(TAG, "Init()");
        StationListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                i(TAG, "onItemClick()");

                MvRockModel.CurrentStation = MvRockModel.StationList.stationArrayList.get(i).get("station_name");
                MvRockUiComponent.StationPlayListView.RequestPlayListByThread();

                MvRockView.MainActivity.getSupportFragmentManager().beginTransaction().
                        replace(R.id.right_drawer, MvRockView.StationPlayListFragment).commit();

                MvRockModel.CurrentSong.currentMVIndex = 0;
                MvRockUiComponent.StationPlayListView.setAvailable();
                MvRockModel.CurrentSong.url = MvRockModel.StationSongList.songArrayList.get(MvRockModel.CurrentSong.currentMVIndex).get("url");
                MvRockModel.CurrentSong.currentTime = 0;

                MvRockUiComponent.MvRockDrawer.mDrawerLayout.closeDrawer(Gravity.RIGHT);
                MvRockUiComponent.RightFloatingMenu.actionMenu.close(true);
                MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer
                        .loadVideo(MvRockModel.CurrentSong.url, MvRockModel.CurrentSong.currentTime);

            }
        });
    }

    public void RefreshListView() {
        Log.i(TAG, "RefreshListView()");
        if (MvRockModel.StationList.stationArrayList != null && !MvRockModel.StationList.stationArrayList.isEmpty()) {
            MvRockModel.StationList.stationImageArrayList = RequestStationImageListByThread(MvRockModel.StationList.stationArrayList);
            StationListAdapter stationListAdapter = new StationListAdapter(MvRockView.MainActivity, MvRockModel.StationList.stationArrayList,
                    new String[]{"station_name"},
                    new int[]{R.id.station_name});
            StationListview.setAdapter(stationListAdapter);

            StationListview.setVisibility(View.VISIBLE);
            noStations.setVisibility(View.GONE);
        } else {
            StationListview.setVisibility(View.GONE);
            noStations.setVisibility(View.VISIBLE);
        }
    }

    public void RequestStationByThread() {
        i(TAG, "RequestStationByThread()");
        GetStationThread getStationThread = new GetStationThread(MvRockModel.User.User_Id, "");
        getStationThread.start();
        try {
            getStationThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getStationThread.setResponse();
        MvRockModel.StationList.convertData();
    }

    public void CreateStationByThread(String stationName) {
        i(TAG, "createStationByThread(" + stationName + ")");
        Thread createStationByThread = new Thread(new CreateStationThread(stationName, MvRockModel.User.User_Id));
        createStationByThread.start();
        try {
            createStationByThread.join();
        } catch (InterruptedException e1) {

            e1.printStackTrace();
        }
    }

    public ArrayList<Drawable> RequestStationImageListByThread(List<Map<String, String>> song_info) {
        Log.i(TAG, "RequestStationImageListByThread()");
        GetStationImageListThread getStationImageListThread = new GetStationImageListThread(song_info);
        getStationImageListThread.start();
        try {
            getStationImageListThread.join();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        return getStationImageListThread.getImageView_List();
    }
}
