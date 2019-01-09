package com.by5388.sy95306;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.by5388.sy95306.fragment.BaseFragment;
import com.by5388.sy95306.fragment.DetailFragment;
import com.by5388.sy95306.fragment.ListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity
        implements RadioGroup.OnCheckedChangeListener {

    private static final int EXIT_TIME = 2000;
    private List<BaseFragment> fragments;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private RadioGroup mRadioGroup;
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
        mRadioGroup = findViewById(R.id.radioGroup);
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    private void initData() {
        initFragment();
    }


    private void initFragment() {
        // TODO: 2019/1/4 Fragment 生命周期注意的事项
        // TODO: 2019/1/4 https://blog.csdn.net/xiaofei_it/article/details/45675497
        fragments = new ArrayList<>();
        fragments.add(ListFragment.newInstance());
        fragments.add(DetailFragment.newInstance());
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        for (BaseFragment fragment : fragments) {
            fragmentTransaction.add(R.id.container, fragment);
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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (group != mRadioGroup) {
            return;
        }
        switch (checkedId) {
            case R.id.radioButton1:
                changeFragment(0);
                break;
            case R.id.radioButton2:
                changeFragment(1);
                break;
            default:
                break;
        }
    }
}
