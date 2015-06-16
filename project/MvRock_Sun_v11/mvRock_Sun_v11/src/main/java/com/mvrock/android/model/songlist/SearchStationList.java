package com.mvrock.android.model.songlist;

import android.util.Log;

import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.model.MvRockModelObject;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Xuer on 6/11/15.
 */
public class SearchStationList extends MvRockModelObject {
    public ArrayList<Map<String, String>> searchStationArrayList;
    public int[] subscribeList;
    public SearchStationList(){
        super();
        this.TAG+="SearchStationList";
        searchStationArrayList = new ArrayList<Map<String, String>>();
        subscribeList = new int[15];
        for(int i=0; i<15;i++)
            subscribeList[i]=0;
    }

    public void convertData(){
        Log.i(TAG,"convertData()");
        try {
            JSONArray searchStationListJSONArray = new JSONArray(strResponse);
            searchStationArrayList = new ArrayList<Map<String, String>>();
            if (searchStationListJSONArray!= null) {
                for (int i = 0; i < searchStationListJSONArray.length(); i++) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("station_name",searchStationListJSONArray.getJSONObject(i)
                            .get("station").toString());
                    searchStationArrayList.add(map);

                    for(int j=0; j< MvRockModel.StationList.stationArrayList.size();j++){
                        if(searchStationArrayList.get(i).get("station_name")
                                .equals(MvRockModel.StationList.stationArrayList.get(j).get("station_name"))){
                            subscribeList[i]=1;
                        }
                    }

                }
                Log.i(TAG, searchStationArrayList.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
