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
    public ImageView RightDrawerControlButton;
    private ImageView YouMayLikePlayListButton;
    private ImageView YouLikedPlayListButton;
    private ImageView StationPlayListButton;

    public RightFloatingMenu() {
        RightDrawerControlButton = new ImageView(MvRockView.MainActivity);
    }

    @Override
    public void Init() {
        //1 - Create a button to attach the menu
        // in Activity Context
        RightDrawerControlButton.setImageDrawable(MvRockView.MainActivity.
                getResources().getDrawable(R.drawable.mvrock_phone_1_icon));

        actionButton = new FloatingActionButton.Builder(MvRockView.MainActivity)
                .setContentView(RightDrawerControlButton).build();

        //2 - Create menu items:

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(MvRockView.MainActivity);
        // repeat many times:
        YouMayLikePlayListButton = new ImageView(MvRockView.MainActivity);
        YouMayLikePlayListButton.setImageDrawable(MvRockView.MainActivity.getResources().getDrawable(R.drawable.mvrock_youmaylike_red));
        SubActionButton button1 = itemBuilder.setContentView(YouMayLikePlayListButton).build();


        YouLikedPlayListButton = new ImageView(MvRockView.MainActivity);
        YouLikedPlayListButton.setImageDrawable(MvRockView.MainActivity.getResources().getDrawable(R.drawable.mvrock_liked_grey));
        SubActionButton button2 = itemBuilder.setContentView(YouLikedPlayListButton).build();


        StationPlayListButton = new ImageView(MvRockView.MainActivity);
        StationPlayListButton.setImageDrawable(MvRockView.MainActivity.getResources().getDrawable(R.drawable.mvrock_station_grey));
        SubActionButton button3 = itemBuilder.setContentView(StationPlayListButton).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(MvRockView.MainActivity)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .attachTo(actionButton)
                .build();

        actionMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener(){
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
                YouMayLikePlayListButton.setImageDrawable(MvRockView.MainActivity.getResources().getDrawable(R.drawable.mvrock_youmaylike_red));
                YouLikedPlayListButton.setImageDrawable(MvRockView.MainActivity.getResources().getDrawable(R.drawable.mvrock_liked_grey));
                StationPlayListButton.setImageDrawable(MvRockView.MainActivity.getResources().getDrawable(R.drawable.mvrock_station_grey));
                MvRockView.MainActivity.getSupportFragmentManager().beginTransaction().
                        replace(R.id.right_drawer, MvRockView.YouMayLikePlayListFragment).commit();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YouMayLikePlayListButton.setImageDrawable(MvRockView.MainActivity.getResources().getDrawable(R.drawable.mvrock_youmaylike_grey));
                YouLikedPlayListButton.setImageDrawable(MvRockView.MainActivity.getResources().getDrawable(R.drawable.mvrock_liked_red));
                StationPlayListButton.setImageDrawable(MvRockView.MainActivity.getResources().getDrawable(R.drawable.mvrock_station_grey));
                MvRockView.MainActivity.getSupportFragmentManager().beginTransaction().
                        replace(R.id.right_drawer, MvRockView.YouLikedPlayListFragment).commit();
            }
        });
        //3 - Create the menu with the items:


//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                YouMayLikePlayListButton.setImageDrawable(MvRockView.MainActivity.getResources().getDrawable(R.drawable.mvrock_youmaylike_grey));
//                YouLikedPlayListButton.setImageDrawable(MvRockView.MainActivity.getResources().getDrawable(R.drawable.mvrock_liked_grey));
//                StationPlayListButton.setImageDrawable(MvRockView.MainActivity.getResources().getDrawable(R.drawable.mvrock_station_red));
//                MvRockView.MainActivity.getSupportFragmentManager().beginTransaction().
//                        add(R.id.right_drawer,MvRockView.StationPlayListFragment).commit();
//            }
//        });

    }
}
