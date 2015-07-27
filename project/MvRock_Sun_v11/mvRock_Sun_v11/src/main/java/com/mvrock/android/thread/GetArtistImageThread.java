package com.mvrock.android.thread;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.mvrock.android.model.MvRockModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kenneth on 6/9/2015.
 */
public class GetArtistImageThread extends Thread {

    private  String TAG = "GetArtistImageThread";
    private ArrayList<Drawable> artistImages;
    private ArrayList<Map<String, String>> artistsList;

    public GetArtistImageThread(ArrayList<Drawable> artistImages, JSONArray imageUrls){
        this.artistImages = artistImages;
        artistsList = new ArrayList<Map<String, String>>();

        for(int i = 0; i < imageUrls.length(); i++){
            try {
                HashMap<String, String> memo = new HashMap<String, String>(1);
                memo.put("url", imageUrls.getString(i));
                artistsList.add(memo);
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        Log.i(TAG,"run()");
        MvRockModel.cache.getImageFromCache(artistImages, artistsList, "", "");
    }
}
