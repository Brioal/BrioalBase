package com.brioal.baselib.interfaces;

import java.util.List;

/**
 * JSON转换工具类
 * 只提供要实现的方法,具体的实现依据项目确定
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by Brioal on 2018/3/26.
 */

public interface JsonConvertUtil<T> {
    /**
     * 获取单个对象
     * 但不能用于获取一些基本类型,如String , Integer等
     *
     * @param content
     * @param c
     * @return
     */
    public T getSingleData(String content, Class<T> c);

    /**
     * 获取返回的基本类型,例如Integer,String等
     *
     * @param content
     * @return
     */
    public String getDataValue(String content);


    /**
     * 获取数据列表
     *
     * @param content
     * @param c
     * @return
     */
    public List<T> getListData(String content, Class<T> c);

    /**
     * 获取是否成功
     *
     * @param content
     * @return
     */
    public boolean isSuccessed(String content);

    /**
     * 返回错误信息
     * @param content
     * @return
     */
    public String getErrorMsg(String content);
}
