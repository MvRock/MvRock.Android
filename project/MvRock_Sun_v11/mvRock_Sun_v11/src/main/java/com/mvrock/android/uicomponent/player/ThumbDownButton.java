package com.mvrock.android.uicomponent.player;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.examples.youtubeapidemo.R;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.uicomponent.MvRockUiComponent;

/**
 * Created by Xuer on 5/5/15.
 */
public class ThumbDownButton extends PlayerControlButton {
    public ImageView dislikeSongImage;
    public ThumbDownButton(){TAG+="ThumbDownButton";}
    public void Init(){
        Log.i(TAG,"Init()");
        dislikeSongImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("dislikeSongImage", "onClick()");
                Log.i(TAG, "isYoumaylikeTabSelected= "+Boolean.toString( MvRockUiComponent.YouMayLikePlayListView.isAvailable())+", MvRockModel.CurrentSong.isLikedIconPressed = "+Boolean.toString(MvRockModel.CurrentSong.isLikedIconPressed)+", MvRockModel.CurrentSong.isDislikedIconPressed= "+Boolean.toString(MvRockModel.CurrentSong.isDislikedIconPressed));
                if( MvRockUiComponent.YouMayLikePlayListView.isAvailable())
                {
                    //the song playing right now is on the you may like list.
                    if(!MvRockModel.CurrentSong.isLikedIconPressed && !MvRockModel.CurrentSong.isDislikedIconPressed)
                    {
                        //1. rate this song
                        PostRatingByThread(-1);
                        //2. change the tool bar image of this song.
                        dislikeSongImage.setImageResource(R.drawable.thumbdownorange);
                        //3. change status variable
                        MvRockModel.CurrentSong.isDislikedIconPressed=true;
                        return;
                    }
                    else if(!MvRockModel.CurrentSong.isLikedIconPressed)
                    {
                        //1. rate this song
                        PostRatingByThread(0);
                        //2. change the tool bar image of this song.
                        dislikeSongImage.setImageResource(R.drawable.thumbdown);
                        //3. change status variable
                        MvRockModel.CurrentSong.isDislikedIconPressed=false;
                        return;
                    }
                    else
                    {
                        //1. rate this song
                        PostRatingByThread(-1);
                        //2. change the tool bar image of this song.
                        MvRockUiComponent.ThumbUpButton.likeSongImage.setImageResource(R.drawable.thumbup);
                        dislikeSongImage.setImageResource(R.drawable.thumbdownorange);
                        //3. change status variable
                        MvRockModel.CurrentSong.isLikedIconPressed=false;
                        MvRockModel.CurrentSong.isDislikedIconPressed=true;
                        //4. remove song from you liked list.
                        String currentSongUrl= MvRockModel.YouMayLikeSongList.songArrayList.get(MvRockModel.CurrentSong.currentMVIndex).get("url");
                        for(int i=0;i<MvRockModel.YouLikedSongList.songArrayList.size();i++)
                        {
                            if(currentSongUrl.equals(MvRockModel.YouLikedSongList.songArrayList.get(i).get("url")))
                            {
                                MvRockModel.YouLikedSongList.songArrayList.remove(i);
                                break;
                            }
                        }
                        //5. play the next song.
                        playNextSongAfterRemovedASongFromYoulikedList();
                        return;
                    }
                }
                else
                {
                    //the song playing right now is on the you liked list.
                    //1. rate this song
                    PostRatingByThread(-1);
                    //2. change the tool bar image of this song.
                    MvRockUiComponent.ThumbUpButton.likeSongImage.setImageResource(R.drawable.thumbup);
                    dislikeSongImage.setImageResource(R.drawable.thumbdownorange);
                    //3. change status variable
                    MvRockModel.CurrentSong.isLikedIconPressed=false;
                    MvRockModel.CurrentSong.isDislikedIconPressed=true;
                    //4. remove song from you liked list.
                    MvRockModel.YouLikedSongList.songArrayList.remove(MvRockModel.CurrentSong.currentMVIndex);
                    //5. play the next song.
                    playNextSongAfterRemovedASongFromYoulikedList();
                    return;
                }
            }
        });

    }
}
