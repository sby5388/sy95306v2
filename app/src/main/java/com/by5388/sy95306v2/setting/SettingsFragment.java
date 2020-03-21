package com.by5388.sy95306v2.setting;


import android.os.Bundle;

import com.by5388.sy95306v2.R;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;

/**
 * @author Administrator  on 2020/3/20.
 */
public class SettingsFragment extends PreferenceFragmentCompat {
    public SettingsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

    }
}
