package com.brioal.brioalbase.list;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.brioal.baselib.base.BrioalBaseActivity;
import com.brioal.baselib.base.BrioalBaseBroadCastReceiver;
import com.brioal.baselib.base.BrioalBaseDialog;
import com.brioal.brioalbase.R;

import java.util.ArrayList;
import java.util.List;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioal on 17-8-20.
 */

public class TestListActivity extends BrioalBaseActivity {


    RecyclerView mRecyclerView;

    private List<String> mDatas = null;

    @Override
    protected void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mDatas.add(i + "");
        }
    }

    @Override
    protected void initView() {
        TestRvAdapter adapter = new TestRvAdapter(mContext);
        adapter.showData(mDatas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(adapter);

    }


    @Override
    protected void initPresenter() {

    }

    @Override
    protected void doInOnCreateEnd() {

    }

    @Override
    protected void doInOnCreateBeforeSuper() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.layout_list;
    }

    @Override
    protected void initIDs() {
        mRecyclerView = (RecyclerView) findViewById(R.id.list_recyclerView);
    }

    @Override
    protected BrioalBaseBroadCastReceiver getReceiver() {
        return null;
    }

    @Override
    protected BrioalBaseDialog getLoadingDialog() {
        return null;
    }
}
