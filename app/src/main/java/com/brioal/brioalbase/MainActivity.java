package com.brioal.brioalbase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.brioal.baselib.utils.log.BLog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testBLog(View view) {
        BLog.title("测试标题");
        BLog.content("大小", "5", "高度", "3");
        BLog.e("测试");
    }
}
