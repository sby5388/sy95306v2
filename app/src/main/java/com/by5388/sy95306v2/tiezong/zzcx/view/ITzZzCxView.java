package com.by5388.sy95306v2.tiezong.zzcx.view;

import android.graphics.Bitmap;

import com.by5388.sy95306v2.base.IBaseView;
import com.by5388.sy95306v2.bean.IRemainingTicket;

import java.util.List;

/**
 * @author by5388  on 2018/8/19.
 */
public interface ITzZzCxView extends IBaseView {
    /**
     * 车次列表
     *
     * @param dataBeans 车次列表
     */
    void updateList(List<IRemainingTicket> dataBeans);

    /**
     * 更新验证码图片
     *
     * @param bitmap 图片
     */
    void updateCheckCodeBitmap(Bitmap bitmap);

    /**
     * 验证码是否正确
     *
     * @param isChecked 正确
     */
    void checkPassCode(boolean isChecked);

    /**
     * 清除验证码
     */
    void clearPassCode();
}
