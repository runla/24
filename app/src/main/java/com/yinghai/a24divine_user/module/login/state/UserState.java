package com.yinghai.a24divine_user.module.login.state;

import android.app.Activity;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/23 11:01
 *         Describe：状态模式（基类）
 */

public interface UserState {

    /**
     * 预约占卜服务
     */
    boolean onBookDivine(Activity activity);

    /**
     * 收藏
     */
    boolean onCollect(Activity activity);

    /**
     * 评论
     */
    boolean onComment(Activity activity);

    /**
     * 购买/购物车
     */
    boolean onShopping(Activity activity);

    /**
     * 点击个人的一切按钮
     */
    boolean onMe(Activity activity);

    /**
     * 查看他人信息
     * @param activity
     * @return true or false
     */
    boolean onLookUserInfo(Activity activity);

}
