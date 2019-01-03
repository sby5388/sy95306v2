package com.by5388.sy95306v2.shanghai.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.shanghai.bean.ShanghaiTrainNumber;
import com.by5388.sy95306v2.shanghai.dialog.view.IShNumberDialogView;
import com.by5388.sy95306v2.shanghai.number.ShNumberAdapter;
import com.by5388.sy95306v2.shanghai.number.presenter.INumberPresenter;
import com.by5388.sy95306v2.shanghai.number.presenter.NumberPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by5388  on 2018/8/10.
 */

public class ShNumberDialog implements IShNumberDialogView {
    public static final String TAG = "ShNumberDialog";
    private final ShNumberAdapter adapter;
    private final Context context;
    private final AlertDialog dialog;
    private View dialogView;
    private View dialogTitleView;
    private RecyclerView recyclerView;
    private final LayoutInflater inflater;
    private TextView trainCode, date, trainName, startTime, endTime;
    private final INumberPresenter presenter;
    private ProgressBar progressBar;
    private final List<ShanghaiTrainNumber> empty;

    public ShNumberDialog(Context context) {
        this.context = context;
        presenter = new NumberPresenter(this);
        inflater = LayoutInflater.from(context);
        empty = new ArrayList<>();
        initMainView();
        initTitleView();
        adapter = new ShNumberAdapter(context, empty);
        dialog = new AlertDialog.Builder(context)
                .setCustomTitle(dialogTitleView)
                .setView(dialogView)
                .setPositiveButton(R.string.ok, null)
                .create();
    }

    private void initMainView() {
        dialogView = inflater.inflate(R.layout.dialog_main_shang_hai, new LinearLayout(context), false);
        recyclerView = dialogView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        progressBar = dialogView.findViewById(R.id.progress_bar);
    }

    private void initTitleView() {
        dialogTitleView = inflater.inflate(R.layout.dialog_title_shang_hai, new LinearLayout(context), false);
        trainCode = dialogTitleView.findViewById(R.id.trainCode);
        date = dialogTitleView.findViewById(R.id.date);
        trainName = dialogTitleView.findViewById(R.id.trainName);
        startTime = dialogTitleView.findViewById(R.id.startTime);
        endTime = dialogTitleView.findViewById(R.id.endTime);
    }

    @Override
    public void show() {
        dialog.show();
    }

    @Override
    public void updateList(List<ShanghaiTrainNumber> numbers) {
        adapter.setNumbers(numbers);
        //始发站-终到站、名称、时间
        trainName.setText(getTrainName(numbers));
        startTime.setText(numbers.get(0).getStartTime());
        endTime.setText(numbers.get(numbers.size() - 1).getEndTime());
    }

    private static String getTrainName(@NonNull List<ShanghaiTrainNumber> numbers) {
        if (numbers.isEmpty()) {
            return "";
        }
        if (numbers.size() == 1) {
            return numbers.get(0).getStationName();
        }
        return numbers.get(0).getStationName() + "-" + numbers.get(numbers.size() - 1).getStationName();
    }


    @Override
    public void dismiss() {
        dialog.dismiss();
    }

    @Override
    public void startQuery() {
        adapter.setNumbers(empty);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        dialog.show();
    }

    @Override
    public void finishQuery() {
        recyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean isShowing() {
        return dialog.isShowing();
    }

    @Override
    public void showError(String tip) {
        Toast.makeText(context, tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public IShNumberDialogView search(String trainCode, String trainDate) {
        this.trainCode.setText(trainCode);
        this.date.setText(trainDate);
        presenter.search(trainCode, trainDate);
        return this;
    }
}
