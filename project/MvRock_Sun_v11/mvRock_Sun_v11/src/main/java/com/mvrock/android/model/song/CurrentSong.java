package com.mvrock.android.model.song;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.mvrock.android.model.MvRockModelObject;
import com.mvrock.android.model.ReasonOption;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Xuer on 5/9/15.
 * Add comment on 5/24/15
 * <p/>
 * This class is used to deal with the current song which is liked or disliked by user.
 */
public class CurrentSong extends MvRockModelObject {
    public boolean isLikedIconPressed, isDislikedIconPressed, isShared, hasSentSong, isReported;
    public boolean isChanged, isArtistSubscribed;
    public int currentMVIndex;
    public String url, songName, artistName, rootShareUserId;
    public int currentTime;
    public ReasonOption reason;
    public int numberOfComments, numLikes, numDislikes, songId;
    public ArrayList<String> commentAuthor;
    public ArrayList<String> commentContent;
    public ArrayList<String> commentTime;
    public ArrayList<String> authorID;
    public Drawable artistImage;

    public CurrentSong() {
        super();
        TAG += "CurrentSong";

        isLikedIconPressed = false;
        isDislikedIconPressed = false;
        isShared = false;
        isReported = false;
        isChanged = false;
        currentMVIndex = -1;
        currentTime = 0;
        url = "";
        reason = ReasonOption.None;
        numberOfComments = 0;
        rootShareUserId = "0";
        numLikes = 0;
        numDislikes = 0;

        commentAuthor = new ArrayList<>();
        commentTime = new ArrayList<>();
        commentContent = new ArrayList<>();
        authorID = new ArrayList<>();
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

            numberOfComments = 0;
            commentAuthor.clear();
            commentContent.clear();
            commentTime.clear();
            authorID.clear();

            try {
                JSONArray commentArrayJSON = infoJSON.getJSONArray("Comment");

                numberOfComments = commentArrayJSON.length();
                for (int i = 0; i < commentArrayJSON.length(); i++) {
                    JSONObject tmp = (JSONObject) commentArrayJSON.get(i);
                    String name = tmp.get("name").toString();
                    String content = tmp.get("content").toString();
                    String ID = tmp.get("uid").toString();
//                    String time = tmp.get("time").toString();
//                    commentTime.add(time);
                    commentAuthor.add(name);
                    commentContent.add(content);
                    authorID.add(ID);
                }
            } catch (JSONException e) {
                Log.i(TAG, "No comments");
                numberOfComments = 0;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}