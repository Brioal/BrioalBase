package com.brioal.baselib.utils;

import com.brioal.baselib.interfaces.JsonConvertUtil;
import com.brioal.baselib.interfaces.OnRequestListener;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by Brioal on 2018/3/11.
 */

public class RequestUtil<T> {


    private OnRequestListener<T> mRequestListener;

    public RequestUtil init(OnRequestListener<T> requestListener) {
        mRequestListener = requestListener;
        return this;
    }


    public void build() {
        if (mRequestListener == null) {
            return;
        }
        if (mRequestListener.isMultData()) {
            // 列表加载
            loadListData();
        } else {
            // 单个结果加载
            loadSingleData();
        }

    }


    /**
     * 获取单个数据
     */
    public void loadSingleData() {
        Observer observer = new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(T bean) {
                mRequestListener.success(bean);
            }


            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mRequestListener.failed(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(final ObservableEmitter<T> emitter) throws Exception {
                T result = mRequestListener.getLocalSingleData();
                if (result != null) {
                    emitter.onNext(result);
                    return;
                }
                OkHttpClient client = mRequestListener.getClient();
                Request.Builder builder = new Request.Builder();
                builder.url(mRequestListener.getUrl());
                RequestBody body = mRequestListener.getRequestBody();
                if (body != null) {
                    builder.post(body);
                }
                final Request request = builder.build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        emitter.onError(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (mRequestListener.interceptResponse(response)) {
                            return;
                        }
                        String content = response.body().string();
                        if (mRequestListener.interceptContent(content)) {
                            response.close();
                            return;
                        }
                        if (mRequestListener.isOnlyCheckSuccess()) {
                            boolean result = mRequestListener.getJsonUtil().isSuccessed(content);
                            if (result) {
                                response.close();
                                mRequestListener.success((T) null);
                                return;
                            } else {
                                response.close();
                                emitter.onError(new Exception(mRequestListener.getJsonUtil().getErrorMsg(content)));
                            }
                        }
                        T result = mRequestListener.getJsonUtil().getSingleData(content, mRequestListener.getEntity());
                        if (result != null) {
                            mRequestListener.dealAfterDone(result);
                            response.close();
                            emitter.onNext(result);
                        } else {
                            response.close();
                            emitter.onError(new Exception(mRequestListener.getJsonUtil().getErrorMsg(content)));
                        }
                    }
                });
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 获取多个数据
     */
    public void loadListData() {
        Observer observer = new Observer<List<T>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<T> bean) {
                mRequestListener.success(bean);
            }


            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mRequestListener.failed(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        Observable.create(new ObservableOnSubscribe<List<T>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<T>> emitter) throws Exception {
                List<T> result = mRequestListener.getLocalListData();
                if (ListUtil.isAvaliable(result)) {
                    emitter.onNext(result);
                    return;
                }
                OkHttpClient client =mRequestListener.getClient();
                Request.Builder builder = new Request.Builder();
                builder.url(mRequestListener.getUrl());
                RequestBody body = mRequestListener.getRequestBody();
                if (body != null) {
                    builder.post(body);
                }
                final Request request = builder.build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        emitter.onError(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (mRequestListener.interceptResponse(response)) {
                            return;
                        }
                        String content = response.body().string();
                        if (mRequestListener.interceptContent(content)) {
                            response.close();
                            return;
                        }
                        List<T> result =mRequestListener.getJsonUtil().getListData(content, mRequestListener.getEntity());
                        if (ListUtil.isAvaliable(result)) {
                            mRequestListener.dealAfterDone(result);
                            response.close();
                            emitter.onNext(result);
                        } else {
                            response.close();
                            emitter.onError(new Exception(mRequestListener.getJsonUtil().getErrorMsg(content)));
                        }
                    }
                });
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
