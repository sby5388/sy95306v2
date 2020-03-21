package com.by5388.sy95306v2.module.guangzhou.late;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.module.guangzhou.bean.late.GzLateStationInfoBean;

import java.util.List;

/**
 * @author by5388  on 2018/8/21.
 */
class GzLateAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private List<GzLateStationInfoBean> beans;

    GzLateAdapter(Context context, List<GzLateStationInfoBean> beans) {
        this.beans = beans;
        this.inflater = LayoutInflater.from(context);
    }

    public void setBeans(List<GzLateStationInfoBean> beans) {
        this.beans = beans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public GzLateStationInfoBean getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GzLateStationInfoBean bean = getItem(position);
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_gz_late, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.bindData(bean);
        return convertView;
    }

    static class ViewHolder {
        final TextView stationName;
        final TextView arriveTime;
        final TextView realTime;
        final TextView lateStatus;

        ViewHolder(View view) {
            stationName = view.findViewById(R.id.stationName);
            arriveTime = view.findViewById(R.id.arriveTime);
            realTime = view.findViewById(R.id.realArriveTime);
            lateStatus = view.findViewById(R.id.lateStatus);
        }

        private void bindData(GzLateStationInfoBean bean) {
            stationName.setText(bean.getStation());
            arriveTime.setText(bean.getTdTime());
            realTime.setText(bean.getSjTime());
            lateStatus.setText(bean.getLate());
        }
    }
}
