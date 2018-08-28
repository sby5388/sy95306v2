package com.by5388.sy95306v2;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import com.by5388.sy95306v2.fragment.BaseFragment;
import com.by5388.sy95306v2.fragment.ChengduFragment;
import com.by5388.sy95306v2.fragment.GuangzhouFragment;
import com.by5388.sy95306v2.fragment.ShanghaiFragment;
import com.by5388.sy95306v2.fragment.ShenYangFragment;
import com.by5388.sy95306v2.fragment.TzFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页面
 *
 * @author by5388
 * @date 20180727
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseFragment.OnFragmentListener {

    /**
     * 记录用户首次点击返回键的时间
     */
    private long firstTime = 0;
    /**
     * 2秒内双返回键直接退出
     */
    private static final int EXIT_TIME = 2000;
    private static final String TITLE_SHEN_YANG = "沈阳12306";
    private static final String TITLE_TZ = "铁总12306";
    private static final String TITLE_CD = "成都12306";
    private static final String TITLE_GZ = "广铁12306";
    private static final String TITLE_SH = "上海12306";


    private Toolbar toolbar;
    private List<Fragment> fragments;
    private List<String> titles;
    private FragmentManager fragmentManager;
    private View mainView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        mainView = findViewById(R.id.container_main);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initData();
        loadData();


    }

    private void loadData() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (Fragment fragment : fragments) {
            fragmentTransaction.add(R.id.container_main, fragment);
            fragmentTransaction.hide(fragment);
        }
        fragmentTransaction.commit();
        changeShenYang();
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(ShenYangFragment.newInstance());
        fragments.add(GuangzhouFragment.newInstance());
        fragments.add(ShanghaiFragment.newInstance());
        fragments.add(TzFragment.newInstance());
        fragments.add(ChengduFragment.newInstance());
        titles = new ArrayList<>();
        titles.add(TITLE_SHEN_YANG);
        titles.add(TITLE_GZ);
        titles.add(TITLE_SH);
        titles.add(TITLE_TZ);
        titles.add(TITLE_CD);

        fragmentManager = getSupportFragmentManager();
    }

    private void changeShenYang() {
        changeFragment(0);
    }

    private void changeFragment(int position) {
        if (position >= fragments.size()) {
            return;
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (Fragment fragment : fragments) {
            fragmentTransaction.hide(fragment);
        }
        fragmentTransaction.show(fragments.get(position));
        fragmentTransaction.commit();
        setTitle(titles.get(position));
    }

    private void changeGuangzhou() {
        changeFragment(1);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_shen_yang:
                changeShenYang();
                break;
            case R.id.nav_guang_tie:
                changeGuangzhou();
                break;
            case R.id.nav_shang_hai:
                changeShanghai();
                break;
            case R.id.nav_tz_12306:
                changeTz();
                break;
            case R.id.nav_cd_12306:
                changeChengdu();
                break;
            case R.id.nav_send:

                break;
            default:
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeChengdu() {
        changeFragment(4);
    }

    private void changeTz() {
        changeFragment(3);
    }

    private void changeShanghai() {
        changeFragment(2);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // TODO: 2018/7/28
    }

    /**
     * 双击返回键 彻底关闭应用
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode && KeyEvent.ACTION_DOWN == event.getAction()) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > EXIT_TIME) {
                Snackbar.make(mainView, "是否退出程序", Snackbar.LENGTH_LONG)
                        .setAction("关闭", v -> finish()).show();
                firstTime = secondTime;
                return true;
            } else {
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
