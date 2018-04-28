package com.yinghai.a24divine_user.module.main.presenter;

import android.content.Context;

import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.ArticleBean;
import com.yinghai.a24divine_user.bean.CollectBean;
import com.yinghai.a24divine_user.bean.LuckBean;
import com.yinghai.a24divine_user.bean.MasterBean;
import com.yinghai.a24divine_user.module.artical.list.ContractArticle;
import com.yinghai.a24divine_user.module.artical.list.model.GetArticleModel;
import com.yinghai.a24divine_user.module.collect.mvp.CollectModel;
import com.yinghai.a24divine_user.module.collect.mvp.ContractCollect;
import com.yinghai.a24divine_user.module.divine.divinelist.ContractDivine;
import com.yinghai.a24divine_user.module.divine.divinelist.model.GetDivineModel;
import com.yinghai.a24divine_user.module.main.ContractMain;
import com.yinghai.a24divine_user.module.main.model.MainModel;

/**
 * @author  Created by：fanson
 * Created on：2017/10/24 13:24
 * Describe：主界面的 presenter，这里有三个接口
 */

public class MainPresenter extends MyBasePresenter<MainModel, ContractMain.IMainView> implements ContractMain.IMainPresenter, ContractDivine.IDivineModel.IDivineCallback, ContractArticle.IArticleModel.IArticleCallback, ContractMain.IMainModel.ILuckCallback, ContractCollect.ICollectModel.IAddCollectCallback, ContractCollect.ICollectModel.ICancelCollectCallback {

    private GetArticleModel mGetArticleModel;
    private GetDivineModel mGetDivineModel;
    private CollectModel mCollectModel;
    private static final String ALL_TYPE = null;
    private static final int ALL_MASTER = 0;
    private static final int PAGE_SIZE = 3;
    private static final int PAGE_NUM = 1;

    public MainPresenter(ContractMain.IMainView view, Context context) {
        attachView(view);
    }

    private CollectModel getCollectModel() {
        if (mCollectModel == null) {
            mCollectModel = new CollectModel();
        }
        return mCollectModel;
    }

    private GetArticleModel getArticleModel() {
        if (mGetArticleModel == null) {
            mGetArticleModel = new GetArticleModel();
        }
        return mGetArticleModel;
    }

    private GetDivineModel getGetDivineModel() {
        if (mGetDivineModel == null) {
            mGetDivineModel = new GetDivineModel();
        }
        return mGetDivineModel;
    }

    @Override
    protected MainModel createModel() {
        return new MainModel();
    }

    @Override
    public void getArticle() {
        getArticleModel().getArticleList(ALL_MASTER, ALL_TYPE, PAGE_NUM, PAGE_SIZE, this);
    }

    @Override
    public void getMasterDivine() {
        getGetDivineModel().getMasterList(PAGE_NUM, PAGE_SIZE, null, this);
    }

    @Override
    public void openLuck() {
        mBaseModel.openLuck(this);
    }

    @Override
    public void changeLuck(int constellation) {
        mBaseModel.changeLuck(constellation, this);
    }

    /**
     * 收藏大师/文章
     *
     * @param type        收藏类型，1大师 2文章 3商品
     * @param id          type=1时为大师id，type=2时为文章id，type=3为商品id
     * @param isCollected 是否收藏
     */
    @Override
    public void onCollect(int type, int id, boolean isCollected) {
        if (isCollected) {
            getCollectModel().cancelCollect(type, id, this);
        } else {
            getCollectModel().addCollect(type, id, this);
        }
    }

    /**
     * 根据int输出周几
     *
     * @param i
     * @eturn string资源
     */
    public int printDayOfWeek(int i) {
        switch (i) {
            case 1:
                return R.string.sunday;
            case 2:
                return R.string.monday;
            case 3:
                return R.string.tuesday;
            case 4:
                return R.string.wednesday;
            case 5:
                return R.string.thursday;
            case 6:
                return R.string.friday;
            case 7:
                return R.string.saturday;
            default:
                break;
        }
        return R.string.sunday;
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    @Override
    public void onGetLuckSuccess(LuckBean bean) {
        if (isViewAttached()) {
            getBaseView().onGetLuckSuccess(bean);
        }
    }

    @Override
    public void onGetLuckFailure(String errMsg) {
        if (isViewAttached()) {
            getBaseView().onGetLuckFailure(errMsg);
        }
    }

    @Override
    public void onGetMasterSuccess(MasterBean.DataBean bean) {
        if (isViewAttached()) {
            getBaseView().onGetDivineSuccess(bean);
        }
    }

    @Override
    public void onGetMasterFailure(String errMsg) {
        if (isViewAttached()) {
            getBaseView().onGetDivineFailure(errMsg);
        }
    }

    @Override
    public void onArticleSuccess(ArticleBean.DataBean bean) {
        if (isViewAttached()) {
            getBaseView().onGetArticleSuccess(bean);
        }
    }

    @Override
    public void onArticleFailure(String errorMsg) {
        getBaseView().onGetArticleFailure(errorMsg);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mGetArticleModel != null) {
            mGetArticleModel.onDestroy();
            mGetArticleModel = null;
        }
        if (mGetDivineModel != null) {
            mGetDivineModel.onDestroy();
            mGetDivineModel = null;
        }
    }

    @Override
    public void onAddCollectSuccess(CollectBean bean, int id) {
        if (isViewAttached()) {
            getBaseView().showCollectSuccess(bean.getData(), id);
        }
    }

    @Override
    public void onAddCollectFailure(String errMsg) {
        if (isViewAttached()) {
            getBaseView().showCollectFailure(errMsg);
        }
    }

    @Override
    public void onCancelCollectSuceess(CollectBean bean, int id) {
        if (isViewAttached()) {
            getBaseView().showCancelCollectSuccess(bean.getData(), id);
        }
    }

    @Override
    public void onCancelCollectFailure(String errMsg) {
        if (isViewAttached()) {
            getBaseView().showCancelCollectFailure(errMsg);
        }
    }
}
