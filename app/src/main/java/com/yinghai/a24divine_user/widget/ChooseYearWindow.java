package com.yinghai.a24divine_user.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;

import com.example.fansonlib.base.BaseDataAdapter;
import com.example.fansonlib.base.BaseHolder;
import com.example.fansonlib.utils.DateFormatUtil;
import com.example.fansonlib.widget.popupwindow.BasePopup;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;
import com.yinghai.a24divine_user.R;

import java.util.ArrayList;

/**
 * @author Created by：fanson
 *         Created Time: 2018/1/15 14:05
 *         Describe：选择年份的window
 */

public class ChooseYearWindow extends BasePopup {

    private View popupView;
    private AutoLoadRecyclerView mRecyclerView;
    private IReturnYearListener mListener;
    private YearAdapter mAdapter;
    private Context mContext;
    private static final int MIN_YEAR = 1930;

    public ChooseYearWindow(Context context,IReturnYearListener listener) {
        super(context);
        mContext = context;
        mListener = listener;
        initRecyclerView();
        initData();
    }

    private void initRecyclerView() {
        mRecyclerView = (AutoLoadRecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setOnPauseListenerParams(true, true);
        mAdapter = new YearAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        ArrayList<YearBean> yearList = new ArrayList<>();
        YearBean yearBean;
        for (int i = DateFormatUtil.getCurrentYear(); i > MIN_YEAR; i--) {
            yearBean = new YearBean();
            yearBean.setYear(i);
            yearList.add(yearBean);
        }
        mAdapter.fillList(yearList);
    }

    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        popupView = LayoutInflater.from(getContext()).inflate(R.layout.window_choose_year, null);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        mListener = null;
    }

    /**
     * 适配器
     */
    private class YearAdapter extends BaseDataAdapter<YearBean> {

        public YearAdapter(Context context) {
            super(context);
        }

        @Override
        public int getLayoutRes(int i) {
            return R.layout.item_chooose_year;
        }

        @Override
        public void bindCustomViewHolder(BaseHolder holder, final int position) {
            holder.setText(R.id.tv_year,String.valueOf(getItem(position).getYear()));
            holder.setOnClickListener(R.id.rootView, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onChooseYear(getItem(position).getYear());
                    dismiss();
                }
            });
        }
    }

    /**
     * 年份数据Bean
     */
    private class YearBean {

        private int year;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }

    public interface IReturnYearListener{
        /**
         * 选中的年份
         * @param year 选中的年份
         */
        void onChooseYear(int year);
    }

}
