package com.yinghai.a24divine_user.module.address.edit;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.AddressListBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/17 11:30
 *         Describe：编辑地址列表的契约类
 */

public interface ContractEditAddress {

    interface IView extends BaseView {

        /**
         * 编辑我的地址列表成功
         */
        void showEditAddressSuccess(AddressListBean bean);

        /**
         * 编辑我的地址列表失败
         */
        void showEditAddressFailure(String errorMsg);
    }


    interface IPresenter {

        /**
         * 编辑地址
         *
         * @param addressId   地址id（可选）
         * @param type        1：为修改默认地址 ； 2：为修改地址信息
         * @param name        姓名
         * @param sex         性别
         * @param countryCode 区号
         * @param tel         号码
         * @param street      街道
         * @param details     详细
         */
        void onEditAddress(int addressId, int type, String name, boolean sex,String countryCode, String tel, String street, String details);
    }


    interface IModel {

        /**
         * 编辑地址
         *
         * @param addressId   地址id（可选）
         * @param type        1：为修改默认地址 ； 2：为修改地址信息
         * @param name        姓名
         * @param sex         性别
         * @param countryCode 区号
         * @param tel         号码
         * @param street      街道
         * @param details     详细
         */
        void editAddress(int addressId, int type, String name,boolean sex ,String countryCode, String tel, String street, String details, IEditAddressLCallback callback);


        interface IEditAddressLCallback extends IHandleCodeCallback {

            void onEditAddressSuccess(AddressListBean bean);

            void onEditAddressFailure(String errorMsg);

        }

    }

}
