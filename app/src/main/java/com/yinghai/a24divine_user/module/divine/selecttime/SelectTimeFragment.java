package com.yinghai.a24divine_user.module.divine.selecttime;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fansonlib.utils.DateFormatUtil;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.calendar.Calendar;
import com.example.fansonlib.widget.calendar.CalendarView;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseMvpFragment;
import com.yinghai.a24divine_user.bean.BusinessBean;
import com.yinghai.a24divine_user.bean.ScheduleBean;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.module.divine.DivineActivity;
import com.yinghai.a24divine_user.utils.LogUtils;

import java.util.List;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/1 11:18
 *         Describe：选择预约时间的Fragment
 */

public class SelectTimeFragment extends MyBaseMvpFragment<SelectPresenter> implements ContractSelectTime.ISelectView, CalendarView.OnDateSelectedListener, CalendarView.OnDateChangeListener, ConfirmBookTimeDialog.ISelectCallback {

    private static final String TAG = SelectTimeFragment.class.getSimpleName();
    private BusinessBean.DataBean mBusinessBean;
    private CalendarView mCalendarView;
    private TextView mTvTime;
    private Button mBtnConfirm;
    private int mClickYear, mClickMonth, mClickDay;
    private StringBuilder mStringBuilder;
    private ImageView mIvBack;

    @Override
    protected void initToolbarTitle() {
    }

    @Override
    protected SelectPresenter createPresenter() {
        return new SelectPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_select_time;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        mIvBack = findMyViewId(R.id.iv_back);
        mTvTime = findMyViewId(R.id.tv_month);
        mBtnConfirm = findMyViewId(R.id.btn_confirm);
        mCalendarView = findMyViewId(R.id.calendarView);
        mCalendarView.setOnDateSelectedListener(this);
        mCalendarView.setOnDateChangeListener(this);
        return rootView;
    }

    public static SelectTimeFragment newInstance(Bundle args) {
        SelectTimeFragment fragment = new SelectTimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        mTvTime.setText(mCalendarView.getCurYear() + getString(R.string.year) + mCalendarView.getCurMonth() + getString(R.string.month));
        mClickYear = mCalendarView.getCurYear();
        mClickMonth = mCalendarView.getCurMonth();
        mClickDay = mCalendarView.getCurDay();
        if (getArguments() == null) {
            return;
        }
        mBusinessBean = getArguments().getParcelable("BEAN");
        showLoading();
        getSchedule(mClickYear, mClickMonth);
    }

    private void getSchedule(int year, int month) {
        mPresenter.onGetMasterSchedule(((DivineActivity) hostActivity).mMasterId, year + "-" + month);
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (judgeCurDayIsBan(mCalendarView.getSelectedCalendar())) {
                    ShowToast.singleLong(getString(R.string.cur_day_ban));
                } else if (judgeSelectDateIsOut()) {
                    ShowToast.singleLong(getString(R.string.cur_day_out));
                } else {
                    mStringBuilder = new StringBuilder();
                    mStringBuilder.append(mClickYear).append("-").append(mClickMonth).append("-").append(mClickDay);
                    mPresenter.getDivineSchedule(mStringBuilder.toString(), String.valueOf(((DivineActivity) hostActivity).mMasterId));
                }
            }
        });

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });
    }

    @Override
    public void showMasterScheduleSuccess(ScheduleBean bean) {
        if (mPresenter.onCalSchemes(bean.getData().getMasterScheduleList()) != null) {
            mCalendarView.setSchemeDate(mPresenter.onCalSchemes(bean.getData().getMasterScheduleList()));
        }
        hideLoading();
    }

    @Override
    public void showMasterScheduleFailure(String errorMsg) {
        hideLoading();
        ShowToast.singleShort(errorMsg);
    }

    private ConfirmBookTimeDialog mConfirmBookTimeDialog;
    @Override
    public void onGetDivineScheduleSuccess(List<String> mScheduleList) {
        if (mConfirmBookTimeDialog == null) {
            mConfirmBookTimeDialog = new ConfirmBookTimeDialog();
            mConfirmBookTimeDialog.setISelectCallback(this);
        }
        mConfirmBookTimeDialog.putData(mScheduleList);
        mConfirmBookTimeDialog.show(getFragmentManager());
    }

    @Override
    public void onGetDivineScheduleFailure() {

    }

    @Override
    public void showSelectTimeSuccess() {

    }

    @Override
    public void showSelectTimeFailure() {

    }

    @Override
    public void onDateSelected(Calendar calendar) {
        mClickYear = calendar.getYear();
        mClickMonth = calendar.getMonth();
        mClickDay = calendar.getDay();
    }

    @Override
    public void onDateChange(Calendar calendar) {
        mTvTime.setText(calendar.getYear() + getString(R.string.year) + calendar.getMonth() + getString(R.string.month));
        mClickYear = calendar.getYear();
        mClickMonth = calendar.getMonth();
        mClickDay = calendar.getDay();
    }

    @Override
    public void onYearChange(int year) {
        mClickYear = year;
    }

    /**
     * 判断选择的日期是否已经超出可预约范围
     *
     * @return
     */
    private boolean judgeSelectDateIsOut() {
        String selectTime = String.format("%d-%d-%d 23:59:00",mClickYear,mClickMonth,mClickDay);
        if (TextUtils.equals(getString(R.string.future), DateFormatUtil.getTimeDifference(selectTime))) {
            return false;
        }
        return true;
    }

    /**
     * 判断当天是否禁止预约，true表示禁止
     *
     * @param calendar 点击当前的日期
     */
    private boolean judgeCurDayIsBan(Calendar calendar) {
        if (mPresenter.getForbidSchemeList() == null) {
            LogUtils.d(TAG, "禁止预约的日程尚未获取到");
            return false;
        }
        for (Calendar calendar1 : mPresenter.getForbidSchemeList()) {
            if (calendar.getDay() == calendar1.getDay()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 选择预约时间段的结果回调
     * @param timeIndex
     * @param time
     */
    @Override
    public void selectResult(int timeIndex, String time) {
        if (timeIndex == -1){
            ShowToast.singleLong("请选择预约时间段");
            return;
        }
        mStringBuilder = new StringBuilder();
        mStringBuilder.append(mClickYear).append("-").append(mClickMonth).append("-").append(mClickDay).append(" ").append(time.split(" - ")[0]);
        ((DivineActivity) hostActivity).mAppoitment = mStringBuilder.toString();
        mMultiFragmentListener.onMultiFragment(ConFragment.OPEN_DIVINE_TYPE, mBusinessBean);
    }
}
