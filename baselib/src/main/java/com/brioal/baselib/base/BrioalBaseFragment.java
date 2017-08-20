package com.brioal.baselib.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.brioal.baselib.utils.StringUtil;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioal on 17-8-20.
 */

public abstract class BrioalBaseFragment extends Fragment {
    protected Context mContext;
    private Toast mToast;
    protected View mRootView;//根View
    protected Handler mHandler = new Handler();
    private ProgressDialog mInterProgressDialog = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutID() == -1) {
            try {
                throw new Exception("布局文件为空");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mRootView = inflater.inflate(getLayoutID(), container, false);
        return mRootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
        initPresenter();
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
     * 返回当前的布局资源ID
     *
     * @return
     */
    abstract int getLayoutID();

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
