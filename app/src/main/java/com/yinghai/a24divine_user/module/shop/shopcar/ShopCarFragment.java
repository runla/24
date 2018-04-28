package com.yinghai.a24divine_user.module.shop.shopcar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.fansonlib.callback.LoadMoreListener;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.dialogfragment.DoubleDialog;
import com.example.fansonlib.widget.dialogfragment.base.IConfirmListener;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.SampleApplicationLike;
import com.yinghai.a24divine_user.base.MyBaseMvpFragment;
import com.yinghai.a24divine_user.bean.NoDataBean;
import com.yinghai.a24divine_user.bean.ShopCarBean;
import com.yinghai.a24divine_user.bean.SubmitShopCarBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConstAdapter;
import com.yinghai.a24divine_user.module.address.AddressManagerActivity;
import com.yinghai.a24divine_user.module.divine.book.pay.PayWaysWindow;
import com.yinghai.a24divine_user.module.shop.shopcar.mvp.ContractShopCar;
import com.yinghai.a24divine_user.module.shop.shopcar.mvp.ShopCarPresenter;
import com.yinghai.a24divine_user.utils.ClearImageUtils;

/**
 * @author Created by：fanson
 *         Created on：2017/10/26 18:28
 *         Description：商品结账（购物车）
 */

public class ShopCarFragment extends MyBaseMvpFragment<ShopCarPresenter> implements ContractShopCar.IShopCarView, LoadMoreListener, OnAdapterListener, PayWaysWindow.PayCallback {

    private static final int REQUEST_ADDRESS = 78;
    private ShopCarAdapter mAdapter;
    private LinearLayout mRootView;
    private AutoLoadRecyclerView mRecyclerView;
    private boolean isSelectAll = false; //标记是否全选
    private boolean mIsPull = false; //标识是否上拉刷新
    private TextView mTvTotalFee;
    private TextView mTvCarryFee;
    private RadioButton mRbSelectAdd;
    private PayWaysWindow payWaysWindow;
    private Button mBtnPay;
    private static int mProductId;

    public static ShopCarFragment newInstance(int productId) {
        mProductId = productId;
        ShopCarFragment shopCarFragment = new ShopCarFragment();
        return shopCarFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_settlement;
    }

    @Override
    protected void initToolbarTitle() {
        setToolbarTitle(getString(R.string.shop_car));
    }

    @Override
    protected ShopCarPresenter createPresenter() {
        return new ShopCarPresenter(this);
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        mRootView = findMyViewId(R.id.rootView);
        mTvTotalFee = (TextView) view.findViewById(R.id.tv_total_fee);
        mTvCarryFee = (TextView) view.findViewById(R.id.tv_carry_fee);
        mRbSelectAdd = (RadioButton) view.findViewById(R.id.rb_select_all);
        mBtnPay = (Button) view.findViewById(R.id.btn_pay);
        initRecyclerView();
        return rootView;
    }

    private void initRecyclerView() {
        mRecyclerView = findMyViewId(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(hostActivity));
        mRecyclerView.setOnPauseListenerParams(true, true);
        mRecyclerView.setLoadMoreListener(this);
        mAdapter = new ShopCarAdapter(hostActivity);
        mAdapter.setOnAdapterListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        showLoading();
        if (mProductId != 0) {
            mPresenter.addCar(mProductId);
        } else {
            mPresenter.onShopCar();
        }
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mRbSelectAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelectAll) {
                    mRbSelectAdd.setChecked(false);
                    isSelectAll = false;
                } else {
                    mRbSelectAdd.setChecked(true);
                    isSelectAll = true;
                }
                mAdapter.selectAll(isSelectAll);
            }
        });

        mBtnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转选择收货地址
                SampleApplicationLike.isShopToAddress = true;
                startActivityForResult(new Intent(hostActivity, AddressManagerActivity.class), REQUEST_ADDRESS);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //选好收货地址后的回调
        if (resultCode == AddressManagerActivity.RESULT_ADDRESS) {
            if (data.getIntExtra(AddressManagerActivity.ADDRESSID_KEY, 0) == 0) {
                ShowToast.singleShort(getString(R.string.get_address_failure));
            } else {
                showLoading();
                mPresenter.onSubmitShopCar(mAdapter.getSelectedShopList(), data.getIntExtra(AddressManagerActivity.ADDRESSID_KEY, 0));
            }
        }
    }

    @Override
    public void loadMore() {
        mPresenter.onShopCar();
    }

    @Override
    public void showShopCarSuccess(ShopCarBean bean) {
        if (mIsPull) {
            mAdapter.clearList();
            mIsPull = false;
        }
        mRecyclerView.loadFinish(null);
        if (bean.getData() != null && bean.getData().size() > 0) {
            mAdapter.appendList(bean.getData());
        }
        if (mAdapter.getItemCount() == 0) {
            showNoDataLayout();
        }
        hideLoading();
    }

    @Override
    public void showShopCarFailure(String errorMsg) {
        ShowToast.singleShort(errorMsg);
        hideLoading();
    }

    @Override
    public void showAddCarSuccess() {
        //如果在商品详情点击了立即购物，默认会将此商品添加到购物车（网络操作），添加成功后，则再次获取购物车数据
        mPresenter.onShopCar();
    }

    @Override
    public void showAddCarFailure(String errorMsg) {
        hideLoading();
        ShowToast.singleShort(errorMsg);
    }


    @Override
    public void showSubmitSuccess(SubmitShopCarBean bean) {
        hideLoading();
        payWaysWindow = new PayWaysWindow(hostActivity, bean.getData().getTOrderNo(), 3, bean.getData().getTAmount(), this);
        payWaysWindow.showPopupWindow();
        mAdapter.clearList();
        mPresenter.resetPage();
        mPresenter.onShopCar();
    }

    @Override
    public void showSubmitFailure(String errorMsg) {
        ShowToast.singleShort(errorMsg);
        hideLoading();
    }

    @Override
    public void showDeleteSuccess(int carId, NoDataBean bean) {
        mAdapter.removeItem(carId);
        ShowToast.singleShort(getString(R.string.delete_success));
        if (mAdapter.getItemCount() == 0) {
            showNoDataLayout();
        }
        hideLoading();
    }

    @Override
    public void showDeleteFailure(String errorMsg) {
        ShowToast.singleShort(errorMsg);
        hideLoading();
    }

    @Override
    public void showEditSuccess(SubmitShopCarBean bean) {

    }

    @Override
    public void showEditFailure(String errorMsg) {
        ShowToast.singleShort(errorMsg);
    }

    @Override
    public void clickItem(final Object... object) {
        switch ((int) object[0]) {
            case ConstAdapter.DELETE_SHOP_CAR:
                DoubleDialog.newInstance(getString(R.string.sure_delete)).setConfirmListener(new IConfirmListener() {
                    @Override
                    public void onConfirm() {
                        showLoading();
                        mPresenter.onDeleteShopCar((Integer) object[1]);
                    }
                }).show(getFragmentManager());
                break;

            case ConstAdapter.SHOP_CAR_FEE:
                mTvTotalFee.setText(String.valueOf(object[1]));
                break;
            default:
                break;
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
        payWaysWindow.dismiss();
        ShowToast.singleShort(getString(R.string.alipay_failure) + errCode);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mProductId = 0;
        ClearImageUtils.recycleBackground(mRootView);
    }
}
