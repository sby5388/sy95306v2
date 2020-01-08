package com.by5388.sy95306v2.net.yupiao;

import com.by5388.sy95306v2.bean.IRemainingTicket;
import com.by5388.sy95306v2.bean.thrid.QueryParam;
import com.by5388.sy95306v2.bean.thrid.SecondRemainTicketData;
import com.by5388.sy95306v2.bean.thrid.SecondResult;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;

/**
 * @author by5388  on 2019/6/9.
 */
public class YpServiceTest {
    private YpService mSubject;
    private QueryParam mQueryParam;


    @Before
    public void setUp() throws Exception {
        Retrofit retrofit = new YpNetTools().getRetrofit();
        mSubject = retrofit.create(YpService.class);
        mQueryParam = new QueryParam( "饶平","深圳北", "2020-01-22");
    }

    @Test
    public void getYpMessageTest() {

        mSubject.getYpMessage(mQueryParam.toString(), mQueryParam.getDate())
                .flatMap((Function<SecondResult, ObservableSource<List<SecondRemainTicketData>>>) result -> {
                    if (null == result) {
                        throw new RuntimeException("结果异常");
                    }
                    return Observable.just(getRemainTicketData(result.getData()));
                })
                .flatMap((Function<List<SecondRemainTicketData>, ObservableSource<List<IRemainingTicket>>>) tzYpData -> {
                    List<IRemainingTicket> list = new ArrayList<>(tzYpData);
                    return Observable.just(list);
                })
                .subscribe(iRemainingTickets -> {
                    for (IRemainingTicket remainingTicket : iRemainingTickets) {
                        System.out.println(remainingTicket.getCode());
                    }
                }, throwable -> {
                    System.err.println(throwable);
                    throw new Exception(throwable);
                });
    }

    /**
     * 手动解析成相应的数据
     *
     * @param data 数据
     * @return 车次+余票信息
     */
    private static List<SecondRemainTicketData> getRemainTicketData(String data) {
        //正确的数据中：有14个数据
        final int max = 14;
        List<SecondRemainTicketData> list = new ArrayList<>();
        String[] items = data.split(",;");
        for (String item : items) {
            String[] params = item.split(",");
            if (params.length != max) {
                continue;
            }
            list.add(new SecondRemainTicketData(params));
        }
        return list;
    }

}