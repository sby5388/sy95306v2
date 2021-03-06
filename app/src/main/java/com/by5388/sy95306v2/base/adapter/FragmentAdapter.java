package com.by5388.sy95306v2.base.adapter;

import com.by5388.sy95306v2.bean.MyViewPager;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

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