package com.yinghai.a24divine_user.module.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.fansonlib.utils.ShowToast;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.animation.CubeAnimation;
import com.yinghai.a24divine_user.base.MyBaseMvpFragment;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.constant.Config;
import com.yinghai.a24divine_user.module.login.state.LoginState;
import com.yinghai.a24divine_user.module.login.state.LoginStateManager;
import com.yinghai.a24divine_user.module.login.wechat.ContractThird;
import com.yinghai.a24divine_user.module.login.wechat.ThirdLoginPresenter;
import com.yinghai.a24divine_user.module.main.MainActivity;
import com.yinghai.a24divine_user.utils.ClearImageUtils;
import com.yinghai.a24divine_user.utils.LogUtils;
import com.yinghai.a24divine_user.utils.ReboundUtils;
import com.yinghai.a24divine_user.wxapi.wechatLogin.WechatLogin;
import com.yinghai.a24divine_user.wxapi.wechatLogin.WechatLoginCallback;

import java.util.Arrays;

import static com.youth.banner.BannerConfig.DURATION;

/**
 * @author Created by：fanson
 *         Created on：2017/10/25 14:30
 *         Describe：选择登陆方式的Fragment
 */

public class ChooseLoginFragment extends MyBaseMvpFragment<ThirdLoginPresenter> implements ContractThird.IView {

    private static final String TAG = ChooseLoginFragment.class.getSimpleName();
    private RelativeLayout mWechatLogin, mPhoneLogin, mFacebookLogin;
    private CallbackManager callbackManager;
    private LinearLayout mMainLl;

    @Override
    protected void initToolbarTitle() {

    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (enter) {
            if (mIsHide){
                return CubeAnimation.create(CubeAnimation.RIGHT, true, DURATION);
            }else {
                return CubeAnimation.create(CubeAnimation.LEFT, true, DURATION);
            }
        } else {
            if (mIsHide){
                return CubeAnimation.create(CubeAnimation.RIGHT, false, DURATION);
            }else {
                return CubeAnimation.create(CubeAnimation.LEFT, false, DURATION);
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_choose;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        LogUtils.d(TAG, "initView");
        mWechatLogin = findMyViewId(R.id.note_wechat_login);
        mPhoneLogin = findMyViewId(R.id.note_phone_login);
        mFacebookLogin = findMyViewId(R.id.note_facebook_login);
        mMainLl = findMyViewId(R.id.ll_note);
        return rootView;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mPhoneLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentListener.onFragment(ConFragment.OPEN_PHONE_LOGIN);
            }
        });

        mWechatLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoading();
                WechatLogin.getInstance().wechatLogin(hostActivity.getApplicationContext(), Config.WX_APP_ID, new WechatLoginCallback() {
                    @Override
                    public void showWechatLoginSuccess() {
                        hideLoading();
                    }

                    @Override
                    public void wechatLoginSuccess(String code) {
                        mPresenter.thirdLogin(0, code);
                    }

                    @Override
                    public void wechatLoginFailed(String errMsg) {
                        hideLoading();
                        if (!TextUtils.isEmpty(errMsg)) {
                            ShowToast.singleShort(errMsg);
                        }
                    }
                });
            }
        });

        mFacebookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initFacebookLoginResponse();
                LoginManager.getInstance().logInWithReadPermissions(ChooseLoginFragment.this, Arrays.asList("public_profile"));
            }
        });
    }

    private void initFacebookLoginResponse(){
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                ShowToast.singleShort("facebook success");
                mPresenter.thirdLogin(3, loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
                ShowToast.singleShort("facebook onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                ShowToast.singleShort("facebook error:" + error);
            }
        });
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected ThirdLoginPresenter createPresenter() {
        return new ThirdLoginPresenter(this);
    }

    private void addAnimation() {
        ReboundUtils reboundUtils = new ReboundUtils();
        reboundUtils.animateViewChain((ViewGroup) findMyViewId(R.id.ll_note), getResources().getDisplayMetrics().widthPixels, 0);
    }

    @Override
    public void showThirdLoginFailure(String msg) {
        ShowToast.singleShort(msg);
        hideLoading();
    }

    @Override
    public void showThirdSuccess() {
        //状态模式，设置为已登陆
        LoginStateManager.getInstance().setState(new LoginState());
        startMyActivity(MainActivity.class);
        hideLoading();
        hostActivity.finish();
    }

    @Override
    public void showIsNewUser(int thirdPartyId) {
        mMultiFragmentListener.onMultiFragment(ConFragment.OPEN_BIND_VIEW, thirdPartyId);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        callbackManager = null;
        ClearImageUtils.recycleBackground(mMainLl);
        WechatLogin.getInstance().releaseCallback();
    }
}
