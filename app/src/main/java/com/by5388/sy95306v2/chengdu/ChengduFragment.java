package com.by5388.sy95306v2.chengdu;

import android.os.Bundle;

import com.by5388.sy95306v2.base.BaseTopFragment;
import com.by5388.sy95306v2.bean.MyViewPager;
import com.by5388.sy95306v2.chengdu.late.CdLateFragment;
import com.by5388.sy95306v2.chengdu.screen.CdScreenFragment;
import com.by5388.sy95306v2.chengdu.yupiao.CdRemainTicketFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 成都 查询页面：包括3个子Fragment
 *
 * @author by5388  on 2018/7/28.
 */

public class ChengduFragment extends BaseTopFragment {
    public static ChengduFragment newInstance() {
        ChengduFragment fragment = new ChengduFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected List<MyViewPager> getViewPagerFragmentList() {
        List<MyViewPager> list = new ArrayList<>();
        list.add(new MyViewPager(CdScreenFragment.newInstance(), "车站大屏幕"));
        list.add(new MyViewPager(CdRemainTicketFragment.newInstance(), "余票查询"));
        list.add(new MyViewPager(CdLateFragment.newInstance(), "晚点查询"));
        return list;
    }
}
