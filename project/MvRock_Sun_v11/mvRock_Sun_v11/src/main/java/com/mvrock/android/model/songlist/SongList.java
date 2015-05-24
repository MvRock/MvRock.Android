package com.mvrock.android.model.songlist;

import android.graphics.drawable.Drawable;

import com.mvrock.android.model.MvRockModelObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Xuer on 5/8/15.
 */
public abstract class SongList extends MvRockModelObject {
    public Map<Integer, Drawable> imageViewList;
    public ArrayList<Map<String, String>> songArrayList;

    protected SongList() {
        songArrayList=new ArrayList<Map<String, String>>();
        imageViewList=new HashMap<Integer, Drawable>();
        strResponse ="";
    }


    /**
     * analyzing the data String strResponse,
     * convert them in ArrayList and store the new data in songArrayList.
     */
    public abstract void convertData();
}
