package com.yinghai.a24divine_user.module.login.findpassword;

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
import com.yinghai.a24divine_user.widget.DrawableEditText;

import static com.youth.banner.BannerConfig.DURATION;

/**
 * Created by chenjianrun on 2017/12/19.
 *
 */

public class FindPasswordFragment extends MyBaseMvpFragment<FindPasswordPresenter> implements ContractFindPassword.IRegisterView{
    private static final int REQUEST_AREA_CODE = 746;
    public static final int PASSWORD_COUNT = 6;
    public static final int TYPE_REGISTER = 0;
    public static final int TYPE_FIND_PASSWORD = 1;
    private int mType = TYPE_REGISTER;

    private DrawableEditText mEtTel;
    private DrawableEditText mEtVerifyCode;
    private DrawableEditText mEtPassword;
    private DrawableEditText mEtPasswordAgain;

    private TextView mTvCountryCode;
    private Button mBtnGetVerifyCode;
    private Button mBtnConfirm;

    // 获取验证码计时器
    private CountDownTimer mCountDownTimer;
    private ImageView mIVBack;

    public static FindPasswordFragment newInstance(int type){
        FindPasswordFragment registerFragment = new FindPasswordFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        registerFragment.setArguments(bundle);
        return registerFragment;
    }

    @Override
    protected void initToolbarTitle() {
        TextView tv_title = findMyViewId(R.id.tv_title_toolbar);
        if (mType == TYPE_REGISTER) {
            tv_title.setText(getString(R.string.register));
        }else{
            tv_title.setText(getString(R.string.find_password));
        }
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (isDestroyView){
            return CubeAnimation.create(CubeAnimation.RIGHT, enter, DURATION);
        }
        if (enter) {
            return CubeAnimation.create(CubeAnimation.LEFT, enter, DURATION);
        } else {
            return CubeAnimation.create(CubeAnimation.LEFT, enter, DURATION);
        }
    }

    @Override
    protected FindPasswordPresenter createPresenter() {
        return new FindPasswordPresenter(this,hostActivity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        mEtTel = findMyViewId(R.id.et_phone);
        mEtVerifyCode = findMyViewId(R.id.et_verify_code);
        mEtPassword = findMyViewId(R.id.et_password);
        mEtPasswordAgain = findMyViewId(R.id.et_password_again);
        mBtnConfirm = findMyViewId(R.id.btn_confirm);
        mBtnGetVerifyCode = findMyViewId(R.id.btn_get_verify_code);
        mTvCountryCode = findMyViewId(R.id.tv_country_code);
        mIVBack = findMyViewId(R.id.btn_back_toolbar);
        return super.initView(view, bundle);
    }

    @Override
    protected void initData() {
        if (getArguments() != null) {
            mType = getArguments().getInt("type");
        }
        if (mType == TYPE_REGISTER) {
            ((LinearLayout)findMyViewId(R.id.linear_password)).setVisibility(View.GONE);
            ((LinearLayout)findMyViewId(R.id.linear_password_again)).setVisibility(View.GONE);
        }
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mType == TYPE_REGISTER) {
                    mPresenter.register();
                }else{
                    mPresenter.findPassword();
                }
            }
        });

        mBtnGetVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnGetVerifyCode.setEnabled(false);
                mPresenter.sendVerifyCode(mType);
            }
        });

        mTvCountryCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hostActivity, InternationalCodeActivity.class);
                startActivityForResult(intent, REQUEST_AREA_CODE);
            }
        });

        mIVBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackClick();
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
    public void onGetVerifyCodeSuccess() {
        startCountTimer();

    }

    @Override
    public void onGetdVerifyCodeFailure(String errMsg) {
        mBtnGetVerifyCode.setEnabled(true);
        ShowToast.singleShort(errMsg);
    }

    @Override
    public void onRegisterSuccess(boolean isHasPassword) {
        if (isHasPassword) {
            SharePreferenceHelper.putBoolean(ConstantPreference.B_USER_LOGIN,true);
            SharePreferenceHelper.apply();
            stopCountTimer();
            //状态模式，设置为已登陆
            LoginStateManager.getInstance().setState(new LoginState());
            startMyActivity(MainActivity.class);
            hostActivity.finish();
        }else{
            // 设置密码
            mFragmentListener.onFragment(ConFragment.SET_PASSWORD);
        }
    }

    @Override
    public void onRegisterFailure(String errMsg) {
        ShowToast.singleLong(errMsg);
    }

    @Override
    public void onFindPasswordSuccess() {
        if (mType == TYPE_FIND_PASSWORD) {
            // 保存密码
            String password = mEtPassword.getText().toString().trim();
            String tel = mEtTel.getText().toString().trim();
            SharePreferenceHelper.putString(ConstantPreference.S_PHONE,tel);
            SharePreferenceHelper.putString(ConstantPreference.S_PASSWORD,password);
            SharePreferenceHelper.apply();
        }
        stopCountTimer();
        ShowToast.singleLong(getString(R.string.setting_successful));
        mFragmentListener.onFragment(ConFragment.FIND_PASSWORD_SUCCESS);
    }

    @Override
    public void onFindPasswordFailure(String errMsg) {
        ShowToast.singleLong(errMsg);
    }

    @Override
    public String getCountryCode() {
        return mTvCountryCode.getText().toString().replace("+","").trim();
    }

    @Override
    public String getTelNo() {
        String telNo = mEtTel.getText().toString().trim();
        String countryCode = getCountryCode();
        if (TextUtils.isEmpty(telNo)) {
            mEtTel.setShakeAnimation();
            ShowToast.singleLong(getString(R.string.please_input_tel));
            return null;
        }
        if (countryCode.equals("86") && telNo.length() != 11){
            mEtTel.setShakeAnimation();
            ShowToast.singleLong(getString(R.string.enter_correct_phone));
            return null;
        }
        if (countryCode.equals("853") && telNo.length() != 8){
            mEtTel.setShakeAnimation();
            ShowToast.singleLong(getString(R.string.enter_correct_phone));
            return null;
        }
        if (countryCode.equals("852") && telNo.length() != 8){
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
            ShowToast.singleShort(getString(R.string.hint_set_password));
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
    public boolean isRepeatPassword() {
        String password = mEtPassword.getText().toString().trim();
        String passwordAgain = mEtPasswordAgain.getText().toString().trim();
        if (TextUtils.isEmpty(passwordAgain)) {
            mEtPasswordAgain.setShakeAnimation();
            ShowToast.singleShort(getString(R.string.please_confirm_password));
            return false;
        }

        if (!TextUtils.equals(password,passwordAgain)) {
            mEtPasswordAgain.setShakeAnimation();
            mEtPassword.setShakeAnimation();
            ShowToast.singleShort(getString(R.string.the_password_different));
            return false;
        }
        return true;
    }

    @Override
    public void setGetVerifyCodeEnable(boolean enable) {
        mBtnGetVerifyCode.setEnabled(enable);
    }


    /**
     * 开启计时器
     */
    private void startCountTimer(){
        if (mCountDownTimer == null) {
            mCountDownTimer = new CountDownTimer(60000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mBtnGetVerifyCode.setText(millisUntilFinished / 1000 + getString(R.string.secs_later));
                }

                @Override
                public void onFinish() {
                    mCountDownTimer = null;
                    mBtnGetVerifyCode.setEnabled(true);
                    mBtnGetVerifyCode.setText(hostActivity.getString(R.string.login_btn_get_verify_code));
                }
            }.start();
        }
    }

    /**
     * 停止计时器
     */
    private void stopCountTimer(){
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopCountTimer();
    }
}
