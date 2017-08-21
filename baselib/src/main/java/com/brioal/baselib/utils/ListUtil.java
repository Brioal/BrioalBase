package com.brioal.baselib.utils;

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
}
