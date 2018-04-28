package com.yinghai.a24divine_user.module.divine.selecttime;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.DivineScheduleTimeBean;
import com.yinghai.a24divine_user.bean.ScheduleBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/1 11:28
 *         Describe：选择预约时间的M层（获取日程）
 */

public class SelectModel extends BaseModel implements ContractSelectTime.ISelectModel {

    private IGetMasterCallback mGetMasterCallback;
    private IDivineScheduleTimeCallback mIDivineScheduleTimeCallback;

    @Override
    public void getMasterSchedule(int masterId, String date, IGetMasterCallback callback) {
        mGetMasterCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(6);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, 0));
        maps.put("masterId", masterId);
        maps.put("msDate", date);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.MASTER_TIME, maps, new HttpResponseCallback<ScheduleBean>() {
            @Override
            public void onSuccess(ScheduleBean bean) {
                if (mGetMasterCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mGetMasterCallback.onGetMasterScheduleSuccess(bean);
                        break;
                    default:
                        mGetMasterCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mGetMasterCallback != null) {
                    mGetMasterCallback.onGetMasterScheduleFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void getDivineScheduleTime(String date, String masterId, IDivineScheduleTimeCallback callback) {
        mIDivineScheduleTimeCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(6);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, 0));
        maps.put("masterId", masterId);
        maps.put("date", date);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.BOOK_SCKEDULE, maps, new HttpResponseCallback<DivineScheduleTimeBean>() {

            @Override
            public void onSuccess(DivineScheduleTimeBean bean) {
                if (mIDivineScheduleTimeCallback == null) {
                    return;
                }
                switch (Integer.parseInt(bean.getCode())) {
                    case ConResultCode.SUCCESS:
                        mIDivineScheduleTimeCallback.onGetDivineScheduleTimeSuccess(bean);
                        break;
                    default:
                        mIDivineScheduleTimeCallback.handlerResultCode(Integer.parseInt(bean.getCode()));
                        break;
                }
            }

            @Override
            public void onFailure(String s) {
                if (mIDivineScheduleTimeCallback != null) {
                    mIDivineScheduleTimeCallback.onGetDivineScheduleTimeFailure(s);
                }
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGetMasterCallback = null;
        mIDivineScheduleTimeCallback = null;
    }
}
