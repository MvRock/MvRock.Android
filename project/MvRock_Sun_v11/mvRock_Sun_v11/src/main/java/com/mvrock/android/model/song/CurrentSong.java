package com.mvrock.android.model.song;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.mvrock.android.model.MvRockModelObject;
import com.mvrock.android.model.ReasonOption;

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
    public boolean isLikedIconPressed, isDislikedIconPressed, isShared;
    public boolean isChanged;
    public int currentMVIndex;
    public String url, songName, artistName, rootShareUserId;
    public int currentTime;
    public ReasonOption reason;
    public int numberOfComments, numLikes, numDislikes;
    public Map<String, String> nameAndComments;
    public Drawable artistImage;

    public CurrentSong() {
        super();
        TAG += "CurrentSong";

        isLikedIconPressed = false;
        isDislikedIconPressed = false;
        isChanged = false;
        currentMVIndex = -1;
        currentTime = 0;
        url = "";
        reason = ReasonOption.None;
        numberOfComments = 0;
        nameAndComments = new HashMap<String, String>();
        rootShareUserId = "0";
        numLikes = 0;
        numDislikes = 0;
    }

    public void convertData() {
        Log.i(TAG, "convertData()");

        try {
            JSONObject infoJSON = new JSONObject(strResponse);

            int rating = Integer.parseInt(infoJSON.get("UserRating").toString());
            Log.i(TAG, "rating = " + rating);
            isLikedIconPressed = rating > 0;
            isDislikedIconPressed = rating < 0;

            int sharing = Integer.parseInt(infoJSON.get("UserSharing").toString());
            Log.i(TAG, "shared = " + sharing);
            isShared = sharing > 0;

            try {
                // gets all the good (liked) users and all the the bad (disliked) users
                // this does not include the user's rating, so we need to add that manually
                JSONObject userRatings = infoJSON.getJSONObject("UserbySongId");

                try {
                    numLikes = userRatings.getJSONArray("goodId").length() + (isLikedIconPressed ? 1 : 0);
                } catch (JSONException e) {
                    Log.i(TAG, "No like ratings");
                    numLikes = (isLikedIconPressed ? 1 : 0);
                }

                try {
                    numDislikes = userRatings.getJSONArray("badId").length() + (isDislikedIconPressed ? 1 : 0);
                } catch (JSONException e) {
                    Log.i(TAG, "No dislike ratings");
                    numDislikes = (isDislikedIconPressed ? 1 : 0);
                }
            } catch (JSONException e) {
                Log.i(TAG, "No user ratings");
                numLikes = (isLikedIconPressed ? 1 : 0);
                numDislikes = (isDislikedIconPressed ? 1 : 0);
            }
            Log.i(TAG, String.format("numLikes = %d, numDislikes = %d", numLikes, numDislikes));

            try {
                JSONArray commentArrayJSON = infoJSON.getJSONArray("Comment");

                numberOfComments = commentArrayJSON.length();
                for (int i = 0; i < commentArrayJSON.length(); i++) {
                    JSONObject tmp = (JSONObject) commentArrayJSON.get(i);
                    nameAndComments.put(tmp.get("name").toString(),
                            tmp.get("content").toString());
                }
            } catch (JSONException e) {
                Log.i(TAG, "No comments");
                numberOfComments = 0;
            }
            Log.i(TAG, nameAndComments.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}