package com.mvrock.android.uicomponent.socialstuff;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.view.MvRockView;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Xuer on 8/12/15.
 */
public class BuddyFeedListViewAdapter extends SimpleAdapter {
    protected String TAG;

    public BuddyFeedListViewAdapter( String[] from, int[] to) {
        super(MvRockView.MainActivity, MvRockModel.BuddyFeed.buddyFeedList, R.layout.fragment_left_drawer_buddy_feed_list, from, to);
        TAG = "UIComponent.BFListViewAdapter";
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getView(" + position + ")");

        convertView =  super.getView(position, convertView, parent);
        ((ImageView) convertView.findViewById(R.id.buddy_feed_user_image)).setBackgroundDrawable(MvRockModel.BuddyFeed.userPic_List.get(position));
        ((ImageView) convertView.findViewById(R.id.buddy_feed_shared_button)).setImageResource(R.drawable.share_red);
        TextView buddy_feed_user_name=(TextView)convertView.findViewById(R.id.buddy_feed_user_name);
        TextView buddy_feed_song_name=(TextView)convertView.findViewById(R.id.buddy_feed_song_name);
        String strUser_Name=MvRockModel.BuddyFeed.buddyFeedList.get(position).get("userName");
        String strSong_Name=MvRockModel.BuddyFeed.buddyFeedList.get(position).get("songName");
        SpannableString spannableUser_Name=new SpannableString(strUser_Name);

        spannableUser_Name.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Android_TextviewActivity.this, Activity1.class);
//                startActivity(intent);

            }
        }, 0, strUser_Name.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString spannableString2=new SpannableString(strSong_Name);
        spannableString2.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(Android_TextviewActivity.this,Activity2.class);
//                //startActivity(intent);

            }
        }, 0, strSong_Name.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        buddy_feed_user_name.setText(spannableUser_Name);
        buddy_feed_user_name.setMovementMethod(LinkMovementMethod.getInstance());
        buddy_feed_song_name.setText(spannableString2);
        buddy_feed_song_name.setMovementMethod(LinkMovementMethod.getInstance());

        return convertView;
    }
}
