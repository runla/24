package com.yinghai.a24divine_user.module.login.findpassword;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.NoDataBean;
import com.yinghai.a24divine_user.bean.PersonInfoBean;
import com.yinghai.a24divine_user.bean.VerifyCodeBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.utils.EncryptUtil;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenjianrun on 2017/12/19.
 * 描述：注册的 Model
 */

public class FindPasswordModel extends BaseModel implements ContractFindPassword.IRegisterModel{
    private ISendVerifyCodeSuccess mISendVerifyCodeSuccess;
    private IRegisterSuccess mIRegisterSuccess;
    private IFindPasswordCallback mIFindPasswordCallback;

    @Override
    public void sendVerifyCode(String countryCode, String telNo, int type, ISendVerifyCodeSuccess callback) {
        this.mISendVerifyCodeSuccess = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(7);
        maps.put("countryCode",countryCode);
        maps.put("tel",telNo);
        maps.put("type", type);
        maps.put("uDeviceId", SharePreferenceHelper.getString(ConstantPreference.S_DEVICE_ID,null));
        maps.put("uDeviceType", 2);
        maps.put("apiSendTime",time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));

        HttpUtils.getHttpUtils().post(ConHttp.USER_REGISTER_GET_VERIFY, maps, new HttpResponseCallback<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {
                if (mISendVerifyCodeSuccess == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        SharePreferenceHelper.putString(ConstantPreference.S_SESSION_ID,bean.getData().getSessionId());
                        SharePreferenceHelper.apply();
                        mISendVerifyCodeSuccess.onGetVerifyCodeSuccess();
                        break;

                    default:
                        mISendVerifyCodeSuccess.handlerResultCode(bean.getCode());
                        break;
                }

            }

            @Override
            public void onFailure(String s) {
                if (mISendVerifyCodeSuccess != null) {
                    mISendVerifyCodeSuccess.onGetdVerifyCodeFailure(s);
                }
            }
        });
    }

    @Override
    public void register(String countryCode, String telNo, String verification, IRegisterSuccess callback) {
        this.mIRegisterSuccess = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(7);
        maps.put("countryCode",countryCode);
        maps.put("tel",telNo);
        maps.put("verification", verification);
        maps.put("uDeviceId", SharePreferenceHelper.getString(ConstantPreference.S_DEVICE_ID,null));
        maps.put("uDeviceType", 2);
        maps.put("apiSendTime",time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));

        HttpUtils.getHttpUtils().post(ConHttp.USER_REGISTER, maps, new HttpResponseCallback<PersonInfoBean>() {
            @Override
            public void onSuccess(PersonInfoBean bean) {
                if (mIRegisterSuccess == null) {
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
                        SharePreferenceHelper.apply();
                        mIRegisterSuccess.onRegisterSuccess(bean);
                        break;
                    default:
                        mIRegisterSuccess.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String s) {
                if (mIRegisterSuccess != null) {
                    mIRegisterSuccess.onRegisterFailure(s);
                }
            }
        });
    }

    @Override
    public void findPassword(String countryCode, String telNo, String verification, String password, IFindPasswordCallback callback) {
        this.mIFindPasswordCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(7);
        maps.put("countryCode",countryCode);
        maps.put("tel",telNo);
        maps.put("verification", verification);
        maps.put("password", EncryptUtil.MD5(password));
        maps.put("uDeviceId", SharePreferenceHelper.getString(ConstantPreference.S_DEVICE_ID,null));
        maps.put("uDeviceType", 2);
        maps.put("apiSendTime",time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));

        HttpUtils.getHttpUtils().post(ConHttp.USER_FIND_PASSWORD, maps, new HttpResponseCallback<NoDataBean>() {
            @Override
            public void onSuccess(NoDataBean bean) {
                if (mIFindPasswordCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mIFindPasswordCallback.onFindPasswordSuccess();
                        break;

                    case ConResultCode.NOT_REGISTER:
                        mIFindPasswordCallback.onFindPasswordFailure(bean.getMsg());
                        break;

                    default:
                        mIFindPasswordCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String s) {
                if (mIFindPasswordCallback != null) {
                    mIFindPasswordCallback.onFindPasswordFailure(s);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIFindPasswordCallback = null;
        mISendVerifyCodeSuccess = null;
        mIRegisterSuccess = null;
    }
}
