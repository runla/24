package com.yinghai.a24divine_user.module.collect.mvp;

import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.CollectBean;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/10 14:01
 *         Describe：获取我的收藏的P实现
 */

public class CollectPresenter extends MyBasePresenter<CollectModel, ContractCollect.ICollectView> implements ContractCollect.ICollectPresenter, ContractCollect.ICollectModel.ICollectCallback, ContractCollect.ICollectModel.ICancelCollectCallback, ContractCollect.ICollectModel.IAddCollectCallback {

    private int mPageNum = 1;

    public CollectPresenter(ContractCollect.ICollectView view) {
        attachView(view);
    }


    @Override
    public void onGetCollect() {
        mBaseModel.getCollect(mPageNum, this);
    }

    @Override
    public void onCollect(int type, int id,boolean isCollect) {
        if (isCollect) {
            mBaseModel.addCollect(type,id,this);
        }else{
            mBaseModel.cancelCollect(type,id,this);
        }

    }

    @Override
    public void onCancelCollect(int type, int id) {
        mBaseModel.cancelCollect(type,id,this);
    }

    @Override
    protected CollectModel createModel() {
        return new CollectModel();
    }

    @Override
    public void onCollectSuccess(CollectBean bean) {
        if (isViewAttached()) {
            getBaseView().getCollectSuccess(bean);
            mPageNum++;
        }
    }

    @Override
    public void onCollectFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().getCollectFailure(errorMsg);
        }
    }

    /**
     * 上拉刷新时，需要重置页码
     */
    public void resetPage() {
        mPageNum = 1;
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    @Override
    public void onCancelCollectSuceess(CollectBean bean,int id) {
        if (isViewAttached()) {
            getBaseView().onCancelCollectSuceess(id);
        }
    }

    @Override
    public void onCancelCollectFailure(String errMsg) {
        if (isViewAttached()) {
            getBaseView().onCancelCollectFailure(errMsg);
        }
    }

    @Override
    public void onAddCollectSuccess(CollectBean bean,int id) {
        if (isViewAttached()) {
            getBaseView().onAddCollectSuccess(id);
        }
    }

    @Override
    public void onAddCollectFailure(String errMsg) {
        if (isViewAttached()) {
            getBaseView().onAddCollectFailure(errMsg);
        }
    }
}
