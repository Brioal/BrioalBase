package com.brioal.baselib.interfaces;

/**
 * 数据加载监听器
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioal on 17-8-20.
 */

public interface BrioalDataLoadListener<T> {

    void success(T result);

    void failed(String errorMsg);
}
