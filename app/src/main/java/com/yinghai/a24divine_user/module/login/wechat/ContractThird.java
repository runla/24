package com.yinghai.a24divine_user.module.login.wechat;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.PersonInfoBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

import io.rong.imlib.RongIMClient;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/27 17:02
 *         Describe：第三方登录契约类
 */

public interface ContractThird {

    interface IView extends BaseView{

        /**
         * 第三方登陸失敗
         */
        void showThirdLoginFailure(String msg);

        /**
         * 第三方登录成功
         */
        void showThirdSuccess();

        /**
         * 尚未绑定第三方账号
         * @param thirdPartyId 绑定新用户时，后台需要的数据
         */
        void showIsNewUser(int thirdPartyId);

    }

    interface IPresenter{

        /**
         * 第三方登录
         * @param type  登录方式： 0 微信；3 baceBook
         * @param code  第三方同意登录标识： facebook第三方登录的 code 为access token；
         */
        void thirdLogin(int type,String code);

    }


    interface IModel{

        /**
         * 第三方登录接口（微信或 Facebook）
         * @param type      登录方式： 0 微信；3 baceBook
         * @param code      第三方同意登录标识： facebook第三方登录的 code 为access token；
         * @param callback
         */
        void onThirdLogin(int type, String code, ILoginCallback callback);

        interface ILoginCallback extends IHandleCodeCallback{
            /**
             * 登陸失敗
             */
            void onThirdLoginFailure(String msg);

            /**
             * 第三方登录成功
             */
            void onThirdSuccess(PersonInfoBean bean);

            /**
             * 尚未绑定第三方账号
             * @param thirdPartyId 绑定新用户时，后台需要的数据
             */
            void onIsNewUser(int thirdPartyId);
        }

        /**
         * 登录融云的服务器
         * @param token
         * @param mConnectCallback
         */
        void rongIMLogin(String token, RongIMClient.ConnectCallback mConnectCallback);
    }

}
