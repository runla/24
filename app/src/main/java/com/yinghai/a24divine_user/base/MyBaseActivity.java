package com.yinghai.a24divine_user.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.ForceUpdateListener;
import com.example.fansonlib.base.BaseActivity;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.callback.OnFragmentListener;
import com.yinghai.a24divine_user.callback.OnMultiFragmentListener;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConstBroadcastCode;
import com.yinghai.a24divine_user.service.CheckVersionService;
import com.yinghai.a24divine_user.utils.LogUtils;
import com.yinghai.a24divine_user.utils.MyStatusBarUtil;
import com.yinghai.a24divine_user.widget.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by：fanson
 * Created on：2017/10/12 18:14
 * Describe：本项目的BaseActivity
 */

public abstract class MyBaseActivity extends BaseActivity implements OnFragmentListener, OnMultiFragmentListener {

    private static final String TAG = MyBaseActivity.class.getSimpleName();
    /**
     * 记录当前Activity的Fragment
     */
    public List<Fragment> mCurrentFragmentList;
    private LoadingDialog mLoadingDialog;
    public TextView mTvTitle;
    private CheckVersionReceiver mCheckVersionReceiver;
    private  String mUpdateUrl = ConHttp.BASE_URL + "/twentyfour/qRCode/downapk";

    @Override
    protected void initView() {
        setStatus();
        mCurrentFragmentList = new ArrayList<>();
        mTvTitle = findMyViewId(R.id.tv_title_toolbar);
        initReceiver();
    }

    /**
     * 注册接收检测更新版本号的广播
     */
    private void initReceiver() {
        mCheckVersionReceiver = new CheckVersionReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConstBroadcastCode.CHECK_VERSION);
        registerReceiver(mCheckVersionReceiver, filter);
    }

    /**
     * 设置状态栏背景
     */
    protected void setStatus(){
        MyStatusBarUtil.setDrawableStatus(this, R.drawable.shape_toolbar_bg);
    }


    @Override
    public void onBackPressed() {
        //TODO 需要优化
        if ((getSupportFragmentManager().getBackStackEntryCount() > 1 && (mCurrentFragmentList.size() > getSupportFragmentManager().getBackStackEntryCount() - 1))) {
            mCurrentFragmentList.remove((getSupportFragmentManager().getBackStackEntryCount() - 1));
            getSupportFragmentManager().popBackStack();
            popBackStack();
            if (mCurrentFragmentList.size()==0){
                this.finish();
            }
        } else {
            this.finish();
            mCurrentFragmentList.clear();
        }
    }

    /**
     * 触发Fragment回退
     * @return 回退后，栈顶的Fragment的TAG
     */
    protected   String popBackStack(){
        return mCurrentFragmentList.get(mCurrentFragmentList.size()-1).getTag();
    }

    /**
     * 返回当前栈顶的Fragment
     *
     * @return 栈顶的Fragment
     */
    public Fragment getCurrentFragment() {
        if (mCurrentFragmentList != null) {
            return mCurrentFragmentList.get((getSupportFragmentManager().getBackStackEntryCount() - 1));
        }
        return null;
    }

    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog();
        }
        mLoadingDialog.show(getSupportFragmentManager());
    }

    public void hideLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCurrentFragmentList != null) {
            mCurrentFragmentList.clear();
            mCurrentFragmentList = null;
        }
        //注销广播
        if (mCheckVersionReceiver != null) {
            try {
                unregisterReceiver(mCheckVersionReceiver);
            } catch (IllegalArgumentException ex) {
                if (ex.getMessage().contains("Receiver not registered")) {
                    //Ignore this exception
                } else {
                    throw ex;
                }
            }
        }
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
    public void onMultiFragment(Object... object) {
        switch ((int) object[0]) {
            case ConFragment.SET_TOOLBAR_TITLE:
                setToolbarTitle((String) object[1]);
                break;
            default:
                break;
        }
    }

    protected void setToolbarTitle(String title) {
        mTvTitle.setText(title);
    }

    /**
     * 接收到版本更新的广播
     */
    private class CheckVersionReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, Intent intent) {
            String versionDescribe = intent.getStringExtra(CheckVersionService.KEY_VERSION_DESCRIBE);
            boolean isForceUpdate = intent.getBooleanExtra(CheckVersionService.KEY_FORCE_UPDATE, false);

            DownloadBuilder builder = AllenVersionChecker
                    .getInstance()
                    .downloadOnly(crateUIData(versionDescribe))
                    .setForceRedownload(true);
            if (isForceUpdate) {
                builder.setForceUpdateListener(new ForceUpdateListener() {
                    @Override
                    public void onShouldForceUpdate() {
                        LogUtils.e(TAG,"need force update");
                    }
                });
            }
            builder.excuteMission(context);
        }
    }

    /**
     * @important 使用请求版本功能，可以在这里设置downloadUrl
     * 这里可以构造UI需要显示的数据
     * UIData 内部是一个Bundle
     */
    private UIData crateUIData(String versionDescribe) {
        UIData uiData = UIData.create();
        uiData.setTitle(getString(R.string.find_latest_version));
        uiData.setDownloadUrl(mUpdateUrl);
        uiData.setContent(versionDescribe);
        return uiData;
    }

}
