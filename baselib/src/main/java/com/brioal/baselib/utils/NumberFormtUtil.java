package com.brioal.baselib.utils;

import java.text.DecimalFormat;

/**
 * Github:https://github.com/Brioal
 * Emalil : brioal@foxmail.com
 * Created by brioal on 17-3-14.
 */

public class NumberFormtUtil {
    /**
     * 将float格式的价格转化为两位小数的字符串
     * 3.455555555555 - 3.46
     * @param price
     * @return
     */
    public static String addTwoZero(float price) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        return decimalFormat.format(price);//format 返回的是字符串
    }

    /**
     * 将数字格式化为两位的字符串，位数不足前面补0
     * 2 -> 02
     * @param time
     * @return
     */
    public static String formatTime(int time) {
        if (time < 0) {
            return "";
        }
        if (time > 99) {
            return (time + "").charAt(0) + (time + "").charAt(1) + "";
        }
        if (time < 10) {
            return "0" + time;
        }
        return time + "";
    }


}
