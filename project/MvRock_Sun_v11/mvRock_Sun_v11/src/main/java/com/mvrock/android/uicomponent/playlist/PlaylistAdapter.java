package com.mvrock.android.uicomponent.playlist;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import com.examples.youtubeapidemo.R;

import java.util.ArrayList;
import java.util.Map;

public abstract class PlaylistAdapter extends SimpleAdapter {
    protected String TAG;

    public PlaylistAdapter(Context context, ArrayList<Map<String, String>> song_info, String[] from, int[] to) {
        super(context, song_info, R.layout.play_list_adapter, from, to);
        this.TAG = "UIComponent.";
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getView(" + position + ")");

        return super.getView(position, convertView, parent);
    }
}