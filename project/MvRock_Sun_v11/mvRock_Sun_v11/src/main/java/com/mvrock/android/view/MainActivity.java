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

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private static final String USER_SKIPPED_LOGIN_KEY = "user_skipped_login";
    private static final String TAG = "View.MainActivity";

    private static final int FBLOGIN_FRAG = 0;
    private static final int FB_LOGOUT = -1;
    private static final int MVROCK_FRAG = 1;
    private int FRAGMENT_COUNT = MVROCK_FRAG + 1;
    private ArrayList<Fragment> fragments;

    private FragmentTransaction transaction;
    private FragmentManager fm;

    private boolean isResumed = false;

    private boolean userSkippedLogin = false;
    private UiLifecycleHelper uiHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate()");

        if (savedInstanceState != null) {
            userSkippedLogin = savedInstanceState.getBoolean(USER_SKIPPED_LOGIN_KEY);
        }


        uiHelper = new UiLifecycleHelper(this, callback);
        uiHelper.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        fragments = new ArrayList<Fragment>();

        FRAGMENT_COUNT = FBLOGIN_FRAG + 1;

        fm = getSupportFragmentManager();

        FbLoginFragment fbLoginFragment = new FbLoginFragment();
        //fragments[FBLOGIN_FRAG] = fm.findFragmentById(R.id.fbloginFragment);

        fragments.add(fm.findFragmentById(R.id.fbloginFragment));
        //fragments[MVROCK_FRAG]=null;

        transaction = fm.beginTransaction();

        transaction.show(fragments.get(FBLOGIN_FRAG));

        transaction.commit();

        fbLoginFragment.setSkipLoginCallback(new FbLoginFragment.SkipLoginCallback() {
            @Override
            public void onSkipLoginPressed() {
                userSkippedLogin = true;
                showFragment(MVROCK_FRAG, false);
            }
        });
    }


    @Override
    public void onResume() {
        Log.i(TAG, "onResume()");
        super.onResume();
        uiHelper.onResume();
        isResumed = true;
        AppEventsLogger.activateApp(this);
    }


    @Override
    public void onPause() {
        Log.i(TAG, "onPause()");
        super.onPause();
        uiHelper.onPause();
        isResumed = false;
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult()");
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MVROCK_FRAG) {
            if (resultCode == FB_LOGOUT) {
                if (Session.getActiveSession() != null) {
                    Session.getActiveSession().closeAndClearTokenInformation();
                }
                Session.setActiveSession(null);
                userSkippedLogin = false;
            }

        }

    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy()");

        super.onDestroy();
        uiHelper.onDestroy();
    }


    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        // Only make changes if the activity is visible
        Log.i(TAG, "onSessionStateChange()");
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
                showFragment(MVROCK_FRAG, false);
            } else if (state.isClosed()) {
                // If the session state is closed:
                // Show the login fragment
                showFragment(FBLOGIN_FRAG, false);
            }
        }
    }

    public void showFragment(int fragmentIndex, boolean addToBackStack) {
        Log.i(TAG, "showFragment(" + fragmentIndex + ")");
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            if (i == fragmentIndex) {
                if (i == 1) {
                    MvRockModel.User.Session = Session.getActiveSession();
                    MvRockModel.User.RequestFBUserInfoByThread();
                }
                transaction.show(fragments.get(i));
            } else {
                transaction.hide(fragments.get(i));
            }
        }
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }


    @Override
    protected void onResumeFragments() {
        Log.i(TAG, "onResumeFragments()");

        super.onResumeFragments();
        Session session = Session.getActiveSession();

        if (session != null && session.isOpened()) {
            if (fragments.size() < (MVROCK_FRAG + 1)) {
                fragments.add(new MvRockFragment());
                fm = getSupportFragmentManager();
                transaction = fm.beginTransaction();
                transaction.add(R.id.mvRockFragment, fragments.get(MVROCK_FRAG));
                transaction.commit();
            }
            showFragment(MVROCK_FRAG, false);
            userSkippedLogin = false;
        } else {
            showFragment(FBLOGIN_FRAG, false);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "onSaveInstanceState()");
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);

        outState.putBoolean(USER_SKIPPED_LOGIN_KEY, userSkippedLogin);
    }

    private Session.StatusCallback callback =
            new Session.StatusCallback() {
                @Override
                public void call(Session session,
                                 SessionState state, Exception exception) {
                    Log.i(TAG, "StatusCallback() call()");
                    onSessionStateChange(session, state, exception);
                }
            };

}
