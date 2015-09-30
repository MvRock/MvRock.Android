package com.mvrock.android.view;
/**
 * This is the MainActivity of the App
 * This activity extends FragmentActivity and so Fragment can be used in this activity.
 * Therefore the consistent of the activity is only one activity and several fragments are created to
 * implement some functions
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.examples.youtubeapidemo.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.mvrock.android.model.Cache;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.view.fragment.FacebookLoginFragment;
import com.mvrock.android.view.fragment.MvRockFragment;

import java.io.IOException;

public class MainActivity extends FragmentActivity {

    private static final String USER_SKIPPED_LOGIN_KEY = "user_skipped_login";
    private static final String TAG = "View.MainActivity";
    private static final long TIMEOUT = 2000L;

    private long backPressTime;
    private boolean userSkippedLogin = false;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    private CallbackManager callbackManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate()");
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            userSkippedLogin = savedInstanceState.getBoolean(USER_SKIPPED_LOGIN_KEY);
        }

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.main);
        MvRockView.MainActivity = this;

        // keeps track of access token changes
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                Log.i(TAG, "onCurrentAccessTokenChanged()");
                MvRockModel.User.accessToken = currentAccessToken;

                if (currentAccessToken == null) {
                    showFragment(MvRockView.FB_LOGIN_FRAG, false);
                }
            }
        };

        // keeps track of profile changes
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                Log.i(TAG, "onCurrentProfileChanged()");

                if (currentProfile != null) {
                    MvRockModel.User.profile = currentProfile;

                    MvRockModel.User.User_Id = currentProfile.getId();
                    MvRockModel.User.User_Name = currentProfile.getName();

                    Log.i(TAG, MvRockModel.User.User_Id);
                    Log.i(TAG, MvRockModel.User.User_Name);

                    MvRockModel.User.getProfilePicByThread(); // gets Facebook profile picture
                    MvRockModel.User.getUserDataByThread(); // gets language for toolbar

                    // we have all the user information, safe to show MvRockFragment now
                    showFragment(MvRockView.MVROCK_FRAG, false);
                }
            }
        };

        MvRockModel.User.accessToken = AccessToken.getCurrentAccessToken(); // assign taken if available
        MvRockModel.User.profile = Profile.getCurrentProfile(); // assign profile if available

        if (MvRockModel.User.accessToken == null) { // not signed in
            showFragment(MvRockView.FB_LOGIN_FRAG, false);
        }

        if (MvRockModel.User.profile != null) {
            MvRockModel.User.User_Id = MvRockModel.User.profile.getId();
            MvRockModel.User.User_Name = MvRockModel.User.profile.getName();

            Log.i(TAG, MvRockModel.User.User_Id);
            Log.i(TAG, MvRockModel.User.User_Name);

            MvRockModel.User.getProfilePicByThread(); // gets Facebook profile picture
            MvRockModel.User.getUserDataByThread(); // gets language for toolbar

            // we have all the user information, safe to show MvRockFragment now
            showFragment(MvRockView.MVROCK_FRAG, false);
        }
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume()");
        super.onResume();

        AppEventsLogger.activateApp(this);
    }

    @Override
    public void onPause() {
        Log.i(TAG, "onPause()");
        super.onPause();
        try {
            Cache.DiskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy()");

        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "onSaveInstanceState()");
        super.onSaveInstanceState(outState);

        outState.putBoolean(USER_SKIPPED_LOGIN_KEY, userSkippedLogin);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult()");
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if (backPressTime + TIMEOUT > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        }
        Toast.makeText(this, getResources().getString(R.string.backPressTwice), Toast.LENGTH_SHORT).show();
        backPressTime = System.currentTimeMillis();
    }

    public void showFragment(int fragmentIndex, boolean addToBackStack) {
        Log.i(TAG, "showFragment(" + fragmentIndex + ")");

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (fragmentIndex == MvRockView.FB_LOGIN_FRAG) {
            transaction.replace(R.id.fragmentLayout, new FacebookLoginFragment());
        } else if (fragmentIndex == MvRockView.MVROCK_FRAG) {
            transaction.replace(R.id.fragmentLayout, new MvRockFragment());
        }

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }
}
