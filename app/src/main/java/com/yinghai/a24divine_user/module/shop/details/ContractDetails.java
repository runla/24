package com.yinghai.a24divine_user.module.shop.details;

import com.yinghai.a24divine_user.bean.CollectBean;
import com.yinghai.a24divine_user.bean.CommentBean;
import com.yinghai.a24divine_user.bean.ProductDetailBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;
import com.yinghai.a24divine_user.module.shop.details.addtocar.ContractAddToCar;

/**
 * @author Created by：fanson
 *         Created on：2017/10/17 11:25
 *         Describe：商品详情的契约管理类
 */

public interface ContractDetails {

    interface ICommentsView extends ContractAddToCar.IAddToCarView {
        /**
         * 获取评论失敗
         */
        void onGetCommentsFailure(String msg);

        /**
         * 获取评论成功
         */
        void onGetCommentsSuccess(CommentBean.DataBean bean);

        /**
         * 获取商品详情失败
         */
        void showDetailFailure(String msg);

        /**
         * 获取商品详情成功
         */
        void showDetailSuccess(ProductDetailBean.DataBean bean);

        /**
         * 收藏商品失败
         */
        void showCollectFailure(String msg);

        /**
         * 收藏商品成功
         */
        void showCollectSuccess(CollectBean bean);

        /**
         * 取消收藏成功
         */
        void showCancelCollectSuccess(CollectBean bean);

        /**
         * 取消收藏失败
         * @param msg
         */
        void showCancelCollectFailure(String msg);
    }

    interface ICommentsPresenter extends ContractAddToCar.IAddToCarPresenter{

        /**
         * 获取评论
         */
        void onComments(int productId);

        /**
         * 获取商品详情
         */
        void onDetails(int productId);

        /**
         * 收藏商品
         * @param type  收藏类型，1大师 2文章 3商品
         * @param id id
         */
        void onCollect(int type, int id);

        /**
         * 取消收藏
         * @param type
         * @param id
         */
        void onCancelCollect(int type,int id);
    }

    interface ICommentsModel{

        /**
         * 获取商品详情
         *
         * @param callback
         */
        void getDetails(int productId,IDetailCallback callback);

        interface IDetailCallback extends IHandleCodeCallback{

            void onIDetailSuccess(ProductDetailBean.DataBean bean);

            void onIDetailFailure(String errorMsg);
        }
    }

}
