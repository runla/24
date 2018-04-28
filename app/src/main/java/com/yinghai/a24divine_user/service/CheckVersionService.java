package com.yinghai.a24divine_user.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.yinghai.a24divine_user.bean.CheckVersionBean;
import com.yinghai.a24divine_user.callback.ICheckVersionCallBack;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstBroadcastCode;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Created by：fanson
 *         Created Time: 2018/3/15 11:13
 *         Describe：检测版本更新的服务
 */

public class CheckVersionService extends Service implements ICheckVersionCallBack {

    private static final String TAG = CheckVersionService.class.getSimpleName();
    public static final String KEY_VERSION_DESCRIBE = "version_describe";
    public static final String KEY_FORCE_UPDATE = "force_update";


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isLatest(getCurrentVersion(getApplicationContext()), this);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 獲取當前程序的版本號
     *
     * @param mContext
     * @return
     */
    private int getCurrentVersion(Context mContext) {
        PackageManager packageManager = mContext.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null ? packageInfo.versionCode : 1;
    }

    /**
     * 检测是否为最新的版本号
     */
    public void isLatest(final int currentVersionCode, final ICheckVersionCallBack checkVersionCallBack) {
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(4);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        maps.put("deviceType", 2);
        maps.put("realm", "user");
        HttpUtils.getHttpUtils().post(ConHttp.CHECK_VERSION, maps, new HttpResponseCallback<CheckVersionBean>() {
            @Override
            public void onSuccess(CheckVersionBean bean) {
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        if (currentVersionCode < bean.getData().getVersionController().getVersionId()) {
                            checkVersionCallBack.onNotLatest(bean.getData().getVersionController().isForceUpdate(), bean.getData().getVersionController().getDescribe());
                        } else {
                            stopSelf();
                        }
                        break;
                    default:
                        Log.d(TAG, "check version failure. " + bean.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                Log.d(TAG, "check version failure. " + errorMsg);
                stopSelf();
            }
        });
    }

    @Override
    public void onNotLatest(final boolean isForceUpdate, String describe) {
        Log.d(TAG, "in main thread : " + (Looper.getMainLooper() == Looper.myLooper()));
        Intent intent = new Intent();
        intent.putExtra(KEY_VERSION_DESCRIBE, describe);
        intent.putExtra(KEY_FORCE_UPDATE, isForceUpdate);
        intent.setAction(ConstBroadcastCode.CHECK_VERSION);
        sendBroadcast(intent);
        stopSelf();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}


