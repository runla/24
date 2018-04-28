package com.yinghai.a24divine_user.module.main;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.ArticleBean;
import com.yinghai.a24divine_user.bean.CollectBean;
import com.yinghai.a24divine_user.bean.LuckBean;
import com.yinghai.a24divine_user.bean.MasterBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * Created by：fanson
 * Created on：2017/10/24 13:22
 * Describe：主界面的契约类
 */

public interface ContractMain {

    interface IMainView extends BaseView {
        /**
         * 获取名师占卜列表成功
         * @param bean
         */
        void onGetDivineSuccess(MasterBean.DataBean bean);

        /**
         * 获取名师占卜失败
         * @param errMsg
         */
        void onGetDivineFailure(String errMsg);

        /**
         * 获取文章失敗
         */
        void onGetArticleFailure(String msg);

        /**
         * 获取文章成功
         */
        void onGetArticleSuccess(ArticleBean.DataBean bean);

        /**
         * 开启运势成功
         */
        void onGetLuckSuccess(LuckBean luckBean);

        /**
         * 开启运势失败
         * @param errMsg
         */
        void onGetLuckFailure(String errMsg);

        /**
         * 收藏大师/文章成功
         * @param id 大师ID
         */
        void showCollectSuccess(CollectBean.DataBean bean,int id);

        /**
         * 收藏大师/文章失败
         * @param errMsg 失败语
         */
        void showCollectFailure(String errMsg);


        /**
         * 取消收藏大师/文章成功
         * @param id 大师ID
         */
        void showCancelCollectSuccess(CollectBean.DataBean bean,int id);

        /**
         * 取消收藏大师/文章失败
         * @param errMsg 失败语
         */
        void showCancelCollectFailure(String errMsg);


    }

    interface IMainPresenter {
        /**
         * 获取文章
         */
        void getArticle();

        /**
         * 获取名师占卜
         */
        void getMasterDivine();

        /**
         * 开启运势
         */
        void openLuck();

        /**
         * 更换运势
         */
        void changeLuck(int constellation);

        /**
         * 收藏
         * @param type  收藏类型，1大师 2文章 3商品
         * @param id    type=1时为大师id，type=2时为文章id，type=3为商品id
         */
        void onCollect(int type,int id,boolean isCollect);

    }

    interface IMainModel {
        /**
         * 开启运势
         * @param luckCallback
         */
        void openLuck(ILuckCallback luckCallback);

        /**
         * 更改运势
         * @param constellation
         * @param luckCallback
         */
        void changeLuck(int constellation,ILuckCallback luckCallback);

        interface ILuckCallback extends IHandleCodeCallback{
            /**
             * 获取运势成功
             * @param bean
             */
            void onGetLuckSuccess(LuckBean bean);

            /**
             * 获取运势失败
             * @param errMsg
             */
            void onGetLuckFailure(String errMsg);
        }

    }

}
