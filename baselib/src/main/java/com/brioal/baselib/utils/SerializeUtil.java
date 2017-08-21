package com.brioal.baselib.utils;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 在字符串和对象之间转换
 */
public class SerializeUtil {
    //将对象序列化成字符串
    public static String serialize(Object o) {
        String str = "";
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    byteArrayOutputStream);
            objectOutputStream.writeObject(o);
            str = byteArrayOutputStream.toString("ISO-8859-1");
            str = java.net.URLEncoder.encode(str, "UTF-8");
            objectOutputStream.close();
            byteArrayOutputStream.close();
            Log.d("serial", "serialize str =" + str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return str;
    }

    //反序列化
    public static Object deSerialize(String str) {
        Object o = null;
        try {
            String redStr = java.net.URLDecoder.decode(str, "UTF-8");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                    redStr.getBytes("ISO-8859-1"));
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    byteArrayInputStream);
            o = objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
            return o;
        } catch (Exception e) {
            e.printStackTrace();
            return o;
        }
    }
}
