package com.mvrock.android.thread;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.mvrock.android.model.MvRockModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class GetImageListThread implements Runnable {
	private static final String TAG = "GetImageListThread";
    private static ArrayList<Drawable> ImageView_List;
	private List<Map<String, String>> song_info;

	public GetImageListThread(List<Map<String, String>> song_info) {
		super();
		this.song_info = song_info;
        ImageView_List=  new ArrayList<Drawable>();
	}
	
	public static ArrayList<Drawable> getImageView_List() {
		return ImageView_List;
	}
	@Override
	public void run(){
		Log.i(TAG, "run()");
		MvRockModel.cache.getImageFromCache(ImageView_List, song_info, "http://img.youtube.com/vi/", "/0.jpg");
	}

}