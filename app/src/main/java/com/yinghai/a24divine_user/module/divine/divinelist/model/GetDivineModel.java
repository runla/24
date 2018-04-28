package com.yinghai.a24divine_user.module.divine.divinelist.model;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.MasterBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.module.divine.divinelist.ContractDivine;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenjianrun on 2017/10/30.
 * 描述：获取名师占卜的 Model 层
 */

public class GetDivineModel extends BaseModel implements ContractDivine.IDivineModel {

    private IDivineCallback mCallback;

    @Override
    public void getMasterList(int pageNum, int pageSize,String businessType, IDivineCallback callback) {
        mCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(5);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, -1));
        maps.put("page", pageNum);
        maps.put("pageSize", pageSize);
        if (businessType!=null){
            maps.put("mBusinessType",businessType);
        }
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.LOOK_MASTER, maps, new HttpResponseCallback<MasterBean>() {
            @Override
            public void onSuccess(MasterBean bean) {
                if (mCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mCallback.onGetMasterSuccess(bean.getData());
                        break;
                    default:
                        mCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mCallback != null) {
                    mCallback.onGetMasterFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCallback = null;
    }
}
