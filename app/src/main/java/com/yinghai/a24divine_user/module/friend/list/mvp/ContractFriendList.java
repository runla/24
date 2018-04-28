package com.yinghai.a24divine_user.module.friend.list.mvp;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.FriendListBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/27 14:44
 *         Describe：好友的契约类
 */

public interface ContractFriendList {

    interface IView extends BaseView{

        /**
         * 获取好友列表成功
         */
        void showGetFriendSuccess(FriendListBean.DataBean bean);

        /**
         * 获取好友列表失败
         */
        void showGetFriendFailure(String errorMsg);

    }


    interface IPresenter{
        /**
         * 获取好友列表
         */
        void getFriendList();
    }

    interface IModel{
        /**
         * 获取好友列表
         */
        void onGetFriend(int page, IGetCallback callback);

        interface IGetCallback extends IHandleCodeCallback{

            /**
             * 获取好友列表成功
             */
            void onGetFriendSuccess(FriendListBean.DataBean bean);

            /**
             * 获取好友列表失败
             */
            void onGetFriendFailure(String errorMsg);
        }
    }


}
