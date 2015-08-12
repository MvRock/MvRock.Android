package com.mvrock.android.model.buddy;

import android.util.Log;

import com.mvrock.android.model.MvRockModelObject;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Tianhao on 15/8/12.
 */
public class MusicBuddy extends MvRockModelObject{
    public ArrayList<String> userName;
    public ArrayList<String> uID;
    public ArrayList<String> vip;

    public MusicBuddy(){
        TAG += "MusicBuddy";
        userName = new ArrayList<>();
        uID = new ArrayList<>();
        vip = new ArrayList<>();
    }

    @Override
    public void convertData() {
        userName.clear();
        uID.clear();
        Log.i(TAG, "convertData()");
        try{
            JSONArray musicBuddyJSON = new JSONArray(strResponse);
            for(int i = 0 ; i < musicBuddyJSON.length() ;i++){
                String curName = (String)musicBuddyJSON.getJSONObject(i).get("userName");
                String curId = (String) musicBuddyJSON.getJSONObject(i).get("uid");
                String curVip = (String) musicBuddyJSON.getJSONObject(i).get("vip");
                userName.add(curName);
                uID.add(curId);
                vip.add(curVip);
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
