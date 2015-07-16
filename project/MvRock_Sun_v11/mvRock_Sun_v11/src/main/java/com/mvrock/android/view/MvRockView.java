package com.mvrock.android.view;

import com.mvrock.android.view.fragment.SearchStationFragment;
import com.mvrock.android.view.fragment.StationListFragment;
import com.mvrock.android.view.fragment.StationPlayListFragment;
import com.mvrock.android.view.fragment.YouLikedPlayListFragment;
import com.mvrock.android.view.fragment.YouMayLikePlayListFragment;

/**
 * Created by Xuer on 5/24/15.
 * Add comment on 5/25/15
 *
 * This class is used to create the view of MvRock and generate the static fragment list.
 */
public class MvRockView {
    public static YouMayLikePlayListFragment YouMayLikePlayListFragment;
    public static YouLikedPlayListFragment YouLikedPlayListFragment;
    public static StationPlayListFragment StationPlayListFragment;
    public static StationListFragment StationListFragment;
    public static SearchStationFragment SearchStationFragment;
    public static MainActivity MainActivity;

    public static final int FB_LOGIN_FRAG = 0;
    public static final int MVROCK_FRAG = 1;
    public static final int FB_LOGOUT = -1;

    public static final int NOW_SHOWING_POSITION = 0;
    public static final int MY_STATION_POSITION = 1;
    public static final int LOGOUT_POSITION = 2;
}
