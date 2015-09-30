package com.mvrock.android.model.songlist;

import android.graphics.drawable.Drawable;

import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.model.MvRockModelObject;
import com.mvrock.android.thread.CreateStationThread;
import com.mvrock.android.thread.RemoveStationThread;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.util.Log.i;

/**
 * Created by Xuer on 5/9/15.
 * Add comment on 5/26/15.
 * <p/>
 * This class is used for reading JSON info of stationList of one user.
 * When the stationList is required to load, add the station one by one and change its format for showing
 */
public class StationList extends MvRockModelObject {

    public ArrayList<Map<String, String>> stationArrayList;
    public ArrayList<Drawable> stationImageArrayList;

    public StationList() {
        super();
        this.TAG += "StationList";
        stationArrayList = new ArrayList<Map<String, String>>();
        stationImageArrayList = new ArrayList<Drawable>();
    }

    public void convertData() {
        stationArrayList = new ArrayList<Map<String, String>>();
        try {
            JSONArray stationListJSONArray = new JSONArray(strResponse);

            for (int i = 0; i < stationListJSONArray.length(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("station_name", stationListJSONArray.getJSONObject(i)
                        .get("stationname").toString());
                map.put("url", stationListJSONArray.getJSONObject(i)
                        .get("ArtistPortrait").toString());
                stationArrayList.add(map);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void createStationByThread(String stationName) {
        i(TAG, "createStationByThread(" + stationName + ")");

        CreateStationThread createStationByThread = new CreateStationThread(stationName, MvRockModel.User.User_Id);
        createStationByThread.start();
        try {
            createStationByThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void removeStationByThread(String stationName) {
        i(TAG, "removeStationByThread(" + stationName + ")");

        RemoveStationThread removeStationByThread = new RemoveStationThread(stationName, MvRockModel.User.User_Id);
        removeStationByThread.start();
        try {
            removeStationByThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isSubscribed(String station) {
        for (int i = 0; i < stationArrayList.size(); i++) {
            if (stationArrayList.get(i).get("station_name").equals(station)) {
                return true;
            }
        }

        return false;
    }
}
