package com.yinghai.a24divine_user.module.history.mvp;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.HistoryBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/13 13:38
 *         Describe：获取历史记录的M层实现
 */

public class HistoryModel extends BaseModel implements ContractHistory.IHistoryModel{

    private IHistoryCallback mCallback;
    private  final int pageSize = 10;

    @Override
    public void getHistoryFromNet(int pageNum,IHistoryCallback callback) {
        mCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(5);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID,0));
        maps.put("page",pageNum);
        maps.put("pageSize",pageSize);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.HISTORY,maps, new HttpResponseCallback<HistoryBean>() {
            @Override
            public void onSuccess(HistoryBean bean) {
                if (mCallback ==null){
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mCallback.onHistorySuccess(bean);
                        break;
                    default:
                        mCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mCallback!=null){
                    mCallback.onHistoryFailure(errorMsg);
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
