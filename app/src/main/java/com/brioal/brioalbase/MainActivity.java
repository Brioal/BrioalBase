package com.brioal.brioalbase;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.brioal.baselib.base.BrioalBaseDialog;
import com.brioal.baselib.utils.CacheUtil;
import com.brioal.baselib.utils.DateFormatUtil;
import com.brioal.baselib.utils.ListUtil;
import com.brioal.baselib.utils.ScreenUtil;
import com.brioal.baselib.utils.SoftInputUtil;
import com.brioal.baselib.utils.log.BLog;
import com.brioal.brioalbase.list.TestListActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testDataFormat();
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

    public void testAdapter(View view) {
        startActivity(new Intent(this, TestListActivity.class));
    }

    public void testDialog(View view) {
        TestDialog dialog = new TestDialog();
        dialog.showDialog();
    }

    class TestDialog extends BrioalBaseDialog {

        @Override
        protected Drawable getDialogBackGround() {
            return new ColorDrawable(Color.GREEN);
        }

        @Override
        protected int getDialogHeight() {
            return ScreenUtil.getScreenHeight(getContext()) / 2;
        }

        @Override
        protected int getDialogWidth() {
            return ScreenUtil.getScreenHeight(getContext());
        }

        @Override
        protected int getWindowAnimations() {
            return 0;
        }

        @Override
        protected void onDialogDismissListener(DialogInterface dialogInterface) {
            BLog.title("Dialog隐藏");
        }

        @Override
        protected void onDialogCancelListener(DialogInterface dialogInterface) {
            BLog.title("Dialog Cancel");
        }

        @Override
        protected void onDialogShowListener(DialogInterface dialogInterface) {
            BLog.title("Dialog Show");
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    SoftInputUtil.showSoftInput(getContext(), mRootView.findViewById(R.id.layout_test_et_input));
                }
            });
        }

        @Override
        protected void bindView(View contentView) {
            final EditText editText = contentView.findViewById(R.id.layout_test_et_input);
            Button btnSubmit = contentView.findViewById(R.id.layout_test_btn_submit);
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String content = editText.getText().toString().trim();
                    BLog.title(content);
                    hideDialog();
                }
            });
        }

        @Override
        protected int getGravity() {
            return Gravity.BOTTOM;
        }

        @Override
        protected int getLayoutID() {
            return R.layout.layout_input_test;
        }

        @Override
        protected Context getContext() {
            return MainActivity.this;
        }
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
}
