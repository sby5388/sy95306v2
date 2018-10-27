package com.by5388.sy95306;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.Toast;

import com.by5388.sy95306.fragment.BaseFragment;
import com.by5388.sy95306.fragment.FirstFragment;
import com.by5388.sy95306.fragment.SecondFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BaseFragment.OnFragmentInteractionListener {

    private static final int EXIT_TIME = 2000;
    private List<BaseFragment> fragments;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Button button1, button2;

    /**
     * 记录用户首次点击返回键的时间
     */
    private long firstTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //强制竖屏
        //切换横竖屏时  出现组件重叠：已解决
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initView() {
        button1 = findViewById(R.id.button_main_p2p);
        button2 = findViewById(R.id.button_main_number);
        button1.setOnClickListener(v -> {
            button1.setEnabled(false);
            button2.setEnabled(true);
            changeFragment(0);
        });
        button2.setOnClickListener(v -> {
            button1.setEnabled(true);
            button2.setEnabled(false);
            changeFragment(1);
        });
    }

    private void initData() {
        initFragment();
    }


    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(FirstFragment.newInstance());
        fragments.add(SecondFragment.newInstance());
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        for (BaseFragment fragment : fragments) {
            fragmentTransaction.add(R.id.center, fragment);
            fragmentTransaction.hide(fragment);
        }
        fragmentTransaction.show(fragments.get(0));
        fragmentTransaction.commit();
    }

    /**
     * 切换显示fragment
     *
     * @param index
     */
    private void changeFragment(int index) {
        fragmentTransaction = fragmentManager.beginTransaction();
        for (BaseFragment fragment : fragments) {
            fragmentTransaction.hide(fragment);
        }
        fragmentTransaction.show(fragments.get(index));
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

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
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = secondTime;
                return true;
            } else {
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
