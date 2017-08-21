package com.brioal.baselib.utils;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioal on 17-8-20.
 */

public class StringUtil {
    /**
     * 判断字符串是否可用（非Null 分 Empty）
     *
     * @param str
     * @return
     */
    public static boolean isAvailable(String str) {
        if (str == null) {
            return false;
        }
        if (str.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 拼接字符串数组
     *
     * @param strs
     * @return
     */
    public static String toString(String[] strs) {
        if (strs == null) {
            return "";
        }
        if (strs.length == 0) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i] == null ? "" : strs[i];
            if (i != str.length() - 1) {
                buffer.append(str + ",");
            } else {
                buffer.append(str);
            }
        }
        return buffer.toString();
    }

}
