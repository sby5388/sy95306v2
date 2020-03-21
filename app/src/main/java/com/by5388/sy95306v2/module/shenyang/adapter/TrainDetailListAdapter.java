package com.by5388.sy95306v2.module.shenyang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.common.Tools;
import com.by5388.sy95306v2.module.shenyang.bean.TrainDetail;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 车次停站详细信息-适配器
 *
 * @author by5388  on 2018/6/14.
 */

public class TrainDetailListAdapter extends RecyclerView.Adapter<TrainDetailListAdapter.ViewHolder> implements View.OnClickListener {

    private final LayoutInflater inflater;
    private List<TrainDetail> details;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_train_detail, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        itemView.setLayoutParams(layoutParams);
        itemView.setOnClickListener(this);
        return new ViewHolder(itemView);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * 序号
         */
        final TextView textViewNumber;
        /**
         * 站名
         */
        final TextView stationName;
        /**
         * 到达时间
         */
        final TextView arriveTime;
        /**
         * 出发时间
         */
        final TextView leaveTime;
        /**
         * 停站时间
         */
        final TextView remainTime;

        ViewHolder(View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.textView_number);
            stationName = itemView.findViewById(R.id.station_name);
            arriveTime = itemView.findViewById(R.id.arrive_time);
            leaveTime = itemView.findViewById(R.id.leave_time);
            remainTime = itemView.findViewById(R.id.remain_time);
        }
    }
}
