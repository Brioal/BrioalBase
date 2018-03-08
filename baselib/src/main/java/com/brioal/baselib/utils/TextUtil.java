package com.brioal.baselib.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioa on 2018/1/11.
 */

public class TextUtil {


    /**
     * 给关键字设置颜色
     *
     * @param textView
     * @param pattern
     * @param color
     */
    public static void setTextColorSpecial(TextView textView, String pattern, int color) {
        String allText = textView.getText().toString();
        if (allText == null) {
            return;
        }
        SpannableString ss = matcherSearchText(color, allText, pattern);
        textView.setText(ss);
    }

    /**
     * 匹配关键字,设置颜色
     *
     * @param color
     * @param text
     * @param keyword
     * @return
     */
    public static SpannableString matcherSearchText(int color, String text, String keyword) {
        SpannableString ss = new SpannableString(text);
        Pattern pattern = Pattern.compile(keyword);
        Matcher matcher = pattern.matcher(ss);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            ss.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }


    /**
     * @param front    前缀
     * @param data     数据
     * @param textView 部件
     */
    public static void setText(String front, String data, TextView textView) {
        setText(front, data, "未知", textView);
    }


    /**
     * @param data     数据
     * @param textView
     */
    public static void setText(String data, TextView textView) {
        setText("", data, "未知", textView);
    }

    /**
     * @param front
     * @param data
     * @param error
     * @param textView
     */
    public static void setText(String front, String data, String error, TextView textView) {
        if (isStringAvailable(data)) {
            //显示
            textView.setText(front + data);
        } else {
            //显示未知
            textView.setText(front + error);
        }
    }

    /**
     * @param front
     * @param data
     * @param error
     * @param textView
     */
    public static void setTextAddNull(String front, String data, String error, TextView textView) {
        if (isStringAvaliableAddNull(data)) {
            //显示
            textView.setText(front + data);
        } else {
            //显示未知
            textView.setText(front + error);
        }
    }

    /**
     * 返回一个冒号
     *
     * @return
     */
    public static String getSign() {
        return "：";
    }

    /**
     * 判断字符串是否可用
     *
     * @param str
     * @return
     */
    public static boolean isStringAvaliableAddNull(String str) {
        if (str == null) {
            return false;
        }
        if (str.equals("")) {
            return false;
        }
        if (str.equals("null")) {
            return false;
        }
        return true;
    }

    /**
     * 判断字符串是否可用
     *
     * @param str
     * @return
     */
    public static boolean isStringAvailable(String str) {
        if (str == null) {
            return false;
        }
        if (str.equals("")) {
            return false;
        }
        return true;
    }


    /**
     * 是否全是英文
     *
     * @param str
     * @return
     */
    public boolean isAllEn(String str) {
        if (!isStringAvailable(str)) {
            return false;
        }
        return str.matches("[a-zA-Z]+");
    }

    /**
     * 是否全是中文
     *
     * @param str
     * @return
     */
    public boolean isAllChinese(String str) {
        if (!isStringAvailable(str)) {
            return false;
        }
        char[] chs = str.toCharArray();
        for (char ch : chs) {
            if (!isChinese(ch)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否是中文字符
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }
}
