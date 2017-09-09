package com.brioal.baselib.utils;

import com.brioal.baselib.utils.log.BLog;

import java.util.List;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by Brioal on 2017/8/4.
 */

public class ListUtil {
    /**
     * 判断list是否可用 对象 + 数量
     *
     * @param list
     * @return
     */
    public static boolean isAvaliable(List list) {
        if (list == null) {
            return false;
        }
        if (list.size() == 0) {
            return false;
        }
        return true;
    }

    /**
     * 输出List内容
     *
     * @param list
     */
    public static void log(List list) {
        for (int i = 0; i < list.size(); i++) {
            BLog.content("List内容:" + (i + 1) + ".", list.get(i));
        }
    }
}
