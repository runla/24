package com.yinghai.a24divine_user.module.login.state;

import android.app.Activity;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/23 11:26
 *         Describe：在状态模式的Context角色，是用户操作对象和管理对象，LoginStateManager委托相关的操作给状态对象，
 *         在其中状态的发生改变，LoginStateManager的行为也发生改变。
 *         方法返回true，即系已登录，可以进行操作；返回false则自动跳转登录界面
 */

public class LoginStateManager {

    /**
     * 默认用户未登录
     */
    private UserState userState = new LogoutState();

    /**
     * 是否是登录状态，true or false；
     */
    private boolean mIsLoginState = false;


    /**
     * 私有构造函数，避免外界可以通过new 获取对象
     */
    private LoginStateManager() {
    }

    //单例模式
    public static LoginStateManager getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 静态代码块
     */
    private static class SingletonHolder {
        private static final LoginStateManager instance = new LoginStateManager();
    }

    public void setState(UserState state) {
        this.userState = state;
    }

    /**
     * 获取状态，登录or登出
     * @return true or false
     */
    public boolean getState() {
        return userState.getClass().getSimpleName().equals(LoginState.class.getSimpleName());
    }

    /**
     * 预约占卜服务
     *
     * @param activity
     */
    public boolean onBookDivine(Activity activity) {
        return userState.onBookDivine(activity);
    }

    /**
     * 收藏功能
     *
     * @param activity
     */
    public boolean onCollect(Activity activity) {
        return userState.onCollect(activity);
    }

    /**
     * 评论功能
     *
     * @param activity
     */
    public boolean onComment(Activity activity) {
        return userState.onComment(activity);
    }

    /**
     * 购物功能
     *
     * @param activity
     */
    public boolean onShopping(Activity activity) {
        return userState.onShopping(activity);
    }

    /**
     * 个人中心功能
     *
     * @param activity
     */
    public boolean onMe(Activity activity) {
        return userState.onMe(activity);
    }

    /**
     * 看他人信息
     *
     * @param activity
     */
    public boolean onLookUserInfo(Activity activity) {
        return userState.onLookUserInfo(activity);
    }


}
