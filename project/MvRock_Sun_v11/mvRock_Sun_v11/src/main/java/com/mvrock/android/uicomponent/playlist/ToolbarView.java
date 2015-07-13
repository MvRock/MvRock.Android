package com.mvrock.android.uicomponent.playlist;

import android.util.Log;
import android.widget.TextView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.LanguageOption;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;
import com.mvrock.android.uicomponent.player.LanguageButton;
import com.mvrock.android.uicomponent.player.NextSongButton;
import com.mvrock.android.uicomponent.player.ReportButton;
import com.mvrock.android.uicomponent.player.ShareButton;
import com.mvrock.android.uicomponent.player.ThumbDownButton;
import com.mvrock.android.uicomponent.player.ThumbUpButton;

/**
 * Created by Kenneth on 7/6/2015.
 */
public class ToolbarView extends MvRockUiComponentObject {

    public TextView thumbUpNumber, thumbDownNumber;
    public NextSongButton nextSongButton;
    public ThumbUpButton thumbUpButton;
    public ThumbDownButton thumbDownButton;
    public ShareButton shareButton;
    public ReportButton reportButton;
    public LanguageButton languageButton;

    public ToolbarView() {
        TAG += "ToolbarView";

        nextSongButton = new NextSongButton();
        thumbUpButton = new ThumbUpButton();
        thumbDownButton = new ThumbDownButton();
        shareButton = new ShareButton();
        reportButton = new ReportButton();
        languageButton = new LanguageButton();
    }

    @Override
    public void Init() {
        Log.i(TAG, "Init()");

        nextSongButton.Init();
        thumbUpButton.Init();
        thumbDownButton.Init();
        shareButton.Init();
        reportButton.Init();
        languageButton.Init();
    }

    public void update() {
        Log.i(TAG, "update()");

        thumbUpNumber.setText(String.valueOf(MvRockModel.CurrentSong.numLikes));
        thumbDownNumber.setText(String.valueOf(MvRockModel.CurrentSong.numDislikes));

        if (MvRockModel.CurrentSong.isLikedIconPressed) {
            thumbUpButton.likeSongImage.setImageResource(R.drawable.thumbup_red);
        } else {
            thumbUpButton.likeSongImage.setImageResource(R.drawable.thumbup);
        }

        if (MvRockModel.CurrentSong.isDislikedIconPressed) {
            thumbDownButton.dislikeSongImage.setImageResource(R.drawable.thumbdown_red);
        } else {
            thumbDownButton.dislikeSongImage.setImageResource(R.drawable.thumbdown);
        }

        if (MvRockModel.CurrentSong.isShared) {
            shareButton.shareSongImage.setImageResource(R.drawable.share_red);
        } else {
            shareButton.shareSongImage.setImageResource(R.drawable.share);
        }

        if (MvRockModel.CurrentSong.isReported) {
            reportButton.reportSongImage.setImageResource(R.drawable.report_red);
        } else {
            reportButton.reportSongImage.setImageResource(R.drawable.report);
        }

        if (MvRockModel.User.language == LanguageOption.ALL) {
            languageButton.languageImage.setImageResource(R.drawable.lang_all);
        } else if (MvRockModel.User.language == LanguageOption.ENG) {
            languageButton.languageImage.setImageResource(R.drawable.lang_english);
        } else if (MvRockModel.User.language == LanguageOption.CHN) {
            languageButton.languageImage.setImageResource(R.drawable.lang_chinese);
        }
    }
}
