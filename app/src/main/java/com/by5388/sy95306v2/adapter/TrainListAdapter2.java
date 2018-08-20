package com.by5388.sy95306v2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.activity.trainList.TrainOnClickListener;
import com.by5388.sy95306v2.bean.Station;
import com.by5388.sy95306v2.bean.shenyang.TrainNumber;
import com.by5388.sy95306v2.common.Tools;
import com.by5388.sy95306v2.database.IShenYangStationDb;
import com.by5388.sy95306v2.database.DataBaseTempAction;

import java.util.ArrayList;
import java.util.List;

/**
 * 车次
 * todo 出现了内存不足的现象，oom
 *
 * @author by5388  on 2018/6/7.
 * // TODO: 2018/8/15 简单再简单
 */
//@SuppressWarnings("unused")
public class TrainListAdapter2 extends BaseAdapter {
    private final static String TAG = "TrainListAdapter2";
    private List<TrainNumber> trainNumbers;
    private List<Station> stations = new ArrayList<>();
    private IShenYangStationDb dataBaseService;
    private Context context;
    private LayoutInflater inflater;


    @Override
    public int getCount() {
        return trainNumbers.size();
    }

    public TrainListAdapter2(@NonNull List<TrainNumber> trainNumbers, Context context, TrainOnClickListener listener) {
        this.trainNumbers = trainNumbers;
        this.dataBaseService = DataBaseTempAction.getInstance();
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TrainNumber trainNumber = trainNumbers.get(position);
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_train_number_list2, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

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
        return convertView;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
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
            imageViewStart = itemView.findViewById(R.id.textView_start_station);
            imageViewEnd = itemView.findViewById(R.id.textView_end_station);
            startStationName = itemView.findViewById(R.id.textView_start_station_name);
            endStationName = itemView.findViewById(R.id.textView_end_station_name);
            startStationTime = itemView.findViewById(R.id.textView_start_station_time);
            endStationTime = itemView.findViewById(R.id.textView_end_station_time);
            spendTime = itemView.findViewById(R.id.textView_spend_time);
            trainNumberName = itemView.findViewById(R.id.textView_train_number_name);
            description = itemView.findViewById(R.id.textView_train_description);
            price = itemView.findViewById(R.id.textView_price);
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

    public void setTrainNumbers(@NonNull List<TrainNumber> trainNumbers) {
        this.trainNumbers = trainNumbers;
        notifyDataSetChanged();
    }

    @Override
    public TrainNumber getItem(int position) {
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
}
