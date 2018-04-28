package com.yinghai.a24divine_user.module.collect.mvp;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.CollectBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/10 13:50
 *         Describe：我的收藏的契约类
 */

public interface ContractCollect {

    interface ICollectView extends BaseView {

        /**
         * 成功获取收藏
         *
         * @param bean
         */
        void getCollectSuccess(CollectBean bean);

        /**
         * 获取收藏失败
         */
        void getCollectFailure(String errorMsg);

        /**
         * 取消收藏成功
         * @param id 取消成功的对应ID
         */
        void onCancelCollectSuceess(int id);

        /**
         * 取消收藏
         *
         * @param errMsg
         */
        void onCancelCollectFailure(String errMsg);

        /**
         * 添加收藏成功
         * @param id 收藏成功的对应ID
         */
        void onAddCollectSuccess(int id);

        /**
         * 添加收藏失败
         *
         * @param errMsg
         */
        void onAddCollectFailure(String errMsg);
    }

    interface ICollectPresenter {

        /**
         * 获取我的收藏
         */
        void onGetCollect();

        /**
         * 添加收藏、取消收藏
         *
         * @param type      收藏类型，1大师 2文章 3商品
         * @param id        type=1时为大师id，type=2时为文章id，type=3为商品id   （取消收藏时，id 为 coleectionId）
         * @param isCollect true：收藏 false：取消收藏
         */
        void onCollect(int type, int id, boolean isCollect);

        /**
         * 取消收藏
         *
         * @param type 收藏类型，1大师 2文章 3商品
         * @param id   type=1时为大师id，type=2时为文章id，type=3为商品id   （取消收藏时，id 为 coleectionId）
         */
        void onCancelCollect(int type, int id);
    }


    interface ICollectModel {

        void getCollect(int pageNum, ICollectCallback callback);

        interface ICollectCallback extends IHandleCodeCallback {

            void onCollectSuccess(CollectBean bean);

            void onCollectFailure(String errorMsg);
        }

        /**
         * 添加收藏
         *
         * @param type 收藏类型，1大师 2文章 3商品
         * @param id   type=1时为大师id，type=2时为文章id，type=3为商品id
         */
        void addCollect(int type, int id, IAddCollectCallback callback);

        interface IAddCollectCallback extends IHandleCodeCallback {

            void onAddCollectSuccess(CollectBean bean,int id);

            /**
             * 添加收藏失败
             * @param errMsg 提示语
             */
            void onAddCollectFailure(String errMsg);
        }


        /**
         * 取消收藏
         *
         * @param type  收藏类型，1大师 2文章 3商品
         * @param keyId type=1时为大师id，type=2时为文章id，type=3为商品id
         */
        void cancelCollect(int type, int keyId, ICancelCollectCallback callback);

        interface ICancelCollectCallback extends IHandleCodeCallback {

            /**
             * 取消收藏成功
             * @param bean
             * @param id ID
             */
            void onCancelCollectSuceess(CollectBean bean,int id);

            /**
             * 取消收藏失败
             * @param errMsg 提示语
             */
            void onCancelCollectFailure(String errMsg);
        }
    }
}
