package com.mvrock.android.view.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.examples.youtubeapidemo.R;
import com.facebook.login.LoginManager;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.widget.AppInviteDialog;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayerView;
import com.mvrock.android.model.DataInitialization;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.drawer.MvRockDrawer;
import com.mvrock.android.uicomponent.player.MvRockYoutubePlayerFragment;
import com.mvrock.android.uicomponent.playlist.ArtistView;
import com.mvrock.android.uicomponent.playlist.CommentView;
import com.mvrock.android.uicomponent.playlist.NonScrollListView;
import com.mvrock.android.uicomponent.playlist.RightFloatingMenu;
import com.mvrock.android.uicomponent.playlist.SongView;
import com.mvrock.android.uicomponent.playlist.ToolbarView;
import com.mvrock.android.uicomponent.socialstuff.LeftFloatingMenu;
import com.mvrock.android.uicomponent.station.StationCancelButton;
import com.mvrock.android.view.MvRockView;

import static android.util.Log.i;

/**
 * A simple YouTube Android API demo application which shows how to create a
 * simple application that displays a YouTube Video in a
 * {@link YouTubePlayerView}.
 * <p/>
 * Note, to use a {@link YouTubePlayerView}, your activity must extend
 * {@link YouTubeBaseActivity}.
 */

public class MvRockFragment extends Fragment {
    private static final String TAG = "View.MvRockFragment";

