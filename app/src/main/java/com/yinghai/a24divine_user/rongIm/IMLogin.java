package com.yinghai.a24divine_user.rongIm;

import android.net.Uri;
import android.text.TextUtils;

import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConstantPreference;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

/**
 * Created by chenjianrun on 2017/11/8.
 * 描述：融云 IM 登录
 */

public class IMLogin {

    /**
     * 登录融云服务器
     * @param token
     * @param callback
     */
    public static void imLogin(final String token, final RongIMClient.ConnectCallback callback){
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                if (callback != null) {
                    callback.onTokenIncorrect();
                }
            }

            @Override
            public void onSuccess(final String userId) {
                if (callback != null) {
                    callback.onSuccess(userId);
                }
                SharePreferenceHelper.putString(ConstantPreference.USER_TOKEN_STR,token);
                SharePreferenceHelper.apply();
                // 设置用户信息提供者
                RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
                    @Override
                    public UserInfo getUserInfo(String s) {
                        final String name = SharePreferenceHelper.getString(ConstantPreference.S_USER_NAME, "");
                        final String face = SharePreferenceHelper.getString(ConstantPreference.S_USER_PHOTO, "");
                        UserInfo userInfo = new UserInfo(userId, name, Uri.parse(String.format("%s%s", ConHttp.BASE_URL,face)));
                        return userInfo;
                    }
                }, true);
                // 发送消息中携带用户信息
                RongIM.getInstance().setMessageAttachedUserInfo(true);
                RongIM.getInstance().enableNewComingMessageIcon(true);//显示新消息提醒
                RongIM.getInstance().enableUnreadMessageIcon(true);//显示未读消息数目

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                if (callback != null) {
                    callback.onError(errorCode);
                }
            }
        });
    }

    /**
     * 检查是否已经登录融云服务；    已登录：返回 未登录：尝试登录
     * @param callback
     */
    public static void imCheckLogin(RongIMClient.ConnectCallback callback){
        // 已登录
        if (imIsLogin()) {
            if (callback != null) {
                callback.onSuccess(RongIM.getInstance().getCurrentUserId());
            }
            return;
        }

        // 未登录
        String token = SharePreferenceHelper.getString(ConstantPreference.USER_TOKEN_STR,"");
        if (TextUtils.isEmpty(token)) {
            if (callback != null) {
                callback.onTokenIncorrect();
            }
            return;
        }
        imLogin(token,callback);
    }

    /**
     * 登出之后不再受到任何的新消息
     */
    public static void logout(){
        RongIM.getInstance().logout();
    }

    /**
     * 检查是否已经登录融云服务器
     * @return
     */
    public static boolean imIsLogin(){
        if (RongIM.getInstance().getCurrentConnectionStatus() == RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTED) {
            return true;
        }
        return false;
    }
}
