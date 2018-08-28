package com.by5388.sy95306v2.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.adapter.MyViewPagerAdapter;

/**
 * 沈阳95306 查询页面：包括2个子Fragment
 *
 * @author by5388  on 2018/7/28.
 */

public abstract class BaseTopFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyViewPagerAdapter adapter;

    @Override
    protected final void initData() {
        //TODO getChildFragmentManager
        adapter = new MyViewPagerAdapter(getChildFragmentManager());
        initFragments(adapter);
    }

    /**
     * 添加子fragment
     *
     * @param adapter 显示数据
     */
    protected abstract void initFragments(MyViewPagerAdapter adapter);


    @Override
    protected final void loadData() {
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected final int getLayoutID() {
        return R.layout.fragment_shen_yang;
    }

    @Override
    protected final void initView(View view) {
        viewPager = view.findViewById(R.id.view_pager_shen_yang);
        tabLayout = view.findViewById(R.id.tab_layout_shen_yang);
    }
}
