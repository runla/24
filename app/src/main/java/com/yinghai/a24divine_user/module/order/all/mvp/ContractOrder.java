package com.yinghai.a24divine_user.module.order.all.mvp;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.OrderBean;
import com.yinghai.a24divine_user.bean.ProductOrderListBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * Created by：fanson
 * Created on：2017/9/29 13:54
 * Describe：订单的契约管理类
 */

public interface ContractOrder {

    interface IOrderView extends BaseView {

        /**
         * 获取占卜订单记录成功
         */
        void showOrderSuccess(OrderBean bean);

        /**
         * 获取占卜订单记录失败
         */
        void showOrderFailure(String errorMsg);

        /**
         * 获取商品订单记录成功
         */
        void showProductListSuccess(ProductOrderListBean bean);

        /**
         * 获取商品订单记录失败
         */
        void showProductListFailure(String errorMsg);

        /**
         * 完成订单成功
         */
        void completeOrderSuccess();

        /**
         * 完成订单失败
         */
        void completeOrderFailure(String errMsg);
    }

    interface IOrderPresenter {

        /**
         * 获取订单记录(逻辑层)
         *
         * @param status    订单状态；null表示所有
         * @param orderType 订单类型 ：1在线占卜订单；2到访占卜订单；3商品订单
         */
        void onOrderOrder(String status, int orderType);

        /**
         * 完成订单（P 层）
         *
         * @param orderId 订单ID
         * @param type    类型，1为商品订单，2为非商品订单
         */
        void completeOrder(int orderId, int type);
    }

    interface IOrderModel {
        /**
         * 获取订单记录(数据层)
         *
         * @param status    订单状态；null表示所有
         * @param orderType 订单类型 ：1在线占卜订单；2到访占卜订单；3商品订单
         */
        void getOrder(String status, int orderType, int pageNum, IOrderCallback callback);

        interface IOrderCallback extends IHandleCodeCallback {
            void onOrderSuccess(OrderBean bean);

            void onOrderFailure(String errorMsg);
        }


        /**
         * 获取商品订单记录(数据层)
         *
         * @param status 订单状态；null表示所有
         */
        void getProductOrder(String status, int pageNum, IProductCallback callback);

        interface IProductCallback extends IHandleCodeCallback {
            void onProductListSuccess(ProductOrderListBean bean);

            void onProductListFailure(String errorMsg);
        }


        /**
         * 完成订单（数据层）
         *
         * @param orderId
         * @param callback
         * @param type     类型，1为商品订单，2为非商品订单
         */
        void completeOrder(int orderId, int type, ICompleteOrderCallback callback);

        interface ICompleteOrderCallback extends IHandleCodeCallback {

            void onCompleteOrderSuccess();

            void onCompleteOrderFailure(String errMsg);
        }
    }

}
