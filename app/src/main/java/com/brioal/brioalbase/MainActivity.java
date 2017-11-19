package com.brioal.brioalbase;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import com.brioal.baselib.base.BrioalBaseActivity;
import com.brioal.baselib.base.BrioalBaseBroadCastReceiver;
import com.brioal.baselib.base.BrioalBaseDialog;
import com.brioal.baselib.interfaces.OnDialogActionListener;
import com.brioal.baselib.utils.CacheUtil;
import com.brioal.baselib.utils.DateFormatUtil;
import com.brioal.baselib.utils.ListUtil;
import com.brioal.baselib.utils.log.BLog;
import com.brioal.brioalbase.receivetest.ReceiverTestActivity;

import java.util.ArrayList;

public class MainActivity extends BrioalBaseActivity {

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        testDataFormat();
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
        return R.layout.activity_main;
    }

    @Override
    protected void initIDs() {

    }

    @Override
    protected BrioalBaseBroadCastReceiver getReceiver() {
        return null;
    }

    @Override
    protected BrioalBaseDialog getLoadingDialog() {
        return null;
    }

    @Override
    protected boolean isDialogCanCancel() {
        return false;
    }

    /**
     * 测试日期格式化工具
     */
    private void testDataFormat() {
        BLog.content("DateFormatUtil.formatLongTime(System.currentTimeMillis())", DateFormatUtil.formatLongTime(System.currentTimeMillis()));
    }

    public void testBLog(View view) {
        BLog.title("测试标题");
        BLog.content("大小", "5", "高度", "3");
        BLog.e("测试");
    }

    public void testDialog(View view) {
        MainDialog dialog = new MainDialog(mContext);
        dialog.setDialogActionListener(new OnDialogActionListener() {
            @Override
            public void onDialogShow(DialogInterface dialogInterface) {
                showToast("onDialogShow");
            }

            @Override
            public void onDialogDismiss(DialogInterface dialogInterface) {
                showToast("onDialogDismiss");
            }

            @Override
            public void onDialogCancel(DialogInterface dialogInterface) {
                showToast("onDialogCancel");
            }

            @Override
            public void onAction1(Object result1) {
                showToast("onAction1");
            }

            @Override
            public void onAction2(Object result2) {
                showToast("onAction2");
            }

            @Override
            public void onAction3(Object result3) {
                showToast("onAction3");
            }
        });
        dialog.showDialog();
    }

    public void testLoadingVIew(View view) {
        showBaseProgressDialog("测试Dialog");
    }


    public void saveCache(View view) {
        try {
            ArrayList<String> list = new ArrayList<>();
            list.add("QXC150373133866515049274311850");
            list.add("QXC150373133866515049274337660");
            CacheUtil<ArrayList<String>> cacheUtil = new CacheUtil<>();
            cacheUtil.saveCache(MainActivity.this, list, "QXC150373133866515049269741840");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readChe(View view) {
        try {
            CacheUtil<ArrayList<String>> cacheUtil = new CacheUtil<>();
            ArrayList<String> map = cacheUtil.getCache(MainActivity.this, "QXC150373133866515049269741840");
            ListUtil.log(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testReceiver(View view) {
        Intent intent = new Intent(mContext, ReceiverTestActivity.class);
        startActivity(intent);
    }

    public void saveShare(View view) {
        MainShareUtil shareUtil = new MainShareUtil(mContext);
        shareUtil.saveName("Brioal");
    }

    public void readShare(View view) {
        MainShareUtil shareUtil = new MainShareUtil(mContext);
        String name = shareUtil.readName();
        showToast(name);
    }

}
