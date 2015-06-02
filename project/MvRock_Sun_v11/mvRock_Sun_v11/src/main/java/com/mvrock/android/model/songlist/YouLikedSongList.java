package com.mvrock.android.model.songlist;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Xuer on 5/6/15.
 * Add comment on 5/26/15
 *
 * Similar with Class StationSongList
 */

/**
 * JSON format:
 *
 * "LikedSong":[{
 * "url":"AJtDXIazrMo",
 * "name":"Love Me Like You Do",
 * "artist":"Ellie Goulding"
 * }]
 */
public class YouLikedSongList extends SongList {
    public YouLikedSongList() {
        super();
        TAG+="YouLikedSongList";
    }

    /**
     * analyzing the data String strResponse,
     * convert them in ArrayList and store the new data in songArrayList.
     */
    public void convertData()  {
        Log.i(TAG, "convertData()");
        songArrayList.clear();
        try {
            Log.i(TAG, strResponse);
            JSONObject YouMayLikeSongJSON=new JSONObject(strResponse);
            JSONArray liked_songs = YouMayLikeSongJSON.getJSONArray("LikedSong");
            for (int i = 0; i < liked_songs.length(); i++) {
                JSONObject cur = (JSONObject) liked_songs.get(i);
                Map<String, String> map = new HashMap<String, String>();
                map.put("song_name", cur.get("name").toString());
                StringBuffer buffer = new StringBuffer();
                buffer.append("By ");
                buffer.append(cur.get("artist").toString());
                map.put("artist_name", buffer.toString());
                map.put("url", cur.get("url").toString());
                songArrayList.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
