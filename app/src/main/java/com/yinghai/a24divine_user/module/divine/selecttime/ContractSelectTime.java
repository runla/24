package com.yinghai.a24divine_user.module.divine.selecttime;

import com.example.fansonlib.base.BaseView;
import com.yinghai.a24divine_user.bean.DivineScheduleTimeBean;
import com.yinghai.a24divine_user.bean.ScheduleBean;
import com.yinghai.a24divine_user.callback.IHandleCodeCallback;

import java.util.List;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/1 11:22
 *         Describe：选择预约时间的契约类
 */

public interface ContractSelectTime {

    interface ISelectView extends BaseView {

        /**
         * 成功获取大师的日程表
         */
        void showMasterScheduleSuccess(ScheduleBean bean);

        /**
         * 失败获取大师的日程表
         */
        void showMasterScheduleFailure(String errorMsg);

        void onGetDivineScheduleSuccess(List<String> mScheduleList);

        void onGetDivineScheduleFailure();

        /**
         * 选择预约时间成功
         */
        void showSelectTimeSuccess();

        /**
         * 选择预约时间失败
         */
        void showSelectTimeFailure();

    }

    interface ISelectPresenter {

        /**
         * 获取大师的日程表
         *
         * @param masterId 大师ID
         * @param date     时间，例yyyy-MM
         */
        void onGetMasterSchedule(int masterId, String date);

        /**
         * 选择预约时间
         */
        void onSelectTime();

        /**
         * 获取占卜预约时间段
         */
        void getDivineSchedule(String date,String masterId);
    }

    interface ISelectModel {

        /**
         * 获取大师的日程表
         *
         * @param masterId 大师ID
         * @param date     时间，例yyyy-MM
         * @param callback
         */
        void getMasterSchedule(int masterId, String date, IGetMasterCallback callback);

        interface IGetMasterCallback extends IHandleCodeCallback {
            /**
             * 回调成功获取的大师的日程表
             */
            void onGetMasterScheduleSuccess(ScheduleBean bean);

            /**
             * 回调失败获取的大师的日程表
             */
            void onGetMasterScheduleFailure(String errorMsg);
        }

        void getDivineScheduleTime(String date,String masterId,IDivineScheduleTimeCallback callback);
        interface IDivineScheduleTimeCallback extends IHandleCodeCallback{
            /**
             * 获取指定日期预约时间表成功
             * @param bean
             */
            void onGetDivineScheduleTimeSuccess(DivineScheduleTimeBean bean);

            /**
             * 获取指定日期预约时间表失败
             * @param errMsg
             */
            void onGetDivineScheduleTimeFailure(String errMsg);
        }
    }

}
