package com.mvrock.android.model.songlist;

import android.util.Log;

import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.thread.GetArtistImageThread;
import com.mvrock.android.uicomponent.MvRockUiComponent;

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
        imageViewList.clear();
        songArrayList.clear();
        artistImages.clear();
        try {
            JSONObject YouMayLikeSongJSON = new JSONObject(strResponse);
            JSONArray names = YouMayLikeSongJSON.getJSONArray("Name");
            JSONArray urls = YouMayLikeSongJSON.getJSONArray("Url");
            JSONArray songIds = YouMayLikeSongJSON.getJSONArray("SongId");
            JSONArray reasons = YouMayLikeSongJSON.getJSONArray("Reason");
            JSONArray artists = YouMayLikeSongJSON.getJSONArray("Artist");
            JSONArray artistImageUrls = YouMayLikeSongJSON.getJSONArray("ArtistPortrait");

            for (int i = 0; i < names.length(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("song_name", names.get(i).toString());
                map.put("url", urls.get(i).toString());
                map.put("song_id", songIds.get(i).toString());
                map.put("reason", reasons.get(i).toString());
                map.put("artist_name", artists.get(i).toString());
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

    public void convertOneRecSongData() {
        try {
            JSONObject recSongJSON = new JSONObject(strResponse);

            Map<String, String> map = new HashMap<String, String>();
            map.put("song_name", recSongJSON.getString("name"));
            map.put("url", recSongJSON.getString("url"));
            map.put("song_id", recSongJSON.getString("songId"));
            map.put("reason", "3");
            map.put("artist_name", recSongJSON.getString("artist"));
            songArrayList.add(MvRockModel.CurrentSong.currentMVIndex + 1, map); // insert after the liked song
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MvRockUiComponent.YouMayLikePlayListView.RefreshListView();
    }
}