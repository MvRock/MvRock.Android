package com.mvrock.android.uicomponent.player;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.thread.GetOneRecSongThread;
import com.mvrock.android.uicomponent.MvRockUiComponent;


/**
 * Created by Xuer on 5/5/15.
 */
public class ThumbUpButton extends PlayerControlButton {

    public ImageView likeSongImage;

    public ThumbUpButton() {
        TAG += "ThumbUpButton";
    }

    public void Init() {
        Log.i(TAG, "Init()");

        likeSongImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("likeSongImage", "onClick()");
                Log.i(TAG, "isYoumaylikeTabSelected = " + Boolean.toString(MvRockUiComponent.YouMayLikePlayListView.isAvailable())
                        + ", MvRockModel.CurrentSong.isLikedIconPressed = " + Boolean.toString(MvRockModel.CurrentSong.isLikedIconPressed) +
                        ", MvRockModel.CurrentSong.isDislikedIconPressed = " + Boolean.toString(MvRockModel.CurrentSong.isDislikedIconPressed));

                if (MvRockUiComponent.YouMayLikePlayListView.isAvailable()) {
                    //the song playing right now is on the you may like list.
                    if (!MvRockModel.CurrentSong.isLikedIconPressed && !MvRockModel.CurrentSong.isDislikedIconPressed) {
                        //1. rate this song
                        getOneRecSongByThread();
                        PostRatingByThread(1);
                        //2. add this song into you liked list
                        MvRockModel.YouLikedSongList.songArrayList.add(MvRockModel.YouMayLikeSongList.songArrayList.get(MvRockModel.CurrentSong.currentMVIndex));
                        MvRockModel.YouLikedSongList.artistImages.add(MvRockModel.YouMayLikeSongList.artistImages.get(MvRockModel.CurrentSong.currentMVIndex));
                        //3. change status variable
                        MvRockModel.CurrentSong.isLikedIconPressed = true;
                        MvRockModel.CurrentSong.numLikes += 1;
                        //4. change the tool bar image of this song.
                        MvRockUiComponent.toolbarView.update();
                    } else if (!MvRockModel.CurrentSong.isLikedIconPressed && MvRockModel.CurrentSong.isDislikedIconPressed) {
                        //1. rate this song
                        getOneRecSongByThread();
                        PostRatingByThread(1);
                        //2. add this song into you liked list
                        MvRockModel.YouLikedSongList.songArrayList.add(MvRockModel.YouMayLikeSongList.songArrayList.get(MvRockModel.CurrentSong.currentMVIndex));
                        MvRockModel.YouLikedSongList.artistImages.add(MvRockModel.YouMayLikeSongList.artistImages.get(MvRockModel.CurrentSong.currentMVIndex));
                        //3. change status variable
                        MvRockModel.CurrentSong.isLikedIconPressed = true;
                        MvRockModel.CurrentSong.isDislikedIconPressed = false;
                        MvRockModel.CurrentSong.numLikes += 1;
                        MvRockModel.CurrentSong.numDislikes -= 1;
                        //4. change the tool bar image of this song.
                        MvRockUiComponent.toolbarView.update();
                    } else {
                        //1. rate this song
                        PostRatingByThread(0);
                        //2. remove song from you liked list.
                        String currentSongUrl = MvRockModel.CurrentSong.url;

                        for (int i = 0; i < MvRockModel.YouLikedSongList.songArrayList.size(); i++) {
                            if (currentSongUrl.equals(MvRockModel.YouLikedSongList.songArrayList.get(i).get("url"))) {
                                MvRockModel.YouLikedSongList.songArrayList.remove(i);
                                MvRockModel.YouLikedSongList.artistImages.remove(i);
                                break;
                            }
                        }
                        //3. change status variable
                        MvRockModel.CurrentSong.isLikedIconPressed = false;
                        MvRockModel.CurrentSong.numLikes -= 1;
                        //4. change the tool bar image of this song.
                        MvRockUiComponent.toolbarView.update();
                    }
                } else if (MvRockUiComponent.YouLikedPlayListView.isAvailable()) {
                    //the song playing right now is on the you liked list.
                    //1. rate this song
                    PostRatingByThread(0);
                    //2. change status variable
                    MvRockModel.CurrentSong.isLikedIconPressed = false;
                    MvRockModel.CurrentSong.numLikes -= 1;
                    //31. change the tool bar image of this song.
                    MvRockUiComponent.toolbarView.update();
                    //4. remove song from you liked list.
                    MvRockModel.YouLikedSongList.songArrayList.remove(MvRockModel.CurrentSong.currentMVIndex);
                    MvRockModel.YouLikedSongList.artistImages.remove(MvRockModel.CurrentSong.currentMVIndex);
                    MvRockUiComponent.YouLikedPlayListView.RefreshListView();
                    //5. play the next song.
                    playNextSongAfterRemovedASongFromYoulikedList();
                } else if (MvRockUiComponent.StationPlayListView.isAvailable()) {
                    if (!MvRockModel.CurrentSong.isLikedIconPressed && !MvRockModel.CurrentSong.isDislikedIconPressed) {
                        //1. rate this song
                        PostRatingByThread(1);
                        //2. add this song into you liked list
                        MvRockModel.YouLikedSongList.songArrayList.add(MvRockModel.StationSongList.songArrayList.get(MvRockModel.CurrentSong.currentMVIndex));
                        MvRockModel.YouLikedSongList.artistImages.add(MvRockModel.StationSongList.artistImages.get(MvRockModel.CurrentSong.currentMVIndex));
                        //3. change status variable
                        MvRockModel.CurrentSong.isLikedIconPressed = true;
                        MvRockModel.CurrentSong.numLikes += 1;
                        //4. change the tool bar image of this song.
                        MvRockUiComponent.toolbarView.update();
                    } else if (!MvRockModel.CurrentSong.isLikedIconPressed && MvRockModel.CurrentSong.isDislikedIconPressed) {
                        //1. rate this song
                        getOneRecSongByThread();
                        PostRatingByThread(1);
                        //2. add this song into you liked list
                        MvRockModel.YouLikedSongList.songArrayList.add(MvRockModel.StationSongList.songArrayList.get(MvRockModel.CurrentSong.currentMVIndex));
                        MvRockModel.YouLikedSongList.artistImages.add(MvRockModel.StationSongList.artistImages.get(MvRockModel.CurrentSong.currentMVIndex));
                        //3. change status variable
                        MvRockModel.CurrentSong.isLikedIconPressed = true;
                        MvRockModel.CurrentSong.isDislikedIconPressed = false;
                        MvRockModel.CurrentSong.numLikes += 1;
                        MvRockModel.CurrentSong.numDislikes -= 1;
                        //4. change the tool bar image of this song.
                        MvRockUiComponent.toolbarView.update();
                    } else {
                        //1. rate this song
                        PostRatingByThread(0);
                        //2. remove song from you liked list.
                        String currentSongUrl = MvRockModel.CurrentSong.url;

                        for (int i = 0; i < MvRockModel.YouLikedSongList.songArrayList.size(); i++) {
                            if (currentSongUrl.equals(MvRockModel.YouLikedSongList.songArrayList.get(i).get("url"))) {
                                MvRockModel.YouLikedSongList.songArrayList.remove(i);
                                MvRockModel.YouLikedSongList.artistImages.remove(i);
                                break;
                            }
                        }
                        //3. change status variable
                        MvRockModel.CurrentSong.isLikedIconPressed = false;
                        MvRockModel.CurrentSong.numLikes -= 1;
                        //4. change the tool bar image of this song.
                        MvRockUiComponent.toolbarView.update();
                    }
                }
            }
        });
    }

    public void getOneRecSongByThread() {
        // only get one recommended song if on you may like list
        if (MvRockUiComponent.YouMayLikePlayListView.isAvailable()) {
            GetOneRecSongThread getOneRecSongThread = new GetOneRecSongThread(MvRockModel.User.User_Id, MvRockModel.CurrentSong.url);
            getOneRecSongThread.start();
        }
    }

}
