package com.mvrock.android.model;

import android.util.Log;

import com.mvrock.android.thread.GetStationSongsThread;
import com.mvrock.android.thread.GetYouLikedSongAndUserDataThread;
import com.mvrock.android.thread.GetYoumaylikePlayListThread;

/**
 * Created by Tianhao on 15/7/20.
 */
public class DataInitialization {
    String TAG = null;
    public DataInitialization(){
        TAG = "DataInitialization";
        Log.i(TAG,"DataInitialization");
        init();
    }
    public void init(){
        GetYouLikedSongAndUserDataThread youLikedSongInfoThread
                = new GetYouLikedSongAndUserDataThread(MvRockModel.User.User_Id, null);
        GetYoumaylikePlayListThread youMayLikedSongInfoThread
                = new GetYoumaylikePlayListThread(MvRockModel.User.User_Id, null);
        GetStationSongsThread stationSongInfoThread
                = new GetStationSongsThread(MvRockModel.User.User_Id, null);
        youLikedSongInfoThread.start();
        youMayLikedSongInfoThread.start();
        stationSongInfoThread.start();

        try{
            youLikedSongInfoThread.join();
            youMayLikedSongInfoThread.join();
            stationSongInfoThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        youLikedSongInfoThread.setResponse();
        MvRockModel.YouLikedSongList.convertData();
        youMayLikedSongInfoThread.setResponse();
        MvRockModel.YouMayLikeSongList.convertData();
        stationSongInfoThread.setResponse();
        MvRockModel.StationSongList.convertData();
    }

}
