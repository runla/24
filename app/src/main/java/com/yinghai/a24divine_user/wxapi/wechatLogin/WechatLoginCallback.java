package com.yinghai.a24divine_user.wxapi.wechatLogin;

/**
 * Created by chenjianrun on 2017/5/8.
 * 描述：调起微信登录时的结果回调，此时的结果是不参与我们自己的后台服务的
 */

public interface WechatLoginCallback {
    /**
     * 调起微信登录的界面
     */
    void showWechatLoginSuccess();
    /**
     * 调起微信登录成功
     * @param code  微信登录唯一标识
     */
    void wechatLoginSuccess(String code);

    /**
     * 调起微信登录失败（包含取消、出错）
     */
    void wechatLoginFailed(String errMsg);
}
