package com.brioal.baselib.interfaces;

import android.content.DialogInterface;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by Brioal on 11/19/2017.
 */

public interface OnDialogActionListener {
    void onDialogShow(DialogInterface dialogInterface);//显示出来的时候调用

    void onDialogDismiss(DialogInterface dialogInterface);//关闭时候调用

    void onDialogCancel(DialogInterface dialogInterface);//取消时候调用

    void onAction1(Object result1);//用户定义动作1

    void onAction2(Object result2);//用户定义动作2

    void onAction3(Object result3);//用户定义动作3
}
