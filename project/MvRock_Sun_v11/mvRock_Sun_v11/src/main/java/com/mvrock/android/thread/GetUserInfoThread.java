package com.mvrock.android.thread;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.mvrock.android.model.MvRockModel;

import java.io.InputStream;
import java.net.URL;

public class GetUserInfoThread implements Runnable {
	private static final String TAG = "Thread.GetUserInfo";


	private void MakeMeRequest() {
	    // Make an API call to get user data and define a 
	    // new callback to handle the response.
		Log.i(TAG, "MakeMeRequest()"); 
	    Request request = Request.newMeRequest(MvRockModel.User.Session,
	            new Request.GraphUserCallback() {
	        @Override
	        public void onCompleted(GraphUser user, Response response) {
	            // If the response is successful
	        	Log.i(TAG, "MakeMeRequest() onCompleted()");
	        	if(MvRockModel.User.Session==Session.getActiveSession())
	        	{
		        	if (user != null) {
	                	//Log.i(TAG, "user="+user.toString());
                        MvRockModel.User.User_Id=user.getId();
                        MvRockModel.User.User_Name=user.getFirstName();
                        try {
                            URL imgUrl = new URL("https://graph.facebook.com/"
                                    + MvRockModel.User.User_Id + "/picture?type=large");

                            InputStream in = (InputStream) imgUrl.getContent();
                            MvRockModel.User.User_Profile_pic = Drawable.createFromStream(in, "src");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
	                                    
	                }
	        	}
	            if (response.getError() != null) {
	                Log.i(TAG, "request error:"+response.getError().toString());
	            }
	        }

		
	    });
	    request.executeAndWait();
	}
	
	@Override
	public void run() {	
	        MakeMeRequest();
	    }	
}