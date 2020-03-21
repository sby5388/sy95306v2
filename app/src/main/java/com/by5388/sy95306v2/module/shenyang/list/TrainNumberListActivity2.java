package com.by5388.sy95306v2.module.shenyang.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.module.shenyang.bean.Station;
import com.by5388.sy95306v2.module.shenyang.net.api.SyService;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class TrainNumberListActivity2 extends AppCompatActivity {
    private static final String KEY_TRAIN_PARAM = "KEY_TRAIN_PARAM";

    public static Intent newIntent(final Context context, final int selectedDate, final Station fromStation, final Station toStation) {
        final Intent intent = new Intent(context, TrainNumberListActivity2.class);
        final Bundle bundle = new Bundle();
        bundle.putInt(SyService.TRAIN_DATE, selectedDate);
        bundle.putString(SyService.FROM_STATION, fromStation.getNameUpper());
        bundle.putString(SyService.TO_STATION, toStation.getNameUpper());
        intent.putExtra(KEY_TRAIN_PARAM, bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setContentView(R.layout.activity_train_number_list2);
        // TODO: 2020/3/19
        final FragmentManager fm = getSupportFragmentManager();
        final Fragment fragment = fm.findFragmentById(R.id.container);
        final Bundle bundle = getIntent().getBundleExtra(KEY_TRAIN_PARAM);
        if (bundle == null) {
            return;
        }
        if (fragment == null) {
            fm.beginTransaction()
                    .add(R.id.container, TrainNumberListFragment.newInstance(bundle))
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
