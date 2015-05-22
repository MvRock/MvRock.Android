package com.mvrock.android.uicomponent.playlist;

import android.content.Context;
import android.widget.ListView;

import com.mvrock.android.uicomponent.MvRockUiComponentObject;

/**
 * Created by Xuer on 5/5/15.
 */
public abstract class PlayListView extends MvRockUiComponentObject {

    public ListView playListview;
    protected Context context;
    public PlayListView(Context context){
        this.TAG="UIComponent.";
        this.context=context;
    }
    public abstract boolean isAvailable();
    public abstract void setAvailable();
    public abstract void RequestPlayListByThread();
    public abstract void RefreshListView();
    public abstract void Init();
}
