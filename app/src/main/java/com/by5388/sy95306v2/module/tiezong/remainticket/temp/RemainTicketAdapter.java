package com.by5388.sy95306v2.module.tiezong.remainticket.temp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.bean.IRemainingTicket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by5388  on 2018/8/13.
 */
public class RemainTicketAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private List<IRemainingTicket> tickets;
    private final String rw;
    private final String yw;
    private final String rz;
    private final String yz;
    private final String wz;
    private final static String EMPTY = "--";
    private final static String EMPTY2 = "无";

    public RemainTicketAdapter(@NonNull Context context) {
        this.inflater = LayoutInflater.from(context);
        this.tickets = new ArrayList<>();
        rw = context.getString(R.string.rw);
        yw = context.getString(R.string.yw);
        rz = context.getString(R.string.rz);
        yz = context.getString(R.string.yz);
        wz = context.getString(R.string.wz);
    }

    public void setTickets(@NonNull List<IRemainingTicket> tickets) {
        this.tickets = tickets;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return tickets.size();
    }

    @Override
    public IRemainingTicket getItem(int position) {
        return tickets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IRemainingTicket data = getItem(position);
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_remain_ticket2, parent, false);
            // FIXME: 2018/8/25 修改成2行 6列的，增加保存按钮
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.code.setText(data.getCode());
        holder.fromStation.setText(data.getFromStation());
        holder.toStation.setText(data.getToStation());
        holder.startTime.setText(data.getStartTime());
        holder.endTime.setText(data.getEndTime());
        holder.speedTime.setText(data.getSpeedTime());

        String mRw = data.getRw();
        String mYw = data.getYw();
        String mRz = data.getRz();
        String mYz = data.getYz();
        String mWz = data.getWz();
        if (TextUtils.isEmpty(mRw) || EMPTY.equals(mRw) || EMPTY2.equals(mRw)) {
            holder.rw.setText(mRw);
        } else {
            holder.rw.setText(String.format(rw, mRw));
        }
        if (TextUtils.isEmpty(mYw) || EMPTY.equals(mYw) || EMPTY2.equals(mYw)) {
            holder.yw.setText(mYw);
        } else {
            holder.yw.setText(String.format(yw, mYw));
        }
        //
        if (TextUtils.isEmpty(mRz) || EMPTY.equals(mRz) || EMPTY2.equals(mRz)) {
            holder.rz.setText(mRz);
        } else {
            holder.rz.setText(String.format(rz, mRz));
        }
        //
        if (TextUtils.isEmpty(mYz) || EMPTY.equals(mYz) || EMPTY2.equals(mYz)) {
            holder.yz.setText(mYz);
        } else {
            holder.yz.setText(String.format(yz, mYz));
        }
        //
        if (TextUtils.isEmpty(mWz) || EMPTY.equals(mWz) || EMPTY2.equals(mWz)) {
            holder.wz.setText(mWz);
        } else {
            holder.wz.setText(String.format(wz, mWz));
        }
        holder.qt.setText(data.getQt());
        return convertView;
    }

    private static class ViewHolder {
        final TextView code;
        final TextView fromStation;
        final TextView toStation;
        final TextView startTime;
        final TextView endTime;
        final TextView speedTime;
        final TextView rw;
        final TextView yw;
        final TextView rz;
        final TextView yz;
        final TextView qt;
        final TextView wz;

        ViewHolder(View view) {
            this.code = view.findViewById(R.id.code);
            this.fromStation = view.findViewById(R.id.fromStation);
            this.toStation = view.findViewById(R.id.toStation);
            this.startTime = view.findViewById(R.id.startTime);
            this.endTime = view.findViewById(R.id.endTime);
            this.speedTime = view.findViewById(R.id.speedTime);
            this.rw = view.findViewById(R.id.rw);
            this.yw = view.findViewById(R.id.yw);
            this.rz = view.findViewById(R.id.rz);
            this.yz = view.findViewById(R.id.yz);
            this.wz = view.findViewById(R.id.wz);
            this.qt = view.findViewById(R.id.qt);
        }
    }
}
