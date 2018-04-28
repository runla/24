package com.yinghai.a24divine_user.module.order.detail.divine;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.dialogfragment.DoubleDialog;
import com.example.fansonlib.widget.dialogfragment.base.IConfirmListener;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.module.order.SubjectHelper;
import com.yinghai.a24divine_user.base.MyBaseMvpFragment;
import com.yinghai.a24divine_user.bean.NoDataBean;
import com.yinghai.a24divine_user.bean.OrderBean;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.constant.ConstOrderStatus;
import com.yinghai.a24divine_user.module.order.detail.mvp.CancelOrderPresenter;
import com.yinghai.a24divine_user.module.order.detail.mvp.ContractOrderDetail;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/28 18:14
 *         Describe：占卜订单详情Fragment
 */

public class DivineDetailFragment extends MyBaseMvpFragment<CancelOrderPresenter> implements ContractOrderDetail.IView {

    public static final String ORDER_BEAN = "ORDER_BEAN";
    private TextView mTvTime;
    private TextView mTvType;
    private TextView mTvPrice;
    private TextView mTvPayStatus;
    private TextView mTvDescribe;
    private TextView mTvName;
    private TextView mTvSex;
    private TextView mTvBusiness;
    private Button mBtnCancel,mBtnCancelOrder;
    private TextView mTvOrderId1;
    private StringBuilder mStringBuilder;
    private OrderBean.DataBean.TfOrderListBean orderBean;

    @Override
    protected CancelOrderPresenter createPresenter() {
        return new CancelOrderPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_divine_detail;
    }

    public static DivineDetailFragment newInstance(OrderBean.DataBean.TfOrderListBean bean) {
        Bundle args = new Bundle();
        args.putParcelable(ORDER_BEAN, bean);
        DivineDetailFragment fragment = new DivineDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        mTvTime = findMyViewId(R.id.tv_time);
        mTvType = findMyViewId(R.id.tv_type);
        mTvPrice = findMyViewId(R.id.tv_price);
        mTvPayStatus = findMyViewId(R.id.tv_pay_status);
        mTvDescribe = findMyViewId(R.id.tv_describe);
        mTvName = findMyViewId(R.id.tv_name);
        mTvSex = findMyViewId(R.id.tv_sex);
        mTvBusiness = findMyViewId(R.id.tv_business);
        mBtnCancel = findMyViewId(R.id.btn_cancel);
        mTvOrderId1 = findMyViewId(R.id.tv_orderId1);
        mBtnCancelOrder = findMyViewId(R.id.btn_cancel_order);
        return rootView;
    }

    @Override
    protected void initToolbarTitle() {
        setToolbarTitle(getString(R.string.order_detail));
    }

    @Override
    protected void initData() {
        orderBean = getArguments().getParcelable(ORDER_BEAN);
        if (orderBean != null) {
            mStringBuilder = new StringBuilder();
            mTvTime.setText(orderBean.getOAppointmentTime());
            mTvType.setText(R.string.online_divine);
            mStringBuilder.append(String.format(getString(R.string.money_unit),orderBean.getOAmount()/100));
            mTvPrice.setText(mStringBuilder.toString());
            switch (orderBean.getOStatus()) {
                case ConstOrderStatus.WAITING_PAY:
                    mBtnCancel.setVisibility(View.VISIBLE);
                    mBtnCancelOrder.setVisibility(View.VISIBLE);
                    mBtnCancel.setText(getString(R.string.go_pay));
                    mTvPayStatus.setText(getString(R.string.wait_pay));
                    break;
                case ConstOrderStatus.HAS_BEEN_PAID:
                    mTvPayStatus.setText(getString(R.string.been_paid));
                    mBtnCancel.setVisibility(View.VISIBLE);
                    break;
                case ConstOrderStatus.HAS_BEEN_CONFIRM:
                    mTvPayStatus.setText(getString(R.string.been_confirm));
                    mBtnCancel.setVisibility(View.VISIBLE);
                    break;
                case ConstOrderStatus.ORDER_ING:
                    mTvPayStatus.setText(getString(R.string.ing));
                    mBtnCancel.setText(R.string.click_complete);
                    mBtnCancel.setVisibility(View.VISIBLE);
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
            if (orderBean.getTfMaster()!=null){
                mTvName.setText(orderBean.getTfMaster().getMNick());
            }
            if (orderBean.getTfOrderAttach()!=null){
                String sex = orderBean.getTfOrderAttach().isAhSex() ? getString(R.string.boy) : getString(R.string.girl);
                mTvSex.setText(sex);
                if (orderBean.getTfOrderAttach().getAhDescribe().length()==0){
                    mTvDescribe.setText(getString(R.string.none));
                }else {
                    mTvDescribe.setText(orderBean.getTfOrderAttach().getAhDescribe());
                }
            }
            if (orderBean.getTfBusiness() != null) {
                mTvBusiness.setText(orderBean.getTfBusiness().getBName());
            }
            mStringBuilder.setLength(0);
            mStringBuilder.append(String.format(getString(R.string.order_id),orderBean.getOrderId()));
            mTvOrderId1.setText(mStringBuilder.toString());
        }
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        // 取消订单
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (orderBean.getOStatus()){
                    case ConstOrderStatus.WAITING_PAY:
                        //参数2订单号；参数3占卜；参数4总价
                        mMultiFragmentListener.onMultiFragment(ConFragment.OPEN_PAY_WINDOW, orderBean.getOOrderNo(), 1, orderBean.getOAmount());
                        break;
                    case ConstOrderStatus.ORDER_ING:
                        showLoading();
                        mPresenter.onCompleteOrder(orderBean.getOrderId());
                        break;
                    default:
                        DoubleDialog.newInstance(getString(R.string.sure_cancel)).setConfirmListener(new IConfirmListener() {
                            @Override
                            public void onConfirm() {
                                showLoading();
                                mPresenter.onCancelOrder(orderBean.getOrderId(),1);
                            }
                        }).show(getFragmentManager());
                        break;
                }
            }
        });

        mBtnCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoubleDialog.newInstance(getString(R.string.sure_cancel)).setConfirmListener(new IConfirmListener() {
                    @Override
                    public void onConfirm() {
                        showLoading();
                        mPresenter.onCancelOrder(orderBean.getOrderId(),1);
                    }
                }).show(getFragmentManager());
            }
        });
    }

    @Override
    public void showCancelSuccess(NoDataBean bean) {
        mTvPayStatus.setText(getString(R.string.been_cancel));
        mBtnCancel.setVisibility(View.GONE);
        mBtnCancelOrder.setVisibility(View.GONE);
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
        mBtnCancel.setVisibility(View.GONE);
        mTvPayStatus.setText(R.string.order_has_complete);
        ShowToast.singleShort(getString(R.string.order_has_complete));
        SubjectHelper.notifyObserverUpdate(ConstOrderStatus.COMPLETED);
        hideLoading();
    }

    @Override
    public void showCompleteFailure(String errorMsg) {
        ShowToast.singleShort(errorMsg);
        hideLoading();
    }

}
