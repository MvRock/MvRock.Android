package com.mvrock.android.thread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class CreateStationThread_old implements Runnable {
	private static final String TAG = "GetRecStationThread";
	public static final String HOST = "http://wanlab.poly.edu";
	public static final String PATH = "/xing/tubeok";
	public static String stationName;
	private static String m_Userid;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Log.i(TAG, "CreateStationThread.run()");
		createStation(m_Userid, stationName);

	}

	public CreateStationThread_old(String stationName, String User_id) {
		CreateStationThread_old.stationName = stationName;
		CreateStationThread_old.m_Userid = User_id;
	}

	public void createStation(String user_id, String stationName) {
		Log.i(TAG, "createStation()");
        String ret=null;
		String url = HOST + PATH + "/createStation.php";
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("myId", user_id));
		params.add(new BasicNameValuePair("stationname", stationName));
        HttpResponse httpResponse;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            httpResponse = new DefaultHttpClient().execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                ret = EntityUtils.toString(httpResponse.getEntity());
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
