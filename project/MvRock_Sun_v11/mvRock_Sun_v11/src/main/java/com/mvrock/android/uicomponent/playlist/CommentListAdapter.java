package com.mvrock.android.uicomponent.playlist;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.thread.GetUserProfilePicture;
import com.mvrock.android.view.MvRockView;

import java.util.ArrayList;

/**
 * Created by Tianhao on 15/8/19.
 */
public class CommentListAdapter extends BaseAdapter {
    private ArrayList<Drawable> authorPicture;
    final int VIEW_TYPE = 1;
    final int TYPE_1 = 0;
    private LayoutInflater inflater = null;

    public CommentListAdapter(ArrayList<String> userID) {
        this.inflater = LayoutInflater.from(MvRockView.MainActivity);
        authorPicture = getAuthorAvatar(userID);
    }

    @Override
    public int getCount() {
        return MvRockModel.CurrentSong.numberOfComments;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.one_comment, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.userAvatar = (ImageView) convertView.findViewById(R.id.image_of_author);
            viewHolder.authorName = (TextView) convertView.findViewById(R.id.authorName);
            viewHolder.commentContent = (TextView) convertView.findViewById(R.id.content_of_comment);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.userAvatar.setImageDrawable(authorPicture.get(position));
        viewHolder.authorName.setText(MvRockModel.CurrentSong.commentAuthor.get(position));
        viewHolder.commentContent.setText(MvRockModel.CurrentSong.commentContent.get(position));
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE;
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_1;
    }

    class ViewHolder {
        ImageView userAvatar;
        TextView authorName;
        TextView commentContent;
    }

    private ArrayList<Drawable> getAuthorAvatar(ArrayList<String> userID) {
        ArrayList<Drawable> userAvatar = new ArrayList<>();
        GetUserProfilePicture getUserProfilePicture = new GetUserProfilePicture(userAvatar, userID);
        getUserProfilePicture.start();
        try {
            getUserProfilePicture.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userAvatar;
    }

}
