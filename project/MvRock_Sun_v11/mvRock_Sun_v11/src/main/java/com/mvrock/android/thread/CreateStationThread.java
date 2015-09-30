package com.mvrock.android.thread;

import android.util.Log;

import org.apache.http.message.BasicNameValuePair;

public class CreateStationThread extends MvRockThreadObject {
    
    public CreateStationThread(String stationName, String User_id) {
        super(User_id, stationName);
        Url = "/createStation.php";
        TAG += "CreateStationThread";
    }

    public void setParams() {
        Log.i(TAG, "setParams()");
        params.add(new BasicNameValuePair("myId", User_id));
        params.add(new BasicNameValuePair("stationname", Extra));
    }

    public void setResponse() {
    }
}
