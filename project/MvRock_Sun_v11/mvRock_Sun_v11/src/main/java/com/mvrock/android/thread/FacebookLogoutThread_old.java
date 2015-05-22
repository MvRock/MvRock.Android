package com.mvrock.android.thread;

import java.io.IOException;
import java.net.MalformedURLException;

import android.content.Context;
import android.util.Log;

import com.facebook.android.Facebook;

public class FacebookLogoutThread_old implements Runnable {
	private Context m_context;
	private static final String TAG = "FacebookLogoutThread";
	public FacebookLogoutThread_old(Context context){this.m_context=context;}
	@SuppressWarnings("deprecation")
	@Override
	public void run() {	
		Log.i(TAG, "run()");
		Facebook mFb=new Facebook("189448637812265");
        try {
			mFb.logout(m_context);
			//LoginManager.getInstance().logOut();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
}
