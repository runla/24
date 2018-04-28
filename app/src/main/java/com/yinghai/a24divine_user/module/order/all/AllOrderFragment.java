package com.yinghai.a24divine_user.module.order.all;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewStub;

import com.example.fansonlib.callback.LoadMoreListener;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.BaseMvpSwipeFragment;
import com.yinghai.a24divine_user.bean.OrderBean;
import com.yinghai.a24divine_user.bean.ProductOrderListBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConstAdapter;
import com.yinghai.a24divine_user.module.divine.book.pay.PayWaysWindow;
import com.yinghai.a24divine_user.module.order.all.adapter.AllOrderAdapter;
import com.yinghai.a24divine_user.module.order.all.adapter.AllProductOrderAdapter;
import com.yinghai.a24divine_user.module.order.all.mvp.ContractOrder;
import com.yinghai.a24divine_user.module.order.all.mvp.OrderPresenter;
import com.yinghai.a24divine_user.module.order.detail.OrderDetailsActivity;
import com.yinghai.a24divine_user.utils.LogUtils;

/**
 * @author Created by：fanson
 *         Created on：2017/10/24 16:00
 *         Describe：全部订单的Fragment
 */

public class AllOrderFragment extends BaseMvpSwipeFragment<OrderPresenter> implements ContractOrder.IOrderView, LoadMoreListener, OnAdapterListener, PayWaysWindow.PayCallback {

    private static final String TAG = AllOrderFragment.class.getSimpleName();
    /**
     * 标识数据是否已全部加载完毕
     */
    private boolean mIsLoadComplete = false;
    private static final int PAGE_SIZE = 10;
    private AllOrderAdapter mAdapter;
    private AllProductOrderAdapter mProductAdapter;
    private AutoLoadRecyclerView mRecyclerView;
    private PayWaysWindow payWaysWindow;
    private ViewStub mViewStub;
    private boolean mIsPull = false; //标识是否上拉刷新
    public static final int TYPE_DIVINE = 1;
    public static final int TYPE_PRODUCT = 3;
    /**
     * 标识有没切换类型
     */
    private boolean mIsChangeType = false;
    /**
     * 获取数据的类型
     */
    private int mDataType = TYPE_DIVINE;

