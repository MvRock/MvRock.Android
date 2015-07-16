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
import com.facebook.share.widget.ShareDialog;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.thread.SetShareThread;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;
import com.mvrock.android.view.MvRockView;

/**
 * Created by Xuer on 5/5/15.
 * Add comment on 5/26/15.
 * <p/>
 * This class is used for defining the function of shareSong button
 * <p/>
 * ImageView
 * Init :
 * onclick : call for PostShareByThread()
 * <p/>
 * PostShareByThread:
 * <p/>
 * get the url of the song from YouMayLikeSongList or YouLikeSongList
 * pass it for the Thread
 * Thread Starts
 */
public class ShareButton extends MvRockUiComponentObject {

    public ImageView shareSongImage;

    public ShareButton() {
        TAG += "ShareButton";
    }

    public void Init() {
        Log.i(TAG, "Init()");

        shareSongImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick()");

                // share to facebook
                new AlertDialog.Builder(MvRockView.MainActivity, R.style.AlertDialogTheme)
                        .setTitle("Share to Facebook?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (ShareDialog.canShow(ShareLinkContent.class)) {
                                    ShareLinkContent content = new ShareLinkContent.Builder()
                                            .setContentUrl(Uri.parse(String.format("http://apps.facebook.com/mv_rock/index.php?song_url=%s&sender_uid=%s", MvRockModel.CurrentSong.url, MvRockModel.User.User_Id)))
                                            .setContentTitle(String.format("%s - %s", MvRockModel.CurrentSong.artistName, MvRockModel.CurrentSong.songName))
                                            .setContentDescription(String.format("Listening to %s by %s from the MvRock Android version.", MvRockModel.CurrentSong.songName, MvRockModel.CurrentSong.artistName))
                                            .setImageUrl(Uri.parse(String.format("http://img.youtube.com/vi/%s/0.jpg", MvRockModel.CurrentSong.url)))
                                            .build();

                                    ShareDialog.show(MvRockView.MainActivity, content);
                                } else {
                                    Toast.makeText(MvRockView.MainActivity, "Unable to share to Facebook. Please install the Facebook app.", Toast.LENGTH_SHORT);
                                }
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                // share to MvRock
                shareMvRockByThread();
                MvRockModel.CurrentSong.isShared = true;
                MvRockUiComponent.toolbarView.update();
            }
        });
    }

    public void shareMvRockByThread() {
        Log.i(TAG, "PostShareByThread()");

        String fromChannel = "1"; // TODO: unknown

        new SetShareThread(MvRockModel.User.User_Id, MvRockModel.CurrentSong.url, fromChannel).start();
        Toast.makeText(MvRockView.MainActivity, "The video has been shared on MvRock", Toast.LENGTH_SHORT).show();
    }
}
