package com.mvrock.android.uicomponent.drawer;

import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;
import com.mvrock.android.view.MvRockView;

/**
 * Created by Xuer on 6/2/15.
 */
public class MvRockDrawer extends MvRockUiComponentObject {
    public DrawerLayout mDrawerLayout;
    public ActionBarDrawerToggle mDrawerToggle;
    public FrameLayout frame, leftFragment, rightFragment;
    public float lastTranslate = 0.0f;

    public MvRockDrawer() {
    }

    @Override
    public void Init() {
        MvRockView.MainActivity.getSupportFragmentManager().beginTransaction().
                replace(R.id.right_drawer, MvRockView.YouMayLikePlayListFragment).commit();

        mDrawerToggle = new ActionBarDrawerToggle(MvRockView.MainActivity, mDrawerLayout, R.string.acc_drawer_open, R.string.acc_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                DrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (drawerView.getId() == R.id.right_drawer) {
                    MvRockUiComponent.RightFloatingMenu.actionMenu.open(true);
                } else {

                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (drawerView.getId() == R.id.right_drawer) {
                    MvRockUiComponent.RightFloatingMenu.actionMenu.close(true);
                } else {

                }
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    public void DrawerSlide(View drawerView, float slideOffset) {
        float moveFactor;
        if (drawerView.getId() == R.id.right_drawer) {
            slideOffset = -slideOffset;
            moveFactor = (MvRockUiComponent.MvRockDrawer.rightFragment.getWidth() * slideOffset);
        } else {
            moveFactor = (MvRockUiComponent.MvRockDrawer.leftFragment.getWidth() * slideOffset);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            MvRockUiComponent.MvRockDrawer.frame.setTranslationX(moveFactor);
            // MvRockUiComponent.MvRockDrawer.mDrawerLayout.setTranslationX(moveFactor);
        } else {
            TranslateAnimation anim1 = new TranslateAnimation(MvRockUiComponent.MvRockDrawer.lastTranslate, moveFactor, 0.0f, 0.0f);
            anim1.setDuration(0);
            anim1.setFillAfter(true);
            MvRockUiComponent.MvRockDrawer.frame.startAnimation(anim1);
            //new
//            TranslateAnimation anim2 = new TranslateAnimation(MvRockUiComponent.MvRockDrawer.lastTranslate, moveFactor, 0.0f, 0.0f);
//            anim2.setDuration(0);
//            anim2.setFillAfter(true);
//            MvRockUiComponent.MvRockDrawer.mDrawerLayout.startAnimation(anim2);
            //new end
            MvRockUiComponent.MvRockDrawer.lastTranslate = moveFactor;
        }
    }
}
