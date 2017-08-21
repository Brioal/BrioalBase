package com.brioal.baselib.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioal on 17-8-20.
 */

public abstract class BrioalBaseRecAdapter<T extends BrioalBaseViewHolder<K>, K> extends RecyclerView.Adapter<T> {

    protected Context mContext;
    protected List<K> mList = new ArrayList<>();

    /**
     * 显示数据
     *
     * @param list
     */
    public void showData(List<K> list) {
        mList.clear();
        mList.addAll(list);
    }

    /**
     * 添加数据
     *
     * @param list
     */
    protected void addData(List<K> list) {
        mList.addAll(list);
        for (int i = 0; i < list.size(); i++) {
            notifyItemInserted(mList.size() + i);
        }
    }

    /**
     * 删除数据
     *
     * @param k
     */
    protected void deleteData(K k) {
        try {
            notifyItemRemoved(mList.indexOf(k));
            mList.remove(k);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public BrioalBaseRecAdapter(Context context) {
        mContext = context;
    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        return getViewHolder(mContext, getItemLayoutID(), parent, viewType);
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        holder.bindView(mList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 返回Item的布局文件ID
     *
     * @return
     */
    protected abstract int getItemLayoutID();

    /**
     * 返回ViewHolder
     *
     * @param context
     * @param resID
     * @param parent
     * @param viewType
     * @return
     */
    protected abstract T getViewHolder(Context context, int resID, ViewGroup parent, int viewType);

}
