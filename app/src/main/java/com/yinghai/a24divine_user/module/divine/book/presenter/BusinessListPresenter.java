package com.yinghai.a24divine_user.module.divine.book.presenter;

import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.BusinessBean;
import com.yinghai.a24divine_user.module.divine.book.ContractBusinessList;
import com.yinghai.a24divine_user.module.divine.book.model.BusinessListModel;

/**
* @author Created by：fanson
* Created on：2017/12/15 13:35
* Description：获取占卜师业务列表P层
*/
public class BusinessListPresenter extends MyBasePresenter<BusinessListModel,ContractBusinessList.IView> implements ContractBusinessList.IPresenter, ContractBusinessList.IModel.IBusinessCallback {

    private int mPage = 1;

    public BusinessListPresenter(ContractBusinessList.IView view ) {
        attachView(view);
    }

    @Override
    protected BusinessListModel createModel() {
        return new BusinessListModel();
    }

    public void resetPage(){
        mPage = 1;
    }

    @Override
    public void getBusinessList(int masterId) {
        mBaseModel.onBusinessList(masterId,mPage,this);
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    @Override
    public void onBusinessListSuccess(BusinessBean.DataBean bean) {
        if (isViewAttached()){
            getBaseView().showBusinessListSuccess(bean);
        }
    }

    @Override
    public void onBusinessListFailure(String errMsg) {
        if (isViewAttached()){
            getBaseView().showBusinessListFailure(errMsg);
        }
    }
}
