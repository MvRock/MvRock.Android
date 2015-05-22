package com.mvrock.android.view;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.examples.youtubeapidemo.R;
import com.facebook.Session;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayerView;
import com.mvrock.android.model.MvRockModel;
import com.mvrock.android.uicomponent.MvRockUiComponent;
import com.mvrock.android.thread.ChangeLanguageThread_old;
import com.mvrock.android.thread.CreateStationThread_old;
import com.mvrock.android.thread.FacebookLogoutThread_old;
import com.mvrock.android.thread.GetSearchStationThread;
import com.mvrock.android.thread.GetStationThread;
import com.mvrock.android.uicomponent.drawer.ExpandableListAdapter;
import com.mvrock.android.uicomponent.player.MvRockYoutubePlayerFragment;
import com.mvrock.android.uicomponent.player.NextSongButton;
import com.mvrock.android.uicomponent.player.ReportButton;
import com.mvrock.android.uicomponent.player.ShareButton;
import com.mvrock.android.uicomponent.player.ThumbDownButton;
import com.mvrock.android.uicomponent.player.ThumbUpButton;
import com.mvrock.android.uicomponent.playlist.YouLikedPlayListView;
import com.mvrock.android.uicomponent.playlist.YouMayLikePlayListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.util.Log.*;

/**
 * A simple YouTube Android API demo application which shows how to create a
 * simple application that displays a YouTube Video in a
 * {@link YouTubePlayerView}.
 * <p/>
 * Note, to use a {@link YouTubePlayerView}, your activity must extend
 * {@link YouTubeBaseActivity}.
 */


@SuppressWarnings("deprecation")
public class MvRockFragment extends Fragment {
	private static final String TAG = "View.MvRockFragment";
	private static final int NOW_SHOWING_POSITION = 0;
	private static final int MY_STATION_POSITION = 1;
    private static final int LOGOUT_POSITION = 2;
    private static final int FBLOGIN_FRAG = 0;

    private ArrayList<String> leftDrawerDirectory;
	private HashMap<String, List<String>> leftDrawerChildDirectory;
	private ExpandableListAdapter leftDrawerListAdapter;
    private ListView stationListview;
    private ExpandableListView leftDrawerListview;
	private ImageView stationCancelImage;
	private SearchView topSearchView;
	private TabHost tabhost;
	private DrawerLayout leftDrawerLayout;
	private ActionBarDrawerToggle leftDrawerToggle;

