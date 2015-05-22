package com.mvrock.android.model.buddy;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.facebook.Session;
import com.mvrock.android.model.MvRockModelObject;
import com.mvrock.android.thread.GetUserInfoThread;

/**
 * Created by Xuer on 5/8/15.
 */
public class User {
    protected String TAG;
    public Session Session;
    public String User_Id;
    public String User_Name;
    public Drawable User_Profile_pic;
    public User() {
        TAG+="Model.User";
        User_Id="";
        User_Name="";
        Session=null;
        User_Profile_pic=null;
    }

    public void RequestFBUserInfoByThread(){
        Log.i(TAG, "RequestFBUserInfoByThread()");
        Thread getUserInfoThread= new Thread(new GetUserInfoThread());
        getUserInfoThread.start();
        try {
            getUserInfoThread.join();
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }



}