    public MvRockFragment() {
        Log.i(TAG, "MvRockFragment()");

        MvRockModel.dataInitialization = new DataInitialization();

        MvRockView.YouMayLikePlayListFragment = new YouMayLikePlayListFragment();
        MvRockView.YouLikedPlayListFragment = new YouLikedPlayListFragment();
        MvRockView.StationListFragment = new StationListFragment();
        MvRockView.StationPlayListFragment = new StationPlayListFragment();
        MvRockView.BuddyFeedFragment = new BuddyFeedFragment();

        MvRockUiComponent.MvRockYoutubePlayer = new MvRockYoutubePlayerFragment();
        MvRockUiComponent.RightFloatingMenu = new RightFloatingMenu();
        MvRockUiComponent.LeftFloatingMenu = new LeftFloatingMenu();
        MvRockUiComponent.StationCancelButton = new StationCancelButton();
        MvRockUiComponent.MvRockDrawer = new MvRockDrawer();
        MvRockUiComponent.songView = new SongView();
        MvRockUiComponent.artistView = new ArtistView();
        MvRockUiComponent.toolbarView = new ToolbarView();
        MvRockUiComponent.commentView = new CommentView();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView()");
        View view = inflater.inflate(R.layout.fragment_mvrock, container, false);

        MvRockUiComponent.songView.songNameView = (TextView) view.findViewById(R.id.music_title);
        MvRockUiComponent.songView.recommendationTitleView = (TextView) view.findViewById(R.id.recommendation_title);
        MvRockUiComponent.songView.recommendationReasonView = (TextView) view.findViewById(R.id.recommendation_reason);
        MvRockUiComponent.songView.Init();

        MvRockUiComponent.artistView.artistImageView = (ImageView) view.findViewById(R.id.artist_image);
        MvRockUiComponent.artistView.artistNameView = (TextView) view.findViewById(R.id.name_of_artist);
        MvRockUiComponent.artistView.subscribeButton = (Button) view.findViewById(R.id.subscribe_button);
        MvRockUiComponent.artistView.Init();

        MvRockUiComponent.toolbarView.thumbUpNumber = (TextView) view.findViewById(R.id.number_thumbup);
        MvRockUiComponent.toolbarView.thumbDownNumber = (TextView) view.findViewById(R.id.number_thumbdown);
        MvRockUiComponent.toolbarView.nextSongButton.nextSongImage = (ImageView) view.findViewById(R.id.music_title_next);
        MvRockUiComponent.toolbarView.thumbUpButton.likeSongImage = (ImageView) view.findViewById(R.id.music_title_thumbup);
        MvRockUiComponent.toolbarView.thumbDownButton.dislikeSongImage = (ImageView) view.findViewById(R.id.music_title_thumbdown);
        MvRockUiComponent.toolbarView.shareButton.shareSongImage = (ImageView) view.findViewById(R.id.music_title_share);
        MvRockUiComponent.toolbarView.sendSongButton.sendSongImage = (ImageView) view.findViewById(R.id.music_title_send_song);
        MvRockUiComponent.toolbarView.reportButton.reportSongImage = (ImageView) view.findViewById(R.id.music_title_report);
        MvRockUiComponent.toolbarView.Init();

        MvRockUiComponent.MvRockYoutubePlayer.Init();
        getChildFragmentManager().beginTransaction().replace(R.id.youtubeplayerfragment, MvRockUiComponent.MvRockYoutubePlayer).commit();

        MvRockUiComponent.RightFloatingMenu.Init();
        MvRockUiComponent.LeftFloatingMenu.Init();

        MvRockUiComponent.MvRockDrawer.mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        MvRockUiComponent.MvRockDrawer.leftFragment = (FrameLayout) view.findViewById(R.id.left_drawer);
        MvRockUiComponent.MvRockDrawer.rightFragment = (FrameLayout) view.findViewById(R.id.right_drawer);
        MvRockUiComponent.MvRockDrawer.frame = (FrameLayout) view.findViewById(R.id.content_frame);
        MvRockUiComponent.MvRockDrawer.Init();

        MvRockUiComponent.commentView.commentNumber = (TextView) view.findViewById(R.id.comment_number);
        MvRockUiComponent.commentView.textInput = (MultiAutoCompleteTextView) view.findViewById(R.id.comment_input);
        MvRockUiComponent.commentView.userAvatar = (ImageView) view.findViewById(R.id.user_avatar);
        MvRockUiComponent.commentView.commentList = (NonScrollListView) view.findViewById(R.id.comment_list);

        MvRockUiComponent.commentView.Init();

        setHasOptionsMenu(true);
        getActivity().getActionBar().hide();
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(TAG, "onOptionsItemSelected()");

        //if (MvRockUiComponent.LeftDrawerToggle.onOptionsItemSelected(item)) return true;

        switch (item.getItemId()) {
            case R.id.invite_friends:
                if (AppInviteDialog.canShow()) {
                    AppInviteContent content = new AppInviteContent.Builder()
                            // edit the app link in Facebook developers; TODO: in the future change url to Play store
                            // Edit here: https://developers.facebook.com/quickstarts/?platform=app-links-host
                            .setApplinkUrl("https://fb.me/942660789157709")
                            .setPreviewImageUrl("https://wanlab.poly.edu/xing/tube/img/mark.png")
                            .build();

                    AppInviteDialog.show(MvRockView.MainActivity, content);
                } else {
                    Toast.makeText(MvRockView.MainActivity, "Unable to invite friends. Please install the Facebook app.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.logout:
                LoginManager loginManager = LoginManager.getInstance();
                loginManager.logOut();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        i(TAG, "onCreateOptionsMenu()");
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        i(TAG, "onConfigurationChanged()");
        super.onConfigurationChanged(newConfig);

        MvRockUiComponent.LeftDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        i(TAG, "onSaveInstanceState()");
        super.onSaveInstanceState(state);

        if (MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer != null && MvRockModel.CurrentSong != null) {
            MvRockModel.CurrentSong.currentTime = MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.getCurrentTimeMillis();
        }
    }

    @Override
    public void onViewStateRestored(Bundle state) {
        i(TAG, "onViewStateRestored()");
        super.onViewStateRestored(state);

        if (MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer != null) {
            MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.cueVideo(MvRockModel.CurrentSong.url, MvRockModel.CurrentSong.currentTime);
        }
    }
}
