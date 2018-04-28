package com.yinghai.a24divine_user.module.shop.shoplist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.fansonlib.callback.LoadMoreListener;
import com.example.fansonlib.utils.InputMethodUtils;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.BaseMvpSwipeFragment;
import com.yinghai.a24divine_user.bean.ProductBean;
import com.yinghai.a24divine_user.module.login.state.LoginStateManager;
import com.yinghai.a24divine_user.module.shop.ShopActivity;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;

/**
 * @author Created by：fanson
 *         Created on：2017/10/24 13:28
 *         Describe：商城Fragment
 */

public class ShopFragment extends BaseMvpSwipeFragment<ShopPresenter> implements ContractShop.IGetAllProductView, ShopAdapter.IShopAdapterListener, LoadMoreListener {

    private ShopAdapter mAdapter;
    private FloatingActionButton mFabShopCar;
    private ImageView mIvSearch, mIvClear;
    private EditText mEtSearch;
    private boolean mIsSearch = false;
    private AutoLoadRecyclerView mRecyclerView;
    private String mStrSearchContent = "";
    private static final int PAGE_SIZE = 10;
    /**
     * 当显示搜索数据时，用作暂时存储商品列表数据
     */
    private ArrayList<ProductBean.DataBean.ProductListBean> mTempProductListBean = new ArrayList<>();
    /**
     * 标识是否上拉刷新
     */
    private boolean mIsPull = false;
    /**
     * 标识数据是否已全部加载完毕
     */
    private boolean mIsLoadComplete = false;

    @Override
    protected ShopPresenter createPresenter() {
        return new ShopPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        initRecyclerView();
        mFabShopCar = findMyViewId(R.id.fab_shop_car);
        mIvSearch = findMyViewId(R.id.iv_search);
        mIvClear = findMyViewId(R.id.iv_clear);
        mEtSearch = findMyViewId(R.id.et_search);
        return rootView;
    }

    @Override
    protected void initToolbarTitle() {

    }

    private void initRecyclerView() {
        mRecyclerView = findMyViewId(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(hostActivity));
        mRecyclerView.setOnPauseListenerParams(true, true);
        mRecyclerView.setLoadMoreListener(this);
        mAdapter = new ShopAdapter(hostActivity);
        mAdapter.setIAdapterListener(this);
        SlideInBottomAnimationAdapter slideAdapter = new SlideInBottomAnimationAdapter(mAdapter);
        slideAdapter.setDuration(800);
        slideAdapter.setFirstOnly(false);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(slideAdapter);
        scaleAdapter.setDuration(800);
        scaleAdapter.setFirstOnly(false);
        mRecyclerView.setAdapter(scaleAdapter);
    }

    @Override
    protected void initData() {
        showLoading();
        getProductData(mStrSearchContent);
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mFabShopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginStateManager.getInstance().onShopping(hostActivity)) {
                    ShopActivity.startShopActivity(hostActivity, ShopActivity.TYPE_SHOP_CAR, 0);
                }
            }
        });

        mIvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowToast.singleShort(getString(R.string.loading));
                mIsSearch = true;
                mIsLoadComplete = false;
                mPresenter.saveTempPageNum();
                mPresenter.resetPage();
                getProductData(mStrSearchContent);
                InputMethodUtils.hideSoftInput(hostActivity);
            }
        });

        mIvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEtSearch.setText("");
                mIvClear.setVisibility(View.GONE);
            }
        });

        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mStrSearchContent = editable.toString();
                if (editable.toString().length() != 0) {
                    mIvClear.setVisibility(View.VISIBLE);
                } else {
                    mIvClear.setVisibility(View.GONE);
                    mIsSearch = false;
                    mIsLoadComplete = false;
                    mAdapter.clearList();
                    mAdapter.appendList(mTempProductListBean);
                    mPresenter.setTempPageNum();
                }
            }
        });
    }

    private void getProductData(String productName) {
        //若是顶部下拉刷新或者数据未加载完毕，才进行数据获取
        if (!mIsLoadComplete) {
            mPresenter.onGetAllProduct(productName);
        }
    }

    @Override
    public void showAllProductFailure(String msg) {
        ShowToast.singleShort(msg);
        stopRefresh();
        hideLoading();
    }

    @Override
    public void showAllProductSuccess(ProductBean bean) {
        if (mIsSearch) {
            mAdapter.clearList();
        }
        if (mIsPull) {
            mAdapter.clearList();
            mIsPull = false;
        }
        mRecyclerView.loadFinish(null);
        if (bean.getData().getProductList() != null && bean.getData().getProductList().size() > 0) {
            hideNoDataLayout();
            mAdapter.appendList(bean.getData().getProductList());
            //防止搜索数据覆盖暂存的初始数据
            if (!mIsSearch) {
                mTempProductListBean.addAll(bean.getData().getProductList());
            }
            mIsSearch = false;
            if (bean.getData().getProductList().size() < PAGE_SIZE) {
                mIsLoadComplete = true;
            }
        } else {
            mIsLoadComplete = true;
        }
        if (mAdapter.getItemCount() == 0) {
            showNoDataLayout();
        }
        stopRefresh();
        hideLoading();
    }

    @Override
    protected void pullRefresh() {
        mIsPull = true;
        mIsLoadComplete = false;
        mPresenter.resetPage();
        getProductData(mStrSearchContent);
    }

    /**
     * 点击shopAdapter的列表
     *
     * @param bean 数据
     */
    @Override
    public void clickItem(ProductBean.DataBean.ProductListBean bean) {
        ShopActivity.startShopActivity(hostActivity, ShopActivity.TYPE_PRODUCT_DETAIL, bean.getProductId());
    }

    @Override
    public void loadMore() {
        getProductData(mStrSearchContent);
    }

}
