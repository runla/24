package com.yinghai.a24divine_user.module.order.all.mvp;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.NoDataBean;
import com.yinghai.a24divine_user.bean.OrderBean;
import com.yinghai.a24divine_user.bean.ProductOrderListBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstOrderStatus;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by：fanson
 * Created on：2017/9/29 12:02
 * Describe：订单记录的M层实现类
 */

public class OrderModel extends BaseModel implements ContractOrder.IOrderModel {

    private IOrderCallback mCallback;
    private IProductCallback mProductCallback;
    private static final int PAGE_SIZE = 10;
    private static final int TYPE_PRODUCT = 3;
    private ICompleteOrderCallback mCompleteOrderCallback;
    @Override
    public void getOrder(String status, int orderType, int pageNum, IOrderCallback callback) {
        mCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(7);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, 0));
        if (status != null) {
            maps.put("statusArray", status);
        }
        if (orderType != ConstOrderStatus.ALL) {
            maps.put("orderType", orderType);
        }
        maps.put("page", pageNum);
        maps.put("pageSize", PAGE_SIZE);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.GET_ORDER, maps, new HttpResponseCallback<OrderBean>() {
            @Override
            public void onSuccess(OrderBean bean) {
                if (mCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mCallback.onOrderSuccess(bean);
                        break;
                    default:
                        mCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mCallback != null) {
                    mCallback.onOrderFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void getProductOrder(String status, int pageNum, IProductCallback callback) {
        mProductCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(7);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, 0));
        if (status != null) {
            maps.put("statusArray", status);
        }
        //订单类型 ：3商品订单
        maps.put("orderType", TYPE_PRODUCT);
        maps.put("page", pageNum);
        maps.put("pageSize", PAGE_SIZE);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.PRODUCT_ORDER_LIST, maps, new HttpResponseCallback<ProductOrderListBean>() {
            @Override
            public void onSuccess(ProductOrderListBean bean) {
                if (mProductCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mProductCallback.onProductListSuccess(bean);
                        break;
                    default:
                        mProductCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mProductCallback != null) {
                    mProductCallback.onProductListFailure(errorMsg);
                }
            }
        });
    }


    @Override
    public void completeOrder(int orderId,int type, ICompleteOrderCallback callback) {
        mCompleteOrderCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(4);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID,0));
        maps.put("orderId", orderId);
        maps.put("type",type);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.COMPLETE_ORDER, maps, new HttpResponseCallback<NoDataBean>() {
            @Override
            public void onSuccess(NoDataBean bean) {
                if (mCompleteOrderCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mCompleteOrderCallback.onCompleteOrderSuccess();
                        break;
                    default:
                        mCompleteOrderCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mCompleteOrderCallback != null) {
                    mCompleteOrderCallback.onCompleteOrderFailure(errorMsg);
                }
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mCallback = null;
        mProductCallback = null;
        mCompleteOrderCallback = null;
    }

}
