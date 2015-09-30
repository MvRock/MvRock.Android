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
        TAG += "YouLikedSongList";
    }

    /**
     * analyzing the data String strResponse,
     * convert them in ArrayList and store the new data in songArrayList.
     */
    public void convertData() {
        Log.i(TAG, "convertData()");
        imageViewList.clear();
        songArrayList.clear();
        artistImages.clear();
        try {
            JSONObject YouMayLikeSongJSON = new JSONObject(strResponse);
            JSONArray liked_songs;
            if (YouMayLikeSongJSON.getJSONArray("LikedSong") == null) {
                songArrayList = null;
                return;
            } else {
                liked_songs = YouMayLikeSongJSON.getJSONArray("LikedSong");
            }

            JSONArray artistImageUrls = new JSONArray();

            for (int i = 0; i < liked_songs.length() && liked_songs.length() > 0; i++) {
                JSONObject cur = (JSONObject) liked_songs.get(i);
                Map<String, String> map = new HashMap<String, String>();
                map.put("song_name", cur.get("name").toString());
                map.put("artist_name", cur.get("artist").toString());
                map.put("url", cur.get("url").toString());
                songArrayList.add(map);

                artistImageUrls.put(i, cur.getString("ArtistPortrait"));
            }

            // get artist images
            GetArtistImageThread thread = new GetArtistImageThread(artistImages, artistImageUrls);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
