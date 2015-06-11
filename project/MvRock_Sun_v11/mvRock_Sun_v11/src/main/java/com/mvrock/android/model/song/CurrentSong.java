package com.mvrock.android.model.song;

import android.util.Log;
import android.widget.TextView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.model.MvRockModelObject;
import com.mvrock.android.model.ReasonOption;
import com.mvrock.android.view.MvRockView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Xuer on 5/9/15.
 * Add comment on 5/24/15
 * <p/>
 * This class is used to deal with the current song which is liked or disliked by user.
 */
public class CurrentSong extends MvRockModelObject {
    public boolean isLikedIconPressed;
    public boolean isDislikedIconPressed;
    public boolean isChanged;
    public int currentMVIndex;
    public String url, name;
    public int currentTime;
    public ReasonOption reason;
    public int numberOfComments;
    public Map<String, String> nameAndComments;

    public CurrentSong() {
        super();
        this.TAG += "CurrentSong";
        this.isLikedIconPressed = false;
        this.isDislikedIconPressed = false;
        this.isChanged = false;
        this.currentMVIndex = -1;
        this.currentTime = 0;
        this.url = "";
        this.reason = ReasonOption.None;
        this.numberOfComments = 0;
        nameAndComments = new HashMap<>();

    }

    public void convertData() {
        try {
            Log.i(TAG, "convertData()");
            JSONObject infoJSON = new JSONObject(strResponse);
            JSONArray commentArrayJSON = infoJSON.getJSONArray("Comment");
            numberOfComments = commentArrayJSON.length();
            for(int i = 0 ; i < commentArrayJSON.length();i++){
                JSONObject tmp = (JSONObject)commentArrayJSON.get(i);
                nameAndComments.put(tmp.get("name").toString(),
                        tmp.get("content").toString());
            }
            Log.i(TAG,nameAndComments.toString());
            int rating = 0;
            rating = Integer.parseInt(infoJSON.get("UserRating").toString());
            Log.i(TAG, "rating = " + rating);
            MvRockModel.CurrentSong.isLikedIconPressed = rating > 0;
            MvRockModel.CurrentSong.isDislikedIconPressed = rating < 0;

        } catch (JSONException e) {
            numberOfComments = 0;
            e.printStackTrace();
        }
    }

    public void updateViews() {
        ((TextView) MvRockView.MvRockFragment.getView().findViewById(R.id.music_title)).setText(name);
        ((TextView) MvRockView.MvRockFragment.getView().findViewById(R.id.recommendation_reasons)).setText(reason.toString());
    }
}
