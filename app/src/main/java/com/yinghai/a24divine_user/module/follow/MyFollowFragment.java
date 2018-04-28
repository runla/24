package com.yinghai.a24divine_user.module.follow;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.FrameLayout;

import com.example.fansonlib.callback.LoadMoreListener;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.BaseMvpSwipeFragment;
import com.yinghai.a24divine_user.bean.FollowBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConstAdapter;
import com.yinghai.a24divine_user.module.divine.DivineActivity;
import com.yinghai.a24divine_user.module.follow.presenter.FollowPresenter;
import com.yinghai.a24divine_user.utils.ClearImageUtils;

import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/25 15:50
 *         Describe：我关注的Fragment
 */

public class MyFollowFragment extends BaseMvpSwipeFragment<FollowPresenter> implements ContractFollow.IFollowView, FollowAdapter.FollowAdapterCallback, LoadMoreListener, OnAdapterListener {

    /**
     * 标识数据是否已全部加载完毕
     */
    private boolean mIsLoadComplete = false;
    private static final int PAGE_SIZE = 10;
    private FollowAdapter mAdapter;
    private boolean mIsPull = false;
    private FrameLayout mRootView;
    private FollowBean.DataBean.SubscribesBean mTemCancelBean;

    @Override
    protected FollowPresenter createPresenter() {
        return new FollowPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_follow;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        initRecyclerView();
        mRootView = findMyViewId(R.id.rootView);
        return rootView;
    }

    @Override
    protected void initToolbarTitle() {
        setToolbarTitle(getString(R.string.my_follow));
    }

    private void initRecyclerView() {
        AutoLoadRecyclerView mRecyclerView = findMyViewId(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(hostActivity, 2));
        mRecyclerView.setOnPauseListenerParams(true, true);
        mRecyclerView.setLoadMoreListener(this);
        mAdapter = new FollowAdapter(hostActivity);
        mAdapter.setCallback(this);
        mAdapter.setOnAdapterListener(this);

        SlideInBottomAnimationAdapter slideAdapter = new SlideInBottomAnimationAdapter(mAdapter);
        slideAdapter.setDuration(1000);
        slideAdapter.setFirstOnly(false);
        mRecyclerView.setAdapter(slideAdapter);

    }

    @Override
    protected void initData() {
        showLoading();
        getData();
    }

    private void getData(){
        mPresenter.getFollowList();
    }

    @Override
    protected void pullRefresh() {
        mPresenter.resetPage();
        mIsPull = true;
        mIsLoadComplete = false;
        getData();
    }

    @Override
    public void getFollowListSuccess(FollowBean bean) {
        if (mIsPull) {
            mAdapter.clearList();
            mIsPull = false;
        }

        if (bean.getData().getSubscribes() != null && bean.getData().getSubscribes().size() > 0) {
            mAdapter.appendList(bean.getData().getSubscribes());
            if (bean.getData().getSubscribes().size() < PAGE_SIZE) {
                mIsLoadComplete = true;
            }
        }
        if (mAdapter.getItemCount() == 0){
            showNoDataLayout();
            mIsLoadComplete = true;
        }

        hideLoading();
        stopRefresh();
    }

    @Override
    public void getFollowListFailure(String errorMsg) {
        hideLoading();
        ShowToast.singleLong(errorMsg);
    }

    @Override
    public void cancelFollowSuccess() {
        hideLoading();
        mAdapter.removeItem(mTemCancelBean);
        ShowToast.singleLong(getString(R.string.cancel_follow_success));
    }


    @Override
    public void cancelFollowFailure(String errMsg) {
        hideLoading();
        ShowToast.singleLong(errMsg);
    }


    @Override
    public void onClickCancel(FollowBean.DataBean.SubscribesBean dataBean) {
        showLoading();
        mPresenter.cancelFollow(dataBean.getSMasterId());
        mTemCancelBean = dataBean;
    }

    @Override
    public void loadMore() {
        if (!mIsLoadComplete){
            getData();
        }
    }

    @Override
    public void clickItem(Object... object) {
        switch ((Integer)object[0]){
            case ConstAdapter.OPEN_MASTER_INDEX:
                DivineActivity.startDivineActivity(hostActivity,DivineActivity.MASTER_INDEX_TYPE,(Integer) object[1]);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ClearImageUtils.recycleBackground(mRootView);
    }
}
