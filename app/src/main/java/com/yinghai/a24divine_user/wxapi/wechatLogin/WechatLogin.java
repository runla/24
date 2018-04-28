package com.yinghai.a24divine_user.wxapi.wechatLogin;

import android.content.Context;

import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yinghai.a24divine_user.R;

/**
 * Created by chenjianrun on 2017/5/4.
 * 描述：微信登录调起类，并将调起结果通过 WechatLoginCallback 进行结果回调
 */

public class WechatLogin {
    private IWXAPI iwxapi;
    private static WechatLogin wechatLogin;
    private WechatLoginCallback mWechatLoginCallback;
    private WechatLogin() {
    }

    public static WechatLogin getInstance() {
        if (wechatLogin == null) {
            synchronized (WechatLogin.class) {
                if (wechatLogin == null) {
                    wechatLogin = new WechatLogin();
                }
            }
        }
        return wechatLogin;
    }

    public IWXAPI getIwxapi() {
        if (mWechatLoginCallback != null) {
            mWechatLoginCallback.showWechatLoginSuccess();
        }
        return iwxapi;
    }

    /**
     * 调起微信登录
     */
    public void wechatLogin(Context context, String wxAppId,WechatLoginCallback callback) {
        iwxapi = WXAPIFactory.createWXAPI(context, null);
        iwxapi.registerApp(wxAppId);
        this.mWechatLoginCallback = callback;
        //检测是否安装微信
        if (!isSupportWXPay()) {
            callback.wechatLoginFailed(context.getString(R.string.wechat_no_support));
            return;
        }
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "diandi_wx_login";
        // sendReq是第三方app主动发送消息给微信，发送完成之后会切回到第三方app界面。
        iwxapi.sendReq(req);
    }

    /**
     * 检测是否支持微信
     * @return
     */
    private boolean isSupportWXPay() {
        return iwxapi.isWXAppInstalled() && iwxapi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
    }
    /**
     * 处理微信登录结果
     *
     * @param baseResp
     */
    public void onResp(BaseResp baseResp) {
        if (mWechatLoginCallback == null) {
            return;
        }
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                String code = ((SendAuth.Resp) baseResp).code;
                mWechatLoginCallback.wechatLoginSuccess(code);
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                mWechatLoginCallback.wechatLoginFailed(baseResp.errStr);
                break;
            default:
                mWechatLoginCallback.wechatLoginFailed(baseResp.errStr);
                break;
//           //拒绝登录
//            case BaseResp.ErrCode.ERR_AUTH_DENIED:
//            //取消登录
//            case BaseResp.ErrCode.ERR_USER_CANCEL:
//            //登录失败
//            case BaseResp.ErrCode.ERR_SENT_FAILED:
        }
    }

    /**
     * 释放监听
     */
    public void releaseCallback(){
        mWechatLoginCallback = null;
    }
}
