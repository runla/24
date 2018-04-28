package com.yinghai.a24divine_user.module.shop.shopcar.mvp;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.ShopCarBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
* @author Created by：fanson
* Created on：2017/11/13 15:37
* Description：购物车的M层
*/

public class ShopCarModel extends BaseModel implements ContractShopCar.IShopCarModel{

    private IShopCarCallback mCallback;
    private final int mPageSize = 10;

    @Override
    public void getShopCarSuccess(int page,IShopCarCallback callback) {
        mCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(5);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID,0));
        maps.put("page",page);
        maps.put("pageSize",mPageSize);
        maps.put("apiSendTime",time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.GET_SHOP_CAR,maps, new HttpResponseCallback<ShopCarBean>() {
            @Override
            public void onSuccess(ShopCarBean bean) {
                if (mCallback ==null){
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mCallback.onShopCarSuccess(bean);
                        break;
                    default:
                        mCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mCallback!=null){
                    mCallback.onShopCarFailure(errorMsg);
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
