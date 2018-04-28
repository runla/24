package com.yinghai.a24divine_user.module.divine.divinelist;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.MasterBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * Created by chenjianrun on 2017/10/30.
 * 描述：名师占卜的管理契约类
 */

public interface ContractDivine {

    interface IDivineView extends BaseView {
        /**
         * 获取名师占卜列表成功
         *
         * @param masterBean
         */
        void showGetMasterSuccess(MasterBean.DataBean masterBean);

        /**
         * 获取名师占卜失败
         *
         * @param errMsg
         */
        void showGetMasterFailure(String errMsg);

        /**
         * 收藏大师成功
         */
        void showCollectMasterSuccess(int id);

        /**
         * 收藏大师失败
         */
        void showCollectMasterFailure(String errMsg);

        /**
         * 取消收藏大师成功
         */
        void showCancelCollectMasterSuccess(int id);

        /**
         * 取消收藏大师失败
         */
        void showCancelCollectMasterFailure(String errMsg);

    }

    interface IDivinePresenter {
        /**
         * 获取名师占卜列表
         *
         * @param pageSize 一页数量
         * @param businessType 业务类型
         */
        void onGetMaster(int pageSize,String businessType);

        /**
         * 收藏
         *
         * @param masterId  收藏类型，1大师 2文章 3商品
         * @param isCollect 是否已收藏
         */
        void onCollectMaster(int masterId, boolean isCollect);
    }

    interface IDivineModel {
        /**
         * 获取名师占卜的列表
         * @param pageNum 页码
         * @param pageSize 一页的内容数量
         * @param businessType 业务类型，null为所有
         * @param callback 回调
         */
        void getMasterList(int pageNum, int pageSize,String businessType, ContractDivine.IDivineModel.IDivineCallback callback);

        interface IDivineCallback extends IHandleCodeCallback {
            void onGetMasterSuccess(MasterBean.DataBean bean);

            void onGetMasterFailure(String errMsg);
        }
    }
}
