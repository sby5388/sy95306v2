package com.by5388.sy95306v2.activity.start;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.by5388.sy95306v2.MainActivity;
import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.activity.BaseActivity;
import com.by5388.sy95306v2.activity.start.presenter.IStartPresenter;
import com.by5388.sy95306v2.activity.start.presenter.StartPresenter;
import com.by5388.sy95306v2.activity.start.view.IStartView;

/**
 * 启动界面
 * 用于加载插入数据库
 *
 * @author by5388
 * @date 20180621
 */
public class StartActivity extends BaseActivity implements IStartView {
    private final static String TAG = "StartActivity";
    /**
     * 读写文件权限：1
     */
    private static final int WRITE_EXTERNAL_STORAGE_PERMISSION = 1;
    /**
     * 双击退出时间内
     */

    private IStartPresenter presenter;
    private AlertDialog updateAlertDialog;

    private TextView textViewAllCount, textViewCurrentCount;
    private ProgressBar progressBar;
    private int stationCount = 0;


    @Override
    protected int getLayoutViewID() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {
        // TODO: 2018/8/9  启动页面不好看
        ConstraintLayout startProgressBar = findViewById(R.id.lly_progress_bar);
    }

    @Override
    protected void loadData() {
        presenter.checkNetworkStatus();
    }

    @Override
    public void initData() {
        presenter = new StartPresenter(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unSubscribe();
            presenter = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case WRITE_EXTERNAL_STORAGE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //授权成功-->写数据库
                    //loadDataBase();
                } else {
                    // 授权失败！
                    Toast.makeText(this, "授权失败！", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void toMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
        //TODO 切换回来之后，重新判断网络状态
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean success = KeyEvent.KEYCODE_BACK == keyCode && KeyEvent.ACTION_DOWN == event.getAction();
        return !success && super.onKeyDown(keyCode, event);
    }


    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void updateFinish() {
        Button finishButton = updateAlertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        finishButton.setText(R.string.finish_update);
        finishButton.setEnabled(true);
        finishButton.setOnClickListener(v -> {
            updateAlertDialog.dismiss();
            Log.d(TAG, "toMainActivity:1 ");
            toMainActivity();
        });

    }

    @Override
    public void updateAllCount(int allCount) {
        Log.d(TAG, "updateAllCount: " + (updateAlertDialog == null));
        if (updateAlertDialog != null) {
            if (updateAlertDialog.isShowing()) {
                updateAlertDialog.dismiss();
            }
            updateAlertDialog = null;
        }

        updateAlertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.updating)
                .setView(getCustomView())
                .setPositiveButton(R.string.ok, null)
                .setNegativeButton(R.string.cancel, null)
                .create();
        //设置对话框外点击不起作用：保持对话框存在
        updateAlertDialog.setCancelable(false);
        updateAlertDialog.setOnShowListener(dialog -> {
            Button positionButton = updateAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            Button negativeButton = updateAlertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            positionButton.setEnabled(false);
            negativeButton.setEnabled(false);
        });
        updateAlertDialog.show();

        Log.d(TAG, "updateAllCount: " + updateAlertDialog.isShowing());
        stationCount = allCount;
        textViewAllCount.setText(String.format(getString(R.string.all_station_count), allCount));
        progressBar.setProgress(0);
    }

    private View getCustomView() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_update_station, new LinearLayout(this), false);
        textViewAllCount = view.findViewById(R.id.textView_all_count);
        textViewCurrentCount = view.findViewById(R.id.textView_current_count);
        progressBar = view.findViewById(R.id.progressBar);
        return view;
    }

    @Override
    public void showUpdateDialog() {
        if (updateAlertDialog != null && updateAlertDialog.isShowing()) {
            return;
        }

        updateAlertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.isUpdating)
                .setMessage(R.string.update_tip)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.ok, null)
                .create();
        //设置对话框外点击不起作用：保持对话框存在
        updateAlertDialog.setCancelable(false);
        updateAlertDialog.setOnShowListener(dialog -> {
            Button positionButton = updateAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            Button negativeButton = updateAlertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            positionButton.setOnClickListener(v -> {
                presenter.startUpdate();
                positionButton.setEnabled(false);

            });
            negativeButton.setOnClickListener(v -> {
                updateAlertDialog.dismiss();
                toMainActivity();
            });
        });

        updateAlertDialog.show();

    }

    @Override
    public void updateProgress(int progress) {
        if (updateAlertDialog == null || !updateAlertDialog.isShowing()) {
            return;
        }
        double percent = progress * 100.0 / stationCount;
        textViewCurrentCount.setText(String.format(getString(R.string.current_station_count), progress));
        progressBar.setProgress((int) percent);
        if (progress == stationCount) {
            presenter.finishUpdate();
        }
    }

    @Override
    public void openNetWorkSetting() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.open_net_work)
                .setMessage(R.string.detail_net_work)
                .setNegativeButton(R.string.cancel, (dialog1, which) -> {
                    dialog1.dismiss();
                    Log.d(TAG, "toMainActivity:3 ");
                    toMainActivity();
                })
                .setPositiveButton(R.string.ok, (dialog1, which) -> {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_SETTINGS);
                    startActivity(intent);
                })
                .create();
        dialog.show();
    }


    @Override
    public void checkNeedUpdate() {
        presenter.checkStationListVersion();
    }

    @Override
    public void clearData() {
        presenter.clearData();
    }

    @Override
    public void insertData() {
        presenter.insertData();
    }

    @Override
    protected boolean isShowActionBar() {
        return false;
    }
}
