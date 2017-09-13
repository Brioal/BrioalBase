package com.brioal.baselib.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.brioal.baselib.utils.SerializeUtil;
import com.brioal.baselib.utils.StringUtil;
import com.brioal.baselib.utils.log.BLog;


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
        setContentView(id);
        initData();
        initIDs();
        initView();
        initPresenter();
        doInOnCreateEnd();
        initReceiver();
    }

    /**
     * 注册广播
     */
    private void initReceiver() {
        BrioalBaseBroadCastReceiver receiver = getReceiver();
        if (receiver == null) {
            return;
        }
        //取消注册
        try {
            unregisterReceiver(receiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //注册
        try {
            IntentFilter filter = new IntentFilter();
            String action = receiver.getAction();
            BLog.title(action);
            filter.addAction(action);
            registerReceiver(receiver, filter);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    protected abstract void initData();

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 初始化Presenter
     */
    protected abstract void initPresenter();

    /**
     * 在Oncreate内的末尾进行的操作
     */
    protected abstract void doInOnCreateEnd();

    /**
     * 在Oncreate内的super之前进行的操作
     */
    protected abstract void doInOnCreateBeforeSuper();


    /**
     * 返回布局资源
     *
     * @return
     */
    protected abstract int getLayoutID();

    /**
     * 实例化组件
     */
    protected abstract void initIDs();

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

    /**
     * 返回要注册的广播
     *
     * @return
     */
    protected abstract BrioalBaseBroadCastReceiver getReceiver();

    /**
     * 发送信息给指定的Receiver
     *
     * @param receiver
     * @param object
     */
    protected void sendBroadCastMsg(BrioalBaseBroadCastReceiver receiver, Object object) {
        try {
            String action = receiver.getAction();
            Intent intent = new Intent(action);
            String msg = SerializeUtil.serialize(object);
            intent.putExtra("Msg", msg);
            sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
