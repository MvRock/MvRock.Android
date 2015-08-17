package com.mvrock.android.uicomponent.playlist;

import android.text.Editable;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.thread.GetNewSongDataThread;
import com.mvrock.android.thread.SetCommentThread;
import com.mvrock.android.uicomponent.MvRockUiComponentObject;
import com.mvrock.android.view.MvRockView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Tianhao on 15/6/8.
 */
public class CommentView extends MvRockUiComponentObject {
    public NonScrollListView commentList;
    public TextView commentNumber;
    public ImageView userAvatar;
    public MultiAutoCompleteTextView textInput;

    private ArrayAdapter<String> userNameArray;


    public CommentView() {
        userNameArray = new ArrayAdapter<>(MvRockView.MainActivity,
                android.R.layout.simple_expandable_list_item_1);
        userNameArray.addAll(MvRockModel.MusicBuddy.userName);
    }

    public void Init() {

        userAvatar.setImageDrawable(MvRockModel.User.User_Profile_pic);
        commentNumber.setText(String.valueOf(MvRockModel.CurrentSong.numberOfComments));
        textInput.setAdapter(userNameArray);
        textInput.setThreshold(1);

        showComment();
        textInput.setTokenizer(new MultiAutoCompleteTextView.Tokenizer() {

            @Override
            public int findTokenStart(CharSequence text, int cursor) {
                int i = cursor;

                while (i > 0 && text.charAt(i - 1) != '@') {
                    i--;
                }

                //Check if token really started with @, else we don't have a valid token
                if (i < 1 || text.charAt(i - 1) != '@') {
                    return cursor;
                }

                return i;
            }

            @Override
            public int findTokenEnd(CharSequence text, int cursor) {
                int i = cursor;
                int len = text.length();

                while (i < len) {
                    if (text.charAt(i) == ' ') {
                        return i;
                    } else {
                        i++;
                    }
                }

                return len;
            }

            public CharSequence terminateToken(CharSequence text) {
                int i = text.length();

                while (i > 0 && text.charAt(i - 1) == ' ') {
                    i--;
                }

                if (i > 0 && text.charAt(i - 1) == ' ') {
                    return text;
                } else {
                    if (text instanceof Spanned) {
                        SpannableString sp = new SpannableString(text + " ");
                        TextUtils.copySpansFrom((Spanned) text, 0, text.length(), Object.class, sp, 0);
                        return sp;
                    } else {
                        return text + " ";
                    }
                }
            }
        });

        textInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Layout layout = textInput.getLayout();
                int pos = textInput.getSelectionStart();
                int line = layout.getLineForOffset(pos);
                int baseline = layout.getLineBaseline(line);

                int bottom = textInput.getHeight();

                textInput.setDropDownVerticalOffset(baseline - bottom);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        textInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String str = textInput.getText().toString();
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) ||
                        (actionId == EditorInfo.IME_ACTION_DONE)) {

                    setNewComment(str);

                    getTheNewInfoAfterSetComment();

                    commentNumber.setText(String.valueOf(MvRockModel.CurrentSong.numberOfComments));
                }
                return false;
            }
        });
    }

    private void setNewComment(String str) {
        SetCommentThread setCommentThread = new SetCommentThread(
                MvRockModel.User.User_Id, str, MvRockModel.CurrentSong.url, "-1");
        setCommentThread.start();

        try {
            setCommentThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void showComment() {
        List<String> allComments = new ArrayList<>();
        for (ArrayList<String> temp : MvRockModel.CurrentSong.nameAndComments.values())
            allComments.addAll(temp);
        ArrayAdapter<String> commentContent = new ArrayAdapter<>(MvRockView.MainActivity,
                android.R.layout.simple_expandable_list_item_1, allComments);
        commentList.setAdapter(commentContent);
    }

    private void getTheNewInfoAfterSetComment() {
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

    public void update() {
        commentNumber.setText(String.valueOf(MvRockModel.CurrentSong.numberOfComments));
        showComment();
    }

}