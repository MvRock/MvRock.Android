package com.mvrock.android.model;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.util.SparseArray;

import com.examples.youtubeapidemo.R;
import com.jakewharton.disklrucache.DiskLruCache;
import com.mvrock.android.view.MvRockView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Tianhao on 15/7/20.
 */
public class Cache {
    public static DiskLruCache DiskLruCache = null;
    String TAG = "Cache";
    public Cache(){
        Log.i(TAG, "Cache Initialization");
        try{
            File cacheDir = getDiskCacheDir(MvRockView.MainActivity, "bitmap");
            if(!cacheDir.exists())
                cacheDir.mkdirs();
            DiskLruCache = DiskLruCache.open(cacheDir, getAppVersion(MvRockView.MainActivity), 1, 10 * 1024 * 1024);
            Log.i(TAG,cacheDir.toString());
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void getImageFromCache(SparseArray<Drawable> ImageView_List,
                                  List<Map<String, String>> song_info, String prefix, String postfix){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-ss-SSS");

        for(int i = 0 ; i < song_info.size();i++){
            String songUrl = song_info.get(i).get("url");
            String imageUrl = prefix + song_info.get(i).get("url")+ postfix;
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
                        InputStream inputStream = null;

                        if(download(imageUrl, outputStream, inputStream)){
                            editor.commit();
                            drawable = Drawable.createFromStream(inputStream, "src");
                            if(drawable != null)
                                ImageView_List.put(i, drawable);
                        }else {
                            editor.abort();
                        }
                    }

                    Log.i(TAG,"DownLoadFrom Internet Time END " + sdf.format(new Date()));
                }
            }catch(IOException e){
                Log.e(this.getClass().getSimpleName(), "Image download failed", e);
                drawable = MvRockView.MainActivity.getResources().getDrawable(R.drawable.image_fail);
                ImageView_List.put(i, drawable);
            }
        }
    }

    private File getDiskCacheDir(Context context, String uniqueName){
        String cachePath;
        Log.i(TAG, "Get Cache Path");
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()){
            cachePath = context.getExternalCacheDir().getPath();
        }else{
            cachePath = context.getCacheDir().getPath();
        }

        return new File(cachePath + File.separator + uniqueName);
    }

    private int getAppVersion(Context context){
        Log.i(TAG, "Get App Version");
        int versionCode;
        try{
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionCode = info.versionCode;
        }catch(PackageManager.NameNotFoundException e) {
            versionCode = 1;
            e.printStackTrace();
        }
        Log.i(TAG,versionCode + "");
        return versionCode;
    }

//    private InputStream download(String urlString)
//            throws  IOException {
//        InputStream inputStream = (InputStream) new URL(urlString).getContent();
//        return inputStream;
//    }

    private boolean download(String imageUrl, OutputStream outputStream, InputStream inputStream)
            throws IOException{
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try{
            final URL url = new URL(imageUrl);
            urlConnection = (HttpURLConnection)url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            inputStream = in;
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
