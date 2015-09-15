package com.mvrock.android.thread;

import android.util.Log;

import com.mvrock.android.model.MvRockModel;

import org.apache.http.message.BasicNameValuePair;

/**
 * Created by Tianhao on 15/8/11.
 */
public class GetBuddyFeedThread extends MvRockThreadObject{

    public GetBuddyFeedThread(String User_id, String Extra){
        super(User_id, Extra);
        this.TAG = "GetBuddyFeedThread";
        this.Url = "/getMusicBuddy.php";
    }
    @Override
    protected void setParams() {
        Log.i(TAG, "setParams()");
        params.add(new BasicNameValuePair("userid", User_id));
    }

    @Override
    public void setResponse() {
        MvRockModel.BuddyFeed.setHttpResponse(this.strResponse);
    }
}
