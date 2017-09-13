package com.brioal.brioalbase.receivetest;

import com.brioal.baselib.base.BrioalBaseBroadCastReceiver;
import com.brioal.baselib.interfaces.BrioalOnReceivedListener;
import com.brioal.brioalbase.PersonBean;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by Brioal on 2017/9/13.
 */

public class ActivityBroadCastReceiver extends BrioalBaseBroadCastReceiver<PersonBean> {


    public ActivityBroadCastReceiver(BrioalOnReceivedListener<PersonBean> loadListener) {
        super(loadListener);
    }

    @Override
    protected String getClassName() {
        return "ActivityBroadCastReceiver";
    }
}
