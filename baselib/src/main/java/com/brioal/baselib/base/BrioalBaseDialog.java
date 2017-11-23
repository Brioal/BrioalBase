package com.brioal.baselib.base;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.brioal.baselib.R;
import com.brioal.baselib.interfaces.OnDialogActionListener;
import com.brioal.baselib.utils.StringUtil;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioal on 17-8-21.
 */

public abstract class BrioalBaseDialog {
    private AlertDialog mAlertDialog;
    protected View mRootView;
    protected Context mContext;
    protected String mMessage;
    private boolean canCancle = true;
    private Toast mToast;

    protected OnDialogActionListener mDialogActionListener;

    public BrioalBaseDialog(Context context) {
        mContext = context;
    }

    /**
     * 设置事件监听器
     * @param dialogActionListener
     * @return
     */
    public BrioalBaseDialog setDialogActionListener(OnDialogActionListener dialogActionListener) {
        mDialogActionListener = dialogActionListener;
        return this;
    }

    /**
     * 显示提示信息
     *
     * @param errorMsg
     */
    protected void showToast(String errorMsg) {
        if (!StringUtil.isAvailable(errorMsg)) {
            return;
        }
        try {
            if (mToast == null) {
                mToast = Toast.makeText(mContext, errorMsg, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(errorMsg);
            }
            mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置提示信息
     *
     * @param message
     * @return
     */
    public BrioalBaseDialog setMessage(String message) {
        mMessage = message;
        return this;
    }


    /**
     * 设置能否返回
     *
     * @param canCancle
     * @return
     */
    public BrioalBaseDialog setCanCancle(boolean canCancle) {
        this.canCancle = canCancle;
        return this;
    }

    private void init() {
        //实例化
        mAlertDialog = new AlertDialog.Builder(mContext).create();
        //设置显示监听器
        mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                if (mDialogActionListener == null) {
                    return;
                }
                mDialogActionListener.onDialogShow(dialogInterface);
            }
        });
        //隐藏监听器
        mAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (mDialogActionListener == null) {
                    return;
                }
                mDialogActionListener.onDialogDismiss(dialogInterface);
            }
        });
        //返回监听器
        mAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                if (mDialogActionListener == null) {
                    return;
                }
                mDialogActionListener.onDialogCancel(dialogInterface);
            }
        });
        mAlertDialog.show();
        if (getLayoutID() == -1) {
            return;
        }
        mRootView = LayoutInflater.from(mContext).inflate(getLayoutID(), null, false);
        Window window = mAlertDialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        bindView(mRootView);
        window.setContentView(mRootView);
        if (getGravity() != -1) {
            window.setGravity(getGravity());
        }
        if (getDialogBackGround() != null) {
            window.setBackgroundDrawable(getDialogBackGround());
        } else {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        if (getDialogWidth() != 0 && getDialogHeight() != 0) {
            window.setLayout(getDialogWidth(), getDialogHeight());
        }
        if (getWindowAnimations() != -1) {
            window.setWindowAnimations(getWindowAnimations());
        } else {
            window.setWindowAnimations(R.style.Animation_Bottom_Dialog);
        }
        window.getDecorView().setPadding(0, 0, 0, 0);
        mAlertDialog.setCancelable(canCancle);
    }


    /**
     * 显示Dialog
     */
    public void showDialog() {
        init();
        if (mAlertDialog != null && !mAlertDialog.isShowing()) {
            mAlertDialog.show();
        }
    }

    /**
     * 隐藏Dialog
     */
    public void hideDialog() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }

    /**
     * 返回窗口的背景
     *
     * @return
     */
    protected abstract Drawable getDialogBackGround();

    /**
     * 返回窗口高度
     *
     * @return
     */
    protected abstract int getDialogHeight();

    /**
     * 返回窗口宽度
     *
     * @return
     */
    protected abstract int getDialogWidth();

    /**
     * 返回窗口动画
     *
     * @return
     */
    protected abstract int getWindowAnimations();

    /**
     * 绑定View
     *
     * @param contentView
     */
    protected abstract void bindView(View contentView);

    /**
     * 返回Gravity
     *
     * @return
     */
    protected abstract int getGravity();

    /**
     * 返回布局资源文件ID
     *
     * @return
     */
    protected abstract int getLayoutID();
}
