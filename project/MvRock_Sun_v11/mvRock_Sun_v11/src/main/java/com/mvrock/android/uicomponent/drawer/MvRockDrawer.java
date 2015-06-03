package com.mvrock.android.uicomponent.drawer;

import android.os.Build;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
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
                add(R.id.right_drawer, MvRockView.YouMayLikePlayListFragment).commit();
        MvRockUiComponent.MvRockDrawer.mDrawerToggle =
                new ActionBarDrawerToggle(MvRockView.MainActivity, MvRockUiComponent.MvRockDrawer.mDrawerLayout,
                        R.drawable.ic_launcher, R.string.acc_drawer_open, R.string.acc_drawer_close) {

                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        DrawerSlide(drawerView, slideOffset);
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        super.onDrawerOpened(drawerView);
                        MvRockUiComponent.RightFloatingMenu.actionMenu.open(true);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);
                        MvRockUiComponent.RightFloatingMenu.actionMenu.close(true);
                    }
                };
        MvRockUiComponent.MvRockDrawer.mDrawerLayout.setDrawerListener(MvRockUiComponent.MvRockDrawer.mDrawerToggle);
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
        } else {
            TranslateAnimation anim = new TranslateAnimation(MvRockUiComponent.MvRockDrawer.lastTranslate, moveFactor, 0.0f, 0.0f);
            anim.setDuration(0);
            anim.setFillAfter(true);
            MvRockUiComponent.MvRockDrawer.frame.startAnimation(anim);
            MvRockUiComponent.MvRockDrawer.lastTranslate = moveFactor;
        }
    }
}
