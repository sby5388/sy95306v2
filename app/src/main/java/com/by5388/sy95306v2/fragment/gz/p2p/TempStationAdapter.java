package com.by5388.sy95306v2.fragment.gz.p2p;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.bean.gz.station.StationsBean;

import java.util.List;

/**
 * @author by5388  on 2018/8/2.
 */

class TempStationAdapter extends BaseAdapter {
    private List<StationsBean> stationsBeans;
    private final LayoutInflater inflater;

    TempStationAdapter(Context context, List<StationsBean> stationsBeans) {
        this.inflater = LayoutInflater.from(context);
        this.stationsBeans = stationsBeans;
    }

    public void setStationsBeans(List<StationsBean> stationsBeans) {
        this.stationsBeans = stationsBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return stationsBeans.size();
    }

    @Override
    public StationsBean getItem(int position) {
        return stationsBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StationsBean trainsBean = getItem(position);
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_temp, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.code.setText(trainsBean.getCc());
        holder.from.setText(trainsBean.getFromStation());
        holder.to.setText(trainsBean.getToStation());
        holder.spend.setText(trainsBean.getSpendTime());
        holder.start.setText(trainsBean.getLeaveTime());
        holder.end.setText(trainsBean.getArriveTime());
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
