package com.yinghai.a24divine_user.module.order.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.fansonlib.utils.ShowToast;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseActivity;
import com.yinghai.a24divine_user.bean.OrderBean;
import com.yinghai.a24divine_user.bean.ProductOrderListBean;
import com.yinghai.a24divine_user.callback.IBackFragmentListener;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.module.divine.book.pay.PayWaysWindow;
import com.yinghai.a24divine_user.module.order.detail.divine.DivineDetailFragment;
import com.yinghai.a24divine_user.module.order.detail.product.ProductDetailFragment;
import com.yinghai.a24divine_user.utils.MyStatusBarUtil;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/18 18:06
 *         Describe：占卜订单详情Activity
 */

public class OrderDetailsActivity extends MyBaseActivity implements IBackFragmentListener, PayWaysWindow.PayCallback {

    public static final String ORDER_BEAN = "ORDER_BEAN";
    private ImageView mBtnBack;

    private DivineDetailFragment mDivineDetailFragment;
    private ProductDetailFragment mProductDetailFragment;

    public static int mType = 1; // 标识打开文章列表还是文章详情
    public static final int TYPE_PRODUCT = 1; //商品
    public static final int TYPE_DIVINE = 2; //占卜
    private  OrderBean.DataBean.TfOrderListBean mOrderBean;

    @Override
    protected int getContentView() {
        return R.layout.activitiy_order_detail;
    }

    /**
     * 打开订单详情界面
     * @param activity
     * @param type 1：商品； 2：占卜
     * @param bean 实体
     */
    public static void startOrderDetailsActivity(Activity activity, int type, OrderBean.DataBean.TfOrderListBean bean) {
        mType = type;
        Intent intent = new Intent(activity, OrderDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(ORDER_BEAN, bean);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    /**
     * 打开订单详情界面
     * @param activity
     * @param type 1：商品； 2：占卜
     * @param bean 实体
     */
    public static void startOrderDetailsActivity(Activity activity, int type, ProductOrderListBean.DataBean.TfOrderListBean bean) {
        mType = type;
        Intent intent = new Intent(activity, OrderDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(ORDER_BEAN, bean);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    protected void initView() {
        super.initView();
        mBtnBack = findMyViewId(R.id.btn_back_toolbar);
        mTvTitle = findMyViewId(R.id.tv_title_toolbar);
    }

    @Override
    protected void initData() {
        MyStatusBarUtil.setDrawableStatus(this, R.drawable.shape_toolbar_bg);
        if (mType == TYPE_DIVINE) {
            initDivineDetailFragment((OrderBean.DataBean.TfOrderListBean) getIntent().getExtras().getParcelable(ORDER_BEAN));
        } else {
            initProductDetailFragment((ProductOrderListBean.DataBean.TfOrderListBean) getIntent().getExtras().getParcelable(ORDER_BEAN));
        }
    }

    private void initDivineDetailFragment(OrderBean.DataBean.TfOrderListBean bean) {
        mDivineDetailFragment = DivineDetailFragment.newInstance(bean);
        replaceFragmentToStack(R.id.fl_detail_main, mDivineDetailFragment, DivineDetailFragment.class.getSimpleName());
    }

    private void initProductDetailFragment(ProductOrderListBean.DataBean.TfOrderListBean bean) {
        mProductDetailFragment = ProductDetailFragment.newInstance(bean);
        replaceFragmentToStack(R.id.fl_detail_main, mProductDetailFragment, ProductDetailFragment.class.getSimpleName());
    }


    @Override
    protected void listenEvent() {
        // 返回
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onMultiFragment(Object... object) {
        super.onMultiFragment(object);
        switch ((Integer)object[0]){
            case ConFragment.OPEN_PAY_WINDOW:
                PayWaysWindow payWaysWindow = new PayWaysWindow(this, (String) object[1],(Integer) object[2],(Integer) object[3], this);
                payWaysWindow.showPopupWindow();
                break;
            default:
                break;

        }
    }

    @Override
    public void currentFragmentBack(Fragment fragment) {
        mCurrentFragmentList.add(fragment) ;
    }

    @Override
    public void showPayLoading() {

    }

    @Override
    public void paySuccess() {
        ShowToast.singleShort(getString(R.string.pay_success_to_order_see));
    }

    @Override
    public void payFailure(int errCode) {
        ShowToast.singleShort(getString(R.string.alipay_failure) + errCode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCurrentFragmentList!=null){
            mCurrentFragmentList.clear();
            mCurrentFragmentList = null;
        }
        mDivineDetailFragment = null;
        mProductDetailFragment = null;
    }
}
