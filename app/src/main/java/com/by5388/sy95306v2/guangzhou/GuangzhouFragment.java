package com.by5388.sy95306v2.guangzhou;

import android.os.Bundle;

import com.by5388.sy95306v2.adapter.MyViewPagerAdapter;
import com.by5388.sy95306v2.base.BaseTopFragment;
import com.by5388.sy95306v2.guangzhou.late.GzLateFragment;
import com.by5388.sy95306v2.guangzhou.number.GzTrainNumberFragment;
import com.by5388.sy95306v2.guangzhou.p2p.GzP2pFragment;
import com.by5388.sy95306v2.guangzhou.screen.StationScreenFragment;

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
    protected void initFragments(MyViewPagerAdapter adapter) {
        adapter.addFragment(GzP2pFragment.newInstance(), "站站查询");
        adapter.addFragment(GzTrainNumberFragment.newInstance(), "车次查询");
        adapter.addFragment(StationScreenFragment.newInstance(), "车站大屏幕");
        adapter.addFragment(GzLateFragment.newInstance(), "正晚点查询");
    }
}
