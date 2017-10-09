package com.brioal.baselib.base;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.brioal.baselib.utils.SerializeUtil;
import com.brioal.baselib.utils.log.BLog;
import com.brioal.baselib.views.FlexLoadingDialog;


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
    private BrioalBaseDialog mLoadingDialog = null;

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
        initIDs();
        initPresenter();
        initView();
        initReceiver();
    }

    /**
     * 注册Receiver
     */
    private void initReceiver() {
        BrioalBaseBroadCastReceiver receiver = getReceiver();
        if (receiver == null) {
            return;
        }
        //取消注册
        try {
            getContext().unregisterReceiver(receiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //注册
        try {
            IntentFilter filter = new IntentFilter();
            String action = receiver.getAction();
            BLog.title(action);
            filter.addAction(action);
            getContext().registerReceiver(receiver, filter);
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
        try {
            if (mToast == null) {
                mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
            }
            mToast.setText(msg);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mToast.show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回当前的布局资源ID
     *
     * @return
     */
    protected abstract int getLayoutID();

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
     * 实例化组件
     */
    protected abstract void initIDs();


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
            getContext().sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示基本加载进度条
     *
     * @param msg
     */
    protected void showBaseProgressDialog(String msg) {
        try {
            if (msg == null) {
                return;
            }
            if (mLoadingDialog == null) {
                mLoadingDialog = getLoadingDialog();
                if (mLoadingDialog == null) {
                    mLoadingDialog = new FlexLoadingDialog(mContext);
                }
            }
            mLoadingDialog.setMessage(msg).setCanCancle(isDialogCanCancel()).showDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏进度条
     */
    protected void hideBaseProgreddDialog() {
        try {
            if (mLoadingDialog == null) {
                return;
            }
            mLoadingDialog.hideDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected abstract BrioalBaseBroadCastReceiver getReceiver();

    /**
     * 返回要显示的LoadDialog
     *
     * @return
     */
    protected abstract BrioalBaseDialog getLoadingDialog();

    /**
     * 是否能被取消
     *
     * @return
     */
    protected abstract boolean isDialogCanCancel();

}
