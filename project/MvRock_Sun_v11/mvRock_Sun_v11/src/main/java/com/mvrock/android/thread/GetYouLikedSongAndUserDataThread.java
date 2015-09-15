package com.mvrock.android.thread;

import android.util.Log;

import com.mvrock.android.model.MvRockModel;

import org.apache.http.message.BasicNameValuePair;

public class GetYouLikedSongAndUserDataThread extends MvRockThreadObject {

    public GetYouLikedSongAndUserDataThread(String User_id, String Extra) {
        super(User_id, Extra);
        this.TAG += "GetYouLikedSongData";
        this.Url = "/getuserdata.php";
    }

    public void setParams() {
        Log.i(TAG, "setParams()");
        params.add(new BasicNameValuePair("uid", User_id));
    }

    public void setResponse() {
        MvRockModel.YouLikedSongList.setHttpResponse(strResponse);
        MvRockModel.BuddyFeed.setHttpResponse(strResponse);
        MvRockModel.User.setHttpResponse(strResponse);
    }
}
