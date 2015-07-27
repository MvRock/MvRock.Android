package com.mvrock.android.thread;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.mvrock.android.model.MvRockModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetStationImageListThread extends Thread {
	private static final String TAG = "GetStationImageThread";
    private ArrayList<Drawable> ImageView_List;
	private List<Map<String, String>> station_url;

	public GetStationImageListThread(List<Map<String, String>> station_url) {
		super();
		this.station_url = station_url;
		ImageView_List = new ArrayList<Drawable>();
	}

	public ArrayList<Drawable> getImageView_List() {
		return ImageView_List;
	}

	@Override
	public void run() {
		Log.i(TAG,"run()");
		MvRockModel.cache.getImageFromCache(ImageView_List, station_url, "", "");
	}

}
