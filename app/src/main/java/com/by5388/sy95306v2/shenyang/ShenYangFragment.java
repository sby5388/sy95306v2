package com.by5388.sy95306v2.shenyang;

import android.os.Bundle;

import com.by5388.sy95306v2.base.BaseTopFragment;
import com.by5388.sy95306v2.adapter.MyViewPagerAdapter;

/**
 * 沈阳95306 查询页面：包括2个子Fragment
 *
 * @author by5388  on 2018/7/28.
 */

public class ShenYangFragment extends BaseTopFragment {
    @Override
    protected void initFragments(MyViewPagerAdapter adapter) {
        adapter.addFragment(Station2StationFragment.newInstance(), "站站查询");
        adapter.addFragment(SyTrainNumberFragment.newInstance(), "车次查询");
    }

    public static ShenYangFragment newInstance() {
        ShenYangFragment fragment = new ShenYangFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

}
