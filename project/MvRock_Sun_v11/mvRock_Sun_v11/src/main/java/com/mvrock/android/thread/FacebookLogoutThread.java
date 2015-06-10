package com.mvrock.android.thread;

import java.io.IOException;
import java.net.MalformedURLException;
import android.util.Log;

import com.facebook.android.Facebook;
import com.mvrock.android.view.MvRockView;

public class FacebookLogoutThread implements Runnable {
	private static final String TAG = "FacebookLogoutThread";
	public FacebookLogoutThread(){}
	@SuppressWarnings("deprecation")
	@Override
	public void run() {	
		Log.i(TAG, "run()");
		Facebook mFb=new Facebook("189448637812265");
        try {
			mFb.logout(MvRockView.MainActivity);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
}
