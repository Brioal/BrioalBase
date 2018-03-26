package com.brioal.baselib.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * 发送通知和管理通知的工具类
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by Brioal on 3/19/2018.
 */

public class NotificationUtils {
    private Intent mIntent;
    private NotificationManager mManager;
    public static final String id = "channel_1";
    public static final String name = "channel_name_1";
    private int mSmallIcon;
    private String mTitle;
    private String mMessage;
    private Context mContext;
    private int mNotifyID = 2;
    // 请求码
    private int mRequestCode=100;

    public NotificationUtils setId(int id) {
        mNotifyID = id;
        return this;
    }

    public NotificationUtils setContext(Context context) {
        mContext = context;
        mManager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        return this;
    }

    public NotificationUtils setTitle(String title) {
        mTitle = title;
        return this;
    }

    public NotificationUtils setMessage(String message) {
        mMessage = message;
        return this;
    }

    public NotificationUtils setSmallIcon(int smallIcon) {
        mSmallIcon = smallIcon;
        return this;
    }

    public NotificationUtils setIntent(Intent intent) {
        mIntent = intent;
        return this;
    }

    public void build() {
        //适配安卓8.0的消息渠道
        String channelID = 0x123 + "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelID, "TestChannal", NotificationManager.IMPORTANCE_HIGH);
            mManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        // 设置标题
        builder.setTicker(mTitle);
        // 设置通知栏标题
        builder.setContentTitle(mTitle);
        // 设置小图标
        builder.setSmallIcon(mSmallIcon);
        // 设置是否点击自动消失
        builder.setAutoCancel(true);
        // 设置点击事件
        if (mIntent != null) {
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    mContext,
                    mRequestCode,
                    mIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT
            );
            builder.setContentIntent(pendingIntent);
        }
        // 设置具体内容
        builder.setContentText(mMessage);
        // 设置channerlid
        builder.setChannelId(channelID);

        Notification notification = builder.build();
        mManager.notify(mNotifyID, notification);
    }

    /**
     * 清理发出的内容
     */
    public void clean() {
        if (mManager != null) {
            mManager.cancel(mNotifyID);
        }
    }

    /**
     * 清理所有
     */
    public void cleanAll() {
        if (mManager != null) {
            mManager.cancelAll();
        }
    }
}
