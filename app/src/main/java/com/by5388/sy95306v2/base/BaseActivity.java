package com.by5388.sy95306v2.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * @author by5388  on 2018/7/28.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected static final String DATA_BUNDLE = "bundle";

    public static final String DATA_STATION_CODE = "code";

    /**
     * 是否显示 actionBar
     *
     * @return 是否显示 actionBar
     */
    protected abstract boolean isShowActionBar();


    /**
     * 布局文件ID
     *
     * @return 布局文件ID
     */
    @LayoutRes
    protected abstract int getLayoutViewID();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化布局
     */
    protected abstract void initView();

    /**
     * 加载数据
     */
    protected abstract void loadData();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //强制竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayoutViewID());
        if (isShowActionBar()) {
            ActionBar actionBar = getSupportActionBar();
            if (null != actionBar) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
        initData();
        initView();
        loadData();
    }

}
