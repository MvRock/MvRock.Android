package com.mvrock.android.uicomponent.player;

import android.util.Log;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.model.ReasonOption;
import com.mvrock.android.thread.GetNewSongDataThread;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.view.DeveloperKey;

import java.util.Map;

/**
 * Created by Xuer on 5/5/15.
 */
public class MvRockYoutubePlayerFragment extends YouTubePlayerSupportFragment {
    private static final String TAG = "MvRockYoutubePlayer";
    public YouTubePlayer YouTubePlayer;

    public static MvRockYoutubePlayerFragment newInstance(String url) {

        MvRockYoutubePlayerFragment playerYouTubeFrag = new MvRockYoutubePlayerFragment();
        return playerYouTubeFrag;
    }

    public void Init() {
        Log.i(TAG, "Init()");
        initialize(DeveloperKey.DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                Log.i(TAG, "onInitializationFailure()");
                Log.i(TAG, arg1.toString());
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

                        updateCurrentSong();
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

    public void updateCurrentSong() {
        Log.i(TAG, "updateCurrentSong()");

        if (MvRockUiComponent.YouMayLikePlayListView.isAvailable()) {
            Map<String, String> currentSongInfo = MvRockModel.YouMayLikeSongList.songArrayList.get(MvRockModel.CurrentSong.currentMVIndex);

            MvRockModel.CurrentSong.url = currentSongInfo.get("url");
            MvRockModel.CurrentSong.songName = currentSongInfo.get("song_name");
            MvRockModel.CurrentSong.artistImage = MvRockModel.YouMayLikeSongList.artistImages.get(MvRockModel.CurrentSong.currentMVIndex);
            MvRockModel.CurrentSong.artistName = currentSongInfo.get("artist_name").replace("By ", "");
            MvRockModel.CurrentSong.rootShareUserId = MvRockModel.User.User_Id;

            int reason = Integer.parseInt(currentSongInfo.get("reason"));
            switch (reason) {
                default:
                case 0:
                    MvRockModel.CurrentSong.reason = ReasonOption.None;
                    break;
                case 1:
                    MvRockModel.CurrentSong.reason = ReasonOption.Random;
                    break;
                case 2:
                    MvRockModel.CurrentSong.reason = ReasonOption.YouLikedBefore;
                    break;
                case 3:
                    MvRockModel.CurrentSong.reason = ReasonOption.Personalized;
                    break;
            }
            MvRockUiComponent.songView.showRecommendation();

        } else if (MvRockUiComponent.YouLikedPlayListView.isAvailable()){
            Map<String, String> currentSongInfo = MvRockModel.YouLikedSongList.songArrayList.get(MvRockModel.CurrentSong.currentMVIndex);

            MvRockModel.CurrentSong.url = currentSongInfo.get("url");
            MvRockModel.CurrentSong.songName = currentSongInfo.get("song_name");
            MvRockModel.CurrentSong.artistImage = MvRockModel.YouLikedSongList.artistImages.get(MvRockModel.CurrentSong.currentMVIndex);
            MvRockModel.CurrentSong.artistName = currentSongInfo.get("artist_name").replace("By ", "");
            MvRockModel.CurrentSong.reason = ReasonOption.None;
            MvRockModel.CurrentSong.rootShareUserId = MvRockModel.User.User_Id;

            MvRockUiComponent.songView.hideRecommendation();
        }

        MvRockModel.CurrentSong.isReported = false;

        GetNewSongDataThread getNewSongDataThread = new GetNewSongDataThread(MvRockModel.User.User_Id, MvRockModel.CurrentSong.url);
        getNewSongDataThread.start();
        try {
            getNewSongDataThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getNewSongDataThread.setResponse();
        MvRockModel.CurrentSong.convertData();

        MvRockUiComponent.songView.update();
        MvRockUiComponent.artistView.update();
        MvRockUiComponent.toolbarView.update();
        MvRockUiComponent.commentView.update();
    }
}
