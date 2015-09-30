package com.mvrock.android.uicomponent.socialstuff;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ListView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.thread.GetBuddyFeedThread;
import com.mvrock.android.thread.GetUserProfilePicture;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Xuer on 8/11/15.
 */
public class BuddyFeedListView extends MvRockUiComponentObject {
    public ListView buddyFeedListView;

    public BuddyFeedListView() {
        this.TAG = "UIComponent.BFListView";
    }

    public void RefreshListView() {
        Log.i(TAG, "RefreshListView()");
        MvRockModel.BuddyFeed.userPic_List = RequestUserPicByThread();
        BuddyFeedListViewAdapter buddyFeedListViewAdapter = new BuddyFeedListViewAdapter(
                new String[] { "song_name","artist_name" },
                new int[] { R.id.song_name, R.id.artist_name });
        this.buddyFeedListView.setAdapter(buddyFeedListViewAdapter);

    }

    public ArrayList<Drawable> RequestUserPicByThread() {
        Log.i(TAG, "RequestUserPicByThread()");
        ArrayList<Drawable> buddyFeedPicture = new ArrayList<>();
        ArrayList<String> buddyFeedUserID = new ArrayList<>();
        for(Map<String, String> temp : MvRockModel.BuddyFeed.buddyFeedList){
            buddyFeedUserID.add(temp.get("uid"));
        }
        GetUserProfilePicture getUserProfilePicture = new GetUserProfilePicture(buddyFeedPicture, buddyFeedUserID);
        getUserProfilePicture.start();
        try{
            getUserProfilePicture.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        MvRockModel.BuddyFeed.userPic_List = buddyFeedPicture;
        return buddyFeedPicture;
    }

    public void RequestBuddyFeedByThread() {
        Log.i(TAG, "RequestBuddyFeedByThread()");
        GetBuddyFeedThread getBuddyFeedThread = new GetBuddyFeedThread(MvRockModel.User.User_Id, "");
        getBuddyFeedThread.start();

        try {
            getBuddyFeedThread.join();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        getBuddyFeedThread.setResponse();
        MvRockModel.BuddyFeed.convertData();
    }


    @Override
    public void Init() {
        Log.i(TAG, "Init()");
        this.RefreshListView();
    }
}
