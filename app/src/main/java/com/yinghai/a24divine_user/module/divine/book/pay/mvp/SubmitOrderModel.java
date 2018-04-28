package com.yinghai.a24divine_user.module.divine.book.pay.mvp;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.SubmitOrderBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/16 14:07
 *         Describe：提交订单M层实现
 */

public class SubmitOrderModel extends BaseModel implements ContractSubmitOrder.IModel {

    private ISubmitCallback mCallback;

    @Override
    public void submitOrder(int masterId,int businessId,int sex,String appointmentTime,String birthday,String describe,ISubmitCallback callback) {
        mCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(10);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID,0));
        maps.put("masterId",masterId);
        maps.put("sex",sex);
        maps.put("appointmentTime",appointmentTime);
        maps.put("businessId",businessId);
        maps.put("name",SharePreferenceHelper.getString(ConstantPreference.S_USER_NAME,null));
        maps.put("birthday",birthday);
        if (describe.length()!=0){
            maps.put("describe",describe);
        }
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.SUBMIT_ORDER,maps, new HttpResponseCallback<SubmitOrderBean>() {
            @Override
            public void onSuccess(SubmitOrderBean bean) {
                if (mCallback ==null){
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mCallback.onSubmitSuccess(bean.getData());
                        break;
                    default:
                        mCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mCallback!=null){
                    mCallback.onSubmitFailure(errorMsg);
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
