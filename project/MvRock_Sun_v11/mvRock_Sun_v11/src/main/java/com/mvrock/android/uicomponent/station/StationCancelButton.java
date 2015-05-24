package com.mvrock.android.uicomponent.station;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;

import static android.util.Log.i;

/**
 * Created by Xuer on 5/24/15.
 */
public class StationCancelButton extends MvRockUiComponentObject {
    public ImageView stationCancelImage;
    public StationCancelButton(){
        TAG+="StationCancelButton";
    }
    public void Init(){
        Log.i(TAG, "Init()");
        stationCancelImage.setVisibility(View.INVISIBLE);
        stationCancelImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i("stationCancelImage", "onClick()");
                TextView tab_tv = (TextView) MvRockUiComponent.MvRockTabHost.TabHost.getTabWidget().getChildAt(0)
                        .findViewById(android.R.id.title);
                tab_tv.setText("You May Like");
                stationCancelImage.setVisibility(View.INVISIBLE);
                MvRockUiComponent.YouMayLikePlayListView.Init();
            }
        });

    }
}