    private MainActivity MainActivity;
    public View view;

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		i(TAG, "onActivityResult()");
	    super.onActivityResult(requestCode, resultCode, data);
	}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity = (MainActivity) getActivity();
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView()");
        view = inflater.inflate(R.layout.mvrock_home,container, false);

		tabhost = (TabHost) view.findViewById(R.id.tabhost);
        stationCancelImage = (ImageView) view.findViewById(R.id.station_cancel);
        stationCancelImage.setVisibility(View.INVISIBLE);
		stationListview = (ListView) view.findViewById(R.id.station_suggestion);
		stationListview.setVisibility(View.INVISIBLE);

		//load tab host.
		tabhost.setup();
		TabSpec tabspec = tabhost.newTabSpec("You May Like");
		tabspec.setContent(R.id.tab1);
		tabspec.setIndicator("You May Like");
		tabhost.addTab(tabspec);
		tabspec = tabhost.newTabSpec("You Liked");
		tabspec.setContent(R.id.tab2);
		tabspec.setIndicator("You Liked");
		tabhost.addTab(tabspec);

		Session.setActiveSession(MvRockModel.User.Session);
        MvRockUiComponent.MvRockYoutubePlayer = MvRockYoutubePlayerFragment.newInstance("video_id");
        MvRockUiComponent.MvRockYoutubePlayer.init();
        MainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.youtubeplayerfragment, MvRockUiComponent.MvRockYoutubePlayer).commit();

        MvRockUiComponent.YouMayLikePlayListView = new YouMayLikePlayListView(MainActivity);
        MvRockUiComponent.YouMayLikePlayListView.playListview=(ListView) view.findViewById(R.id.youmaylike);
        MvRockUiComponent.YouMayLikePlayListView.Init();

        MvRockUiComponent.YouLikedPlayListView = new YouLikedPlayListView(MainActivity);
        MvRockUiComponent.YouLikedPlayListView.playListview = (ListView) view.findViewById(R.id.youliked);
        MvRockUiComponent.YouLikedPlayListView.Init();

        MvRockUiComponent.NextSongButton= new NextSongButton();
        MvRockUiComponent.NextSongButton.nextSongImage = (ImageView) view.findViewById(R.id.nextbutton);
        MvRockUiComponent.NextSongButton.Init();

        MvRockUiComponent.ThumbUpButton= new ThumbUpButton();
        MvRockUiComponent.ThumbUpButton.likeSongImage = (ImageView) view.findViewById(R.id.thumbupbutton);
        MvRockUiComponent.ThumbUpButton.Init();

        MvRockUiComponent.ThumbDownButton=new ThumbDownButton();
        MvRockUiComponent.ThumbDownButton.dislikeSongImage = (ImageView) view.findViewById(R.id.thumbdownbotton);
        MvRockUiComponent.ThumbDownButton.Init();

        MvRockUiComponent.ReportButton=new ReportButton();
        MvRockUiComponent.ReportButton.reportSongImage = (ImageView) view.findViewById(R.id.reportbutton);
        MvRockUiComponent.ReportButton.Init();

        MvRockUiComponent.ShareButton=new ShareButton();
        MvRockUiComponent.ShareButton.shareSongImage = (ImageView) view.findViewById(R.id.sharebutton);
        MvRockUiComponent.ShareButton.Init();



		/********************************************************************
		 *****************************4. Station ****************************
		 ********************************************************************/
		RequestStationByThread();

		stationCancelImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                i("stationCancelImage", "onClick()");
                TextView tab_tv = (TextView) tabhost.getTabWidget().getChildAt(0)
                        .findViewById(android.R.id.title);
                tab_tv.setText("You May Like");
                stationCancelImage.setVisibility(View.INVISIBLE);
                MvRockUiComponent.YouMayLikePlayListView.Init();
            }
        });

		stationListview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,int i, long l) {
                i("stationListview", "onItemClick()");
                TextView tab_tv = (TextView) tabhost.getTabWidget().getChildAt(0)
                        .findViewById(android.R.id.title);

                tab_tv.setText(MvRockModel.SearchStationResultList[i]);
                CreateStationByThread(MvRockModel.SearchStationResultList[i]);
                MvRockUiComponent.YouMayLikePlayListView.playListview=null;
                MvRockUiComponent.StationPlayListView.playListview = (ListView) view.findViewById(R.id.youmaylike);
                MvRockUiComponent.StationPlayListView.Init();
                topSearchView.onActionViewCollapsed();
                stationListview.setVisibility(View.INVISIBLE);
                stationCancelImage.setVisibility(View.VISIBLE);
                RequestStationByThread();
                leftDrawerChildDirectory.put(leftDrawerDirectory.get(MY_STATION_POSITION), MvRockModel.StationList.stationArrayList);
                leftDrawerListAdapter = new ExpandableListAdapter(MainActivity,
                        leftDrawerDirectory, leftDrawerChildDirectory);
                leftDrawerListview.setAdapter(leftDrawerListAdapter);
            }
        });


		/********************************************************************
		 **************************5. Set left drawer************************
		 ********************************************************************/
		//取得user_name之后再做
        leftDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        leftDrawerListview = (ExpandableListView) view.findViewById(R.id.left_drawer);
		leftDrawerDirectory = new ArrayList<String>();
		leftDrawerChildDirectory = new HashMap<String, List<String>>();
		leftDrawerDirectory.add("Now Showing");
		leftDrawerDirectory.add(MvRockModel.User.User_Name + "'s Stations");
		leftDrawerDirectory.add("logout");
		leftDrawerChildDirectory.put(leftDrawerDirectory.get(MY_STATION_POSITION), MvRockModel.StationList.stationArrayList);
		leftDrawerListAdapter = new ExpandableListAdapter(MainActivity, leftDrawerDirectory, leftDrawerChildDirectory);
		leftDrawerListview.setAdapter(leftDrawerListAdapter);
		leftDrawerListview.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                i("leftDrawerListview", "onChildClick(" + groupPosition + ")");
                if (groupPosition == MY_STATION_POSITION) {
                    MvRockModel.CurrentStation = leftDrawerChildDirectory.get(
                            leftDrawerDirectory.get(MY_STATION_POSITION)).get(childPosition);
                    MvRockUiComponent.StationPlayListView.playListview = (ListView) view.findViewById(R.id.youmaylike);
                    MvRockUiComponent.StationPlayListView.Init();
                    TextView tab_tv = (TextView) tabhost.getTabWidget()
                            .getChildAt(0).findViewById(android.R.id.title);
                    tab_tv.setText(MvRockModel.CurrentStation);
                    stationCancelImage.setVisibility(View.VISIBLE);
                    leftDrawerLayout.closeDrawers();
                }
                return false;
            }
        });

		leftDrawerListview.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,int groupPosition, long id) {
                i("leftDrawerListview", "onGroupClick(" + groupPosition + ")");
                if (groupPosition == NOW_SHOWING_POSITION)
                    return true;
                else if (groupPosition == LOGOUT_POSITION) {
                    Session session = new Session(MainActivity);
                    Session.setActiveSession(session);
                    session.close();
                    session.closeAndClearTokenInformation();
                    Session.setActiveSession(null);
                    FBLogoutByThread();
                    MainActivity.showFragment(FBLOGIN_FRAG,false);
                    return true;
                }
                return false;
            }
        });

		leftDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        MainActivity.getActionBar().setDisplayHomeAsUpEnabled(true);
        MainActivity.getActionBar().setHomeButtonEnabled(true);
        MainActivity.getActionBar().setTitle("MvRock");
		leftDrawerToggle = new LeftDrawerToggle(MainActivity, leftDrawerLayout, R.drawable.ic_drawer,
				R.string.drawer_open, R.string.drawer_close /* "close drawer" description for accessibility */);
		leftDrawerLayout.setDrawerListener(leftDrawerToggle);

    return view;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		i(TAG, "onOptionsItemSelected()");
		if (leftDrawerToggle.onOptionsItemSelected(item)) {
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
	public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
		i(TAG, "onCreateOptionsMenu()");
		inflater.inflate(R.menu.main, menu);
		topSearchView = (SearchView) menu.findItem(R.id.search_stations)
				.getActionView();
		topSearchView.setQueryHint("Search Stations");
		topSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
					public boolean onQueryTextChange(String s) {
						return false;
					}
					public boolean onQueryTextSubmit(String s) {
						i(TAG, "onQueryTextSubmit()");
						RequestSearchStationResultByThread();
						return true;
					}
				});
		super.onCreateOptionsMenu(menu,inflater);
	}
	
	private final class LeftDrawerToggle extends
	ActionBarDrawerToggle {
		private static final String TAG = "LeftDrawerToggle";
	private LeftDrawerToggle(Activity activity,
                             DrawerLayout drawerLayout, int drawerImageRes,
                             int openDrawerContentDescRes, int closeDrawerContentDescRes) {
	super(activity, drawerLayout, drawerImageRes,
			openDrawerContentDescRes, closeDrawerContentDescRes);
	}

	public void onDrawerClosed(View view) {
	i(TAG, "onDrawerClosed()");
        MvRockUiComponent.MvRockYoutubePlayer.YouTubePlayer.play();
        MainActivity.invalidateOptionsMenu();
	}

	public void onDrawerOpened(View drawerView) {
		i(TAG, "onDrawerOpened()");
		MainActivity.getActionBar().setTitle("MvRock");
        MainActivity.invalidateOptionsMenu();
		}
	}
	
	public void FBLogoutByThread(){
		i(TAG, "FBLogoutByThread()");
		Thread facebookLogoutThread= new Thread(new FacebookLogoutThread_old(MainActivity));
        facebookLogoutThread.start();
		try {
            facebookLogoutThread.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	public void RequestStationByThread(){
		i(TAG, "RequestStationByThread()");
        GetStationThread getStationThread = new GetStationThread(MvRockModel.User.User_Id,"");
		getStationThread.start();
		try {
			getStationThread.join();
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}
        getStationThread.setResponse();
	}

	
	public void RequestSearchStationResultByThread(){
		i(TAG, "RequestSearchStationResultByThread()");
		Thread getRecStationThread = new Thread(
				new GetSearchStationThread(MvRockModel.User.User_Id,String.valueOf(topSearchView.getQuery())));
		getRecStationThread.start();
		try {
			getRecStationThread.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		MvRockModel.SearchStationResultList = GetSearchStationThread.getArrayRecStations();
		stationListview.setAdapter(new ArrayAdapter<String>(MainActivity, android.R.layout.simple_list_item_1,MvRockModel.SearchStationResultList));
		stationListview.setVisibility(View.VISIBLE);
	}

	public void CreateStationByThread(String stationName){
		i(TAG, "CreateStationByThread(" + stationName + ")");
		Thread createStationByThread = new Thread(new CreateStationThread_old(stationName, MvRockModel.User.User_Id));
		createStationByThread.start();
		try {
			createStationByThread.join();
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}
	}
	
	public void ChangeLanguageByThread(int lang){
		i(TAG, "ChangeLanguageByThread(" + lang + ")");
		Thread changeLanguageByThread = new Thread(new ChangeLanguageThread_old(lang, MvRockModel.User.User_Id));
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
        leftDrawerToggle.onConfigurationChanged(newConfig);
    }

}
