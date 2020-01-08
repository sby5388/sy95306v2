package com.by5388.sy95306v2.main;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.main.presenter.IMainPresenter;
import com.by5388.sy95306v2.main.presenter.MainPresenter;
import com.by5388.sy95306v2.main.view.IMainView;
import com.by5388.sy95306v2.module.chengdu.ChengduFragment;
import com.by5388.sy95306v2.module.guangzhou.GuangzhouFragment;
import com.by5388.sy95306v2.module.shanghai.ShanghaiFragment;
import com.by5388.sy95306v2.module.shenyang.ShenYangFragment;
import com.by5388.sy95306v2.module.tiezong.TzFragment;
import com.by5388.sy95306v2.mvp.FragmentDisplayManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页面
 *
 * @author by5388
 * @date 20180727
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IMainView, FragmentDisplayManager {
    /**
     * 记录用户首次点击返回键的时间
     */
    private long firstTime = 0;
    /**
     * 2秒内双返回键直接退出
     */
    private static final int EXIT_TIME = 2000;
    private static final int TITLE_SHEN_YANG = R.string.title_shen_yang;
    private static final int TITLE_TZ = R.string.title_tie_zhong;
    private static final int TITLE_CD = R.string.title_cheng_du;
    private static final int TITLE_GZ = R.string.title_guang_zhou;
    private static final int TITLE_SH = R.string.title_shang_hai;

    private static final int SHEN_YANG = 0;
    private static final int GUANG_ZHOU = 1;
    private static final int SHANG_HAI = 2;
    private static final int TIE_ZONG = 3;
    private static final int CHENG_DU = 4;

    private List<Fragment> fragments = new ArrayList<>();
    private List<Integer> titles = new ArrayList<>();
    private FragmentManager mFragmentManager;
    private View mainView;
    private IMainPresenter mPresenter;

    private AlertDialog updatingDialog;

    private TextView textViewAllCount, textViewCurrentCount;
    private ProgressBar progressBar;
    private int stationCount = 0;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: 2019/6/15 加载过慢，日志显示耗时1040毫秒
        // TODO: 2019/7/8 加载过慢，1199毫秒
        super.onCreate(savedInstanceState);
        //竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainView = findViewById(R.id.container_main);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initData();
        loadData();
    }

    private void loadData() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        for (Fragment fragment : fragments) {
            fragmentTransaction.add(R.id.container_main, fragment);
            fragmentTransaction.hide(fragment);
        }
        fragmentTransaction.commit();
        changeFragment(SHEN_YANG);
        mPresenter.checkUpdate();
    }

    private void initData() {
        fragments = new ArrayList<>();
        // TODO: 2019/11/26 一开始就初始化这么多的数据 需要处理
        // FIXME: 2019/11/26
        fragments.add(ShenYangFragment.newInstance());
        fragments.add(GuangzhouFragment.newInstance());
        fragments.add(ShanghaiFragment.newInstance());
        fragments.add(TzFragment.newInstance());
//        fragments.add(ChengduFragment.newInstance());
        titles = new ArrayList<>();
        titles.add(TITLE_SHEN_YANG);
        titles.add(TITLE_GZ);
        titles.add(TITLE_SH);
        titles.add(TITLE_TZ);
//        titles.add(TITLE_CD);

        mFragmentManager = getSupportFragmentManager();
        mPresenter = new MainPresenter(this);
    }

    private void changeFragment(int position) {
        if (position >= fragments.size()) {
            return;
        }
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        for (Fragment fragment : fragments) {
            fragmentTransaction.hide(fragment);
        }
        fragmentTransaction.show(fragments.get(position));
        fragmentTransaction.commit();
        setTitle(titles.get(position));
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_shen_yang:
                changeFragment(SHEN_YANG);
                break;
            case R.id.nav_guang_tie:
                changeFragment(GUANG_ZHOU);
                break;
            case R.id.nav_shang_hai:
                changeFragment(SHANG_HAI);
                break;
            case R.id.nav_tz_12306:
                changeFragment(TIE_ZONG);
                break;
            case R.id.nav_cd_12306:
//                changeFragment(CHENG_DU);
                break;
            case R.id.about:
                // TODO: 2018/11/12  show about dialog
                break;
            default:
                break;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 双击返回键 彻底关闭应用
     *
     * @param keyCode 按键
     * @param event   按键事件：抬起或者按下
     * @return true:自己处理，false :系统处理
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode && KeyEvent.ACTION_DOWN == event.getAction()) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > EXIT_TIME) {
                Snackbar snackbar = Snackbar.make(mainView, "是否退出程序", Snackbar.LENGTH_LONG)
                        .setAction("关闭", v -> finish());
                // TODO: 2018/12/17 这里可以
                snackbar.getView().requestFocus();
                snackbar.show();
                firstTime = secondTime;
                return true;
            } else {
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onNotifyUpdate() {
        //使用一个对话框来提供升级的入口
        new AlertDialog.Builder(this)
                .setTitle(R.string.isUpdating)
                .setMessage(R.string.update_tip)
                .setPositiveButton(android.R.string.ok, (dialog1, which) -> mPresenter.startUpdate())
                .setNegativeButton(android.R.string.cancel, null)
                .setCancelable(true)
                .show();
    }

    @Override
    public void onShowUpdating() {
        checkUpdatingDialog();
        AlertDialog alertDialog = updatingDialog;
        alertDialog.show();
    }


    private void checkUpdatingDialog() {
        if (updatingDialog == null) {
            updatingDialog = new AlertDialog.Builder(this)
                    .setTitle(R.string.updating)
                    .setView(getDialogView())
                    .setPositiveButton(android.R.string.ok, (dialog, which) -> dialog.dismiss())
                    .create();
            updatingDialog.setCancelable(false);
            updatingDialog.setOnCancelListener(dialog -> {
                // TODO: 2019/1/11 刷新Fragment
            });
        }
    }

    private View getDialogView() {
        View view = getLayoutInflater().inflate(R.layout.dialog_update_station, new LinearLayout(this), false);
        textViewAllCount = view.findViewById(R.id.textView_all_count);
        textViewCurrentCount = view.findViewById(R.id.textView_current_count);
        progressBar = view.findViewById(R.id.progressBar);
        return view;
    }

    @Override
    public void onStartChecking() {
        Toast.makeText(this, R.string.check_updating, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFinishChecked() {
        Toast.makeText(this, R.string.lastest_version, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateAllCount(int allCount) {
        stationCount = allCount;
        textViewAllCount.setText(String.format(getString(R.string.all_station_count), allCount));
        progressBar.setProgress(0);
    }

    @Override
    public void updateProgress(int progress) {
        double percent = progress * 100.0 / stationCount;
        textViewCurrentCount.setText(String.format(getString(R.string.current_station_count), progress));
        progressBar.setProgress((int) percent);
    }

    @Override
    public void openNetWorkSetting() {
        onTip("网络异常");
    }

    @Override
    public void onTip(String tip) {
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFinishUpdate() {
        final AlertDialog alertDialog = updatingDialog;
        alertDialog.setCancelable(true);
        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        positiveButton.setEnabled(true);
        positiveButton.setText(R.string.finish_update);
    }

    @Override
    public void onFragmentAttach(Fragment fragment) {

    }
}
