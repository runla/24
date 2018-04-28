package com.yinghai.a24divine_user.module.artical.details.mvp;

import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.ArticleDetailBean;
import com.yinghai.a24divine_user.bean.CommentBean;

/**
 * Created by：fanson
 * Created on：2017/10/17 12:00
 * Describe：获取文章评论的P层（数据）实现类
 */

public class CommentPresenter extends MyBasePresenter<ArticleDetailModel, ContractArticleDetails.IView> implements ContractComments.ICommentsPresenter,
        ContractComments.ICommentsModel.ICommentCallback, ContractComments.ICommentsModel.IPublishCallback, ContractComments.ICommentsModel.IDeleteCallback, ContractArticleDetails.IModel.IDetailsCallback {

    private int mPageNum = 1;
    private CommentModel mCommentModel;

    public CommentPresenter(ContractArticleDetails.IView view) {
        attachView(view);
    }

    @Override
    protected ArticleDetailModel createModel() {
        return new ArticleDetailModel();
    }

    private CommentModel getCommentModel(){
        if (mCommentModel == null){
            mCommentModel = new CommentModel();
        }
        return mCommentModel;
    }

    @Override
    public void onCommentSuccess(CommentBean.DataBean bean) {
        if (isViewAttached()) {
            getBaseView().showCommentsSuccess(bean);
        }
    }

    @Override
    public void onCommentFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().showCommentsFailure(errorMsg);
            mPageNum++;
        }
    }

    @Override
    public void onComments(int type, int id) {
        getCommentModel().getComments(type, id, mPageNum, this);
    }

    @Override
    public void onPublishComments(int type, int id, String content) {
        getCommentModel().publishComments(type,id,content,this);
    }

    @Override
    public void onDeleteComments(int commentId) {
        getCommentModel().deleteComments(commentId,this);
    }

    @Override
    public void onDetails(int articleId) {
        mBaseModel.getDetails(articleId,this);
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    @Override
    public void onPublishSuccess(CommentBean.DataBean bean) {
        if (isViewAttached()){
            getBaseView().showPublishSuccess(bean);
        }
    }

    @Override
    public void onPublishFailure(String errorMsg) {
        if (isViewAttached()){
            getBaseView().showPublishFailure(errorMsg);
        }
    }

    @Override
    public void onDeleteSuccess(CommentBean.DataBean bean) {
        if (isViewAttached()){
            getBaseView().showDeleteSuccess(bean);
        }
    }

    @Override
    public void onDeleteFailure(String errorMsg) {
        if (isViewAttached()){
            getBaseView().showPublishFailure(errorMsg);
        }
    }

    @Override
    public void onArticleDetailSuccess(ArticleDetailBean bean) {
        if (isViewAttached()){
            getBaseView().showArticleDetailSuccess(bean.getData());
        }
    }

    @Override
    public void onArticleDetailFailure(String errorMsg) {
        if (isViewAttached()){
            getBaseView().showPublishFailure(errorMsg);
        }
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mCommentModel!=null){
            mCommentModel.onDestroy();
            mCommentModel = null;
        }
    }
}
