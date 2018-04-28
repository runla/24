package com.yinghai.a24divine_user.module.address.edit;

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
 *         Describe：编辑地址M层
 */

public class EditAddressModel extends BaseModel implements ContractEditAddress.IModel {
    public static final int TYPE_DEFAULE = 0;           // 修改类型：添加地址
    public static final int TYPE_UPDATE_DEFAULT = 1;    // 修改类型：修改默认地址
    public static final int TYPE_UPDATE = 2;            // 修改类型：修改地址
    private IEditAddressLCallback mEditCallback;

    @Override
    public void editAddress(int addressId, int type, String name, boolean sex, String countryCode, String tel, String street, String details, IEditAddressLCallback callback) {
        mEditCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(10);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, 0));
        if (addressId != 0) {
            maps.put("addressId", addressId);
        }
        if (type != 0) {
            maps.put("type", type);
        }
        maps.put("name", name);
        if (sex) {
            maps.put("sex", 1);
        } else {
            maps.put("sex", 0);
        }
        maps.put("countryCode", countryCode);
        maps.put("tel", tel);
        maps.put("street", street);
        maps.put("details", details);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.EDIT_ADDRESS, maps, new HttpResponseCallback<AddressListBean>() {
            @Override
            public void onSuccess(AddressListBean bean) {
                if (mEditCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mEditCallback.onEditAddressSuccess(bean);
                        break;
                    default:
                        mEditCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mEditCallback != null) {
                    mEditCallback.onEditAddressFailure(errorMsg);
                }
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mEditCallback = null;
    }
}
