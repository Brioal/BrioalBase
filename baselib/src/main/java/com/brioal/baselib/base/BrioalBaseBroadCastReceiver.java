package com.brioal.baselib.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.brioal.baselib.interfaces.BrioalOnReceivedListener;
import com.brioal.baselib.utils.SerializeUtil;
import com.brioal.baselib.utils.StringUtil;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by Brioal on 2017/9/13.
 */

public abstract class BrioalBaseBroadCastReceiver<T> extends BroadcastReceiver {
    private BrioalOnReceivedListener<T> mOnDataLoadListener;

    public BrioalBaseBroadCastReceiver(BrioalOnReceivedListener<T> loadListener) {
        super();
        mOnDataLoadListener = loadListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            String msg = intent.getStringExtra("Msg");
            T result = (T) SerializeUtil.deSerialize(msg);
            if (mOnDataLoadListener != null) {
                mOnDataLoadListener.OnReceived(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract String getClassName();

    /**
     * 返回Action名称
     *
     * @return
     * @throws Exception
     */
    public String getAction() throws Exception {
        if (!StringUtil.isAvailable(getClassName())) {
            throw new Exception("BrioalBaseBroadCastReceiver未实现getClassName方法");
        }
        return "android.intent.action." + getClassName();
    }
}
