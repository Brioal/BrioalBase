package com.brioal.baselib.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * BaseViewHolder
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioal on 17-8-20.
 */

public abstract class BrioalBaseViewHolder<T> extends RecyclerView.ViewHolder {
    protected View mItemView;

    public BrioalBaseViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * 绑定视图
     *
     * @param result
     * @param position
     */
    protected abstract void bindView(T result, int position);
}
