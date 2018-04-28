package com.yinghai.a24divine_user.module.shop;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseActivity;
import com.yinghai.a24divine_user.bean.ProductDetailBean;
import com.yinghai.a24divine_user.callback.IBackFragmentListener;
import com.yinghai.a24divine_user.module.shop.details.ProductDetailFragment;
import com.yinghai.a24divine_user.module.shop.shopcar.ShopCarFragment;
import com.yinghai.a24divine_user.utils.MyStatusBarUtil;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/26 18:06
 *         Describe：购物Activity
 */

public class ShopActivity extends MyBaseActivity implements IBackFragmentListener, ProductDetailFragment.OnDetailFragmentCallback {

    private ImageView mBtnBack;
    private ShopCarFragment mShopCarFragment;
    private ProductDetailFragment mProductDetailFragment;
    public static int mType; // 标识打开购物车还是商品详情
    public static final int TYPE_SHOP_CAR = 1; //购物车
    public static final int TYPE_PRODUCT_DETAIL = 2; //商品详情
    private  static int mShowDetailsForProductId;

    @Override
    protected int getContentView() {
        return R.layout.activity_shop_car;
    }

    /**
     * 跳转到这界面
     * @param activity
     * @param type 类型：打开购物车or商品详情 1or2
     * @param productId 商品ID
     */
    public static void startShopActivity(Activity activity, int type,int productId) {
        mType = type;
        mShowDetailsForProductId = productId;
        Intent intent = new Intent(activity, ShopActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void initView() {
        super.initView();
        mBtnBack = findMyViewId(R.id.btn_back_toolbar);
        chooseOpenFragment();
    }

    private void chooseOpenFragment() {
        switch (mType) {
            case TYPE_SHOP_CAR:
                initShopCarFragment();
                overridePendingTransition(R.anim.scale_in_from_right_bottom,R.anim.scale_in_from_left_bottom);
                break;
            case TYPE_PRODUCT_DETAIL:
                initProductDetailFragment(mShowDetailsForProductId);
                overridePendingTransition(R.anim.scale_in_from_center,R.anim.scale_out);
                break;
            default:
                break;
        }
    }

    @Override
    protected void initData() {
        MyStatusBarUtil.setDrawableStatus(this, R.drawable.shape_toolbar_bg);
    }

    @Override
    protected void listenEvent() {
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopActivity.this.finish();
                overridePendingTransition(R.anim.scale_in_from_right_bottom,R.anim.scale_out_to_right_bottom);
            }
        });
    }

    private void initShopCarFragment() {
        if (mShopCarFragment == null) {
            mShopCarFragment = new ShopCarFragment();
        }
        replaceFragmentToStack(R.id.fl_shop_main, mShopCarFragment, ShopCarFragment.class.getSimpleName());
    }

    private void initProductDetailFragment(int productId) {
        mProductDetailFragment = ProductDetailFragment.newInstance(productId);
        mProductDetailFragment.setOnDetailFragmentCallback(this);
        replaceFragmentToStack(R.id.fl_shop_main, mProductDetailFragment, ProductDetailFragment.class.getSimpleName());
    }

    private void switchShopCarFragment(ProductDetailBean.DataBean.TfProductBean bean) {
        mShopCarFragment = ShopCarFragment.newInstance(bean.getProductId());
        switchFragmentWithTag(R.id.fl_shop_main, getCurrentFragment(), mShopCarFragment, ShopCarFragment.class.getSimpleName());
    }

    @Override
    public void currentFragmentBack(Fragment fragment) {
        mCurrentFragmentList.add(fragment) ;
    }

    @Override
    public void openShopCarFragment(ProductDetailBean.DataBean.TfProductBean bean) {
        switchShopCarFragment(bean);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            onBackPressed();
            overridePendingTransition(R.anim.scale_in_from_right_bottom,R.anim.scale_out_to_right_bottom);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCurrentFragmentList!=null){
            mCurrentFragmentList.clear();
            mCurrentFragmentList = null;
        }
    }
}
