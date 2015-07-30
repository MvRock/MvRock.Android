package com.mvrock.android.uicomponent.station;

import android.util.Log;
import android.view.View;
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

    public StationSearchView(){
        TAG+="SearchView";
    }
    public void Init(){
        Log.i(TAG, "Init()");

        topSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                MvRockView.StationListFragment.title.setText("Stations");

                MvRockUiComponent.SearchStationListView.SearchStationListView.setVisibility(View.GONE);
                MvRockUiComponent.SearchStationListView.noSearchResults.setVisibility(View.GONE);

                MvRockView.StationListFragment.refreshButton.setVisibility(View.VISIBLE);
                MvRockUiComponent.StationListView.RefreshListView();
                return false;
            }
        });

        topSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String s) {
                i(TAG, "onQueryTextSubmit()");
                MvRockView.StationListFragment.title.setText("Search Stations");

                MvRockUiComponent.StationListView.StationListview.setVisibility(View.GONE);
                MvRockUiComponent.StationListView.noStations.setVisibility(View.GONE);
                MvRockView.StationListFragment.refreshButton.setVisibility(View.GONE);

                RequestSearchStationResultByThread();
                MvRockUiComponent.SearchStationListView.RefreshListView();
                return true;
            }
        });
    }

    public void RequestSearchStationResultByThread() {
        i(TAG, "RequestSearchStationResultByThread()");
        GetSearchStationThread getRecStationThread = new GetSearchStationThread(MvRockModel.User.User_Id,
                MvRockUiComponent.StationSearchView.topSearchView.getQuery().toString());
        getRecStationThread.start();
        try {
            getRecStationThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getRecStationThread.setResponse();
        MvRockModel.SearchStationList.convertData();
    }
}
