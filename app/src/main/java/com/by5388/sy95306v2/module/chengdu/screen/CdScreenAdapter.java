package com.by5388.sy95306v2.module.chengdu.screen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.by5388.sy95306v2.R;

import java.util.List;

/**
 * @author by5388  on  2018/8/18
 */

class CdScreenAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private List<CdScreenItem> screenItems;

    CdScreenAdapter(Context context, List<CdScreenItem> screenItems) {
        this.inflater = LayoutInflater.from(context);
        this.screenItems = screenItems;
    }

    void setScreenItems(List<CdScreenItem> screenItems) {
        this.screenItems = screenItems;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return screenItems.size();
    }

    @Override
    public CdScreenItem getItem(int position) {
        return screenItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CdScreenItem item = getItem(position);
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_cd_screen, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.bind(item);
        return convertView;
    }

    private static class ViewHolder {
        final TextView code;
        final TextView from;
        final TextView to;
        final TextView time;
        final TextView place;
        final TextView status;

        ViewHolder(View v) {
            code = v.findViewById(R.id.trainCode);
            from = v.findViewById(R.id.startStation);
            to = v.findViewById(R.id.endStation);
            time = v.findViewById(R.id.showTime);
            place = v.findViewById(R.id.showPlace);
            status = v.findViewById(R.id.showStatus);
        }

        void bind(CdScreenItem item) {
            code.setText(item.getTrainCode());
            from.setText(item.getStartStation());
            to.setText(item.getEndStation());
            time.setText(item.getTime());
            place.setText(item.getPlace());
            status.setText(item.getStatus());
        }
    }
}
