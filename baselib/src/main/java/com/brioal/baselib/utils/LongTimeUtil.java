package com.brioal.baselib.utils;

import java.util.Calendar;

/**
 * Long 类型的时间
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by Brioal on 11/20/2017.
 */

public class LongTimeUtil {
    /**
     * 判断是否是同一天
     *
     * @param current
     * @param last
     * @return
     */
    public static boolean isSameDay(long current, long last) {
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTimeInMillis(current);
        Calendar lastCalendar = Calendar.getInstance();
        lastCalendar.setTimeInMillis(last);
        if (currentCalendar.get(Calendar.YEAR) != lastCalendar.get(Calendar.YEAR)) {
            return false;
        }
        if (currentCalendar.get(Calendar.MONTH) != lastCalendar.get(Calendar.MONTH)) {
            return false;
        }
        if (currentCalendar.get(Calendar.DAY_OF_MONTH) != lastCalendar.get(Calendar.DAY_OF_MONTH)) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否是同一天
     * @param time
     * @return
     */
    public static boolean isToday(long time) {
        return isSameDay(System.currentTimeMillis(), time);
    }
}
