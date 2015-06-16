package com.mvrock.android.uicomponent.station;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;
import com.mvrock.android.view.MvRockView;

import static android.util.Log.i;

/**
 * Created by Xuer on 6/11/15.
 */
public class SearchStationListView extends MvRockUiComponentObject {
    public ListView SearchStationListview;

    public SearchStationListView(){
        TAG+="SearchStationListView";
    }
    public void Init(){
        i(TAG,"Init()");
    }

    public void RefreshListView(){
        Log.i(TAG, "RefreshListView()");
        if(MvRockModel.SearchStationList.searchStationArrayList!=null) {
            SearchStationListAdapter SearchStationListAdapter= new SearchStationListAdapter(MvRockView.MainActivity,MvRockModel.SearchStationList.searchStationArrayList,
                    new String[] {"station_name" },
                    new int[] {  R.id.station_name });
            this.SearchStationListview.setAdapter(SearchStationListAdapter);
        }else{
            MvRockUiComponent.noResultTextViewOnSearchTextView.setVisibility(View.VISIBLE);
        }

    }

}
