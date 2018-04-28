package com.yinghai.a24divine_user.module.friend.list.mvp;

import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.FriendListBean;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/27 14:51
 *         Describe：获取好友列表的P层
 */

public class FriendListPresenter extends MyBasePresenter<FriendListModel, ContractFriendList.IView> implements ContractFriendList.IPresenter, ContractFriendList.IModel.IGetCallback {


    private int mPageNum = 1;

    public FriendListPresenter(ContractFriendList.IView view){
        attachView(view);
    }

    @Override
    protected FriendListModel createModel() {
        return new FriendListModel();
    }

    @Override
    public void getFriendList( ) {
        mBaseModel.onGetFriend(mPageNum,this);
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
    public void onGetFriendSuccess(FriendListBean.DataBean bean) {
        if (isViewAttached()){
            getBaseView().showGetFriendSuccess(bean);
            mPageNum++;
        }
    }

    @Override
    public void onGetFriendFailure(String errorMsg) {
        if (isViewAttached()){
            getBaseView().showGetFriendFailure(errorMsg);
        }
    }
}

