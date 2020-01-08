package com.by5388.sy95306.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.by5388.sy95306.R;
import com.by5388.sy95306.bean.TrainDetail;
import com.by5388.sy95306.common.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * 车次停站详细信息-适配器
 *
 * @author by5388  on 2018/6/14.
 */

public class TrainDetailAdapter extends RecyclerView.Adapter<TrainDetailAdapter.ViewHolder> {

    private List<TrainDetail> details = new ArrayList<>();
    private final LayoutInflater inflater;

    public TrainDetailAdapter(Context context) {
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


    class ViewHolder extends RecyclerView.ViewHolder {
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
            stationName = itemView.findViewById(R.id.stationName);
            arriveTime = itemView.findViewById(R.id.arriveTime);
            leaveTime = itemView.findViewById(R.id.leave_time);
            remainTime = itemView.findViewById(R.id.remain_time);
        }
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
        return new TrainDetailAdapter.ViewHolder(itemView);
    }
}
