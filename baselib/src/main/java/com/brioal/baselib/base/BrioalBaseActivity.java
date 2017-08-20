package com.brioal.baselib.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.brioal.baselib.utils.log.StringUtil;

/**
 * 封装基类Activity
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioal on 17-8-18.
 */

public abstract class BrioalBaseActivity extends AppCompatActivity {
    protected Context mContext;
    private Toast mToast;
    protected Handler mHandler = new Handler();
    private ProgressDialog mInterProgressDialog = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        doInOnCreateBeforeSuper();
        super.onCreate(savedInstanceState);
        mContext = this;
        int id = getLayoutID();
        if (id == -1) {
            try {
                throw new Exception("布局资源为空");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        setContentView(getLayoutID());
        initData();
        initView();
        initPresenter();
        doInOnCreateEnd();
    }

    /**
     * 显示提示信息
     *
     * @param msg
     */
    protected void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }

    /**
     * 初始化基本数据
     */
    abstract void initData();

    /**
     * 初始化视图
     */
    abstract void initView();

    /**
     * 初始化Presenter
     */
    abstract void initPresenter();

    /**
     * 在Oncreate内的末尾进行的操作
     */
    abstract void doInOnCreateEnd();

    /**
     * 在Oncreate内的super之前进行的操作
     */
    abstract void doInOnCreateBeforeSuper();


    /**
     * 返回布局资源
     *
     * @return
     */
    abstract int getLayoutID();

    /**
     * 显示基本加载进度条
     *
     * @param title
     * @param msg
     */
    protected void showProgressDialog(String title, String msg) {
        if (mInterProgressDialog != null && mInterProgressDialog.isShowing()) {
            mInterProgressDialog.dismiss();
        }
        mInterProgressDialog = new ProgressDialog(mContext);
        if (StringUtil.isAvailable(title)) {
            mInterProgressDialog.setTitle(title);
        }
        if (StringUtil.isAvailable(msg)) {
            mInterProgressDialog.setMessage(msg);
        }
        mInterProgressDialog.show();
    }

    /**
     * 隐藏进度条
     */
    protected void hideProgreddDialog() {
        if (mInterProgressDialog != null && mInterProgressDialog.isShowing()) {
            mInterProgressDialog.dismiss();
        }
    }

}
