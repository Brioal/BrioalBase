package com.brioal.brioalbase.list;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brioal.baselib.base.BrioalBaseViewHolder;
import com.brioal.brioalbase.R;


/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioal on 17-8-20.
 */

public class TestViewHolder extends BrioalBaseViewHolder<String> {
    TextView mTvContent;

    public TestViewHolder(Context context, int resID, ViewGroup parent) {
        super(context, resID, parent);
    }

    @Override
    protected void bindView(String result, int position) {
        try {
            mTvContent.setText(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void initIDs() {
        mTvContent = mItemView.findViewById(R.id.item_text_tv_content);
    }
}
