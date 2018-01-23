package com.brioal.baselib.utils;

import android.view.View;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioa on 2018/1/11.
 */

public class ListenerUtil {

    /**
     * 设置点击事件
     *
     * @param view
     * @param listener
     */
    public static void setOnClickListener(View view, View.OnClickListener listener) {
        if (view == null) {
            return;
        }
        if (listener == null) {
            return;
        }
        view.setOnClickListener(listener);
    }
}
