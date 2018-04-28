package com.yinghai.a24divine_user.module.login.setpassword;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.PersonInfoBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.utils.EncryptUtil;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenjianrun on 2017/12/20.
 * 描述：设置密码的 Model 类
 */

public class SetPasswordModel extends BaseModel implements ContractSetPassword.ISetPasswordModel{
    private ISetPasswordCallback mISetPasswordCallback;
    @Override
    public void setPassword(String password, ISetPasswordCallback callback) {
        mISetPasswordCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(7);
        maps.put("password", EncryptUtil.MD5(password));
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID,0));
        maps.put("uDeviceId", SharePreferenceHelper.getString(ConstantPreference.S_DEVICE_ID,null));
        maps.put("uDeviceType", 2);
        maps.put("apiSendTime",time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));

        HttpUtils.getHttpUtils().post(ConHttp.EDIT_PERSON, maps, new HttpResponseCallback<PersonInfoBean>() {
            @Override
            public void onSuccess(PersonInfoBean bean) {
                if (mISetPasswordCallback == null) {
                    return;
                }

                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        SharePreferenceHelper.putBoolean(ConstantPreference.B_USER_LOGIN,true);
                        SharePreferenceHelper.apply();
                        mISetPasswordCallback.setPasswordSuccess(bean);
                        break;
                    default:
                        mISetPasswordCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String s) {
                if (mISetPasswordCallback != null) {
                    mISetPasswordCallback.setPasswordFailure(s);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mISetPasswordCallback = null;
    }
}
