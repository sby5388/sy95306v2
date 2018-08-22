package com.by5388.sy95306v2.fragment.shanghai.number;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.bean.shanghai.InfoBeanDelay;
import com.by5388.sy95306v2.bean.shanghai.QueryMethod;
import com.by5388.sy95306v2.bean.shanghai.ShanghaiTrainNumber;
import com.by5388.sy95306v2.net.shanghai.ShangHaiNetTools;
import com.by5388.sy95306v2.net.shanghai.ShanghaiService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * @author by5388  on 2018/8/9.
 */

public class ShNumberAdapter extends RecyclerView.Adapter {
    private static final String TAG = "ShNumberAdapter";
    private List<ShanghaiTrainNumber> numbers;
    private final LayoutInflater inflater;
    private final ShanghaiService shanghaiService;

    public ShNumberAdapter(Context context, List<ShanghaiTrainNumber> numbers) {
        this.numbers = numbers;
        this.inflater = LayoutInflater.from(context);
        Retrofit retrofit = new ShangHaiNetTools().getRetrofit();
        shanghaiService = retrofit.create(ShanghaiService.class);
    }

    public void setNumbers(List<ShanghaiTrainNumber> numbers) {
        Log.d(TAG, "setNumbers: ");
        this.numbers = numbers;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_sh_number, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new NumberViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, int position) {
        ShanghaiTrainNumber number = numbers.get(position);
        NumberViewHolder holder = (NumberViewHolder) holder1;
        holder.startTime.setText(number.getStartTime());
        holder.stayTime.setText(number.getStayTime());
        holder.stationNum.setText(number.getStationNum());
        holder.arrive.setText("未知");
        holder.endTime.setText(number.getEndTime());
        holder.stationName.setText(number.getStationName());
        //TODO 测试方法
        //TODO 对字符串判断，特定值时才取值，否则不处理，避免重复调用
        //String stationName, String trainCode
        Disposable disposable = shanghaiService.queryTrainDelay(new QueryMethod<>(new InfoBeanDelay(number.getStationName(), number.getTrainCode())))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(delays -> {
                    if (null == delays || delays.isEmpty()) {
                        Log.d(TAG, "accept: 空值");
                        return;
                    }
                    String arrive = delays.get(0).getArrive();
                    Log.d(TAG, "accept: " + number.getStationName() + ":::" + arrive);
                    holder.arrive.setText(arrive);
                }, throwable -> holder.arrive.setText("未知1"));

    }

    static class NumberViewHolder extends RecyclerView.ViewHolder {
        final TextView stationName;
        final TextView stayTime;
        final TextView stationNum;
        final TextView arrive;
        final TextView endTime;
        final TextView startTime;
        final TextView delay;
        final ProgressBar progressBar;

        NumberViewHolder(View view) {
            super(view);
            stationName = view.findViewById(R.id.stationName);
            stayTime = view.findViewById(R.id.stayTime);
            stationNum = view.findViewById(R.id.stationNum);
            arrive = view.findViewById(R.id.arrive_time);
            endTime = view.findViewById(R.id.endTime);
            startTime = view.findViewById(R.id.startTime);
            delay = view.findViewById(R.id.textView_delay);
            progressBar = view.findViewById(R.id.progressBar);
        }
    }
}
