package com.mvrock.android.model.songlist;

import android.graphics.drawable.Drawable;

import com.mvrock.android.model.MvRockModelObject;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Xuer on 5/9/15.
 * Add comment on 5/26/15.
 *
 * This class is used for reading JSON info of stationList of one user.
 * When the stationList is required to load, add the station one by one and change its format for showing
 */
public class StationList  extends MvRockModelObject {
    public ArrayList<Map<String, String>> stationArrayList;
    public ArrayList<Drawable> stationImageArrayList;
    public StationList(){
        super();
        this.TAG+="StationList";
        stationArrayList = new ArrayList<Map<String, String>>();
        stationImageArrayList = new ArrayList<Drawable>();
    }

    public void convertData(){
        try {
            JSONArray stationListJSONArray = new JSONArray(strResponse);
            stationArrayList = new ArrayList<Map<String, String>>();

            if (stationListJSONArray!= null) {
                for (int i = 0; i < stationListJSONArray.length(); i++) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("station_name",stationListJSONArray.getJSONObject(i)
                            .get("stationname").toString());
                    map.put("url",stationListJSONArray.getJSONObject(i)
                            .get("ArtistPortrait").toString());
                    stationArrayList.add(map);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
