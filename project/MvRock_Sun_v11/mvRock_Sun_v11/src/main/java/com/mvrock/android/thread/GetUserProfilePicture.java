package com.mvrock.android.thread;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.mvrock.android.model.MvRockModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tianhao on 15/8/19.
 */
public class GetUserProfilePicture extends Thread {
    private String TAG;
    private ArrayList<Drawable> userPicture;
    private List<Map<String, String>> userIDMap;

    public GetUserProfilePicture(ArrayList<Drawable> userPicture, ArrayList<String> userID) {
        TAG = "GetUserProfilePicture";
        this.userPicture = userPicture;
        this.userIDMap = new ArrayList<>();
        for (int i = 0; i < userID.size(); i++) {
            Map<String, String> memo = new HashMap<>();
            memo.put("url", userID.get(i));
            userIDMap.add(memo);
        }
    }

    @Override
    public void run() {
        Log.i(TAG, "run()");
        MvRockModel.cache.getImageFromCache(userPicture, userIDMap, "https://graph.facebook.com/", "/picture?width=100&height=100");

    }
}
