package com.yinghai.a24divine_user.module.shop.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fansonlib.callback.LoadMoreListener;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;
import com.yinghai.a24divine_user.Impl.BannerImageLoader;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseMvpFragment;
import com.yinghai.a24divine_user.bean.CollectBean;
import com.yinghai.a24divine_user.bean.CommentBean;
import com.yinghai.a24divine_user.bean.PictureBean;
import com.yinghai.a24divine_user.bean.ProductDetailBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.function.previewImage.PhotoActivity;
import com.yinghai.a24divine_user.module.login.state.LoginStateManager;
import com.yinghai.a24divine_user.utils.ClearImageUtils;
import com.yinghai.a24divine_user.utils.LogUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import static com.yinghai.a24divine_user.module.setting.SettingFragment.IMAGE_PICKER;

/**
 * @author Created by：fanson
 *         Created on：2017/10/27 09:33
 *         Describe：商品详情的Fragment
 */

public class ProductDetailFragment extends MyBaseMvpFragment<DetailsPresenter> implements ContractDetails.ICommentsView, LoadMoreListener {

    private static final String TAG = ProductDetailFragment.class.getSimpleName();
    private static final String PARAM_PRODUCT_ID = "ProductId";
    /**
     * 记录商品ID的显示商品详情
     */
    private int mShowDetailForProductId;
    private ProductDetailBean.DataBean.TfProductBean mBean;
    private Banner mBanner;
    private TextView mTvDescribe;
    private TextView mTvName;
    private TextView mTvPrice;
    private TextView mTvAddress;
    private TextView mTvSold;
    private TextView mTvShipping;
    private LinearLayout mRootView;
    private TextView mTvBuyNow;
    private TextView mTvAddToCar;
    private TextView mTvAddToLike;

    private AutoLoadRecyclerView mAutoLoadRecyclerView;
    private CommentsAdapter mAdapter;

    private OnDetailFragmentCallback onDetailFragmentCallback;

    public void setOnDetailFragmentCallback(OnDetailFragmentCallback onDetailFragmentCallback) {
        this.onDetailFragmentCallback = onDetailFragmentCallback;
    }

    @Override
    protected DetailsPresenter createPresenter() {
        return new DetailsPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_detail;
    }

    public static ProductDetailFragment newInstance(Bundle args) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ProductDetailFragment newInstance(int productId) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PARAM_PRODUCT_ID, productId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initToolbarTitle() {
        setToolbarTitle(getString(R.string.shop_details));
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        mBanner = findMyViewId(R.id.banner_product);
        mTvDescribe = findMyViewId(R.id.tv_product_describe);
        mTvPrice = findMyViewId(R.id.tv_shop_price);
        mTvName = findMyViewId(R.id.tv_shop_name);
        mTvAddress = findMyViewId(R.id.tv_shop_address);
        mTvSold = findMyViewId(R.id.tv_shop_pay_num);
        mRootView = findMyViewId(R.id.rootView);
        mTvShipping = findMyViewId(R.id.tv_shop_shipping);
        mTvBuyNow = findMyViewId(R.id.tv_buy_now);
        mTvAddToCar = findMyViewId(R.id.tv_add_car);
        mTvAddToLike = findMyViewId(R.id.tv_like);
        initRecyclerView();
        return rootView;
    }

    private void initRecyclerView() {
        mAutoLoadRecyclerView = findMyViewId(R.id.recyclerview);
        mAutoLoadRecyclerView.setLayoutManager(new LinearLayoutManager(hostActivity));
        mAutoLoadRecyclerView.setNestedScrollingEnabled(false);
        mAutoLoadRecyclerView.setOnPauseListenerParams(true, true);
        mAutoLoadRecyclerView.addItemDecoration(new DividerItemDecoration(hostActivity, DividerItemDecoration.VERTICAL));
        mAutoLoadRecyclerView.setLoadMoreListener(this);
        mAdapter = new CommentsAdapter(hostActivity);
        mAutoLoadRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        showLoading();
        mShowDetailForProductId = getArguments().getInt(PARAM_PRODUCT_ID);
        mPresenter.onDetails(mShowDetailForProductId);
    }

