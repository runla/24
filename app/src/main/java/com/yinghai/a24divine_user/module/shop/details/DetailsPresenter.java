package com.yinghai.a24divine_user.module.shop.details;

import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.CollectBean;
import com.yinghai.a24divine_user.bean.CommentBean;
import com.yinghai.a24divine_user.bean.NoDataBean;
import com.yinghai.a24divine_user.bean.ProductDetailBean;
import com.yinghai.a24divine_user.module.artical.details.mvp.CommentModel;
import com.yinghai.a24divine_user.module.artical.details.mvp.ContractComments;
import com.yinghai.a24divine_user.module.collect.mvp.CollectModel;
import com.yinghai.a24divine_user.module.collect.mvp.ContractCollect;
import com.yinghai.a24divine_user.module.shop.details.addtocar.AddToCarModel;
import com.yinghai.a24divine_user.module.shop.details.addtocar.ContractAddToCar;

/**
 * @author Created by：fanson
 *         Created on：2017/10/17 12:00
 *         Describe：商品详情的P层（数据）实现类
 */

public class DetailsPresenter extends MyBasePresenter<ProductDetailModel, ContractDetails.ICommentsView> implements ContractDetails.ICommentsPresenter,
         ContractAddToCar.IAddToCarModel.IAddToCarCallback, ContractComments.ICommentsModel.ICommentCallback, ContractDetails.ICommentsModel.IDetailCallback, ContractCollect.ICollectModel.IAddCollectCallback, ContractCollect.ICollectModel.ICancelCollectCallback {

    private AddToCarModel mAddToCarModel;
    private CollectModel mCollectModel;
    private CommentModel mCommentModel;

    public DetailsPresenter(ContractDetails.ICommentsView view) {
        attachView(view);
    }

    private CommentModel getCommentModel(){
        if (mCommentModel==null){
            mCommentModel = new CommentModel();
        }
        return mCommentModel;
    }

    private CollectModel getCollectModel(){
        if (mCollectModel==null){
            mCollectModel = new CollectModel();
        }
        return mCollectModel;
    }

    @Override
    public void onComments(int productId) {
        getCommentModel().getComments(2,productId,10,this);
    }

    @Override
    public void onDetails(int productId) {
        mBaseModel.getDetails(productId,this);
    }

    @Override
    public void onCollect(int type, int id) {
        getCollectModel().addCollect(type,id,this);
    }

    @Override
    public void onCancelCollect(int type, int id) {
        getCollectModel().cancelCollect(type,id,this);
    }

    @Override
    protected ProductDetailModel createModel() {
        return new ProductDetailModel();
    }

    @Override
    public void onCommentSuccess(CommentBean.DataBean bean) {
        if (isViewAttached()) {
            getBaseView().onGetCommentsSuccess(bean);
        }
    }

    @Override
    public void onCommentFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().onGetCommentsFailure(errorMsg);
        }
    }

    @Override
    public void onAddToCar(int productId,int count) {
        if (mAddToCarModel == null) {
            mAddToCarModel = new AddToCarModel();
        }
        mAddToCarModel.addToCar(productId,count,this);
    }

    @Override
    public void onAddToCarSuccess(NoDataBean bean) {
        if (isViewAttached()) {
            getBaseView().showAddToCarSuccess();
        }
    }

    @Override
    public void onAddToCarFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().showAddToCarFailure(errorMsg);
        }
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    @Override
    public void onIDetailSuccess(ProductDetailBean.DataBean bean) {
        if (isViewAttached()){
            getBaseView().showDetailSuccess(bean);
        }
    }

    @Override
    public void onIDetailFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().showDetailFailure(errorMsg);
        }
    }

    @Override
    public void onAddCollectSuccess(CollectBean bean,int id) {
        if (isViewAttached()) {
            getBaseView().showCollectSuccess(bean);
        }
    }

    @Override
    public void onAddCollectFailure(String errMsg) {
        if (isViewAttached()) {
            getBaseView().showCollectFailure(errMsg);
        }
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mAddToCarModel != null) {
            mAddToCarModel.onDestroy();
            mAddToCarModel = null;
        }
        if (mCollectModel != null) {
            mCollectModel.onDestroy();
            mCollectModel = null;
        }
        if (mCommentModel != null) {
            mCommentModel.onDestroy();
            mCommentModel = null;
        }
    }

    @Override
    public void onCancelCollectSuceess(CollectBean bean, int id) {
        if (isViewAttached()) {
            getBaseView().showCancelCollectSuccess(bean);
        }
    }

    @Override
    public void onCancelCollectFailure(String errMsg) {
        if (isViewAttached()) {
            getBaseView().showCancelCollectFailure(errMsg);
        }
    }
}
