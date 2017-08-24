package com.brioal.baselib.utils;

import android.content.Context;

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

    public T getCache(Context context, String name) {
        T result = null;
        try {
            File targetFile = new File(context.getFilesDir().getAbsolutePath() + "/" + name);
            if (!targetFile.exists()) {
                return null;
            }
            String cacheStr = IOUtil.readStr(new FileInputStream(targetFile));
            result = (T) SerializeUtil.deSerialize(cacheStr);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean saveCache(Context context, T result, String name) {
        try {
            File targetFile = new File(context.getFilesDir().getAbsolutePath() + "/" + name);
            if (targetFile.exists()) {
                targetFile.delete();
            }
            String cacheStr = SerializeUtil.serialize(result);
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
