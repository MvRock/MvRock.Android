package com.mvrock.android.model;

import com.mvrock.android.model.buddy.MusicBuddy;
import com.mvrock.android.model.buddy.RecBuddy;
import com.mvrock.android.model.buddy.User;
import com.mvrock.android.model.song.CurrentSong;
import com.mvrock.android.model.songlist.StationList;
import com.mvrock.android.model.songlist.StationSongList;
import com.mvrock.android.model.songlist.YouLikedSongList;
import com.mvrock.android.model.songlist.YouMayLikeSongList;

import org.json.JSONArray;

/**
 * Created by Xuer on 5/6/15.
 * Add comments on 5/24/15
 *
 * Define the object used in MvRock and initialize them in static
 * Define Set and Get method to get and set private object.
 */
public class MvRockModel {
    private static final String TAG;
    public static PlayListOption playListOption;
    public static YouLikedSongList YouLikedSongList;
    public static YouMayLikeSongList YouMayLikeSongList;
    public static StationSongList StationSongList;
    public static String[] SearchStationResultList;
    public static String CurrentStation;
    public static StationList StationList;
    public static CurrentSong CurrentSong;

    public static RecBuddy RecBuddy;
    public static MusicBuddy MusicBuddy;
    public static User User;


    private static String UserData;
    public static int skin=-1;
    public static int language=-1;
    public static int accuPoint=-1;



    /**
     * Default "Constructor"
     */
    static{
        TAG="MvRockModel";
        YouLikedSongList=new YouLikedSongList();
        YouMayLikeSongList=new YouMayLikeSongList();
        StationSongList=new StationSongList();
        SearchStationResultList =null;
        CurrentStation="";
        StationList =new StationList();
        CurrentSong = new CurrentSong();
        RecBuddy=new RecBuddy();
        MusicBuddy=new MusicBuddy();
        User=new User();
        UserData="";
        playListOption = PlayListOption.YOU_MAY_LIKE_LIST;
    }

    /**
     * Setter for UserData
     * @param userData get this data from GetYouLikedSongAndUserDataThread
     */
    public static void setUserData(String userData) {UserData = userData;}

    /**
     * get # of Language from UserData
     * @return # of Language.
     */
    private static int getLanguage(){
        return language;
    }

    /**
     * get # of Skin from UserData
     * @return # of Skin.
     */
     private static int getSkin(){
         //TODO not finished
         return skin;
     }

    /**
     * get # of AccuPoint from UserData
     * @return # of AccuPoint;
     */
    private static int getAccuPoint(){
        //TODO not finished
        return accuPoint;
    }

    /**
     * YouLikedSongList object will call this function to assign JSONArray YouLikedSong
     * @return JSONArray of LikedSong
     */
    protected static JSONArray getYouLikedSong(){
        //TODO not finished
        return null;
    }



    /**
     * StationSongList object will call this function to assign JSONArray YouMayLikeSong
     * @return JSONArray of StationSong
     */
    protected static JSONArray getStationSong(){
        //TODO not finished
        return null;
    }

    /**
     * RecBuddy object will call this function to assign
     * @return JSONArray of RecBuddy
     */
    protected static JSONArray getRecBuddy(){
        //TODO not finished
        return null;
    }

    /**
     * MusicBuddy object will call this function to assign
     * @return JSONArray of MusicBuddy
     */
    protected static JSONArray getMusicBuddy(){
        //TODO not finished
        return null;
    }

}
