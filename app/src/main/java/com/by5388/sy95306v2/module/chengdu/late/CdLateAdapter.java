package com.by5388.sy95306v2.module.chengdu.late;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.module.chengdu.bean.late.CdTrainAllNodeBean;

import java.util.List;

/**
 * @author by5388  on 2018/8/19.
 */
final class CdLateAdapter extends BaseAdapter {
    private List<CdTrainAllNodeBean> beans;
    private final LayoutInflater inflater;


    CdLateAdapter(@NonNull Context context, @NonNull List<CdTrainAllNodeBean> beans) {
        this.inflater = LayoutInflater.from(context);
        this.beans = beans;
    }

    public void setBeans(List<CdTrainAllNodeBean> beans) {
        this.beans = beans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public CdTrainAllNodeBean getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CdTrainAllNodeBean bean = getItem(position);
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_cd_late, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.bindData(bean);
        return convertView;
    }

    static class ViewHolder {
        final TextView stayTime;
        final TextView stationName;
        final TextView arriveTime;
        final TextView leaveTime;
        final TextView showStatus;

        ViewHolder(View view) {
            stayTime = view.findViewById(R.id.stayTime);
            stationName = view.findViewById(R.id.stationName);
            arriveTime = view.findViewById(R.id.arriveTime);
            leaveTime = view.findViewById(R.id.leaveTime);
            showStatus = view.findViewById(R.id.showStatus);
        }

        void bindData(CdTrainAllNodeBean bean) {
            stayTime.setText(String.valueOf(bean.getTlsj()));
            stationName.setText(bean.getStation());
            arriveTime.setText(bean.getTdddsj().split(" ")[1]);
            leaveTime.setText(bean.getTdcfsj().split(" ")[1]);
            showStatus.setText(bean.getDdwd());
        }
    }
}
