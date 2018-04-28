package com.yinghai.a24divine_user.module.login.findpassword;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.PersonInfoBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * Created by chenjianrun on 2017/12/19.
 * 描述：注册、找回密码的契约类
 */

public class ContractFindPassword {
    interface IRegisterView extends BaseView{
        /**
         * 获取验证码成功
         */
        void onGetVerifyCodeSuccess();

        /**
         * 获取验证码失败
         * @param errMsg
         */
        void onGetdVerifyCodeFailure(String errMsg);

        /**
         * 注册成功
         */
        void onRegisterSuccess(boolean isHasPassword);

        /**
         * 注册失败
         */
        void onRegisterFailure(String errMsg);

        /**
         * 找回密码成功
         */
        void onFindPasswordSuccess();

        /**
         * 找回密码失败
         * @param errMsg
         */
        void onFindPasswordFailure(String errMsg);


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

        /**
         * 判斷兩次密碼輸入是否相同
         * @return
         */
        boolean isRepeatPassword();

        /**
         * 設置獲取驗證碼是否可點擊
         * @param enable
         */
        void setGetVerifyCodeEnable(boolean enable);
    }

    interface IRegisterPresenter{
        /**
         * 注册或者忘记密码的获取验证码
         * @param type              类型0为注册1为忘记密码
         */
        void sendVerifyCode(int type);

        /**
         *  注册
         */
        void register();

        /**
         * 忘记密码
         */
        void findPassword();
    }

    interface IRegisterModel{
        /**
         * 注册或者忘记密码的获取验证码
         * @param countryCode
         * @param telNo
         * @param type          类型0为注册1为忘记密码
         * @param callback
         */
        void sendVerifyCode(String countryCode,String telNo,int type,ISendVerifyCodeSuccess callback);
        interface ISendVerifyCodeSuccess extends IHandleCodeCallback{
            /**
             * 获取验证码成功
             */
            void onGetVerifyCodeSuccess();

            /**
             * 获取验证码失败
             * @param errMsg
             */
            void onGetdVerifyCodeFailure(String errMsg);
        }

        /**
         * 注册
         * @param countryCode
         * @param telNo
         * @param verification
         * @param callback
         */
        void register(String countryCode,String telNo,String verification,IRegisterSuccess callback);
        interface IRegisterSuccess extends IHandleCodeCallback{
            /**
             * 注册成功
             */
            void onRegisterSuccess(PersonInfoBean personInfoBean);

            /**
             * 注册失败
             */
            void onRegisterFailure(String errMsg);

        }
        /**
         * 忘记密码
         * @param countryCode
         * @param telNo
         * @param verification
         * @param password
         * @param callback
         */
        void findPassword(String countryCode,String telNo,String verification,String password,IFindPasswordCallback callback);
        interface IFindPasswordCallback extends IHandleCodeCallback{
            /**
             * 找回密码成功
             */
            void onFindPasswordSuccess();

            /**
             * 找回密码失败
             * @param errMsg
             */
            void onFindPasswordFailure(String errMsg);
        }
    }
}
