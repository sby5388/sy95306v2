package com.by5388.t12306.api.vague;

import com.by5388.t12306.api.BaseNetTool;
import com.by5388.t12306.api.vague.bean.SimpleTrainCode;
import com.by5388.t12306.api.vague.bean.VagueQueryResult;

import org.junit.Test;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author by5388  on 2019/1/24.
 */
public class VagueTest {
    @Test
    public void testVague() {
        BaseNetTool tool = new VagueNetTool();
        VagueService service = tool.getRetrofit().create(VagueService.class);
        final String keyWord = "D2288";
        final String date = "20190526";
        service.getSimpleTrainCodeList(keyWord, date)
                .subscribe(new Observer<VagueQueryResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VagueQueryResult vagueQueryResult) {
                        if (vagueQueryResult == null || !vagueQueryResult.isStatus()) {
                            System.err.println("没有数据");
                            return;
                        }
                        List<SimpleTrainCode> simpleBeanList = vagueQueryResult.getSimpleBeanList();
                        if (simpleBeanList == null || simpleBeanList.isEmpty()) {
                            System.err.println(vagueQueryResult.getErrorMsg());
                            return;
                        }
                        for (SimpleTrainCode code : simpleBeanList) {
                            System.out.println(code.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.err.println(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("完成");
                    }
                });
    }
}
