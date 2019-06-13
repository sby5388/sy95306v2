package com.by5388.sy95306v2.module.chengdu.late;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.module.chengdu.bean.late.CdTrainAllNodeBean;
import com.by5388.sy95306v2.module.chengdu.BaseCdFragment;
import com.by5388.sy95306v2.module.chengdu.late.persenter.CdLatePresenter;
import com.by5388.sy95306v2.module.chengdu.late.persenter.ICdLatePresenter;
import com.by5388.sy95306v2.module.chengdu.late.view.ICdLateView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * @author by5388  on 2018/8/19.
 */
public class CdLateFragment extends BaseCdFragment implements ICdLateView {
    private static final String TAG = "CdLateFragment";
    private Button buttonSearch;
    private ListView listView;
    private TextInputEditText trainCode;
    private CdLateAdapter adapter;
    private ICdLatePresenter presenter;
    private AlertDialog dialog;
    private Calendar calendar;

    public static CdLateFragment newInstance() {
        CdLateFragment fragment = new CdLateFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        calendar = Calendar.getInstance();
        presenter = new CdLatePresenter(this);
        adapter = new CdLateAdapter(Objects.requireNonNull(getContext()), new ArrayList<>());
    }

    @Override
    protected void loadData() {
        listView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_cd_late;
    }

    @Override
    protected void initView(View view) {
        trainCode = view.findViewById(R.id.textView_train_code);
        listView = view.findViewById(R.id.listView_train_code);
        buttonSearch = view.findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(v -> query());
    }

    private void query() {
        Editable editable = trainCode.getText();
        if (editable == null) {
            return;
        }
        final String trainNo = editable.toString().trim();
        if (TextUtils.isEmpty(trainNo)) {
            trainCode.setError("请输入车次");
            trainCode.requestFocus();
            return;
        }
        String date = getData(calendar);
        presenter.getLateStationList(trainNo, date);
    }

    @Override
    public void updateView(int year, int month, int dayOfMonth) {
    }

    @Override
    public void startQuery() {
        buttonSearch.setEnabled(false);
    }

    @Override
    public void finishQuery() {
        buttonSearch.setEnabled(true);
    }

    @Override
    public void showError(String tip) {
        Toast.makeText(getContext(), tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog(@NonNull List<String> trainDetail) {
        int length = trainDetail.size();
        Editable editable = trainCode.getText();
        if (editable == null) {
            return;
        }
        final String trainNo = editable.toString().trim();

        String[] items = new String[length];
        for (int position = 0; position < length; position++) {
            items[position] = getData(calendar) + trainDetail.get(position);
        }
        if (null != dialog) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            dialog = null;
        }

        dialog = new AlertDialog.Builder(getContext())
                .setTitle("请选择车次")
                .setSingleChoiceItems(items, 0, (dialog, which) -> {
                    Calendar newCalendar = Calendar.getInstance();
                    newCalendar.add(Calendar.DATE, (0 - which));
                    String date = getData(newCalendar);
                    dialog.dismiss();
                    presenter.getLateDetail(trainNo, date, "");
                }).create();
        dialog.show();
    }

    @Override
    public void updateDetailData(List<CdTrainAllNodeBean> beans) {
        if (null == beans || beans.isEmpty()) {
            Toast.makeText(getContext(), "没有数据", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "updateDetailData:没有数据 ");
            return;
        }
        adapter.setBeans(beans);
    }

    @Override
    public void getLateDetail() {
        Editable editable = trainCode.getText();
        if (editable == null) {
            return;
        }
        final String trainNo = editable.toString().trim();
        String date = getData(calendar);
        presenter.getLateDetail(trainNo, date, "");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }

    /**
     * 日期选择
     *
     * @param calendar 日期
     * @return 格式转化后的日期
     */
    private String getData(Calendar calendar) {
        Locale locale = Locale.getDefault();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", locale);
        Date date = calendar.getTime();
        return sdf.format(date);
    }
}
