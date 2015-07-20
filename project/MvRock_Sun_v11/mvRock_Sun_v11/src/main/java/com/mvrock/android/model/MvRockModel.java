package com.mvrock.android.model;

import android.content.Context;

import com.mvrock.android.model.buddy.MusicBuddy;
import com.mvrock.android.model.buddy.RecBuddy;
import com.mvrock.android.model.buddy.User;
import com.mvrock.android.model.song.CurrentSong;
import com.mvrock.android.model.songlist.SearchStationList;
import com.mvrock.android.model.songlist.StationList;
import com.mvrock.android.model.songlist.StationSongList;
import com.mvrock.android.model.songlist.YouLikedSongList;
import com.mvrock.android.model.songlist.YouMayLikeSongList;

/**
 * Created by Xuer on 5/6/15.
 * Add comments on 5/24/15
 * <p/>
 * Define the object used in MvRock and initialize them in static
 * Define Set and Get method to get and set private object.
 */
public class MvRockModel {
    public static  DataInitialization dataInitialization;
    public static PlayListOption playListOption;
    public static YouLikedSongList YouLikedSongList;
    public static YouMayLikeSongList YouMayLikeSongList;
    public static StationSongList StationSongList;
    public static String CurrentStation;
    public static StationList StationList;
    public static SearchStationList SearchStationList;
    public static CurrentSong CurrentSong;

    public static Cache cache;
    public static RecBuddy RecBuddy;
    public static MusicBuddy MusicBuddy;
    public static User User;

    static {
        YouLikedSongList = new YouLikedSongList();
        YouMayLikeSongList = new YouMayLikeSongList();
        StationSongList = new StationSongList();
        SearchStationList = new SearchStationList();
        CurrentStation = "";
        StationList = new StationList();
        CurrentSong = new CurrentSong();
        RecBuddy = new RecBuddy();
        MusicBuddy = new MusicBuddy();
        User = new User();
        cache = new Cache();
        playListOption = PlayListOption.YOU_MAY_LIKE_LIST;
    }
}
