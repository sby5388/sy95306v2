package com.by5388.sy95306v2.setting;

import java.util.Map;

/**
 * @author by5388  on 2018/7/30.
 */

public interface ISettingSharedPreferences {

    /**
     * 存储对象(写)，参考 https://www.cnblogs.com/1925yiyi/p/7489367.html
     * 抽象。封装
     *
     * @param key    键
     * @param object 值
     */
    void put(String key, Object object);

    /**
     * 读取
     *
     * @param key           键
     * @param defaultObject 类型+默认值
     * @return 值
     */
    Object getSharedPreference(String key, Object defaultObject);

    /**
     * 移除键值
     *
     * @param key 键
     */
    void remove(String key);

    /**
     * 清除设置
     */
    void clear();

    /**
     * 是否存在
     *
     * @param key 键
     * @return true:存在;false:不存在
     */
    boolean contains(String key);

    /**
     * 所有的键值对
     * @return 返回所有的键值对
     */
    Map<String, ?> getAll();
}
