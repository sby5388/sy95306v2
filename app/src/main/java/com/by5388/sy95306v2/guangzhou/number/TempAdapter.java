package com.by5388.sy95306v2.guangzhou.number;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.guangzhou.bean.station.TrainsBean;

import java.util.List;

/**
 * @author by5388  on 2018/8/2.
 */

class TempAdapter extends BaseAdapter {
    private List<TrainsBean> trainsBeans;
    private final LayoutInflater inflater;

    TempAdapter(Context context, List<TrainsBean> trainsBeans) {
        this.inflater = LayoutInflater.from(context);
        this.trainsBeans = trainsBeans;
    }

    void setTrainsBeans(List<TrainsBean> trainsBeans) {
        this.trainsBeans = trainsBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return trainsBeans.size();
    }

    @Override
    public TrainsBean getItem(int position) {
        return trainsBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TrainsBean trainsBean = getItem(position);
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_temp, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.code.setText(trainsBean.getTrainNo());
        holder.from.setText(trainsBean.getSfStation());
        holder.to.setText(trainsBean.getZdStation());
        holder.spend.setText(trainsBean.getSpendTime());
        holder.start.setText(trainsBean.getSfTime());
        holder.end.setText(trainsBean.getZdTime());
        return convertView;
    }

    static class ViewHolder {
        final TextView code;
        final TextView from;
        final TextView to;
        final TextView spend;
        final TextView start;
        final TextView end;

        ViewHolder(View v) {
            code = v.findViewById(R.id.temp_code);
            from = v.findViewById(R.id.temp_from);
            to = v.findViewById(R.id.temp_to);
            spend = v.findViewById(R.id.temp_spend);
            start = v.findViewById(R.id.temp_start);
            end = v.findViewById(R.id.temp_end);
        }
    }
}
