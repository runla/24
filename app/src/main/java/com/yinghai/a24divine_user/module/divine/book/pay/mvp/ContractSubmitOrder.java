package com.yinghai.a24divine_user.module.divine.book.pay.mvp;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.SubmitOrderBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/16 14:03
 *         Describe：提交订单的契约类
 */

public interface ContractSubmitOrder {

    interface IView extends BaseView{

        /**
         * 提交订单成功
         */
        void showSubmitSuccess(SubmitOrderBean.DataBean bean);

        /**
         * 提交订单失败
         */
        void showSubmitFailure(String errorMsg);

    }


    interface IPresenter{

        /**
         * 提交占卜订单
         * @param masterId 大师ID
         * @param businessId 业务ID
         * @param sex 性别
         * @param appointmentTime 预约时间
         * @param birthday 生日
         * @param describe 描述
         */
        void onSubmitOrder(int masterId,int businessId,int sex,String appointmentTime,String birthday,String describe);
    }


    interface IModel{

        /**
         * 提交占卜订单
         * @param masterId 大师ID
         * @param businessId 业务ID
         * @param sex 性别
         * @param appointmentTime 预约时间
         * @param birthday 生日
         * @param describe 备注
         * @param callback 回调
         */
        void submitOrder(int masterId,int businessId,int sex,String appointmentTime,String birthday,String describe,ISubmitCallback callback);

        interface ISubmitCallback extends IHandleCodeCallback{

            /**
             * 提交订单成功
             */
            void  onSubmitSuccess(SubmitOrderBean.DataBean bean);

            /**
             * 提交订单失败
             */
            void onSubmitFailure(String errorMsg);

        }

    }

}
