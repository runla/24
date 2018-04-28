package com.yinghai.a24divine_user.module.divine.index.detail;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.MasterDetailBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
* @author Created by：fanson
* Created on：2017/11/21 13:40
* Description：占卜师详情
*/

public interface ContractMasterDetail {

    interface IView extends BaseView{
        /**
         * 获取占卜师详情成功
         */
        void showMasterDetailSuccess(MasterDetailBean.DataBean bean);

        /**
         * 获取占卜师详情失败
         * @param errMsg
         */
        void showMasterDetailFailuer(String errMsg);

    }

    interface IPresenter{
        /**
         * 获取占卜师详情
         */
        void getMasterDetail(int masterId);

    }


    interface IModel{

        /**
         * 获取占卜师详情
         */
        void onMasterDetail(int masterId, IMasterDetailCallabck callabck);

        interface IMasterDetailCallabck extends IHandleCodeCallback{
            /**
             * 获取占卜师详情成功
             * @param bean
             */
            void onMasterDetailSuccess(MasterDetailBean.DataBean bean);

            /**
             * 获取占卜师详情失败
             * @param errMsg
             */
            void onMasterDetailFailuer(String errMsg);
        }
    }
}
