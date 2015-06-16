package com.mvrock.android.uicomponent.playlist;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.thread.GetNewSongDataThread;
import com.mvrock.android.thread.SetCommentThread;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;


/**
 * Created by Tianhao on 15/6/8.
 */
public class CommentView extends MvRockUiComponentObject {
    public TextView commentNumber;
    public ImageView userAvatar;
    public EditText textInput;

    public CommentView() {

        //commentNumber.setText(0);
    }


    public void Init() {

        userAvatar.setImageDrawable(MvRockModel.User.User_Profile_pic);
        commentNumber.setText(String.valueOf(MvRockModel.CurrentSong.numberOfComments));
        textInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String str = textInput.getText().toString();
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) ||
                        (actionId == EditorInfo.IME_ACTION_DONE)) {
                    SetCommentThread setCommentThread = new SetCommentThread(
                            MvRockModel.User.User_Id, str, MvRockModel.CurrentSong.url, "-1");
                    setCommentThread.start();
                    try {
                        setCommentThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    getTheNewInfoAfterSetComement();
                    // refresh the comment list

                    // reread the comment content

                    // refresh the number of comments
                    commentNumber.setText(String.valueOf(MvRockModel.CurrentSong.numberOfComments));

                }
                return false;
            }
        });
    }
    private void getTheNewInfoAfterSetComement(){
        GetNewSongDataThread getNewSongDataThread = new GetNewSongDataThread(MvRockModel.User.User_Id, MvRockModel.CurrentSong.url);
        getNewSongDataThread.start();
        try {
            getNewSongDataThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getNewSongDataThread.setResponse();
        MvRockModel.CurrentSong.convertData();
    }

}