package com.mvrock.android.thread;

import org.apache.http.message.BasicNameValuePair;
import android.util.Log;

public class SetShareThread extends MvRockThreadObject {
	private String Song_Url;
	
	public SetShareThread(String User_id, String Song_Url) {
		super(User_id,"");
        this.TAG+="SetRatingThread";
        this.Song_Url = Song_Url;
        this.Url="/setShare.php";
	}

    public void setParams() {
        Log.i(TAG, "setParams()");
		params.add(new BasicNameValuePair("userid", this.User_id));
		params.add(new BasicNameValuePair("url", Song_Url));
		params.add(new BasicNameValuePair("fromChannel", "1"));
		params.add(new BasicNameValuePair("songfromid", "0"));
	}
    public void setResponse(){}
}