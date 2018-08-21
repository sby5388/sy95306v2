package com.by5388.sy95306v2.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.by5388.sy95306v2.App;

import java.util.Map;

/**
 * @author by5388  on 2018/7/30.
 */

public class SettingSharedPreferences implements ISettingSharedPreferences {
    private static final String FILE_NAME = "by5388";
    private final SharedPreferences sharedPreferences;

    private SettingSharedPreferences() {
        sharedPreferences = App.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public static SettingSharedPreferences getInstance() {
        return SingletonHandler.instance;
    }

    @Override
    public void put(String key, Object object) {
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
        sharedPreferences
                .edit()
                .remove(key)
                .apply();
    }

    @Override
    public void clear() {
        sharedPreferences
                .edit()
                .clear()
                .apply();
    }

    @Override
    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    @Override
    public Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }

    private static class SingletonHandler {
        private static final SettingSharedPreferences instance = new SettingSharedPreferences();
    }
}
