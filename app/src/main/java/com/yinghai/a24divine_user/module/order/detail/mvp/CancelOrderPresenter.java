package com.yinghai.a24divine_user.module.order.detail.mvp;

import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.NoDataBean;
import com.yinghai.a24divine_user.module.order.all.mvp.ContractOrder;
import com.yinghai.a24divine_user.module.order.all.mvp.OrderModel;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/18 18:14
 *         Describe：取消占卜订单P层
 */

public class CancelOrderPresenter extends MyBasePresenter<CancelOrderModel, ContractOrderDetail.IView> implements ContractOrderDetail.IPresenter, ContractOrderDetail.IModel.ICancelCallback, ContractOrder.IOrderModel.ICompleteOrderCallback {

    private OrderModel mOrderModel;

    private OrderModel getOrderModel() {
        if (mOrderModel == null) {
            mOrderModel = new OrderModel();
        }
        return mOrderModel;
    }


    public CancelOrderPresenter(ContractOrderDetail.IView view) {
        attachView(view);
    }

    @Override
    protected CancelOrderModel createModel() {
        return new CancelOrderModel();
    }

    @Override
    public void onCancelOrder(int orderId,int type) {
        mBaseModel.cancelOrder(orderId,type, this);
    }

    @Override
    public void onCompleteOrder(int orderId) {
        // 2表示非商品订单
        getOrderModel().completeOrder(orderId, 2, this);
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }


    @Override
    public void onCancelSuccess(NoDataBean bean) {
        if (isViewAttached()) {
            getBaseView().hideLoading();
            getBaseView().showCancelSuccess(bean);
        }
    }

    @Override
    public void onCancelFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().showCancelFailure(errorMsg);
        }
    }

    @Override
    public void onCompleteOrderSuccess() {
        if (isViewAttached()){
            getBaseView().showCompleteSuccess();
        }
    }

    @Override
    public void onCompleteOrderFailure(String errMsg) {
        if (isViewAttached()){
            getBaseView().showCompleteFailure(errMsg);
        }
    }
}
