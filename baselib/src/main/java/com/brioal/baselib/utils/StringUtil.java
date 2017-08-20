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
}
