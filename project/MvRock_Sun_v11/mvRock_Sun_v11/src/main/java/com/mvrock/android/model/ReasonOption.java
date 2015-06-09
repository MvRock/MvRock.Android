package com.mvrock.android.model;

/**
 * Created by Tianhao on 15/6/2.
 */
public enum ReasonOption {
    None("0"),
    Random("1"),
    YouLikedBefore("2"),
    Personalized("3");
    private String option;

    private ReasonOption(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        String option = "";
        switch (this) {
            case None:
                option = "None";
                break;
            case Random:
                option = "Random";
                break;
            case YouLikedBefore:
                option = "You Liked Before";
                break;
            case Personalized:
                option = "Personalized";
            default:
                break;
        }
        return option;
    }
}
