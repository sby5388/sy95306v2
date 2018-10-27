package com.by5388.sy95306.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.by5388.sy95306.R;
import com.by5388.sy95306.bean.Station;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 觉得一个适配器显示的只是一个String,并不需要关联到这么多变量
 * 如果想使用Station的相关属性，只需重新再数据库里面抓取即可
 *
 * @author by5388  on 2018/6/6.
 */
@SuppressWarnings("unused")
public class StationAdapter extends BaseAdapter {
    /**
     * 车站列表
     */
    private List<Station> stations;
    private LayoutInflater inflater;
    private final static String TAG = "StationAdapter";

    public StationAdapter(Context context, List<Station> stations) {
        this.stations = stations;
        this.inflater = LayoutInflater.from(context);
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return stations.size();
    }

    @Override
    public Station getItem(int position) {
        return stations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Station city = getItem(position);
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_select_city_name, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(city.getName());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.textView_city_name)
        TextView textView;

        ViewHolder(View convertView) {
            ButterKnife.bind(this, convertView);
        }
    }
}


