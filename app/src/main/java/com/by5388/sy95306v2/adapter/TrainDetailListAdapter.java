package com.by5388.sy95306v2.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.bean.shenyang.TrainDetail;
import com.by5388.sy95306v2.common.Tools;

import java.util.List;

/**
 * 车次停站详细信息-适配器
 *
 * @author by5388  on 2018/6/14.
 */

public class TrainDetailListAdapter extends RecyclerView.Adapter<TrainDetailListAdapter.ViewHolder> implements View.OnClickListener {

    private List<TrainDetail> details;
    private LayoutInflater inflater;

    public TrainDetailListAdapter(Context context, List<TrainDetail> details) {
        this.details = details;
        this.inflater = LayoutInflater.from(context);
    }

    public void setDetails(@NonNull List<TrainDetail> details) {
        this.details = details;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TrainDetail detail = details.get(position);
        holder.textViewNumber.setText(detail.getSTNO());
        holder.stationName.setText(detail.getSNAME());
        holder.arriveTime.setText(Tools.showTime(detail.getATIME()));
        holder.leaveTime.setText(Tools.showTime(detail.getSTIME()));
        holder.remainTime.setText(Tools.stopTime(detail.getATIME(), detail.getSTIME()));
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_train_detail, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        itemView.setLayoutParams(layoutParams);
        itemView.setOnClickListener(this);
        return new TrainDetailListAdapter.ViewHolder(itemView);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * 序号
         */
        TextView textViewNumber;
        /**
         * 站名
         */
        TextView stationName;
        /**
         * 到达时间
         */
        TextView arriveTime;
        /**
         * 出发时间
         */
        TextView leaveTime;
        /**
         * 停站时间
         */
        TextView remainTime;

        ViewHolder(View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.textView_number);
            stationName = itemView.findViewById(R.id.station_name);
            arriveTime = itemView.findViewById(R.id.arrive_time);
            leaveTime = itemView.findViewById(R.id.leave_time);
            remainTime = itemView.findViewById(R.id.remain_time);
            textViewNumber.setTypeface(Typeface.DEFAULT, 0);
            stationName.setTypeface(Typeface.DEFAULT, 0);
            arriveTime.setTypeface(Typeface.DEFAULT, 0);
            leaveTime.setTypeface(Typeface.DEFAULT, 0);
            remainTime.setTypeface(Typeface.DEFAULT, 0);
        }
    }
}
