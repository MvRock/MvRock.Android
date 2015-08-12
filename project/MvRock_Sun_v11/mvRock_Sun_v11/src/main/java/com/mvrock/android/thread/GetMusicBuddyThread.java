package com.mvrock.android.thread;

import android.util.Log;

import com.mvrock.android.model.MvRockModel;

import org.apache.http.message.BasicNameValuePair;

/**
 * Created by Tianhao on 15/8/12.
 */
public class GetMusicBuddyThread extends MvRockThreadObject{
    public GetMusicBuddyThread(String User_id, String Extra){
        super(User_id, Extra);
        this.TAG = "GetMusicBuddyThread";
        this.Url = "/musicbuddylist.php";
    }
    @Override
    protected void setParams() {
        Log.i(TAG, "setParams()");
        params.add(new BasicNameValuePair("userid", User_id));
    }

    @Override
    public void setResponse() {
        MvRockModel.MusicBuddy.setHttpResponse(this.strResponse);
    }
}
