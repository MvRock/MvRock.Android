package com.mvrock.android.thread;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.SparseArray;
import com.mvrock.android.model.MvRockModel;
import java.util.List;
import java.util.Map;

public class GetStationImageListThread implements Runnable {
	private static final String TAG = "GetStationImageThread";
    private static SparseArray<Drawable> ImageView_List;
	private List<Map<String, String>> station_url;

	public GetStationImageListThread(List<Map<String, String>> station_url) {
		super();
		this.station_url = station_url;
		ImageView_List=  new SparseArray<Drawable>();
	}

	public static SparseArray<Drawable> getImageView_List() {
		return ImageView_List;
	}

	@Override
	public void run() {
		Log.i(TAG,"run()");
		MvRockModel.cache.getImageFromCache(ImageView_List, station_url, "", "");
	}

}
