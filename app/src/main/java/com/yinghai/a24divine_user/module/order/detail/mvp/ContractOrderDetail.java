package com.yinghai.a24divine_user.module.order.detail.mvp;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.NoDataBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/18 18:07
 *         Describe：占卜订单详情契约类
 */

public interface ContractOrderDetail {

    interface IView extends BaseView{

        /**
         * 取消占卜订单成功
         */
        void showCancelSuccess(NoDataBean bean);

        /**
         * 取消占卜订单失败
         */
        void showCancelFailure(String errorMsg);

        /**
         * 完成占卜订单成功
         */
        void showCompleteSuccess( );

        /**
         * 完成占卜订单失败
         */
        void showCompleteFailure(String errorMsg);

    }


    interface IPresenter{

        /**
         * * 取消占卜订单
         * @param orderId 订单号
         * @param type 订单类型；商品or占卜
         */
        void onCancelOrder(int orderId,int type);

        /**
         * 完成占卜订单
         * @param orderId 订单号
         */
        void onCompleteOrder(int orderId);
    }


    interface IModel{

        /**
         * * 取消占卜订单
         * @param orderId 订单号
         * @param type 订单类型；商品or占卜
         */
        void cancelOrder(int orderId,int type,ICancelCallback callback);


        interface ICancelCallback extends IHandleCodeCallback{

            /**
             * 取消占卜订单成功
             */
            void onCancelSuccess(NoDataBean bean);

            /**
             * 取消占卜订单失败
             */
            void onCancelFailure(String errorMsg);


        }

    }



}
