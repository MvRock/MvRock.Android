package com.mvrock.android.thread;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.util.SparseArray;

import com.examples.youtubeapidemo.R;
import com.jakewharton.disklrucache.DiskLruCache;
import com.mvrock.android.model.Cache;
import com.mvrock.android.model.MvRockModel;

import org.apache.http.entity.ByteArrayEntity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.security.auth.callback.CallbackHandler;

public class GetImageListThread implements Runnable {
	private static final String TAG = "GetImageListThread";
    private static SparseArray<Drawable> ImageView_List;
	private List<Map<String, String>> song_info;
	private Context context;

	public GetImageListThread(List<Map<String, String>> song_info,
                              Context context) {
		super();
		this.song_info = song_info;
		this.context = context;
        ImageView_List=  new SparseArray<Drawable>();
	}
	
	public static SparseArray<Drawable> getImageView_List() {
		return ImageView_List;
	}

	
	

	@Override
//	public void run() {
//		Log.i(TAG,"run()");
//		for(int i=0;i<song_info.size();i++) {
//			String imageUrl = "http://img.youtube.com/vi/" + song_info.get(i).get("url")+ "/0.jpg";
//			Drawable drawable = null;
//			try {
//				InputStream is = download(imageUrl);
//				drawable = Drawable.createFromStream(is, "src");
//
//				if (drawable != null) {
//					ImageView_List.put(i, drawable);
//				}
//			} catch (Exception e) {
//				Log.e(this.getClass().getSimpleName(),
//						"Image download failed", e);
//				// Show "download fail" image
//				drawable=context.getResources().getDrawable(R.drawable.image_fail);
//				ImageView_List.put(i, drawable);
//			}
//
//		}
//	}
	public void run(){
		Log.i(TAG, "run()");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-ss-SSS");

		for(int i = 0 ; i < song_info.size();i++){
			String songUrl = song_info.get(i).get("url");
			String imageUrl = "http://img.youtube.com/vi/" + song_info.get(i).get("url")+ "/0.jpg";
			String key = hashKeyForDisk(songUrl);
			Drawable drawable = null;
			try{
				DiskLruCache.Snapshot snapshot = Cache.DiskLruCache.get(key);
				if(snapshot != null){
					Log.i(TAG, "ReadFrom Cache Time Begin " + sdf.format(new Date()));
					InputStream is = snapshot.getInputStream(0);
					drawable = Drawable.createFromStream(is, "src");
					if(drawable != null)
						ImageView_List.put(i,drawable);
					Log.i(TAG, "ReadFrom Cache Time End " + sdf.format(new Date()));

				}else{
					Log.i(TAG,"DownLoadFrom Internet Time Begin " + sdf.format(new Date()));
					DiskLruCache.Editor editor = Cache.DiskLruCache.edit(key);
					if(editor != null){
						OutputStream outputStream = editor.newOutputStream(0);
						if(download(imageUrl, outputStream)){
							editor.commit();
						}else
							editor.abort();
					}

					InputStream is = download(imageUrl);
					drawable = Drawable.createFromStream(is, "src");
					if(drawable != null)
						ImageView_List.put(i, drawable);
					Log.i(TAG,"DownLoadFrom Internet Time END " + sdf.format(new Date()));

				}
			}catch(IOException e){
				Log.e(this.getClass().getSimpleName(), "Image download failed", e);
				drawable = context.getResources().getDrawable(R.drawable.image_fail);
				ImageView_List.put(i, drawable);
			}
		}
	}
	
	
	/**
	 * Download image from given url. Make sure you have
	 * "android.permission.INTERNET" permission set in AndroidManifest.xml.
	 * 
	 * @param urlString
	 * @throws IOException
	 */
	private InputStream download(String urlString)
			throws  IOException {
		InputStream inputStream = (InputStream) new URL(urlString).getContent();
		return inputStream;
	}

	private boolean download(String imageUrl, OutputStream outputStream)
			throws IOException{
		HttpURLConnection urlConnection = null;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try{
			final URL url = new URL(imageUrl);
			urlConnection = (HttpURLConnection)url.openConnection();
			in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
			out = new BufferedOutputStream(outputStream, 8 * 1024);
			int b;
			while((b = in.read()) != -1){
				out.write(b);
			}
			return true;
		}catch (final IOException e){
			e.printStackTrace();
		}finally {
			if(urlConnection != null){
				urlConnection.disconnect();
			}
			try{
				if(out != null)
					out.close();
				if(in != null)
					in.close();
			}catch (final IOException e){
				e.printStackTrace();
			}
		}
		return false;
	}

	private String hashKeyForDisk(String key){
		String cacheKey;
		try{
			final MessageDigest mDigest = MessageDigest.getInstance("MD5");
			mDigest.update(key.getBytes());
			cacheKey = bytesToHexString(mDigest.digest());
		}catch(NoSuchAlgorithmException e){
			cacheKey = String.valueOf(key.hashCode());
		}
		return cacheKey;
	}

	private String bytesToHexString(byte[] bytes){
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ;  i < bytes.length ; i++){
			String hex = Integer.toHexString(0xFF & bytes[i]);
			if(hex.length() == 1)
				sb.append('0');
			sb.append(hex);
		}
		return sb.toString();
	}
	
}