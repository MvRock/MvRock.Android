package com.mvrock.android.model.buddy;

import android.util.Log;

import com.mvrock.android.model.MvRockModelObject;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Xuer on 5/6/15.
 */

/**
 * JSON format:
 * <p/>
 * "MusicBuddy":[{
 * "uid":"100005047002553",
 * "userName":"Sai Jiang",
 * "CurrentUrl":"-Tkuifcx-y8",
 * "songName":"\u539a\u8138\u76ae",
 * "Artist":"Ella",
 * "From":"3",
 * "Message":" "
 * }]
 */

public class BuddyFeed extends MvRockModelObject {
    public Set<String> musicBuddy;

    public BuddyFeed() {
        musicBuddy = new HashSet<>();
        TAG += "Music Buddy";
    }

    public void convertData() {
        Log.i(TAG, "convertData()");
        try {
            JSONArray musicBuddyJSON = new JSONArray(strResponse);
            for (int i = 0; i < musicBuddyJSON.length(); i++) {
                String name = (String) musicBuddyJSON.getJSONObject(i).get("userName");
                if (!musicBuddy.contains(name))
                    musicBuddy.add(name);
            }
            Log.i(TAG, musicBuddy.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
