package com.mvrock.android.thread;

import android.util.Log;

import com.mvrock.android.model.MvRockModel;

import org.apache.http.message.BasicNameValuePair;

/**
 * Created by Tianhao on 15/8/11.
 */
public class GetRecBuddyThread extends MvRockThreadObject{

    public GetRecBuddyThread(String User_id, String Extra){
        super(User_id,Extra);
        this.TAG = "GetRecBuddyThread";
        this.Url = "/getRecBuddy.php";
    }
    @Override
    protected void setParams() {
        Log.i(TAG, "setParams()");
        params.add(new BasicNameValuePair("uid", User_id));
    }

    @Override
    public void setResponse() {
        MvRockModel.RecBuddy.setHttpResponse(this.strResponse);
    }
}
