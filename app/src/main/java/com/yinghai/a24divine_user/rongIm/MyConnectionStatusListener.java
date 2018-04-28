package com.yinghai.a24divine_user.rongIm;

import android.util.Log;

import com.example.fansonlib.rxbus.MyRxbus2;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.constant.RxBusTag;
import com.yinghai.a24divine_user.module.login.state.LoginStateManager;
import com.yinghai.a24divine_user.module.login.state.LogoutState;

import io.rong.imlib.RongIMClient;

/**
 * Created by chenjianrun on 2017/12/4.
 * 描述：连接状态监听类
 */

public class MyConnectionStatusListener implements RongIMClient.ConnectionStatusListener{
    private static final String TAG = "MyConnectionStatusListe";
    @Override
    public void onChanged(ConnectionStatus connectionStatus) {
        switch (connectionStatus){
            //连接成功。
            case CONNECTED:
                Log.d(TAG, "onChanged: CONNECTED");
                break;

            //断开连接。
            case DISCONNECTED:
                Log.d(TAG, "onChanged: DISCONNECTED");
                break;

            //连接中。
            case CONNECTING:
                Log.d(TAG, "onChanged: CONNECTING");
                break;

            //网络不可用。
            case NETWORK_UNAVAILABLE:
                Log.d(TAG, "onChanged: NETWORK_UNAVAILABLE");
                break;

            //用户账户在其他设备登录，本机会被踢掉线
            case KICKED_OFFLINE_BY_OTHER_CLIENT:
                Log.d(TAG, "onChanged: KICKED_OFFLINE_BY_OTHER_CLIENT");
                SharePreferenceHelper.clear();
                LoginStateManager.getInstance().setState(new LogoutState());
                MyRxbus2.getInstance().send(RxBusTag.KICKED_OFFLINE);
                break;

            default:
                break;
        }
    }
}
