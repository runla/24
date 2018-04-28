package com.yinghai.a24divine_user.wxapi.wechatLogin;

import android.app.Activity;
import android.os.Bundle;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * Created by chenjianrun on 2017/5/4.
 */

public abstract class WXEntryBaseActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = "WXEntryBaseActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WechatLogin.getInstance().getIwxapi().handleIntent(getIntent(),this);
    }

    /**
     * 设置 APPID
     * @return
     */
//    public abstract String getAppId();

    @Override
    public void onReq(BaseReq baseReq) {
    }

    @Override
    public void onResp(BaseResp baseResp) {
        WechatLogin.getInstance().onResp(baseResp);
        finish();
    }
}
