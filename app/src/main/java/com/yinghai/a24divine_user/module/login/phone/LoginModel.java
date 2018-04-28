package com.yinghai.a24divine_user.module.login.phone;

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
import com.yinghai.a24divine_user.utils.EncryptUtil;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

import io.rong.imlib.RongIMClient;

/**
 * @author  Created by：fanson
 * Created on：2017/10/25 15:44
 * Describe：登录的M层（数据）实现类
 */

public class LoginModel extends BaseModel implements ContractLogin.ILoginModel{

    private ILoginCallback mCallback;
    private IGetCodeCallback mGetCodeCallback;
    private RongIMClient.ConnectCallback mConnectCallback;

    @Override
    public void verifyLogin(String countryCode, String telNo, String verifyCode, ILoginCallback callback) {
        mCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(7);
        maps.put("countryCode",countryCode);
        maps.put("tel",telNo);
        maps.put("verification", verifyCode);
        maps.put("uDeviceId", SharePreferenceHelper.getString(ConstantPreference.S_DEVICE_ID,null));
        maps.put("uDeviceType", 2);
        maps.put("apiSendTime",time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));

        HttpUtils.getHttpUtils().post(ConHttp.LOGIN_PHONE,maps, new HttpResponseCallback<PersonInfoBean>() {
            @Override
            public void onSuccess(PersonInfoBean bean) {
                if (mCallback ==null){
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
                        mCallback.onLoginSuccess(bean);
                        break;

                    case ConResultCode.NOT_REGISTER:
                        mCallback.onLoginFailure(bean.getMsg());
                        break;

                    default:
                        mCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mCallback!=null){
                    mCallback.onLoginFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void pwdLogin(String countryCode, String telNo, String password, ILoginCallback callback) {
        mCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(7);
        maps.put("countryCode",countryCode);
        maps.put("tel",telNo);
        maps.put("password", EncryptUtil.MD5(password));// todo:md5 加密
        maps.put("uDeviceId", SharePreferenceHelper.getString(ConstantPreference.S_DEVICE_ID,null));
        maps.put("uDeviceType", 2);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.LOGIN_PWD,maps, new HttpResponseCallback<PersonInfoBean>() {
            @Override
            public void onSuccess(PersonInfoBean bean) {
                if (mCallback ==null){
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
                        mCallback.onLoginSuccess(bean);
                        break;

                    case ConResultCode.NOT_REGISTER:
                        mCallback.onLoginFailure(bean.getMsg());
                        break;

                    default:
                        mCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mCallback!=null){
                    mCallback.onLoginFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void getVerifyCode(String countryCode, String telNo, IGetCodeCallback callback) {
        mGetCodeCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(5);
        maps.put("countryCode",countryCode);
        maps.put("tel",telNo);
        maps.put("uDeviceType", 2);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.GET_CODE,maps, new HttpResponseCallback<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {
                if (mGetCodeCallback == null){
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        SharePreferenceHelper.putString(ConstantPreference.S_SESSION_ID,bean.getData().getSessionId());
                        SharePreferenceHelper.apply();
                        mGetCodeCallback.onGetCodeSuccess();
                        break;
                    default:
                        mGetCodeCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mGetCodeCallback!=null){
                    mGetCodeCallback.onGetCodeFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void rongIMLogin(String token, RongIMClient.ConnectCallback connectCallback) {
        mConnectCallback = connectCallback;
        IMLogin.imLogin(token,mConnectCallback);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCallback = null;
        mGetCodeCallback = null;
        mConnectCallback = null;
    }
}
