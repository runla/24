package com.yinghai.a24divine_user.utils;

import com.orhanobut.logger.Logger;

/**
 * Created by：fanson
 * Created on：2017/8/29 13:19
 * Describe：打印Log的工具类
 */

public class LogUtils {

    public static void i(String message, Object object) {
        Logger.i(message, object);
    }

    public static void d(Object object) {
        Logger.d(object);
    }

    public static void d(String tag, Object object) {
        Logger.t(tag);
        Logger.d(object);
    }

    public static void w(String message, Object object) {
        Logger.w(message, object);
    }

    public static void e(String message, Object object) {
        Logger.e(message, object);
    }

    public static void v(String message, Object object) {
        Logger.v(message, object);
    }

    public static void json(String json) {
        Logger.json(json);
    }

    /**
     * 更改TAG
     *
     * @param tag
     */
    public static void t(String tag) {
        Logger.t(tag);
    }
}