    @Override
    protected OrderPresenter createPresenter() {
        return new OrderPresenter(this, hostActivity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all_order;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        initRecyclerView();
        return rootView;
    }

    @Override
    protected void initToolbarTitle() {

    }

    /**
     * 更改获取数据的类型
     *
     * @param type
     */
    public void resetDataType(int type) {
        if (type == TYPE_DIVINE) {
            if (mAdapter == null) {
                mAdapter = new AllOrderAdapter(hostActivity, this);
            }
            mAdapter.clearList();
            mRecyclerView.setAdapter(mAdapter);
        } else {
            if (mProductAdapter == null) {
                mProductAdapter = new AllProductOrderAdapter(hostActivity, this);
            }
            mProductAdapter.clearList();
            mRecyclerView.setAdapter(mProductAdapter);
        }
        mDataType = type;
        mIsChangeType = true;
        mIsLoadComplete = false;
        mPresenter.resetPageNum();
        if (getUserVisibleHint()){
            showLoading();
            getOrderData();
            mIsChangeType = false;
        }
    }

    private void initRecyclerView() {
        mRecyclerView = findMyViewId(R.id.rv_all_order);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(hostActivity));
        mRecyclerView.setOnPauseListenerParams(true, true);
        mRecyclerView.setLoadMoreListener(this);
        mAdapter = new AllOrderAdapter(hostActivity, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        showLoading();
        getOrderData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtils.d(TAG, isVisibleToUser);
        if (isVisibleToUser && mIsChangeType ) {
            showLoading();
            getOrderData();
            mIsChangeType = false;
        }
    }

    private void getOrderData() {
        mPresenter.onOrderOrder(null, mDataType);
    }

    @Override
    protected void pullRefresh() {
        mIsPull = true;
        mIsLoadComplete = false;
        mPresenter.resetPageNum();
        getOrderData();
    }

    @Override
    public void loadMore() {
        if (!mIsLoadComplete){
            getOrderData();
        }
    }

    @Override
    public void showOrderSuccess(OrderBean bean) {
        if (mIsPull) {
            mAdapter.clearList();
            mIsPull = false;
        }
        mRecyclerView.loadFinish(null);
        if (bean.getData().getTfOrderList() != null && bean.getData().getTfOrderList().size() > 0) {
            hideNoDataLayout();
            mAdapter.appendList(bean.getData().getTfOrderList());
            if (bean.getData().getTfOrderList().size() < PAGE_SIZE) {
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
    public void showOrderFailure(String errorMsg) {
        ShowToast.singleShort(errorMsg);
        stopRefresh();
        hideLoading();
    }

    @Override
    public void showProductListSuccess(ProductOrderListBean bean) {
        LogUtils.d(TAG,"showProductListSuccess");
        if (mIsPull) {
            mProductAdapter.clearList();
            mIsPull = false;
        }
        mRecyclerView.loadFinish(null);
        if (bean.getData().getTfOrderList() != null && bean.getData().getTfOrderList().size() > 0) {
            hideNoDataLayout();
            mProductAdapter.appendList(bean.getData().getTfOrderList());
            if (bean.getData().getTfOrderList().size() < PAGE_SIZE) {
                mIsLoadComplete = true;
            }
        }
        if (mProductAdapter.getItemCount() == 0) {
            showNoDataLayout();
            mIsLoadComplete = true;
        }
        stopRefresh();
        hideLoading();
    }

    @Override
    public void showProductListFailure(String errorMsg) {
        ShowToast.singleShort(errorMsg);
        stopRefresh();
        hideLoading();
    }

    @Override
    public void completeOrderSuccess() {
        ShowToast.singleShort(getString(R.string.order_has_complete));
        hideLoading();
    }

    @Override
    public void completeOrderFailure(String errMsg) {
        ShowToast.singleShort(errMsg);
        hideLoading();
    }

    @Override
    public void clickItem(Object... object) {
        switch ((Integer) object[0]) {
            case ConstAdapter.DIVINE_ORDER_DETAIL:
                OrderDetailsActivity.startOrderDetailsActivity(hostActivity, 2, (OrderBean.DataBean.TfOrderListBean) object[1]);
                break;
            case ConstAdapter.OPEN_PRODUCT_ORDER_DETAIL:
                OrderDetailsActivity.startOrderDetailsActivity(hostActivity, 1, (ProductOrderListBean.DataBean.TfOrderListBean) object[1]);
                break;
            case ConstAdapter.OPEN_PAY_WINDOW:
                payWaysWindow = new PayWaysWindow(hostActivity, (String) object[1], (Integer) object[2], (Integer) object[3], this);
                payWaysWindow.showPopupWindow();
                break;
            case ConstAdapter.COMPLETE_ORDER:
                showLoading();
                mPresenter.completeOrder((Integer) object[1], (Integer) object[2]);
                break;
            default:
                break;
        }
    }

    /**
     * 显示无数据界面
     */
    @Override
    public void showNoDataLayout() {
        if (mViewStub==null){
            mViewStub = (ViewStub) hostActivity.findViewById(R.id.vs_no_data);
        }
        if (mViewStub != null) {
            mViewStub.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏无数据界面
     */
    @Override
    public void hideNoDataLayout() {
        if (mViewStub != null) {
            mViewStub.setVisibility(View.GONE);
        }
    }

    @Override
    public void showPayLoading() {

    }

    @Override
    public void paySuccess() {
        payWaysWindow.dismiss();
        ShowToast.singleShort(getString(R.string.pay_success_to_order_see));
    }

    @Override
    public void payFailure(int errCode) {
        ShowToast.singleShort(getString(R.string.alipay_failure) + errCode);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.releaseResource();
    }
}
