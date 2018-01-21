package com.brioal.baselib.utils;

import android.content.Context;

import com.brioal.baselib.utils.log.BLog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioal on 17-8-22.
 */

public class CacheUtil<T> {

    /**
     * @param context
     * @param name
     * @param durationTime 间隔时间
     * @return
     */
    public T getCache(Context context, String name, long durationTime) {
        T result = null;
        try {
            File targetFile = new File(context.getFilesDir().getAbsolutePath() + "/" + name);
            if (!targetFile.exists()) {
                return null;
            }
            String cacheStr = IOUtil.readStr(new FileInputStream(targetFile));
            CacheBean<T> bean = (CacheBean) SerializeUtil.deSerialize(cacheStr);
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
            BLog.content("缓存文件路径", targetFile.getAbsolutePath());
            if (!targetFile.exists()) {
                BLog.title("缓存文件不存在,返回null");
                return null;
            }
            String cacheStr = IOUtil.readStr(new FileInputStream(targetFile));
            BLog.content("序列化的文件内容", cacheStr);
            CacheBean<T> bean = (CacheBean) SerializeUtil.deSerialize(cacheStr);
            if (bean == null) {
                BLog.title("序列化之后的内容为空");
                result = null;
            } else {
                result = bean.getResult();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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
            BLog.content("要删除的缓存路径", targetFile);
            if (targetFile.exists()) {
                BLog.title("缓存文件已存在,删除");
                targetFile.delete();
                return true;
            }
            BLog.title("缓存文件不存在");
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
            BLog.content("缓存文件路径", targetFile.getAbsolutePath());
            if (targetFile.exists()) {
                BLog.title("缓存文件已存在,删除");
                targetFile.delete();
            }
            CacheBean bean = new CacheBean();
            bean.setResult(result);
            bean.setTime(System.currentTimeMillis());
            String cacheStr = SerializeUtil.serialize(bean);
            BLog.content("序列化之后内容", cacheStr);
            BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile));
            bw.write(cacheStr);
            bw.flush();
            bw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
