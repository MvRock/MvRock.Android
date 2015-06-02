package com.mvrock.android.uicomponent;

/**
 * Created by Xuer on 5/9/15.
 * Add comment on 5/26/15
 *
 * Abstract Class
 * Cluster Classes is used for defining UI events.
 *
 */
public abstract class MvRockUiComponentObject {
    protected String TAG;

    public abstract void Init();
    public MvRockUiComponentObject(){
        TAG="UiComponent.";
    }
}
