package com.mvrock.android.model.buddy;

import com.mvrock.android.model.MvRockModel;

import org.json.JSONArray;

/**
 * Created by Xuer on 5/6/15.
 */

/**
 * JSON format:
 *
 *  "MusicBuddy":[{
 *  "uid":"100005047002553",
 *  "userName":"Sai Jiang",
 *  "CurrentUrl":"-Tkuifcx-y8",
 *  "songName":"\u539a\u8138\u76ae",
 *  "Artist":"Ella",
 *  "From":"3",
 *  "Message":" "
 *  }]
 */
public class MusicBuddy extends MvRockModel {

    private JSONArray MusicBuddy;

    public MusicBuddy(){
        MusicBuddy=new JSONArray();
    }
}
