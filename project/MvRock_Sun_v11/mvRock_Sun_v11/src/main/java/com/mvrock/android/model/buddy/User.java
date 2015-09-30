package com.mvrock.android.model.buddy;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.mvrock.android.model.LanguageOption;
import com.mvrock.android.model.MvRockModelObject;
import com.mvrock.android.thread.GetProfilePicThread;
import com.mvrock.android.thread.GetYouLikedSongAndUserDataThread;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Xuer on 5/8/15.
 * Add comments on 5/25/15
 *
 * This class is used for get the info of one user.
 * call a thread to get the info.
 *
 */
public class User extends MvRockModelObject {
    public AccessToken accessToken;
    public Profile profile;
    public String User_Id;
    public String User_Name;
    public Drawable User_Profile_pic;

    public int skin;
    public int language;
    public int accuPoint;

    public User() {
        TAG += "User";
        User_Id = "";
        User_Name = "";
        accessToken = null;
        profile = null;
        User_Profile_pic = null;

        skin = -1;
        language = LanguageOption.ENG;
        accuPoint = -1;
    }

    public void getProfilePicByThread(){
        Log.i(TAG, "getProfilePicByThread()");

        GetProfilePicThread getProfilePicThread = new GetProfilePicThread();
        getProfilePicThread.start();
        try {
            getProfilePicThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getUserDataByThread() {
        // this is used to get the language, skin, and accuPoint data! language data is used for toolbar image
        GetYouLikedSongAndUserDataThread getYouLikedSongAndUserDataThread = new GetYouLikedSongAndUserDataThread(User_Id, null);
        getYouLikedSongAndUserDataThread.start();
        try {
            getYouLikedSongAndUserDataThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getYouLikedSongAndUserDataThread.setResponse();
        convertData();
    }

    @Override
    public void convertData() {
        try {
            JSONObject jsonObject = new JSONObject(strResponse);

            skin = Integer.parseInt(jsonObject.getString("Skin"));
            language = Integer.parseInt(jsonObject.getString("Language"));
            accuPoint = Integer.parseInt(jsonObject.getString("AccuPoint"));

            Log.i(TAG, String.format("skin = %d, language = %d, accuPoint = %d", skin, language, accuPoint));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
