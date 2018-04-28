package com.yinghai.a24divine_user.module.history.mvp;

import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.HistoryBean;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/13 13:49
 *         Describe：获取历史记录的P层实现
 */

public class HistoryPresenter extends MyBasePresenter<HistoryModel,ContractHistory.IHistoryView> implements ContractHistory.IHistoryPresenter,
        ContractHistory.IHistoryModel.IHistoryCallback {

    private int mPageNum = 1;

    public HistoryPresenter(ContractHistory.IHistoryView view){
        attachView(view);
    }

    @Override
    protected HistoryModel createModel() {
        return new HistoryModel();
    }

    @Override
    public void getHistory() {
        mBaseModel.getHistoryFromNet(mPageNum,this);
    }

    /**
     * 上拉刷新时，需要重置页码
     */
    public void resetPage() {
        mPageNum = 1;
    }

    @Override
    public void onHistorySuccess(HistoryBean bean) {
        if (isViewAttached()){
            getBaseView().showHistorySuccess(bean);
            mPageNum++;
        }
    }

    @Override
    public void onHistoryFailure(String errMsg) {
        if (isViewAttached()){
            getBaseView().showHistoryFailure(errMsg);
        }
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }
}
