package com.mvrock.android.uicomponent.player;

import android.util.Log;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.thread.GetNewSongDataThread;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.view.DeveloperKey;

/**
 * Created by Xuer on 5/5/15.
 */
public class MvRockYoutubePlayerFragment extends YouTubePlayerSupportFragment {
    private static final String TAG="MvRockYoutubePlayer";
    public YouTubePlayer YouTubePlayer;
    public boolean isReady=false;
    public static MvRockYoutubePlayerFragment newInstance(String url) {

        MvRockYoutubePlayerFragment playerYouTubeFrag = new MvRockYoutubePlayerFragment();
        return playerYouTubeFrag;
    }

    public void Init() {
        Log.i(TAG,"Init()");
        initialize(DeveloperKey.DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                Log.i(TAG,"onInitializationFailure()");
                Log.i(TAG,arg1.toString());
            }

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
                Log.i(TAG, "onInitializationSuccess()");
                YouTubePlayer = player;
                YouTubePlayer.setPlayerStyle(com.google.android.youtube.player.YouTubePlayer.PlayerStyle.DEFAULT);
                isReady=true;
                player.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {

                    @Override
                    public void onVideoStarted() {
                        Log.i(TAG, "onVideoStarted()");
                        RequestNewSongDataByThread();
                        MvRockUiComponent.changeToolBarImage();
                    }

                    @Override
                    public void onVideoEnded() {
                        Log.i(TAG, "onVideoEnded()");
                        if (MvRockUiComponent.YouMayLikePlayListView.isAvailable()) {
                            if (MvRockModel.CurrentSong.currentMVIndex < 14) {
                                String next = MvRockModel.YouLikedSongList.songArrayList.get(MvRockModel.CurrentSong.currentMVIndex + 1).get("url");
                                MvRockModel.CurrentSong.currentMVIndex++;
                                YouTubePlayer.loadVideo(next);

                            } else {
                                MvRockUiComponent.YouMayLikePlayListView.RequestPlayListByThread();
                                MvRockModel.CurrentSong.currentMVIndex = 0;
                                YouTubePlayer.loadVideo(MvRockModel.YouLikedSongList.songArrayList.get(0).get("url"));

                            }
                        } else {
                            if (MvRockModel.YouLikedSongList.songArrayList.size() > 0) {
                                if (MvRockModel.CurrentSong.currentMVIndex < MvRockModel.YouLikedSongList.songArrayList.size() - 1) {
                                    String next = MvRockModel.YouLikedSongList.songArrayList.get(MvRockModel.CurrentSong.currentMVIndex + 1).get("url");
                                    MvRockModel.CurrentSong.currentMVIndex++;
                                    YouTubePlayer.loadVideo(next);

                                } else {
                                    MvRockModel.CurrentSong.currentMVIndex = 0;
                                    YouTubePlayer.loadVideo(MvRockModel.YouLikedSongList.songArrayList.get(0).get("url"));

                                }
                            }

                        }
                    }

                    @Override
                    public void onLoading() {
                    }

                    @Override
                    public void onLoaded(String videoId) {
                    }

                    @Override
                    public void onError(com.google.android.youtube.player.YouTubePlayer.ErrorReason reason) {
                    }

                    @Override
                    public void onAdStarted() {
                    }
                });
                if (!wasRestored) {
                    Log.i(TAG, "play the first song.");
                        YouTubePlayer.loadVideo(MvRockModel.YouMayLikeSongList.songArrayList.get(0).get("url"));
                }
            }
        });

    }
    public void RequestNewSongDataByThread(){
        Log.i(TAG, "RequestNewSongDataByThread()");
        String song_url;
        if ( MvRockUiComponent.YouMayLikePlayListView.isAvailable()) {
            song_url =  MvRockModel.YouMayLikeSongList.songArrayList.get(MvRockModel.CurrentSong.currentMVIndex).get("url");

        } else {
            song_url = MvRockModel.YouLikedSongList.songArrayList.get(MvRockModel.CurrentSong.currentMVIndex).get("url");
        }

        GetNewSongDataThread getNewSongDataThread = new GetNewSongDataThread(MvRockModel.User.User_Id,song_url);
        getNewSongDataThread.start();
        try {
            getNewSongDataThread.join();
        } catch (InterruptedException e1) {

            e1.printStackTrace();
        }
        getNewSongDataThread.setResponse();
    }

}
