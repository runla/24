package com.yinghai.a24divine_user.module.divine.book.pay;

import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.AliPayBean;
import com.yinghai.a24divine_user.bean.WechatPayBean;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/16 17:09
 *         Describe：
 */

public class PayPresenter extends MyBasePresenter<WeChatPayModel,ContractPay.IPayView> implements ContractPay.IPayPresenter, ContractPay.IPayModel.IPayCallback, ContractPay.IPayModel.IAliPayCallback {


    @Override
    protected WeChatPayModel createModel() {
        return new WeChatPayModel();
    }

    public PayPresenter(ContractPay.IPayView view){
        attachView(view);
    }

    @Override
    public void wechatCreateOrder(String orderNo,  int orderType, String spbillCreateIp) {
        mBaseModel.wechatPay(orderNo,orderType,spbillCreateIp,this);
    }

    @Override
    public void aliPay(String orderNo, int orderType) {
        mBaseModel.aliPay(orderNo,orderType,this);
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    @Override
    public void paySuccess(WechatPayBean bean) {
        if (isViewAttached()){
            getBaseView().wechatCreateSuccess(bean);
        }
    }

    @Override
    public void payFailure(String errorMsg) {
        if (isViewAttached()){
            getBaseView().wechatCreateFailure(errorMsg);
        }
    }


    @Override
    public void onPaySuccess(AliPayBean bean) {
        if (isViewAttached()){
            getBaseView().showAliPaySuccess(bean);
        }
    }

    @Override
    public void onPayFailure(String errorMsg) {
        if (isViewAttached()){
            getBaseView().wechatCreateFailure(errorMsg);
        }
    }
}
