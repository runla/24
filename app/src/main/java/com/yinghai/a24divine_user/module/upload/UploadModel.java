package com.yinghai.a24divine_user.module.upload;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.retrofit.RetrofitClient;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.bean.UploadPictureBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConResultCode;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.http.ApiStores;
import com.yinghai.a24divine_user.utils.ValidateAPITokenUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/22 15:23
 *         Describe：上传Model
 */

public class UploadModel extends BaseModel implements ContractUpload.IModel{

    private UploadPictureCallback mUploadCallback;
    private Disposable mDisposable;

    @Override
    public void onUploadPicture(int type, String appPath, UploadPictureCallback callback) {
        mUploadCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(5);
        maps.put("type", type);
        maps.put("appPath",appPath);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, 1));
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));

        File file = new File(appPath);
        maps.put("file\";filename=\""+file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"),file));
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        mDisposable =  addSubscrebe(RetrofitClient.getRetrofit(ApiStores.class).uploadPicture(SharePreferenceHelper.getString(ConstantPreference.S_DEVICE_ID, null), ConHttp.UPLOAD_PICTRUE, part,maps),
                new ResourceSubscriber<UploadPictureBean>() {
            @Override
            public void onNext(UploadPictureBean bean) {
                if (mUploadCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mUploadCallback.onUploadSuccess(bean.getData());
                        SharePreferenceHelper.putString(ConstantPreference.S_USER_PHOTO,bean.getData().getImgUrl());
                        SharePreferenceHelper.apply();
                        break;
                    default:
                        mUploadCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onError(Throwable t) {
                if (mUploadCallback != null) {
                    mUploadCallback.onUploadFailure(t.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUploadCallback = null;
        if (mDisposable!=null){
            mDisposable.dispose();
        }
    }
}
