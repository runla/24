package com.yinghai.a24divine_user.callback;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/27 9:53
 *         Describe：监听Adapter里的点击事件
 */

public interface OnAdapterListener {

    /**
     * 点击Adapter的某项
     * @param object 序号/Model
     */
    void clickItem(Object... object);

}
