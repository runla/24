package com.yinghai.a24divine_user.module.login.findpassword;

import android.content.Context;
import android.text.TextUtils;

import com.example.fansonlib.utils.NetWorkUtil;
import com.example.fansonlib.utils.ShowToast;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.PersonInfoBean;
import com.yinghai.a24divine_user.rongIm.IMLogin;

import io.rong.imlib.RongIMClient;

/**
 * Created by chenjianrun on 2017/12/19.
 * 描述：注册、找回密码的 p 层
 */

public class FindPasswordPresenter extends MyBasePresenter<FindPasswordModel,ContractFindPassword.IRegisterView> implements ContractFindPassword.IRegisterPresenter, ContractFindPassword.IRegisterModel.ISendVerifyCodeSuccess, ContractFindPassword.IRegisterModel.IRegisterSuccess, ContractFindPassword.IRegisterModel.IFindPasswordCallback {
    private Context mContext;
    public FindPasswordPresenter(ContractFindPassword.IRegisterView baseView, Context context){
        mContext = context;
        attachView(baseView);
    }

    @Override
    protected FindPasswordModel createModel() {
        return new FindPasswordModel();
    }

    @Override
    public void sendVerifyCode(int type) {
        if (!checkToGetVerifyCode()) {
            getBaseView().setGetVerifyCodeEnable(true);
            return;
        }
        getBaseView().showLoading();
        mBaseModel.sendVerifyCode(getBaseView().getCountryCode(),getBaseView().getTelNo(),type,this);
    }

    /**
     * 獲取驗證碼前進行輸入判斷
     * @return
     */
    private boolean checkToGetVerifyCode(){
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
    public void register() {
        if (!checkToRegister()) {
            return;
        }
        getBaseView().showLoading();
        mBaseModel.register(getBaseView().getCountryCode()
                ,getBaseView().getTelNo()
                ,getBaseView().getVerifyCode()
                ,this);
    }

    /**
     * 註冊前進行輸入判空
     * @return
     */
    private boolean checkToRegister(){

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
    public void findPassword() {
        if (!checkToFindPassword()){
            return;
        }
        getBaseView().showLoading();
        mBaseModel.findPassword(getBaseView().getCountryCode()
                ,getBaseView().getTelNo()
                ,getBaseView().getVerifyCode()
                ,getBaseView().getPassword()
                ,this);
    }

    /**
     * 找回密碼前進行輸入檢查(判空)
     * @return
     */
    private boolean checkToFindPassword(){
        if (TextUtils.isEmpty(getBaseView().getTelNo())) {
            return false;
        }
        if (TextUtils.isEmpty(getBaseView().getVerifyCode())) {
            return false;
        }
        if (TextUtils.isEmpty(getBaseView().getPassword())) {
            return false;
        }
        if (!getBaseView().isRepeatPassword()) {
            return false;
        }
        if (!NetWorkUtil.isNetWordConnected(mContext)) {
            ShowToast.singleShort(mContext.getString(R.string.no_net));
            return false;
        }
        return true;
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    @Override
    public void onGetVerifyCodeSuccess() {
        if (isViewAttached()) {
            getBaseView().hideLoading();
            getBaseView().onGetVerifyCodeSuccess();
        }
    }

    @Override
    public void onGetdVerifyCodeFailure(String errMsg) {
        if (!isViewAttached()) {
            return;
        }

        getBaseView().hideLoading();
        getBaseView().onGetdVerifyCodeFailure(errMsg);
    }


    @Override
    public void onRegisterSuccess(final PersonInfoBean personInfoBean) {
        IMLogin.imLogin(personInfoBean.getData().getTfUser().getUToken(),new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                if (isViewAttached()) {
                    getBaseView().onRegisterFailure(mContext.getString(R.string.token_incorrect));
                }
            }

            @Override
            public void onSuccess(String userId) {
                if (!isViewAttached()) {
                    return;
                }
                getBaseView().hideLoading();
                if (TextUtils.isEmpty(personInfoBean.getData().getTfUser().getUPassword())) {
                    getBaseView().onRegisterSuccess(false);
                }else{
                    getBaseView().onRegisterSuccess(true);
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                if (isViewAttached()) {
                    getBaseView().onRegisterFailure(mContext.getString(R.string.im_connect_error)+errorCode.getMessage());
                }
            }
        });

    }

    @Override
    public void onRegisterFailure(String errMsg) {
        if (isViewAttached()) {
            getBaseView().hideLoading();
            getBaseView().onRegisterFailure(errMsg);
        }
    }

    @Override
    public void onFindPasswordSuccess() {
        if (isViewAttached()) {
            getBaseView().hideLoading();
            getBaseView().onFindPasswordSuccess();
        }
    }

    @Override
    public void onFindPasswordFailure(String errMsg) {
        if (isViewAttached()) {
            getBaseView().hideLoading();
            getBaseView().onFindPasswordFailure(errMsg);
        }
    }
}
