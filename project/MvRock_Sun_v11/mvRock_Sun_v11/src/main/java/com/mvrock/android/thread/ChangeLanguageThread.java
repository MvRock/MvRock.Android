package com.mvrock.android.thread;

import org.apache.http.message.BasicNameValuePair;
import android.util.Log;

public class ChangeLanguageThread extends MvRockThreadObject {

	public ChangeLanguageThread(int lang, String User_id) {
        super(User_id,String.valueOf(lang));
        TAG+="ChangeLanguageThread";
        Url="/changelang.php";
	}

    public void setParams() {
        Log.i(TAG, "setParams()");
		params.add(new BasicNameValuePair("myId", this.User_id));
		params.add(new BasicNameValuePair("val", Extra));
	}

    public void setResponse(){}

}
