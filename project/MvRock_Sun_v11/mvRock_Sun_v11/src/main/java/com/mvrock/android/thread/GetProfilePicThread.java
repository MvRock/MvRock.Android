package com.mvrock.android.thread;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;

import com.mvrock.android.model.MvRockModel;

import java.io.InputStream;
import java.net.URL;

public class GetProfilePicThread extends Thread {

    private static final String TAG = "Thread.GetUserInfo";

    @Override
    public void run() {
        Log.i(TAG, "run()");
        try {
            Uri uri = MvRockModel.User.profile.getProfilePictureUri(200, 200);

            URL imgUrl = new URL(uri.toString());

            InputStream is = (InputStream) imgUrl.getContent();
            MvRockModel.User.User_Profile_pic = Drawable.createFromStream(is, "src");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}