package com.yinghai.a24divine_user.module.shop.details;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.ProductDetailBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by：fanson
 *         Created on：2017/10/17 11:53
 *         Describe：获取文章评论的M层（数据）实现类
 */

public class ProductDetailModel extends BaseModel implements ContractDetails.ICommentsModel {

    private IDetailCallback mDetailCallback;

    @Override
    public void getDetails(int productId, IDetailCallback callback) {
        mDetailCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(4);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, -1));
        maps.put("productId", productId);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));

        HttpUtils.getHttpUtils().post(ConHttp.PRODUCT_DETAIL, maps, new HttpResponseCallback<ProductDetailBean>() {
            @Override
            public void onSuccess(ProductDetailBean bean) {
                if ( mDetailCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mDetailCallback.onIDetailSuccess(bean.getData());
                        break;
                    default:
                        mDetailCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mDetailCallback != null) {
                    mDetailCallback.onIDetailFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDetailCallback = null;
    }
}
