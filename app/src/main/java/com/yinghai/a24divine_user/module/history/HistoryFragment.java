package com.yinghai.a24divine_user.module.history;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.LinearLayout;

import com.example.fansonlib.callback.LoadMoreListener;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.BaseMvpSwipeFragment;
import com.yinghai.a24divine_user.bean.HistoryBean;
import com.yinghai.a24divine_user.bean.MasterBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConstAdapter;
import com.yinghai.a24divine_user.module.artical.ArticleActivity;
import com.yinghai.a24divine_user.module.divine.DivineActivity;
import com.yinghai.a24divine_user.module.history.adapter.HistoryAdapter;
import com.yinghai.a24divine_user.module.history.mvp.ContractHistory;
import com.yinghai.a24divine_user.module.history.mvp.HistoryPresenter;
import com.yinghai.a24divine_user.module.shop.ShopActivity;
import com.yinghai.a24divine_user.utils.ClearImageUtils;

import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;

import static com.yinghai.a24divine_user.module.artical.ArticleActivity.startActivityToDetail;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/30 12:03
 *         Describe：历史记录Fragment
 */

public class HistoryFragment extends BaseMvpSwipeFragment<HistoryPresenter> implements ContractHistory.IHistoryView, LoadMoreListener, OnAdapterListener {

    /**
     * 标识数据是否已全部加载完毕
     */
    private boolean mIsLoadComplete = false;
    private static final int PAGE_SIZE = 10;
    private boolean mIsPull = false; //标识是否上拉刷新
    private AutoLoadRecyclerView mRecyclerView;
    private HistoryAdapter mAdapter;
    private LinearLayout mRootView;

    @Override
    protected HistoryPresenter createPresenter() {
        return new HistoryPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_history;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        initRecyclerView();
        mRootView = findMyViewId(R.id.rootView);
        return rootView;
    }

    private void initRecyclerView() {
        mRecyclerView = findMyViewId(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(hostActivity));
        mRecyclerView.setOnPauseListenerParams(true, true);
        mRecyclerView.setLoadMoreListener(this);
        mAdapter = new HistoryAdapter(hostActivity);
        mAdapter.setOnAdapterListener(this);

        SlideInLeftAnimationAdapter slideAdapter = new SlideInLeftAnimationAdapter(mAdapter);
        slideAdapter.setDuration(800);
        slideAdapter.setInterpolator(new AnticipateOvershootInterpolator());
        slideAdapter.setFirstOnly(false);
        mRecyclerView.setAdapter(slideAdapter);
    }

    @Override
    protected void initToolbarTitle() {
        setToolbarTitle(getString(R.string.history));
    }

    @Override
    protected void initData() {
        showLoading();
        startGetHistoryData();
    }

    private void startGetHistoryData(){
        mPresenter.getHistory();
    }

    @Override
    public void showHistorySuccess(HistoryBean bean) {
        if (mIsPull) {
            mAdapter.clearList();
            mIsPull = false;
        }
        mRecyclerView.loadFinish(null);
        if (bean.getData() != null && bean.getData().size() > 0) {
            mAdapter.appendList(bean.getData());
            if (bean.getData().size() < PAGE_SIZE) {
                ShowToast.singleShort(getString(R.string.load_complete));
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
    public void showHistoryFailure(String errorMsg) {
        ShowToast.singleLong(errorMsg);
        stopRefresh();
        hideLoading();
    }

    @Override
    public void loadMore() {
        if (!mIsLoadComplete){
            startGetHistoryData();
        }
    }

    @Override
    protected void pullRefresh() {
        mIsPull = true;
        mIsLoadComplete = false;
        mPresenter.resetPage();
        startGetHistoryData();
    }

    /**
     * 点击适配器内容的回调
     * @param object 序号/Model
     */
    @Override
    public void clickItem(Object... object) {
        switch ((int) object[0]) {
            case ConstAdapter.CLICK_PRODUCT:
                // 跳转到商品详情
                ShopActivity.startShopActivity(hostActivity,ShopActivity.TYPE_PRODUCT_DETAIL, (int)object[1] );
                break;

            case ConstAdapter.CLICK_MASTER:
                // 跳转到大师主页
                DivineActivity.startDivineActivity(hostActivity,DivineActivity.MASTER_INDEX_TYPE, (int)object[1]);

                break;
            case ConstAdapter.CLICK_ARTICLE:
                // 跳转到文章详情
                HistoryBean.DataBean.ArticleBean articleBean = (HistoryBean.DataBean.ArticleBean) object[1];
                startActivityToDetail(hostActivity, ArticleActivity.TYPE_DETAIL, articleBean.getArticleId());
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
