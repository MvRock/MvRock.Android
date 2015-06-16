package com.mvrock.android.uicomponent.station;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.thread.CreateStationThread;

import java.util.ArrayList;
import java.util.Map;

import static android.util.Log.i;

/**
 * Created by Xuer on 6/11/15.
 */
public class SearchStationListAdapter extends SimpleAdapter {
    protected String TAG;
    protected ViewHolder holder= new ViewHolder();
    public Context context;
    @SuppressLint("UseSparseArrays")
    public SearchStationListAdapter( Context context, ArrayList<Map<String, String>> station_info,
                               String[] from, int[] to) {
        super(context,station_info , R.layout.search_station_list_adapter, from, to);
        this.TAG="UIComponent.";
        TAG+="SearchStationListAdapter";
        this.context = context;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getView(" + position + ")");
        holder= new ViewHolder();
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.search_station_list_adapter, parent,false);
        convertView = super.getView(position, convertView, parent);
        holder = new ViewHolder();
        holder.stationname_textview = (TextView) convertView.findViewById(R.id.station_name);
        holder.SubscribeOrUnsubscribe=(Button) convertView.findViewById(R.id.sub_or_unsub);
        if(MvRockModel.SearchStationList.subscribeList[position]==0){
            holder.SubscribeOrUnsubscribe.setText("Subscribe");
            holder.SubscribeOrUnsubscribe.setBackgroundColor(Color.GREEN);
        }else{
            holder.SubscribeOrUnsubscribe.setText("Unsubscribe");
            holder.SubscribeOrUnsubscribe.setBackgroundColor(Color.RED);
        }
        holder.SubscribeOrUnsubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG,Integer.toString(position));
                if(MvRockModel.SearchStationList.subscribeList[position]==0){
                    CreateStationByThread(holder.stationname_textview.getText().toString());
                    holder.SubscribeOrUnsubscribe.setText("Unsubscribe");
                    holder.SubscribeOrUnsubscribe.setBackgroundColor(Color.RED);
                }else{
                    holder.SubscribeOrUnsubscribe.setText("Subscribe");
                    holder.SubscribeOrUnsubscribe.setBackgroundColor(Color.GREEN);
                }
                MvRockModel.SearchStationList.subscribeList[position]=1-MvRockModel.SearchStationList.subscribeList[position];
            }
        });
        convertView.setTag(holder);
        return convertView;
    }

    public void CreateStationByThread(String stationName){
        i(TAG, "CreateStationByThread(" + stationName + ")");
        Thread createStationByThread = new Thread(new CreateStationThread(stationName, MvRockModel.User.User_Id));
        createStationByThread.start();
        try {
            createStationByThread.join();
        } catch (InterruptedException e1) {

            e1.printStackTrace();
        }
    }

    protected static class ViewHolder {
        public TextView stationname_textview;
        public Button SubscribeOrUnsubscribe;
    }




}
