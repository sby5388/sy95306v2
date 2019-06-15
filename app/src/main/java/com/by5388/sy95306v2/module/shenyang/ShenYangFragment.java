package com.by5388.sy95306v2.module.shenyang;

import android.os.Bundle;

import com.by5388.sy95306v2.base.BaseTopFragment;
import com.by5388.sy95306v2.bean.MyViewPager;
import com.by5388.sy95306v2.module.shenyang.temp.PlusOneFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 沈阳95306 查询页面：包括2个子Fragment
 *
 * @author by5388  on 2018/7/28.
 */

public class ShenYangFragment extends BaseTopFragment {

    public static ShenYangFragment newInstance() {
        ShenYangFragment fragment = new ShenYangFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected List<MyViewPager> getViewPagerFragmentList() {
        List<MyViewPager> list = new ArrayList<>();
        list.add(new MyViewPager(Station2StationFragment.newInstance(), "站站查询"));
        list.add(new MyViewPager(SyTrainNumberFragment.newInstance(), "车次查询"));
        list.add(new MyViewPager(PlusOneFragment.newInstance("",""), "临时"));
        return list;
    }
}
