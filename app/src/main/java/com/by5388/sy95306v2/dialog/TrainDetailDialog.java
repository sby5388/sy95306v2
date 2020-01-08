package com.by5388.sy95306v2.dialog;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.module.guangzhou.bean.station.TrainsBean;
import com.by5388.sy95306v2.dialog.adapter.DetailAdapter;

import java.util.ArrayList;

/**
 * @author by5388  on 2018/8/2.
 */

public class TrainDetailDialog implements ITrainDetailView {
    private final DetailAdapter adapter;
    private final Context context;
    private final AlertDialog dialog;
    private View dialogView;
    private View dialogTitleView;
    private final LayoutInflater inflater;
    private TextView titleName, titleTime;

    public TrainDetailDialog(Context context) {
        adapter = new DetailAdapter(context, new ArrayList<>());
        inflater = LayoutInflater.from(context);
        this.context = context;
        initTitleView();
        initMainView();
        dialog = new AlertDialog.Builder(context)
                .setCustomTitle(dialogTitleView)
                .setView(dialogView)
                .setPositiveButton(R.string.ok, (dialog, which) -> dialog.dismiss()).create();
    }

    private void initMainView() {
        dialogView = inflater.inflate(R.layout.custom_dialog_main_view, new LinearLayout(context), false);
        ListView dialogListView = dialogView.findViewById(R.id.listView_detail);
        dialogListView.setAdapter(adapter);
    }

    private void initTitleView() {
        dialogTitleView = inflater.inflate(R.layout.custom_dialog_title_view, new LinearLayout(context), false);
        titleName = dialogTitleView.findViewById(R.id.textView_name);
        titleTime = dialogTitleView.findViewById(R.id.textView_time);
    }

    @Override
    public void show() {
        dialog.show();
    }

    @Override
    public void dismiss() {
        dialog.dismiss();
    }

    @Override
    public TrainDetailDialog setData(@NonNull TrainsBean trainsBean) {
        // TODO: 2018/8/3 修改->显示
        adapter.setInfoBeans(trainsBean);
        String name = String.format(context.getString(R.string.show_title), trainsBean.getTrainNo(), trainsBean.getSfStation(), trainsBean.getZdStation());
        String time = String.format(context.getString(R.string.show_time), trainsBean.getSfTime(), trainsBean.getZdTime(), trainsBean.getSpendTime());
        titleName.setText(name);
        titleTime.setText(time);
        return this;

    }

    @Override
    public boolean isShowing() {
        return dialog.isShowing();
    }
}
