package com.yinghai.a24divine_user.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fansonlib.base.BaseFragment;
import com.yinghai.a24divine_user.callback.IBackFragmentListener;
import com.yinghai.a24divine_user.callback.OnFragmentListener;
import com.yinghai.a24divine_user.callback.OnMultiFragmentListener;
import com.yinghai.a24divine_user.constant.ConFragment;

/**
 * Created by：fanson
 * Created on：2017/10/24 13:30
 * Describe：本项目BaseFragment
 */

public abstract class MyBaseFragment extends BaseFragment{

    public OnFragmentListener mFragmentListener;
    public IBackFragmentListener mIBackFragmentListener;
    public OnMultiFragmentListener mMultiFragmentListener;
    /**
     * 当前Fragment是否隐藏状态
     */
    protected boolean mIsHide = false;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mFragmentListener = (OnFragmentListener) activity;
        mIBackFragmentListener = (IBackFragmentListener) activity;
        mMultiFragmentListener = (OnMultiFragmentListener) activity;
    }

    protected  void setToolbarTitle(String title){
        if (mMultiFragmentListener!=null){
            mMultiFragmentListener.onMultiFragment(ConFragment.SET_TOOLBAR_TITLE,title);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        mIBackFragmentListener.currentFragmentBack(this);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        initToolbarTitle();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mIsHide = hidden;
        if (!hidden){
            initToolbarTitle();
        }
    }

    /**
     * 设置Toolbar标题
     */
    protected abstract void initToolbarTitle();

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
//        SampleApplicationLike.getRefWatcher().watch(this);
    }
}
