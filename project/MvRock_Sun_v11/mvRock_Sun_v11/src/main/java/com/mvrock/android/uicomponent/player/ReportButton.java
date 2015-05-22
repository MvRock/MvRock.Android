package com.mvrock.android.uicomponent.player;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Xuer on 5/5/15.
 */
public class ReportButton extends PlayerControlButton{
    public ImageView reportSongImage;
    public ReportButton(){TAG="ReportButton";};
    public void Init(){
        Log.i(TAG,"Init()");
        reportSongImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("reportSongImage", "onClick()");
                //TODO
            }
        });

    }
}
