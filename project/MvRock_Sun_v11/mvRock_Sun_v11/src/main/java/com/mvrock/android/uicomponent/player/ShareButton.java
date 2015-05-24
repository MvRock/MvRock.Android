package com.mvrock.android.uicomponent.player;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.thread.SetShareThread_old;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;

/**
 * Created by Xuer on 5/5/15.
 */
public class ShareButton extends MvRockUiComponentObject {
    public ImageView shareSongImage;
    public ShareButton(){TAG="ShareButton";}
    public void Init(){
        Log.i(TAG,"Init()");
        shareSongImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("shareSongImage", "onClick()");
                //share to facebook
                PostShareByThread();
            }
        });
    }

    public void PostShareByThread(){
        Log.i(TAG, "PostShareByThread()");
        String url;
        if ( MvRockUiComponent.YouMayLikePlayListView.isAvailable()) {
            url=  MvRockModel.YouMayLikeSongList.songArrayList.get(MvRockModel.currentMVIndex).get("url");
        } else {
            url= MvRockModel.YouLikedSongList.songArrayList.get(MvRockModel.currentMVIndex).get("url");
        }
        Thread setShareThread = new Thread(new SetShareThread_old(MvRockModel.User.User_Id,url));
        setShareThread.start();
    }

}
