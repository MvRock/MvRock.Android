package com.mvrock.android.uicomponent.player;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.LanguageOption;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.thread.ChangeLanguageThread;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;
import com.mvrock.android.view.MvRockView;

/**
 * Created by Kenneth on 7/9/15.
 */
public class LanguageButton extends MvRockUiComponentObject {

    public ImageView languageImage;

    public LanguageButton() {
        TAG += "LanguageButton";
    }

    public void Init() {
        Log.i(TAG, "Init()");

        languageImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick()");

                LayoutInflater inflater = (LayoutInflater) MvRockView.MainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                ScrollView scrollView = (ScrollView) inflater.inflate(R.layout.language_layout, null, false);

                switch (MvRockModel.User.language) {
                    case LanguageOption.ALL:
                        ((RadioGroup) scrollView.findViewById(R.id.radGroupLang)).check(R.id.radAll);
                        break;
                    case LanguageOption.ENG:
                        ((RadioGroup) scrollView.findViewById(R.id.radGroupLang)).check(R.id.radEng);
                        break;
                    case LanguageOption.CHN:
                        ((RadioGroup) scrollView.findViewById(R.id.radGroupLang)).check(R.id.radChn);
                        break;
                }

                new AlertDialog.Builder(MvRockView.MainActivity, R.style.AlertDialogTheme)
                        .setTitle("Select Video Language")
                        .setView(scrollView)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog alertDialog = (AlertDialog) dialog;
                                RadioGroup group = (RadioGroup) alertDialog.findViewById(R.id.radGroupLang);

                                switch (group.getCheckedRadioButtonId()) {
                                    case R.id.radAll:
                                        changeLanguageByThread(LanguageOption.ALL);
                                        MvRockModel.User.language = LanguageOption.ALL;
                                        break;
                                    case R.id.radEng:
                                        changeLanguageByThread(LanguageOption.ENG);
                                        MvRockModel.User.language = LanguageOption.ENG;
                                        break;
                                    case R.id.radChn:
                                        changeLanguageByThread(LanguageOption.CHN);
                                        MvRockModel.User.language = LanguageOption.CHN;
                                        break;
                                }

                                MvRockView.YouMayLikePlayListFragment.update();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });
    }

    public void changeLanguageByThread(int lang) {
        Log.i(TAG, "changeLanguageByThread(" + lang + ")");

        ChangeLanguageThread changeLanguageByThread = new ChangeLanguageThread(MvRockModel.User.User_Id, lang);
        changeLanguageByThread.start();
        try {
            changeLanguageByThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MvRockUiComponent.YouMayLikePlayListView.RequestPlayListByThread();
        MvRockUiComponent.YouMayLikePlayListView.RefreshListView();

        if (MvRockUiComponent.YouMayLikePlayListView.isAvailable()) {
            MvRockModel.CurrentSong.currentMVIndex = 0;

            String url = MvRockModel.YouMayLikeSongList.songArrayList.get(MvRockModel.CurrentSong.currentMVIndex).get("url");
            MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.loadVideo(url);
        }
    }
}
