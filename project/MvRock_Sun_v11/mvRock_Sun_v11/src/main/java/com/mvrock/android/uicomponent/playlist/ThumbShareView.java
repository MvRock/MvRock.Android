package com.mvrock.android.uicomponent.playlist;

import android.util.Log;
import android.widget.TextView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;
import com.mvrock.android.uicomponent.player.ShareButton;
import com.mvrock.android.uicomponent.player.ThumbDownButton;
import com.mvrock.android.uicomponent.player.ThumbUpButton;

/**
 * Created by Kenneth on 7/6/2015.
 */
public class ThumbShareView extends MvRockUiComponentObject {

    public TextView thumbUpNumber, thumbDownNumber;
    public ThumbUpButton thumbUpButton;
    public ThumbDownButton thumbDownButton;
    public ShareButton shareButton;

    public ThumbShareView() {
        TAG += "ThumbShareView";

        thumbUpButton = new ThumbUpButton();
        thumbDownButton = new ThumbDownButton();
        shareButton = new ShareButton();
    }

    @Override
    public void Init() {
        Log.i(TAG, "Init()");

        thumbUpButton.Init();
        thumbDownButton.Init();
        shareButton.Init();
    }

    public void update() {
        Log.i(TAG, "update()");

        thumbUpNumber.setText(String.valueOf(MvRockModel.CurrentSong.numLikes));
        thumbDownNumber.setText(String.valueOf(MvRockModel.CurrentSong.numDislikes));

        if (MvRockModel.CurrentSong.isLikedIconPressed) {
            thumbUpButton.likeSongImage.setImageResource(R.drawable.thumbup_new_colored);
        } else {
            thumbUpButton.likeSongImage.setImageResource(R.drawable.thumbup_new);
        }

        if (MvRockModel.CurrentSong.isDislikedIconPressed) {
            thumbDownButton.dislikeSongImage.setImageResource(R.drawable.thumbdown_new_colored);
        } else {
            thumbDownButton.dislikeSongImage.setImageResource(R.drawable.thumbdown_new);
        }

        if (MvRockModel.CurrentSong.isShared) {
            shareButton.shareSongImage.setImageResource(R.drawable.share_new_colored);
        } else {
            shareButton.shareSongImage.setImageResource(R.drawable.share_new);
        }
    }
}
