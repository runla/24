package com.yinghai.a24divine_user.Impl;

import android.content.Context;
import android.widget.ImageView;

import com.example.fansonlib.image.ImageLoaderUtils;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by：fanson
 * Created on：2017/5/15 11:16
 * Describe：Banner图片加载的接口实现
 */

public class BannerImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        if (path instanceof Integer) {
            ImageLoaderUtils.displayFromDrawable(context,(Integer) path, imageView);
        } else {
            ImageLoaderUtils.loadImage(context, imageView,(String) path);
        }
    }
}
