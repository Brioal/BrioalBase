package com.brioal.baselib.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * BaseViewHolder
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioal on 17-8-20.
 */

public abstract class BrioalBaseViewHolder<T> extends RecyclerView.ViewHolder {
    protected View mItemView;

    public BrioalBaseViewHolder(Context context, int resID, ViewGroup parent) {
        super(LayoutInflater.from(context).inflate(resID, parent, false));
        mItemView = itemView;
        initIDs();
    }

    /**
     * 绑定视图
     *
     * @param result
     * @param position
     */
    protected abstract void bindView(T result, int position);

    /**
     * 实例化组件
     */
    protected abstract void initIDs();
}
