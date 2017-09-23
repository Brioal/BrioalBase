package com.brioal.brioalbase;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;

import com.brioal.baselib.base.BrioalBaseDialog;
import com.brioal.baselib.utils.ScreenUtil;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by Brioal on 2017/9/24.
 */

public class MainDialog extends BrioalBaseDialog {
    public MainDialog(Context context) {
        super(context);
    }

    @Override
    protected Drawable getDialogBackGround() {
        return null;
    }

    @Override
    protected int getDialogHeight() {
        return ScreenUtil.getScreenHeight(mContext) / 2;
    }

    @Override
    protected int getDialogWidth() {
        return ScreenUtil.getScreenWidth(mContext);
    }

    @Override
    protected int getWindowAnimations() {
        return 0;
    }

    @Override
    protected void onDialogDismissListener(DialogInterface dialogInterface) {

    }

    @Override
    protected void onDialogCancelListener(DialogInterface dialogInterface) {

    }

    @Override
    protected void onDialogShowListener(DialogInterface dialogInterface) {

    }

    @Override
    protected void bindView(View contentView) {

    }

    @Override
    protected int getGravity() {
        return Gravity.BOTTOM;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.layout_input_test;
    }
}