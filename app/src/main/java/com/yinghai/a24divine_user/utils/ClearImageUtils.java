package com.yinghai.a24divine_user.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/9 10:20
 *         Describe：释放图片资源的工具类
 */

public class ClearImageUtils {

    /**
     * 回收这个ImageView所对应的资源
     *
     * @param view ImageView
     */
    public static void recycleImageBitmap(ImageView view) {
        if (view != null) {
            view.setImageDrawable(null);//首先解除与bitmap有关的所有绑定,否则出现：Canvas: trying to use a recycled bitmap android.graphics.Bitmap
            BitmapDrawable drawable = (BitmapDrawable) view.getDrawable();
            recycleImageDrawable(drawable);
        }
    }

    /**
     * 回收这个Background所对应的资源
     *
     * @param view Background
     */
    public static void recycleBackground(View view) {
        if (view != null) {
            view.setBackground(null); //首先解除与bitmap有关的所有绑定,否则出现：Canvas: trying to use a recycled bitmap android.graphics.Bitmap
            BitmapDrawable drawable = (BitmapDrawable) view.getBackground();
            recycleImageDrawable(drawable);
        }
    }

    /**
     * 主动释放ImageView的背景资源
     */
    public static void recycleBackgroundBitmap(ImageView view) {
        if (view != null) {
            view.setImageDrawable(null);//首先解除与bitmap有关的所有绑定,否则出现：Canvas: trying to use a recycled bitmap android.graphics.Bitmap
            BitmapDrawable drawable = (BitmapDrawable) view.getDrawable();
            recycleImageDrawable(drawable);
        }
    }

    private static void recycleImageDrawable(BitmapDrawable drawable) {
        if (drawable != null) {
            Bitmap bitmap = drawable.getBitmap();
            recycleBitmap(bitmap);
        }
    }

    private static void recycleBitmap(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
        }
    }

}
