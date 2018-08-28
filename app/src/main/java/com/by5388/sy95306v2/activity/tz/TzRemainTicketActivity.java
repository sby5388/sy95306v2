package com.by5388.sy95306v2.activity.tz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.activity.BaseActivity;
import com.by5388.sy95306v2.activity.tz.view.ICombinationView;
import com.by5388.sy95306v2.bean.IRemainingTicket;

import java.util.List;

/**
 * 铁总余票查询多功能界面
 *
 * @author by5388  on 2018/8/26.
 */
public class TzRemainTicketActivity extends BaseActivity implements ICombinationView {
    private static final String TAG = "TzRemainTicket";
    private static final String KEY_BUNDLE = "bundle";
    private static final String KEY_TRAIN_CODE = "trainCode";
    private static final String KEY_DATE = "date";
    private static final String KEY_FROM = "fromStation";
    private static final String KEY_TO = "toStation";
    private static final String KEY_RAND_CODE = "randCode";

    public static Intent toTzRemainTicketActivity(@NonNull Context context,
                                                  String trainCode,
                                                  String date,
                                                  String fromStation,
                                                  String toStation,
                                                  String randCode
    ) {
        Intent intent = new Intent(context, TzRemainTicketActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TRAIN_CODE, trainCode);
        bundle.putString(KEY_DATE, date);
        bundle.putString(KEY_FROM, fromStation);
        bundle.putString(KEY_TO, toStation);
        bundle.putString(KEY_RAND_CODE, randCode);
        intent.putExtra(KEY_BUNDLE, bundle);
        return intent;
    }

    @Override
    protected boolean isShowActionBar() {
        return true;
    }

    @Override
    protected int getLayoutViewID() {
        return R.layout.activity_tz_combination;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(KEY_BUNDLE);
        if (null == bundle) {
            return;
        }
        Log.d(TAG, "loadData: " + bundle.getString(KEY_TO));
        Log.d(TAG, "loadData: " + bundle.getString(KEY_FROM));
        Log.d(TAG, "loadData: " + bundle.getString(KEY_RAND_CODE));
        Log.d(TAG, "loadData: " + bundle.getString(KEY_DATE));
        Log.d(TAG, "loadData: " + bundle.getString(KEY_TRAIN_CODE));

    }

    @Override
    public void addIRemainingTicket(IRemainingTicket ticket) {

    }

    @Override
    public void showFilterDialog() {

    }

    @Override
    public void updateList(List<IRemainingTicket> dataBeans) {

    }

    @Override
    public void updateCheckCodeBitmap(Bitmap bitmap) {

    }

    @Override
    public void checkPassCode(boolean isChecked) {

    }

    @Override
    public void clearPassCode() {

    }

    @Override
    public void startQuery() {

    }

    @Override
    public void finishQuery() {

    }

    @Override
    public void showError(String tip) {

    }
}
