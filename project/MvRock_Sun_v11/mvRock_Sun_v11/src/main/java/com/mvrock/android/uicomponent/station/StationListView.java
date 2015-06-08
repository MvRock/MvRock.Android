package com.mvrock.android.uicomponent.station;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.thread.CreateStationThread;
import com.mvrock.android.thread.GetStationThread;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;
import com.mvrock.android.uicomponent.playlist.StationPlayListView;

import static android.util.Log.i;

/**
 * Created by Xuer on 5/24/15.
 *
 */
public class StationListView extends MvRockUiComponentObject {
    public ListView StationListview;
    public StationListView(){
        TAG+="StationListView";
        RequestStationByThread();
    }
    public void Init(){
        i(TAG,"Init()");
        StationListview.setVisibility(View.INVISIBLE);
        StationListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,int i, long l) {
                i(TAG, "onItemClick()");
                TextView tab_tv = (TextView) MvRockUiComponent.MvRockTabHost.TabHost.getTabWidget().getChildAt(0)
                        .findViewById(android.R.id.title);

                tab_tv.setText(MvRockModel.SearchStationResultList[i]);
                CreateStationByThread(MvRockModel.SearchStationResultList[i]);
                MvRockUiComponent.YouMayLikePlayListView.playListview=null;

                MvRockUiComponent.StationPlayListView=new StationPlayListView();
//                MvRockUiComponent.StationPlayListView.playListview = (ListView) view.findViewById(R.id.youmaylike);
                MvRockUiComponent.StationPlayListView.Init();

                MvRockUiComponent.StationSearchView.topSearchView.onActionViewCollapsed();
                StationListview.setVisibility(View.INVISIBLE);
                MvRockUiComponent.StationCancelButton.stationCancelImage.setVisibility(View.VISIBLE);
//                MvRockUiComponent.LeftTopDrawer.AddStationList();
            }
        });
    }
    public void RequestStationByThread(){
        i(TAG, "RequestStationByThread()");
        GetStationThread getStationThread = new GetStationThread(MvRockModel.User.User_Id,"");
        getStationThread.start();
        try {
            getStationThread.join();
        } catch (InterruptedException e1) {

            e1.printStackTrace();
        }
        getStationThread.setResponse();
    }

    public void CreateStationByThread(String stationName){
        i(TAG, "CreateStationByThread(" + stationName + ")");
        Thread createStationByThread = new Thread(new CreateStationThread(stationName, MvRockModel.User.User_Id));
        createStationByThread.start();
        try {
            createStationByThread.join();
        } catch (InterruptedException e1) {

            e1.printStackTrace();
        }
    }
}
