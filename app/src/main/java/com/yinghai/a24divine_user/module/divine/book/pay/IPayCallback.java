package com.yinghai.a24divine_user.module.divine.book.pay;

/**
 * Created by chenjianrun on 2017/4/11.
 */

public interface IPayCallback {
    /**
     * 成功显示付款的界面，还未付款
     */
    void showPaySuccess();
    /**
     * 支付成功
     */
    void success();

    /**
     * 支付失败
     */
    void failed(int errCode);

    /**
     * 支付取消
     */
    void cancel();
}
