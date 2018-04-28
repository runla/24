package com.yinghai.a24divine_user.wxapi.pay;

import android.app.Activity;
import android.text.TextUtils;

import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by chenjianrun on 2017/4/11.
 */

public class WechatPay {
    public static final int ERROR_CODE_WECHAT_NOT_EXIST = 550;  // 没有安装微信
    public static final int ERROR_CODE_NULL = 551;              // bean 字段有为空的字段
    public static final int ERROR_CODE_CALL_PAY_FAILED = 552;   // 微信支付失败
    public static final int ERROR_CODE_CALL_PAY_CANCEL = 553;   // 微信支付取消

    private IWXAPI iwxapi;
    private static WechatPay wechatPay;
    private com.yinghai.a24divine_user.bean.WechatPayBean.DataBeanX.DataBean wechatPayBean;
    private static WechatPayCallback wxPayCallback;

    private WechatPay(){

    }

    public static WechatPay getInstance(){
        if (wechatPay == null) {
            synchronized (WechatPay.class){
                if (wechatPay == null) {
                    wechatPay = new WechatPay();
                }
            }
        }
        return wechatPay;
    }

    public IWXAPI getIwxapi(){
        //该方法被调用时，说明已经出现支付的窗口
        if (wxPayCallback != null) {
            wxPayCallback.showPayLoading();
        }
        return iwxapi;
    }

    public void startPay(Activity activity, String wxAppId, com.yinghai.a24divine_user.bean.WechatPayBean.DataBeanX.DataBean payBean, WechatPayCallback payCallback) {
        initWechat(activity,wxAppId);
        this.wechatPayBean = payBean;
        wxPayCallback =payCallback;
        //wechatPayBean.setSign(createSign());//重新生成簽名


        //检查是否支持微信支付
        if (!isSupportWXPay()) {
            if (wxPayCallback != null) {
                wxPayCallback.payFailure(ERROR_CODE_WECHAT_NOT_EXIST);
            }
            return;
        }

        //检查某个字段是否为空
        if(isHasNull(wechatPayBean)) {
            wxPayCallback.payFailure(ERROR_CODE_NULL);
            return;
        }


        PayReq req = new PayReq();
        req.appId = wechatPayBean.getAppid();
        req.partnerId = wechatPayBean.getPartnerid();
        req.prepayId = wechatPayBean.getPrepayid();
        req.packageValue = wechatPayBean.getPackageX();
        req.nonceStr = wechatPayBean.getNoncestr();
        req.timeStamp = wechatPayBean.getTimestamp();
        req.sign = wechatPayBean.getSign();
        //调起微信支付
        iwxapi.sendReq(req);
    }

    /**
     * 微信初始化
     * @param activity
     * @param wxAppId
     */
    private void initWechat(Activity activity, String wxAppId){
        // 微信初始化
        iwxapi = WXAPIFactory.createWXAPI(activity, null);
        iwxapi.registerApp(wxAppId);
    }

    private boolean isHasNull(com.yinghai.a24divine_user.bean.WechatPayBean.DataBeanX.DataBean payBean){
        //检查某个字段是否为空
        if(payBean == null || TextUtils.isEmpty(payBean.getAppid())
                || TextUtils.isEmpty(payBean.getPartnerid())
                || TextUtils.isEmpty(payBean.getPrepayid())
                || TextUtils.isEmpty(payBean.getPackageX())
                || TextUtils.isEmpty(payBean.getNoncestr())
                || TextUtils.isEmpty(payBean.getTimestamp())
                || TextUtils.isEmpty(payBean.getSign())) {

            return true;
        }
        return false;
    }


    /**
     * 微信支付结果回调
     * @param err_code
     */
    public void onResp(int err_code){
        if(wxPayCallback == null) {
            return;
        }
        if(err_code == 0) {             // 成功
            wxPayCallback.paySuccess();
        } else if(err_code == -2) {     // 取消
            wxPayCallback.payFailure(ERROR_CODE_CALL_PAY_CANCEL);
        } else {
            wxPayCallback.payFailure(ERROR_CODE_CALL_PAY_FAILED);
        }
        wxPayCallback = null;
    }

    /**
     * 检测是否支持微信支付
     * @return
     */
    private boolean isSupportWXPay() {
        return iwxapi.isWXAppInstalled() && iwxapi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
    }

    /**
     * 生成參數簽名，這樣是為了防止服務器返回的是有無的簽名
     * @return
     */
    private String createSign(String key){
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", wechatPayBean.getAppid());
        parameters.put("partnerid", wechatPayBean.getPartnerid());
        parameters.put("noncestr", wechatPayBean.getNoncestr());
        parameters.put("prepayid", wechatPayBean.getPrepayid());
        parameters.put("package", wechatPayBean.getPackageX());
        parameters.put("timestamp", wechatPayBean.getTimestamp());
        return WXPayUtil.createSign("UTF-8", parameters,key);
    }
}
