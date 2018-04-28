package com.yinghai.a24divine_user.module.address.mylist;

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
 *         Created Time: 2017/11/17 11:33
 *         Describe：获取地址列表的M层
 */

public class GetAddressModel extends BaseModel implements ContractMyAddress.IModel{

    private IAddressListCallback mCallback;
    private static final int PAGE_SIZE = 10;

    @Override
    public void getAddressList(int pageNum,IAddressListCallback callback) {
        mCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(5);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID,0));
        maps.put("page",pageNum);
        maps.put("pageSize",PAGE_SIZE);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.GET_ADDRESS,maps, new HttpResponseCallback<AddressListBean>() {
            @Override
            public void onSuccess(AddressListBean bean) {
                if (mCallback ==null){
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mCallback.onGetAddressSuccess(bean);
                        break;
                    default:
                        mCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mCallback!=null){
                    mCallback.onGetAddressFailure(errorMsg);
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
