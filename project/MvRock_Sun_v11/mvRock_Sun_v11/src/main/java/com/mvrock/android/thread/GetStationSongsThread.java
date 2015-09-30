package com.mvrock.android.thread;

import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

import com.mvrock.android.model.MvRockModel;

public class GetStationSongsThread extends MvRockThreadObject {
	public GetStationSongsThread(String User_id,String Extra){
        super(User_id,Extra);
        this.TAG+= "GetStationSongsThread";
        this.Url= "/getstationsong.php";
    }

    protected void setParams() {
		Log.i(TAG, "setParams()");
		params.add(new BasicNameValuePair("myUid", this.User_id));
		params.add(new BasicNameValuePair("stationname", this.Extra));
	}

    public void setResponse(){
        MvRockModel.StationSongList.setHttpResponse(this.strResponse);
    }
}
