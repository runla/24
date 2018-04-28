package com.yinghai.a24divine_user.module.divine.index;

import com.yinghai.a24divine_user.bean.BusinessBean;
import com.yinghai.a24divine_user.bean.FollowBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;
import com.yinghai.a24divine_user.module.divine.index.detail.ContractMasterDetail;

/**
 * Created by chenjianrun on 2017/10/30.
 * 描述：占卜师主界面的管理契约类
 */

public interface ContractMasterIndex {

    interface IBusinessView extends ContractMasterDetail.IView{
        /**
         * 获取占卜师的信息成功
         */
        void showGetBusinessSuccess(BusinessBean.DataBean bean);

        /**
         * 获取占卜师的信息失败
         * @param errMsg
         */
        void showBusinessFailure(String errMsg);

        /**
         * 关注成功
         */
        void showFollowSuccess(FollowBean.DataBean bean);

        /**
         * 关注失败
         * @param errMsg
         */
        void showFollowFailuer(String errMsg);

        /**
         * 取消关注成功
         */
        void showCancelFollowSuccess();

        /**
         * 取消关注失败
         * @param errMsg
         */
        void showCancelFollowFailuer(String errMsg);

    }

    interface IMasterIndexPresenter{
        /**
         * 获取占卜师的业务
         */
        void getMasterBusiness(int masterId );

        /**
         * 添加关注
         */
        void addFollow(int masterId);

        /**
         * 取消关注
         */
        void cancelFollow(int masterId);

    }


    interface IMasterIndexModel{

        void getBusiness(int masterId,int pageNum,IGetBusinessCallabck callabck);

        interface IGetBusinessCallabck extends IHandleCodeCallback{
            /**
             * 获取占卜师的信息成功
             * @param bean
             */
            void onGetBusinessSuccess(BusinessBean.DataBean bean);

            /**
             * 获取占卜师的信息失败
             * @param errMsg
             */
            void onGetBusinessFailuer(String errMsg);
        }

        /**
         * 添加关注
         * @param masterId ID
         * @param callabck 回调
         */
        void onAddFollow(int masterId,IFollowCallabck callabck);

        interface IFollowCallabck extends IHandleCodeCallback{
            /**
             * 关注成功
             */
            void onFollowSuccess(FollowBean.DataBean bean);

            /**
             * 关注失败
             * @param errMsg
             */
            void onFollowFailuer(String errMsg);
        }
    }
}
