package com.by5388.sy95306v2.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.adapter.MyViewPagerAdapter;
import com.by5388.sy95306v2.fragment.shenyang.Station2StationFragment;
import com.by5388.sy95306v2.fragment.shenyang.SyTrainNumberFragment;

/**
 * 沈阳95306 查询页面：包括2个子Fragment
 *
 * @author by5388  on 2018/7/28.
 */

public class ShenYangFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyViewPagerAdapter adapter;


    public ShenYangFragment() {
    }


    public static ShenYangFragment newInstance() {
        ShenYangFragment fragment = new ShenYangFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        adapter = new MyViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(Station2StationFragment.newInstance(), "站站");
        adapter.addFragment(SyTrainNumberFragment.newInstance(), "车次1");
    }

    @Override
    protected void loadData() {
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_shen_yang;
    }

    @Override
    protected void initView(View view) {
        viewPager = view.findViewById(R.id.view_pager_shen_yang);
        tabLayout = view.findViewById(R.id.tab_layout_shen_yang);
    }


}
