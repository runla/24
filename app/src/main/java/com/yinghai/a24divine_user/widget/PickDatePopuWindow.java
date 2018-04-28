package com.yinghai.a24divine_user.widget;

import android.animation.Animator;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.example.fansonlib.widget.calendar.Calendar;
import com.example.fansonlib.widget.calendar.CalendarView;
import com.example.fansonlib.widget.popupwindow.BasePopup;
import com.yinghai.a24divine_user.R;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/10 18:21
 *         Describe：选择生日的Dialog
 */

public class PickDatePopuWindow extends BasePopup implements CalendarView.OnDateChangeListener, CalendarView.OnDateSelectedListener, ChooseYearWindow.IReturnYearListener {

    private View popupView;
    private TextView mTvYear, mTvMonth;
    private CalendarView mCalendarView;
    private Button mBtnConfirm;
    private ChooseYearWindow mChooseYearWindow;
    private IPickTimeListener mPickTimeListener;
    private String mSelectYear, mSelectMonth, mSelectDay; //当前选择的;

    public void setPickTimeListener(IPickTimeListener listener) {
        mPickTimeListener = listener;
    }

    public PickDatePopuWindow(Activity context) {
        super(context);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mTvYear = (TextView) findViewById(R.id.tv_year);
        mTvMonth = (TextView) findViewById(R.id.tv_month);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mBtnConfirm = (Button) findViewById(R.id.btn_confirm);
        mCalendarView.setOnDateChangeListener(this);
        mCalendarView.setOnDateSelectedListener(this);
    }

    private void initData() {
        mSelectYear = String.valueOf(mCalendarView.getCurYear());
        mSelectMonth = String.valueOf(mCalendarView.getCurMonth());
        mSelectDay = String.valueOf(mCalendarView.getCurDay());

        mTvYear.setText(mSelectYear);
        mTvMonth.setText(mSelectMonth);
    }

    private void initListener() {
        //打开年份选择
        mTvYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChooseYearWindow = new ChooseYearWindow(getContext(),PickDatePopuWindow.this);
                mChooseYearWindow.showPopupWindow();
            }
        });

        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPickTimeListener.onPickTime(mSelectYear, mSelectMonth, mSelectDay);
                PickDatePopuWindow.this.dismiss();
            }
        });
    }

    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    @Override
    protected Animator initShowAnimator() {
        return getDefaultSlideFromBottomAnimationSet();
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        popupView =  LayoutInflater.from(getContext()).inflate(R.layout.window_pick_date, null);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (mChooseYearWindow!=null){
            mChooseYearWindow.dismiss();
            mChooseYearWindow = null;
        }
        mPickTimeListener = null;
    }

    @Override
    public void onDateChange(Calendar calendar) {
        mTvMonth.setText(String.valueOf(calendar.getMonth()));
        mTvYear.setText(String.valueOf(calendar.getYear()));
    }

    @Override
    public void onYearChange(int year) {
        mSelectYear = String.valueOf(year);
        mTvYear.setText(mSelectYear);
    }

    @Override
    public void onDateSelected(Calendar calendar) {
        mSelectMonth = String.valueOf(calendar.getMonth());
        mTvMonth.setText(mSelectMonth);
        mSelectYear = String.valueOf(calendar.getYear());
        mSelectDay = String.valueOf(calendar.getDay());
    }

    /**
     * 选择年份窗口的回调
     * @param year 选中的年份
     */
    @Override
    public void onChooseYear(int year) {
        mCalendarView.scrollToYear(year);
    }

    /**
     * 时间选择后回调返回界面
     */
    public interface IPickTimeListener {
        /**
         * 时间选择后回调
         *
         * @param year
         * @param month
         * @param day
         */
        void onPickTime(String year, String month, String day);
    }
}
