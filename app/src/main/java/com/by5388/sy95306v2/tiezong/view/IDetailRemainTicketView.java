package com.by5388.sy95306v2.tiezong.view;

import android.graphics.Bitmap;

import com.by5388.sy95306v2.base.IBaseView;
import com.by5388.sy95306v2.tiezong.bean.yp.success.TzDataBean;

import java.util.List;

/**
 * @author by5388  on 2018/8/19.
 */
public interface IDetailRemainTicketView extends IBaseView {

    String TAG = "TzRemainTicket";
    String KEY_BUNDLE = "bundle";
    String KEY_TRAIN_CODE = "trainCode";
    String KEY_DATE = "date";
    String KEY_FROM = "fromStation";
    String KEY_TO = "toStation";
    String KEY_RAND_CODE = "randCode";


    /**
     * 筛选对话框
     */
    void showFilterDialog();

    /**
     * 显示齐全的余票信息
     *
     * @param bean 余票信息
     */
    void addRemainingTicket(TzDataBean bean);

    /**
     * 车次列表
     *
     * @param dataBeans 车次列表
     */
    void updateList(List<TzDataBean> dataBeans);

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
