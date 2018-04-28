package com.yinghai.a24divine_user.module.login.setpassword;

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
 * Created by chenjianrun on 2017/12/20.
 */

public class SetPasswordPresneter extends MyBasePresenter<SetPasswordModel,ContractSetPassword.ISetPasswordView> implements ContractSetPassword.ISetPasswordPresenter, ContractSetPassword.ISetPasswordModel.ISetPasswordCallback {
    private Context mContext;
    public SetPasswordPresneter(ContractSetPassword.ISetPasswordView baseview,Context context){
        this.mContext = context;
        attachView(baseview);
    }
    @Override
    protected SetPasswordModel createModel() {
        return new SetPasswordModel();
    }

    @Override
    public void setPassword() {
        if (!checkToSetPassword()){
            return;
        }
        getBaseView().showLoading();
        mBaseModel.setPassword(getBaseView().getPassword(),this);
    }

    /**
     * 设置密碼前進行輸入檢查(判空)
     * @return
     */
    private boolean checkToSetPassword(){

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
    public void setPasswordSuccess(PersonInfoBean personInfoBean) {
        IMLogin.imLogin(personInfoBean.getData().getTfUser().getUToken(),connectCallback);
    }

    @Override
    public void setPasswordFailure(String errMsg) {

    }

    private RongIMClient.ConnectCallback connectCallback = new RongIMClient.ConnectCallback() {
        @Override
        public void onTokenIncorrect() {
            if (isViewAttached()) {
                getBaseView().setPasswordFailure(mContext.getString(R.string.token_incorrect));
            }
        }

        @Override
        public void onSuccess(String userId) {
            if (!isViewAttached()) {
                return;
            }
            getBaseView().hideLoading();
            getBaseView().setPasswordSuccess();
        }

        @Override
        public void onError(RongIMClient.ErrorCode errorCode) {
            if (isViewAttached()) {
                getBaseView().setPasswordFailure(mContext.getString(R.string.im_connect_error)+errorCode.getMessage());
            }
        }
    };
}
