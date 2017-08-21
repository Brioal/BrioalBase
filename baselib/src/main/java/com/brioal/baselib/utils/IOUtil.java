package com.brioal.baselib.utils;

import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioal on 17-8-21.
 */

public class IOUtil {

    /**
     * 删除文件或文件夹，返回是否删除成功
     *
     * @param path
     * @return
     */
    public static boolean deleteFileOrDir(File path) {
        if (path == null || !path.exists()) {
            return true;
        }
        if (path.isFile()) {
            return path.delete();
        }
        File[] files = path.listFiles();
        if (files != null) {
            for (File file : files) {
                deleteFileOrDir(file);
            }
        }
        return path.delete();
    }

    /**
     * 将字符串写到输出流
     *
     * @param out
     * @param str
     * @param charset
     * @throws IOException
     */
    public static void writeStr(OutputStream out, String str, String charset) throws IOException {
        if (TextUtils.isEmpty(charset)) charset = "UTF-8";
        Writer writer = new OutputStreamWriter(out, charset);
        writer.write(str);
        writer.flush();
    }

    /**
     * 将字符串写到输出流
     *
     * @param out
     * @param str
     * @throws IOException
     */
    public static void writeStr(OutputStream out, String str) throws IOException {
        writeStr(out, str, "UTF-8");
    }

    /**
     * 从输入流获取字符串
     *
     * @param in
     * @param charset
     * @return
     * @throws IOException
     */
    public static String readStr(InputStream in, String charset) throws IOException {
        if (TextUtils.isEmpty(charset)) charset = "UTF-8";

        if (!(in instanceof BufferedInputStream)) {
            in = new BufferedInputStream(in);
        }
        Reader reader = new InputStreamReader(in, charset);
        StringBuilder sb = new StringBuilder();
        char[] buf = new char[1024];
        int len;
        while ((len = reader.read(buf)) >= 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    /**
     * 从输入流获取字符串
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static String readStr(InputStream in) throws IOException {
        return readStr(in, "UTF-8");
    }

}
