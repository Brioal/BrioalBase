package com.brioal.baselib.utils;

import java.io.Serializable;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioal on 17-8-28.
 */

public class CacheBean<T> implements Serializable {
    T mResult;
    long mTime;

    public T getResult() {
        return mResult;
    }

    public void setResult(T result) {
        mResult = result;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }
}
