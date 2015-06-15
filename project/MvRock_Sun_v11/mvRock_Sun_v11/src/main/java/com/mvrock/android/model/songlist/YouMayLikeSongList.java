package com.mvrock.android.model.songlist;

import android.util.Log;

import com.mvrock.android.thread.GetArtistImageThread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Xuer on 5/6/15.
 * Add comment on 5/26/15
 * <p/>
 * Similar with Class StationSongList
 */

/**
 * JSON format:
 * <p/>
 * {
 * "Name”:["I Want You To Know”,"\u5e78\u798f\u989d\u5ea6"],
 * "Url":["X46t8ZFqUB4","lWd7YUSzTMg"],
 * "Artist":["Zedd","\u82cf\u6253\u7eff"],
 * "SongId":["172447","31719"]
 * }
 */
public class YouMayLikeSongList extends SongList {
    public YouMayLikeSongList() {
        super();
        TAG += "YouMayLikeSongList";
    }

    /**
     * analyzing the data String strResponse,
     * convert them in ArrayList and store the new data in songArrayList.
     */
    public void convertData() {
        Log.i(TAG, "convertData()");
        songArrayList.clear();
        try {
            JSONObject YouMayLikeSongJSON = new JSONObject(strResponse);
            JSONArray names = YouMayLikeSongJSON.getJSONArray("Name");
            JSONArray urls = YouMayLikeSongJSON.getJSONArray("Url");
            JSONArray reasons = YouMayLikeSongJSON.getJSONArray("Reason");
            JSONArray artists = YouMayLikeSongJSON.getJSONArray("Artist");
            JSONArray artistImageUrls = YouMayLikeSongJSON.getJSONArray("ArtistPortrait");

            for (int i = 0; i < names.length(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("song_name", names.get(i).toString());
                map.put("url", urls.get(i).toString());
                map.put("reason", reasons.get(i).toString());
                StringBuffer buffer = new StringBuffer();
                buffer.append("By ");
                buffer.append(artists.get(i).toString());
                map.put("artist_name", buffer.toString());
                songArrayList.add(map);
            }

            // start gathering artist images
            GetArtistImageThread thread = new GetArtistImageThread(artistImages, artistImageUrls);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.i(TAG, songArrayList.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}