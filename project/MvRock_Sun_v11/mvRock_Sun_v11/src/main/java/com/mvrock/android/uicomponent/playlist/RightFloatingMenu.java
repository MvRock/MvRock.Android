package com.mvrock.android.uicomponent.playlist;

import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;
import com.mvrock.android.view.MvRockView;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

/**
 * Created by Xuer on 6/2/15.
 */
public class RightFloatingMenu extends MvRockUiComponentObject {
    private FloatingActionButton actionButton;
    private ImageView youMayLikePlayListButton;
    private ImageView youLikedPlayListButton;
    private ImageView stationPlayListButton;
    public FloatingActionMenu actionMenu;

    @Override
    public void Init() {
        //1 - Create a button to attach the menu
        // in Activity Context
        actionButton = new FloatingActionButton.Builder(MvRockView.MainActivity)
                .setBackgroundDrawable(R.drawable.mvrock_phone_1_icon)
                .build();

        //2 - Create menu items:
        // repeat many times:
        youMayLikePlayListButton = new ImageView(MvRockView.MainActivity);
        youMayLikePlayListButton.setImageResource(R.drawable.mvrock_youmaylike_red);
        SubActionButton button1 = new SubActionButton.Builder(MvRockView.MainActivity).setContentView(youMayLikePlayListButton).build();

        youLikedPlayListButton = new ImageView(MvRockView.MainActivity);
        youLikedPlayListButton.setImageResource(R.drawable.mvrock_liked_grey);
        SubActionButton button2 = new SubActionButton.Builder(MvRockView.MainActivity).setContentView(youLikedPlayListButton).build();


        stationPlayListButton = new ImageView(MvRockView.MainActivity);
        stationPlayListButton.setImageResource(R.drawable.mvrock_station_grey);
        SubActionButton button3 = new SubActionButton.Builder(MvRockView.MainActivity).setContentView(stationPlayListButton).build();

        actionMenu = new FloatingActionMenu.Builder(MvRockView.MainActivity)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .attachTo(actionButton)
                .build();

        actionMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu floatingActionMenu) {
                MvRockUiComponent.MvRockDrawer.mDrawerLayout.openDrawer(Gravity.RIGHT);
            }

            @Override
            public void onMenuClosed(FloatingActionMenu floatingActionMenu) {
                MvRockUiComponent.MvRockDrawer.mDrawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youMayLikePlayListButton.setImageResource(R.drawable.mvrock_youmaylike_red);
                youLikedPlayListButton.setImageResource(R.drawable.mvrock_liked_grey);
                stationPlayListButton.setImageResource(R.drawable.mvrock_station_grey);
                MvRockView.MainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.right_drawer, MvRockView.YouMayLikePlayListFragment).commit();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youMayLikePlayListButton.setImageResource(R.drawable.mvrock_youmaylike_grey);
                youLikedPlayListButton.setImageResource(R.drawable.mvrock_liked_red);
                stationPlayListButton.setImageResource(R.drawable.mvrock_station_grey);
                MvRockView.MainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.right_drawer, MvRockView.YouLikedPlayListFragment).commit();
            }
        });
        //3 - Create the menu with the items:

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youMayLikePlayListButton.setImageResource(R.drawable.mvrock_youmaylike_grey);
                youLikedPlayListButton.setImageResource(R.drawable.mvrock_liked_grey);
                stationPlayListButton.setImageResource(R.drawable.mvrock_station_red);
                if (MvRockUiComponent.StationPlayListView.isAvailable()) {
                    MvRockView.MainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.right_drawer, MvRockView.StationPlayListFragment).commit();
                } else {
                    MvRockView.MainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.right_drawer, MvRockView.StationListFragment).commit();
                }
            }
        });
    }
}
