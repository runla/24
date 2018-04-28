package com.yinghai.a24divine_user.module.artical.list;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.ArticleBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * Created by：fanson
 * Created on：2017/9/28 16:19
 * Describe：文章的契约管理类
 */

public interface ContractArticle {

    interface IArticleView extends BaseView {
        /**
         * 获取文章失敗
         */
        void showArticleFailure(String msg);

        /**
         * 获取文章成功
         */
        void showArticleSuccess(ArticleBean.DataBean bean);

        /**
         * 收藏文章成功
         *
         * @param id 收藏成功的对应ID
         */
        void showCollectSuccess(int id);

        /**
         * 收藏文章失敗
         */
        void showCollectFailure(String errorMsg);

        /**
         * 取消收藏文章成功
         *
         * @param id 取消收藏成功的对应ID
         */
        void showCancelCollectSuccess(int id);

        /**
         * 取消收藏文章失敗
         */
        void showCancelCollectFailure(String errorMsg);

    }

    interface IArticlePresenter {

        /**
         * 获取文章
         *
         * @param masterId    大师ID
         * @param articleType 文章类型，null为所有
         * @param pageSize    一页数量
         */
        void onGetArticleList(int masterId, String articleType, int pageSize);

        /**
         * 收藏文章
         *
         * @param id        ID
         * @param isCollect 是否已收藏
         */
        void onCollectArticle(int id, boolean isCollect);

    }

    interface IArticleModel {
        /**
         * 获取文章
         *
         * @param masterId    大师ID
         * @param articleType 文章类型，null为所有
         * @param pageNum     页码
         * @param pageSize    一页的数量
         * @param callback    回调
         */
        void getArticleList(int masterId, String articleType, int pageNum, int pageSize, IArticleCallback callback);

        interface IArticleCallback extends IHandleCodeCallback {

            void onArticleSuccess(ArticleBean.DataBean bean);

            void onArticleFailure(String errorMsg);
        }
    }


}
