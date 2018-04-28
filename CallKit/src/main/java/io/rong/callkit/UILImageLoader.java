package io.rong.callkit;

/**
 * Created by chenjianrun on 2018/3/15.
 */

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.example.fansonlib.function.imagepicker.loader.ImageLoader;
import com.example.fansonlib.utils.ImageLoaderProxy;
import com.nostra13.universalimageloader.core.assist.ImageSize;

/**
 * @描述 图片选择控件需要用到的图片加载类
 */
public class UILImageLoader implements ImageLoader {


    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        ImageSize size = new ImageSize(width, height);
        ImageLoaderProxy.getImageLoader().displayImage(Uri.parse("file://" + path).toString(), imageView, size);
    }

    @Override
    public void clearMemoryCache() {
    }
}