package com.yinghai.a24divine_user.module.shop.shoplist;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.ProductBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/26 10:42
 *         Describe：商城的契约类
 */

public interface ContractShop {

    interface IGetAllProductView extends BaseView {
        /**
         * 获取所有商品失敗
         */
        void showAllProductFailure(String msg);

        /**
         * 获取所有商品成功
         */
        void showAllProductSuccess(ProductBean bean);

    }

    interface IGetAllProductPresenter {

        /**
         * 获取所有商品
         * @param productName 商品名（模糊搜索）
         */
        void onGetAllProduct(String productName);

    }

    interface IGetAllProductModel  {
        /**
         * 获取所有商品
         * @param pageNum 页码
         * @param name 商品名（模糊搜索）
         * @param callback 回调
         */
        void getAllProduct(int pageNum,String name,IGetAllProductCallback callback);

        interface IGetAllProductCallback extends IHandleCodeCallback{
            void onGetAllProductSuccess(ProductBean bean);
            void onGetAllProductFailure(String errorMsg);
        }

    }


}
