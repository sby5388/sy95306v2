package com.by5388.sy95306v2.base.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.by5388.sy95306v2.bean.MyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO  ViewPager 适配器的构造方法、源码、使用方法
 *
 * @author by5388
 * @date 20180727
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter {
    private final List<MyViewPager> viewPagers;


    public MyViewPagerAdapter(@NonNull FragmentManager manager) {
        super(manager);
        viewPagers = new ArrayList<>();
    }

    public MyViewPagerAdapter(@NonNull FragmentManager manager,@NonNull List<MyViewPager> viewPagers) {
        super(manager);
        this.viewPagers = viewPagers;
    }

    public void addFragment(@NonNull Fragment fragment, @NonNull String title) {
        viewPagers.add(new MyViewPager(fragment, title));
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