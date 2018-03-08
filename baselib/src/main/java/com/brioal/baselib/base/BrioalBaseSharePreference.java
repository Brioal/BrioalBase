package com.brioal.baselib.base;

import android.content.Context;
import android.content.SharedPreferences;

import com.brioal.baselib.utils.TextUtil;

/**
 * 首选项基类
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by Brioal on 2017/9/16.
 */

public abstract class BrioalBaseSharePreference {
    protected Context mContext;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public BrioalBaseSharePreference(Context context) {
        mContext = context;
        initPreference();
    }

    /**
     * 初始化首选项
     */
    private void initPreference() {
        if (!TextUtil.isStringAvailable(getBrioalShareFerenceName())) {
            return;
        }
        try {
            mSharedPreferences = mContext.getSharedPreferences(getBrioalShareFerenceName(), Context.MODE_PRIVATE);
            mEditor = mSharedPreferences.edit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * String
     *
     * @param name
     * @param result
     */
    protected void saveAndFlush(String name, String result) {
        try {
            mEditor.putString(name, result);
            mEditor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存Boolean类型
     *
     * @param name
     * @param result
     */
    protected void saveAndFlush(String name, boolean result) {
        try {
            mEditor.putBoolean(name, result);
            mEditor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存 float
     *
     * @param name
     * @param result
     */
    protected void saveAndFlush(String name, float result) {
        try {
            mEditor.putFloat(name, result);
            mEditor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * int
     *
     * @param name
     * @param result
     */
    protected void saveAndFlush(String name, int result) {
        try {
            mEditor.putInt(name, result);
            mEditor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Long
     *
     * @param name
     * @param result
     */
    protected void saveAndFlush(String name, long result) {
        try {
            mEditor.putLong(name, result);
            mEditor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * getString
     *
     * @param name
     * @param defaultStr
     * @return
     */
    protected String getString(String name, String defaultStr) {
        try {
            String result = mSharedPreferences.getString(name, defaultStr);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultStr;
    }

    /**
     * getInt
     *
     * @param name
     * @param defaultInt
     * @return
     */
    protected int getInt(String name, int defaultInt) {
        try {
            int result = mSharedPreferences.getInt(name, defaultInt);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultInt;
    }

    /**
     * getLong
     *
     * @param name
     * @param defaultLong
     * @return
     */
    protected long getLong(String name, int defaultLong) {
        try {
            long result = mSharedPreferences.getLong(name, defaultLong);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultLong;
    }

    /**
     * getBoolean
     *
     * @param name
     * @param defaultBoolean
     * @return
     */
    protected boolean getBoolean(String name, boolean defaultBoolean) {
        try {
            boolean result = mSharedPreferences.getBoolean(name, defaultBoolean);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultBoolean;
    }

    /**
     * getFloat
     *
     * @param name
     * @param defaultFloat
     * @return
     */
    protected float getFloat(String name, float defaultFloat) {
        try {
            float result = mSharedPreferences.getFloat(name, defaultFloat);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultFloat;
    }


    /**
     * 返回SharePreference的名称
     *
     * @return
     */
    protected abstract String getBrioalShareFerenceName();


}
