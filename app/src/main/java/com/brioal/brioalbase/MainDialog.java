package com.brioal.brioalbase;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;

import com.brioal.baselib.base.BrioalBaseDialog;

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
    protected boolean disTransparent() {
        return false;
    }

    @Override
    protected Drawable getDialogBackGround() {
        return null;
    }

    @Override
    protected int getDialogHeight() {
        return 0;
    }

    @Override
    protected int getDialogWidth() {
        return 0;
    }

    @Override
    protected int getWindowAnimations() {
        return 0;
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
