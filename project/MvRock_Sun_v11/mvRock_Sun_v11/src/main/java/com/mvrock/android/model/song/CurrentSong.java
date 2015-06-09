package com.mvrock.android.model.song;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.model.MvRockModelObject;
import com.mvrock.android.model.ReasonOption;
import com.mvrock.android.view.MvRockView;

import org.json.JSONException;
import org.json.JSONObject;

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
    public String url, songName, artistName;
    public int currentTime;
    public ReasonOption reason;
    public Drawable artistImage;

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
    }

    public void convertData() {
        try {
            Log.i(TAG, "convertData()");
            JSONObject JSON = new JSONObject(strResponse);
            int rating = 0;
            rating = Integer.parseInt(JSON.get("UserRating").toString());
            Log.i(TAG, "rating = " + rating);
            MvRockModel.CurrentSong.isLikedIconPressed = rating > 0;
            MvRockModel.CurrentSong.isDislikedIconPressed = rating < 0;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void updateViews() {
        View view = MvRockView.MvRockFragment.getView();

        ((TextView) view.findViewById(R.id.music_title)).setText(songName);
        ((TextView) view.findViewById(R.id.recommendation_reasons)).setText(reason.toString());
        ((TextView) view.findViewById(R.id.artist_title)).setText(artistName);
        ((ImageView) view.findViewById(R.id.artist_image)).setImageDrawable(artistImage);
    }
}
