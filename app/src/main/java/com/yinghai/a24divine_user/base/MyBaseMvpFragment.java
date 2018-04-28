package com.yinghai.a24divine_user.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import com.example.fansonlib.base.BaseMvpFragment;
import com.example.fansonlib.base.BasePresenter;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.callback.IBackFragmentListener;
import com.yinghai.a24divine_user.callback.OnFragmentListener;
import com.yinghai.a24divine_user.callback.OnMultiFragmentListener;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.manager.MyFragmentManager;

/**
 * @author Created by：fanson
 *         Created on：2017/10/24 13:18
 *         Describe：本项目的BaseMvpFragment
 */

public abstract class MyBaseMvpFragment<P extends BasePresenter> extends BaseMvpFragment<P> implements MyFragmentManager.OnBackClickListener {

    public OnFragmentListener mFragmentListener;
    public OnMultiFragmentListener mMultiFragmentListener;
    private IBackFragmentListener mIBackFragmentListener;
    private ViewStub mViewStub;
    /**
     * 当前Fragment是否隐藏状态
     */
    protected boolean mIsHide = false;
    /**
     * 记录Fragment是否被销毁视图
     */
    protected boolean isDestroyView = false;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mFragmentListener = (OnFragmentListener) activity;
        mIBackFragmentListener = (IBackFragmentListener) activity;
        mMultiFragmentListener = (OnMultiFragmentListener) activity;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        isDestroyView = false;
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mIBackFragmentListener.currentFragmentBack(this);
        initToolbarTitle();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mIsHide = hidden;
        if (!hidden) {
            initToolbarTitle();
        }
    }

    /**
     * 设置Toolbar标题
     */
    protected abstract void initToolbarTitle();

    protected void setToolbarTitle(String title) {
        if (mMultiFragmentListener != null) {
            mMultiFragmentListener.onMultiFragment(ConFragment.SET_TOOLBAR_TITLE, title);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mFragmentListener = null;
        mIBackFragmentListener = null;
        mMultiFragmentListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isDestroyView = true;
    }

    @Override
    public void showLoading() {
        if (mFragmentListener != null) {
            mFragmentListener.onFragment(ConFragment.SHOW_LOADING);
        }
    }

    @Override
    public void hideLoading() {
        if (mFragmentListener != null) {
            mFragmentListener.onFragment(ConFragment.HIDE_LOADING);
        }
    }

    @Override
    public boolean onBackClick() {
        if (getFragmentManager() == null) {
            return false;
        }else {
            return MyFragmentManager.popTopFragment(getFragmentManager());
        }
    }

    /**
     * 显示无数据界面
     */
    public void showNoDataLayout() {
        if (mViewStub == null) {
            mViewStub = (ViewStub) hostActivity.findViewById(R.id.vs_no_data);
        }
        if (mViewStub != null) {
            mViewStub.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏无数据界面
     */
    public void hideNoDataLayout() {
        if (mViewStub != null) {
            mViewStub.setVisibility(View.GONE);
        }
    }
}
