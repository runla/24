package com.yinghai.a24divine_user.module.divine.divinelist.model;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.BusinessTypeListBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by：fanson
 *         Created Time: 2017/12/28 14:13
 *         Describe：获取类型的M层
 */

public class GetBusinessTypeModel extends BaseModel implements ContractBusinessType.IModel{

    private ITypeCallback mCallback;

    @Override
    public void onGetBusinessType(ITypeCallback callback) {
        mCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(5);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, -1));
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.GET_BUSINESS_TYPE, maps, new HttpResponseCallback<BusinessTypeListBean>() {
            @Override
            public void onSuccess(BusinessTypeListBean bean) {
                if (mCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mCallback.onGetBusinessTypeSuccess(bean.getData());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mCallback != null) {
                    mCallback.onGetBusinessTypeFailure(errorMsg);
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
