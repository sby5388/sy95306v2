package com.by5388.sy95306v2.main;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.main.presenter.IMainPresenter;
import com.by5388.sy95306v2.main.presenter.MainPresenter;
import com.by5388.sy95306v2.main.view.IMainView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

/**
 * @author Administrator  on 2020/3/20.
 */
public class UpdateDialogFragment extends DialogFragment implements IMainView {
    public static final String TAG = MainPresenter.class.getSimpleName();
    private IMainPresenter mPresenter;
    private AlertDialog mAlertDialog;
    private View mRootView;
    private Handler mHandler = new Handler();

    /**
     * 当前可见的View
     */
    private View mViewVisible;

    private View mViewChecking;
    private View mViewChecked;
    private View mViewUpdating;
    private View mViewUpdateFinish;
    private ProgressBar mProgressBar;

    private TextView mTextViewSummary;
    private TextView mTextViewUpdating;

    private Button mButtonOk;
    private Button mButtonCancel;


    public UpdateDialogFragment() {
        //empty
    }

    public static UpdateDialogFragment newInstance() {
        return new UpdateDialogFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MainPresenter(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Context context = getContext();
        if (context == null) {
            return super.onCreateDialog(savedInstanceState);
        }
        if (mAlertDialog != null) {
            if (mAlertDialog.isShowing()) {
                mAlertDialog.dismiss();
            }
            return mAlertDialog;
        }

        mAlertDialog = new AlertDialog.Builder(context)
                .setTitle(R.string.check_updating)
                .setView(createMainView())
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
        mPresenter.checkUpdate();
        return mAlertDialog;
    }


    private View createMainView() {
        final Context context = getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);
        mRootView = inflater.inflate(R.layout.dialog_update, new LinearLayout(context), false);
        mViewChecking = mRootView.findViewById(R.id.view_checking);
        mViewChecked = mRootView.findViewById(R.id.view_checked);
        mViewUpdating = mRootView.findViewById(R.id.view_updating);
        mViewUpdateFinish = mRootView.findViewById(R.id.view_update_finish);
        mProgressBar = mRootView.findViewById(R.id.progress_horizontal);
        mTextViewSummary = mRootView.findViewById(R.id.textView_show_update_summary);
        mTextViewUpdating = mRootView.findViewById(R.id.textView_show_update_progress);
        mButtonOk = mRootView.findViewById(R.id.button_ok);
        mButtonCancel = mRootView.findViewById(R.id.button_cancel);
        mButtonCancel.setOnClickListener(v -> this.dismiss());
        mButtonOk.setOnClickListener(v -> handleButton());
        mViewVisible = mViewChecking;

        return mRootView;
    }


    private void handleButton() {
        if (mViewVisible == null) {
            return;
        }
        // TODO: 2020/3/20 点击事件 根据当前显示的View来响应不同的事件
        if (mViewVisible == mViewChecked) {
            mPresenter.startUpdate();
            mButtonCancel.setEnabled(false);
            mButtonOk.setEnabled(false);
        } else if (mViewVisible == mViewUpdateFinish) {
            refreshView();
            this.dismiss();
        }


    }

    @Override
    public void onNotifyUpdate() {
        mAlertDialog.setTitle(R.string.update_tip_simple);
        mViewVisible.setVisibility(View.GONE);
        mTextViewSummary.setText("发现新的车站列表版本，是否马上更新？");
        mViewChecked.setVisibility(View.VISIBLE);
        mViewVisible = mViewChecked;
        mButtonOk.setEnabled(true);
    }

    @Override
    public void onStartChecking() {
        // empty
        mButtonOk.setEnabled(false);
    }

    @Override
    public void onFinishChecked() {
        // TODO: 2020/3/20
        mButtonOk.setEnabled(true);
        mButtonCancel.setEnabled(true);
        mAlertDialog.setCancelable(true);
        //App.getInstance().toast(R.string.lastest_version);
        //this.dismiss();
    }

    @Override
    public void updateAllCount(final int allCount) {
        mTextViewSummary.setText(getString(R.string.find_station_count, allCount));
        mViewVisible.setVisibility(View.GONE);
        mViewUpdating.setVisibility(View.VISIBLE);
        mViewVisible = mViewUpdating;
        mProgressBar.setMax(allCount);
        mProgressBar.setProgress(0);
    }

    @Override
    public void updateProgress(int progress) {
        mTextViewUpdating.setText(String.format(getString(R.string.current_station_count), progress));
        mProgressBar.setProgress(progress);
        mButtonOk.setEnabled(false);
        mButtonCancel.setEnabled(false);
        mAlertDialog.setCancelable(false);
    }

    @Override
    public void openNetWorkSetting() {
        mViewVisible.setVisibility(View.GONE);
        mTextViewSummary.setText("网络异常，请重试");
        mViewChecked.setVisibility(View.VISIBLE);
//        App.getInstance().toast("网络异常，请重试");
    }

    @Override
    public void onErrorTip(String tip) {
        mViewVisible.setVisibility(View.GONE);
        mTextViewSummary.setText(tip);
        mViewChecked.setVisibility(View.VISIBLE);
        mViewVisible = mViewChecked;
    }

    @Override
    public void onShowUpdating() {
        mAlertDialog.setTitle(R.string.updating);
        mViewVisible.setVisibility(View.GONE);
        mViewUpdating.setVisibility(View.VISIBLE);
        mAlertDialog.setCancelable(false);
        mViewVisible = mViewUpdating;
    }

    @Override
    public void onFinishUpdate() {
        mAlertDialog.setTitle(R.string.update_finish);
        mAlertDialog.setCancelable(true);
        mViewVisible.setVisibility(View.GONE);
        mViewUpdateFinish.setVisibility(View.VISIBLE);
        mViewVisible = mViewUpdateFinish;
        mButtonCancel.setEnabled(true);
        mButtonOk.setEnabled(true);
    }

    /**
     * 刷新主页面
     */
    private void refreshView() {
        final Fragment targetFragment = getTargetFragment();
        if (targetFragment == null) {
            return;
        }
        targetFragment.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, null);
    }
}
