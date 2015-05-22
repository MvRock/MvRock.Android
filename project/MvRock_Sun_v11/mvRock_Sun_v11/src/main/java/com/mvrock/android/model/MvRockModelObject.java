package com.mvrock.android.model;

import android.util.Log;

import org.json.JSONException;

/**
 * Created by Xuer on 5/9/15.
 */
public abstract class MvRockModelObject {
    protected String TAG;
    protected String strResponse;

    protected MvRockModelObject() {
        TAG="Model.";
        strResponse ="";
    }

    /**
     * Setter for strResponse. Thread call this function to pass the HttpResponse.
     * @param strResponse get this data from Thread
     */
    public void setHttpResponse(String strResponse) {
        Log.i(TAG, "setHttpResponse()");
        this.strResponse =strResponse;
    }

    /**
     * analyzing the data String strResponse,
     * convert them in new Data Type which can be used in the specific view.
     */
    public abstract void convertData();
}
