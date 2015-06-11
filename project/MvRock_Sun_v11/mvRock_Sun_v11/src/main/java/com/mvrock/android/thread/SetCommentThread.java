package com.mvrock.android.thread;

import org.apache.http.message.BasicNameValuePair;

/**
 * Created by Tianhao on 15/6/10.
 */
public class SetCommentThread extends MvRockThreadObject {
    String songUrl;
    String replyTo;
    public SetCommentThread(String User_id, String Extra, String songUrl, String replyTo) {
        super(User_id, Extra);
        this.Url = "setComment.php";
        this.TAG = "setComment";
        this.songUrl = songUrl;
        this.replyTo = replyTo;

    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public void setResponse() {
    }

    @Override
    protected void setParams() {
        params.add(new BasicNameValuePair("uid",User_id));
        params.add(new BasicNameValuePair("url",songUrl));
        params.add(new BasicNameValuePair("replyTo", replyTo));
        params.add(new BasicNameValuePair("content", Extra));
    }
}
