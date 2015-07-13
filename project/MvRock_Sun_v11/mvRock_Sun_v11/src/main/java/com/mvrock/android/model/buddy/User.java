package com.mvrock.android.model.buddy;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.facebook.Session;
import com.mvrock.android.model.LanguageOption;
import com.mvrock.android.model.MvRockModelObject;
import com.mvrock.android.thread.GetUserInfoThread;
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
    public Session Session;
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
        Session = null;
        User_Profile_pic = null;

        skin = -1;
        language = LanguageOption.ENG;
        accuPoint = -1;
    }

    public void RequestFBUserInfoByThread(){
        Log.i(TAG, "RequestFBUserInfoByThread()");
        Thread getUserInfoThread= new Thread(new GetUserInfoThread());
        getUserInfoThread.start();
        try {
            getUserInfoThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, User_Id);
    }

    public void getUserDataByThread() {
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
