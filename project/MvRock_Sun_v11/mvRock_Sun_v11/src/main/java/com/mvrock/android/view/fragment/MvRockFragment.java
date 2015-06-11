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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TextView;

import com.examples.youtubeapidemo.R;
import com.facebook.Session;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayerView;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.thread.ChangeLanguageThread;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.uicomponent.drawer.MvRockDrawer;
import com.mvrock.android.uicomponent.player.MvRockYoutubePlayerFragment;
import com.mvrock.android.uicomponent.player.NextSongButton;
import com.mvrock.android.uicomponent.player.ReportButton;
import com.mvrock.android.uicomponent.player.ShareButton;
import com.mvrock.android.uicomponent.player.ThumbDownButton;
import com.mvrock.android.uicomponent.player.ThumbUpButton;
import com.mvrock.android.uicomponent.playlist.CommentView;
import com.mvrock.android.uicomponent.playlist.MvRockTabHost;
import com.mvrock.android.uicomponent.playlist.RightFloatingMenu;
import com.mvrock.android.uicomponent.station.StationCancelButton;
import com.mvrock.android.uicomponent.station.StationListView;
import com.mvrock.android.uicomponent.station.StationSearchView;
import com.mvrock.android.view.MainActivity;
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
        MvRockUiComponent.MvRockYoutubePlayer = MvRockYoutubePlayerFragment.newInstance("video_id");
        MvRockUiComponent.NextSongButton = new NextSongButton();
        MvRockUiComponent.ThumbUpButton = new ThumbUpButton();
        MvRockUiComponent.ThumbDownButton = new ThumbDownButton();
        MvRockUiComponent.ReportButton = new ReportButton();
        MvRockUiComponent.ShareButton = new ShareButton();
//        MvRockUiComponent.MvRockTabHost=new MvRockTabHost();
        MvRockUiComponent.RightFloatingMenu = new RightFloatingMenu();
        MvRockUiComponent.StationCancelButton = new StationCancelButton();
        MvRockUiComponent.StationListView = new StationListView();
        MvRockUiComponent.MvRockDrawer = new MvRockDrawer();
        MvRockUiComponent.commentView = new CommentView();


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MvRockView.MainActivity = (MainActivity) getActivity();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView()");
        View view = inflater.inflate(R.layout.fragment_mvrock, container, false);


        Session.setActiveSession(MvRockModel.User.Session);

        MvRockUiComponent.MvRockYoutubePlayer.Init();
        MvRockView.MainActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.youtubeplayerfragment, MvRockUiComponent.MvRockYoutubePlayer).commit();

        MvRockUiComponent.NextSongButton.nextSongImage = (ImageView) view.findViewById(R.id.nextbutton);
        MvRockUiComponent.NextSongButton.Init();

        MvRockUiComponent.ThumbUpButton.likeSongImage = (ImageView) view.findViewById(R.id.thumbupbutton);
        MvRockUiComponent.ThumbUpButton.Init();

        MvRockUiComponent.ThumbDownButton.dislikeSongImage = (ImageView) view.findViewById(R.id.thumbdownbotton);
        MvRockUiComponent.ThumbDownButton.Init();

        MvRockUiComponent.ReportButton.reportSongImage = (ImageView) view.findViewById(R.id.reportbutton);
        MvRockUiComponent.ReportButton.Init();

        MvRockUiComponent.ShareButton.shareSongImage = (ImageView) view.findViewById(R.id.sharebutton);
        MvRockUiComponent.ShareButton.Init();


        MvRockUiComponent.RightFloatingMenu.Init();


        MvRockUiComponent.MvRockDrawer.mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        MvRockUiComponent.MvRockDrawer.leftFragment = (FrameLayout) view.findViewById(R.id.left_drawer);
        MvRockUiComponent.MvRockDrawer.rightFragment = (FrameLayout) view.findViewById(R.id.right_drawer);
        MvRockUiComponent.MvRockDrawer.frame = (FrameLayout) view.findViewById(R.id.content_frame);
        MvRockUiComponent.MvRockDrawer.Init();

        MvRockUiComponent.commentView.commentNumber = (TextView) view.findViewById(R.id.comment_nummber);
        MvRockUiComponent.commentView.textInput = (EditText) view.findViewById(R.id.comment_input);
        MvRockUiComponent.commentView.userAvatar = (ImageView) view.findViewById(R.id.user_avatar);
        MvRockUiComponent.commentView.Init();

        MvRockUiComponent.StationCancelButton.stationCancelImage = (ImageView) view.findViewById(R.id.station_cancel);
        MvRockUiComponent.StationCancelButton.Init();

        MvRockUiComponent.StationListView.StationListview = (ListView) view.findViewById(R.id.station_suggestion);
        MvRockUiComponent.StationListView.Init();


        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        i(TAG, "onOptionsItemSelected()");
        if (MvRockUiComponent.LeftDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.ALL:
                ChangeLanguageByThread(3);
                break;
            case R.id.ENG:
                ChangeLanguageByThread(1);
                break;
            case R.id.CHN:
                ChangeLanguageByThread(2);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        i(TAG, "onCreateOptionsMenu()");
        inflater.inflate(R.menu.main, menu);
        MvRockUiComponent.StationSearchView = new StationSearchView();
        MvRockUiComponent.StationSearchView.topSearchView = (SearchView) menu.findItem(R.id.search_stations)
                .getActionView();
        MvRockUiComponent.StationSearchView.Init();
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void ChangeLanguageByThread(int lang) {
        i(TAG, "ChangeLanguageByThread(" + lang + ")");
        Thread changeLanguageByThread = new Thread(new ChangeLanguageThread(lang, MvRockModel.User.User_Id));
        changeLanguageByThread.start();
        try {
            changeLanguageByThread.join();
        } catch (InterruptedException e1) {

            e1.printStackTrace();
        }
        MvRockUiComponent.YouMayLikePlayListView.RefreshListView();
    }

    public void onResume() {
        super.onResume();
        i(TAG, "onResume()");
        Session.getActiveSession();

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        i(TAG, "onPrepareOptionsMenu()");
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        i(TAG, "onConfigurationChanged()");
        super.onConfigurationChanged(newConfig);
        MvRockUiComponent.LeftDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        i(TAG, "onSaveInstanceState()");
        MvRockModel.CurrentSong.currentTime = MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.getCurrentTimeMillis();
    }

    @Override
    public void onViewStateRestored(Bundle state) {
        super.onViewStateRestored(state);
        i(TAG, "onViewStateRestored()");
        if (MvRockUiComponent.MvRockYoutubePlayer.isReady)
            MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer
                    .cueVideo(MvRockModel.CurrentSong.url, MvRockModel.CurrentSong.currentTime);
    }

}
