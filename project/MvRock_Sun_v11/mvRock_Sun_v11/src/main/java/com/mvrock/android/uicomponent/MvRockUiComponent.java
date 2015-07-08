package com.mvrock.android.uicomponent;

import android.support.v4.app.ActionBarDrawerToggle;
import android.widget.TextView;

import com.mvrock.android.uicomponent.drawer.MvRockDrawer;
import com.mvrock.android.uicomponent.player.MvRockYoutubePlayerFragment;
import com.mvrock.android.uicomponent.player.NextSongButton;
import com.mvrock.android.uicomponent.player.ReportButton;
import com.mvrock.android.uicomponent.playlist.ArtistView;
import com.mvrock.android.uicomponent.playlist.CommentView;
import com.mvrock.android.uicomponent.playlist.MvRockTabHost;
import com.mvrock.android.uicomponent.playlist.RightFloatingMenu;
import com.mvrock.android.uicomponent.playlist.SongView;
import com.mvrock.android.uicomponent.playlist.StationPlayListView;
import com.mvrock.android.uicomponent.playlist.ThumbShareView;
import com.mvrock.android.uicomponent.playlist.YouLikedPlayListView;
import com.mvrock.android.uicomponent.playlist.YouMayLikePlayListView;
import com.mvrock.android.uicomponent.station.SearchStationListView;
import com.mvrock.android.uicomponent.station.StationCancelButton;
import com.mvrock.android.uicomponent.station.StationListView;
import com.mvrock.android.uicomponent.station.StationSearchView;
import com.mvrock.android.uicomponent.station.fadeStationSearchview;

/**
 * Created by Xuer on 5/8/15.
 * Add comment on 5/26/15.
 *
 * This class is used for define and storing UI component.
 *
 *
 * Component :
 *
 * List:
 * YouMayLikePlayListView
 * YouLikedPlayListView
 * StationPlayListView
 *
 * Player:
 * MvRockYoutubePlayerFragment
 *
 * Button:
 * NextSongButton
 * ThumbUpButton
 * ThumbDownButton
 * ReportButton
 * ShareButton
 *
 * TabHost:
 * MvRockTabHost
 *
 * Tool:
 * ActionbarDrawerToggle
 * LeftTopDrawer
 *
 * Station Operation:
 * StationListView
 * StationListView
 * StationSearchView
 *
 * ChangeToolBarImage :
 * when the thumbup or thumbdown button are pressed, the icon of each should be changed
 * load the icon.
 */
public class MvRockUiComponent {
    private static final String TAG;
    public static YouMayLikePlayListView YouMayLikePlayListView;
    public static YouLikedPlayListView YouLikedPlayListView;
    public static StationPlayListView StationPlayListView;
    public static MvRockYoutubePlayerFragment MvRockYoutubePlayer;

    public static NextSongButton NextSongButton;
    public static ReportButton ReportButton;

    public static MvRockTabHost MvRockTabHost;

    public static ActionBarDrawerToggle LeftDrawerToggle;

    public static StationCancelButton StationCancelButton;
    public static StationListView StationListView;
    public static fadeStationSearchview fadeStationSearchview;
    public static TextView noResultTextViewOnStationFrag;

    public static StationSearchView StationSearchView;
    public static SearchStationListView SearchStationListView;
    public static TextView noResultTextViewOnSearchTextView;

    public static RightFloatingMenu RightFloatingMenu;

    public static MvRockDrawer MvRockDrawer;

    public static SongView songView;
    public static ArtistView artistView;
    public static ThumbShareView thumbShareView;
    public static CommentView commentView;

    static{
        TAG="MvRockUiComponent.";
    }
}
