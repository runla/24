package com.yinghai.a24divine_user.wxapi.pay;

/**
 * Created by chenjianrun on 2017/4/11.
 * 描述：微信支付的回调接口
 */

public interface WechatPayCallback {
    /**
     * 成功显示付款的界面，还未付款
     */
    void showPayLoading();

    /**
     * 支付成功
     */
    void paySuccess();

    /**
     * 支付失败
     */
    void payFailure(int errCode);

}
