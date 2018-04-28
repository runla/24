package com.yinghai.a24divine_user.module.login.bind;

import com.example.fansonlib.base.AppUtils;
import com.example.fansonlib.utils.ShowToast;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.PersonInfoBean;

import io.rong.imlib.RongIMClient;

import static com.example.fansonlib.utils.notification.MyNotificationUtils.mContext;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/29 13:57
 *         Describe：第三方登陆绑定的P层
 */

public class BindPresenter extends MyBasePresenter<BindModel,ContractBind.IView> implements ContractBind.IPresenter, ContractBind.IModel.IGetCodeCallback, ContractBind.IModel.IBindCallback {



    public BindPresenter(ContractBind.IView view){
        attachView(view);
    }

    @Override
    protected BindModel createModel() {
        return new BindModel();
    }

    @Override
    public void getCode(String countryCode, String tel, int thirdId) {
        mBaseModel.onGetCode(countryCode,tel,thirdId,this);
    }

    @Override
    public void bind(String countryCode, String tel, int thirdId, String verification) {
        mBaseModel.onBind(countryCode,tel,thirdId,verification,this);
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    @Override
    public void onGetCodeSuccess() {
        if (isViewAttached()){
            getBaseView().showGetCodeSuccess();
            ShowToast.singleShort(AppUtils.getString(R.string.verify_code_has_send));
        }

    }

    @Override
    public void onGetCodeFailure(String errorMsg) {
        if (isViewAttached()){
            getBaseView().showGetCodeFailure(errorMsg);
        }
    }

    /**
     * 融云登录的回调
     */
    private RongIMClient.ConnectCallback mConnectCallback = new RongIMClient.ConnectCallback() {
        @Override
        public void onTokenIncorrect() {
//            ShowToast.singleLong(mContext.getString(R.string.token_incorrect));
            if (isViewAttached()) {
                getBaseView().showBindFailure(mContext.getString(R.string.token_incorrect));
            }
        }

        @Override
        public void onSuccess(String userId) {
            if (isViewAttached()) {
                getBaseView().showBindSuccess();
            }
        }

        @Override
        public void onError(RongIMClient.ErrorCode errorCode) {
            if (isViewAttached()) {
                getBaseView().showBindFailure(mContext.getString(R.string.im_connect_error)+errorCode.getMessage());
            }
        }
    };

    @Override
    public void onBindSuccess(PersonInfoBean bean) {
        mBaseModel.rongIMLogin(bean.getData().getTfUser().getUToken(), mConnectCallback);
    }

    @Override
    public void onBindFailure(String errorMsg) {
        if (isViewAttached()){
            getBaseView().showBindFailure(errorMsg);
        }
    }
}
