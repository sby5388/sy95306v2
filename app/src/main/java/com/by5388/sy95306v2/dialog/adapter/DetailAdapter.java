package com.by5388.sy95306v2.dialog.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.guangzhou.bean.StationInfoBean;
import com.by5388.sy95306v2.guangzhou.bean.station.TrainsBean;
import com.by5388.sy95306v2.tiezong.api.TzQuery;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/8/3.
 */

public class DetailAdapter extends BaseAdapter {
    /**
     * 到达
     */
    private static final int ARRIVE = TzQuery.ARRIVE;
    /**
     * 离开
     */
    private static final int LEAVE = TzQuery.LEAVE;

    private List<StationInfoBean> infoBeans;
    private final LayoutInflater inflater;
    private String trainCode = "";
    private String date = "";
    private final Consumer<Throwable> throwableConsumer;
    private String[][] status;

    public DetailAdapter(@NonNull Context context, @NonNull List<StationInfoBean> infoBeans) {
        this.infoBeans = infoBeans;
        this.inflater = LayoutInflater.from(context);
        throwableConsumer = throwable -> Toast.makeText(context, throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        status = new String[infoBeans.size()][2];
    }

    public void setInfoBeans(@NonNull TrainsBean trainsBean) {
        List<StationInfoBean> infoBeans = trainsBean.getStationInfos();
        if (infoBeans != null) {
            this.infoBeans = infoBeans;
            trainCode = trainsBean.getTrainNo().split("/")[0];
            date = getDate(infoBeans.get(0).getArrDate());
            status = new String[infoBeans.size()][2];
            notifyDataSetChanged();
        }
    }

    private static String getDate(String arrDate) {
        // FIXME: 2018/8/20 
        return "2018-08-15";
    }


    @Override
    public int getCount() {
        return infoBeans.size();
    }

    @Override
    public StationInfoBean getItem(int position) {
        return infoBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StationInfoBean infoBean = getItem(position);
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_detail_simple, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String name = infoBean.getStationName();
        holder.name.setText(name);
        holder.arrive.setText(infoBean.getArrTime());
        holder.leave.setText(infoBean.getGoTime());
        Disposable disposable;
        if (TextUtils.isEmpty(status[position][ARRIVE]) || TextUtils.isEmpty(status[position][LEAVE])) {
            if (!TextUtils.isEmpty(trainCode) && !(TextUtils.isEmpty(date))) {
                disposable = TzQuery.queryLate(name, trainCode, ARRIVE, date)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(s -> {
                            status[position][ARRIVE] = s.trim();
                            holder.realArrive.setText(s.trim());
                        }, throwableConsumer);
                disposable = TzQuery.queryLate(name, trainCode, LEAVE, date)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(s -> {
                            status[position][LEAVE] = s.trim();
                            holder.realLeave.setText(s.trim());
                        }, throwableConsumer);
            }
        } else {
            holder.realArrive.setText(status[position][ARRIVE]);
            holder.realLeave.setText(status[position][LEAVE]);
        }

        return convertView;
    }

    static class ViewHolder {
        final TextView name;
        final TextView arrive;
        final TextView leave;
        final TextView realArrive;
        final TextView realLeave;

        ViewHolder(View view) {
            name = view.findViewById(R.id.textView_name);
            arrive = view.findViewById(R.id.textView_arrive_time);
            leave = view.findViewById(R.id.textView_leave_time);
            realArrive = view.findViewById(R.id.textView_real_arrive);
            realLeave = view.findViewById(R.id.textView_real_leave);
        }
    }
}
