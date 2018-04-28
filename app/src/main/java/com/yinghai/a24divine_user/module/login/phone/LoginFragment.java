package com.yinghai.a24divine_user.module.login.phone;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fansonlib.utils.SharePreferenceHelper;
import com.example.fansonlib.utils.ShowToast;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.animation.CubeAnimation;
import com.yinghai.a24divine_user.base.MyBaseMvpFragment;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.function.internationalCode.InternationalCodeActivity;
import com.yinghai.a24divine_user.module.login.state.LoginState;
import com.yinghai.a24divine_user.module.login.state.LoginStateManager;
import com.yinghai.a24divine_user.module.main.MainActivity;
import com.yinghai.a24divine_user.utils.ClearImageUtils;
import com.yinghai.a24divine_user.widget.DrawableEditText;

import static com.yinghai.a24divine_user.module.login.findpassword.FindPasswordFragment.PASSWORD_COUNT;
import static com.youth.banner.BannerConfig.DURATION;

/**
 * @author Created by：fanson
 *         Created on：2017/10/25 15:44
 *         Describe：手机登陆
 */

public class LoginFragment extends MyBaseMvpFragment<LoginPresenter> implements ContractLogin.ILoginView {
    public static final int TYPE_PHONE = 0;
    public static final int TYPE_PASSWORD = 1;
    /**
     * 登录的类型
     */
    private int mType = TYPE_PASSWORD;
    private static final int REQUEST_AREA_CODE = 746;
    private DrawableEditText mEtTel, mEtVerifyCode, mEtPassword;
    private Button mBtnLogin;
    private LinearLayout mMainRl;
    private Button mBtnGetVerifyCode;
    private CountDownTimer mCountDownTimer;
    private TextView mTvCountryCode;
    private TextView mTvLoginType;
    private TextView mTvFindPassword;
    private ImageView mIVBack;

    public static LoginFragment newInstance(int type) {
        LoginFragment loginFragment = new LoginFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        loginFragment.setArguments(bundle);
        return loginFragment;
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this, hostActivity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (isDestroyView) {
            return CubeAnimation.create(CubeAnimation.RIGHT, enter, DURATION);
        }
        if (enter) {
            if (mIsHide) {
                return CubeAnimation.create(CubeAnimation.RIGHT, true, DURATION);
            } else {
                return CubeAnimation.create(CubeAnimation.LEFT, true, DURATION);
            }
        } else {
            if (mIsHide) {
                return CubeAnimation.create(CubeAnimation.RIGHT, false, DURATION);
            } else {
                return CubeAnimation.create(CubeAnimation.LEFT, false, DURATION);
            }
        }
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        mEtTel = findMyViewId(R.id.et_tel);
        mEtVerifyCode = findMyViewId(R.id.et_verify_code);
        mBtnLogin = findMyViewId(R.id.btn_login);
        mBtnGetVerifyCode = findMyViewId(R.id.btn_get_verify_code);
        mEtPassword = findMyViewId(R.id.et_password);
        mTvCountryCode = findMyViewId(R.id.spinner_country_code);
        mTvLoginType = findMyViewId(R.id.tv_login_type);
        mTvFindPassword = findMyViewId(R.id.tv_find_password);
        mMainRl = findMyViewId(R.id.rootView);
        mIVBack = findMyViewId(R.id.btn_back_toolbar);
        mEtTel.setText(SharePreferenceHelper.getString(ConstantPreference.S_PHONE, ""));
        mTvCountryCode.setText("+" + SharePreferenceHelper.getString(ConstantPreference.S_COUNTRY_CODE, "86"));
        mEtPassword.setText(SharePreferenceHelper.getString(ConstantPreference.S_PASSWORD, ""));
        return rootView;
    }

    @Override
    protected void initToolbarTitle() {
        TextView mTvTitle = findMyViewId(R.id.tv_title_toolbar);
        if (mType == TYPE_PASSWORD) {
            mTvTitle.setText(getString(R.string.password_login));
        } else {
            mTvTitle.setText(getString(R.string.verify_code_login));
        }
    }

