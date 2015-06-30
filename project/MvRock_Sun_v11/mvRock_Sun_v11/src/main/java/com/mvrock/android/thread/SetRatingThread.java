package com.mvrock.android.thread;

import android.util.Log;

import com.mvrock.android.model.MvRockModel;

import org.apache.http.message.BasicNameValuePair;

public class SetRatingThread extends MvRockThreadObject {

    private int rating;

    public SetRatingThread(String User_id, String Song_Url, int rating) {
        super(User_id, Song_Url);

        TAG += "SetRatingThread";
        Url = "/setrating.php";

        this.rating = rating;
    }

    public void setParams() {
        Log.i(TAG, "setParams()");
        params.add(new BasicNameValuePair("userid", User_id));
        params.add(new BasicNameValuePair("url", Extra));
        params.add(new BasicNameValuePair("rating", String.valueOf(rating)));
        params.add(new BasicNameValuePair("songsource", "1")); // TODO: unknown
        params.add(new BasicNameValuePair("songfromid", MvRockModel.CurrentSong.rootShareUserId)); // original user id
    }

    public void setResponse() {}
}
