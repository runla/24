package com.yinghai.a24divine_user.module.shop.shopcar.submit;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.NoDataBean;
import com.yinghai.a24divine_user.bean.SubmitShopCarBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.http.ApiFactoryImpl;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/13 16:31
 *         Describe：提交购物车的M层
 */

public class SubmitShopCarModel extends BaseModel implements ContractSubmitShopCar.ISubmitModel {

    private ISubmitCallback mSubmitCallback;
    private IDeleteCallback mDeleteCallback;

    @Override
    public void submitShopCar(String carIds, String amounts, int addressId, ISubmitCallback callback) {
        mSubmitCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(6);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, 0));
        maps.put("carIds", carIds);
        maps.put("addressId", addressId);
        maps.put("quantities", amounts);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        //TODO 底层封装有问题，有时会不触发，所以这里没有使用统一的方式
        addSubscrebe(new ApiFactoryImpl().createApi(ConHttp.SUBMIT_SHOP_CAR, maps), new ResourceSubscriber<SubmitShopCarBean>() {
            @Override
            public void onNext(SubmitShopCarBean bean) {
                if (mSubmitCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mSubmitCallback.onSubmitSuccess(bean);
                        break;
                    case ConResultCode.NOT_REGISTER:
                        mSubmitCallback.onSubmitFailure(bean.getMsg());
                        break;
                    default:
                        mSubmitCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onError(Throwable t) {
                if (mSubmitCallback!=null){
                    mSubmitCallback.onSubmitFailure(t.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void deleteShopCar(final int carId, IDeleteCallback callback) {
        mDeleteCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(4);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, 0));
        maps.put("carId", carId);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.DELETE_SHOP_CAR, maps, new HttpResponseCallback<NoDataBean>() {
            @Override
            public void onSuccess(NoDataBean bean) {
                if (mDeleteCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mDeleteCallback.onDeleteSuccess(carId, bean);
                        break;
                    default:
                        mDeleteCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mDeleteCallback != null) {
                    mDeleteCallback.onDeleteFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void edtiShopCar(int carId, IEditCallback callback) {
//        String time = String.valueOf(System.currentTimeMillis());
//        Map<String,Object> maps = new HashMap<>(5);
//        maps.put("userId", 5);
//        maps.put("carId",carId);
//        maps.put("Authorization", "46541231test");
//        maps.put("apiSendTime", String.valueOf(System.currentTimeMillis()));
//        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
//        HttpUtils.getHttpUtils().post(ConHttp.DELETE_SHOP_CAR,maps, new HttpResponseCallback<NoDataBean>() {
//            @Override
//            public void onSuccess(NoDataBean bean) {
//                if (mDeleteCallback ==null){
//                    return;
//                }
//                switch (bean.getCode()) {
//                    case ConResultCode.SUCCESS:
//                        mDeleteCallback.onDeleteSuccess(carId, bean);
//                        break;
//                    default:
//                        mDeleteCallback.handlerResultCode(bean.getCode());
//                        break;
//                }
//            }
//
//            @Override
//            public void onFailure(String errorMsg) {
//                if (mDeleteCallback!=null){
//                    mDeleteCallback.onDeleteFailure(errorMsg);
//                }
//            }
//        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubmitCallback = null;
        mDeleteCallback = null;
    }
}
