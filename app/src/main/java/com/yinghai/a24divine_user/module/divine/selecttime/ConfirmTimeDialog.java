package com.yinghai.a24divine_user.module.divine.selecttime;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.dialogfragment.base.BaseDialogFragment;
import com.example.fansonlib.widget.dialogfragment.base.ViewHolder;
import com.yinghai.a24divine_user.R;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/1 13:30
 *         Describe：确认预约时间（上/下午）
 */

public class ConfirmTimeDialog extends BaseDialogFragment {
    private RadioButton mRbMorning;
    private RadioButton mRbAfternoon;
    private TextView mTvMorningTime;        // 上午可预约时间
    private TextView mTvMorningNum;         // 上午可预约人数
    private TextView mTvMorningHasNum;      // 上午已预约人数
    private TextView mTvAfterTime;          // 下午可预约时间
    private TextView mTvAfterNum;           // 下午可预约人数
    private TextView mTvAfterHasNum;        // 下午已预约人数
    private IChooseTimeListener mListener;
    //后台所需的时间段，上午/下午
    private static final String MORMING_TIME = "10:00";
    private static final String AFTER_TIME = "14:00";

    public void setIChooseTimeListener(IChooseTimeListener listener) {
        mListener = listener;
    }

    @Override
    public int intLayoutId() {
        return R.layout.dialog_confirm_time;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setOutCancel(true);
        this.setMargin(20);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void convertView(ViewHolder holder, final BaseDialogFragment dialog) {
        mRbMorning = holder.getView(R.id.rb_morning);
        mRbAfternoon = holder.getView(R.id.rb_aftermoon);
        holder.setText(R.id.tv_morning_time, "8:00-14:00");
        holder.setText(R.id.tv_aftermoon_time, "15:00-18:00");
        holder.setText(R.id.tv_morning_num, String.format(getString(R.string.appointment_num), 10));
        holder.setText(R.id.tv_aftermoon_num, String.format(getString(R.string.appointment_num), 10));
//        holder.setText(R.id.tv_morning_has_people, String.format(getString(R.string.has_appointment_num),8));
//        holder.setText(R.id.tv_aftermoon_has_people, String.format(getString(R.string.has_appointment_num),8));
        holder.setOnClickListener(R.id.rl_morning, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRbMorning.setChecked(true);
                mRbAfternoon.setChecked(false);
            }
        });

        holder.setOnClickListener(R.id.rl_aftermoon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRbMorning.setChecked(false);
                mRbAfternoon.setChecked(true);
            }
        });
        holder.setOnClickListener(R.id.btn_confirm, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    if (mRbMorning.isChecked()) {
                        mListener.chooseTime(MORMING_TIME);
                        dialog.dismiss();
                    } else if (mRbAfternoon.isChecked()) {
                        mListener.chooseTime(AFTER_TIME);
                        dialog.dismiss();
                    } else {
                        ShowToast.singleShort(getString(R.string.please_choose_time));
                    }
                }
            }
        });
    }

    public interface IChooseTimeListener {
        /**
         * 选择上下午时间段
         *
         * @param time 10:00 or 14:00
         */
        void chooseTime(String time);
    }
}
