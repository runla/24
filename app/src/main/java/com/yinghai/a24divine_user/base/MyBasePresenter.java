package com.yinghai.a24divine_user.base;

import com.example.fansonlib.base.AppUtils;
import com.example.fansonlib.base.BasePresenterWithM;
import com.example.fansonlib.base.BaseView;
import com.example.fansonlib.base.IBaseModel;
import com.example.fansonlib.utils.ShowToast;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.SampleApplicationLike;
import com.yinghai.a24divine_user.constant.ConResultCode;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/2 11:39
 *         Describe：本项目的BasePresenter，带统一处理ResultCode
 */

public abstract class MyBasePresenter<M extends IBaseModel, V extends BaseView> extends BasePresenterWithM<M,V> {

    /**
     * 统一处理Result Code
     * @param code 接收到的code
     */
    protected void handleResultCode(int code){
        if (isViewAttached()){
            getBaseView().hideLoading();
        }
        switch (code){
            case ConResultCode.LOSS_PARAMS:
                ShowToast.singleShort(AppUtils.getString(R.string.loss_params));
                SampleApplicationLike.getInstance().retryLaunch();
                break;

            case ConResultCode.NOT_REGISTER:
                ShowToast.singleShort(AppUtils.getString(R.string.data_not_exist));
                break;

            case ConResultCode.VERIFICATION_OVERDUE:
                ShowToast.singleShort(AppUtils.getString(R.string.verify_code_useless));
                break;

            case ConResultCode.RETRY_LOGIN:
                SampleApplicationLike.getInstance().retryLaunch();
                break;

            case ConResultCode.PRODUCT_SOLD_OUT:
                ShowToast.singleShort(AppUtils.getString(R.string.sold_out));
                break;

            case ConResultCode.PASSWORD_ERROR:
                ShowToast.singleShort(AppUtils.getString(R.string.password_error));
                break;

            case ConResultCode.REPEAT_COLLECT:
                ShowToast.singleShort(AppUtils.getString(R.string.repeat_collect));
                break;

            case ConResultCode.REPEAT_FOLLOW:
                ShowToast.singleShort(AppUtils.getString(R.string.repeat_follow));
                break;

            case ConResultCode.BOOK_TIME_ERROR:
                ShowToast.singleLong(AppUtils.getString(R.string.book_time_error));
                break;

            default:
                ShowToast.singleShort(AppUtils.getString(R.string.result_unknown)+code);
                break;
        }
    }

}
