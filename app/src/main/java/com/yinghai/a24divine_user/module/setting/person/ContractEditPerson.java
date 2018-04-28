package com.yinghai.a24divine_user.module.setting.person;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.PersonInfoBean;
import com.yinghai.a24divine_user.bean.UploadPictureBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/21 15:43
 *         Describe：修改个人信息的契约类
 */

public interface ContractEditPerson {

    interface IView extends BaseView{

        /**
         * 修改个人信息成功
         */
        void showEditPersonSuccess(PersonInfoBean.DataBean bean);

        /**
         * 修改个人信息失败
         */
        void showEditPersonFailure(String errMsg);

        /**
         * 上传图片成功
         */
        void showUploadSuccess(UploadPictureBean.DataBean bean);

        /**
         * 上传图片失败
         */
        void showUploadFailure(String errMsg);

        /**
         * 登出成功
         */
        void showLogoutSuccess( );

        /**
         * 登出失败
         */
        void showLogoutFailure(String errMsg);

    }


    interface IPresenter{

        /**
         * 修改个人信息
         * @param name 昵称
         * @param birthday 生日
         * @param constellationId 星座ID
         * @param sex 性别 ture:男
         * @param photoUrl 头像
         */
        void editPerson(String name,String birthday,int constellationId,boolean sex,String photoUrl);

        /**
         * 登出
         */
        void logout();

        /**
         * 上传头像
         * @param type 图片类型:1为大师头像; 2为用户头像; 3文章图片; 4产品图片
         * @param appPath APP访问路径
         */
        void uploadPicture(int type, String appPath);

    }


    interface IModel{
        /**
         * 修改个人信息
         * @param name 昵称
         * @param birthday 生日
         * @param constellationId 星座ID
         * @param sex 性别 ture:男
         * @param photoUrl 头像
         */
        void editPerson(String name,String birthday,int constellationId,boolean sex,String photoUrl,IEditCallback callback);


        interface IEditCallback extends IHandleCodeCallback{

            /**
             * 修改个人信息成功
             */
            void onEditPersonSuccess(PersonInfoBean.DataBean bean);

            /**
             * 修改个人信息失败
             */
            void onEditPersonFailure(String errMsg);

        }

        /**
         * 登出
         */
        void onLogout(ILogoutCallback callback);

        interface ILogoutCallback extends IHandleCodeCallback{

            /**
             * 登出成功
             */
            void onLogoutSuccess( );

            /**
             * 登出失败
             */
            void onLogoutFailure(String errMsg);
        }

    }


}
