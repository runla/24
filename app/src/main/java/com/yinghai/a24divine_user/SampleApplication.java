package com.yinghai.a24divine_user;

import android.content.Context;

import com.tencent.tinker.loader.app.TinkerApplication;

import static com.tencent.tinker.loader.shareutil.ShareConstants.TINKER_ENABLE_ALL;

/**
 * Created by：fanson
 * Created on：2017/9/28 14:52
 * Describe：
 */

public class SampleApplication extends TinkerApplication {
    public static Context context;

    public SampleApplication() {
        super(TINKER_ENABLE_ALL, "com.yinghai.a24divine_user.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
        //参数解析
//        参数1：tinkerFlags 表示Tinker支持的类型 dex only、library only or all suuport，default: TINKER_ENABLE_ALL
//        参数2：delegateClassName Application代理类 这里填写你自定义的ApplicationLike
//        参数3：loaderClassName Tinker的加载器，使用默认即可
//        参数4：tinkerLoadVerifyFlag 加载dex或者lib是否验证md5，默认为false
    }

    /*@Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }*/

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {    return context;}
}
