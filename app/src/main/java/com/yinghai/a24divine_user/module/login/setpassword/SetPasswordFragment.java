package com.yinghai.a24divine_user.module.login.setpassword;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fansonlib.utils.SharePreferenceHelper;
import com.example.fansonlib.utils.ShowToast;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseMvpFragment;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.module.login.state.LoginState;
import com.yinghai.a24divine_user.module.login.state.LoginStateManager;
import com.yinghai.a24divine_user.module.main.MainActivity;
import com.yinghai.a24divine_user.widget.DrawableEditText;

import static com.yinghai.a24divine_user.module.login.findpassword.FindPasswordFragment.PASSWORD_COUNT;

/**
 * Created by chenjianrun on 2017/12/20.
 * 描述：设置密码的 Fragment
 */

public class SetPasswordFragment extends MyBaseMvpFragment<SetPasswordPresneter> implements ContractSetPassword.ISetPasswordView{
    private DrawableEditText mEtPassword;
    private DrawableEditText mEtPasswordAgain;
    private Button mBtnSetPassword;
    private ImageView mIVBack;

    @Override
    protected void initToolbarTitle() {
        TextView tv_title = findMyViewId(R.id.tv_title_toolbar);
        tv_title.setText(R.string.set_password);
    }

    @Override
    protected SetPasswordPresneter createPresenter() {
        return new SetPasswordPresneter(this,hostActivity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_set_password;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        mEtPassword = findMyViewId(R.id.et_password);
        mEtPasswordAgain = findMyViewId(R.id.et_password_again);
        mBtnSetPassword = findMyViewId(R.id.btn_confirm);
        mIVBack = findMyViewId(R.id.btn_back_toolbar);
        return super.initView(view, bundle);

    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mBtnSetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.setPassword();
            }
        });

        mIVBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentListener.onFragment(ConFragment.SET_PASSWORD_FRAGMENT_BACK);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setPasswordSuccess() {
        // 保存密码
        String password = mEtPassword.getText().toString().trim();
        SharePreferenceHelper.putString(ConstantPreference.S_PASSWORD,password);
        SharePreferenceHelper.apply();

        //状态模式，设置为已登陆
        LoginStateManager.getInstance().setState(new LoginState());
        startMyActivity(MainActivity.class);
        hostActivity.finish();
    }

    @Override
    public void setPasswordFailure(String errMsg) {
        ShowToast.singleLong(errMsg);
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
}
