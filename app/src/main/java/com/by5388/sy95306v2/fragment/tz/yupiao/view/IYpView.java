package com.by5388.sy95306v2.fragment.tz.yupiao.view;

import com.by5388.sy95306v2.bean.IYp;

import java.util.List;

/**
 * TODO 接口类有待统一
 *
 * @author by5388  on 2018/8/13.
 */
public interface IYpView {
    void startQuery();

    void finishQuery();

    void showError(String tip);

    void updateDate(List<IYp> yuPiaoData);
}
