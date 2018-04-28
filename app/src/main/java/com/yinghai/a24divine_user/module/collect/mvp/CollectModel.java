package com.yinghai.a24divine_user.module.collect.mvp;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.CollectBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/10 13:54
 *         Describe：获取我的收藏的Model实现
 */

public class CollectModel extends BaseModel implements ContractCollect.ICollectModel {

    private ICollectCallback mCallback;
    private ICancelCollectCallback mCancelCallback;
    private IAddCollectCallback mAddCallback;
    private static final int pageSize = 10;

    @Override
    public void getCollect(int pageNum, ICollectCallback callback) {
        mCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(5);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, 0));
        maps.put("page", pageNum);
        maps.put("pageSize", pageSize);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.MY_COLLECT, maps, new HttpResponseCallback<CollectBean>() {
            @Override
            public void onSuccess(CollectBean bean) {
                if (mCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mCallback.onCollectSuccess(bean);
                        break;
                    default:
                        mCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mCallback != null) {
                    mCallback.onCollectFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void addCollect(final int type,final int id, IAddCollectCallback callback) {
        this.mAddCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(5);
        maps.put("type", type);
        maps.put("id", id);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, 0));
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));

        HttpUtils.getHttpUtils().post(ConHttp.ADD_COLLECT, maps, new HttpResponseCallback<CollectBean>() {

            @Override
            public void onSuccess(CollectBean bean) {
                if (mAddCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mAddCallback.onAddCollectSuccess(bean,id);
                        break;

                    case ConResultCode.REPEAT_COLLECT:
                        mAddCallback.onAddCollectSuccess(bean,id);
                        break;

                    default:
                        mAddCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String s) {
                if (mAddCallback != null) {
                    mAddCallback.onAddCollectFailure(s);
                }
            }
        });
    }

    @Override
    public void cancelCollect(int type, final int keyId, ICancelCollectCallback callback) {
        this.mCancelCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(5);
        maps.put("type", type);
        maps.put("keyId", keyId);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, 0));
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));

        HttpUtils.getHttpUtils().post(ConHttp.CANCEL_COLLECT, maps, new HttpResponseCallback<CollectBean>() {

            @Override
            public void onSuccess(CollectBean bean) {
                if (mCancelCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mCancelCallback.onCancelCollectSuceess(bean,keyId);
                        break;

                    case ConResultCode.REPEAT_CANCEL_COLLECT:
                        mCancelCallback.onCancelCollectSuceess(bean,keyId);
                        break;

                    default:
                        mCancelCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String s) {
                if (mCancelCallback != null) {
                    mCancelCallback.onCancelCollectFailure(s);
                }
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCallback = null;
        mCancelCallback = null;
        mAddCallback = null;
    }
}
