package com.yinghai.a24divine_user.module.login.bind;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fansonlib.utils.ShowToast;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseMvpFragment;
import com.yinghai.a24divine_user.function.internationalCode.InternationalCodeActivity;
import com.yinghai.a24divine_user.module.login.state.LoginState;
import com.yinghai.a24divine_user.module.login.state.LoginStateManager;
import com.yinghai.a24divine_user.module.main.MainActivity;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/29 14:01
 *         Describe：第三方登陆绑定的界面
 */

public class BindFragment extends MyBaseMvpFragment<BindPresenter> implements ContractBind.IView {

    private static final int REQUEST_AREA_CODE = 745;
    private EditText mEtName,mEtPassword;
    private Button mBtnBind;
    private Button mBtnGetVerifyCode;
    private CountDownTimer mCountDownTimer;
    private TextView mTvCountryCode;
    private static  final String THIRD_ID = "thirdId" ;


    @Override
    protected void initToolbarTitle() {
    }

    @Override
    protected BindPresenter createPresenter() {
        return new BindPresenter(this);
    }

    public static BindFragment newInstance(int thirdId ) {
        Bundle args = new Bundle();
        args.putInt(THIRD_ID,thirdId);
        BindFragment fragment = new BindFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bind;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        mEtName = findMyViewId(R.id.et_tel);
        mEtPassword = findMyViewId(R.id.et_verify_code);
        mBtnBind = findMyViewId(R.id.btn_bind);
        mBtnGetVerifyCode = findMyViewId(R.id.btn_get_verify_code);
        mTvCountryCode = findMyViewId(R.id.spinner_country_code);
        return rootView;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mBtnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoading();
                mPresenter.bind(mTvCountryCode.getText().toString().replace("+","").trim(),mEtName.getText().toString(),getArguments().getInt(THIRD_ID),
                        mEtPassword.getText().toString());
            }
        });

        mBtnGetVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                mPresenter.getCode(mTvCountryCode.getText().toString().replace("+","").trim(),mEtName.getText().toString(),getArguments().getInt(THIRD_ID));
            }
        });

        mTvCountryCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(hostActivity, InternationalCodeActivity.class);
                startActivityForResult(intent, REQUEST_AREA_CODE);
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
    public void showGetCodeSuccess() {
        mBtnGetVerifyCode.setClickable(false);
        hideLoading();
        ShowToast.singleLong(getString(R.string.verify_code_has_send));
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

    @Override
    public void showGetCodeFailure(String errorMsg) {
        hideLoading();
        ShowToast.singleShort(errorMsg);
    }

    @Override
    public void showBindSuccess() {
        stopCountDown();
        //状态模式，设置为已登陆
        LoginStateManager.getInstance().setState(new LoginState());
        startMyActivity(MainActivity.class);
        hideLoading();
        hostActivity.finish();
    }

    @Override
    public void showBindFailure(String errorMsg) {
        hideLoading();
        ShowToast.singleShort(errorMsg);
    }

    private void stopCountDown(){
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stopCountDown();
    }
}
