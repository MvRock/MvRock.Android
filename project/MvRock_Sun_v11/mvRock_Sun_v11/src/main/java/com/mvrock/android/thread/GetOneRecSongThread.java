package com.mvrock.android.thread;

import android.util.Log;

import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.view.MvRockView;

import org.apache.http.message.BasicNameValuePair;

public class GetOneRecSongThread extends MvRockThreadObject {

    public GetOneRecSongThread(String userId, String url) {
        super(userId, url);
        this.TAG += "GetOneRecSongThread";
        this.Url = "/getOneRecSong.php";
    }

    @Override
    public void run() {
        super.run();

        setResponse();

        MvRockView.MainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MvRockModel.YouMayLikeSongList.convertOneRecSongData();
            }
        });
    }

    public void setParams() {
        Log.i(TAG, "setParams()");
        params.add(new BasicNameValuePair("myuid", User_id));
        params.add(new BasicNameValuePair("url", Extra));
        params.add(new BasicNameValuePair("songId", String.valueOf(MvRockModel.CurrentSong.songId)));
        params.add(new BasicNameValuePair("playlistIndex", String.valueOf(MvRockModel.CurrentSong.currentMVIndex)));
    }

    public void setResponse() {
        MvRockModel.YouMayLikeSongList.setHttpResponse(strResponse);
    }
}
