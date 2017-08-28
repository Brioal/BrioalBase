package com.brioal.baselib.utils;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Serializable;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioal on 17-8-22.
 */

public class CacheUtil<T> {
    class CacheBean implements Serializable {
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

    /**
     *
     * @param context
     * @param name
     * @param durationTime 间隔时间
     * @return
     */
    public T getCache(Context context, String name,long durationTime) {
        T result = null;
        try {
            File targetFile = new File(context.getFilesDir().getAbsolutePath() + "/" + name);
            if (!targetFile.exists()) {
                return null;
            }
            String cacheStr = IOUtil.readStr(new FileInputStream(targetFile));
            CacheBean bean = (CacheBean) SerializeUtil.deSerialize(cacheStr);
            if (bean == null) {
                result = null;
            } else {
                long saveTime = bean.getTime();
                if (System.currentTimeMillis() - saveTime < durationTime) {
                    result = bean.getResult();
                } else {
                    return null;
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取缓存
     *
     * @param context
     * @param name
     * @return
     */
    public T getCache(Context context, String name) {
        T result = null;
        try {
            File targetFile = new File(context.getFilesDir().getAbsolutePath() + "/" + name);
            if (!targetFile.exists()) {
                return null;
            }
            String cacheStr = IOUtil.readStr(new FileInputStream(targetFile));
            CacheBean bean = (CacheBean) SerializeUtil.deSerialize(cacheStr);
            if (bean == null) {
                result = null;
            } else {
                result = bean.getResult();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除备份
     *
     * @param context
     * @param name
     * @return
     */
    public boolean deleteCache(Context context, String name) {
        try {
            File targetFile = new File(context.getFilesDir().getAbsolutePath() + "/" + name);
            if (targetFile.exists()) {
                targetFile.delete();
                return true;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 保存缓存
     *
     * @param context
     * @param result
     * @param name
     * @return
     */
    public boolean saveCache(Context context, T result, String name) {
        try {
            File targetFile = new File(context.getFilesDir().getAbsolutePath() + "/" + name);
            if (targetFile.exists()) {
                targetFile.delete();
            }
            CacheBean bean = new CacheBean();
            bean.setResult(result);
            bean.setTime(System.currentTimeMillis());
            String cacheStr = SerializeUtil.serialize(bean);
            BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile));
            bw.write(cacheStr);
            bw.flush();
            return true;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
}
