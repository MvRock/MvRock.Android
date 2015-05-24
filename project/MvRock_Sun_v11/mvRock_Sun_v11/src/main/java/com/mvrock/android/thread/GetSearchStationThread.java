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

public class GetSearchStationThread implements Runnable {
	private static final String TAG = "Thread.GetRecStation";
	public static final String HOST = "http://wanlab.poly.edu";
	public static final String PATH = "/xing/tubeok";
	private String m_Userid;
	private String searchStr;
	private static String[] arrayRecStations;

	public static String[] getArrayRecStations() {
		return arrayRecStations;
	}

	public GetSearchStationThread(String m_Userid, String searchStr) {
		super();
        Log.i(TAG, "GetSearchStationThread("+m_Userid+", "+searchStr+")");

        this.m_Userid = m_Userid;
		this.searchStr = searchStr;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Log.i(TAG, "run()");
		arrayRecStations=null;
		JSONArray recStationJA = getSearchStation(m_Userid, searchStr);
		if (recStationJA != null) {
			arrayRecStations = new String[recStationJA.length()];
			for (int i = 0; i < arrayRecStations.length; i++) {
				try {
					arrayRecStations[i] = recStationJA.getJSONObject(i)
							.get("station").toString();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			arrayRecStations = new String[1];
			arrayRecStations[0] = "Sorry, no artist found.";
		}
	}
	
	public JSONArray getSearchStation(String user_id, String searchStr) {
		Log.i(TAG, "getSearchStation()");
		String ret = "";
		String url = HOST + PATH + "/getRecStation.php";
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("myId", user_id));
		params.add(new BasicNameValuePair("searchStr", searchStr));

		HttpResponse httpResponse = null;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			httpResponse = new DefaultHttpClient().execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				ret = EntityUtils.toString(httpResponse.getEntity());
			}
            Log.i(TAG,httpResponse.getStatusLine().getStatusCode()+", "+ret);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONArray recStations = null;
		try {
			recStations = new JSONArray(ret);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return recStations;
	}
}
