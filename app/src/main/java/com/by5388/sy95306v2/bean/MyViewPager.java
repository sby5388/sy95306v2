package com.by5388.sy95306v2.bean;

import android.support.v4.app.Fragment;

/**
 * @author by5388  on 2018/8/2.
 */

public class MyViewPager {
    private Fragment fragments;
    private String titles;

    public MyViewPager(Fragment fragments, String titles) {
        this.fragments = fragments;
        this.titles = titles;
    }

    public Fragment getFragments() {
        return fragments;
    }

    public void setFragments(Fragment fragments) {
        this.fragments = fragments;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }
}
