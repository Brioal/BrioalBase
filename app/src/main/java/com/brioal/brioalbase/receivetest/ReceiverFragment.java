package com.brioal.brioalbase.receivetest;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.brioal.baselib.base.BrioalBaseBroadCastReceiver;
import com.brioal.baselib.base.BrioalBaseDialog;
import com.brioal.baselib.base.BrioalBaseFragment;
import com.brioal.baselib.interfaces.BrioalOnReceivedListener;
import com.brioal.baselib.utils.log.BLog;
import com.brioal.brioalbase.PersonBean;
import com.brioal.brioalbase.R;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by Brioal on 2017/9/13.
 */

public class ReceiverFragment extends BrioalBaseFragment implements BrioalOnReceivedListener<PersonBean> {
    private TextView mTvMsg;
    private Button mBtnSend;


    @Override
    protected int getLayoutID() {
        return R.layout.fra_receiver;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        //发送消息
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonBean personBean = new PersonBean();
                personBean.setName("李四");
                personBean.setSex(100);
                personBean.setSex(0);
                ActivityBroadCastReceiver receiver = new ActivityBroadCastReceiver(null);
                sendBroadCastMsg(receiver, personBean);
            }
        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initIDs() {
        mTvMsg = mRootView.findViewById(R.id.fra_receiver_tv_mg);
        mBtnSend = mRootView.findViewById(R.id.fra_receiver_btn_send);
    }

    @Override
    protected BrioalBaseBroadCastReceiver getReceiver() {
        return new FragmentReceiver(this);
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
        BLog.content("Fragment收到的内容", result.toString());
        mTvMsg.setText(result.toString());
    }
}
