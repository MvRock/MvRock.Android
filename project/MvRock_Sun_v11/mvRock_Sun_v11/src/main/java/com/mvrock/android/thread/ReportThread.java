package com.mvrock.android.thread;

import android.util.Log;

import org.apache.http.message.BasicNameValuePair;

/**
 * Created by Kenneth on 6/24/2015.
 */
public class ReportThread extends MvRockThreadObject {

    private String comment;

    public ReportThread(String userId, String songUrl, String comment) {
        super(userId, songUrl);
        this.comment = comment;

        TAG += "ReportThread";
        Url = "/reportWrongMV.php";
    }

    @Override
    protected void setParams() {
        Log.i(TAG, "setParams()");

        params.add(new BasicNameValuePair("url", Extra));
        params.add(new BasicNameValuePair("userid", User_id));
        params.add(new BasicNameValuePair("comments", comment));
        // if the flagValue is 1, the report is stored, otherwise the report is deleted
        params.add(new BasicNameValuePair("flagValue", "1"));
    }

    @Override
    public void setResponse() {}
}
