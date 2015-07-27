package com.mvrock.android.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.LanguageOption;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.player.LanguageButton;
import com.mvrock.android.uicomponent.playlist.YouMayLikePlayListView;

/**
 * Created by Tianhao on 15/6/1.
 */
public class YouMayLikePlayListFragment extends Fragment {

    private static final String TAG = "View.YMLPlayListFrag";

    private TextView title;
    private LanguageButton languageButton;

    public YouMayLikePlayListFragment() {
        Log.i(TAG, "YouMayLikePlayListFragment()");

        MvRockUiComponent.YouMayLikePlayListView = new YouMayLikePlayListView();
        languageButton = new LanguageButton();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView()");

        View rightDrawerView = inflater.inflate(R.layout.fragment_right_drawer_play_list, container, false);

        MvRockUiComponent.YouMayLikePlayListView.playListview = (ListView) rightDrawerView.findViewById(R.id.right_drawer_listview);
        MvRockUiComponent.YouMayLikePlayListView.Init();

        title = (TextView) rightDrawerView.findViewById(R.id.right_drawer_title);
        title.setText("You May Like");

        languageButton.languageImage = (ImageView) rightDrawerView.findViewById(R.id.right_drawer_button);
        languageButton.Init();
        update();

        return rightDrawerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
        MvRockUiComponent.YouMayLikePlayListView.RefreshListView();
    }

    public void update() {
        if (MvRockModel.User.language == LanguageOption.ALL) {
            languageButton.languageImage.setImageResource(R.drawable.lang_all);
        } else if (MvRockModel.User.language == LanguageOption.ENG) {
            languageButton.languageImage.setImageResource(R.drawable.lang_english);
        } else if (MvRockModel.User.language == LanguageOption.CHN) {
            languageButton.languageImage.setImageResource(R.drawable.lang_chinese);
        }
    }
}
