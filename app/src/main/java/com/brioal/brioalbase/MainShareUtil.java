package com.brioal.brioalbase;

import android.content.Context;

import com.brioal.baselib.base.BrioalBaseSharePreference;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by Brioal on 2017/9/16.
 */

public class MainShareUtil extends BrioalBaseSharePreference {
    private final String INDEX_NAME = "Name";

    public MainShareUtil(Context context) {
        super(context);
    }

    @Override
    protected String getBrioalShareFerenceName() {
        return "MainShareUtil";
    }

    public void saveName(String name) {
        saveAndFlush(INDEX_NAME, name);
    }

    public String readName() {
        return getString(INDEX_NAME, "错误");
    }

}
