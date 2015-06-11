package com.mvrock.android.uicomponent.playlist;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvrock.android.model.MvRockModel;
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
    }


    public void Init() {
        userAvatar.setImageDrawable(MvRockModel.User.User_Profile_pic);
        commentNumber.setText(String.valueOf(MvRockModel.CurrentSong.numberOfComments));
        textInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String str = textInput.getText().toString();
                if (keyCode == KeyEvent.KEYCODE_ENTER &&
                        !str.equals("Your Comment for This Song")) {

                    SetCommentThread setCommentThread = new SetCommentThread(
                            MvRockModel.User.User_Id, str, MvRockModel.CurrentSong.url, "-1");
                    setCommentThread.start();
                    try {
                        setCommentThread.join();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    // refresh the comment list
                    // reread the comment content
                    // refresh the number of comments
                }
                return false;
            }
        });
    }

}
