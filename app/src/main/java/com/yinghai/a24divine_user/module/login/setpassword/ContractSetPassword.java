package com.yinghai.a24divine_user.module.login.setpassword;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.PersonInfoBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * Created by chenjianrun on 2017/12/20.
 * 描述：设置密码的契约类
 */

public class ContractSetPassword {

    interface ISetPasswordView extends BaseView{
        void setPasswordSuccess();
        void setPasswordFailure(String errMsg);

        /**
         * 获取密码
         * @return
         */
        String getPassword();


        /**
         * 判斷兩次密碼輸入是否相同
         * @return
         */
        boolean isRepeatPassword();
    }

    interface ISetPasswordPresenter{
        /**
         * 这是密码
         */
        void setPassword();
    }

    interface ISetPasswordModel{
        /**
         * 设置密码
         * @param password
         */
        void setPassword(String password,ISetPasswordCallback callback);
        interface ISetPasswordCallback extends IHandleCodeCallback{
            void setPasswordSuccess(PersonInfoBean personInfoBean);
            void setPasswordFailure(String errMsg);
        }
    }
}
