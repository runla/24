package com.yinghai.a24divine_user.module.login.phone;

import android.content.Context;
import android.text.TextUtils;

import com.example.fansonlib.utils.NetWorkUtil;
import com.example.fansonlib.utils.ShowToast;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.PersonInfoBean;

import io.rong.imlib.RongIMClient;

/**
 * @author Created by：fanson
 *         Created on：2017/10/25 15:44
 *         Describe：登录的P层（逻辑）实现类
 */

public class LoginPresenter extends MyBasePresenter<LoginModel, ContractLogin.ILoginView> implements ContractLogin.ILoginPresenter
        , ContractLogin.ILoginModel.ILoginCallback, ContractLogin.ILoginModel.IGetCodeCallback {

    private Context mContext;

    public LoginPresenter(ContractLogin.ILoginView view, Context context) {
        this.mContext = context;
        attachView(view);
    }

    @Override
    public void onLogin(String countryCode, String telNo, String verifyCode) {
        if (!checkToPhoneLogin()) {
            return;
        }

        getBaseView().showLoading();
        mBaseModel.verifyLogin(getBaseView().getCountryCode(), getBaseView().getTelNo(), getBaseView().getVerifyCode(), this);
    }

    /**
     * 手机登录前進行輸入判空
     *
     * @return
     */
    private boolean checkToPhoneLogin() {

        if (TextUtils.isEmpty(getBaseView().getTelNo())) {
            return false;
        }
        if (TextUtils.isEmpty(getBaseView().getVerifyCode())) {
            return false;
        }

        if (!NetWorkUtil.isNetWordConnected(mContext)) {
            ShowToast.singleShort(mContext.getString(R.string.no_net));
            return false;
        }
        return true;
    }

    @Override
    public void onLoingPwd(String countryCode, String telNo, String pwd) {
        if (!checkToPasswordLogin()) {
            return;
        }
        getBaseView().showLoading();
        mBaseModel.pwdLogin(getBaseView().getCountryCode(), getBaseView().getTelNo(), getBaseView().getPassword(), this);

    }

    /**
     * 密码登录前進行輸入判空
     *
     * @return
     */
    private boolean checkToPasswordLogin() {

        if (TextUtils.isEmpty(getBaseView().getTelNo())) {
            return false;
        }
        if (TextUtils.isEmpty(getBaseView().getPassword())) {
            return false;
        }

        if (!NetWorkUtil.isNetWordConnected(mContext)) {
            ShowToast.singleShort(mContext.getString(R.string.no_net));
            return false;
        }
        return true;
    }

    @Override
    public void onGetCode(String countryCode, String telNo) {
        if (!checkToGetVerifyCode()) {
            getBaseView().setGetVerifyCodeEnable(true);
            return;
        }
        getBaseView().hideLoading();
        mBaseModel.getVerifyCode(getBaseView().getCountryCode(), getBaseView().getTelNo(), this);
    }

    /**
     * 獲取驗證碼前進行輸入判斷
     *
     * @return
     */
    private boolean checkToGetVerifyCode() {
        if (TextUtils.isEmpty(getBaseView().getTelNo())) {
            return false;
        }
        if (!NetWorkUtil.isNetWordConnected(mContext)) {
            ShowToast.singleShort(mContext.getString(R.string.no_net));
            return false;
        }
        return true;
    }


    @Override
    public void onGetCodeSuccess() {
        if (isViewAttached()) {
            getBaseView().getCodeSuccess();
        }
    }

    @Override
    public void onGetCodeFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().getCodeFailure(errorMsg);
        }
    }

    @Override
    public void onLoginSuccess(final PersonInfoBean bean) {

        mBaseModel.rongIMLogin(bean.getData().getTfUser().getUToken(), new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                if (isViewAttached()) {
                    getBaseView().hideLoading();
                    getBaseView().onLoginFailure(mContext.getString(R.string.token_incorrect));
                }
            }

            @Override
            public void onSuccess(String s) {
                if (isViewAttached()) {
                    getBaseView().hideLoading();
                    if (TextUtils.isEmpty(bean.getData().getTfUser().getUPassword())) {
                        getBaseView().loginSuccess(false);
                    } else {
                        getBaseView().loginSuccess(true);
                    }
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                if (isViewAttached()) {
                    getBaseView().hideLoading();
                    getBaseView().onLoginFailure(mContext.getString(R.string.im_connect_error) + errorCode.getMessage());
                }
            }
        });

    }

    @Override
    public void onLoginFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().getCodeFailure(errorMsg);
        }
    }


    @Override
    protected LoginModel createModel() {
        return new LoginModel();
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    @Override
    public void detachView() {
        super.detachView();
    }
}
