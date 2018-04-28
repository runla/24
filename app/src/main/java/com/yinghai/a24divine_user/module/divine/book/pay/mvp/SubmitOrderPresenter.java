package com.yinghai.a24divine_user.module.divine.book.pay.mvp;

import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.SubmitOrderBean;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/16 14:11
 *         Describe：提交订单P层
 */

public class SubmitOrderPresenter extends MyBasePresenter<SubmitOrderModel, ContractSubmitOrder.IView> implements ContractSubmitOrder.IPresenter, ContractSubmitOrder.IModel.ISubmitCallback {


    public SubmitOrderPresenter(ContractSubmitOrder.IView view) {
        attachView(view);
    }

    @Override
    protected SubmitOrderModel createModel() {
        return new SubmitOrderModel();
    }


    @Override
    public void onSubmitOrder(int masterId, int businessId,int sex, String appointmentTime, String birthday,String describe) {
        mBaseModel.submitOrder(masterId,businessId, sex, appointmentTime, birthday,describe, this);
    }


    @Override
    public void onSubmitSuccess(SubmitOrderBean.DataBean bean) {
        if (isViewAttached()) {
            getBaseView().showSubmitSuccess(bean);
        }
    }

    @Override
    public void onSubmitFailure(String errorMsg) {
        if (isViewAttached()) {
          getBaseView().showSubmitFailure(errorMsg);
        }
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }
}
