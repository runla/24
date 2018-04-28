package com.yinghai.a24divine_user.callback;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/2 11:42
 *         Describe：统一处理Result Code的接口
 */

public interface IHandleCodeCallback {

    /**
     * 接收code回调
     * @param code code
     */
    void handlerResultCode(int code);

}
