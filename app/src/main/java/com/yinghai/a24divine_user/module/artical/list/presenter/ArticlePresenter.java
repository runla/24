package com.yinghai.a24divine_user.module.artical.list.presenter;

import android.content.Context;

import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.ArticleBean;
import com.yinghai.a24divine_user.bean.CollectBean;
import com.yinghai.a24divine_user.module.artical.list.ContractArticle;
import com.yinghai.a24divine_user.module.artical.list.model.GetArticleModel;
import com.yinghai.a24divine_user.module.collect.mvp.CollectModel;
import com.yinghai.a24divine_user.module.collect.mvp.ContractCollect;

/**
 * Created by：fanson
 * Created on：2017/9/28 16:29
 * Describe：获取文章的P层（逻辑）实现类
 */

public class ArticlePresenter extends MyBasePresenter<GetArticleModel, ContractArticle.IArticleView> implements ContractArticle.IArticlePresenter,
        ContractArticle.IArticleModel.IArticleCallback, ContractCollect.ICollectModel.ICancelCollectCallback, ContractCollect.ICollectModel.IAddCollectCallback {

    private int mPageNum = 1;
    private CollectModel mCollectModel;

    public ArticlePresenter(ContractArticle.IArticleView view, Context context) {
        attachView(view);
    }

    private CollectModel getCollectModel() {
        if (mCollectModel == null) {
            mCollectModel = new CollectModel();
        }
        return mCollectModel;
    }

    @Override
    protected GetArticleModel createModel() {
        return new GetArticleModel();
    }

    @Override
    public void onGetArticleList(int masterId, String articleType, int pageSize) {
        mBaseModel.getArticleList(masterId, articleType, mPageNum, pageSize, this);
    }

    @Override
    public void onCollectArticle(int id, boolean isCollect) {
        if (isCollect) {
            getCollectModel().cancelCollect(2, id, this);
        } else {
            getCollectModel().addCollect(2, id, this);
        }
    }

    @Override
    public void onArticleSuccess(ArticleBean.DataBean bean) {
        if (isViewAttached()) {
            getBaseView().showArticleSuccess(bean);
            mPageNum++;
        }
    }

    @Override
    public void onArticleFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().showArticleFailure(errorMsg);
        }
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    public void resetPageNum() {
        mPageNum = 1;
    }


    @Override
    public void onCancelCollectSuceess(CollectBean bean, int id) {
        if (isViewAttached()) {
            getBaseView().showCancelCollectSuccess(id);
        }
    }

    @Override
    public void onCancelCollectFailure(String errMsg) {
        if (isViewAttached()) {
            getBaseView().showCancelCollectFailure(errMsg);
        }
    }

    @Override
    public void onAddCollectSuccess(CollectBean bean, int id) {
        if (isViewAttached()) {
            getBaseView().showCollectSuccess(id);
        }
    }

    @Override
    public void onAddCollectFailure(String errMsg) {
        if (isViewAttached()) {
            getBaseView().showArticleFailure(errMsg);
        }
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mCollectModel != null) {
            mCollectModel.onDestroy();
            mCollectModel = null;
        }
    }
}
