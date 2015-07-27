package com.mvrock.android.model.songlist;

import android.graphics.drawable.Drawable;

import com.mvrock.android.model.MvRockModelObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Xuer on 5/8/15.
 * Add comment on 5/26/15
 * <p/>
 * <p/>
 * Abstract class for Song
 * Extend from MvRockModelObjectList
 */
public abstract class SongList extends MvRockModelObject {
    public ArrayList<Drawable> imageViewList;
    public ArrayList<Map<String, String>> songArrayList;
    public ArrayList<Drawable> artistImages;

    protected SongList() {
        songArrayList = new ArrayList<Map<String, String>>();
        imageViewList = new ArrayList<Drawable>();
        artistImages = new ArrayList<Drawable>();
        strResponse = "";
    }


    /**
     * analyzing the data String strResponse,
     * convert them in ArrayList and store the new data in songArrayList.
     */
    public abstract void convertData();
}
