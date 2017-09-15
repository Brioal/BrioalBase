package com.brioal.baselib.base;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioal on 17-8-21.
 */

public abstract class BrioalBaseDialog {
    private AlertDialog mAlertDialog;
    protected View mRootView;
    protected Context mContext;
    public BrioalBaseDialog(Context context) {
        mContext = context;
        init();
    }

    private void init() {
        //实例化
        mAlertDialog = new AlertDialog.Builder(mContext).create();
        //设置显示监听器
        mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                onDialogShowListener(dialogInterface);
            }
        });
        //隐藏监听器
        mAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                onDialogDismissListener(dialogInterface);
            }
        });
        //返回监听器
        mAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                onDialogCancelListener(dialogInterface);
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
        }
        if (getDialogWidth() != -1 && getDialogHeight() != -1) {
            window.setLayout(getDialogWidth(), getDialogHeight());
        }
        if (getWindowAnimations() != -1) {
            window.setWindowAnimations(getWindowAnimations());
        }
    }


    /**
     * 显示Dialog
     */
    public void showDialog() {
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
     * 隐藏时候的监听器
     *
     * @param dialogInterface
     */
    protected abstract void onDialogDismissListener(DialogInterface dialogInterface);

    /**
     * 当返回的时候的监听器
     *
     * @param dialogInterface
     */
    protected abstract void onDialogCancelListener(DialogInterface dialogInterface);

    /**
     * dialog显示的时候的监听器
     *
     * @param dialogInterface
     */
    protected abstract void onDialogShowListener(DialogInterface dialogInterface);

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
