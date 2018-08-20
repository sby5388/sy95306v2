package com.by5388.sy95306v2.activity.trainList;

import android.view.View;

/**
 * @author by5388  on 2018/7/7.
 */

public interface TrainOnClickListener {
    /**
     * RecyclerView点击事件
     *
     * @param view
     * @param position 位置
     */
    void onItemClickListener(View view, int position);
}
