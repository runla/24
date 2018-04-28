package com.yinghai.a24divine_user.module.follow.presenter;

import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.FollowBean;
import com.yinghai.a24divine_user.module.follow.ContractFollow;
import com.yinghai.a24divine_user.module.follow.model.FollowModel;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/25 16:32
 *         Describe：我关注的P层实现类
 */

public class FollowPresenter extends MyBasePresenter<FollowModel,ContractFollow.IFollowView> implements ContractFollow.IFollowPresenter, ContractFollow.IFollowModel.IFollowCallback, ContractFollow.IFollowModel.ICancelFollowCallback {
    private int mPage = 1;
    private int mPageSize = 10;

    public FollowPresenter(ContractFollow.IFollowView view){
        attachView(view);
    }

    @Override
    protected FollowModel createModel() {
        return new FollowModel();
    }

    @Override
    public void getFollowList() {
        mBaseModel.onGetFollowList(mPage, mPageSize,this);
    }

    @Override
    public void cancelFollow(int masterId) {
        mBaseModel.onCancelFollow(masterId,this);
    }

    @Override
    public void onGetFollowListSuccess(FollowBean bean) {
        if (isViewAttached()){
            getBaseView().getFollowListSuccess(bean);
            mPage++;
        }
    }

    @Override
    public void onGetFollowListFailure(String errorMsg) {
        if (isViewAttached()){
            getBaseView().getFollowListFailure(errorMsg);
        }
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    @Override
    public void onCancelFollowSuccess() {
        if (isViewAttached()) {
            getBaseView().cancelFollowSuccess();
        }
    }

    @Override
    public void onCancelFollowFailure(String errMsg) {
        if (isViewAttached()) {
            getBaseView().cancelFollowFailure(errMsg);
        }
    }

    /**
     * 上拉刷新，重置加载页码
     */
    public void resetPage(){
        mPage=1;
    }
}