    /**
     * 加载数据到这个视图
     *
     * @param mBean
     */
    private void setDataInView(ProductDetailBean.DataBean.TfProductBean mBean) {
        if (mBean.getImgList() != null) {
            List<String> imgList = new ArrayList<>();
            for (int i = 0; i < mBean.getImgList().size(); i++) {
                imgList.add(ConHttp.BASE_URL + mBean.getImgList().get(i).getItUrl());
            }
            mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR).setImageLoader(new BannerImageLoader()).setImages(imgList).start();
        }
        //TODO 是否已收藏
//            if (mBean()) {
//                mTvAddToLike.setSelected(true);
//            }
        mTvDescribe.setText(mBean.getPIntroduction());
        mTvPrice.setText(String.format(getString(R.string.money_unit), mBean.getPPrice() / 100));
        mTvName.setText(mBean.getPName());
        mTvAddress.setText(mBean.getPAttribution());
        mTvSold.setText(String.format(getString(R.string.sold), mBean.getPDeals()));
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mTvBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginStateManager.getInstance().onShopping(hostActivity)) {
                    if (mBean.getPTotal() > 0) {
                        onDetailFragmentCallback.openShopCarFragment(mBean);
                    } else {
                        ShowToast.singleShort(getString(R.string.sold_out));
                    }
                }
            }
        });

        mTvAddToCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginStateManager.getInstance().onShopping(hostActivity)) {
                    if (mBean.getPTotal() > 0) {
                        showLoading();
                        mPresenter.onAddToCar(mBean.getProductId(), 1);
                    } else {
                        ShowToast.singleShort(getString(R.string.sold_out));
                    }
                }
            }
        });

        mTvAddToLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginStateManager.getInstance().onShopping(hostActivity)) {
                    showLoading();
                    if (mTvAddToLike.isSelected()) {
                        mPresenter.onCancelCollect(3, mBean.getProductId());
                    } else {
                        mPresenter.onCollect(3, mBean.getProductId());
                    }
                }
            }
        });

        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ArrayList<PictureBean> mPictureList = new ArrayList<PictureBean>();
                for (int i = 0; i < mBean.getImgList().size(); i++) {
                    PictureBean bean = new PictureBean();
                    bean.setUrlPicture(ConHttp.BASE_URL + mBean.getImgList().get(i).getItUrl());
                    mPictureList.add(bean);
                }
                Intent intent = new Intent(hostActivity, PhotoActivity.class);
                intent.putParcelableArrayListExtra("imagePaths", mPictureList);
                intent.putExtra("position", position);
                intent.putExtra(PhotoActivity.IS_NEED_TOOLBAR, false);
                startActivityForResult(intent, IMAGE_PICKER);
            }
        });

    }

    private void getCommentsData() {
        mPresenter.onComments(mBean.getProductId());
    }


    @Override
    public void onGetCommentsFailure(String msg) {
        mAutoLoadRecyclerView.loadFinish(null);
    }

    @Override
    public void onGetCommentsSuccess(CommentBean.DataBean bean) {
        mAutoLoadRecyclerView.loadFinish(null);
        if (bean.getComments() != null && bean.getComments().size() > 0) {
            mAdapter.appendList(bean.getComments());
        }
        if (mAdapter.getItemCount() == 0) {
            View viewNoData = LayoutInflater.from(getContext()).inflate(R.layout.layout_no_data, mAutoLoadRecyclerView, false);
            mAdapter.addHeaderView(viewNoData);
        }
        hideLoading();
    }

    @Override
    public void showDetailFailure(String msg) {
        ShowToast.singleShort(msg);
        hideLoading();
    }

    @Override
    public void showDetailSuccess(ProductDetailBean.DataBean bean) {
        LogUtils.d(TAG, "获取商品详情成功");
        hideLoading();
        mBean = bean.getTfProduct();
        setDataInView(bean.getTfProduct());
        getCommentsData();
    }

    @Override
    public void showCollectFailure(String msg) {
        ShowToast.singleShort(msg);
        hideLoading();
    }

    @Override
    public void showCollectSuccess(CollectBean bean) {
        hideLoading();
        ShowToast.singleShort(getString(R.string.collect_add_success));
        mTvAddToLike.setSelected(true);
    }

    @Override
    public void showCancelCollectSuccess(CollectBean bean) {
        hideLoading();
        ShowToast.singleShort(getString(R.string.has_success_cancel_collect));
        mTvAddToLike.setSelected(false);
    }

    @Override
    public void showCancelCollectFailure(String msg) {
        ShowToast.singleShort(msg);
        hideLoading();
    }

    @Override
    public void showAddToCarSuccess() {
        ShowToast.singleShort(getString(R.string.add_to_car_success));
        mTvAddToCar.setSelected(true);
        hideLoading();
    }

    @Override
    public void showAddToCarFailure(String errorMsg) {
        ShowToast.singleShort(errorMsg);
        hideLoading();
    }


    @Override
    public void loadMore() {
        getCommentsData();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ClearImageUtils.recycleBackground(mRootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBanner.stopAutoPlay();
        mBanner = null;
    }

    /**
     * 打开购物车回调接口
     */
    public interface OnDetailFragmentCallback {
        /**
         * 打开购物车
         *
         * @param bean 数据
         */
        void openShopCarFragment(ProductDetailBean.DataBean.TfProductBean bean);
    }
}
