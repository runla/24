package com.yinghai.a24divine_user.module.friend.index.mvp;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.FriendListBean;
import com.yinghai.a24divine_user.bean.PersonInfoBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/27 14:44
 *         Describe：好友的契约类
 */

public interface ContractFriend {

    interface IView extends BaseView{

        /**
         * 添加好友成功
         */
        void showAddFriendSuccess();

        /**
         * 添加好友失败
         */
        void showAddFriendFailure(String errorMsg);

        /**
         * 删除好友成功
         */
        void showDelFriendSuccess();

        /**
         * 删除好友失败
         */
        void showDelFriendFailure(String errorMsg);

        /**
         * 处理好友验证消息成功
         */
        void showFriendRequestSuccess();

        /**
         * 处理好友验证消息失败
         */
        void showFriendRequestSFailure(String errorMsg);

        /**
         * 显示好友信息成功
         */
        void showFriendInfoSuccess(PersonInfoBean.DataBean bean);

        /**
         * 显示好友信息失败
         */
        void showFriendInfoFailure(String errorMsg);

    }


    interface IPresenter{
        /**
         * 添加好友
         * @param friendId 好友ID
         */
        void addFriend(int friendId);

        /**
         * 删除好友
         */
        void delFriend(int friendId);

        /**
         * 显示好友信息
         */
        void getFriendInfo(int targetId);

        /**
         * 处理好友验证消息
         * @param friendId ID
         * @param type 类型，1为同意，2为拒绝
         */
        void friendRequest(int friendId,int type);

    }

    interface IModel{
        /**
         * 添加好友
         */
        void onAddFriend(int friendId,IAddCallback callback);

        interface IAddCallback extends IHandleCodeCallback{

            /**
             * 添加好友成功
             */
            void onAddFriendSuccess();

            /**
             * 添加好友失败
             */
            void onAddFriendFailure(String errorMsg);
        }


        /**
         * 删除好友
         */
        void onDelFriend(int friendId,IDelCallback callback);

        interface IDelCallback extends IHandleCodeCallback{

            /**
             * 删除好友成功
             */
            void onDelFriendSuccess();

            /**
             * 删除好友失败
             */
            void onDelFriendFailure(String errorMsg);
        }


        /**
         *  显示好友信息
         */
        void onshowFriendInfo(int targetId,IShowCallback callback);

        interface IShowCallback extends IHandleCodeCallback{

            /**
             * 显示好友信息成功
             */
            void onFriendInfoSuccess(PersonInfoBean.DataBean bean);

            /**
             * 显示好友信息失败
             */
            void onFriendInfoFailure(String errorMsg);

        }

        /**
         *  处理好友验证消息
         * @param friendId ID
         * @param type 类型，1为同意，2为拒绝
         */
        void onFriendRequest(int friendId,int type,IRequestCallback callback);

        interface IRequestCallback extends IHandleCodeCallback{

            /**
             * 处理好友验证消息成功
             */
            void onFriendRequestSuccess(FriendListBean.DataBean bean);

            /**
             * 处理好友验证消息失败
             */
            void onFriendRequestFailure(String errorMsg);

        }


    }


}
