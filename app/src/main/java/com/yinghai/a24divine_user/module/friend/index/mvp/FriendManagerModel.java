package com.yinghai.a24divine_user.module.friend.index.mvp;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.FriendListBean;
import com.yinghai.a24divine_user.bean.NoDataBean;
import com.yinghai.a24divine_user.bean.PersonInfoBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/27 14:47
 *         Describe：添加/删除好友的M层
 */

public class FriendManagerModel extends BaseModel implements ContractFriend.IModel{

    private IAddCallback mAddCallback;
    private IShowCallback mShowCallback;
    private IDelCallback mDelCallback;
    private IRequestCallback mRequestCallback;

    @Override
    public void onAddFriend(int friendId,IAddCallback callback) {
        mAddCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(4);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID,0));
        maps.put("friendId", friendId);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.ADD_FRIEND,maps, new HttpResponseCallback<FriendListBean>() {
            @Override
            public void onSuccess(FriendListBean bean) {
                if (mAddCallback==null){
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mAddCallback.onAddFriendSuccess();
                        break;
                    default:
                        mAddCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mAddCallback!=null){
                    mAddCallback.onAddFriendFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void onDelFriend(int friendId, IDelCallback callback) {
        mDelCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(5);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID,0));
        maps.put("friendId", friendId);
        maps.put("type",1);// 1删除；2拉黑
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.DELETE_FRIEND,maps, new HttpResponseCallback<NoDataBean>() {
            @Override
            public void onSuccess(NoDataBean bean) {
                if (mDelCallback==null){
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mDelCallback.onDelFriendSuccess();
                        break;
                    default:
                        mDelCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mDelCallback!=null){
                    mDelCallback.onDelFriendFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void onshowFriendInfo(int targetId, IShowCallback callback) {
        mShowCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(4);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID,-1));
        maps.put("targetId", targetId);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.USER_INFO_BYID,maps, new HttpResponseCallback<PersonInfoBean>() {
            @Override
            public void onSuccess(PersonInfoBean bean) {
                if (mShowCallback==null){
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mShowCallback.onFriendInfoSuccess(bean.getData());
                        break;
                    default:
                        mShowCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mShowCallback!=null){
                    mShowCallback.onFriendInfoFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void onFriendRequest(int friendId, int type, IRequestCallback callback) {
        mRequestCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(5);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID,0));
        maps.put("friendId", friendId);
        maps.put("type", type);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.FRIEND_REQUEST,maps, new HttpResponseCallback<FriendListBean>() {
            @Override
            public void onSuccess(FriendListBean bean) {
                if (mRequestCallback==null){
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mRequestCallback.onFriendRequestSuccess(bean.getData());
                        break;
                    default:
                        mRequestCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mRequestCallback!=null){
                    mRequestCallback.onFriendRequestFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAddCallback = null;
        mShowCallback = null;
        mDelCallback = null;
        mRequestCallback = null;
    }
}
