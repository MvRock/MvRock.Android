package com.mvrock.android.uicomponent.playlist;

import android.util.Log;
import android.widget.TabHost;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;
import com.mvrock.android.view.MvRockView;

/**
 * Created by Xuer on 5/24/15.
 */
public class MvRockTabHost extends MvRockUiComponentObject{
    public TabHost TabHost;

    public MvRockTabHost(){
        TAG+="MvRockTabHost";
        TabHost =new TabHost(MvRockView.Context);
    }

    public void Init(){
       Log.i(TAG, "Init()");
       TabHost.setup();
       android.widget.TabHost.TabSpec TabSpec;
       TabSpec= TabHost.newTabSpec("You May Like");
       TabSpec.setContent(R.id.tab1);
       TabSpec.setIndicator("You May Like");
       TabHost.addTab(TabSpec);

       TabSpec =TabHost.newTabSpec("You Liked");
       TabSpec.setContent(R.id.tab2);
       TabSpec.setIndicator("You Liked");
       TabHost.addTab(TabSpec);
    }

}
