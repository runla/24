package com.yinghai.a24divine_user.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.example.fansonlib.base.BasePresenter;
import com.yinghai.a24divine_user.R;

/**
 * @author Created by：fanson
 * Created on：2017/10/23 17:11
 * Describe：本项目的BaseMvpFragment（带下拉刷新，id：swipeRefresh）
 */

public abstract class BaseMvpSwipeFragment<P extends BasePresenter> extends MyBaseMvpFragment<P> implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected View initView(View view, Bundle bundle) {
         super.initView(view, bundle);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        return rootView;
    }

    /**
     * 停止下拉刷新的动画
     */
    public void stopRefresh() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        pullRefresh();
    }

    /**
     * 下拉刷新获取数据
     */
    protected abstract void pullRefresh();

}
