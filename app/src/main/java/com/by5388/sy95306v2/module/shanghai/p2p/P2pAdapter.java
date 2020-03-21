package com.by5388.sy95306v2.module.shanghai.p2p;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.module.shanghai.bean.ShanghaiTrainP2P;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by5388  on 2018/8/10.
 */

class P2pAdapter extends BaseAdapter {
    public static final String TAG = "P2pAdapter";
    private final LayoutInflater inflater;
    private final Map<String, String> priceCache;
    private List<ShanghaiTrainP2P> trainP2PS;

    P2pAdapter(Context context, List<ShanghaiTrainP2P> trainP2PS) {
        this.inflater = LayoutInflater.from(context);
        this.trainP2PS = trainP2PS;
        this.priceCache = new HashMap<>();
    }

    void setTrainP2PS(List<ShanghaiTrainP2P> trainP2PS) {
        this.trainP2PS = trainP2PS;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return trainP2PS.size();
    }

    @Override
    public ShanghaiTrainP2P getItem(int position) {
        return trainP2PS.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ShanghaiTrainP2P p2p = getItem(position);
        P2pViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_shang_hai_p2p, parent, false);
            holder = new P2pViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (P2pViewHolder) convertView.getTag();
        }
        holder.endTime.setText(p2p.getEndTime());
        holder.trainCode.setText(p2p.getTrainCode());
        holder.fromStation.setText(p2p.getFromStation());
        holder.startTime.setText(p2p.getStartTime());
        holder.toStation.setText(p2p.getToStation());
        holder.needTime.setText(p2p.getNeedTime());
        holder.ticketPrice.setText(getMinPrice(p2p.getTicketPrice()));
        return convertView;
    }

    private String getMinPrice(String string) {
        if (TextUtils.isEmpty(string)) {
            return "--";
        }
        if (priceCache.containsKey(string)) {
            return priceCache.get(string);
        }

        try {
            String[] prices = string.split(",");
            float minValue = Float.MAX_VALUE;
            for (String price : prices) {
                price = price.replaceAll("[^0-9.]", "");
                float currentValue = Float.parseFloat(price);
                if (currentValue < minValue) {
                    minValue = currentValue;
                }
            }
            String showPrice = minValue + "起";
            priceCache.put(string, showPrice);
            return showPrice;
        } catch (NumberFormatException e) {
            return "--";
        }
    }

    private static class P2pViewHolder {
        private final TextView trainCode;
        private final TextView fromStation;
        private final TextView startTime;
        private final TextView toStation;
        private final TextView endTime;
        private final TextView needTime;
        private final TextView ticketPrice;

        P2pViewHolder(View view) {
            trainCode = view.findViewById(R.id.trainCode);
            fromStation = view.findViewById(R.id.fromStation);
            startTime = view.findViewById(R.id.startTime);
            toStation = view.findViewById(R.id.toStation);
            endTime = view.findViewById(R.id.endTime);
            needTime = view.findViewById(R.id.needTime);
            ticketPrice = view.findViewById(R.id.ticketPrice);
        }
    }
}
