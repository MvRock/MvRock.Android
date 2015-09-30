package com.mvrock.android.thread;

import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

import com.mvrock.android.model.MvRockModel;

public class GetNewSongDataThread extends MvRockThreadObject {
	
	public GetNewSongDataThread(String User_id,String Extra) {
        super(User_id,Extra);
        this.TAG+= "GetNewSongDataThread";
        this.Url= "/getNewSongData.php";
    }

	public void setParams() {
        Log.i(TAG, "setParams("+this.User_id+","+this.Extra+")");
		params.add(new BasicNameValuePair("userid",  this.User_id));
		params.add(new BasicNameValuePair("url", this.Extra));
	}

    public void setResponse(){
        MvRockModel.CurrentSong.setHttpResponse(this.strResponse);
    }
}
