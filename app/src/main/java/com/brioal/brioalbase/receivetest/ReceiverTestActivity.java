package com.brioal.brioalbase.receivetest;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.brioal.baselib.base.BrioalBaseActivity;
import com.brioal.baselib.base.BrioalBaseBroadCastReceiver;
import com.brioal.baselib.base.BrioalBaseDialog;
import com.brioal.baselib.interfaces.BrioalOnReceivedListener;
import com.brioal.baselib.utils.log.BLog;
import com.brioal.brioalbase.PersonBean;
import com.brioal.brioalbase.R;

public class ReceiverTestActivity extends BrioalBaseActivity implements BrioalOnReceivedListener<PersonBean> {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Button mBtnSend;


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        //初始化ViewPager
        ReceiverViewPagerAdapter adapter = new ReceiverViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        //发送数据
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonBean personBean = new PersonBean();
                personBean.setName("张三");
                personBean.setAge(11);
                personBean.setSex(1);
                sendBroadCastMsg(new FragmentReceiver(null), personBean);
            }
        });

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
        return R.layout.act_receiver_test;
    }

    @Override
    protected void initIDs() {
        mViewPager = (ViewPager) findViewById(R.id.receiver_ViewPager);
        mBtnSend = (Button) findViewById(R.id.receiver_btn_send);
        mTabLayout = (TabLayout) findViewById(R.id.receiver_tabLayout);
    }

    @Override
    protected BrioalBaseBroadCastReceiver getReceiver() {
        return new ActivityBroadCastReceiver(this);
    }

    @Override
    protected BrioalBaseDialog getLoadingDialog() {
        return null;
    }

    @Override
    protected boolean isDialogCanCancel() {
        return false;
    }

    @Override
    public void OnReceived(PersonBean result) {
        BLog.content("Activity收到的内容", result);
        showToast(result.toString());
    }
}
