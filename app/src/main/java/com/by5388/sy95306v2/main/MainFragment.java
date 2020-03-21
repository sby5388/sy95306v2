package com.by5388.sy95306v2.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.by5388.sy95306v2.App;
import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.module.shenyang.ShenYangFragment;
import com.by5388.sy95306v2.module.tiezong.TzFragment;
import com.by5388.sy95306v2.setting.SettingActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * @author Administrator  on 2020/3/20.
 */
public class MainFragment extends Fragment
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String ACTION_REFRESH_STATION_LIST = MainFragment.class.getName() + ".ACTION_REFRESH_STATION_LIST";
    private static final int REQUEST_CODE_UPDATE_STATION_LIST = 200;
    private static final String TAG_SHEN_YANG = ShenYangFragment.class.getName();
    private static final String TAG_TIE_ZONG = TzFragment.class.getName();
    private DrawerLayout mDrawerLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_setting:
                startActivity(new Intent(getContext(), SettingActivity.class));
                return true;
            case R.id.menu_check_update:
                checkUpdateNow();
                return true;
        }
        App.getInstance().toast(item.getTitle().toString());
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Toolbar toolbar = view.findViewById(R.id.toolbar);

        final AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        activity.setSupportActionBar(toolbar);
        View mainView = view.findViewById(R.id.container_main);
        mDrawerLayout = view.findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity,
                mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        final NavigationView navigationView = view.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loadDefaultData();
    }

    private void loadDefaultData() {
        final FragmentManager childFragmentManager = getChildFragmentManager();
        final Fragment shenYangFragment = childFragmentManager.findFragmentByTag(ShenYangFragment.class.getName());
        if (shenYangFragment == null) {
            childFragmentManager.beginTransaction()
                    .add(R.id.container_main, ShenYangFragment.newInstance(), TAG_SHEN_YANG)
                    .commit();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        final FragmentActivity activity = Objects.requireNonNull(getActivity());
        final CharSequence title = item.getTitle();
        switch (item.getItemId()) {
            case R.id.nav_shen_yang:
                changeFragment(TAG_SHEN_YANG);
                activity.setTitle(R.string.title_shen_yang);
                return true;
            case R.id.nav_tz_12306:
                changeFragment(TAG_TIE_ZONG);
                activity.setTitle(R.string.title_tie_zong);
                return true;
            default:
                break;
        }
        App.getInstance().toast(title.toString());
        return false;
    }

    private void changeFragment(final String fragmentTag) {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        final FragmentManager manager = getChildFragmentManager();
        Fragment fragmentByTag = manager.findFragmentByTag(fragmentTag);
        boolean exists = true;
        if (fragmentByTag == null) {
            if (TAG_SHEN_YANG.equals(fragmentTag)) {
                fragmentByTag = ShenYangFragment.newInstance();
            } else if (TAG_TIE_ZONG.equals(fragmentTag)) {
                fragmentByTag = TzFragment.newInstance();
            }
            exists = false;
        }
        if (fragmentByTag == null) {
            //unexpected tag
            return;
        }
        final List<Fragment> fragments = manager.getFragments();
        final FragmentTransaction transaction = manager.beginTransaction();
        for (Fragment f : fragments) {
            //hide all fragment
            transaction.hide(f);
        }
        if (exists) {
            transaction.show(fragmentByTag);
        } else {
            transaction.add(R.id.container_main, fragmentByTag, fragmentTag);
        }
        transaction.commit();
    }


    public boolean onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
        // TODO: 2020/3/20 退出提示
        return false;
    }


    private void checkUpdateNow() {
        final FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager == null) {
            return;
        }
        final UpdateDialogFragment updateDialogFragment = UpdateDialogFragment.newInstance();
        updateDialogFragment.setTargetFragment(this, REQUEST_CODE_UPDATE_STATION_LIST);
        updateDialogFragment.show(fragmentManager, UpdateDialogFragment.class.getName());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_UPDATE_STATION_LIST) {
            // TODO: 2020/3/21
            final Context context = getContext();
            if (context == null) {
                return;
            }
            context.sendBroadcast(new Intent(ACTION_REFRESH_STATION_LIST));
        }
    }
}
