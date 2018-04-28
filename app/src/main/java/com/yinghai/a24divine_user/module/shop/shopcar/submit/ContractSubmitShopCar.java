package com.yinghai.a24divine_user.module.shop.shopcar.submit;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.NoDataBean;
import com.yinghai.a24divine_user.bean.ShopCarBean;
import com.yinghai.a24divine_user.bean.SubmitShopCarBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

import java.util.List;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/13 16:28
 *         Describe：购物车的契约类
 */

public interface ContractSubmitShopCar {

    public interface ISubmitView extends BaseView{

        void showSubmitSuccess(SubmitShopCarBean bean);

        void showSubmitFailure(String errorMsg);

        /**
         * 删除购物车成功
         * @param carId
         * @param bean
         */
        void showDeleteSuccess(int carId, NoDataBean bean);

        /**
         * 删除购物车失败
         * @param errorMsg
         */
        void showDeleteFailure(String errorMsg);


        /**
         * 修改购物车成功
         * @param bean
         */
        void showEditSuccess(SubmitShopCarBean bean);

        /**
         * 修改购物车失败
         * @param errorMsg
         */
        void showEditFailure(String errorMsg);

    }


    public interface ISubmitPresenter  {

        /**
         * P层提交购物车数据
         * @param carId 购物车ID
         * @param addressId 地址ID
         */
        void onSubmitShopCar(List<ShopCarBean.DataBean> carId,int addressId);

        /**
         * P层删除购物车数据
         */
        void onDeleteShopCar(int carId);

        /**
         * P层修改购物车数据
         */
        void onEditShopCar();


    }


    public interface ISubmitModel  {

        /**
         * M层提交购物车数据
         * @param carIds 购物车记录id
         * @param amounts 对应的商品数量
         * @param addressId 地址ID
         * @param callback 回调
         */
        void submitShopCar(String carIds, String amounts,int addressId, ISubmitCallback callback);

        interface ISubmitCallback extends IHandleCodeCallback{

            void onSubmitSuccess(SubmitShopCarBean bean);

            void onSubmitFailure(String errorMsg);

        }

        /**
         * M层删除购物车数据
         */
        void deleteShopCar(int carId,IDeleteCallback callback);

        interface IDeleteCallback extends IHandleCodeCallback{

            /**
             * 删除购物车商品成功
             * @param carId 购物车id
             * @param bean
             */
            void onDeleteSuccess(int carId, NoDataBean bean);

            /**
             * 删除购物车商品失败
             * @param errorMsg
             */
            void onDeleteFailure(String errorMsg);
        }

        /**
         * M层修改购物车数据
         */
        void edtiShopCar(int carId,IEditCallback callback);

        interface IEditCallback extends IHandleCodeCallback{

            void onEditSuccess(SubmitShopCarBean bean);

            void onEditFailure(String errorMsg);
        }

    }

}
