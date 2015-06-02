package com.mvrock.android.thread;

import android.util.Log;

import com.mvrock.android.model.MvRockModel;

import org.apache.http.message.BasicNameValuePair;

/**
 * Created by Xuer on 5/9/15.
 */
public class GetYoumaylikePlayListThread extends MvRockThreadObject {
    public GetYoumaylikePlayListThread(String User_id, String Extra){
        super(User_id,Extra);
        this.TAG+= "GetYoumaylikePlayList";
        this.Url="/getsong.php";
    }

    public void setParams() {
        Log.i(TAG, "setParams()");
        params.add(new BasicNameValuePair("myUid", this.User_id));

    }

    public void setResponse(){MvRockModel.YouMayLikeSongList.setHttpResponse(this.strResponse);}
}
