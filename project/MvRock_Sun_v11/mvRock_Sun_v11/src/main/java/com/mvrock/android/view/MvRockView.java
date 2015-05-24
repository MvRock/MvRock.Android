package com.mvrock.android.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

/**
 * Created by Xuer on 5/24/15.
 */
public class MvRockView {
    public static ArrayList<Fragment> FragmentList;
    public static FragmentTransaction Transaction;
    public static FragmentManager FragmentManager;
    public static MainActivity MainActivity;
    public static Context Context;

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
