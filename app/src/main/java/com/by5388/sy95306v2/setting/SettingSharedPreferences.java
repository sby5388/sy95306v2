package com.by5388.sy95306v2.setting;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.by5388.sy95306v2.App;

import java.util.Map;

/**
 * @author by5388  on 2018/7/30.
 */

public final class SettingSharedPreferences implements ISettingSharedPreferences {
    private SettingSharedPreferences() {
    }

    public static SettingSharedPreferences getInstance() {
        return SingletonHandler.INSTANCE;
    }

    @Override
    public void put(String key, Object object) {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        Editor editor = sharedPreferences.edit();
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.apply();
    }

    @Override
    public Object getSharedPreference(String key, Object defaultObject) {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        if (defaultObject instanceof String) {
            return sharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultObject);
        } else {
            return sharedPreferences.getString(key, null);
        }
    }


    @Override
    public void remove(String key) {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        sharedPreferences
                .edit()
                .remove(key)
                .apply();
    }

    @Override
    public void clear() {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        sharedPreferences
                .edit()
                .clear()
                .apply();
    }

    @Override
    public boolean contains(String key) {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        return sharedPreferences.contains(key);
    }

    @Override
    public Map<String, ?> getAll() {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        return sharedPreferences.getAll();
    }

    private static final class SingletonHandler {
        private static final SettingSharedPreferences INSTANCE = new SettingSharedPreferences();
    }
}
