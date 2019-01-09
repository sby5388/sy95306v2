package com.by5388.sy95306v2.shanghai;

import android.os.Bundle;

import com.by5388.sy95306v2.base.BaseTopFragment;
import com.by5388.sy95306v2.base.adapter.MyViewPagerAdapter;
import com.by5388.sy95306v2.shanghai.number.ShanghaiNumberFragment;
import com.by5388.sy95306v2.shanghai.p2p.ShanghaiP2pFragment;

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
    protected void initFragments(MyViewPagerAdapter adapter) {
        adapter.addFragment(ShanghaiP2pFragment.newInstance(), "车次查询");
        adapter.addFragment(ShanghaiNumberFragment.newInstance(), "车次查询");
    }

}
