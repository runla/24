package com.yinghai.a24divine_user.module.shop.details.addtocar;

import com.example.fansonlib.base.AppUtils;
import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.NoDataBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/13 14:57
 *         Describe：添加到购物车的M层实现
 */

public class AddToCarModel extends BaseModel implements ContractAddToCar.IAddToCarModel{

    private IAddToCarCallback mCallback;

    @Override
    public void addToCar(int productId,int count,IAddToCarCallback callback) {
        mCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(5);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID,0));
        maps.put("productId",productId);
        maps.put("quantity",count);
        maps.put("apiSendTime",time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.PRODUCT_ADD_CAR,maps, new HttpResponseCallback<NoDataBean>() {
            @Override
            public void onSuccess(NoDataBean bean) {
                if (mCallback ==null){
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mCallback.onAddToCarSuccess(bean);
                        break;
                    case ConResultCode.SOLD_OUT:
                        mCallback.onAddToCarFailure(AppUtils.getString(R.string.sold_out));
                        break;
                    default:
                        mCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mCallback!=null){
                    mCallback.onAddToCarFailure(errorMsg);
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
