package com.by5388.sy95306v2.fragment.cd.screen;

/**
 * @author by5388  on 2018/8/18.
 */
public interface CdScreenItem {
    /**
     * 车次
     * 1
     *
     * @return 车次
     */
    String getTrainCode();

    /**
     * 始发
     * 2
     *
     * @return 始发
     */
    String getStartStation();

    /**
     * 终到
     * 3
     *
     * @return 终到
     */
    String getEndStation();

    /**
     * 显示时刻
     * 4
     *
     * @return 显示时刻
     */
    String getTime();

    /**
     * 检票口或者出站口
     * 5
     *
     * @return 位置
     */
    String getPlace();

    /**
     * 正点 、晚点、早点
     * 6
     *
     * @return 状态
     */
    String getStatus();
}
