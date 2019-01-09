package com.by5388.sy95306v2.tiezong.temp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.tiezong.bean.yp.success.TzDataBean;

import java.util.List;

/**
 * @author by5388  on 2018/8/28.
 */
class DetailRemainTicketAdapter extends RecyclerView.Adapter {

    private List<TzDataBean> beans;
    private final LayoutInflater inflater;

    DetailRemainTicketAdapter(Context context, List<TzDataBean> beans) {
        this.beans = beans;
        this.inflater = LayoutInflater.from(context);
    }


    public void setBeans(List<TzDataBean> beans) {
        this.beans = beans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_detail_remain_ticket, parent, false);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder1, int position) {
        DetailViewHolder holder = (DetailViewHolder) holder1;
        TzDataBean bean = beans.get(position);
        holder.setData(bean);

    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    static class DetailViewHolder extends RecyclerView.ViewHolder {
        final TextView trainCode;
        final TextView fromStation;
        final TextView toStation;
        final TextView leaveTime;
        final TextView arriveTime;
        final TextView speedTime;
        final TextView contentDescription;
        final TextView swz;
        final TextView tz;
        final TextView zy;
        final TextView ze;
        final TextView gjrw;
        final TextView rw;
        final TextView dw;
        final TextView yw;
        final TextView rz;
        final TextView yz;
        final TextView wz;

        DetailViewHolder(View view) {
            super(view);
            trainCode = view.findViewById(R.id.trainCode);
            fromStation = view.findViewById(R.id.fromStation);
            toStation = view.findViewById(R.id.toStation);
            leaveTime = view.findViewById(R.id.leaveTime);
            arriveTime = view.findViewById(R.id.arriveTime);
            speedTime = view.findViewById(R.id.speedTime);
            contentDescription = view.findViewById(R.id.contentDescription);
            swz = view.findViewById(R.id.swz);
            tz = view.findViewById(R.id.tz);
            zy = view.findViewById(R.id.zy);
            ze = view.findViewById(R.id.ze);
            gjrw = view.findViewById(R.id.gjrw);
            rw = view.findViewById(R.id.rw);
            dw = view.findViewById(R.id.dw);
            yw = view.findViewById(R.id.yw);
            rz = view.findViewById(R.id.rz);
            yz = view.findViewById(R.id.yz);
            wz = view.findViewById(R.id.wz);
        }


        void setData(TzDataBean bean) {
            trainCode.setText(bean.getStation_train_code());
            fromStation.setText(bean.getFrom_station_name());
            toStation.setText(bean.getTo_station_name());
            leaveTime.setText(bean.getStart_time());
            arriveTime.setText(bean.getArrive_time());
            speedTime.setText(bean.getLishi());
            //TODO
            contentDescription.setText(bean.getStation_train_code());
            // TextView swz, tz, zy, ze, gjrw, rw, dw, yw, rz, yz, wz;
            swz.setText(bean.getSwz_num());
            tz.setText(bean.getTz_num());
            zy.setText(bean.getZy_num());
            ze.setText(bean.getZe_num());
            gjrw.setText(bean.getGr_num());
            rw.setText(bean.getRw_num());
            dw.setText(bean.getSrrb_num());
            yw.setText(bean.getYw_num());
            rz.setText(bean.getRz_num());
            yz.setText(bean.getYz_num());
            wz.setText(bean.getWz_num());


        }

    }
}
