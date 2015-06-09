package com.mvrock.android.uicomponent.playlist;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;


/**
 * Created by Tianhao on 15/6/8.
 */
public class CommentView extends MvRockUiComponentObject {
    public TextView commentNumber;
    public ImageView userAvatar;
    public EditText textInput;

    public CommentView() {
        commentNumber.setText(0);
    }


    public void Init() {
        userAvatar.setImageDrawable(MvRockModel.User.User_Profile_pic);
        textInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String str = textInput.getText().toString();
                if (keyCode == KeyEvent.KEYCODE_ENTER &&
                        !str.equals("Your Comment for This Song")) {
                    // put the comment to the database
                    // refresh the comment list
                    // reread the comment content
                    // refresh the number of comments
                }
                return false;
            }
        });
    }

}
