package com.yinghai.a24divine_user.module.upload;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.UploadPictureBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/22 15:23
 *         Describe：上传契约类
 */

public interface ContractUpload {

    interface IView extends BaseView{

        /**
         * 上传成功
         */
        void showUploadSuccess(UploadPictureBean bean);

        /**
         * 上传失败
         */
        void showUploadFailure(String errMsg);

    }


    interface IPresenter{

        /**
         * 上传图片
         * @param type 图片类型:1为大师头像; 2为用户头像; 3文章图片; 4产品图片
         * @param appPath APP访问路径
         */
        void uploadPicture(int type,String appPath);

    }

    interface IModel{

        /**
         * 上传图片
         * @param type 图片类型:1为大师头像; 2为用户头像; 3文章图片; 4产品图片
         * @param appPath APP访问路径
         * @param callback
         */
        void onUploadPicture(int type,String appPath,UploadPictureCallback callback);


        interface UploadPictureCallback extends IHandleCodeCallback{
            /**
             * 上传成功
             * @param bean
             */
            void onUploadSuccess(UploadPictureBean.DataBean bean);

            /**
             * 上传失败
             */
            void onUploadFailure(String errMsg);

        }

    }

}
