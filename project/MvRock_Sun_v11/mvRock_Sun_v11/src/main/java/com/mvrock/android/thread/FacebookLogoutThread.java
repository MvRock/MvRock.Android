package com.mvrock.android.thread;

import android.util.Log;

public class FacebookLogoutThread extends Thread {

	private static final String TAG = "FacebookLogoutThread";

    /* I AM NOT SURE IF WE NEED THIS THREAD ANYMORE
        The code below should log the user out

                LoginManager loginManager = LoginManager.getInstance();
                loginManager.logOut();
     */
	@Override
	public void run() {
		Log.i(TAG, "run()");
//		Facebook mFb=new Facebook("189448637812265");
//        try {
//			mFb.logout(MvRockView.MainActivity);
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
}
