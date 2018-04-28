package com.yinghai.a24divine_user.module.friend.index.mvp;

import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.FriendListBean;
import com.yinghai.a24divine_user.bean.PersonInfoBean;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/27 14:51
 *         Describe：添加/删除好友的P层
 */

public class FriendManagerPresenter extends MyBasePresenter<FriendManagerModel,ContractFriend.IView> implements ContractFriend.IPresenter, ContractFriend.IModel.IAddCallback, ContractFriend.IModel.IShowCallback, ContractFriend.IModel.IDelCallback, ContractFriend.IModel.IRequestCallback {


    public FriendManagerPresenter(ContractFriend.IView view){
        attachView(view);
    }

    @Override
    public void addFriend(int friendId) {
        mBaseModel.onAddFriend(friendId,this);
    }

    @Override
    public void delFriend(int friendId) {
        mBaseModel.onDelFriend(friendId,this);
    }

    @Override
    public void getFriendInfo(int targetId) {
        mBaseModel.onshowFriendInfo(targetId,this);
    }

    @Override
    public void friendRequest(int friendId, int type) {
        mBaseModel.onFriendRequest(friendId,type,this);
    }

    @Override
    protected FriendManagerModel createModel() {
        return new FriendManagerModel();
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    @Override
    public void onAddFriendSuccess() {
        if (isViewAttached()){
            getBaseView().showAddFriendSuccess();
        }
    }

    @Override
    public void onAddFriendFailure(String errorMsg) {
        if (isViewAttached()){
            getBaseView().showAddFriendFailure(errorMsg);
        }
    }

    @Override
    public void onFriendInfoSuccess(PersonInfoBean.DataBean bean) {
        if (isViewAttached()){
            getBaseView().showFriendInfoSuccess(bean);
        }
    }

    @Override
    public void onFriendInfoFailure(String errorMsg) {
        if (isViewAttached()){
            getBaseView().showFriendInfoFailure(errorMsg);
        }
    }

    @Override
    public void onDelFriendSuccess() {
        if (isViewAttached()){
            getBaseView().showDelFriendSuccess();
        }
    }

    @Override
    public void onDelFriendFailure(String errorMsg) {
        if (isViewAttached()){
            getBaseView().showDelFriendFailure(errorMsg);
        }
    }

    @Override
    public void onFriendRequestSuccess(FriendListBean.DataBean bean) {
        if (isViewAttached()){
            getBaseView().showFriendRequestSuccess();
        }
    }

    @Override
    public void onFriendRequestFailure(String errorMsg) {
        if (isViewAttached()){
            getBaseView().showFriendRequestSFailure(errorMsg);
        }
    }
}
