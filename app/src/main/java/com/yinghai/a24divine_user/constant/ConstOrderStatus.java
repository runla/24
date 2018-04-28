package com.yinghai.a24divine_user.constant;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/15 10:09
 *         Describe：订单状态
 */

public class ConstOrderStatus {

    /**
     * 所有
     */
    public static final int ALL = 0;

    /**
     * 等待支付
     */
    public static final int WAITING_PAY = 1;

    /**
     * 已支付
     */
    public static final int HAS_BEEN_PAID = 2;


    /**
     * 已确定
     */
    public static final int HAS_BEEN_CONFIRM = 3;

    /**
     * 进行中
     */
    public static final int ORDER_ING = 4;

    /**
     * 已完成
     */
    public static final int COMPLETED = 5;

    /**
     * 退款中
     */
    public static final int REFUND_ING = 997;

    /**
     * 已退款
     */
    public static final int REFUND = 998;


    /**
     * 已取消
     */
    public static final int CANCELED = 999;

}
