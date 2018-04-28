package com.yinghai.a24divine_user.module.artical.list.model;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.loopj.android.http.RequestParams;
import com.yinghai.a24divine_user.bean.ArticleBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.module.artical.list.ContractArticle;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by：fanson
 *         Created on：2017/9/28 16:44
 *         Describe：获取文章的M层（数据）实现类
 */

public class GetArticleModel extends BaseModel implements ContractArticle.IArticleModel {

    private IArticleCallback mCallback;

    @Override
    public void getArticleList(int masterId, String articleType, int pageNum, int pageSize, IArticleCallback callback) {
        mCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(6);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, -1));
        if (masterId != 0) {
            maps.put("masterId", masterId);
        }
        if (articleType != null) {
            maps.put("type", articleType);
        }
        maps.put("page", pageNum);
        maps.put("pageSize", pageSize);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));

        HttpUtils.getHttpUtils().post(ConHttp.GET_ARTICLE, maps, new HttpResponseCallback<ArticleBean>() {
            @Override
            public void onSuccess(ArticleBean bean) {
                if (mCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mCallback.onArticleSuccess(bean.getData());
                        break;
                    default:
                        mCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mCallback != null) {
                    mCallback.onArticleFailure(errorMsg);
                }
            }
        });
    }

    /**
     * 将Map类型转为RequestParams
     *
     * @param params Map
     * @return
     */
    private RequestParams MapToParams(Map params) {
        RequestParams requestParams = new RequestParams();
        for (Object key : params.keySet()) {
            requestParams.put((String) key, params.get(key));
        }
        return requestParams;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCallback = null;
    }
}
