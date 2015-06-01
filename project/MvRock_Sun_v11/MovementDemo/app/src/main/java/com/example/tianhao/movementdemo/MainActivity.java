package com.example.tianhao.movementdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Tianhao on 15/5/30.
 */
public class MainActivity extends FragmentActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().
                    add(R.id.content_frame, new FBFragment()).commit();
        }
    }
}
