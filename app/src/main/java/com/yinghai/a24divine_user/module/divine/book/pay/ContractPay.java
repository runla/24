package com.yinghai.a24divine_user.module.divine.book.pay;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.AliPayBean;
import com.yinghai.a24divine_user.bean.WechatPayBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * Created by chenjianrun on 2017/5/3.
 */

public interface ContractPay {

     interface IPayView extends BaseView {

        /**
         * 微信统一下单成功
         */
        void wechatCreateSuccess( WechatPayBean bean);

        /**
         * 微信统一下单失败
         */
        void wechatCreateFailure(String errorMsg);


         /**
          * 支付宝下单成功
          */
         void showAliPaySuccess( AliPayBean bean);

         /**
          * 支付宝下单失败
          */
         void showAliPayFailure(String errorMsg);


    }

     interface IPayPresenter {
        /**
         * 微信统一下单
         */
        void wechatCreateOrder(String orderNo,int orderType,String spbillCreateIp);

         /**
          * 支付宝下单
          * @param orderNo 订单号
          * @param orderType 订单类型 1：在线占卜 2：到访  3：商品
          */
         void aliPay(String orderNo,int orderType);
    }

    interface IPayModel{

        void wechatPay(String orderNo,int orderType,String spbillCreateIp,IPayCallback callback);

        interface IPayCallback extends IHandleCodeCallback{

            void paySuccess(WechatPayBean bean);

            void payFailure(String errorMsg);

        }


        void aliPay(String orderNo,int orderType, IAliPayCallback callback);

        interface IAliPayCallback extends IHandleCodeCallback{

            void onPaySuccess(AliPayBean bean);

            void onPayFailure(String errorMsg);

        }

    }
}
