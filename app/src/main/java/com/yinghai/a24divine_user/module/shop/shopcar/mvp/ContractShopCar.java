package com.yinghai.a24divine_user.module.shop.shopcar.mvp;

import com.yinghai.a24divine_user.bean.ShopCarBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;
import com.yinghai.a24divine_user.module.shop.shopcar.submit.ContractSubmitShopCar;

/**
* @author Created by：fanson
* Created on：2017/11/13 15:35
* Description：购物车的契约类
*/

public interface ContractShopCar {

    interface IShopCarView extends ContractSubmitShopCar.ISubmitView{

        /**
         * 获取购物车记录成功
         * @param bean 数据
         */
        void showShopCarSuccess(ShopCarBean bean);

        /**
         * 获取购物车记录失败
         * @param errorMsg 提示
         */
        void showShopCarFailure(String errorMsg);

        /**
         * 添加到购物车成功
         */
        void showAddCarSuccess();

        /**
         * 添加到购物车失败
         * @param errorMsg 提示
         */
        void showAddCarFailure(String errorMsg);

    }

    interface IShopCarPresenter  extends ContractSubmitShopCar.ISubmitPresenter{

        /**
         * 获取购物车数据
         */
        void onShopCar();

        /**
         * 添加购物车
         * @param productId 商品ID
         */
        void addCar(int productId);

    }

    interface IShopCarModel  {

        /**
         * 获取购物车数据
         * @param page
         * @param callback
         */
        void getShopCarSuccess(int page,IShopCarCallback callback);

        interface IShopCarCallback extends IHandleCodeCallback{

            void onShopCarSuccess(ShopCarBean bean);

            void onShopCarFailure(String errorMsg);

        }

    }

}
