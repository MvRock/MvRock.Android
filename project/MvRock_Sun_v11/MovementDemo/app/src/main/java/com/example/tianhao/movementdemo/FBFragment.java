package com.example.tianhao.movementdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Tianhao on 15/5/31.
 */
public class FBFragment extends Fragment {
    Button moveOnButton;

    public FBFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fblogin, container, false);
        moveOnButton = (Button) view.findViewById(R.id.button);
        moveOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.content_frame, new MvRockFragment())
                        .commit();
            }
        });
        return view;
    }
}
