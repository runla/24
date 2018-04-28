package com.yinghai.a24divine_user.module.address.delete;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.AddressListBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/17 16:20
 *         Describe：删除地址的契约类
 */

public interface ContractDeleteAddress {

    interface IView extends BaseView {

        /**
         * 删除地址成功
         */
        void showDeleteSuccess(AddressListBean bean);

        /**
         * 删除地址失败
         */
        void showDeleteFailure(String errorMsg);

    }

    interface IPresenter {

        /**
         * 删除地址
         *
         * @param addressId 地址ID
         */
        void onDeleteAddress(int addressId);

    }


    interface IModel {

        /**
         * 删除地址
         *
         * @param addressId 地址ID
         * @param callback
         */
        void deleteAddress(int addressId, IDeleteCallback callback);


        interface IDeleteCallback extends IHandleCodeCallback {

            /**
             * 删除地址成功
             */
            void onDeleteSuccess(AddressListBean bean);

            /**
             * 删除地址失败
             */
            void onDeleteFailure(String errorMsg);
        }

    }

}
