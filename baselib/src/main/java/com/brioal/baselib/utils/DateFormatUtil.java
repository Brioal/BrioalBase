package com.brioal.baselib.utils;

import java.text.SimpleDateFormat;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioa on 2017/5/18.
 */

public class DateFormatUtil {
    /**
     * 格式化成标准日期
     *
     * @param time
     * @return
     */
    public static String formartLongTime(long time) {
        return formartLongTime(time, "yyyy/mm/DD HH:MM:SS");
    }

    /**
     * 按照格式格式化日期
     *
     * @param time
     * @param format
     * @return
     */
    public static String formartLongTime(long time, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.format(time);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
