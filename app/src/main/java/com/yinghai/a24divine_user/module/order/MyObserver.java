package com.yinghai.a24divine_user.module.order;

/**
 * @author Created by：fanson
 *         Created Time: 2017/12/22 12:21
 *         Describe：
 */

public interface MyObserver {

    /**
     * 更新数据
     * @param status 订单状态值
     */
    void update(int status);
}
