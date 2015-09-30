package com.mvrock.android.thread;

import android.util.Log;

import org.apache.http.message.BasicNameValuePair;

public class ChangeLanguageThread extends MvRockThreadObject {

	public ChangeLanguageThread(String User_id, int lang) {
        super(User_id, String.valueOf(lang));

        TAG += "ChangeLanguageThread";
        Url = "/changelang.php";
	}

    public void setParams() {
        Log.i(TAG, "setParams()");
		params.add(new BasicNameValuePair("myId", User_id));
		params.add(new BasicNameValuePair("val", Extra));
	}

    public void setResponse() {}
}
