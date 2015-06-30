package com.mvrock.android.uicomponent.player;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.WebDialog;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.thread.SetShareThread;
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
                if (FacebookDialog.canPresentShareDialog(MvRockView.MainActivity, FacebookDialog.ShareDialogFeature.SHARE_DIALOG)) {
                    new FacebookDialog.ShareDialogBuilder(MvRockView.MainActivity)
                            .setLink(String.format("http://apps.facebook.com/mv_rock/index.php?song_url=%s&sender_uid=%s", MvRockModel.CurrentSong.url, MvRockModel.User.User_Id))
                            .setName(String.format("%s - %s", MvRockModel.CurrentSong.artistName, MvRockModel.CurrentSong.songName))
                            .setDescription(String.format("Listening to %s by %s from the MvRock Android version.", MvRockModel.CurrentSong.songName, MvRockModel.CurrentSong.artistName))
                            //.setCaption("Caption")
                            .setPicture(String.format("http://img.youtube.com/vi/%s/0.jpg", MvRockModel.CurrentSong.url))
                            .build()
                            .present();
                } else {
                    Bundle params = new Bundle();
                    params.putString("link", String.format("http://apps.facebook.com/mv_rock/index.php?song_url=%s&sender_uid=%s", MvRockModel.CurrentSong.url, MvRockModel.User.User_Id));
                    params.putString("name", String.format("%s - %s", MvRockModel.CurrentSong.artistName, MvRockModel.CurrentSong.songName));
                    //params.putString("caption", "Caption");
                    params.putString("description", String.format("Listening to %s by %s from the MvRock Android version.", MvRockModel.CurrentSong.songName, MvRockModel.CurrentSong.artistName));
                    params.putString("picture", String.format("http://img.youtube.com/vi/%s/0.jpg", MvRockModel.CurrentSong.url));

                    new WebDialog.FeedDialogBuilder(MvRockView.MainActivity, Session.getActiveSession(), params)
                            .setOnCompleteListener(new WebDialog.OnCompleteListener() {
                                @Override
                                public void onComplete(Bundle values, FacebookException error) {
                                    if (error == null) {
                                        // When the story is posted, echo the success
                                        // and the post Id.
                                        final String postId = values.getString("post_id");
                                        if (postId != null) {
                                            Toast.makeText(MvRockView.MainActivity, "Shared on Facebook", Toast.LENGTH_SHORT).show();
                                        } else {
                                            // User clicked the Cancel button
                                            Toast.makeText(MvRockView.MainActivity, "Cancelled", Toast.LENGTH_SHORT).show();
                                        }
                                    } else if (error instanceof FacebookOperationCanceledException) {
                                        // User clicked the "x" button
                                        Toast.makeText(MvRockView.MainActivity, "Cancelled", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(MvRockView.MainActivity, "Error sharing to Facebook", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .build()
                            .show();
                }

                // share to MvRock
                shareMvRockByThread();
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
