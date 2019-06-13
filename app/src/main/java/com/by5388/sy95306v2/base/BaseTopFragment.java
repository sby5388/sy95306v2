package com.by5388.sy95306v2.base;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.base.adapter.FragmentAdapter;
import com.by5388.sy95306v2.bean.MyViewPager;

import java.util.List;

/**
 * 沈阳95306 查询页面：包括2个子Fragment
 *
 * @author by5388  on 2018/7/28.
 */

public abstract class BaseTopFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    protected FragmentAdapter adapter;

    @Override
    protected final void initData() {
        //getChildFragmentManager
        adapter = new FragmentAdapter(getChildFragmentManager(), getViewPagerFragmentList());
    }

    /**
     * 数据源
     *
     * @return
     */
    protected abstract List<MyViewPager> getViewPagerFragmentList();


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
