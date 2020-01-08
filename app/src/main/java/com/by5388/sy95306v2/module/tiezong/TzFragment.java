package com.by5388.sy95306v2.module.tiezong;

import android.os.Bundle;

import com.by5388.sy95306v2.base.BaseTopFragment;
import com.by5388.sy95306v2.bean.MyViewPager;
import com.by5388.sy95306v2.module.tiezong.combination.CombinationFragment;
import com.by5388.sy95306v2.module.tiezong.zzcx.ZzCxFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 铁总查询页面：包括2个子Fragment
 *
 * @author by5388  on 2018/7/28.
 */

public class TzFragment extends BaseTopFragment {
    public TzFragment() {
    }


    public static TzFragment newInstance() {
        TzFragment fragment = new TzFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected List<MyViewPager> getViewPagerFragmentList() {
        List<MyViewPager> list = new ArrayList<>();
        list.add(new MyViewPager(ZzCxFragment.newInstance(), "余票查询"));
        list.add(new MyViewPager(CombinationFragment.newInstance(), "联合查询"));
        list.add(new MyViewPager(TrainCodeQueryFragment.newInstance(), "车次查询"));
        return list;
    }
}
