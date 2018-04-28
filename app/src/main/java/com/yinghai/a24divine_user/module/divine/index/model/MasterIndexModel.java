package com.yinghai.a24divine_user.module.divine.index.model;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.BusinessBean;
import com.yinghai.a24divine_user.bean.FollowBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.module.divine.index.ContractMasterIndex;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by chenjianrun on 2017/10/30.
 * 描述：占卜师主界面，获取占卜师业务的 Model
 */

public class MasterIndexModel extends BaseModel implements ContractMasterIndex.IMasterIndexModel {

    private IGetBusinessCallabck mCallback;
    private IFollowCallabck mFollowCallabck;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private static final int PAGE_SIZE = 10;

    @Override
    public void getBusiness(int masterId, int pageNum, IGetBusinessCallabck callabck) {
        mCallback = callabck;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(6);
            maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, -1));
        maps.put("masterId", masterId);
        maps.put("page", pageNum);
        maps.put("pageSize", PAGE_SIZE);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.BUSINESS, maps, new HttpResponseCallback<BusinessBean>() {
            @Override
            public void onSuccess(BusinessBean bean) {
                if (mCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mCallback.onGetBusinessSuccess(bean.getData());
                        break;
                    default:
                        mCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mCallback != null) {
                    mCallback.onGetBusinessFailuer(errorMsg);
                }
            }
        });
        mCompositeDisposable.add(HttpUtils.getHttpUtils().getCurrentDisposable());
    }

    @Override
    public void onAddFollow(int masterId, IFollowCallabck callabck) {
        mFollowCallabck = callabck;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(4);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, 0));
        maps.put("masterId", masterId);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.ADD_FOLLOW, maps, new HttpResponseCallback<FollowBean>() {
            @Override
            public void onSuccess(FollowBean bean) {
                if (mFollowCallabck == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mFollowCallabck.onFollowSuccess(bean.getData());
                        break;
                    default:
                        mFollowCallabck.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mFollowCallabck != null) {
                    mFollowCallabck.onFollowFailuer(errorMsg);
                }
            }
        });
        mCompositeDisposable.add(HttpUtils.getHttpUtils().getCurrentDisposable());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mCallback = null;
        mFollowCallabck = null;
        if(mCompositeDisposable!=null){
            mCompositeDisposable.dispose();
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
    }
}
