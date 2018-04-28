package com.yinghai.a24divine_user.module.divine.book.pay;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.AliPayBean;
import com.yinghai.a24divine_user.bean.WechatPayBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/16 17:11
 *         Describe：
 */

public class WeChatPayModel extends BaseModel implements ContractPay.IPayModel{


    private IPayCallback mCallback;
    private IAliPayCallback mAliPayCallback;

    @Override
    public void wechatPay(String orderNo,int orderType,String spbillCreateIp,IPayCallback callback) {
        mCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(6);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID,0));
        maps.put("orderNo",orderNo);
        maps.put("orderType",orderType);
        maps.put("spbillCreateIp",spbillCreateIp);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.WECHAT_PAY,maps, new HttpResponseCallback<WechatPayBean>() {
            @Override
            public void onSuccess(WechatPayBean bean) {
                if (mCallback ==null){
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mCallback.paySuccess(bean);
                        break;
                    default:
                        mCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mCallback!=null){
                    mCallback.payFailure(errorMsg);
                }
            }
        });

    }

    @Override
    public void aliPay(String orderNo, int orderType, IAliPayCallback callback) {
        mAliPayCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(5);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID,0));
        maps.put("orderNo",orderNo);
        maps.put("orderType",orderType);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.ALI_PAY,maps, new HttpResponseCallback<AliPayBean>() {
            @Override
            public void onSuccess(AliPayBean bean) {
                if (mAliPayCallback ==null){
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mAliPayCallback.onPaySuccess(bean);
                        break;
                    default:
                        mAliPayCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mAliPayCallback!=null){
                    mAliPayCallback.onPayFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCallback = null;
        mAliPayCallback = null;
    }
}
