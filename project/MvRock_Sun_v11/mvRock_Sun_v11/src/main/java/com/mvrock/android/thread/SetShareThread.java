package com.mvrock.android.thread;

import android.util.Log;

import com.mvrock.android.model.MvRockModel;

import org.apache.http.message.BasicNameValuePair;

public class SetShareThread extends MvRockThreadObject {

    private String fromChannel;

    public SetShareThread(String User_id, String Song_Url, String fromChannel) {
        super(User_id, Song_Url);
        TAG += "SetRatingThread";
        Url = "/setShare.php";

        this.fromChannel = fromChannel;
    }

    public void setParams() {
        Log.i(TAG, "setParams()");
        params.add(new BasicNameValuePair("userid", User_id));
        params.add(new BasicNameValuePair("url", Extra));
        params.add(new BasicNameValuePair("fromChannel", fromChannel)); // currently unknown
        params.add(new BasicNameValuePair("songfromid", MvRockModel.CurrentSong.rootShareUserId)); // the original user id that shared this song
    }

    public void setResponse() {}
}