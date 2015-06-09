package com.mvrock.android.model.songlist;
/**
 * Created by Xuer on 5/7/15.
 * Add comment on 5/26/15.
 *
 * This class is used for reading info from JSON and store the info in songArrayList.
 *
 * The construction of the songArrayList is
 *
 * songArrayList
 * [
 * ...
 *  Map[
 *      song_name, songInfo
 *      url, songInfo
 *      artist_name, By artistInfo
 *      ]
 * ...
 * ]
 */
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
/**
 * JSON format:
 *
 * {
 *    "Name”:["I Want You To Know”,"\u5e78\u798f\u989d\u5ea6"],
 *    "Url":["X46t8ZFqUB4","lWd7YUSzTMg"],
 *    "Artist":["Zedd","\u82cf\u6253\u7eff"],
 *    "SongId":["172447","31719"]
 * }
 */
public class StationSongList extends SongList {
    public StationSongList() {
        super();
        TAG+="StationSongList";
    }

    /**
     * analyzing the data String strResponse,
     * convert them in ArrayList and store the new data in songArrayList.
     */
    public void convertData() {
        Log.i(TAG, "convertData()");
        songArrayList.clear();
        try {
            JSONObject YouMayLikeSongJSON=new JSONObject(strResponse);
            JSONArray names = YouMayLikeSongJSON.getJSONArray("Name");
            JSONArray urls = YouMayLikeSongJSON.getJSONArray("Url");
            JSONArray artists = YouMayLikeSongJSON.getJSONArray("Artist");
            for (int i = 0; i < names.length(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("song_name", names.get(i).toString());
                map.put("url", urls.get(i).toString());
                StringBuffer buffer = new StringBuffer();
                buffer.append("By ");
                buffer.append(artists.get(i).toString());
                map.put("artist_name", new String(buffer));
                songArrayList.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
