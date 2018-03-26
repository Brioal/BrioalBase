package com.brioal.baselib.interfaces;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * 配合RequestUtil的请求
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by Brioal on 3/18/2018.
 */

public interface OnRequestListener<T> {
    // 获取Json转换工具类
    JsonConvertUtil<T> getJsonUtil();

    // 返回请求的Client
    OkHttpClient getClient();

    // 返回post用请求体
    RequestBody getRequestBody();

    // 返回请求链接
    String getUrl();

    // 返回数据实体
    Class<T> getEntity();

    // 是否只判断是否成功
    boolean isOnlyCheckSuccess();

    // 是否是多数据
    boolean isMultData();

    // 是否拦截返回数据
    boolean interceptContent(String content);

    // 是否拦截返回数据
    boolean interceptResponse(Response response);

    // 加载成功
    void success(T bean);

    // 加载成功
    void success(List<T> list);

    // 加载失败
    void failed(String errorMsg);

    // 返回本地的缓存数据
    T getLocalSingleData();

    // 返回本地的列表数据
    List<T> getLocalListData();

    // 在数据获取之后和返回之前
    void dealAfterDone(T bean);

    // 在数据获取之后和返回之前
    void dealAfterDone(List<T> list);


}
