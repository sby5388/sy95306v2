package com.by5388.sy95306.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.by5388.sy95306.R;
import com.by5388.sy95306.bean.Station;
import com.by5388.sy95306.bean.TrainNumber;
import com.by5388.sy95306.common.StaticData;
import com.by5388.sy95306.common.Tools;
import com.by5388.sy95306.database.DataBaseApi;
import com.by5388.sy95306.database.DataBaseTempApiImpl;
import com.by5388.sy95306.list.TrainOnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 车次
 *
 * @author by5388  on 2018/6/7.
 */
public class TrainListAdapter extends RecyclerView.Adapter<TrainListAdapter.ViewHolder> implements View.OnClickListener {
    private List<TrainNumber> trainNumbers;
    private List<Station> stations = new ArrayList<>();
    private DataBaseApi dataBaseService;
    private LayoutInflater inflater;
    private final static String TAG = "TrainListAdapter";
    private Context context;
    private TrainOnClickListener listener;


    public TrainListAdapter(@NonNull Context context, @NonNull List<TrainNumber> trainNumbers, TrainOnClickListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.trainNumbers = trainNumbers;
        this.dataBaseService = DataBaseTempApiImpl.getInstance();
        this.context = context;
        this.listener = listener;
    }

    public void setTrainNumbers(@NonNull List<TrainNumber> trainNumbers) {
        this.trainNumbers = trainNumbers;
        notifyDataSetChanged();
    }

    public TrainNumber getItem(int position) {
        if (position >= getItemCount()) {
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
            if (nameUpper.equals(station.getNameLower())) {
                return station.getName();
            }
        }
        Station station = dataBaseService.selectStationByNameUpper(nameUpper);
        if (Station.EMPTY_ID == station.getId()) {
            return nameUpper;
        }
        stations.add(station);
        return station.getName();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        TextView imageViewStart, imageViewEnd;
        TextView startStationName, endStationName;
        TextView startStationTime, endStationTime;
        TextView spendTime, trainNumberName;
        TextView description;
        /**
         * 四种价格
         */
        TextView price;


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

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TrainNumber trainNumber = trainNumbers.get(position);
        holder.trainNumberName.setText(trainNumber.getSTCODE());
        holder.description.setText(StaticData.getDescription(trainNumber));
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_train_number_list, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        itemView.setLayoutParams(layoutParams);
        itemView.setOnClickListener(this);
        return new ViewHolder(itemView);
    }


    @Override
    public long getItemId(int position) {
        return position;
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
            textView.setBackgroundColor(context.getResources().getColor(R.color.detail_top_lly_bg));
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

    @Override
    public void onClick(View v) {
        listener.onItemClickListener(v, ((int) v.getTag()));
    }
}
