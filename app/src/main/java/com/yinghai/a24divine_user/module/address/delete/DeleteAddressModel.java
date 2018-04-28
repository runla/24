package com.yinghai.a24divine_user.module.address.delete;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.AddressListBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/17 13:40
 *         Describe：删除地址M层
 */

public class DeleteAddressModel extends BaseModel implements ContractDeleteAddress.IModel {

    private IDeleteCallback mCallback;

    @Override
    public void deleteAddress(int addressId, IDeleteCallback callback) {
        mCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(4);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, 0));
        maps.put("addressId", addressId);
        maps.put("apiSendTime",time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.DELETE_ADDRESS, maps, new HttpResponseCallback<AddressListBean>() {
            @Override
            public void onSuccess(AddressListBean bean) {
                if (mCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mCallback.onDeleteSuccess(bean);
                        break;
                    default:
                        mCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mCallback != null) {
                    mCallback.onDeleteFailure(errorMsg);
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
