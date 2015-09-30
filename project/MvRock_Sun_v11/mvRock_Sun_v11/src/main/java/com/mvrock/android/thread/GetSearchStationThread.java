package com.mvrock.android.thread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

import com.mvrock.android.model.MvRockModel;

public class GetSearchStationThread extends MvRockThreadObject {

	public GetSearchStationThread(String User_id, String searchStr) {
        super(User_id,searchStr);
        this.TAG+= "GetSearchStationThread";
        this.Url="/getRecStation.php";
	}

    public void setResponse(){
        MvRockModel.SearchStationList.setHttpResponse(this.strResponse);}

    public void setParams() {
        Log.i(TAG, "setParams()");
        params.add(new BasicNameValuePair("myId", this.User_id));
        params.add(new BasicNameValuePair("searchStr", this.Extra));
    }

}
