package com.mvrock.android.thread;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.SparseArray;

import com.mvrock.android.model.MvRockModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Kenneth on 6/9/2015.
 */
public class GetArtistImageThread extends Thread {

    private  String TAG = "GetArtistImageThread";
    private SparseArray<Drawable> artistImages;
    private List<Map<String, String>> artistsList;

    public GetArtistImageThread(SparseArray<Drawable> artistImages, JSONArray imageUrls){
        this.artistImages = artistImages;
        artistsList = new LinkedList<>();

        for(int i = 0 ; i < imageUrls.length() ; i++){
            try {

                Map<String, String> memo = new HashMap<>();
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
