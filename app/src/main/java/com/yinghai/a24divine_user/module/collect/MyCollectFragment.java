package com.yinghai.a24divine_user.module.collect;

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
import com.yinghai.a24divine_user.bean.CollectBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConstAdapter;
import com.yinghai.a24divine_user.module.artical.ArticleActivity;
import com.yinghai.a24divine_user.module.collect.adapter.CollectionAdapter;
import com.yinghai.a24divine_user.module.collect.mvp.CollectPresenter;
import com.yinghai.a24divine_user.module.collect.mvp.ContractCollect;
import com.yinghai.a24divine_user.module.divine.DivineActivity;
import com.yinghai.a24divine_user.module.shop.ShopActivity;
import com.yinghai.a24divine_user.utils.ClearImageUtils;

import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;

import static com.yinghai.a24divine_user.module.artical.ArticleActivity.startActivityToDetail;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/30 11:41
 *         Describe：My Collect Fragment
 */

public class MyCollectFragment extends BaseMvpSwipeFragment<CollectPresenter> implements ContractCollect.ICollectView, LoadMoreListener, OnAdapterListener {

    /**
     * 标识数据是否已全部加载完毕
     */
    private boolean mIsLoadComplete = false;
    private static final int PAGE_SIZE = 10;
    private CollectionAdapter mAdapter;
    private boolean mIsPull = false; //标识是否上拉刷新
    private AutoLoadRecyclerView mRecyclerView;
    private LinearLayout mRootView;

    @Override
    protected CollectPresenter createPresenter() {
        return new CollectPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collection;
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
        setToolbarTitle(getString(R.string.collection));
    }

    private void initRecyclerView() {
        mRecyclerView = findMyViewId(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(hostActivity));
        mRecyclerView.setOnPauseListenerParams(true, true);
        mRecyclerView.setLoadMoreListener(this);
        mAdapter = new CollectionAdapter(hostActivity);
        mAdapter.setOnAdapterListener(this);
        SlideInLeftAnimationAdapter slideAdapter = new SlideInLeftAnimationAdapter(mAdapter);
        slideAdapter.setDuration(800);
        slideAdapter.setInterpolator(new AnticipateOvershootInterpolator());
        slideAdapter.setFirstOnly(false);
        mRecyclerView.setAdapter(slideAdapter);
    }

    @Override
    protected void initData() {
        showLoading();
        getCollectData();
    }

    private void getCollectData() {
        mPresenter.onGetCollect();
    }

    @Override
    public void getCollectSuccess(CollectBean bean) {
        if (mIsPull) {
            mAdapter.clearList();
            mIsPull = false;
        }
        mRecyclerView.loadFinish(null);
        if (bean.getData() != null && bean.getData().getCollection().size() > 0) {
            mAdapter.appendList(bean.getData().getCollection());
            if (bean.getData().getCollection().size() < PAGE_SIZE) {
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
    public void getCollectFailure(String errorMsg) {
        ShowToast.singleLong(errorMsg);
        stopRefresh();
        hideLoading();
    }

    @Override
    public void onCancelCollectSuceess(int id) {
        hideLoading();
        ShowToast.singleLong(getString(R.string.has_success_cancel_collect));
    }

    @Override
    public void onCancelCollectFailure(String errMsg) {
        hideLoading();
        ShowToast.singleLong(errMsg);
    }

    @Override
    public void onAddCollectSuccess(int id) {
        hideLoading();
        ShowToast.singleLong(getString(R.string.collect_add_success));
    }

    @Override
    public void onAddCollectFailure(String errMsg) {
        hideLoading();
        ShowToast.singleLong(errMsg);
    }

    @Override
    protected void pullRefresh() {
        mIsPull = true;
        mIsLoadComplete = false;
        mPresenter.resetPage();
        getCollectData();
    }

    @Override
    public void loadMore() {
        if (!mIsLoadComplete){
            getCollectData();
        }
    }

    /**
     * 点击适配器Item的回调
     *
     * @param object 序号/Model
     */
    @Override
    public void clickItem(Object... object) {
        switch ((int) object[0]) {
            case ConstAdapter.CLICK_PRODUCT:
                ShopActivity.startShopActivity(hostActivity, ShopActivity.TYPE_PRODUCT_DETAIL, (int)object[1]);
                break;

            case ConstAdapter.CLICK_MASTER:
                // 跳转到大师主页
                DivineActivity.startDivineActivity(hostActivity, DivineActivity.MASTER_INDEX_TYPE, (int)object[1]);

                break;
            case ConstAdapter.CLICK_ARTICLE:
                // 跳转到文章详情
                CollectBean.DataBean.CollectionBean.ArticleBean articleBean = (CollectBean.DataBean.CollectionBean.ArticleBean) object[1];
                startActivityToDetail(hostActivity, ArticleActivity.TYPE_DETAIL, articleBean.getArticleId());
                break;

            // 收藏商品
            case ConstAdapter.COLLECT_PRODUCT:
                mPresenter.onCollect(3, (int) object[1], (boolean) object[2]);
                break;

            // 收藏文章
            case ConstAdapter.COLLECT_ARTICLE:
                mPresenter.onCollect(2, (int) object[1], (boolean) object[2]);
                break;

            // 收藏大师
            case ConstAdapter.COLLECT_MASTER:
                mPresenter.onCollect(1, (int) object[1], (boolean) object[2]);
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
