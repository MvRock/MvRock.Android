package com.mvrock.android.uicomponent.station;

import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;
import com.mvrock.android.view.MvRockView;


/**
 * Created by Xuer on 6/11/15.
 */
public class fadeStationSearchview extends MvRockUiComponentObject {
    public SearchView topSearchView;
    public fadeStationSearchview(){
        TAG+="fadeSearchView";
    }
    public void Init(){
        Log.i(TAG, "Init()");
        topSearchView.setQueryHint("Search Stations");
        topSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MvRockView.MainActivity.getSupportFragmentManager().beginTransaction().
                        replace(R.id.right_drawer, MvRockView.SearchStationFragment).commit();
            }
        });
        topSearchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                    MvRockView.MainActivity.getSupportFragmentManager().beginTransaction().
                            replace(R.id.right_drawer, MvRockView.SearchStationFragment).commit();
                }

        });

    }


}

