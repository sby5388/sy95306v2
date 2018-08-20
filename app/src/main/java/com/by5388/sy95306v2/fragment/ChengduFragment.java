package com.by5388.sy95306v2.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.adapter.MyViewPagerAdapter;
import com.by5388.sy95306v2.fragment.cd.late.CdLateFragment;
import com.by5388.sy95306v2.fragment.cd.screen.CdScreenFragment;
import com.by5388.sy95306v2.fragment.cd.yupiao.CdYpFragment;

/**
 * 沈阳95306 查询页面：包括2个子Fragment
 *
 * @author by5388  on 2018/7/28.
 */

public class ChengduFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyViewPagerAdapter adapter;


    public ChengduFragment() {
    }


    public static ChengduFragment newInstance() {
        ChengduFragment fragment = new ChengduFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        adapter = new MyViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(CdScreenFragment.newInstance(), "车站大屏幕");
        adapter.addFragment(CdYpFragment.newInstance(), "余票查询");
        adapter.addFragment(CdLateFragment.newInstance(), "晚点查询");
        // adapter.addFragment(YuPiaoFragment.newInstance(), "余票查询2");
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
