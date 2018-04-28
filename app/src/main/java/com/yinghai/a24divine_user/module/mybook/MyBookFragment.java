package com.yinghai.a24divine_user.module.mybook;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.BaseMvpSwipeFragment;
import com.yinghai.a24divine_user.bean.OrderBean;
import com.yinghai.a24divine_user.bean.ProductOrderListBean;
import com.yinghai.a24divine_user.module.order.all.mvp.ContractOrder;
import com.yinghai.a24divine_user.module.order.all.mvp.OrderPresenter;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/25 17:46
 *         Describe：我的预约订单Fragment
 */

public class MyBookFragment extends BaseMvpSwipeFragment<OrderPresenter> implements ContractOrder.IOrderView {

    private MyBookAdapter mAdapter;

    @Override
    protected OrderPresenter createPresenter() {
        return new OrderPresenter(this,hostActivity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_book;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        initRecyclerView();
        return rootView;
    }

    @Override
    protected void initToolbarTitle() {
        setToolbarTitle(getString(R.string.book));
    }

    private void initRecyclerView() {
        AutoLoadRecyclerView mRecyclerView = findMyViewId(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(hostActivity));
        mRecyclerView.setOnPauseListenerParams(true, true);
        mAdapter = new MyBookAdapter(hostActivity);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        showLoading();
//        mPresenter.getHistoryOrder();
    }

    private void getData(){
//        mPresenter.onOrderOrder();
    }

    @Override
    protected void pullRefresh() {
        getData();
    }


    @Override
    public void showOrderSuccess(OrderBean bean) {
        hideLoading();
        stopRefresh();
//        mAdapter.fillList(bean.getData().getOrderList());
    }

    @Override
    public void showOrderFailure(String errorMsg) {
        ShowToast.singleShort(errorMsg);
    }

    @Override
    public void showProductListSuccess(ProductOrderListBean bean) {

    }

    @Override
    public void showProductListFailure(String errorMsg) {

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
}
