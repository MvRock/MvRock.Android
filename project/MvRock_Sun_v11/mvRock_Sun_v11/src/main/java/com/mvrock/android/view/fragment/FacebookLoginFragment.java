package com.mvrock.android.view.fragment;
/**
 * This is a view used for FB login.
 * Based on Fragment.
 * Using FB API
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.examples.youtubeapidemo.R;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class FacebookLoginFragment extends Fragment {

    private static final String TAG = "View.FBLoginFragment";

    //FB API to generate the view and get the permissions of the APP user from FB
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView()");

        View view = inflater.inflate(R.layout.fragment_facebook_login, container, false);

        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "user_likes", "user_status", "user_friends", "email"));

        return view;
    }
}