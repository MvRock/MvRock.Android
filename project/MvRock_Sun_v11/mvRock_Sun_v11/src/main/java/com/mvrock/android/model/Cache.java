package com.mvrock.android.model;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import com.jakewharton.disklrucache.DiskLruCache;
import com.mvrock.android.view.MvRockView;

import java.io.File;
import java.io.IOException;

/**
 * Created by Tianhao on 15/7/20.
 */
public class Cache {
    DiskLruCache mDiskLruCache = null;
    String TAG = "Cache";
    public Cache(){
        Log.i(TAG, "Cache Initialization");
        try{
            File cacheDir = getDiskCacheDir(MvRockView.MainActivity, "bitmap");
            if(!cacheDir.exists())
                cacheDir.mkdirs();
            mDiskLruCache = DiskLruCache.open(cacheDir, getAppVersion(MvRockView.MainActivity), 1, 10 * 1024 * 1024);
            Log.i(TAG,cacheDir.toString());
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public File getDiskCacheDir(Context context, String uniqueName){
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

    public int getAppVersion(Context context){
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
}
