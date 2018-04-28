package com.yinghai.a24divine_user.module.login.state;

import android.app.Activity;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/23 11:08
 *         Describe：（状态模式）用户在登录后的情况下操作,返回true方可进行操作
 */

public class LoginState implements UserState{

    @Override
    public boolean onBookDivine(Activity activity) {
        return true;
    }

    @Override
    public boolean onCollect(Activity activity) {
        return true;
    }

    @Override
    public boolean onComment(Activity activity) {
        return true;
    }

    @Override
    public boolean onShopping(Activity activity) {
        return true;
    }

    @Override
    public boolean onMe(Activity activity) {
        return true;
    }

    @Override
    public boolean onLookUserInfo(Activity activity) {
        return true;
    }
}
