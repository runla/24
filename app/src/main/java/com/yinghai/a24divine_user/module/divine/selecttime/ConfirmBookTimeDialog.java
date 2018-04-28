package com.yinghai.a24divine_user.module.divine.selecttime;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fansonlib.base.AppUtils;
import com.example.fansonlib.base.BaseDataAdapter;
import com.example.fansonlib.base.BaseHolder;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.dialogfragment.base.BaseDialogFragment;
import com.example.fansonlib.widget.dialogfragment.base.ViewHolder;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;
import com.yinghai.a24divine_user.R;

import java.util.List;

/**
 * Created by chenjianrun on 2018/3/27.
 * 描述：占卜下单，预约时间刻的选择
 */

public class ConfirmBookTimeDialog extends BaseDialogFragment {
    private AutoLoadRecyclerView mRecyclerView;
    private Button mBtnConfirm;
    private BookTimeAdapter mAdapter;
    private List<String> mDataList;
    private ISelectCallback mISelectCallback;
    private TextView mTvDataTip;

    public ConfirmBookTimeDialog(){
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setOutCancel(true);
        setHeight(350);
        setWidth(250);

    }
    @Override
    public int intLayoutId() {
        return R.layout.dialog_confirm_book_time;
    }


    @Override
    public void convertView(ViewHolder viewHolder, BaseDialogFragment baseDialogFragment) {
        mBtnConfirm = viewHolder.getView(R.id.btn_confirm);
        mRecyclerView = viewHolder.getView(R.id.recyclerview);
        mTvDataTip = viewHolder.getView(R.id.tv_data_tip);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        if (mAdapter == null) {
            mAdapter = new BookTimeAdapter(getContext());
        }

        if (mDataList == null || mDataList.size() <= 0){
            mTvDataTip.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }else{
            mTvDataTip.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mAdapter.fillList(mDataList);
            mRecyclerView.setAdapter(mAdapter);
        }


        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mISelectCallback != null) {
                    /**
                     * 返回 -1 表示未选中
                     */
                    mISelectCallback.selectResult(mAdapter.getmSelectTimeIndex(),mAdapter.getmSelectTime());
                }
                if (mAdapter.getmSelectTimeIndex() != -1){
                    dismiss();
                }
            }
        });
    }

    public void putData(List<String> mDataList){
        this.mDataList = mDataList;
        if (mAdapter != null) {
            mAdapter.fillList(mDataList);
            mAdapter.clear();
        }

    }

    public void setISelectCallback(ISelectCallback mISelectCallback) {
        this.mISelectCallback = mISelectCallback;
    }

    private static class BookTimeAdapter extends BaseDataAdapter<String>{
        private String[] timeArrays;
        private ImageView mIvLastSelect;
        private int mLastSelectPos = -1;

        public String mSelectTime;
        public int mSelectTimeIndex = -1;

        public BookTimeAdapter(Context context) {
            super(context);
            timeArrays = AppUtils.getAppContext().getResources().getStringArray(R.array.book_time);
        }

        @Override
        public int getLayoutRes(int i) {
            return R.layout.item_book_time;
        }

        @Override
        public void bindCustomViewHolder(final BaseHolder baseHolder, final int i) {
            final int timeIndex = Integer.parseInt(getItem(i).split(":")[0]);
            final int canBookNum = Integer.parseInt(getItem(i).split(":")[1]);
            TextView tv_time =  baseHolder.getView(R.id.tv_book_time);

            if ((timeIndex < 0 || timeIndex>timeArrays.length)) {
                return;
            }

            tv_time.setText(timeArrays[timeIndex-1]);
            if (canBookNum <= 0) {
                tv_time.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
                tv_time.setTextColor(ContextCompat.getColor(AppUtils.getAppContext(),R.color.rc_voice_color_right));
            }else{
                tv_time.getPaint().setFlags(0);                         // 取消设置的的划线
                tv_time.setTextColor(ContextCompat.getColor(AppUtils.getAppContext(),R.color.blue_light));
            }

            if (i == mLastSelectPos){
                baseHolder.getView(R.id.iv_yes).setVisibility(View.VISIBLE);
            }else{
                baseHolder.getView(R.id.iv_yes).setVisibility(View.INVISIBLE);
            }

            baseHolder.setOnClickListener(R.id.rootView, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (canBookNum <= 0) {
                        ShowToast.singleLong("该时间段的预约人数已满");
                        return;
                    }

                    mSelectTime = timeArrays[timeIndex-1];
                    mSelectTimeIndex = timeIndex;

                    if (mIvLastSelect != null) {
                        mIvLastSelect.setVisibility(View.INVISIBLE);
                    }
                    mIvLastSelect = baseHolder.getView(R.id.iv_yes);
                    mIvLastSelect.setVisibility(View.VISIBLE);
                    mLastSelectPos = i;

                }
            });
        }

        public void clear(){
            mIvLastSelect = null;
            mLastSelectPos = -1;
            mSelectTime = null;
            mSelectTimeIndex = -1;
        }

        public String getmSelectTime() {
            return mSelectTime;
        }

        public int getmSelectTimeIndex() {
            return mSelectTimeIndex;
        }

        public void setmSelectTime(String mSelectTime) {
            this.mSelectTime = mSelectTime;
        }

        public void setmSelectTimeIndex(int mSelectTimeIndex) {
            this.mSelectTimeIndex = mSelectTimeIndex;
        }
    }

    /**
     * 预约时间选择回调接口
     */
    public interface ISelectCallback{
        /**
         * 返回 -1 表示未选中
         */
        void selectResult(int timeIndex,String time);
    }
}
