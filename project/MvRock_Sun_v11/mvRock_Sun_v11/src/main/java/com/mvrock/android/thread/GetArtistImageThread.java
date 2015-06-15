package com.mvrock.android.thread;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.SparseArray;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.view.MvRockView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Kenneth on 6/9/2015.
 */
public class GetArtistImageThread extends Thread {

    private SparseArray<Drawable> artistImages;
    private JSONArray imageUrls;

    public GetArtistImageThread(SparseArray<Drawable> artistImages, JSONArray imageUrls){
        this.artistImages = artistImages;
        this.imageUrls = imageUrls;
    }

    @Override
    public void run() {
        for (int i = 0; i < imageUrls.length(); i++) {
            try {
                InputStream is = (InputStream) new URL(imageUrls.getString(i)).getContent();
                Drawable drawable = Drawable.createFromStream(is, "src");

                if (drawable != null) {
                    artistImages.put(i, drawable);
                }
            } catch (JSONException|IOException e) {
                Log.e(this.getClass().getSimpleName(), "Image download failed", e);
                // Show "download fail" image
                Drawable drawable = MvRockView.MainActivity.getResources().getDrawable(R.drawable.image_fail);
                artistImages.put(i, drawable);
            }
        }
    }
}
