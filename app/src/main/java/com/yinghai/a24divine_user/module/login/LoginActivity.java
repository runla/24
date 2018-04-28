package com.yinghai.a24divine_user.module.login;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;

import com.example.fansonlib.utils.MyPermissionHelper;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.example.fansonlib.utils.ShowToast;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseActivity;
import com.yinghai.a24divine_user.callback.IBackFragmentListener;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.manager.MyFragmentManager;
import com.yinghai.a24divine_user.module.login.bind.BindFragment;
import com.yinghai.a24divine_user.module.login.phone.LoginFragment;
import com.yinghai.a24divine_user.module.login.findpassword.FindPasswordFragment;
import com.yinghai.a24divine_user.module.login.setpassword.SetPasswordFragment;
import com.yinghai.a24divine_user.module.login.state.LoginStateManager;
import com.yinghai.a24divine_user.module.main.MainActivity;
import com.yinghai.a24divine_user.utils.LogUtils;
import com.yinghai.a24divine_user.utils.MyStatusBarUtil;

/**
 * @author Created by：fanson
 *         Created on：2017/10/25 15:42
 *         Description：登录Activity
 */
public class LoginActivity extends MyBaseActivity implements IBackFragmentListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private LoginFragment mLoginFragment;
    private ChooseLoginFragment mChooseLoginFragment;
    private MyPermissionHelper mPermissionHelper;
    private BindFragment mBindFragment;
    private FindPasswordFragment mFindPasswordFragment;
    private SetPasswordFragment mSetPasswordFragment;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        super.initView();
        LogUtils.d(TAG, "initView");
        if (SharePreferenceHelper.getBoolean(ConstantPreference.B_USER_LOGIN, false) && LoginStateManager.getInstance().getState()) {
            startMyActivity(MainActivity.class);
        } else {
            SharePreferenceHelper.clear();
            checkPermission();
            initChooseLoginFragment();
        }

    }

    @Override
    protected void setStatus() {
        MyStatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);
    }

    private void initChooseLoginFragment() {
        if (mChooseLoginFragment == null) {
            mChooseLoginFragment = new ChooseLoginFragment();
        }
        MyFragmentManager.replaceFragment(getSupportFragmentManager(), R.id.login_fl_main, mChooseLoginFragment);
    }

    private void initPhoneLoginFragment() {
        if (mLoginFragment == null) {
            mLoginFragment = new LoginFragment();
        }
        MyFragmentManager.switchFragment(getSupportFragmentManager(), R.id.login_fl_main, getCurrentFragment(), mLoginFragment);
    }

    private void initBindFragment(int thirdId) {
        mBindFragment = BindFragment.newInstance(thirdId);
        MyFragmentManager.switchFragment(getSupportFragmentManager(),R.id.login_fl_main, getCurrentFragment(), mBindFragment);
    }

    /**
     * 找回密码
     */
    private void initFindPasswordFragment() {
        mFindPasswordFragment = FindPasswordFragment.newInstance(FindPasswordFragment.TYPE_FIND_PASSWORD);
        MyFragmentManager.switchFragment(getSupportFragmentManager(),R.id.login_fl_main, mLoginFragment, mFindPasswordFragment);
    }

    private void initSetPasswordFragment() {
        mSetPasswordFragment = new SetPasswordFragment();
        MyFragmentManager.switchFragment(getSupportFragmentManager(),R.id.login_fl_main, getCurrentFragment(), mSetPasswordFragment);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void listenEvent() {

    }

    @Override
    public void onFragment(Object object) {
        super.onFragment(object);
        switch ((int) object) {
            case ConFragment.OPEN_PHONE_LOGIN:
                initPhoneLoginFragment();
                break;

            // 找回密码
            case ConFragment.FIND_PASSWORD:
                initFindPasswordFragment();
                break;

            // 设置密码
            case ConFragment.SET_PASSWORD:
                initSetPasswordFragment();
                break;

            // 找回密码成功
            case ConFragment.FIND_PASSWORD_SUCCESS:
                mLoginFragment = new LoginFragment();
                switchFragment(R.id.login_fl_main, getCurrentFragment(), mLoginFragment);
                break;

            case ConFragment.SET_PASSWORD_FRAGMENT_BACK:
                initPhoneLoginFragment();
                break;

            case ConFragment.Login_FRAGMENT_BACK:
                onBackPressed();
                break;

            case ConFragment.FIND_PASSWORD_FRAGMENT_BACK:
                initPhoneLoginFragment();
                break;

            default:
                break;
        }
    }

    @Override
    public void currentFragmentBack(Fragment fragment) {
        mCurrentFragmentList.add(fragment);
    }

    public void checkPermission() {
        if (Build.VERSION.SDK_INT < 23) {
            getDeviceId();
            return;
        }
        if (!MyPermissionHelper.hasPermissions(this, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_SMS)) {
            mPermissionHelper = new MyPermissionHelper(this);
            mPermissionHelper.requestPermissions(getString(R.string.phone_permission_request), new MyPermissionHelper.PermissionListener() {
                @Override
                public void doAfterGrand(String... permission) {
                    getDeviceId();
                }

                @Override
                public void doAfterDenied(String... permission) {
                    ShowToast.Long(getString(R.string.phone_permission_no_agress));
                    checkPermission();
                }
            }, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_SMS);
        } else {
            getDeviceId();
        }
    }

    private void getDeviceId() {
        String strIMEI = ((TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        SharePreferenceHelper.putString(ConstantPreference.S_DEVICE_ID, strIMEI);
        SharePreferenceHelper.apply();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mPermissionHelper.handleRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onMultiFragment(Object... object) {
        super.onMultiFragment(object);
        switch ((Integer) object[0]) {
            case ConFragment.OPEN_BIND_VIEW:
                initBindFragment((Integer) object[1]);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (!MyFragmentManager.handlerBackPress(getSupportFragmentManager())){
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCurrentFragmentList != null) {
            mCurrentFragmentList.clear();
            mCurrentFragmentList = null;
        }
        mBindFragment = null;
        mChooseLoginFragment = null;
        mLoginFragment = null;
        mFindPasswordFragment = null;
        mSetPasswordFragment = null;
    }
}
