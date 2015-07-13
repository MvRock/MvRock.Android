package com.mvrock.android.uicomponent.player;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.thread.ReportThread;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.view.MvRockView;

/**
 * Created by Xuer on 5/5/15.
 * Add comment on 5/26/15
 * <p/>
 * coming soon :)
 */
public class ReportButton extends PlayerControlButton {

    public ImageView reportSongImage;

    public ReportButton() {
        TAG += "ReportButton";
    }

    public void Init() {
        Log.i(TAG, "Init()");

        reportSongImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick()");
                showReportDialog();
            }
        });
    }

    public void showReportDialog() {
        LayoutInflater inflater = (LayoutInflater) MvRockView.MainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ScrollView scrollView = (ScrollView) inflater.inflate(R.layout.report_layout, null, false);

        new AlertDialog.Builder(MvRockView.MainActivity, R.style.AlertDialogTheme)
                .setTitle("Report Music Video")
                .setView(scrollView)
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog d = (AlertDialog) dialog;
                        String message = ((EditText) d.findViewById(R.id.report_text)).getText().toString();

                        if (message.length() < 15) {
                            Toast.makeText(MvRockView.MainActivity, "Must be at least 15 characters", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // send report to MvRock email
                        new ReportThread(MvRockModel.User.User_Id, MvRockModel.CurrentSong.url, message).start();
                        MvRockModel.CurrentSong.isReported = true;
                        MvRockUiComponent.toolbarView.update();
                        Toast.makeText(MvRockView.MainActivity, "Your report has been sent", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
