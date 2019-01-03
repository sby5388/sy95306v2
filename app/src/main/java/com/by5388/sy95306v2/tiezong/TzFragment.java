package com.by5388.sy95306v2.tiezong;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.by5388.sy95306v2.base.BaseFragment;
import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.adapter.MyViewPagerAdapter;
import com.by5388.sy95306v2.tiezong.combination.CombinationFragment;
import com.by5388.sy95306v2.tiezong.remainticket.RemainTicketFragment;
import com.by5388.sy95306v2.tiezong.zzcx.ZzCxFragment;

/**
 * 沈阳95306 查询页面：包括2个子Fragment
 *
 * @author by5388  on 2018/7/28.
 */

public class TzFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyViewPagerAdapter adapter;


    public TzFragment() {
    }


    public static TzFragment newInstance() {
        TzFragment fragment = new TzFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        adapter = new MyViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(ZzCxFragment.newInstance(), "中转查询");
        adapter.addFragment(RemainTicketFragment.newInstance(), "余票查询2");
        adapter.addFragment(CombinationFragment.newInstance(), "联合查询");
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
