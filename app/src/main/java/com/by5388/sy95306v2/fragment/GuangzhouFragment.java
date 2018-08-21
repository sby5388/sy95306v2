package com.by5388.sy95306v2.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.adapter.MyViewPagerAdapter;
import com.by5388.sy95306v2.bean.MyViewPager;
import com.by5388.sy95306v2.fragment.gz.late.GzLateFragment;
import com.by5388.sy95306v2.fragment.gz.p2p.GzP2pFragment;
import com.by5388.sy95306v2.fragment.gz.screen.StationScreenFragment;
import com.by5388.sy95306v2.fragment.gz.number.GzTrainNumberFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 广铁集团的查询页面：2个
 *
 * @author by5388  on 2018/7/28.
 */

public class GuangzhouFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyViewPagerAdapter adapter;

    public static GuangzhouFragment newInstance() {
        GuangzhouFragment fragment = new GuangzhouFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        List<MyViewPager> viewPagers = new ArrayList<>();
        viewPagers.add(new MyViewPager(GzP2pFragment.newInstance(), "站站查询"));
        viewPagers.add(new MyViewPager(GzTrainNumberFragment.newInstance(), "车次查询"));
        viewPagers.add(new MyViewPager(StationScreenFragment.newInstance(), "车站大屏幕"));
        viewPagers.add(new MyViewPager(GzLateFragment.newInstance(), "正晚点查询"));

        adapter = new MyViewPagerAdapter(getChildFragmentManager(), viewPagers);
    }

    @Override
    protected void loadData() {
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_guang_zhou;
    }

    @Override
    protected void initView(View view) {
        viewPager = view.findViewById(R.id.view_pager_guang_zhou);
        tabLayout = view.findViewById(R.id.tab_layout_guang_zhou);
    }

}
