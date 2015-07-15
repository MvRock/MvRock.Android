package com.mvrock.android.uicomponent.playlist;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.SparseArray;
import android.widget.ListView;

import com.mvrock.android.thread.GetImageListThread;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;
import com.mvrock.android.view.MvRockView;

import java.util.List;
import java.util.Map;

/**
 * Created by Xuer on 5/5/15.
 */
public abstract class PlayListView extends MvRockUiComponentObject {

    public ListView playListview;
    protected Context context;
    public PlayListView(Context context){
        this.TAG="UIComponent.";
        this.context=context;
        this.RequestPlayListByThread();
    }
    public abstract boolean isAvailable();
    public abstract void setAvailable();
    public abstract void RequestPlayListByThread();
//    public abstract void RequestPlayListDataAtBeginning();
    public abstract void RefreshListView();
    public abstract void Init();

    public SparseArray<Drawable> RequestImageListByThread(List<Map<String, String>> song_info){
        Log.i(TAG, "RequestImageListByThread()");
        Thread getImageListThread=  new Thread(new GetImageListThread(song_info, MvRockView.MainActivity));
        getImageListThread.start();
        try {
            getImageListThread.join();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        return GetImageListThread.getImageView_List();
    }
}
