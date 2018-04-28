package com.yinghai.a24divine_user.module.login.wechat;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.PersonInfoBean;
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
 *         Created on：2017/10/25 15:44
 *         Describe：登录的M层（数据）实现类
 */

public class ThirdLoginModel extends BaseModel implements ContractThird.IModel {

    private ILoginCallback mLoginCallback;
    private RongIMClient.ConnectCallback mConnectCallback;
    /**
     * 第三方登录
     *
     * @param type     登录方式： 0 微信；3 faceBook
     * @param code     第三方同意登录标识： facebook第三方登录的 code 为access token；
     * @param callback
     */
    @Override
    public void onThirdLogin(int type, String code, ILoginCallback callback) {
        mLoginCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(7);
        maps.put("type", type);
        maps.put("tfType", 0); // 0为用户端1为大师端
        maps.put("code", code);
        maps.put("deviceId", SharePreferenceHelper.getString(ConstantPreference.S_DEVICE_ID, null));
        maps.put("deviceType", 2); //1IOS;2安卓
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.THIRD_LOGIN, maps, new HttpResponseCallback<PersonInfoBean>() {
            @Override
            public void onSuccess(PersonInfoBean bean) {
                if (mLoginCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        SharePreferenceHelper.putInt(ConstantPreference.I_USER_ID, bean.getData().getTfUser().getUserId());
                        SharePreferenceHelper.putString(ConstantPreference.S_USER_BIRTHDAY, bean.getData().getTfUser().getUBirthday());
                        SharePreferenceHelper.putString(ConstantPreference.S_USER_PHOTO, bean.getData().getTfUser().getUImgUrl());
                        SharePreferenceHelper.putString(ConstantPreference.S_COUNTRY_CODE, bean.getData().getTfUser().getUCountryCode());
                        SharePreferenceHelper.putString(ConstantPreference.S_PHONE, bean.getData().getTfUser().getUTel());
                        SharePreferenceHelper.putString(ConstantPreference.S_USER_NAME, bean.getData().getTfUser().getUNick());
                        SharePreferenceHelper.putInt(ConstantPreference.I_USER_CONSTELLATION, bean.getData().getTfUser().getUConstellation());
                        SharePreferenceHelper.putBoolean(ConstantPreference.B_USER_SEX, bean.getData().getTfUser().isUSex());
                        SharePreferenceHelper.putBoolean(ConstantPreference.B_USER_LOGIN, true);
                        SharePreferenceHelper.apply();
                        mLoginCallback.onThirdSuccess(bean);
                        break;
                    case ConResultCode.IS_NEW_USER:
                        mLoginCallback.onIsNewUser(bean.getData().getThirdPartyId());
                        break;
                    default:
                        mLoginCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mLoginCallback != null) {
                    mLoginCallback.onThirdLoginFailure(errorMsg);
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
        mLoginCallback = null;
        mConnectCallback = null;
    }
}
