package com.yinghai.a24divine_user.module.artical.details.mvp;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.CommentBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * @author Created by：fanson
 *         Created on：2017/10/17 11:25
 *         Describe：详情页评论的契约管理类
 */

public interface ContractComments {

    interface ICommentsView extends BaseView {
        /**
         * 获取评论失敗
         */
        void showCommentsFailure(String msg);

        /**
         * 获取评论成功
         */
        void showCommentsSuccess(CommentBean.DataBean bean);

        /**
         * 发表评论失敗
         */
        void showPublishFailure(String msg);

        /**
         * 发表评论成功
         */
        void showPublishSuccess(CommentBean.DataBean bean);

        /**
         * 删除评论失敗
         */
        void showDeleteFailure(String msg);

        /**
         * 删除评论成功
         */
        void showDeleteSuccess(CommentBean.DataBean bean);

    }

    interface ICommentsPresenter {

        /**
         * 获取评论
         *
         * @param type 评论类型，1为文章2为商品
         * @param id   商品或文章id
         */
        void onComments(int type, int id);

        /**
         * 发表评论
         *
         * @param type 评论类型，1为文章2为商品
         * @param id   商品或文章id
         */
        void onPublishComments(int type, int id,String content);

        /**
         * 删除评论
         *
         * @param commentId 评论ID
         */
        void onDeleteComments(int commentId);

        /**
         * 获取详情（为了给后台作历史记录）
         * @param articleId 文章ID
         */
        void onDetails(int articleId);

    }

    interface ICommentsModel {
        /**
         * 获取评论
         *
         * @param type     评论类型，1为文章2为商品
         * @param id       商品或文章id
         * @param callback
         */
        void getComments(int type, int id, int pageNum, ICommentCallback callback);

        interface ICommentCallback extends IHandleCodeCallback {

            void onCommentSuccess(CommentBean.DataBean bean);

            void onCommentFailure(String errorMsg);
        }

        /**
         * 发表评论
         *
         * @param type     评论类型，1为文章2为商品
         * @param id       商品或文章id
         * @param callback
         */
        void publishComments(int type, int id, String content, IPublishCallback callback);

        interface IPublishCallback extends IHandleCodeCallback {

            void onPublishSuccess(CommentBean.DataBean bean);

            void onPublishFailure(String errorMsg);
        }

        /**
         * 删除评论
         *
         * @param commentId     评论ID
         * @param callback
         */
        void deleteComments(int commentId  , IDeleteCallback callback);

        interface IDeleteCallback extends IHandleCodeCallback {

            void onDeleteSuccess(CommentBean.DataBean bean);

            void onDeleteFailure(String errorMsg);
        }

        /**
         * 获取详情（为了给后台作历史记录）
         *
         * @param articleId    ID
         */
        void details(int articleId );

    }

}
