package com.by5388.sy95306v2.module.guangzhou;

import android.os.Bundle;

import com.by5388.sy95306v2.base.BaseTopFragment;
import com.by5388.sy95306v2.bean.MyViewPager;
import com.by5388.sy95306v2.module.guangzhou.late.GzLateFragment;
import com.by5388.sy95306v2.module.guangzhou.number.GzTrainNumberFragment;
import com.by5388.sy95306v2.module.guangzhou.p2p.GzP2pFragment;
import com.by5388.sy95306v2.module.guangzhou.screen.StationScreenFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 广铁集团的查询页面：2个
 *
 * @author by5388  on 2018/7/28.
 */

public class GuangzhouFragment extends BaseTopFragment {
    public static GuangzhouFragment newInstance() {
        GuangzhouFragment fragment = new GuangzhouFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected List<MyViewPager> getViewPagerFragmentList() {
        List<MyViewPager> list = new ArrayList<>();
        list.add(new MyViewPager(GzP2pFragment.newInstance(), "站站查询"));
        list.add(new MyViewPager(GzTrainNumberFragment.newInstance(), "车次查询"));
//        list.add(new MyViewPager(StationScreenFragment.newInstance(), "车站大屏幕"));
        list.add(new MyViewPager(GzLateFragment.newInstance(), "正晚点查询"));
        return list;
    }
}
