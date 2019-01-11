package com.by5388.sy95306v2.step1;

import android.support.annotation.NonNull;

import com.by5388.sy95306v2.shenyang.bean.TrainDetail;
import com.by5388.sy95306v2.other.step1.sy.Step1SyNetTools;
import com.by5388.sy95306v2.other.step1.sy.Step1SyService;

import org.junit.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @author by5388  on 2018/8/31.
 */
public class TestGetTrainCodeDetail {
    @Test
    public void getDetail() {
        Retrofit retrofit = new Step1SyNetTools().getRetrofit();
        Step1SyService service = retrofit.create(Step1SyService.class);
        int date = 20180120;
        String trainCode = "D7414";
        Call<List<TrainDetail>> call = service.getTrainByTrainCode(date, trainCode);
        call.enqueue(new Callback<List<TrainDetail>>() {
            @Override
            public void onResponse(@NonNull Call<List<TrainDetail>> call, @NonNull Response<List<TrainDetail>> response) {
                System.out.println("response");
                System.out.println(Thread.currentThread().getName());

            }

            @Override
            public void onFailure(@NonNull Call<List<TrainDetail>> call, @NonNull Throwable t) {
                System.err.println(t.getLocalizedMessage());
            }
        });
    }
}
