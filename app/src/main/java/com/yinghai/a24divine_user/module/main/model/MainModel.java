package com.yinghai.a24divine_user.module.main.model;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.LuckBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.module.main.ContractMain;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by：fanson
 * Created on：2017/10/24 13:26
 * Describe：运程的M层
 */

public class MainModel extends BaseModel implements ContractMain.IMainModel{
    private ContractMain.IMainModel.ILuckCallback mLuckCallback;

    @Override
    public void openLuck(ILuckCallback luckCallback) {
        this.mLuckCallback = luckCallback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(4);
        maps.put("uDeviceType", 2);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID,-1));
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.OPEN_LUCK, maps, new HttpResponseCallback<LuckBean>() {
            @Override
            public void onSuccess(LuckBean bean) {
                if (mLuckCallback == null) {
                    return;
                }

                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mLuckCallback.onGetLuckSuccess(bean);
                        break;

                    default:
                        mLuckCallback.handlerResultCode(bean.getCode());
                        break;
                }

            }

            @Override
            public void onFailure(String s) {
                if (mLuckCallback != null) {
                    mLuckCallback.onGetLuckFailure(s);
                }
            }
        });
    }

    @Override
    public void changeLuck(int constellation,ILuckCallback luckCallback) {
        this.mLuckCallback = luckCallback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(5);
        maps.put("constellation", constellation);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID,-1));
        maps.put("uDeviceType", 2);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.CHANGE_LUCK, maps, new HttpResponseCallback<LuckBean>() {
            @Override
            public void onSuccess(LuckBean bean) {
                if (mLuckCallback == null) {
                    return;
                }

                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mLuckCallback.onGetLuckSuccess(bean);
                        break;

                    default:
                        mLuckCallback.handlerResultCode(bean.getCode());
                        break;
                }

            }

            @Override
            public void onFailure(String s) {
                if (mLuckCallback != null) {
                    mLuckCallback.onGetLuckFailure(s);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLuckCallback = null;
    }
}
