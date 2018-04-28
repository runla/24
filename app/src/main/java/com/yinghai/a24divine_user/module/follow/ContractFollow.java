package com.yinghai.a24divine_user.module.follow;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.FollowBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/25 16:29
 *         Describe：获取我的关注的契约管理类
 */

public interface ContractFollow {

    interface IFollowView extends BaseView {

        /**
         * 获取我的关注成功
         */
        void getFollowListSuccess(FollowBean bean);

        /**
         * 获取我的关注失败
         */
        void getFollowListFailure(String errorMsg);

        /**
         * 取消关注成功
         */
        void cancelFollowSuccess();

        /**
         * 取消关注失败
         * @param errMsg
         */
        void cancelFollowFailure(String errMsg);



    }

    interface IFollowPresenter{

        /**
         * 获取我的关注(逻辑层)
         */
        void getFollowList();

        /**
         * 取消关注
         * @param masterId
         */
        void cancelFollow(int masterId);
    }

    interface IFollowModel{
        /**
         * 取消关注
         * @param masterId
         */
        void onCancelFollow(int masterId,ICancelFollowCallback cancelFollowCallback);
        interface ICancelFollowCallback extends IHandleCodeCallback{
            void onCancelFollowSuccess();
            void onCancelFollowFailure(String errMsg);
        }
        /**
         * 获取我的关注(数据层)
         */
        void onGetFollowList(int page,int pageSize,IFollowCallback callback);
        interface IFollowCallback extends IHandleCodeCallback{
            void onGetFollowListSuccess(FollowBean bean);
            void onGetFollowListFailure(String errorMsg);
        }
    }


}
