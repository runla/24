package com.yinghai.a24divine_user.module.shop.details.addtocar;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.NoDataBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/13 14:54
 *         Describe：添加到购物车的契约类
 */

public class ContractAddToCar {

    public interface IAddToCarView extends BaseView{

        /**
         * 添加到购物车成功
         */
        void showAddToCarSuccess();

        /**
         * 添加到购物车失败
         * @param errorMsg 失败语
         */
        void showAddToCarFailure(String errorMsg);

    }


    public interface IAddToCarPresenter{

        /**
         * 添加到购物车
         * @param productId 商品ID
         * @param count 数量
         */
        void onAddToCar(int productId,int count);
    }

    public interface IAddToCarModel {

        /**
         * 添加到购物车
         * @param productId 商品ID
         * @param count 数量
         * @param callback 回调
         */
        void addToCar(int productId,int count,IAddToCarCallback callback);


        interface IAddToCarCallback extends IHandleCodeCallback{

            void onAddToCarSuccess(NoDataBean bean);

            void onAddToCarFailure(String errorMsg);

        }

    }



}
