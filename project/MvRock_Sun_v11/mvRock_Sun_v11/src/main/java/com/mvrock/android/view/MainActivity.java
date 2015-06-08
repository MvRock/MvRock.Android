package com.mvrock.android.view;
/**
 *This is the MainActivity of the App
 *This activity extends FragmentActivity and so Fragment can be used in this activity.
 *Therefore the consistent of the activity is only one activity and several fragments are created to
 implement some functions
 */
import com.examples.youtubeapidemo.R;
import com.facebook.AppEventsLogger;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.view.fragment.FbLoginFragment;
import com.mvrock.android.view.fragment.MvRockFragment;
import com.mvrock.android.view.fragment.YouLikedPlayListFragment;
import com.mvrock.android.view.fragment.YouMayLikePlayListFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

public class MainActivity extends FragmentActivity {
	private static final String USER_SKIPPED_LOGIN_KEY = "user_skipped_login";
	private static final String TAG = "View.MainActivity";

	
	private boolean isResumed = false;
	private boolean userSkippedLogin = false;
	private UiLifecycleHelper uiHelper;

    /*In onCreate, we initialize the uiHelper provided by FB; Initialize the Fragment list and Fragment
    Manager and Fragment Transaction. Also set the the fragment without login.
    */

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    Log.i(TAG,"onCreate()");
	    if (savedInstanceState != null) {
            userSkippedLogin = savedInstanceState.getBoolean(USER_SKIPPED_LOGIN_KEY);
        }
	    uiHelper = new UiLifecycleHelper(this, callback);
	    uiHelper.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
        MvRockView.MainActivity=this;
	    FbLoginFragment fbLoginFragment = new FbLoginFragment();
        MvRockView.FragmentList.add(this.getSupportFragmentManager().findFragmentById(R.id.fbloginFragment));


        this.getSupportFragmentManager().beginTransaction().show(MvRockView.FragmentList.get(MvRockView.FBLOGIN_FRAG)).commit();
	    fbLoginFragment.setSkipLoginCallback(new FbLoginFragment.SkipLoginCallback() {
            @Override
            public void onSkipLoginPressed() {
                userSkippedLogin = true;
                showFragment(MvRockView.MVROCK_FRAG, false);
            }
        });
	}	

	@Override
	public void onResume() {
		Log.i(TAG,"onResume()");
        super.onResume();
        uiHelper.onResume();
        isResumed = true;
        AppEventsLogger.activateApp(this);
    }
	
	
	@Override
	 public void onPause() {
		Log.i(TAG,"onPause()");
        super.onPause();
        uiHelper.onPause();
        isResumed = false;
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	Log.i(TAG,"onActivityResult()");
        super.onActivityResult(requestCode, resultCode, data);    
            uiHelper.onActivityResult(requestCode, resultCode, data);
            if (requestCode== MvRockView.MVROCK_FRAG){
    			if(resultCode==MvRockView.FB_LOGOUT)
    			{
    				if (Session.getActiveSession() != null) {
    				    Session.getActiveSession().closeAndClearTokenInformation();
    				}

    				Session.setActiveSession(null);
    				userSkippedLogin=false;
    			}
    				
    			}
       
    }

    @Override
    public void onDestroy() {
    	Log.i(TAG,"onDestroy()");
    	
        super.onDestroy();
        uiHelper.onDestroy();
    }

    //Change when the state of current session state change
    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        // Only make changes if the activity is visible
        Log.i(TAG,"onSessionStateChange()");
        if (isResumed) {
            FragmentManager manager = getSupportFragmentManager();
            // Get the number of entries in the back stack
            int backStackSize = manager.getBackStackEntryCount();
            // Clear the back stack
            for (int i = 0; i < backStackSize; i++) {
                manager.popBackStack();
            }
            if (state.isOpened()) {
                // If the session state is open:
                // Show the authenticated fragment
                showFragment(MvRockView.MVROCK_FRAG, false);
            } else if (state.isClosed()) {
                // If the session state is closed:
                // Show the login fragment
                showFragment(MvRockView.FBLOGIN_FRAG, false);
            }
        }
    }
    //According to the info to choose which fragment showed.
    public void showFragment(int fragmentIndex, boolean addToBackStack) {
        Log.i(TAG,"showFragment("+fragmentIndex+")");
        for (int i = 0; i < MvRockView.FragmentList.size(); i++) {
            if (i == fragmentIndex) {
                this.getSupportFragmentManager().beginTransaction().show(MvRockView.FragmentList.get(i)).commit();
            } else {
                this.getSupportFragmentManager().beginTransaction().hide(MvRockView.FragmentList.get(i)).commit();
            }
        }
        if (addToBackStack) {
            this.getSupportFragmentManager().beginTransaction().addToBackStack(null).commit();
        }

    }


    @Override
    protected void onResumeFragments() {
        Log.i(TAG,"onResumeFragments()");

        super.onResumeFragments();
        Session session = Session.getActiveSession();

        if (session != null && session.isOpened()) {
            MvRockModel.User.Session= Session.getActiveSession();
            MvRockModel.User.RequestFBUserInfoByThread();
            if (MvRockView.FragmentList.size()<(MvRockView.MVROCK_FRAG+1)) {
                MvRockView.MvRockFragment=new MvRockFragment();
                MvRockView.YouMayLikePlayListFragment=new YouMayLikePlayListFragment();
                MvRockView.YouLikedPlayListFragment =new YouLikedPlayListFragment();

                MvRockView.FragmentList.add(MvRockView.MvRockFragment);
                this.getSupportFragmentManager().beginTransaction()
                        .add(R.id.mvRockFragment, MvRockView.FragmentList.get(MvRockView.MVROCK_FRAG)).commit();

            }
            showFragment(MvRockView.MVROCK_FRAG, false);
            userSkippedLogin = false;
        }else {
            showFragment(MvRockView.FBLOGIN_FRAG, false);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i(TAG,"onSaveInstanceState()");
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);

        outState.putBoolean(USER_SKIPPED_LOGIN_KEY, userSkippedLogin);
    }

    private Session.StatusCallback callback =
            new Session.StatusCallback() {
                @Override
                public void call(Session session,
                                 SessionState state, Exception exception) {
                    Log.i(TAG,"StatusCallback() call()");
                    onSessionStateChange(session, state, exception);
                }
            };
	
}
