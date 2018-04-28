package com.yinghai.a24divine_user.module.setting.person;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.NoDataBean;
import com.yinghai.a24divine_user.bean.PersonInfoBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/21 15:47
 *         Describe：修改个人信息M层
 */

public class EditPersonModel extends BaseModel implements ContractEditPerson.IModel {

    private IEditCallback mCallback;
    private ILogoutCallback mLogoutCallback;

    @Override
    public void editPerson(String name, String birthday, int constellationId, boolean sex, String photoUrl, IEditCallback callback) {
        mCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(7);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, 0));
        maps.put("nick", name);
        maps.put("birthday", birthday);
        maps.put("sex", sex);
        maps.put("constellation", constellationId);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.EDIT_PERSON, maps, new HttpResponseCallback<PersonInfoBean>() {
            @Override
            public void onSuccess(PersonInfoBean bean) {
                if (mCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mCallback.onEditPersonSuccess(bean.getData());
                        SharePreferenceHelper.putString(ConstantPreference.S_USER_BIRTHDAY, bean.getData().getTfUser().getUBirthday());
                        SharePreferenceHelper.putString(ConstantPreference.S_USER_PHOTO, bean.getData().getTfUser().getUImgUrl());
                        SharePreferenceHelper.putString(ConstantPreference.S_USER_NAME, bean.getData().getTfUser().getUNick());
                        SharePreferenceHelper.putInt(ConstantPreference.I_USER_CONSTELLATION, bean.getData().getTfUser().getUConstellation());
                        SharePreferenceHelper.putBoolean(ConstantPreference.B_USER_SEX, bean.getData().getTfUser().isUSex());
                        SharePreferenceHelper.apply();
                        break;
                    default:
                        mCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mCallback != null) {
                    mCallback.onEditPersonFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void onLogout(ILogoutCallback callback) {
        mLogoutCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(3);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, 0));
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.LOGOUT, maps, new HttpResponseCallback<NoDataBean>() {
            @Override
            public void onSuccess(NoDataBean bean) {
                if (mLogoutCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        SharePreferenceHelper.clear();
                        mLogoutCallback.onLogoutSuccess();
                        break;
                    default:
                        mLogoutCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mLogoutCallback != null) {
                    mLogoutCallback.onLogoutFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCallback = null;
        mLogoutCallback = null;
    }
}
