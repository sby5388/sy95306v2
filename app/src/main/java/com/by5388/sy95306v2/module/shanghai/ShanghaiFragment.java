package com.by5388.sy95306v2.module.shanghai;

import android.os.Bundle;

import com.by5388.sy95306v2.base.BaseTopFragment;
import com.by5388.sy95306v2.bean.MyViewPager;
import com.by5388.sy95306v2.module.shanghai.number.ShanghaiNumberFragment;
import com.by5388.sy95306v2.module.shanghai.p2p.ShanghaiP2pFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 上海集团的查询页面：2个
 *
 * @author by5388  on 2018/7/28.
 */

public class ShanghaiFragment extends BaseTopFragment {
    public static ShanghaiFragment newInstance() {
        ShanghaiFragment fragment = new ShanghaiFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected List<MyViewPager> getViewPagerFragmentList() {
        List<MyViewPager> list = new ArrayList<>();
        list.add(new MyViewPager(ShanghaiP2pFragment.newInstance(), "车次查询"));
        list.add(new MyViewPager(ShanghaiNumberFragment.newInstance(), "车次查询"));
        return list;
    }
}
