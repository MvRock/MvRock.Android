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

public class SetRatingThread_old implements Runnable {
	private static final String TAG = "SetRatingThread";
	public static final String HOST = "http://wanlab.poly.edu";
	public static final String PATH = "/xing/tubeok";
	private String m_Userid;
	private String url;
	private int rating;
	
	public SetRatingThread_old(
            String m_Userid, String url, int rating) {
		super();
		this.url = url;
		this.m_Userid = m_Userid;
		this.rating = rating;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Log.i(TAG, "run()");
		setRating(m_Userid,url,rating);
	}

	
	public void setRating(String user_id, String song_url, int rating) {
		Log.i(TAG, "setRating("+rating+")");
        String ret = null;
		String url = HOST + PATH + "/setrating.php";
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userid", user_id));
		params.add(new BasicNameValuePair("url", song_url));
		params.add(new BasicNameValuePair("rating", String.valueOf(rating)));
		params.add(new BasicNameValuePair("songsource", "1"));
		params.add(new BasicNameValuePair("songfromid", "0"));
        HttpResponse httpResponse = null;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            httpResponse = new DefaultHttpClient().execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                ret = EntityUtils.toString(httpResponse.getEntity());

            }
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println(ret);
	}

}