    @Override
    protected void initData() {
        if (getArguments() != null) {
            mType = getArguments().getInt("type");
        }
        if (mType == TYPE_PASSWORD) {
            ((LinearLayout) findMyViewId(R.id.linear_password)).setVisibility(View.VISIBLE);
            ((LinearLayout) findMyViewId(R.id.linear_verify_code)).setVisibility(View.GONE);
            mTvLoginType.setText(getString(R.string.verify_code_login));
        } else {
            ((LinearLayout) findMyViewId(R.id.linear_verify_code)).setVisibility(View.VISIBLE);
            ((LinearLayout) findMyViewId(R.id.linear_password)).setVisibility(View.GONE);
            mTvLoginType.setText(getString(R.string.password_login));
        }
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mIVBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentListener.onFragment(ConFragment.Login_FRAGMENT_BACK);
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mType == TYPE_PASSWORD) {
                    mPresenter.onLoingPwd(mTvCountryCode.getText().toString().replace("+", "").trim(), mEtTel.getText().toString(), mEtPassword.getText().toString());
                } else {
                    mPresenter.onLogin(mTvCountryCode.getText().toString().replace("+", "").trim(), mEtTel.getText().toString(), mEtVerifyCode.getText().toString());
                }
            }
        });

        mBtnGetVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                mPresenter.onGetCode(mTvCountryCode.getText().toString().replace("+", "").trim(), mEtTel.getText().toString());
            }
        });

        mTvCountryCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(hostActivity, InternationalCodeActivity.class);
                startActivityForResult(intent, REQUEST_AREA_CODE);
            }
        });

        // 切换登录模式
        mTvLoginType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mType == TYPE_PASSWORD) {
                    mType = TYPE_PHONE;
                } else {
                    mType = TYPE_PASSWORD;
                }
                initToolbarTitle();
                initData();
            }
        });

        // 找回密码
        mTvFindPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentListener.onFragment(ConFragment.FIND_PASSWORD);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_AREA_CODE && resultCode == InternationalCodeActivity.RESULT_CODE) {
            mTvCountryCode.setText(data.getStringExtra(InternationalCodeActivity.AREA_CODE));
        }
    }

    @Override
    public void onLoginFailure(String msg) {
        hideLoading();
        ShowToast.singleLong(msg);
    }

    @Override
    public void loginSuccess(boolean isHasPassword) {
        if (isHasPassword) {
            if (mType == TYPE_PASSWORD) {
                // 保存密码
                String password = mEtPassword.getText().toString().trim();
                SharePreferenceHelper.putString(ConstantPreference.S_PASSWORD, password);
                SharePreferenceHelper.apply();
            }
            stopCountDown();
            //状态模式，设置为已登陆
            LoginStateManager.getInstance().setState(new LoginState());
            startMyActivity(MainActivity.class);
            hideLoading();
            hostActivity.finish();
        } else {
            mFragmentListener.onFragment(ConFragment.SET_PASSWORD);
        }

    }

    @Override
    public void getCodeFailure(String msg) {
        hideLoading();
        ShowToast.singleShort(msg);
    }

    @Override
    public void getCodeSuccess() {
        mBtnGetVerifyCode.setClickable(false);
        hideLoading();
        ShowToast.singleShort(getString(R.string.verify_code_has_send));
        mCountDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mBtnGetVerifyCode.setText("" + millisUntilFinished / 1000 + getString(R.string.secs_later));
            }

            @Override
            public void onFinish() {
                mBtnGetVerifyCode.setClickable(true);
                mBtnGetVerifyCode.setText(hostActivity.getString(R.string.login_btn_get_verify_code));
            }
        }.start();
    }

    private void stopCountDown() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isDestroyView = true;
        stopCountDown();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ClearImageUtils.recycleBackground(mMainRl);
    }

    @Override
    public String getCountryCode() {
        return mTvCountryCode.getText().toString().replace("+", "").trim();
    }

    @Override
    public String getTelNo() {
        String telNo = mEtTel.getText().toString().trim();
        String countryCode = getCountryCode();
        if (TextUtils.isEmpty(telNo)) {
            mEtTel.setShakeAnimation();
            ShowToast.singleLong(getString(R.string.login_edit_please_input_phone));
            return null;
        }

        if (countryCode.equals("86") && telNo.length() != 11) {
            mEtTel.setShakeAnimation();
            ShowToast.singleLong(getString(R.string.enter_correct_phone));
            return null;
        }
        if (countryCode.equals("853") && telNo.length() != 8) {
            mEtTel.setShakeAnimation();
            ShowToast.singleLong(getString(R.string.enter_correct_phone));
            return null;
        }
        if (countryCode.equals("852") && telNo.length() != 8) {
            mEtTel.setShakeAnimation();
            ShowToast.singleLong(getString(R.string.enter_correct_phone));
            return null;
        }
        return telNo;
    }

    @Override
    public String getPassword() {
        String password = mEtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            mEtPassword.setShakeAnimation();
            ShowToast.singleShort(getString(R.string.login_input_word));
            return null;
        }
        if (password.length() < PASSWORD_COUNT) {
            mEtPassword.setShakeAnimation();
            ShowToast.singleShort(getString(R.string.hint_enter_password));
            return null;
        }
        return password;
    }

    @Override
    public String getVerifyCode() {
        String verifyCode = mEtVerifyCode.getText().toString().trim();
        if (TextUtils.isEmpty(verifyCode)) {
            mEtVerifyCode.setShakeAnimation();
            ShowToast.singleShort(getString(R.string.string_input_code));
            return null;
        }
        return verifyCode;
    }

    @Override
    public void setGetVerifyCodeEnable(boolean enable) {
        mBtnGetVerifyCode.setEnabled(enable);
    }
}
