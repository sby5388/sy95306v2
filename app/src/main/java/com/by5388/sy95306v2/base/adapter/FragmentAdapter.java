package com.by5388.sy95306v2.base.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.by5388.sy95306v2.bean.MyViewPager;

import java.util.List;

/**
 * TODO  ViewPager 适配器的构造方法、源码、使用方法
 *
 * @author by5388
 * @date 20180727
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {
    private final List<MyViewPager> viewPagers;


    public FragmentAdapter(@NonNull FragmentManager manager, @NonNull List<MyViewPager> viewPagers) {
        super(manager);
        this.viewPagers = viewPagers;
    }


    @Override
    public Fragment getItem(int position) {
        return viewPagers.get(position).getFragments();
    }

    @Override
    public int getCount() {
        return viewPagers.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return viewPagers.get(position).getTitles();
    }
}