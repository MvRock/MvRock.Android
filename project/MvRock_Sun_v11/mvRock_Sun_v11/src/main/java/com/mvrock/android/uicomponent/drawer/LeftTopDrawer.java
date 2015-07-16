package com.mvrock.android.uicomponent.drawer;

/**
 * Created by Xuer on 5/24/15.
 */
public class LeftTopDrawer /*extends MvRockUiComponentObject*/ {
//    public DrawerLayout LeftDrawerLayout;
//    private ArrayList<String> leftDrawerDirectory;
//    private HashMap<String, List<String>> leftDrawerChildDirectory;
//    private ExpandableListAdapter leftDrawerListAdapter;
//    public ExpandableListView LeftDrawerListview;
//
//    public LeftTopDrawer(){
//        TAG+="LeftTopDrawer";
//    }
//    public void Init(){
//        i(TAG,"Init()");
//        LeftDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
//        MvRockUiComponent.LeftDrawerToggle = new LeftDrawerToggle(MvRockView.MainActivity, LeftDrawerLayout, R.drawable.ic_drawer,
//                R.string.drawer_open, R.string.drawer_close /* "close drawer" description for accessibility */);
//        LeftDrawerLayout.setDrawerListener(MvRockUiComponent.LeftDrawerToggle);
//
//        leftDrawerDirectory = new ArrayList<String>();
//        leftDrawerDirectory.add("Now Showing");
//        leftDrawerDirectory.add(MvRockModel.User.User_Name + "'s Stations");
//        leftDrawerDirectory.add("logout");
//
//        leftDrawerChildDirectory = new HashMap<String, List<String>>();
//        leftDrawerChildDirectory.put(leftDrawerDirectory.get(MvRockView.MY_STATION_POSITION), MvRockModel.StationList.searchStationArrayList);
//        leftDrawerListAdapter = new ExpandableListAdapter(MvRockView.MainActivity, leftDrawerDirectory, leftDrawerChildDirectory);
//        LeftDrawerListview.setAdapter(leftDrawerListAdapter);
//
//        LeftDrawerListview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                i(TAG, "onChildClick(" + groupPosition + ")");
//                if (groupPosition == MvRockView.MY_STATION_POSITION) {
//                    MvRockModel.CurrentStation = leftDrawerChildDirectory.get(
//                            leftDrawerDirectory.get(MvRockView.MY_STATION_POSITION)).get(childPosition);
////                    MvRockUiComponent.StationPlayListView.playListview = (ListView) v.findViewById(R.id.youmaylike);
//                    MvRockUiComponent.StationPlayListView.Init();
//                    TextView tab_tv = (TextView) MvRockUiComponent.MvRockTabHost.TabHost.getTabWidget()
//                            .getChildAt(0).findViewById(android.R.id.title);
//                    tab_tv.setText(MvRockModel.CurrentStation);
//                    MvRockUiComponent.StationCancelButton.stationCancelImage.setVisibility(View.VISIBLE);
//                    LeftDrawerLayout.closeDrawers();
//                }
//                return false;
//            }
//        });
//
//        LeftDrawerListview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                i(TAG, "onGroupClick(" + groupPosition + ")");
//                if (groupPosition == MvRockView.NOW_SHOWING_POSITION)
//                    return true;
//                else if (groupPosition == MvRockView.LOGOUT_POSITION) {
//                    Session session = new Session(MvRockView.MainActivity);
//                    Session.setActiveSession(session);
//                    session.close();
//                    session.closeAndClearTokenInformation();
//                    Session.setActiveSession(null);
//                    FBLogoutByThread();
//                    MvRockView.MainActivity.showFragment(MvRockView.FBLOGIN_FRAG, false);
//                    return true;
//                }
//                return false;
//            }
//        });
//
//    }
//
//    private void FBLogoutByThread(){
//        i(TAG, "FBLogoutByThread()");
//        Thread facebookLogoutThread= new Thread(new FacebookLogoutThread());
//        facebookLogoutThread.start();
//        try {
//            facebookLogoutThread.join();
//        } catch (InterruptedException e1) {
//            e1.printStackTrace();
//        }
//    }
//
//    public void AddStationList(){
//        i(TAG, "AddStationList()");
//        leftDrawerChildDirectory.put(leftDrawerDirectory.get(MvRockView.MY_STATION_POSITION), MvRockModel.StationList.searchStationArrayList);
//        leftDrawerListAdapter = new ExpandableListAdapter(MvRockView.MainActivity,
//                leftDrawerDirectory, leftDrawerChildDirectory);
//        LeftDrawerListview.setAdapter(leftDrawerListAdapter);
//    }
}
