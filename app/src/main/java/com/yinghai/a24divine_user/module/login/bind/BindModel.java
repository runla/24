package com.yinghai.a24divine_user.module.login.bind;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.PersonInfoBean;
import com.yinghai.a24divine_user.bean.VerifyCodeBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.rongIm.IMLogin;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

import io.rong.imlib.RongIMClient;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/29 13:45
 *         Describe：第三方登陆绑定的M层
 */

public class BindModel extends BaseModel implements ContractBind.IModel{


    private IGetCodeCallback mCodeCallback;
    private IBindCallback mBindCallback;
    private RongIMClient.ConnectCallback mConnectCallback;
    @Override
    public void onGetCode(String countryCode, String tel, int thirdId, IGetCodeCallback callback) {
        mCodeCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(5);
        maps.put("countryCode",countryCode);
        maps.put("tel",tel);
        maps.put("thirdPartyId", thirdId);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.THIRD_GET_CODE,maps, new HttpResponseCallback<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {
                if (mCodeCallback == null){
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        SharePreferenceHelper.putString(ConstantPreference.S_SESSION_ID,bean.getData().getSessionId());
                        SharePreferenceHelper.apply();
                        mCodeCallback.onGetCodeSuccess();
                        break;
                    default:
                        mCodeCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mCodeCallback!=null){
                    mCodeCallback.onGetCodeFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void onBind(String countryCode, String tel, int thirdId, String verification, IBindCallback callback) {
        mBindCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(8);
        maps.put("countryCode",countryCode);
        maps.put("tel",tel);
        maps.put("thirdPartyId", thirdId);
        maps.put("verification",verification);
        maps.put("deviceId", SharePreferenceHelper.getString(ConstantPreference.S_DEVICE_ID,null));
        maps.put("deviceType", 2);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.SUBMIT_BIND,maps, new HttpResponseCallback<PersonInfoBean>() {
            @Override
            public void onSuccess(PersonInfoBean bean) {
                if (mBindCallback == null){
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        SharePreferenceHelper.putInt(ConstantPreference.I_USER_ID,bean.getData().getTfUser().getUserId());
                        SharePreferenceHelper.putString(ConstantPreference.S_USER_BIRTHDAY,bean.getData().getTfUser().getUBirthday());
                        SharePreferenceHelper.putString(ConstantPreference.S_USER_PHOTO,bean.getData().getTfUser().getUImgUrl());
                        SharePreferenceHelper.putString(ConstantPreference.S_COUNTRY_CODE,bean.getData().getTfUser().getUCountryCode());
                        SharePreferenceHelper.putString(ConstantPreference.S_PHONE,bean.getData().getTfUser().getUTel());
                        SharePreferenceHelper.putString(ConstantPreference.S_USER_NAME,bean.getData().getTfUser().getUNick());
                        SharePreferenceHelper.putInt(ConstantPreference.I_USER_CONSTELLATION,bean.getData().getTfUser().getUConstellation());
                        SharePreferenceHelper.putBoolean(ConstantPreference.B_USER_SEX,bean.getData().getTfUser().isUSex());
                        SharePreferenceHelper.putBoolean(ConstantPreference.B_USER_LOGIN,true);
                        SharePreferenceHelper.apply();
                        mBindCallback.onBindSuccess(bean);
                        break;
                    default:
                        mBindCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mBindCallback!=null){
                    mBindCallback.onBindFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void rongIMLogin(String token, RongIMClient.ConnectCallback callback) {
        this.mConnectCallback = callback;
        IMLogin.imLogin(token,mConnectCallback);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCodeCallback = null;
        mBindCallback = null;
        this.mConnectCallback = null;
    }
}
