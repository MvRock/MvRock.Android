package com.mvrock.android.model.song;

import android.util.Log;

import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.model.MvRockModelObject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Xuer on 5/9/15.
 * Add comment on 5/24/15
 *
 * This class is used to deal with the current song which is liked or disliked by user.
 *
 */
public class CurrentSong extends MvRockModelObject {
    public boolean isLikedIconPressed;
    public boolean isDislikedIconPressed;
    protected String strResponse;

    public CurrentSong() {
        super();
        this.TAG +="CurrentSong";
        this.isLikedIconPressed =false;
        this.isDislikedIconPressed = false;
    }

    public void convertData(){
        try {
            JSONObject JSON = new JSONObject(strResponse);
            int rating = 0;
            rating = Integer.parseInt(JSON.get("UserRating").toString());
            Log.i(TAG, "rating = " + rating);
            MvRockModel.CurrentSong.isLikedIconPressed = rating>0;
            MvRockModel.CurrentSong.isDislikedIconPressed = rating<0;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
