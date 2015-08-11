package com.mvrock.android.model.buddy;

import android.util.Log;

import com.mvrock.android.model.MvRockModelObject;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


/**
 * Created by Xuer on 5/6/15.
 */
public class RecBuddy extends MvRockModelObject {

    public ArrayList<String> buddyName;
    public ArrayList<String> buddyUid;

    public RecBuddy() {
        buddyName = new ArrayList<>();
        buddyUid = new ArrayList<>();
        TAG += "RecBuddy";
    }

    @Override
    public void convertData() {
        Log.i(TAG, "convertDate()");
        buddyName.clear();
        buddyUid.clear();
        try {
            JSONArray RecBuddyJSON = new JSONArray(strResponse);
            for (int i = 0; i < RecBuddyJSON.length(); i++) {
                String uid = (String) RecBuddyJSON.getJSONObject(i).get("uid");
                String name = (String) RecBuddyJSON.getJSONObject(i).get("userName");
                buddyName.add(name);
                buddyUid.add(uid);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
