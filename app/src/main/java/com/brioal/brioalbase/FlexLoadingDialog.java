package com.brioal.brioalbase;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;

import com.brioal.baselib.base.BrioalBaseDialog;
import com.brioal.baselib.utils.ScreenUtil;
import com.brioal.baselib.views.FlexLoadingView;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by Brioal on 2017/9/15.
 */
public class FlexLoadingDialog extends BrioalBaseDialog {
    private FlexLoadingView mFlexLoadingView;

    public FlexLoadingDialog(Context context) {
        super(context);
    }


    @Override
    protected Drawable getDialogBackGround() {
        return mContext.getResources().getDrawable(R.drawable.bg_sowhite_coten);
    }

    @Override
    protected int getDialogHeight() {
        return getDialogWidth();
    }

    @Override
    protected int getDialogWidth() {
        return ScreenUtil.getScreenWidth(mContext) / 3;
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
        mFlexLoadingView = contentView.findViewById(R.id.dialog_flex_loading_view);
//        mFlexLoadingView.setBackSrc(mContext.getResources().getDrawable(R.drawable.bg_gra_three)).setLinesCount(5);
        mFlexLoadingView.setBackSrc(new ColorDrawable(Color.BLUE)).setLinesCount(5);
    }

    @Override
    protected int getGravity() {
        return Gravity.CENTER;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.layout_dialog_flex_loading;
    }
}
