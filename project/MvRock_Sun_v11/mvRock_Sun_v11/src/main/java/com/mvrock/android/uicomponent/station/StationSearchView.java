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
 * Add comments on 5/26/15.
 *
 * This class is used for define station searching component
 *
 * Add topSearchView object
 * Init:
 *      Default String : Search Stations.
 *      onQueryTextChange : not change with the text typed in (false)
 *      onQueryTextSubmit : call for RequestSearchStationResultByThread() function
 *
 * RequestSearchStationResultByThread()
 *      Call for GetSearchStationThread
 *      Processing
 *      Set the StationViewList
 *
 */
public class StationSearchView extends MvRockUiComponentObject {
    public SearchView topSearchView;
    public int[] subscribelist;
    public StationSearchView(){
        TAG+="SearchView";
    }
    public void Init(){
        Log.i(TAG, "Init()");

        topSearchView.setQueryHint("Search Stations");
        topSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
            public boolean onQueryTextSubmit(String s) {
                i(TAG, "onQueryTextSubmit()");
                RequestSearchStationResultByThread();
                MvRockModel.SearchStationList.convertData();
                MvRockUiComponent.SearchStationListView.RefreshListView();
                return true;
            }
        });
    }

    public void RequestSearchStationResultByThread() {
        i(TAG, "RequestSearchStationResultByThread()");
        GetSearchStationThread getRecStationThread =
                new GetSearchStationThread(MvRockModel.User.User_Id,
                        String.valueOf(MvRockUiComponent.StationSearchView.topSearchView.getQuery()));
        getRecStationThread.start();
        try {
            getRecStationThread.join();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        getRecStationThread.setResponse();
        MvRockModel.SearchStationList.convertData();
    }
}
