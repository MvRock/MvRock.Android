package com.mvrock.android.uicomponent.station;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.thread.GetSearchStationThread;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;
import com.mvrock.android.view.MvRockView;

import static android.util.Log.i;

/**
 * Created by Xuer on 5/5/15.
 */
public class StationSearchView extends MvRockUiComponentObject {
    public SearchView topSearchView;
    public StationSearchView(){
        TAG+="SearchView";
    }
    public void Init(){
        Log.i(TAG, "Init()");
        topSearchView.setQueryHint("Search Stations");
        topSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String s) {
                return false;
            }
            public boolean onQueryTextSubmit(String s) {
                i(TAG, "onQueryTextSubmit()");
                RequestSearchStationResultByThread();
                return true;
            }
        });
    }

    public void RequestSearchStationResultByThread() {
        i(TAG, "RequestSearchStationResultByThread()");
        Thread getRecStationThread = new Thread(
                new GetSearchStationThread(MvRockModel.User.User_Id, String.valueOf(MvRockUiComponent.StationSearchView.topSearchView.getQuery())));
        getRecStationThread.start();
        try {
            getRecStationThread.join();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        MvRockModel.SearchStationResultList = GetSearchStationThread.getArrayRecStations();
        MvRockUiComponent.StationListView.StationListview.setAdapter(new ArrayAdapter<String>(MvRockView.MainActivity, android.R.layout.simple_list_item_1, MvRockModel.SearchStationResultList));
        MvRockUiComponent.StationListView.StationListview.setVisibility(View.VISIBLE);
    }
}
