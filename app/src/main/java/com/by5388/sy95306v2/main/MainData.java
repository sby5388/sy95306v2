package com.by5388.sy95306v2.main;

import android.support.v4.app.Fragment;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.module.chengdu.ChengduFragment;
import com.by5388.sy95306v2.module.guangzhou.GuangzhouFragment;
import com.by5388.sy95306v2.module.shanghai.ShanghaiFragment;
import com.by5388.sy95306v2.module.shenyang.ShenYangFragment;
import com.by5388.sy95306v2.module.tiezong.TzFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by5388  on 2019/1/5.
 */
class MainData {
    private static final int TITLE_SHEN_YANG = R.string.title_shen_yang;
    private static final int TITLE_TZ = R.string.title_tie_zhong;
    private static final int TITLE_CD = R.string.title_cheng_du;
    private static final int TITLE_GZ = R.string.title_guang_zhou;
    private static final int TITLE_SH = R.string.title_shang_hai;

    /**
     * 初始化Fragment
     */
    static void initFragmentData(List<Fragment> fragments) {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        if (fragments.isEmpty()) {
            fragments.add(ShenYangFragment.newInstance());
            fragments.add(GuangzhouFragment.newInstance());
            fragments.add(ShanghaiFragment.newInstance());
            fragments.add(TzFragment.newInstance());
            fragments.add(ChengduFragment.newInstance());
        }
    }

    /**
     * 初始化Fragment的标题
     */
    static void initTabTitles(List<Integer> titles) {
        if (titles == null) {
            titles = new ArrayList<>();
        }
        if (titles.isEmpty()) {
            titles.add(TITLE_SHEN_YANG);
            titles.add(TITLE_GZ);
            titles.add(TITLE_SH);
            titles.add(TITLE_TZ);
            titles.add(TITLE_CD);
        }
    }
}
