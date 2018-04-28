package com.yinghai.a24divine_user.module.artical.details.mvp;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.CommentBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by：fanson
 * Created on：2017/10/17 11:53
 * Describe：评论的M层（数据）实现类
 */

public class CommentModel extends BaseModel implements ContractComments.ICommentsModel {

    private static final String TAG = CommentModel.class.getSimpleName();
    private ICommentCallback mCallback;
    private IPublishCallback mPublishCallback;
    private IDeleteCallback mDeleteCallback;
    private static final int PAGE_SIZE = 10;

    @Override
    public void getComments(int type, int id, int pageNum, ICommentCallback callback) {
        mCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(7);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, -1));
        maps.put("type", type);
        maps.put("id", id);
        maps.put("page", pageNum);
        maps.put("pageSize", PAGE_SIZE);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.GET_COMMENTS, maps, new HttpResponseCallback<CommentBean>() {
            @Override
            public void onSuccess(CommentBean bean) {
                if (mCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mCallback.onCommentSuccess(bean.getData());
                        break;
                    default:
                        mCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mCallback != null) {
                    mCallback.onCommentFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void publishComments(int type, int id, String content, IPublishCallback callback) {
        mPublishCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(6);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, 0));
        maps.put("type", type);
        maps.put("id", id);
        maps.put("content", content);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.PUBLISH_COMMENTS, maps, new HttpResponseCallback<CommentBean>() {
            @Override
            public void onSuccess(CommentBean bean) {
                if (mPublishCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mPublishCallback.onPublishSuccess(bean.getData());
                        break;
                    default:
                        mPublishCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mPublishCallback != null) {
                    mPublishCallback.onPublishFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void deleteComments( int commentId, IDeleteCallback callback) {
        mDeleteCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(4);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, 0));
        maps.put("commentId", commentId);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.DELETE_COMMENTS, maps, new HttpResponseCallback<CommentBean>() {
            @Override
            public void onSuccess(CommentBean bean) {
                if (mDeleteCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mDeleteCallback.onDeleteSuccess(bean.getData());
                        break;
                    default:
                        mDeleteCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mDeleteCallback != null) {
                    mDeleteCallback.onDeleteFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void details(int articleId) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCallback = null;
        mPublishCallback = null;
        mDeleteCallback = null;
    }
}
