package com.mvrock.android.thread;

import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

public class SetRatingThread extends MvRockThreadObject {
    private String Song_Url;
    private int rating;

    public SetRatingThread(String User_id, String Song_Url, int rating) {
        super(User_id, "");
        this.TAG += "SetRatingThread";
        this.Song_Url = Song_Url;
        this.rating = rating;
        this.Url = "/setrating.php";
    }

    public void setParams() {
        Log.i(TAG, "setParams()");
        params.add(new BasicNameValuePair("userid", this.User_id));
        params.add(new BasicNameValuePair("url", Song_Url));
        params.add(new BasicNameValuePair("rating", String.valueOf(rating)));
        params.add(new BasicNameValuePair("songsource", "1"));
        params.add(new BasicNameValuePair("songfromid", "0"));
    }

    public void setResponse() {
    }
}
