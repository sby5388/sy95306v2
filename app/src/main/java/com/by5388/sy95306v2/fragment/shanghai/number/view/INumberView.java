package com.by5388.sy95306v2.fragment.shanghai.number.view;

import com.by5388.sy95306v2.base.view.IBaseView;
import com.by5388.sy95306v2.bean.shanghai.ShanghaiTrainNumber;

import java.util.List;

/**
 * @author by5388  on 2018/8/9.
 */

public interface INumberView  extends IBaseView{
    /**
     * 刷新数据
     *
     * @param numbers 数据
     */
    void updateList(List<ShanghaiTrainNumber> numbers);
}
