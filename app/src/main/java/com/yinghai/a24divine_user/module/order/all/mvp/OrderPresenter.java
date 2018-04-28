package com.yinghai.a24divine_user.module.order.all.mvp;

import android.content.Context;

import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.OrderBean;
import com.yinghai.a24divine_user.bean.ProductOrderListBean;

/**
 * Created by：fanson
 * Created on：2017/9/29 12:04
 * Describe：历史记录的P层（逻辑）实现类
 */

public class OrderPresenter extends MyBasePresenter<OrderModel, ContractOrder.IOrderView> implements ContractOrder.IOrderPresenter,
        ContractOrder.IOrderModel.IOrderCallback, ContractOrder.IOrderModel.IProductCallback, ContractOrder.IOrderModel.ICompleteOrderCallback {

    private Context mContext;
    private int mPageNum = 1;
    private static final int TYPE_DIVINE = 1;

    public OrderPresenter(ContractOrder.IOrderView view, Context context) {
        attachView(view);
        mContext = context;
    }

    @Override
    protected OrderModel createModel() {
        return new OrderModel();
    }

    @Override
    public void onOrderOrder(String status, int orderType) {
        if (orderType == TYPE_DIVINE) {
            mBaseModel.getOrder(status, orderType, mPageNum, this);
        } else {
            mBaseModel.getProductOrder(status, mPageNum, this);
        }
    }

    @Override
    public void completeOrder(int orderId, int type) {
        mBaseModel.completeOrder(orderId, type, this);
    }

    @Override
    public void onOrderSuccess(OrderBean bean) {
        if (isViewAttached()) {
            getBaseView().showOrderSuccess(bean);
            mPageNum++;
        }
    }

    @Override
    public void onOrderFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().showOrderFailure(errorMsg);
        }
    }

    /**
     * 重置页码，下拉刷新使用
     */
    public void resetPageNum() {
        mPageNum = 1;
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }


    @Override
    public void onProductListSuccess(ProductOrderListBean bean) {
        if (isViewAttached()) {
            getBaseView().showProductListSuccess(bean);
        }
    }

    @Override
    public void onProductListFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().showProductListFailure(errorMsg);
        }
    }

    @Override
    public void onCompleteOrderSuccess() {
        if (isViewAttached()) {
            getBaseView().completeOrderSuccess();
        }
    }

    @Override
    public void onCompleteOrderFailure(String errMsg) {
        if (isViewAttached()) {
            getBaseView().completeOrderFailure(errMsg);
        }
    }
}
