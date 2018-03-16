package com.brioal.baselib.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.brioal.baselib.R;
import com.brioal.baselib.base.BrioalBaseDialog;
import com.brioal.baselib.utils.ScreenUtil;
import com.brioal.baselib.utils.TextUtil;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by Brioal on 2017/9/15.
 */
public class FlexLoadingDialog extends BrioalBaseDialog {
    protected FlexLoadingView mFlexLoadingView;
    protected TextView mTvMsg;

    public FlexLoadingDialog(Context context) {
        super(context);
    }

    @Override
    protected boolean disTransparent() {
        return false;
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
    protected void bindView(View contentView) {
        mFlexLoadingView = contentView.findViewById(R.id.dialog_flex_loading_view);
        mTvMsg = contentView.findViewById(R.id.dialog_flex_loading_text);
        mFlexLoadingView.setBackSrc(mContext.getResources().getDrawable(R.drawable.bg_gra_three)).setLinesCount(5);
        if (TextUtil.isStringAvailable(mMessage)) {
            mTvMsg.setText(mMessage + "");
        } else {
            mTvMsg.setText("正在加载,请稍等");
        }
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
