package com.mvrock.android.thread;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.examples.youtubeapidemo.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetStationImageListThread implements Runnable {
	private static final String TAG = "GetStationImageThread";
	@SuppressLint("UseSparseArrays")
	private static Map<Integer, Drawable> ImageView_List;
	private List<Map<String, String>> song_info;
	private Context context;

	@SuppressLint("UseSparseArrays")
	public GetStationImageListThread(List<Map<String, String>> song_info,
                                     Context context) {
		super();
		this.song_info = song_info;
		this.context = context;
		ImageView_List=  new HashMap<Integer, Drawable>();
	}

	public static Map<Integer, Drawable> getImageView_List() {
		return ImageView_List;
	}



	@SuppressLint("UseSparseArrays")
	@Override
	public void run() {
		Log.i(TAG,"run()");
		for(int i=0;i<song_info.size();i++)
		{
			String imageUrl = song_info.get(i).get("url");
			Drawable drawable = null;
			try {
				InputStream is = download(imageUrl);
				drawable = Drawable.createFromStream(is, "src");

				if (drawable != null) {
					ImageView_List.put(i, drawable);
				}
			} catch (Exception e) {
				Log.e(this.getClass().getSimpleName(),
						"Image download failed", e);
				// Show "download fail" image
				drawable=context.getResources().getDrawable(R.drawable.image_fail);
				ImageView_List.put(i, drawable);
			}

		}
	}


	/**
	 * Download image from given url. Make sure you have
	 * "android.permission.INTERNET" permission set in AndroidManifest.xml.
	 *
	 * @param urlString
	 * @return
	 * @throws java.net.MalformedURLException
	 * @throws java.io.IOException
	 */
	private InputStream download(String urlString)
			throws MalformedURLException, IOException {
		//Log.i(TAG,"download( "+urlString+" )");
		InputStream inputStream = (InputStream) new URL(urlString).getContent();
		return inputStream;
	}
	
}
