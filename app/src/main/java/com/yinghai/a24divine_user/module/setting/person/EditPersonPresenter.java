package com.yinghai.a24divine_user.module.setting.person;

import android.text.TextUtils;

import com.example.fansonlib.base.AppUtils;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.PersonInfoBean;
import com.yinghai.a24divine_user.bean.UploadPictureBean;
import com.yinghai.a24divine_user.module.upload.ContractUpload;
import com.yinghai.a24divine_user.module.upload.UploadModel;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/21 15:50
 *         Describe：修改个人信息P层
 */

public class EditPersonPresenter extends MyBasePresenter<EditPersonModel,ContractEditPerson.IView> implements ContractEditPerson.IPresenter, ContractEditPerson.IModel.IEditCallback, ContractEditPerson.IModel.ILogoutCallback, ContractUpload.IModel.UploadPictureCallback {

    private UploadModel mUploadModel;

    public EditPersonPresenter(ContractEditPerson.IView view){
        attachView(view);
    }

    @Override
    protected EditPersonModel createModel() {
        return new EditPersonModel();
    }


    private UploadModel getUploadModel(){
        if (mUploadModel==null){
            mUploadModel = new UploadModel();
        }
        return mUploadModel;
    }

    @Override
    public void editPerson(String name, String birthday, int constellationId, boolean sex,String photoUrl) {
        if (TextUtils.isEmpty(name)) {
            getBaseView().showEditPersonFailure(AppUtils.getString(R.string.setting_name_empty));
            return;
        }
        if (TextUtils.isEmpty(birthday)) {
            getBaseView().showEditPersonFailure(AppUtils.getString(R.string.setting_birthday_unset));
            return;
        }
        mBaseModel.editPerson(name,birthday,constellationId,sex,photoUrl,this);
    }

    @Override
    public void logout() {
        mBaseModel.onLogout(this);
    }

    @Override
    public void uploadPicture(int type, String appPath) {
        getUploadModel().onUploadPicture(type,appPath,this);
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    @Override
    public void onEditPersonSuccess(PersonInfoBean.DataBean bean) {
        if (isViewAttached()){
            getBaseView().showEditPersonSuccess(bean);
        }
    }

    @Override
    public void onEditPersonFailure(String errMsg) {
        if (isViewAttached()){
            getBaseView().showEditPersonFailure(errMsg);
        }
    }

    @Override
    public void onLogoutSuccess() {
        if (isViewAttached()){
            getBaseView().showLogoutSuccess();
        }
    }

    @Override
    public void onLogoutFailure(String errMsg) {
        if (isViewAttached()){
            getBaseView().showEditPersonFailure(errMsg);
        }
    }

    @Override
    public void onUploadSuccess(UploadPictureBean.DataBean bean) {
        if (isViewAttached()){
            getBaseView().showUploadSuccess(bean);
        }
    }

    @Override
    public void onUploadFailure(String errMsg) {
        if (isViewAttached()){
            getBaseView().showEditPersonFailure(errMsg);
        }
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mUploadModel!=null){
            mUploadModel.onDestroy();
            mUploadModel =null;
        }
    }
}
