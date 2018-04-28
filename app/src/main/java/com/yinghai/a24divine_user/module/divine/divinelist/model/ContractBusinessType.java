package com.yinghai.a24divine_user.module.divine.divinelist.model;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.BusinessTypeListBean;

/**
 * @author Created by：fanson
 *         Created Time: 2017/12/28 14:17
 *         Describe：获取业务类型的契约类
 */

public interface ContractBusinessType {

    interface IView extends BaseView{

        /**
         * 获取业务类型成功
         */
        void showBusinessTypeSuccess(BusinessTypeListBean.DataBean bean);

        /**
         * 获取业务类型失败
         */
        void showBusinessTypeFailure(String errorMsg);

    }

    interface IPresenter{

        /**
         * 获取业务类型
         */
        void getBusinessType();

    }

    interface IModel{

        /**
         * 获取业务类型
         * @param callback
         */
        void onGetBusinessType(ITypeCallback callback);


        interface ITypeCallback  {

            /**
             * 获取业务类型成功
             */
            void onGetBusinessTypeSuccess(BusinessTypeListBean.DataBean bean);

            /**
             * 获取业务类型失败
             */
            void onGetBusinessTypeFailure(String errorMsg);

        }

    }


}
