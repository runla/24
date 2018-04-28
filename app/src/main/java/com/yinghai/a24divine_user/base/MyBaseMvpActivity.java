package com.yinghai.a24divine_user.base;

import android.support.v4.content.ContextCompat;

import com.example.fansonlib.base.BaseMvpActivity;
import com.example.fansonlib.base.BasePresenter;
import com.example.fansonlib.utils.StatusBarUtil;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.callback.OnFragmentListener;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.widget.LoadingDialog;

/**
 * Created by：fanson
 * Created on：2017/10/17 14:57
 * Describe：本项目的MyBaseMvpActivity
 */

public abstract class MyBaseMvpActivity<P extends BasePresenter> extends BaseMvpActivity<P> implements OnFragmentListener {

    private LoadingDialog mLoadingDialog;

    @Override
    protected void initView() {
        StatusBarUtil.setStatusBarColor(this, ContextCompat.getColor(this, R.color.primary));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        SampleApplicationLike.getRefWatcher().watch(this);
    }

    @Override
    public void onFragment(Object object) {
        switch ((int) object) {
            case ConFragment.SHOW_LOADING:
                showLoading();
                break;
            case ConFragment.HIDE_LOADING:
                hideLoading();
                break;
            default:
                break;
        }
    }

    @Override
    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog();
        }
        mLoadingDialog.show(getSupportFragmentManager());
    }

    @Override
    public void hideLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

}
