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
    public static MvRockYoutubePlayerFragment newInstance(String url) {

        MvRockYoutubePlayerFragment playerYouTubeFrag = new MvRockYoutubePlayerFragment();
        return playerYouTubeFrag;
    }

    public void init() {
        Log.i(TAG,"init()");
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
                            if (MvRockModel.currentMVIndex < 14) {
                                String next = MvRockModel.YouLikedSongList.songArrayList.get(MvRockModel.currentMVIndex + 1).get("url");
                                MvRockModel.currentMVIndex++;
                                YouTubePlayer.loadVideo(next);

                            } else {
                                MvRockUiComponent.YouMayLikePlayListView.RequestPlayListByThread();
                                MvRockModel.currentMVIndex = 0;
                                YouTubePlayer.loadVideo(MvRockModel.YouLikedSongList.songArrayList.get(0).get("url"));

                            }
                        } else {
                            if (MvRockModel.YouLikedSongList.songArrayList.size() > 0) {
                                if (MvRockModel.currentMVIndex < MvRockModel.YouLikedSongList.songArrayList.size() - 1) {
                                    String next = MvRockModel.YouLikedSongList.songArrayList.get(MvRockModel.currentMVIndex + 1).get("url");
                                    MvRockModel.currentMVIndex++;
                                    YouTubePlayer.loadVideo(next);

                                } else {
                                    String first = MvRockModel.YouLikedSongList.songArrayList.get(0).get("url");
                                    MvRockModel.currentMVIndex = 0;
                                    YouTubePlayer.loadVideo(first);

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
                    String first = null;
                    if (MvRockModel.YouLikedSongList.songArrayList != null && !MvRockModel.YouLikedSongList.songArrayList.isEmpty()) {
                        first = MvRockModel.YouLikedSongList.songArrayList.get(0).get("url");
                    }
                    player.loadVideo(first);
                }
            }
        });

    }
    public void RequestNewSongDataByThread(){
        Log.i(TAG, "RequestNewSongDataByThread()");
        String song_url;
        if ( MvRockUiComponent.YouMayLikePlayListView.isAvailable()) {
            song_url =  MvRockModel.YouMayLikeSongList.songArrayList.get(MvRockModel.currentMVIndex).get("url");

        } else {
            song_url = MvRockModel.YouLikedSongList.songArrayList.get(MvRockModel.currentMVIndex).get("url");
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
