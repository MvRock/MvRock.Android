package com.mvrock.android.uicomponent;

import android.support.v4.app.ActionBarDrawerToggle;
import android.util.Log;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.uicomponent.drawer.LeftTopDrawer;
import com.mvrock.android.uicomponent.player.MvRockYoutubePlayerFragment;
import com.mvrock.android.uicomponent.player.NextSongButton;
import com.mvrock.android.uicomponent.player.ReportButton;
import com.mvrock.android.uicomponent.player.ShareButton;
import com.mvrock.android.uicomponent.player.ThumbDownButton;
import com.mvrock.android.uicomponent.player.ThumbUpButton;
import com.mvrock.android.uicomponent.playlist.MvRockTabHost;
import com.mvrock.android.uicomponent.playlist.StationPlayListView;
import com.mvrock.android.uicomponent.playlist.YouLikedPlayListView;
import com.mvrock.android.uicomponent.playlist.YouMayLikePlayListView;
import com.mvrock.android.uicomponent.station.StationCancelButton;
import com.mvrock.android.uicomponent.station.StationListView;
import com.mvrock.android.uicomponent.station.StationSearchView;

/**
 * Created by Xuer on 5/8/15.
 */
public class MvRockUiComponent {
    private static final String TAG;
    public static YouMayLikePlayListView YouMayLikePlayListView;
    public static YouLikedPlayListView YouLikedPlayListView;
    public static StationPlayListView StationPlayListView;
    public static MvRockYoutubePlayerFragment MvRockYoutubePlayer;

    public static NextSongButton NextSongButton;
    public static ThumbUpButton ThumbUpButton;
    public static ThumbDownButton ThumbDownButton;
    public static ReportButton ReportButton;
    public static ShareButton ShareButton;

    public static MvRockTabHost MvRockTabHost;

    public static ActionBarDrawerToggle LeftDrawerToggle;
    public static LeftTopDrawer LeftTopDrawer;

    public static StationCancelButton StationCancelButton;
    public static StationListView StationListView;
    public static StationSearchView StationSearchView;

    static{
        TAG="MvRockUiComponent.";

    }

    public static void changeToolBarImage() {
        Log.i(TAG, "changeToolBarImage()");
        Log.i(TAG, "MvRockModel.CurrentSong.isLikedIconPressed = "+Boolean.toString(MvRockModel.CurrentSong.isLikedIconPressed)
                +", MvRockModel.CurrentSong.isDislikedIconPressed= "+Boolean.toString(MvRockModel.CurrentSong.isDislikedIconPressed));
        if (MvRockModel.CurrentSong.isLikedIconPressed) {
            ThumbUpButton.likeSongImage.setImageResource(R.drawable.thumbuporange);
        } else {
            ThumbUpButton.likeSongImage.setImageResource(R.drawable.thumbup);
        }

        if (MvRockModel.CurrentSong.isDislikedIconPressed) {
            ThumbDownButton.dislikeSongImage.setImageResource(R.drawable.thumbdownorange );
        }else {
            ThumbDownButton.dislikeSongImage.setImageResource(R.drawable.thumbdown);
        }
    }
}
