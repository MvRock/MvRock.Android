package com.mvrock.android.uicomponent.player;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.examples.youtubeapidemo.R;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.MessageDialog;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;
import com.mvrock.android.view.MvRockView;

/**
 * Created by Xuer on 5/5/15.
 */
public class SendSongButton extends MvRockUiComponentObject {

    public ImageView sendSongImage;

    public SendSongButton() {
        TAG += "SendSongButton";
    }

    @Override
    public void Init() {
        Log.i(TAG, "Init()");

        sendSongImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MvRockView.MainActivity, R.style.AlertDialogTheme)
                        .setTitle("Send video to friends?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (MessageDialog.canShow(ShareLinkContent.class)) {
                                    ShareLinkContent content = new ShareLinkContent.Builder()
                                            .setContentUrl(Uri.parse(String.format("http://apps.facebook.com/mv_rock/index.php?song_url=%s&sender_uid=%s", MvRockModel.CurrentSong.url, MvRockModel.User.User_Id)))
                                            .setContentTitle(String.format("%s - %s", MvRockModel.CurrentSong.artistName, MvRockModel.CurrentSong.songName))
                                            .setContentDescription(String.format("Listening to %s by %s from the MvRock Android version.", MvRockModel.CurrentSong.songName, MvRockModel.CurrentSong.artistName))
                                            .setImageUrl(Uri.parse(String.format("http://img.youtube.com/vi/%s/0.jpg", MvRockModel.CurrentSong.url)))
                                            .build();

                                    MessageDialog.show(MvRockView.MainActivity, content);

                                    MvRockModel.CurrentSong.hasSentSong = true;
                                    MvRockUiComponent.toolbarView.update();
                                } else {
                                    Toast.makeText(MvRockView.MainActivity, "Unable to share. Please install the Facebook Messenger app.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }
}