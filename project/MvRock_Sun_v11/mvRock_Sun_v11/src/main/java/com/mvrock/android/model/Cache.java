package com.mvrock.android.model;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

import com.examples.youtubeapidemo.R;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.jakewharton.disklrucache.DiskLruCache;
import com.mvrock.android.view.MvRockView;


import org.json.JSONException;

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
    private static final long timeDifference = 7 * 24 * 60 * 60 * 1000; //unit is ms
    private static final long testTimeDifference = 33 * 60 * 1000;
//    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public Cache() {
        Log.i(TAG, "Cache Initialization");
        try {
            File cacheDir = getDiskCacheDir(MvRockView.MainActivity, "bitmap");
            if (!cacheDir.exists())
                cacheDir.mkdirs();
            DiskLruCache = DiskLruCache.open(cacheDir, getAppVersion(MvRockView.MainActivity), 1, 10 * 1024 * 1024);
            Log.i(TAG, cacheDir.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getImageFromCache (List<Drawable> ImageView_List,
                                  List<Map<String, String>> song_info, String prefix, String postfix) {


        for (int i = 0; i < song_info.size(); i++) {
            String songUrl = song_info.get(i).get("url");
            String imageUrl = prefix + song_info.get(i).get("url") + postfix;
            String key = hashKeyForDisk(songUrl);
            Drawable drawable = null;
            try {
                DiskLruCache.Snapshot snapshot = Cache.DiskLruCache.get(key);
                if (snapshot != null && !imageNeedUpdate(key)) {
//                    Log.i(TAG + "Cache Time Cost Test", "ReadFrom Cache Time Begin " + sdf.format(new Date()));
                    drawable = getDrawable(snapshot);
                    ImageView_List.add(drawable);
//                    Log.i(TAG + "Cache Time Cost Test", "ReadFrom Cache Time End " + sdf.format(new Date()));
                } else {
//                    Log.i(TAG + "Cache Time Cost Test", "DownLoadFrom Internet Time Begin " + sdf.format(new Date()));
                    DiskLruCache.Editor editor = Cache.DiskLruCache.edit(key);

                    if (editor != null) {
                        OutputStream outputStream = editor.newOutputStream(0);
                        if (download(imageUrl, outputStream)) {
                            editor.commit();
                        } else {
                            editor.abort();
                        }
                    }
                    DiskLruCache.Snapshot snapshot1 = Cache.DiskLruCache.get(key);
                    drawable = getDrawable(snapshot1);
                    ImageView_List.add(drawable);

//                    Log.i(TAG + "Cache Time Cost Test", "DownLoadFrom Internet Time END " + sdf.format(new Date()));
                }
            } catch (IOException e) {
                Log.e(this.getClass().getSimpleName(), "Image download failed", e);
                drawable = MvRockView.MainActivity.getResources().getDrawable(R.drawable.image_fail);
                ImageView_List.add(drawable);
            }
        }
    }

//    public void getUserPicFromCache(List<Drawable> UserPic_List,
//                                    List<Map<String, String>> User_id_list) {
//
//
//        for (int i = 0; i < User_id_list.size(); i++) {
//            String User_id = User_id_list.get(i).get("uid");
//
//            String key = hashKeyForDisk(User_id);
//            Drawable drawable = null;
//            try {
//                DiskLruCache.Snapshot snapshot = Cache.DiskLruCache.get(key);
//                if (snapshot != null && !imageNeedUpdate(key)) {
////                    Log.i(TAG + "Cache Time Cost Test", "ReadFrom Cache Time Begin " + sdf.format(new Date()));
//                    drawable = getDrawable(snapshot);
//                    UserPic_List.add(drawable);
////                    Log.i(TAG + "Cache Time Cost Test", "ReadFrom Cache Time End " + sdf.format(new Date()));
//                } else {
////                    Log.i(TAG + "Cache Time Cost Test", "DownLoadFrom Internet Time Begin " + sdf.format(new Date()));
//                    DiskLruCache.Editor editor = Cache.DiskLruCache.edit(key);
//
//                    if (editor != null) {
//                        OutputStream outputStream = editor.newOutputStream(0);
//                        if (downloadUserPicFromFB(User_id, outputStream)) {
//                            editor.commit();
//
//                        } else {
//                            editor.abort();
//                        }
//                    }
//                    DiskLruCache.Snapshot snapshot1 = Cache.DiskLruCache.get(key);
//                    drawable = getDrawable(snapshot1);
//                    UserPic_List.add(drawable);
//
////                    Log.i(TAG + "Cache Time Cost Test", "DownLoadFrom Internet Time END " + sdf.format(new Date()));
//                }
//            } catch (IOException e) {
//                Log.e(this.getClass().getSimpleName(), "Image download failed", e);
//                drawable = MvRockView.MainActivity.getResources().getDrawable(R.drawable.image_fail);
//                UserPic_List.add(drawable);
//            }
//        }
//    }


    private boolean imageNeedUpdate(String key) {
        File file = new File(DiskLruCache.getDirectory(), key + ".0");
        long oldTime = file.lastModified();
        Date nowDate = new Date();
        long nowTime = nowDate.getTime();
        long difference = nowTime - oldTime;
        if (difference > testTimeDifference) {
            try {
                DiskLruCache.remove(key);
                Log.i(TAG + "remove Image", "This picture is expired");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else
            return false;
    }

    private Drawable getDrawable(DiskLruCache.Snapshot snapshot) {
        if (snapshot == null)
            return MvRockView.MainActivity.getResources().getDrawable(R.drawable.image_fail);

        InputStream is = snapshot.getInputStream(0);
        Drawable drawable = Drawable.createFromStream(is, "src");
        if (drawable != null) {
            return drawable;
        } else {
            drawable = MvRockView.MainActivity.getResources().getDrawable(R.drawable.image_fail);
            return drawable;
        }
    }

    private File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        Log.i(TAG, "Get Cache Path");
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }

        return new File(cachePath + File.separator + uniqueName);
    }


    private int getAppVersion(Context context) {
        Log.i(TAG, "Get App Version");
        int versionCode;
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            versionCode = 1;
            e.printStackTrace();
        }
        Log.i(TAG, versionCode + "");
        return versionCode;
    }

//    private InputStream download(String urlString)
//            throws  IOException {
//        InputStream inputStream = (InputStream) new URL(urlString).getContent();
//        return inputStream;
//    }

    private boolean download(String imageUrl, OutputStream outputStream)
            throws IOException {
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            final URL url = new URL(imageUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 50 * 1024);
            out = new BufferedOutputStream(outputStream, 50 * 1024);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (out != null)
                    out.close();
                if (in != null)
                    in.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

//    private boolean downloadUserPicFromFB(String User_id, OutputStream outputStream)
//            throws IOException {
//        HttpURLConnection urlConnection = null;
//        BufferedInputStream in = null;
//        BufferedOutputStream out = null;
//        String imageUrl = "";
//         /* make the API call */
//        GraphResponse response = new GraphRequest(
//                AccessToken.getCurrentAccessToken(),
//                "/" + User_id + "/picture",
//                null,
//                HttpMethod.GET,
//                new GraphRequest.Callback() {
//                    public String url = "";
//                    public void onCompleted(GraphResponse response) {
//                            /* handle the result */
//                    }
//                }
//        ).executeAndWait();
//
//        try {
//            imageUrl = response.getJSONObject().get("url").toString();
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            final URL url = new URL(imageUrl);
//            urlConnection = (HttpURLConnection) url.openConnection();
//            in = new BufferedInputStream(urlConnection.getInputStream(), 50 * 1024);
//            out = new BufferedOutputStream(outputStream, 50 * 1024);
//            int b;
//            while ((b = in.read()) != -1) {
//                out.write(b);
//            }
//            return true;
//        } catch (final IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (urlConnection != null) {
//                urlConnection.disconnect();
//            }
//            try {
//                if (out != null)
//                    out.close();
//                if (in != null)
//                    in.close();
//            } catch (final IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return false;
//    }

    private String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1)
                sb.append('0');
            sb.append(hex);
        }
        return sb.toString();
    }
}
