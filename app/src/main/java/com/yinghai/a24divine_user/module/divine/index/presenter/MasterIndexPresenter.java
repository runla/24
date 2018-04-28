package com.yinghai.a24divine_user.module.divine.index.presenter;

import android.content.Context;

import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.BusinessBean;
import com.yinghai.a24divine_user.bean.FollowBean;
import com.yinghai.a24divine_user.bean.MasterDetailBean;
import com.yinghai.a24divine_user.module.divine.index.ContractMasterIndex;
import com.yinghai.a24divine_user.module.divine.index.detail.ContractMasterDetail;
import com.yinghai.a24divine_user.module.divine.index.detail.MasterDetailModel;
import com.yinghai.a24divine_user.module.divine.index.model.MasterIndexModel;
import com.yinghai.a24divine_user.module.follow.ContractFollow;
import com.yinghai.a24divine_user.module.follow.model.FollowModel;

/**
 * Created by chenjianrun on 2017/10/30.
 * 描述：占卜师主界面的 P 层
 * 有两个 Model：MasterIndexModel、DivineBookModel
 */

public class MasterIndexPresenter extends MyBasePresenter<MasterIndexModel, ContractMasterIndex.IBusinessView>  implements ContractMasterIndex.IMasterIndexPresenter,
      ContractMasterDetail.IPresenter, ContractMasterIndex.IMasterIndexModel.IGetBusinessCallabck, ContractMasterDetail.IModel.IMasterDetailCallabck, ContractMasterIndex.IMasterIndexModel.IFollowCallabck, ContractFollow.IFollowModel.ICancelFollowCallback {

    private MasterDetailModel mMasterDetailModel;
    private MasterIndexModel mMasterIndexModel;
    private FollowModel mFollowModel;
    private int pageNum = 1;

    public MasterIndexPresenter(ContractMasterIndex.IBusinessView baseview, Context context) {
        attachView(baseview);
    }

    private FollowModel getFollowModel(){
        if (mFollowModel==null){
            mFollowModel = new FollowModel();
        }
        return mFollowModel;
    }

    private MasterDetailModel getMasterDetailModel(){
        if (mMasterDetailModel == null){
            mMasterDetailModel  = new MasterDetailModel();
        }
        return  mMasterDetailModel;
    }

    @Override
    protected MasterIndexModel createModel() {
        if (mMasterIndexModel==null){
            mMasterIndexModel = new MasterIndexModel();
        }
        return mMasterIndexModel;
    }

    public void resetPage(){
        pageNum = 1;
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    @Override
    public void getMasterBusiness(int masterId ) {
        mBaseModel.getBusiness(masterId, pageNum, this);
    }

    @Override
    public void addFollow(int masterId) {
        mBaseModel.onAddFollow(masterId,this);
    }

    @Override
    public void cancelFollow(int masterId) {
        getFollowModel().onCancelFollow(masterId,this);
    }

    @Override
    public void onGetBusinessSuccess(BusinessBean.DataBean bean) {
        if (isViewAttached()) {
            getBaseView().showGetBusinessSuccess(bean);
            pageNum++;
        }
    }

    @Override
    public void onGetBusinessFailuer(String errMsg) {
        if (isViewAttached()) {
            getBaseView().showBusinessFailure(errMsg);
        }
    }

    @Override
    public void getMasterDetail(int masterId) {
        getMasterDetailModel().onMasterDetail(masterId,this);
    }

    @Override
    public void onMasterDetailSuccess(MasterDetailBean.DataBean bean) {
        if (isViewAttached()) {
            getBaseView().showMasterDetailSuccess(bean);
        }
    }

    @Override
    public void onMasterDetailFailuer(String errMsg) {
        if (isViewAttached()) {
            getBaseView().showMasterDetailFailuer(errMsg);
        }
    }

    @Override
    public void onFollowSuccess(FollowBean.DataBean bean) {
        if (isViewAttached()) {
            getBaseView().showFollowSuccess(bean);
        }
    }

    @Override
    public void onFollowFailuer(String errMsg) {
        if (isViewAttached()) {
            getBaseView().showFollowFailuer(errMsg);
        }
    }

    @Override
    public void onCancelFollowSuccess() {
        if (isViewAttached()) {
            getBaseView().showCancelFollowSuccess();
        }
    }

    @Override
    public void onCancelFollowFailure(String errMsg) {
        if (isViewAttached()) {
            getBaseView().showCancelFollowFailuer(errMsg);
        }
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mMasterIndexModel!=null){
            mMasterIndexModel.onDestroy();
            mMasterIndexModel = null;
        }
        if (mMasterDetailModel!=null){
            mMasterDetailModel.onDestroy();
            mMasterDetailModel = null;
        }
        if (mFollowModel!=null){
            mFollowModel.onDestroy();
            mFollowModel = null;
        }
    }
}
