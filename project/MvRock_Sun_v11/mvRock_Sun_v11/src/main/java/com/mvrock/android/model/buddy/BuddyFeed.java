package com.mvrock.android.model.buddy;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.mvrock.android.model.MvRockModelObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

/**
 * <div class="musicBuddyElement1">
 *     <div id="MusicBuddy_0" class="Photos">
 *         <div class="headImg" id="100007963398995" onclick="showProfile(100007963398995)">
 *             <img width="35px" height="35px" src="https://graph.facebook.com/100007963398995/picture">
 *         </div>
 *     </div>
 *     <div class="musicBuddyExp">
 *         <a class="buddyname" target="_blank" href="https://www.facebook.com/100007963398995">
 *             Xingyu Gu
 *         </a>
 *         shared
 *         <b>
 *             <span id="MusicBuddySong-0" class="buddysong">Star in You</span>
 *         </b>
 *     </div>
 * </div>
 */

public class BuddyFeed extends MvRockModelObject {
    public List<Map<String,String>> buddyFeedList;
    public ArrayList<Drawable> userPic_List;
    public BuddyFeed() {
        buddyFeedList = new ArrayList<>();
        userPic_List = new ArrayList<>();
        TAG += "BuddyFeed";
    }

    public void convertData() {
        Log.i(TAG, "convertData()");
        try {
            JSONArray buddyFeedJSON = new JSONArray(strResponse);
            for (int i = 0; i < buddyFeedJSON.length(); i++) {
                JSONObject json = buddyFeedJSON.getJSONObject(i);
                Map<String,String> map = new HashMap<String,String>();
                map.put("uid", json.get("uid").toString());
                map.put("userName",json.get("userName").toString());
                map.put("CurrentUrl", json.get("CurrentUrl").toString());
                map.put("songName",json.get("songName").toString());
                map.put("Artist", json.get("Artist").toString());
                map.put("From",json.get("From").toString());
                map.put("Message", json.get("Message").toString());
                buddyFeedList.add(map);
            }
            Log.i(TAG, buddyFeedList.toString());

            //start gathering user picture from facebook.

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
