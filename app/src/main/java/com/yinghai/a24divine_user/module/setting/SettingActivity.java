package com.yinghai.a24divine_user.module.setting;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseActivity;
import com.yinghai.a24divine_user.callback.IBackFragmentListener;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.utils.MyStatusBarUtil;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/25 18:19
 *         Describe：个人设置Activity
 */

public class SettingActivity extends MyBaseActivity implements IBackFragmentListener {

    private ImageView mBtnBack;
    private SettingFragment mSettingFragment;
    private Button mBtnSave;
    public static final int RESULT_PHOTO = 579;
    public static final int RESULT_INFO = 580;
    public static final String PHOTO_KEY = "photo";
    public static final String INFO_KEY = "info";
    /**
     * 暂存更新后头像Url
     */
    private String  mPhotoUrl =null;
    private String mMasterName = null;

    @Override
    protected int getContentView() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        super.initView();
        mBtnBack = findMyViewId(R.id.btn_back_toolbar);
        mBtnSave = findMyViewId(R.id.btn_save);
        initSettingFragment();
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
                SettingActivity.this.finish();
                overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
            }
        });

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePersonInfo();
            }
        });
    }

    /**
     * 更新个人信息
     */
    private void updatePersonInfo() {
        mSettingFragment.updatePersonInfo();
    }

    private void initSettingFragment() {
        if (mSettingFragment == null) {
            mSettingFragment = new SettingFragment();
        }
        replaceFragment(R.id.fl_setting_main, mSettingFragment);
    }

    @Override
    public void currentFragmentBack(Fragment fragment) {
        mCurrentFragmentList.add(fragment) ;
    }

    @Override
    public void onMultiFragment(Object... object) {
        super.onMultiFragment(object);
        switch ((Integer)object[0]){
            case ConFragment.PHOTO_CHANGE:
                mPhotoUrl = (String) object[1];
                break;
            case ConFragment.INFO_CHANGE:
                mMasterName = (String) object[1];
                break;
            default:
                break;
        }
    }

    @Override
    public void finish() {
        if (mPhotoUrl!=null){
            Intent intent = new Intent();
            intent.putExtra(PHOTO_KEY,mPhotoUrl);
            setResult(RESULT_PHOTO,intent);
        }
        if (mMasterName!=null){
            Intent intent = new Intent();
            intent.putExtra(INFO_KEY,mMasterName);
            setResult(RESULT_INFO,intent);
        }
        super.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
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
        mSettingFragment = null;
    }

}


