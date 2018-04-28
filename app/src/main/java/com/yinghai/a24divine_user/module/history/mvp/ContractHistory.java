package com.yinghai.a24divine_user.module.history.mvp;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.HistoryBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/13 13:35
 *         Describe：历史记录的契约类
 */

public interface ContractHistory {

    interface IHistoryView extends BaseView{

        void showHistorySuccess(HistoryBean bean);

        void showHistoryFailure(String errMsg);

    }

    interface IHistoryPresenter{

        void getHistory();

    }

    interface IHistoryModel{

        void getHistoryFromNet(int pageNum,IHistoryCallback callback);

        interface IHistoryCallback extends IHandleCodeCallback{

            void onHistorySuccess(HistoryBean bean);

            void onHistoryFailure(String errMsg);
        }


    }
}
