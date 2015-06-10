package com.mvrock.android.view.fragment;
/**
 *This is a view used for FB login.
 *Based on Fragment.
 *Using FB API
 */
import java.util.Arrays;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.LoginButton;
import com.examples.youtubeapidemo.R;
public class FbLoginFragment extends Fragment{
	
	public LoginButton login_button;
	private static final String TAG = "View.FbLoginFragment";
    
	public View view;
	private UiLifecycleHelper uiHelper;



	public interface SkipLoginCallback {

        void onSkipLoginPressed();
    }
    public void setSkipLoginCallback(SkipLoginCallback callback) {
    	Log.i(TAG, "setSkipLoginCallback()");
	}



	@Override
	/*
	 * onCreate(Bundle) is where you initialize your activity. 
	 * Most importantly, here you will usually call setContentView(int) with a layout resource defining your UI, 
	 * and using findViewById(int) to retrieve the widgets in that UI that you need to interact with programmatically.
	 */
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    Log.i(TAG, "onCreate()");
	    uiHelper = new UiLifecycleHelper(getActivity(), callback);
	    uiHelper.onCreate(savedInstanceState);
	}

	//FB API to generate the view and get the permissions of the APP user from FB
	@Override
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, 
	        Bundle savedInstanceState) {
		Log.i(TAG, "onCreateView()");
	    view = inflater.inflate(R.layout.fragment_facebook_login,container, false);
	    login_button = (LoginButton) view.findViewById(R.id.login_button);  
	    login_button.setReadPermissions(Arrays.asList("public_profile","user_likes", "user_status","user_friends","email"));
	    return view;
	}
	
	
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
	    if (state.isOpened()) {
	        Log.i(TAG, "Logged in...");
	    } else if (state.isClosed()) {
	        Log.i(TAG, "Logged out...");
	    }
	}
	private Session.StatusCallback callback = new Session.StatusCallback() {
		
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	    	Log.i(TAG, "StatusCallback() call()");
	        onSessionStateChange(session, state, exception);
	    }
	};
	
	@Override
	public void onResume() {
	    super.onResume();
	 // For scenarios where the main activity is launched and user
	    // session is not null, the session state change notification
	    // may not be triggered. Trigger it if it's open/closed.
	    Log.i(TAG, "onResume()");
	    Session session = Session.getActiveSession();
	    if (session != null &&
	           (session.isOpened() || session.isClosed()) ) {
	        onSessionStateChange(session, session.getState(), null);
	    }

	    uiHelper.onResume();
	}

	//This function is used for passing back the login info.
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    Log.i(TAG, "onActivityResult()");
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	/*
	 * onPause() is where you deal with the user leaving your activity. 
	 * Most importantly, any changes made by the user should at this point be committed 
	 * (usually to the ContentProvider holding the data). 
	 */
	public void onPause() {
		Log.i(TAG, "onPause()");
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
		Log.i(TAG, "onDestroy()");
	    super.onDestroy();
	    uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		Log.i(TAG, "onSaveInstanceState()");
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}

	
};
