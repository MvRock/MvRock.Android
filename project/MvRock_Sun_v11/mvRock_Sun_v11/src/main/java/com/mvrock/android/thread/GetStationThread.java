package com.mvrock.android.thread;

import android.util.Log;

import com.mvrock.android.model.MvRockModel;

import org.apache.http.message.BasicNameValuePair;


public class GetStationThread  extends MvRockThreadObject {

	public GetStationThread(String User_id, String Extra){
        super(User_id,Extra);
        this.TAG+= "GetStationThread";
        this.Url="/getStation.php";
    }

	public void setParams() {
		Log.i(TAG, "setParams()");
        params.add(new BasicNameValuePair("uid", this.User_id));
	}
    public void setResponse(){
        MvRockModel.StationList.setHttpResponse(this.strResponse);}
}
