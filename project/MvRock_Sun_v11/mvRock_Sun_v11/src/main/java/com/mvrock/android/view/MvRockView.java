package com.mvrock.android.view;

import android.content.Context;
import android.os.Build;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.view.fragment.MvRockFragment;
import com.mvrock.android.view.fragment.StationPlayListFragment;
import com.mvrock.android.view.fragment.YouLikedPlayListFragment;
import com.mvrock.android.view.fragment.YouMayLikePlayListFragment;

import java.util.ArrayList;

/**
 * Created by Xuer on 5/24/15.
 * Add comment on 5/25/15
 *
 * This class is used to create the view of MvRock and generate the static fragment list.
 */
public class MvRockView {
    public static ArrayList<Fragment> FragmentList;
    public static MvRockFragment MvRockFragment;
    public static YouMayLikePlayListFragment YouMayLikePlayListFragment;
    public static YouLikedPlayListFragment YouLikedPlayListFragment;
    public static StationPlayListFragment StationPlayListFragment;
    public static MainActivity MainActivity;

    public static final int FBLOGIN_FRAG = 0;
    public static final int FB_LOGOUT = -1;
    public static final int MVROCK_FRAG = 1;

    public static final int NOW_SHOWING_POSITION = 0;
    public static final int MY_STATION_POSITION = 1;
    public static final int LOGOUT_POSITION = 2;






    static{
        FragmentList =new ArrayList<Fragment>();
    }
}
