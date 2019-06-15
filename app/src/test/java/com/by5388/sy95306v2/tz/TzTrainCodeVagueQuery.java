package com.by5388.sy95306v2.tz;

import com.by5388.sy95306v2.base.BaseNetTools;
import com.by5388.sy95306v2.module.tiezong.api.train.code.TzTrainCodeNetTools;
import com.by5388.sy95306v2.module.tiezong.api.train.code.TzTrainCodeService;
import com.by5388.sy95306v2.module.tiezong.bean.temp2.DataSimpleBean;
import com.by5388.sy95306v2.module.tiezong.bean.temp2.ResultQueryTrainCode;

import org.junit.Test;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 模糊
 *
 * @author by5388  on 2019/1/22.
 */
public class TzTrainCodeVagueQuery {
    @Test
    public void testVague() {
        BaseNetTools tools = new TzTrainCodeNetTools();
        TzTrainCodeService service = tools.getRetrofit().create(TzTrainCodeService.class);
        final String keyWord = "D2283";
        final String date = "20190615";
        service.getSimpleTrainCodeList(keyWord, date)
                .subscribe(new Observer<ResultQueryTrainCode>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultQueryTrainCode resultQueryTrainCode) {
                        show(resultQueryTrainCode);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.err.println(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void show(ResultQueryTrainCode resultQueryTrainCode) {
        if (resultQueryTrainCode == null) {
            showError(0);
            return;
        }
        if (!resultQueryTrainCode.isStatus()) {
            showError(1);
            return;
        }
        List<DataSimpleBean> simpleBeanList = resultQueryTrainCode.getSimpleBeanList();
        if (simpleBeanList == null || simpleBeanList.isEmpty()) {
            showError(2);
            return;
        }
        for (DataSimpleBean bean : simpleBeanList) {
            System.out.println(bean.getFromStation() + " " + bean.getToStation() + " " + bean.getTrainCodeSimple());
//            System.out.println(bean.getFromStation() + " " + bean.getToStation() + " " + bean.getTrainCodeSimple());
            System.out.println(bean.getTrainCodeFull());
        }
    }

    private void showError(int error) {
        System.err.println(error);
    }
}
