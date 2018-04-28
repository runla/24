package com.yinghai.a24divine_user.module.artical.details.mvp;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.ArticleDetailBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/21 11:59
 *         Describe：获取文章详情的M层
 */

public class ArticleDetailModel extends BaseModel implements ContractArticleDetails.IModel{

    private IDetailsCallback mCallback;


    @Override
    public void getDetails(int articleId,IDetailsCallback callback) {
        mCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(4);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, -1));
        maps.put("articleId", articleId);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.ARTICLE_DETAIL, maps, new HttpResponseCallback<ArticleDetailBean>() {
            @Override
            public void onSuccess(ArticleDetailBean bean) {
                if (mCallback==null){
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mCallback.onArticleDetailSuccess(bean);
                        break;
                    default:
                        mCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                mCallback.onArticleDetailFailure(errorMsg);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCallback  = null;
    }
}
