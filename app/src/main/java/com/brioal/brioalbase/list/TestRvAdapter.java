package com.brioal.brioalbase.list;

import android.content.Context;
import android.view.ViewGroup;

import com.brioal.baselib.base.BrioalBaseRecAdapter;
import com.brioal.brioalbase.R;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioal on 17-8-20.
 */

public class TestRvAdapter extends BrioalBaseRecAdapter<TestViewHolder, String> {


    public TestRvAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutID() {
        return R.layout.item_text;
    }

    @Override
    protected TestViewHolder getViewHolder(Context context, int resID, ViewGroup parent, int viewType) {
        return new TestViewHolder(context, resID, parent);
    }

}
