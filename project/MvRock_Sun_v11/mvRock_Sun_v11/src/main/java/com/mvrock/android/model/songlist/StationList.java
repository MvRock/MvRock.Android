package com.mvrock.android.model.songlist;

import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.model.MvRockModelObject;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Xuer on 5/9/15.
 * Add comment on 5/26/15.
 *
 * This class is used for reading JSON info of stationList of one user.
 * When the stationList is required to load, add the station one by one and change its format for showing
 */
public class StationList  extends MvRockModelObject {
    public ArrayList<String> stationArrayList;

    public StationList(){
        super();
        this.TAG+="StationList";
        stationArrayList = new ArrayList<String>();
    }

    public void convertData(){
        try {
            JSONArray stationListJSONArray = new JSONArray(strResponse);
            if (stationListJSONArray!= null) {
                for (int i = 0; i < stationListJSONArray.length(); i++) {
                    MvRockModel.StationList.stationArrayList.add(stationListJSONArray.getJSONObject(i)
                            .get("stationname").toString());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
