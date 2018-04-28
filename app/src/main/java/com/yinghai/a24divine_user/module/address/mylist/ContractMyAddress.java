package com.yinghai.a24divine_user.module.address.mylist;

import com.yinghai.a24divine_user.bean.AddressListBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;
import com.yinghai.a24divine_user.module.address.delete.ContractDeleteAddress;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/17 11:30
 *         Describe：我的地址列表的契约类
 */

public interface ContractMyAddress {

    interface IView extends ContractDeleteAddress.IView{

        /**
         * 获取我的地址列表成功
         */
        void showGetAddressSuccess(AddressListBean bean);

        /**
         * 获取我的地址列表失败
         */
        void showGetAddressFailure(String errorMsg);
    }


    interface IPresenter extends ContractDeleteAddress.IPresenter{

        /**
         * 获取地址列表
         */
        void onGetAddress();
    }


    interface IModel {

        void getAddressList(int pageNum,IAddressListCallback callback);


        interface IAddressListCallback extends IHandleCodeCallback{

            void onGetAddressSuccess(AddressListBean bean);

            void onGetAddressFailure(String errorMsg);

        }

    }

}
