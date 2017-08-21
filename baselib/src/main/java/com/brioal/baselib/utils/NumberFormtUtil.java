package com.brioal.baselib.utils;

import android.widget.EditText;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Github:https://github.com/Brioal
 * Emalil : brioal@foxmail.com
 * Created by brioal on 17-3-14.
 */

public class NumberFormtUtil {

    private static final DecimalFormat amountFormat = new DecimalFormat("###,###,###,##0.00");

    /**
     * 将float格式的价格转化为两位小数的字符串
     * 3.455555555555 - 3.46
     *
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
     *
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

    /**
     * 四舍五入
     *
     * @param value 数值
     * @param digit 保留小数位
     * @return
     */
    public static String getRoundUp(BigDecimal value, int digit) {
        return value.setScale(digit, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 四舍五入
     *
     * @param value 数值
     * @param digit 保留小数位
     * @return
     */
    public static String getRoundUp(double value, int digit) {
        BigDecimal result = new BigDecimal(value);
        return result.setScale(digit, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 四舍五入
     *
     * @param value 数值
     * @param digit 保留小数位
     * @return
     */
    public static String getRoundUp(String value, int digit) {
        BigDecimal result = new BigDecimal(Double.parseDouble(value));
        return result.setScale(digit, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 获取百分比（乘100）
     *
     * @param value 数值
     * @param digit 保留小数位
     * @return
     */
    public static String getPercentValue(BigDecimal value, int digit) {
        BigDecimal result = value.multiply(new BigDecimal(100));
        return getRoundUp(result, digit);
    }

    /**
     * 获取百分比（乘100）
     *
     * @param value 数值
     * @param digit 保留小数位
     * @return
     */
    public static String getPercentValue(double value, int digit) {
        BigDecimal result = new BigDecimal(value);
        return getPercentValue(result, digit);
    }

    /**
     * 获取百分比（乘100,保留两位小数）
     *
     * @param value 数值
     * @return
     */
    public static String getPercentValue(double value) {
        BigDecimal result = new BigDecimal(value);
        return getPercentValue(result, 2);
    }

    /**
     * 金额格式化
     *
     * @param value 数值
     * @return
     */
    public static String getAmountValue(double value) {
        return amountFormat.format(value);
    }

    /**
     * 金额格式化
     *
     * @param value 数值
     * @return
     */
    public static String getAmountValue(String value) {
        return amountFormat.format(Double.parseDouble(value));
    }

    /**
     * int -tostring
     *
     * @param value 数值
     * @return
     */
    public static String getIntegerValue(int value) {
        return Integer.valueOf(value).toString();
    }

    /**
     * 动态格式化输入
     * onTextChanged
     *
     * @param sequence (CharSequenc s
     * @param editText
     */
    public static void formatDot(CharSequence sequence, EditText editText) {
        String s = sequence.toString();
        if (s.contains(".")) {
            /**
             * 如果小数点位数大于两位 截取后两位
             */
            if (s.length() - 1 - s.indexOf(".") > 2) {
                s = s.substring(0, (s.indexOf(".") + 3));
                editText.setText(s);
                editText.setSelection(s.length());
            }
        }
        /**
         * 如果第一个输入为小数点 ，自动补零
         */
        if (s.trim().substring(0).equals(".")) {
            s = "0" + s;
            editText.setText(s);
            editText.setSelection(s.length());
        }
        /**
         * 如果第一个第二个均为0
         */
        if (s.startsWith("0") && s.trim().length() > 1) {
            if (!s.substring(1, 2).equals(".")) {
                editText.setText(s.substring(0, 1));
                editText.setSelection(1);
                return;
            }
        }
    }


}
