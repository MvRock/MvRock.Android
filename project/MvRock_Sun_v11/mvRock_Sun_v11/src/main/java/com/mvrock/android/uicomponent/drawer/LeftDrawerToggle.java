package com.mvrock.android.uicomponent.drawer;

import android.app.Activity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.mvrock.android.uicomponent.MvRockUiComponent;
import static android.util.Log.i;

/**
 * Created by Xuer on 5/23/15.
 */
public  class LeftDrawerToggle extends
        ActionBarDrawerToggle {
    private static final String TAG = "LeftDrawerToggle";
    private Activity activity;
    public LeftDrawerToggle(Activity activity,
                             DrawerLayout drawerLayout, int drawerImageRes,
                             int openDrawerContentDescRes, int closeDrawerContentDescRes) {
        super(activity, drawerLayout, drawerImageRes,
                openDrawerContentDescRes, closeDrawerContentDescRes);
        this.activity = activity;
    }

    public void onDrawerClosed(View view) {
        i(TAG, "onDrawerClosed()");
        MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.play();
        this.activity.invalidateOptionsMenu();

    }

    public void onDrawerOpened(View drawerView) {
        i(TAG, "onDrawerOpened()");
        this.activity.getActionBar().setTitle("MvRock");
        this.activity.invalidateOptionsMenu();
    }
}
