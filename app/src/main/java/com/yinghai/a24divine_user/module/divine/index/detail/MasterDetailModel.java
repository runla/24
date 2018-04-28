package com.yinghai.a24divine_user.module.divine.index.detail;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.MasterDetailBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/21 13:40
 *         Describe：获取占卜师详情M层
 */

public class MasterDetailModel extends BaseModel implements ContractMasterDetail.IModel {

    private IMasterDetailCallabck mCallback;

    @Override
    public void onMasterDetail(int masterId, IMasterDetailCallabck callabck) {
        mCallback = callabck;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(4);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, -1));
        maps.put("masterId", masterId);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.MASTER_DETAIL, maps, new HttpResponseCallback<MasterDetailBean>() {
            @Override
            public void onSuccess(MasterDetailBean bean) {
                if (mCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mCallback.onMasterDetailSuccess(bean.getData());
                        break;
                    default:
                        mCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mCallback != null) {
                    mCallback.onMasterDetailFailuer(errorMsg);
                }
            }
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mCallback = null;
    }
}
