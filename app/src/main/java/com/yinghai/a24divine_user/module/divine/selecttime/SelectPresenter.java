package com.yinghai.a24divine_user.module.divine.selecttime;

import android.support.v4.content.ContextCompat;

import com.example.fansonlib.utils.DateFormatUtil;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.calendar.Calendar;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.DivineScheduleTimeBean;
import com.yinghai.a24divine_user.bean.ScheduleBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.example.fansonlib.utils.notification.MyNotificationUtils.mContext;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/1 11:32
 *         Describe：选择预约时间的P层
 */

public class SelectPresenter extends MyBasePresenter<SelectModel,ContractSelectTime.ISelectView> implements ContractSelectTime.ISelectPresenter,
        ContractSelectTime.ISelectModel.IGetMasterCallback, ContractSelectTime.ISelectModel.IDivineScheduleTimeCallback {

    private  List<Calendar> mSchemeList ; //有标记的日程
    private List<Calendar> mForbidSchemeList; //已禁止预约的日程

    public SelectPresenter(ContractSelectTime.ISelectView view){
        attachView(view);
    }


    @Override
    public void onGetMasterSchedule(int masterId, String date) {
        mBaseModel.getMasterSchedule(masterId,date,this );
    }

    @Override
    public void onSelectTime() {

    }

    @Override
    public void getDivineSchedule(String date,String masterId) {
        mBaseModel.getDivineScheduleTime(date,masterId,this);
    }

    @Override
    protected SelectModel createModel() {
        return new SelectModel();
    }

    @Override
    public void onGetMasterScheduleSuccess(ScheduleBean bean) {
        if (isViewAttached()){
            getBaseView().showMasterScheduleSuccess(bean);
        }
    }

    @Override
    public void onGetMasterScheduleFailure(String errorMsg) {
        if (isViewAttached()){
            getBaseView().showMasterScheduleFailure(errorMsg);
        }
    }


    public List<Calendar> onCalSchemes(List<ScheduleBean.DataBean.MasterScheduleListBean> beanlist) {
        if (beanlist==null){
            return null;
        }
        if (mSchemeList==null){
            mSchemeList = new ArrayList<>();
            mForbidSchemeList= new ArrayList<>();
        }
        for (int i=0;i<beanlist.size();i++){
//            if (beanlist.get(i).getForbid()){
//                mSchemeList.add(subStringTime(beanlist.get(i), ContextCompat.getColor(mContext, R.color.forbid)));
//                mForbidSchemeList.add(subStringTime(beanlist.get(i), ContextCompat.getColor(mContext,R.color.forbid)));
//            }else {
                mSchemeList.add(subStringTime(beanlist.get(i),ContextCompat.getColor(mContext,R.color.freedom)));
//            }
        }
        return mSchemeList;
    }

    /**
     * 为了设置格式，截取时间
     * @param bean
     * @param color
     */
    private Calendar subStringTime(ScheduleBean.DataBean.MasterScheduleListBean bean ,int color){
        return getSchemeCalendar(Integer.parseInt(bean.getMsDate().substring(0,4)),
                Integer.parseInt(bean.getMsDate().substring(5,7)),
                Integer.parseInt(bean.getMsDate().substring(8,10)),
                color);
    }

    /**
     * 设置日历格式
     */
    private Calendar getSchemeCalendar(int year, int month, int day, int color) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        return calendar;
    }

    /**
     * 获取已禁止预约的日程
     */
    public List<Calendar> getForbidSchemeList(){
        return mForbidSchemeList;
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }


    @Override
    public void onGetDivineScheduleTimeSuccess(DivineScheduleTimeBean bean) {
        if (isViewAttached()) {
            // 排序
            Collections.sort(bean.getData().getTimetable(), new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    Integer str1 = Integer.valueOf(o1.split(":")[0]);
                    Integer str2 = Integer.valueOf(o2.split(":")[0]);
                    return str1.compareTo(str2);
                }
            });
            List<String> resultList = deleteOutTime(bean.getData().getDate(),bean.getData().getTimetable());
            if (isAllDenySelect(resultList)){
                getBaseView().onGetDivineScheduleSuccess(null);
                return;
            }
            getBaseView().onGetDivineScheduleSuccess(resultList);
        }
    }

    @Override
    public void onGetDivineScheduleTimeFailure(String errMsg) {
        ShowToast.singleLong(errMsg);
    }

    /**
     * 去除早于当前的时间段(由于从服务器中返回的是“48:1”（表示23:30-00:00）这个时间段
     * @param mScheduleList 这个 list 是有序的，[“1:0”,“2:0”,“3:1”,.....“46:1”,“47:0”,“48:0”]
     * @param date 格式 2018-12-11
     * @return
     */
    private List<String> deleteOutTime(String date,List<String> mScheduleList){
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int hour = calendar.get(java.util.Calendar.HOUR_OF_DAY);
        int selectYear = Integer.parseInt(date.split("-")[0]);
        int selectMonth = Integer.parseInt(date.split("-")[1]);
        int selectDay = Integer.parseInt(date.split("-")[2]);
        if (selectYear > DateFormatUtil.getCurrentYear()){
            return mScheduleList;
        }
        if (selectMonth > DateFormatUtil.getCurrentMonth()) {
            return mScheduleList;
        }
        if (selectDay > DateFormatUtil.getCurrentDay()) {
            return mScheduleList;
        }

        int len = mScheduleList.size();
        for (int i = 0; i < len; i++) {
            if (i/2 <= hour+1) {
                mScheduleList.remove(0);
            }else{
                return mScheduleList;
            }
        }
        return mScheduleList;
    }

    /**
     * 判断预约的时间段是否全部禁止选择,如果全部都是禁止选择的，那么要求返回的 List 为空
     * @param scheduleList
     * @return
     */
    private boolean isAllDenySelect(List<String> scheduleList){
        for (String s : scheduleList) {
            if (Integer.parseInt(s.split(":")[1]) > 0) {
                return false;
            }
        }
        return true;
    }
}
