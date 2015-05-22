package com.mvrock.android.uicomponent;

import android.util.Log;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.uicomponent.player.MvRockYoutubePlayerFragment;
import com.mvrock.android.uicomponent.player.NextSongButton;
import com.mvrock.android.uicomponent.player.ReportButton;
import com.mvrock.android.uicomponent.player.ShareButton;
import com.mvrock.android.uicomponent.player.ThumbDownButton;
import com.mvrock.android.uicomponent.player.ThumbUpButton;
import com.mvrock.android.uicomponent.playlist.StationPlayListView;
import com.mvrock.android.uicomponent.playlist.YouLikedPlayListView;
import com.mvrock.android.uicomponent.playlist.YouMayLikePlayListView;

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

    static{
        TAG="MvRockUiComponent";

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
