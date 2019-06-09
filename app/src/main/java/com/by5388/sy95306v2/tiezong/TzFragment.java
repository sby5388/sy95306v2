package com.by5388.sy95306v2.tiezong;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.base.BaseFragment;
import com.by5388.sy95306v2.base.BaseTopFragment;
import com.by5388.sy95306v2.base.adapter.FragmentAdapter;
import com.by5388.sy95306v2.bean.MyViewPager;
import com.by5388.sy95306v2.tiezong.combination.CombinationFragment;
import com.by5388.sy95306v2.tiezong.remainticket.RemainTicketFragment;
import com.by5388.sy95306v2.tiezong.zzcx.ZzCxFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 铁总查询页面：包括2个子Fragment
 *
 * @author by5388  on 2018/7/28.
 */

public class TzFragment extends BaseTopFragment {

    @Override
    protected List<MyViewPager> getViewPagerFragmentList() {
        List<MyViewPager> list = new ArrayList<>();
        list.add(new MyViewPager(ZzCxFragment.newInstance(), "余票查询"));
        list.add(new MyViewPager(RemainTicketFragment.newInstance(), "余票查询2"));
        list.add(new MyViewPager(CombinationFragment.newInstance(), "联合查询"));
        // TODO: 2019/1/23 增加其他的车次查询等
        return list;
    }

    public TzFragment() {
    }


    public static TzFragment newInstance() {
        TzFragment fragment = new TzFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

}
