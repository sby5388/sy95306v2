package com.by5388.sy95306v2.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

/**
 * @author Administrator  on 2020/3/19.
 */
public class TrainFilterDialog2 extends DialogFragment {
    public static final String TAG = TrainFilterDialog2.class.getSimpleName();
    private static final String PARAM_UPDATE_TITLE = "param_update_title";
    private ITrainType mTrainType;
    private boolean[] mShowBooleans;

    public static TrainFilterDialog2 newInstance() {
        return new TrainFilterDialog2();
    }

    public static String getTitle(Intent intent) {
        if (intent == null) {
            return "";
        }
        if (!intent.hasExtra(PARAM_UPDATE_TITLE)) {
            return "";
        }
        return intent.getStringExtra(PARAM_UPDATE_TITLE);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTrainType = TrainType.getInstance();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Context context = getContext();
        if (context == null) {
            return super.onCreateDialog(savedInstanceState);
        }
        return new AlertDialog.Builder(context)
                .setTitle("选择类型")
                .setMultiChoiceItems(getTitles(), getChecks(), (dialog, which, isChecked) -> {
                    //TODO 虽然这个方法 是空的，但是没有的话会出异常
                })
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    mTrainType.updateCheck(mShowBooleans);
                    sendResult();
                })
                .setNegativeButton(android.R.string.cancel, null)
                .setCancelable(true)
                .create();
    }

    private String[] getTitles() {
        return mTrainType.getNames();
    }

    private boolean[] getChecks() {
        final boolean[] checks = mTrainType.getChecks();
        if (mShowBooleans == null) {
            mShowBooleans = new boolean[checks.length];
        }
        System.arraycopy(checks, 0, mShowBooleans, 0, checks.length);
        return mShowBooleans;
    }

    private void sendResult() {
        final Fragment targetFragment = getTargetFragment();
        if (targetFragment == null) {
            return;
        }
        final Intent intent = new Intent();
        intent.putExtra(PARAM_UPDATE_TITLE, mTrainType.getTitle());
        final int requestCode = getTargetRequestCode();
        targetFragment.onActivityResult(requestCode, Activity.RESULT_OK, intent);
    }

}
