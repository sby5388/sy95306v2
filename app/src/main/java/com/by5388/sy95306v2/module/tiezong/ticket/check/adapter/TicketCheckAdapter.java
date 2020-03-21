package com.by5388.sy95306v2.module.tiezong.ticket.check.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.by5388.sy95306v2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin  on 2019/1/24.
 */
public class TicketCheckAdapter extends BaseAdapter {
    private Context mContext;
    private List<ITicketCheck> mITicketCheckList;

    public TicketCheckAdapter(Context context) {
        mContext = context;
        mITicketCheckList = new ArrayList<>();
    }

    public void setITicketCheckList(List<ITicketCheck> iTicketCheckList) {
        mITicketCheckList = iTicketCheckList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mITicketCheckList.size();
    }

    @Override
    public ITicketCheck getItem(int position) {
        return mITicketCheckList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_ticket_check, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ITicketCheck item = getItem(position);
        holder.bind(item);
        return convertView;
    }


    private static class ViewHolder {
        private TextView fromStation;
        private TextView stationName;
        private TextView checkPlace;
        private TextView trainCode;

        ViewHolder(View convertView) {

        }

        void bind(ITicketCheck item) {
        }
    }
}
