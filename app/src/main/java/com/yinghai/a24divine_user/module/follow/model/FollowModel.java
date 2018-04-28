package com.yinghai.a24divine_user.module.follow.model;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.FollowBean;
import com.yinghai.a24divine_user.bean.NoDataBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.module.follow.ContractFollow;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/25 16:31
 *         Describe：我关注的M层实现类
 */

public class FollowModel extends BaseModel implements ContractFollow.IFollowModel {

    private IFollowCallback mCallback;
    private ICancelFollowCallback mCancelFollowCallback;

    @Override
    public void onCancelFollow(int masterId,ICancelFollowCallback cancelFollowCallback) {
        mCancelFollowCallback = cancelFollowCallback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(5);
        maps.put("userId",SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID,0));
        maps.put("masterId",masterId);
        maps.put("uDeviceType", 2);
        maps.put("apiSendTime",time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.CANCEL_FOLLOW,maps, new HttpResponseCallback<NoDataBean>() {

            @Override
            public void onSuccess(NoDataBean noDataBean) {
                if (mCancelFollowCallback==null){
                    return;
                }
                switch (noDataBean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mCancelFollowCallback.onCancelFollowSuccess();
                        break;
                    default:
                        mCancelFollowCallback.onCancelFollowFailure(noDataBean.getMsg());
                        break;
                }
            }

            @Override
            public void onFailure(String s) {

            }
        });
    }


    @Override
    public void onGetFollowList(int page,int pageSize,IFollowCallback callback) {
        mCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(6);
        maps.put("userId",SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID,0));
        maps.put("page", page);
        maps.put("pageSize", pageSize);
        maps.put("uDeviceType", 2);
        maps.put("Authorization", SharePreferenceHelper.getString(ConstantPreference.S_DEVICE_ID,null));
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.MY_FOLLOW,maps, new HttpResponseCallback<FollowBean>() {
            @Override
            public void onSuccess(FollowBean bean) {
                if (mCallback==null){
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mCallback.onGetFollowListSuccess(bean);
                        break;
                    default:
                        mCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mCallback!=null){
                    mCallback.onGetFollowListFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCallback = null;
        mCancelFollowCallback = null;
    }

}
