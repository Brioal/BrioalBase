package com.brioal.baselib.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioa on 2018/1/21.
 */

public class DrawableUtil {

    /**
     * Bitmap转Drawable
     *
     * @param bitmap
     * @return
     */
    public static Drawable convertFromBitmap(Bitmap bitmap) {
        Drawable drawable = null;
        try {
            drawable = new BitmapDrawable(bitmap);
            return drawable;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return drawable;
    }
}
