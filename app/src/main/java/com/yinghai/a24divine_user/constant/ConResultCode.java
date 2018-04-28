package com.yinghai.a24divine_user.constant;

/**
 * Created by：fanson
 * Created on：2017/10/24 13:28
 * Describe：Http网络结果返回的code
 */

public class ConResultCode {

    /**
     * 请求成功
     */
    public static final int SUCCESS = 1;

    /**
     * 微信登陆成功，尚未绑定
     */
    public static final int IS_NEW_USER = 2;

    /**
     * 缺少参数
     */
    public static final int LOSS_PARAMS = 101;

    /**
     * 未登记、数据不存在
     */
    public static final int NOT_REGISTER = 102;

    /**
     * 密码错误
     */
    public static final int PASSWORD_ERROR = 103;

    /**
     * 验证码过期
     */
    public static final int VERIFICATION_OVERDUE = 105;

    /**
     * 操作失败
     */
    public static final int OPERATE_FAILED = 106;

    /**
     * 异地登录或数据出错，需要重新登录
     */
    public static final int RETRY_LOGIN = 111;

    /**
     * 预约时间早于当前时间
     */
    public static final int BOOK_TIME_ERROR = 117;

    /**
     * 商品库存不足
     */
    public static final int PRODUCT_SOLD_OUT = 115;

    /**
     * 订单状态异常
     */
    public static final int SOLD_OUT = 508;

    /**
     * 重复收藏
     */
    public static final int REPEAT_COLLECT = 1021;

    /**
     * 重复关注
     */
    public static final int REPEAT_FOLLOW = 1022;

    /**
     * 已取消收藏，请勿重复
     */
    public static final int REPEAT_CANCEL_COLLECT = 1023;
}
