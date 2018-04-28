package com.yinghai.a24divine_user.module.login.state;

import android.app.Activity;
import android.content.Intent;

import com.yinghai.a24divine_user.module.login.LoginActivity;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/23 11:09
 *         Describe：（状态模式）用户在未登录的情况下操作，返回false就自动跳转登录界面
 */

public class LogoutState implements UserState, LoginTipWindow.IToLoginListener {

    private Activity mActivity;

    @Override
    public boolean onBookDivine(Activity activity) {
        goLoginActivity(activity);
        return false;
    }

    @Override
    public boolean onCollect(Activity activity) {
        goLoginActivity(activity);
        return false;
    }

    @Override
    public boolean onComment(Activity activity) {
        goLoginActivity(activity);
        return false;
    }

    @Override
    public boolean onShopping(Activity activity) {
        goLoginActivity(activity);
        return false;
    }

    @Override
    public boolean onMe(Activity activity) {
        goLoginActivity(activity);
        return false;
    }

    @Override
    public boolean onLookUserInfo(Activity activity) {
        goLoginActivity(activity);
        return false;
    }

    private void goLoginActivity(Activity activity){
        mActivity = activity;
        new LoginTipWindow(activity,this).showPopupWindow();

    }

    @Override
    public void gotoLogin() {
        mActivity.startActivity(new Intent(mActivity, LoginActivity.class));
        mActivity.finish();
        mActivity = null;
    }
}
