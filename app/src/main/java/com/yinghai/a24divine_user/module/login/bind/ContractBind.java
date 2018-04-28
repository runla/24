package com.yinghai.a24divine_user.module.login.bind;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.PersonInfoBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

import io.rong.imlib.RongIMClient;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/29 13:23
 *         Describe：第三方登陆绑定的操作
 */

public interface ContractBind {

    interface IView extends BaseView {

        /**
         * 获取验证码成功
         */
        void showGetCodeSuccess();

        /**
         * 获取验证码失败
         */
        void showGetCodeFailure(String errorMsg);


        /**
         * 绑定成功
         */
        void showBindSuccess();

        /**
         * 绑定失败
         */
        void showBindFailure(String errorMsg);

    }


    interface IPresenter {

        /**
         * 获取验证码
         *
         * @param countryCode 区号
         * @param tel         号码
         * @param thirdId     第三方ID
         */
        void getCode(String countryCode, String tel, int thirdId);

        /**
         * 获取验证码
         *
         * @param countryCode  区号
         * @param tel          号码
         * @param thirdId      第三方ID
         * @param verification 验证码
         */
        void bind(String countryCode, String tel, int thirdId, String verification);

    }


    interface IModel{

        void onGetCode(String countryCode, String tel, int thirdId,IGetCodeCallback callback);

        interface IGetCodeCallback extends IHandleCodeCallback{
            /**
             * 获取验证码成功
             */
            void onGetCodeSuccess();

            /**
             * 获取验证码失败
             */
            void onGetCodeFailure(String errorMsg);
        }


        void onBind(String countryCode, String tel, int thirdId, String verification,IBindCallback callback);

        interface IBindCallback extends IHandleCodeCallback{
            /**
             * 绑定成功
             */
            void onBindSuccess(PersonInfoBean bean);

            /**
             * 绑定失败
             */
            void onBindFailure(String errorMsg);
        }

        /**
         * 登录融云的服务器
         * @param token
         * @param mConnectCallback
         */
        void rongIMLogin(String token, RongIMClient.ConnectCallback mConnectCallback);
    }

}
