package com.yinghai.a24divine_user.module.artical.details.mvp;

import com.yinghai.a24divine_user.bean.ArticleDetailBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/21 11:59
 *         Describe：文章详情契约类
 */

public interface ContractArticleDetails {

    interface IView extends ContractComments.ICommentsView{

        /**
         * 获取文章详情成功
         */
        void showArticleDetailSuccess(ArticleDetailBean.DataBean bean);

        /**
         * 获取文章详情失败
         */
        void showArticleDetailFailure(String errorMsg);

    }

    interface IPresenter{

        /**
         * 获取文章详情
         * @param articleId 文章ID
         */
        void onDetails(int articleId);

    }


    interface IModel{

        /**
         * 获取文章详情
         * @param articleId 文章ID
         */
        void getDetails(int articleId,IDetailsCallback callback);


        interface IDetailsCallback extends IHandleCodeCallback{

            /**
             * 获取文章详情成功
             * @param bean
             */
            void onArticleDetailSuccess(ArticleDetailBean bean);

            /**
             * 获取文章详情失败
             * @param errorMsg
             */
            void onArticleDetailFailure(String errorMsg);

        }

    }


}
