package com.yinghai.a24divine_user.module.divine.book;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.BusinessBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * @author Created by：fanson
 *         Created on：2017/12/15 13:25
 *         Description：获取占卜师业务列表
 */
public interface ContractBusinessList {

    interface IView extends BaseView {
        /**
         * 获取获取占卜师业务列表成功
         *
         * @param bean
         */
        void showBusinessListSuccess(BusinessBean.DataBean bean);

        /**
         * 获取获取占卜师业务列表失败
         *
         * @param errMsg
         */
        void showBusinessListFailure(String errMsg);
    }

    interface IPresenter {
        /**
         * 获取占卜师业务列表
         */
        void getBusinessList(int masterId );
    }

    interface IModel {
        /**
         * 获取占卜师业务列表
         */
        void onBusinessList(int masterId,int pageNum,IBusinessCallback callback);

        interface IBusinessCallback extends IHandleCodeCallback{
            void onBusinessListSuccess(BusinessBean.DataBean bean);

            void onBusinessListFailure(String errMsg);
        }
    }
}
