package com.yinghai.a24divine_user.module.login.phone;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.PersonInfoBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

import io.rong.imlib.RongIMClient;

/**
 * @author  Created by：fanson
 * Created on：2017/10/25 14:19
 * Describe：登录的契约管理类
 */

public interface ContractLogin {

     interface ILoginView extends BaseView {

        /**
         * 登陸失敗
         */
        void onLoginFailure(String msg);

        /**
         * 登录成功
         */
        void loginSuccess(boolean isHasPassword);

        /**
         * 获取验证码失敗
         */
        void getCodeFailure(String msg);

        /**
         * 获取验证码成功
         */
        void getCodeSuccess();

         /**
          * 获取区号
          * @return
          */
         String getCountryCode();

         /**
          * 获取手机号码
          * @return
          */
         String getTelNo();


         /**
          * 获取密码
          * @return
          */
         String getPassword();

         /**
          * 获取验证码
          * @return
          */
         String getVerifyCode();

         void setGetVerifyCodeEnable(boolean enable);
     }

    interface ILoginPresenter {

        /**
         * 登录验证
         *
         * @param countryCode
         * @param telNo
         * @param verifyCode
         */
        void onLogin(String countryCode, String telNo, String verifyCode);

        void onLoingPwd(String countryCode,String telNo, String pwd);

        /**
         * 获取验证码
         */
        void onGetCode(final String countryCode, String telNo);
    }

    interface ILoginModel  {
        /**
         * 验证码登录验证
         *  @param countryCode
         * @param telNo
         * @param verifyCode
         * @param callback
         */
        void verifyLogin(String countryCode, String telNo, String verifyCode, ILoginCallback callback);

        /**
         * 密码登录
         * @param countryCode
         * @param telNo
         * @param password
         * @param callback
         */
        void pwdLogin(String countryCode, String telNo, String password, ILoginCallback callback);

        interface ILoginCallback extends IHandleCodeCallback{
            void onLoginSuccess(PersonInfoBean bean);
            void onLoginFailure(String errorMsg);
        }

        /**
         * 获取验证码
         */
        void getVerifyCode(final String countryCode, String telNo, IGetCodeCallback callback);

        interface IGetCodeCallback extends IHandleCodeCallback {
            void onGetCodeSuccess();
            void onGetCodeFailure(String errorMsg);
        }

        /**
         * 登录融云的服务器
         * @param token
         * @param mConnectCallback
         */
        void rongIMLogin(String token, RongIMClient.ConnectCallback mConnectCallback);
    }


}
