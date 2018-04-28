package com.yinghai.a24divine_user.module.login.wechat;

import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.PersonInfoBean;

import io.rong.imlib.RongIMClient;

import static com.example.fansonlib.utils.notification.MyNotificationUtils.mContext;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/27 17:28
 *         Describe：微信登陆的P层
 */

public class ThirdLoginPresenter extends MyBasePresenter<ThirdLoginModel,ContractThird.IView> implements ContractThird.IPresenter, ContractThird.IModel.ILoginCallback {

    public ThirdLoginPresenter(ContractThird.IView view){
        attachView(view);
    }

    @Override
    protected ThirdLoginModel createModel() {
        return new ThirdLoginModel();
    }

    @Override
    public void thirdLogin(int type,String code) {
        mBaseModel.onThirdLogin(type,code,this);
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    @Override
    public void onThirdLoginFailure(String msg) {
        if (isViewAttached()){
            getBaseView().showThirdLoginFailure(msg);
        }
    }

    @Override
    public void onThirdSuccess(PersonInfoBean bean) {
        // IM 登录
        mBaseModel.rongIMLogin(bean.getData().getTfUser().getUToken(),mConnectCallback);
    }

    // 融云登录的回调
    private RongIMClient.ConnectCallback mConnectCallback = new RongIMClient.ConnectCallback() {
        @Override
        public void onTokenIncorrect() {
            if (isViewAttached()){
                getBaseView().showThirdLoginFailure(mContext.getString(R.string.token_incorrect));
            }
        }

        @Override
        public void onSuccess(String userId) {
            if (isViewAttached()){
                getBaseView().showThirdSuccess();
            }
        }

        @Override
        public void onError(RongIMClient.ErrorCode errorCode) {
            if (isViewAttached()){
                getBaseView().showThirdLoginFailure(mContext.getString(R.string.im_connect_error)+errorCode.getMessage());
            }
        }
    };
    @Override
    public void onIsNewUser(int thirdPartyId) {
        if (isViewAttached()){
            getBaseView().showIsNewUser(thirdPartyId);
        }
    }
}
