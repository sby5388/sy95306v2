package com.by5388.sy95306v2.main;

import android.os.Bundle;

import com.by5388.sy95306v2.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class TempActivity extends AppCompatActivity {

    private MainFragment mMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        final FragmentManager manager = getSupportFragmentManager();
        final Fragment fragment = manager.findFragmentById(R.id.container);
        if (fragment == null) {
            manager.beginTransaction().add(R.id.container, new MainFragment()).commit();
        }
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof MainFragment) {
            mMainFragment = (MainFragment) fragment;
        }
    }

    @Override
    public void onBackPressed() {
        if (mMainFragment == null) {
            super.onBackPressed();
            return;
        }
        final boolean handle = mMainFragment.onBackPressed();
        if (!handle) {
            super.onBackPressed();
        }
    }
}
