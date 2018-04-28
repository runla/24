package com.yinghai.a24divine_user.module.address.mylist;

import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.AddressListBean;
import com.yinghai.a24divine_user.module.address.delete.ContractDeleteAddress;
import com.yinghai.a24divine_user.module.address.delete.DeleteAddressModel;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/17 11:36
 *         Describe：获取地址列表的P层
 */

public class GetAddressPresenter extends MyBasePresenter<GetAddressModel, ContractMyAddress.IView> implements ContractMyAddress.IPresenter, ContractMyAddress.IModel.IAddressListCallback, ContractDeleteAddress.IModel.IDeleteCallback {

    private int mPageNum = 1;
    public DeleteAddressModel mDeleteAddressModel;

    private DeleteAddressModel getDeleteModel() {
        if (mDeleteAddressModel == null) {
            mDeleteAddressModel = new DeleteAddressModel();
        }
        return mDeleteAddressModel;
    }

    public GetAddressPresenter(ContractMyAddress.IView view) {
        attachView(view);
    }

    @Override
    protected GetAddressModel createModel() {
        return new GetAddressModel();
    }

    @Override
    public void onGetAddress() {
        mBaseModel.getAddressList(mPageNum, this);
    }


    @Override
    public void onGetAddressSuccess(AddressListBean bean) {
        if (isViewAttached()) {
            getBaseView().showGetAddressSuccess(bean);
            mPageNum++;
        }
    }

    @Override
    public void onGetAddressFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().showGetAddressFailure(errorMsg);
        }
    }

    public void resetPage() {
        mPageNum = 1;
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    @Override
    public void onDeleteAddress(int addressId) {
        getDeleteModel().deleteAddress(addressId,this);
    }

    @Override
    public void onDeleteSuccess(AddressListBean bean) {
        if (isViewAttached()) {
            getBaseView().showDeleteSuccess(bean);
        }
    }

    @Override
    public void onDeleteFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().showDeleteFailure(errorMsg);
        }
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDeleteAddressModel!=null){
            mDeleteAddressModel.onDestroy();
            mDeleteAddressModel = null;
        }
    }
}
