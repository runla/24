package com.yinghai.a24divine_user.module.divine.book.model;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.BusinessBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.module.divine.book.ContractBusinessList;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
* @author Created by：fanson
* Created on：2017/12/15 13:20
* Description：获取占卜师业务列表M层
 * @Param
*/
public class BusinessListModel extends BaseModel implements ContractBusinessList.IModel{

    private IBusinessCallback mCallback;
    private static final int PAGE_SIZE = 10;


    @Override
    public void onBusinessList(int masterId,int pageNum,IBusinessCallback callback) {
        mCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(6);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID,0));
        maps.put("masterId",masterId);
        maps.put("page",pageNum);
        maps.put("pageSize",PAGE_SIZE);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.BUSINESS,maps, new HttpResponseCallback<BusinessBean>() {
            @Override
            public void onSuccess(BusinessBean bean) {
                if (mCallback ==null){
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mCallback.onBusinessListSuccess(bean.getData());
                        break;
                    default:
                        mCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mCallback!=null){
                    mCallback.onBusinessListFailure(errorMsg);
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
