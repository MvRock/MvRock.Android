package com.example.tianhao.movementdemo;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by Tianhao on 15/5/31.
 */
public class MvRockFragment extends Fragment {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private LinearLayout linearLayout;
    private FrameLayout leftFragment, rightFragment;
    private float lastTranslate = 0.0f;

    private Button backButton;
    public MvRockFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mvrock, container, false);
        backButton = (Button)root.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        mDrawerLayout = (DrawerLayout) root.findViewById(R.id.drawer_layout);
        leftFragment = (FrameLayout) root.findViewById(R.id.left_drawer);
        rightFragment = (FrameLayout) root.findViewById(R.id.right_drawer);
        linearLayout = (LinearLayout) root.findViewById(R.id.mvrock_linear);
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, R.drawable.ic_launcher, R.string.acc_drawer_open, R.string.acc_drawer_close) {

            public void onDrawerSlide(View drawerView, float slideOffset) {
                float moveFactor;
                if (drawerView.getId() == R.id.right_drawer) {
                    slideOffset = -slideOffset;
                    moveFactor = (rightFragment.getWidth() * slideOffset);
                } else {
                    moveFactor = (leftFragment.getWidth() * slideOffset);
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    linearLayout.setTranslationX(moveFactor);
                } else {
                    TranslateAnimation anim = new TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f);
                    anim.setDuration(0);
                    anim.setFillAfter(true);
                    linearLayout.startAnimation(anim);
                    lastTranslate = moveFactor;
                }
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        return root;
    }

}