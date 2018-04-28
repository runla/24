package com.yinghai.a24divine_user.callback;

/**
 * @author Created by：fanson
 *         Created Time: 2018/3/15 11:26
 *         Describe：版本检测更新的接口回调
 */

public interface ICheckVersionCallBack {

    /**
     * 当前版本不是最新版
     * @param isForceUpdate 是否需要强制更新
     * @param describe 版本描述
     */
    void onNotLatest(boolean isForceUpdate, String describe);
}
