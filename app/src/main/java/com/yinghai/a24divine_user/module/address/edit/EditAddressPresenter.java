package com.yinghai.a24divine_user.module.address.edit;

import android.text.TextUtils;

import com.example.fansonlib.base.AppUtils;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.AddressListBean;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/17 13:47
 *         Describe：编辑地址P层
 */

public class EditAddressPresenter extends MyBasePresenter<EditAddressModel,ContractEditAddress.IView> implements ContractEditAddress.IPresenter, ContractEditAddress.IModel.IEditAddressLCallback {


    public EditAddressPresenter(ContractEditAddress.IView view){
        attachView(view);
    }


    @Override
    protected EditAddressModel createModel() {
        return new EditAddressModel();
    }

    @Override
    public void onEditAddress(int addressId, int type, String name, boolean sex,String countryCode, String tel, String street, String details) {
        if (TextUtils.isEmpty(name)) {
            getBaseView().showEditAddressFailure(AppUtils.getString(R.string.address_name_empty));
            return;
        }
        if (TextUtils.isEmpty(countryCode) || TextUtils.isEmpty(tel) ) {
            getBaseView().showEditAddressFailure(AppUtils.getString(R.string.address_tel_empty));
            return;
        }

        if (TextUtils.isEmpty(street)) {
            getBaseView().showEditAddressFailure(AppUtils.getString(R.string.address_empty));
            return;
        }
        if (TextUtils.isEmpty(details)) {
            getBaseView().showEditAddressFailure(AppUtils.getString(R.string.address_details));
            return;
        }
        mBaseModel.editAddress(addressId,type,name,sex,countryCode,tel,street,details,this);
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    @Override
    public void onEditAddressSuccess(AddressListBean bean) {
        if (isViewAttached()){
            getBaseView().showEditAddressSuccess(bean);
        }
    }

    @Override
    public void onEditAddressFailure(String errorMsg) {
        if (isViewAttached()){
            getBaseView().showEditAddressFailure(errorMsg);
        }
    }
}
