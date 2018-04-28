package com.yinghai.a24divine_user.module.divine.divinelist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.fansonlib.callback.LoadMoreListener;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;
import com.example.fansonlib.widget.textview.TextViewDrawable;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.animation.CubeAnimation;
import com.yinghai.a24divine_user.base.BaseMvpSwipeFragment;
import com.yinghai.a24divine_user.bean.BusinessTypeListBean;
import com.yinghai.a24divine_user.bean.MasterBean;
import com.yinghai.a24divine_user.callback.OnMultiFragmentListener;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.module.divine.divinelist.presenter.DivinePresenter;
import com.yinghai.a24divine_user.module.login.state.LoginStateManager;
import com.yinghai.a24divine_user.widget.ChooseTypeWindow;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;

/**
 * @author Created by：fanson
 *         Created on：2017/10/31 14:52
 *         Description：查看所有占卜师列表Fragment
 */

public class DivineFragment extends BaseMvpSwipeFragment<DivinePresenter> implements ContractDivine.IDivineView, DivineAdapter.IAdapterListener, LoadMoreListener,
        ChooseTypeWindow.IChooseTypeListener {

    /**
     * 已选的业务类型
     */
    private String mChooseBusinessType = null;

    /**
     * 标识数据是否已全部加载完毕
     */
    private boolean mIsLoadComplete = false;
    /**
     * 立体翻转的动画时间
     */
    private static final int ANIM_DURATION = 500;
    private DivineAdapter mAdapter;
    private static final int PAGE_SIZE = 10;
    private boolean mIsPull = false; //标识是否上拉刷新
    private AutoLoadRecyclerView mRecyclerView;
    private OnMultiFragmentListener mOnMultiFragmentListener;
    private TextViewDrawable mTdChooseType;
    private ChooseTypeWindow mChooseTypeWindow;
    private ImageView mBtnBack;

    @Override
    protected DivinePresenter createPresenter() {
        return new DivinePresenter(this, hostActivity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_divine;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mOnMultiFragmentListener = (OnMultiFragmentListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnMultiFragmentListener = null;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (enter) {
            return CubeAnimation.create(CubeAnimation.RIGHT, true, ANIM_DURATION);
        } else {
            return CubeAnimation.create(CubeAnimation.LEFT, false, ANIM_DURATION);
        }
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        initRecyclerView();
        mBtnBack = findMyViewId(R.id.btn_back_toolbar);
        mTdChooseType = findMyViewId(R.id.td_choose);
        mTdChooseType.setText(getString(R.string.all));
        return rootView;
    }

    private void initRecyclerView() {
        mRecyclerView = findMyViewId(R.id.rv_divine);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(hostActivity));
        mRecyclerView.setOnPauseListenerParams(true, true);
        mRecyclerView.setLoadMoreListener(this);
        mAdapter = new DivineAdapter(hostActivity);
        mAdapter.setAdapterListener(this);

        SlideInBottomAnimationAdapter slideAdapter = new SlideInBottomAnimationAdapter(mAdapter);
        slideAdapter.setDuration(800);
        slideAdapter.setFirstOnly(false);
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(slideAdapter);
        alphaAdapter.setDuration(800);
        alphaAdapter.setFirstOnly(false);
        mRecyclerView.setAdapter(alphaAdapter);
    }

    @Override
    protected void initToolbarTitle() {
    }

    @Override
    protected void initData() {
        showLoading();
        getMasterListData(mChooseBusinessType);
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hostActivity.finish();
                hostActivity.overridePendingTransition(R.anim.bottom_in, R.anim.rotate_big_to_small);
            }
        });

        mTdChooseType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mChooseTypeWindow == null) {
                    mChooseTypeWindow = new ChooseTypeWindow(hostActivity, DivineFragment.this);
                }
                if (mChooseTypeWindow.isShowing()) {
                    mChooseTypeWindow.dismiss();
                }
            }
        });
    }

    /**
     * 触发网络获取大师列表
     */
    private void getMasterListData(String businessType) {
        mPresenter.onGetMaster(10, businessType);
    }

    /**
     * 点击帅选类型后的触发
     *
     * @param businessType 类型
     */
    public void changeBusinessType(String businessType) {
        showLoading();
        mChooseBusinessType = businessType;
        mAdapter.clearList();
        mPresenter.resetPage();
        mIsLoadComplete = false;
        getMasterListData(mChooseBusinessType);
    }

    @Override
    protected void pullRefresh() {
        mIsPull = true;
        mIsLoadComplete = false;
        mPresenter.resetPage();
        getMasterListData(mChooseBusinessType);
    }

    @Override
    public void showGetMasterSuccess(MasterBean.DataBean bean) {
        if (mIsPull) {
            mAdapter.clearList();
            mIsPull = false;
        }
        mRecyclerView.loadFinish(null);
        if (bean.getMasterList() != null && bean.getMasterList().size() > 0) {
            hideNoDataLayout();
            mAdapter.appendList(bean.getMasterList());
            if (bean.getMasterList().size() < PAGE_SIZE) {
                mIsLoadComplete = true;
            }
        }
        if (mAdapter.getItemCount() == 0) {
            showNoDataLayout();
            mIsLoadComplete = true;
        }
        stopRefresh();
        hideLoading();
    }

    @Override
    public void showGetMasterFailure(String errMsg) {
        hideLoading();
        stopRefresh();
        ShowToast.singleShort(errMsg);
    }

    @Override
    public void showCollectMasterSuccess(int id) {
        mAdapter.updateLike(id, true);
        ShowToast.singleShort(getString(R.string.collect_add_success));
        hideLoading();
    }

    @Override
    public void showCollectMasterFailure(String errMsg) {
        ShowToast.singleShort(errMsg);
        hideLoading();
    }

    @Override
    public void showCancelCollectMasterSuccess(int id) {
        mAdapter.updateLike(id, false);
        ShowToast.singleShort(getString(R.string.collect_cancel_success));
        hideLoading();
    }

    @Override
    public void showCancelCollectMasterFailure(String errMsg) {
        ShowToast.singleShort(errMsg);
        hideLoading();
    }

    @Override
    public void loadMore() {
        if (!mIsLoadComplete) {
            getMasterListData(mChooseBusinessType);
        }
    }

    /**
     * 点击了Adapter item
     *
     * @param bean
     */
    @Override
    public void onClickDivineRootView(MasterBean.DataBean.MasterListBean bean) {
        mOnMultiFragmentListener.onMultiFragment(ConFragment.OPEN_MASTER_DIVINE, bean.getMasterId());
    }

    @Override
    public void onClickLikeDivine(MasterBean.DataBean.MasterListBean bean) {
        if (LoginStateManager.getInstance().onCollect(hostActivity)) {
            showLoading();
            mPresenter.onCollectMaster(bean.getMasterId(), bean.isIsCollection());
        }
    }

    /**
     * 帅选类型的回调
     *
     * @param bean 结果
     */
    @Override
    public void onChooseType(BusinessTypeListBean.DataBean.TfBusinessTypeListBean bean) {
        mChooseTypeWindow.dismiss();
        mChooseTypeWindow = null;
        mTdChooseType.setText(bean.getTypeName());
        changeBusinessType(bean.getBusinessTypeId());
    }

    @Override
    public void onLoadDataCompleted() {
        mChooseTypeWindow.showPopupWindow();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
