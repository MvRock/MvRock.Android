package com.mvrock.android.thread;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xuer on 5/5/15.
 */
public abstract class MvRockThreadObject extends Thread{
    public static final String HOST = "http://wanlab.poly.edu";
    public static final String PATH = "/xing/tubeok";
    protected String TAG ;
    protected List<NameValuePair> params;
    protected String User_id;
    protected String Extra;
    protected String Url;
    protected String strResponse;


    public MvRockThreadObject(String User_id, String Extra) {
        this.TAG="Thread.";
        this.User_id = User_id;
        this.Extra = Extra;
        this.params=new ArrayList<NameValuePair>();
        this.strResponse = "";
    }

    @Override
    public void run() {
        Log.i(TAG, "run()");
        this.Url = HOST + PATH + this.Url;
        HttpPost httpPost = new HttpPost(this.Url);

        setParams();

        HttpResponse httpResponse = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            httpResponse = new DefaultHttpClient().execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                strResponse = EntityUtils.toString(httpResponse.getEntity());
               // Log.i(TAG, strResponse);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void setParams();

    public abstract void setResponse();
}
