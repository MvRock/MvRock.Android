package com.mvrock.android.uicomponent;

import android.support.v7.app.ActionBarDrawerToggle;

import com.mvrock.android.uicomponent.drawer.MvRockDrawer;
import com.mvrock.android.uicomponent.player.MvRockYoutubePlayerFragment;
import com.mvrock.android.uicomponent.playlist.ArtistView;
import com.mvrock.android.uicomponent.playlist.CommentView;
import com.mvrock.android.uicomponent.playlist.MvRockTabHost;
import com.mvrock.android.uicomponent.playlist.RightFloatingMenu;
import com.mvrock.android.uicomponent.playlist.SongView;
import com.mvrock.android.uicomponent.playlist.StationPlayListView;
import com.mvrock.android.uicomponent.playlist.ToolbarView;
import com.mvrock.android.uicomponent.playlist.YouLikedPlayListView;
import com.mvrock.android.uicomponent.playlist.YouMayLikePlayListView;
import com.mvrock.android.uicomponent.socialstuff.BuddyFeedListView;
import com.mvrock.android.uicomponent.socialstuff.LeftFloatingMenu;
import com.mvrock.android.uicomponent.station.SearchStationListView;
import com.mvrock.android.uicomponent.station.StationCancelButton;
import com.mvrock.android.uicomponent.station.StationListView;
import com.mvrock.android.uicomponent.station.StationSearchView;

/**
 * Created by Xuer on 5/8/15.
 * Add comment on 5/26/15.
 * <p/>
 * This class is used for define and storing UI component.
 * <p/>
 * <p/>
 * Component :
 * <p/>
 * List:
 * YouMayLikePlayListView
 * YouLikedPlayListView
 * StationPlayListView
 * <p/>
 * Player:
 * MvRockYoutubePlayerFragment
 * <p/>
 * Button:
 * NextSongButton
 * ThumbUpButton
 * ThumbDownButton
 * ReportButton
 * ShareButton
 * <p/>
 * TabHost:
 * MvRockTabHost
 * <p/>
 * Tool:
 * ActionbarDrawerToggle
 * LeftTopDrawer
 * <p/>
 * Station Operation:
 * StationListView
 * StationListView
 * StationSearchView
 * <p/>
 * ChangeToolBarImage :
 * when the thumbup or thumbdown button are pressed, the icon of each should be changed
 * load the icon.
 */
public class MvRockUiComponent {
    public static YouMayLikePlayListView YouMayLikePlayListView;
    public static YouLikedPlayListView YouLikedPlayListView;
    public static StationPlayListView StationPlayListView;

    public static BuddyFeedListView BuddyFeedListView;
    public static MvRockYoutubePlayerFragment MvRockYoutubePlayer;

    public static MvRockTabHost MvRockTabHost;

    public static ActionBarDrawerToggle LeftDrawerToggle;

    public static StationCancelButton StationCancelButton;
    public static StationListView StationListView;

    public static StationSearchView StationSearchView;
    public static SearchStationListView SearchStationListView;

    public static RightFloatingMenu RightFloatingMenu;
    public static LeftFloatingMenu LeftFloatingMenu;

    public static MvRockDrawer MvRockDrawer;

    public static SongView songView;
    public static ArtistView artistView;
    public static ToolbarView toolbarView;
    public static CommentView commentView;
}
