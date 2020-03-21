package com.by5388.sy95306v2.module.shanghai.number;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.by5388.sy95306v2.MyListener;
import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.module.shanghai.BaseShFragment;
import com.by5388.sy95306v2.module.shanghai.bean.ShanghaiTrainNumber;
import com.by5388.sy95306v2.module.shanghai.number.presenter.INumberPresenter;
import com.by5388.sy95306v2.module.shanghai.number.presenter.NumberPresenter;
import com.by5388.sy95306v2.module.shanghai.number.view.INumberView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author by5388  on 2018/8/9.
 */

public class ShanghaiNumberFragment extends BaseShFragment implements INumberView, MyListener.UpdateDate {
    public static final String TAG = "SH-NumberFragment";
    private ShNumberAdapter adapter;
    private INumberPresenter presenter;
    private RecyclerView recyclerView;
    private Button buttonSearch, buttonDate;
    private ProgressBar progressBar;
    private TextInputEditText trainCode;
    private MyListener dateListener;
    private Calendar calendar;

    public static ShanghaiNumberFragment newInstance() {
        ShanghaiNumberFragment fragment = new ShanghaiNumberFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        buttonSearch = view.findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(v -> testSearch());
        buttonDate = view.findViewById(R.id.button_query_date);
        buttonDate.setOnClickListener(v -> selectDate(dateListener, calendar));
        progressBar = view.findViewById(R.id.progressBar);
        trainCode = view.findViewById(R.id.textView_train_code);
    }

    private void testSearch() {
        // TODO: 2018/8/2
        final String trainNo = trainCode.getText().toString().trim();
        if (TextUtils.isEmpty(trainNo)) {
            //设置错误提示内容
            trainCode.setError("请输入车次");
            trainCode.requestFocus();
            return;
        }
        final String date = buttonDate.getText().toString().trim().replace("/", "-");
        presenter.search(trainNo, date);

    }

    @Override
    protected void initData() {
        presenter = new NumberPresenter(this);
        adapter = new ShNumberAdapter(getContext(), new ArrayList<>());
        calendar = Calendar.getInstance();
        dateListener = new MyListener(this);
    }

    @Override
    protected void loadData() {
        buttonDate.setText(getData(calendar));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_shang_hai_train_number;
    }


    @Override
    public void updateList(List<ShanghaiTrainNumber> numbers) {
        adapter.setNumbers(numbers);
    }

    @Override
    public void startQuery() {
        buttonSearch.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void finishQuery() {
        buttonSearch.setEnabled(true);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String tip) {
        Toast.makeText(getContext(), tip, Toast.LENGTH_SHORT).show();
        buttonSearch.setEnabled(true);
    }

    @Override
    public void updateView(int year, int month, int dayOfMonth) {
        calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        buttonDate.setText(String.valueOf(getData(calendar)));
    }

    @Override
    public void onDestroy() {
        if (null != presenter) {
            presenter.unSubscribe();
            presenter = null;
        }
        super.onDestroy();
    }
}
