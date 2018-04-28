package com.yinghai.a24divine_user.module.divine.divinelist.presenter;

import android.content.Context;

import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.CollectBean;
import com.yinghai.a24divine_user.bean.MasterBean;
import com.yinghai.a24divine_user.module.collect.mvp.CollectModel;
import com.yinghai.a24divine_user.module.collect.mvp.ContractCollect;
import com.yinghai.a24divine_user.module.divine.divinelist.ContractDivine;
import com.yinghai.a24divine_user.module.divine.divinelist.model.GetDivineModel;

/**
 * Created by chenjianrun on 2017/10/30.
 * 描述：查看所有名师占卜的列表的Presenter
 */

public class DivinePresenter extends MyBasePresenter<GetDivineModel, ContractDivine.IDivineView>
        implements ContractDivine.IDivinePresenter, ContractDivine.IDivineModel.IDivineCallback, ContractCollect.ICollectModel.IAddCollectCallback, ContractCollect.ICollectModel.ICancelCollectCallback {

    private int mPageNum = 1;
    private CollectModel mCollectModel;

    public DivinePresenter(ContractDivine.IDivineView baseView, Context context) {
        attachView(baseView);
    }

    private CollectModel getCollectModel() {
        if (mCollectModel == null) {
            mCollectModel = new CollectModel();
        }
        return mCollectModel;
    }

    @Override
    protected GetDivineModel createModel() {
        return new GetDivineModel();
    }

    @Override
    public void onGetMaster(int pageSize,String businessType) {
        mBaseModel.getMasterList(mPageNum, pageSize,businessType, this);
    }

    @Override
    public void onCollectMaster(int masterId, boolean isCollect) {
        if (isCollect) {
            getCollectModel().cancelCollect(1, masterId, this);
        } else {
            getCollectModel().addCollect(1, masterId, this);
        }
    }

    @Override
    public void onGetMasterSuccess(MasterBean.DataBean bean) {
        if (isViewAttached()) {
            getBaseView().showGetMasterSuccess(bean);
            mPageNum++;
        }
    }

    @Override
    public void onGetMasterFailure(String errMsg) {
        if (isViewAttached()) {
            getBaseView().showGetMasterFailure(errMsg);
        }
    }

    @Override
    public void onAddCollectSuccess(CollectBean bean,int id) {
        if (isViewAttached()) {
            getBaseView().showCollectMasterSuccess(id);
        }
    }

    @Override
    public void onAddCollectFailure(String errMsg) {
        if (isViewAttached()) {
            getBaseView().showCollectMasterFailure(errMsg);
        }
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    /**
     * 上拉刷新时，需要重置页码
     */
    public void resetPage() {
        mPageNum = 1;
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mCollectModel != null) {
            mCollectModel.onDestroy();
            mCollectModel = null;
        }
    }

    @Override
    public void onCancelCollectSuceess(CollectBean bean,int id) {
        if (isViewAttached()) {
            getBaseView().showCancelCollectMasterSuccess(id);
        }
    }

    @Override
    public void onCancelCollectFailure(String errMsg ) {
        if (isViewAttached()) {
            getBaseView().showCancelCollectMasterFailure(errMsg);
        }
    }
}
