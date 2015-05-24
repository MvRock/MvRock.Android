package com.mvrock.android.model;

/**
 * Created by Xuer on 5/5/15.
 */
public enum PlayListOption{
    YOU_MAY_LIKE_LIST(0),
    YOU_LIKED_LIST(1),
    STATION_LIST(2);
    private int option;
    private PlayListOption(int option){this.option = option;}

    @Override
    public String toString() {
        String option="";
        switch (this) {
            case YOU_MAY_LIKE_LIST:
                option="YOU_MAY_LIKE_LIST";
                break;
            case YOU_LIKED_LIST:
                option="YOU_LIKED_LIST";
                break;
            case STATION_LIST:
                option="STATION_LIST";
                break;
            default:
                break;
        }
        return option;
    }
}
