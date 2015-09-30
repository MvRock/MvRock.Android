package com.mvrock.android.uicomponent.socialstuff;

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
 * Created by Xuer on 8/11/15.
 */
public class LeftFloatingMenu  extends MvRockUiComponentObject {
    private FloatingActionButton actionButton;
    private ImageView buddyFeedButton;
    private ImageView buddyListButton;
    public FloatingActionMenu actionMenu;

    @Override
    public void Init() {
        //1 - Create a button to attach the menu
        // in Activity Context
        actionButton = new FloatingActionButton.Builder(MvRockView.MainActivity)
                .setBackgroundDrawable(R.drawable.mvrock_phone_2_icon)
                .setPosition(FloatingActionButton.POSITION_BOTTOM_LEFT)
                .build();

        //2 - Create menu items:
        // repeat many times:
        buddyFeedButton = new ImageView(MvRockView.MainActivity);
        buddyFeedButton.setImageResource(R.drawable.mvrock_feeds_red);
        SubActionButton button1 = new SubActionButton.Builder(MvRockView.MainActivity).setContentView(buddyFeedButton).build();

        buddyListButton = new ImageView(MvRockView.MainActivity);
        buddyListButton.setImageResource(R.drawable.mvrock_profile_grey);
        SubActionButton button2 = new SubActionButton.Builder(MvRockView.MainActivity).setContentView(buddyListButton).build();



        actionMenu = new FloatingActionMenu.Builder(MvRockView.MainActivity)
                .setStartAngle(-60)
                .setEndAngle(-30)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .attachTo(actionButton)
                .build();

        actionMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu floatingActionMenu) {
                MvRockUiComponent.MvRockDrawer.mDrawerLayout.openDrawer(Gravity.LEFT);
            }

            @Override
            public void onMenuClosed(FloatingActionMenu floatingActionMenu) {
                MvRockUiComponent.MvRockDrawer.mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buddyFeedButton.setImageResource(R.drawable.mvrock_feeds_red);
                buddyListButton.setImageResource(R.drawable.mvrock_profile_grey);
                MvRockView.MainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.left_drawer, MvRockView.BuddyFeedFragment).commit();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buddyFeedButton.setImageResource(R.drawable.mvrock_feeds_grey);
                buddyListButton.setImageResource(R.drawable.mvrock_profile_red);
                //MvRockView.MainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.right_drawer, MvRockView.BuddyListFragment).commit();
            }
        });

    }
}
