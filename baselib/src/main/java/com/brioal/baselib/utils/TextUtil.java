package com.brioal.baselib.utils;

import android.widget.TextView;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioa on 2018/1/11.
 */

public class TextUtil {

    /**
     * 设置文字
     *
     * @param text
     * @param textView
     */
    public static void setText(String text, TextView textView) {
        if (textView == null) {
            return;
        }
        if (text == null) {
            textView.setText("未知");
            return;
        }
        textView.setText(text + "");
    }
}
