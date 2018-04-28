package com.yinghai.a24divine_user.module.order.detail.product;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.dialogfragment.DoubleDialog;
import com.example.fansonlib.widget.dialogfragment.base.IConfirmListener;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.module.order.SubjectHelper;
import com.yinghai.a24divine_user.base.MyBaseMvpFragment;
import com.yinghai.a24divine_user.bean.NoDataBean;
import com.yinghai.a24divine_user.bean.ProductOrderListBean;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.constant.ConstOrderStatus;
import com.yinghai.a24divine_user.module.order.detail.mvp.CancelOrderPresenter;
import com.yinghai.a24divine_user.module.order.detail.mvp.ContractOrderDetail;

import static com.yinghai.a24divine_user.constant.ConstOrderStatus.WAITING_PAY;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/28 18:28
 *         Describe：商品订单详情Fragment
 */

public class ProductDetailFragment extends MyBaseMvpFragment<CancelOrderPresenter> implements ContractOrderDetail.IView {

    private TextView mTvPayStatus;
    private Button mBtnStatus, mBtnCancel;
    private TextView mTvOrderId1;
    private TextView mTvTotalPrice, mTvAddress;
    private StringBuilder mStringBuilder;
    private AutoLoadRecyclerView mRecyclerView;
    private ProductDetailAdapter mAdapter;
    public static final String ORDER_BEAN = "ORDER_BEAN";
    private ProductOrderListBean.DataBean.TfOrderListBean orderBean;

    @Override
    protected void initToolbarTitle() {
        setToolbarTitle(getString(R.string.order_detail));
    }

    @Override
    protected CancelOrderPresenter createPresenter() {
        return new CancelOrderPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_order_detail;
    }

    public static ProductDetailFragment newInstance(ProductOrderListBean.DataBean.TfOrderListBean bean) {
        Bundle args = new Bundle();
        args.putParcelable(ORDER_BEAN, bean);
        ProductDetailFragment fragment = new ProductDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        mBtnCancel = findMyViewId(R.id.btn_cancel);
        mTvPayStatus = findMyViewId(R.id.tv_pay_status);
        mTvAddress = findMyViewId(R.id.tv_address);
        mBtnStatus = findMyViewId(R.id.btn_status);
        mTvOrderId1 = findMyViewId(R.id.tv_orderId1);
        mTvTotalPrice = findMyViewId(R.id.tv_order_amount);
        initRecyclerView();
        return rootView;
    }

    private void initRecyclerView() {
        mRecyclerView = findMyViewId(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(hostActivity));
        mRecyclerView.setOnPauseListenerParams(true, true);
        mAdapter = new ProductDetailAdapter(hostActivity);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        orderBean = getArguments().getParcelable(ORDER_BEAN);
        if (orderBean != null) {
            mAdapter.fillList(orderBean.getOrderList());
            mStringBuilder = new StringBuilder();
            mStringBuilder.append(String.format(getString(R.string.money_unit), orderBean.getTAmount() / 100));
            switch (orderBean.gettStatus()) {
                case WAITING_PAY:
                    mBtnStatus.setVisibility(View.VISIBLE);
                    mBtnCancel.setVisibility(View.VISIBLE);
                    mBtnStatus.setText(getString(R.string.go_pay));
                    mTvPayStatus.setText(getString(R.string.wait_pay));
                    break;
                case ConstOrderStatus.HAS_BEEN_PAID:
                    mTvPayStatus.setText(getString(R.string.been_paid));
                    mBtnStatus.setVisibility(View.VISIBLE);
                    break;
                case ConstOrderStatus.HAS_BEEN_CONFIRM:
                    mTvPayStatus.setText(getString(R.string.been_confirm));
                    mBtnStatus.setVisibility(View.VISIBLE);
                    break;
                case ConstOrderStatus.COMPLETED:
                    mTvPayStatus.setText(getString(R.string.been_completed));
                    break;
                case ConstOrderStatus.REFUND_ING:
                    mTvPayStatus.setText(getString(R.string.refunding));
                    break;
                case ConstOrderStatus.REFUND:
                    mTvPayStatus.setText(getString(R.string.been_refund));
                    break;
                case ConstOrderStatus.CANCELED:
                    mTvPayStatus.setText(getString(R.string.been_cancel));
                    break;
                default:
                    break;
            }
            mStringBuilder.setLength(0);
            mStringBuilder.append(String.format(getString(R.string.order_id), orderBean.getTotalId()));
            mTvOrderId1.setText(mStringBuilder.toString());
            mTvTotalPrice.setText(String.valueOf(orderBean.getTAmount() / 100));
            mStringBuilder.setLength(0);
            if (orderBean.getAddress() != null) {
                mStringBuilder.append(orderBean.getAddress().getAsStreet()).append(orderBean.getAddress().getAsDetails());
                mTvAddress.setText(mStringBuilder.toString());
            }
        }
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mBtnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (orderBean.gettStatus() == WAITING_PAY) {
                    //参数2订单号；参数3商品；参数4总价
                    mMultiFragmentListener.onMultiFragment(ConFragment.OPEN_PAY_WINDOW, orderBean.getTOrderNo(), 3, orderBean.getTAmount());
                } else {
                    DoubleDialog.newInstance(getString(R.string.sure_cancel)).setConfirmListener(new IConfirmListener() {
                        @Override
                        public void onConfirm() {
                            showLoading();
                            mPresenter.onCancelOrder(orderBean.getTotalId(), 2);
                        }
                    }).show(getFragmentManager());
                }
            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoubleDialog.newInstance(getString(R.string.sure_cancel)).setConfirmListener(new IConfirmListener() {
                    @Override
                    public void onConfirm() {
                        showLoading();
                        mPresenter.onCancelOrder(orderBean.getTotalId(), 2);
                    }
                }).show(getFragmentManager());
            }
        });
    }

    @Override
    public void showCancelSuccess(NoDataBean bean) {
        mBtnStatus.setVisibility(View.GONE);
        mBtnCancel.setVisibility(View.GONE);
        mTvPayStatus.setText(getString(R.string.been_cancel));
        ShowToast.singleShort(getString(R.string.cancel_success));
        SubjectHelper.notifyObserverUpdate(ConstOrderStatus.CANCELED);
        hideLoading();
    }

    @Override
    public void showCancelFailure(String errorMsg) {
        ShowToast.singleShort(errorMsg);
        hideLoading();
    }

    @Override
    public void showCompleteSuccess() {
        mBtnStatus.setVisibility(View.GONE);
        mTvPayStatus.setText(getString(R.string.been_completed));
        SubjectHelper.notifyObserverUpdate(ConstOrderStatus.COMPLETED);
    }

    @Override
    public void showCompleteFailure(String errorMsg) {

    }
}
