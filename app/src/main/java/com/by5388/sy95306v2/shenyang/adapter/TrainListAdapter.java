package com.by5388.sy95306v2.shenyang.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.database.DataBaseApiImpl;
import com.by5388.sy95306v2.shenyang.list.TrainOnClickListener;
import com.by5388.sy95306v2.shenyang.bean.Station;
import com.by5388.sy95306v2.shenyang.bean.TrainNumber;
import com.by5388.sy95306v2.common.Tools;
import com.by5388.sy95306v2.database.IShenYangDbApi;

import java.util.ArrayList;
import java.util.List;

/**
 * 车次
 *
 * @author by5388  on 2018/6/7.
 */
public class TrainListAdapter extends RecyclerView.Adapter<TrainListAdapter.ViewHolder> implements View.OnClickListener {
    private final static String TAG = "TrainListAdapter";
    private List<TrainNumber> trainNumbers;
    private final List<Station> stations = new ArrayList<>();
    private final IShenYangDbApi dataBaseService;
    private final Context context;
    private final TrainOnClickListener listener;


    public TrainListAdapter(@NonNull List<TrainNumber> trainNumbers, Context context, TrainOnClickListener listener) {
        this.trainNumbers = trainNumbers;
        this.dataBaseService = DataBaseApiImpl.getInstance();
        this.context = context;
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TrainNumber trainNumber = trainNumbers.get(position);

        holder.trainNumberName.setText(trainNumber.getSTCODE());
        holder.description.setText(trainNumber.getDescription());
        holder.startStationName.setText(getStationName(trainNumber.getFST()));
        holder.endStationName.setText(getStationName(trainNumber.getTST()));

        setStartStationIcon(trainNumber, holder.imageViewStart, context);
        setEndStationIcon(trainNumber, holder.imageViewEnd, context);

        holder.startStationTime.setText(Tools.showTime(trainNumber.getSTIME()));
        holder.endStationTime.setText(Tools.showTime(trainNumber.getATIME()));
        holder.spendTime.setText(Tools.getAllSpendTime(trainNumber.getLISHI()));

        String price1 = trainNumber.getDESPRI1() + trainNumber.getPRI1();
        String price2 = trainNumber.getDESPRI2() + trainNumber.getPRI2();
        String price3 = trainNumber.getDESPRI3() + trainNumber.getPRI3();
        String price4 = trainNumber.getDESPRI4() + trainNumber.getPRI4();
        String allPrice = String.format(context.getResources().getString(R.string.all_price), price1, price2, price3, price4);
        holder.price.setText(allPrice);
        holder.rootView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return trainNumbers.size();
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_train_number_list2, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        itemView.setLayoutParams(layoutParams);
        itemView.setOnClickListener(this);
        return new ViewHolder(itemView);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View v) {
        listener.onItemClickListener(v, ((int) v.getTag()));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        final View rootView;
        final TextView imageViewStart;
        final TextView imageViewEnd;
        final TextView startStationName;
        final TextView endStationName;
        final TextView startStationTime;
        final TextView endStationTime;
        final TextView spendTime;
        final TextView trainNumberName;
        final TextView description;
        /**
         * 四种价格
         */
        final TextView price;


        ViewHolder(View itemView) {
            super(itemView);
            this.rootView = itemView.findViewById(R.id.lly_item_all);
            this.imageViewStart = itemView.findViewById(R.id.textView_start_station);
            this.imageViewEnd = itemView.findViewById(R.id.textView_end_station);
            this.startStationName = itemView.findViewById(R.id.textView_start_station_name);
            this.endStationName = itemView.findViewById(R.id.textView_end_station_name);
            this.startStationTime = itemView.findViewById(R.id.textView_start_station_time);
            this.endStationTime = itemView.findViewById(R.id.textView_end_station_time);
            this.spendTime = itemView.findViewById(R.id.textView_spend_time);
            this.trainNumberName = itemView.findViewById(R.id.textView_train_number_name);
            this.description = itemView.findViewById(R.id.textView_train_description);
            this.price = itemView.findViewById(R.id.textView_price);
        }
    }


    /**
     * 始发、路过
     *
     * @param trainNumber 车次
     * @param textView    标签
     * @param context     上下文对象
     */
    private static void setStartStationIcon(@NonNull TrainNumber trainNumber, @NonNull TextView textView, @NonNull Context context) {
        if (trainNumber.isFirstStation()) {
            textView.setText(R.string.station_start);
            // TODO: 2018/7/15 过时方法
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
                textView.setBackgroundColor(context.getColor(R.color.detail_top_lly_bg));
            } else {
                textView.setBackgroundColor(context.getResources().getColor(R.color.detail_top_lly_bg));
            }
        } else {
            textView.setText(R.string.station_pass);
            textView.setBackgroundColor(context.getResources().getColor(R.color.station_time));
        }
    }

    /**
     * 终到、过路
     *
     * @param trainNumber 车次
     * @param textView    标签
     * @param context     上下文对象
     */
    private static void setEndStationIcon(@NonNull TrainNumber trainNumber, @NonNull TextView textView, @NonNull Context context) {
        if (trainNumber.isLastStation()) {
            textView.setText(R.string.station_end);
            textView.setBackgroundColor(context.getResources().getColor(R.color.button_selected));
        } else {
            textView.setText(R.string.station_pass);
            textView.setBackgroundColor(context.getResources().getColor(R.color.station_time));
        }
    }

    public void setTrainNumbers(@NonNull List<TrainNumber> trainNumbers) {
        this.trainNumbers = trainNumbers;
        notifyDataSetChanged();
    }

    public TrainNumber getItem(int position) {
        if (position >= trainNumbers.size()) {
            return null;
        }
        return trainNumbers.get(position);

    }

    /**
     * 根据NameUpper来取得车站名称
     *
     * @param nameUpper 车站唯一简称
     * @return 车站名称
     */
    private String getStationName(@NonNull String nameUpper) {
        for (Station station : stations) {
            if (nameUpper.equals(station.getNameUpper())) {
                return station.getName();
            }
        }
        Station station = dataBaseService.selectStationByNameUpper(nameUpper);
        if (TextUtils.isEmpty(station.getName())) {
            return nameUpper;
        }
        stations.add(station);
        return station.getName();
    }
}
