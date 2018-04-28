package com.yinghai.a24divine_user.module.order;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.fansonlib.widget.textview.TextViewDrawable;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseActivity;
import com.yinghai.a24divine_user.callback.IBackFragmentListener;
import com.yinghai.a24divine_user.module.order.all.AllOrderFragment;
import com.yinghai.a24divine_user.module.order.book.BookFragment;
import com.yinghai.a24divine_user.module.order.complete.CompleteOrderFragment;
import com.yinghai.a24divine_user.utils.MyStatusBarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by：fanson
 *         Created on：2017/10/24 16:44
 *         Description：我的订单Activity
 */

public class MyOrderActivity extends MyBaseActivity implements IBackFragmentListener, ChooseOrderTypeWindow.IChooseTypeListener {

    private static final String TAG = MyOrderActivity.class.getSimpleName();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private OrderViewPagerAdapter mVpAdapter;
    private ImageView mBtnBack;
    private List<Fragment> mFragmentList;
    private AllOrderFragment mAllOrderFragment;
    private BookFragment mBookFragment;
    private CompleteOrderFragment mHistoryOrderFragment;
    private TextViewDrawable mTdChooseType;
    private ChooseOrderTypeWindow mChooseTypeWindow;

    @Override
    protected int getContentView() {
        return R.layout.activity_my_order;
    }

    @Override
    protected void initView() {
        super.initView();
        mTabLayout = findMyViewId(R.id.tl_order);
        mViewPager = findMyViewId(R.id.vp_order);
        mBtnBack = findMyViewId(R.id.btn_back_toolbar);
        mTdChooseType = findMyViewId(R.id.td_choose);
        initTabLayout();
    }

    @Override
    protected void initData() {
        mTvTitle.setText(getString(R.string.my_order));
        MyStatusBarUtil.setDrawableStatus(this, R.drawable.shape_toolbar_bg);
    }

    @Override
    protected void listenEvent() {
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyOrderActivity.this.finish();
                overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
            }
        });

        mTdChooseType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mChooseTypeWindow==null){
                    mChooseTypeWindow = new ChooseOrderTypeWindow(MyOrderActivity.this,MyOrderActivity.this);
                }
                if (mChooseTypeWindow.isShowing()){
                    mChooseTypeWindow.dismiss();
                }else {
                    mChooseTypeWindow.showPopupWindow(view);
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initTabLayout() {
        if (mVpAdapter == null) {
            //Add Fragment
            mFragmentList = new ArrayList<>();
            mAllOrderFragment = new AllOrderFragment();
            mBookFragment = new BookFragment();
            mHistoryOrderFragment = new CompleteOrderFragment();
            mFragmentList.add(mAllOrderFragment);
            mFragmentList.add(mBookFragment);
            mFragmentList.add(mHistoryOrderFragment);
            //Add Tab Title
            List<String> titleList = new ArrayList<>();
            titleList.add(getString(R.string.all));
            titleList.add(getString(R.string.to_be_complete));
            titleList.add(getString(R.string.completed));
            mVpAdapter = new OrderViewPagerAdapter(getSupportFragmentManager(), mFragmentList, titleList);
            mViewPager.setOffscreenPageLimit(2);
            mViewPager.setAdapter(mVpAdapter);
            mTabLayout.setupWithViewPager(mViewPager);
            mTabLayout.setTabMode(TabLayout.GRAVITY_FILL);
        }
    }

    @Override
    public void currentFragmentBack(Fragment fragment) {
        mCurrentFragmentList.add(fragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCurrentFragmentList != null) {
            mCurrentFragmentList.clear();
            mCurrentFragmentList = null;
        }
        if (mFragmentList != null) {
            mFragmentList.clear();
            mFragmentList = null;
        }
        if (mChooseTypeWindow!=null){
            mChooseTypeWindow.removeListener();
            mChooseTypeWindow = null;
        }
        mAllOrderFragment = null;
        mBookFragment = null;
        mHistoryOrderFragment = null;
    }

    /**
     * 帅选类型的回调
     * @param type 结果
     */
    @Override
    public void onChooseType(int type) {
        switch (type) {
            //占卜
            case ChooseOrderTypeWindow.TYPE_DIVINE:
                mTdChooseType.setText(getString(R.string.divine));
                mAllOrderFragment.resetDataType(AllOrderFragment.TYPE_DIVINE);
                mBookFragment.resetDataType(AllOrderFragment.TYPE_DIVINE);
                mHistoryOrderFragment.resetDataType(AllOrderFragment.TYPE_DIVINE);
                break;
            //商品
            case ChooseOrderTypeWindow.TYPE_PRODUCT:
                mTdChooseType.setText(getString(R.string.string_shop));
                mAllOrderFragment.resetDataType(AllOrderFragment.TYPE_PRODUCT);
                mBookFragment.resetDataType(AllOrderFragment.TYPE_PRODUCT);
                mHistoryOrderFragment.resetDataType(AllOrderFragment.TYPE_PRODUCT);
                break;
            default:
                break;

        }
    }
}
