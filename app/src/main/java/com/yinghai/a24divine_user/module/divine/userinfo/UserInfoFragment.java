package com.yinghai.a24divine_user.module.divine.userinfo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.fansonlib.bean.StepBean;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.stepprogress.HorizontalStepProgress;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseFragment;
import com.yinghai.a24divine_user.callback.OnMultiFragmentListener;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.widget.PickDatePopuWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjianrun on 2017/10/30.
 * 描述：预约服务过程中添加用户信息的 fragment
 */

public class UserInfoFragment extends MyBaseFragment implements PickDatePopuWindow.IPickTimeListener {
    public static final int BOY = 1;    //男
    public static final int GIRL = 0;   //女
    private EditText mEtName;
    private TextView mTvBirthday;
    private EditText mEtTel;
//    private EditText mEtAddress;
    private EditText mEtProblem;
    private RadioGroup mRgSex;
    private RadioButton mRbBoy;
    private RadioButton mRbGirl;
    private Button mBtnConfirm;
    private int mSex = -1;
    private ImageView mIvBack;

    private OnMultiFragmentListener onMultiFragmentListener;
    private PickDatePopuWindow mPickDatePopuWindow;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_divine_book_input_info;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onMultiFragmentListener = (OnMultiFragmentListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onMultiFragmentListener = null;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        mEtName = findMyViewId(R.id.et_user_name);
        mTvBirthday = findMyViewId(R.id.tv_user_birthday);
        mEtTel = findMyViewId(R.id.et_user_tel);
//        mEtAddress = findMyViewId(R.id.et_user_address);
        mIvBack = findMyViewId(R.id.iv_back);
        mEtProblem = findMyViewId(R.id.et_user_problem);
        mRgSex = findMyViewId(R.id.rg_user_sex);
        mRbBoy = findMyViewId(R.id.rb_sex_boy);
        mRbGirl = findMyViewId(R.id.rb_sex_girl);
        mBtnConfirm = findMyViewId(R.id.btn_confirm_publish);

        initStepProgress();
        return rootView;
    }

    @Override
    protected void initData() {
        mEtTel.setText(SharePreferenceHelper.getString(ConstantPreference.S_PHONE,""));
        mEtName.setText(SharePreferenceHelper.getString(ConstantPreference.S_USER_NAME,""));
        mTvBirthday.setText(SharePreferenceHelper.getString(ConstantPreference.S_USER_BIRTHDAY,""));
        if (SharePreferenceHelper.getBoolean(ConstantPreference.B_USER_SEX,true)){
            mRbBoy.setChecked(true);
            mSex = BOY;
        }else {
            mRbGirl.setChecked(true);
            mSex = GIRL;
        }
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mRgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.rb_sex_boy:
                        mSex = BOY;
                        break;
                    case R.id.rb_sex_girl:
                        mSex = GIRL;
                        break;
                    default:
                        break;
                }
            }
        });

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });

        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFragmentListener != null ) {
                    if (checkNull()){
                        Bundle bundle = new Bundle();
                        bundle.putInt("SEX",mSex);
                        bundle.putString("REMARK",mEtProblem.getText().toString());
                        bundle.putString("BIRTHDAY",mTvBirthday.getText().toString());
                        onMultiFragmentListener.onMultiFragment(ConFragment.OPEN_DIVINE_BOOK_USER_PAY,bundle);
                    }else {
                        ShowToast.singleLong(getString(R.string.info_not_complete));
                    }
                }
            }


        });

        mTvBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPickDatePopuWindow = new PickDatePopuWindow(hostActivity);
                mPickDatePopuWindow.setPickTimeListener(UserInfoFragment.this);
                mPickDatePopuWindow.showPopupWindow();
            }
        });
    }

    /**
     * 检查数据填写是否完成
     * @return ture or false
     */
    private boolean checkNull() {
        if (mTvBirthday.getText().toString().isEmpty()){
            return false;
        }
        if (mSex == -1){
            return false;
        }
        return  true;
    }

    @Override
    protected void initToolbarTitle() {
    }

    private void initStepProgress() {
       HorizontalStepProgress mStepProgress = findMyViewId(R.id.stepProgress);
        List<StepBean> stepList = new ArrayList<>();
        StepBean bean1 = new StepBean(getString(R.string.divine_type2), StepBean.STEP_COMPLETED);
        StepBean bean2 = new StepBean(getString(R.string.edit_info), StepBean.STEP_CURRENT);
        StepBean bean3 = new StepBean(getString(R.string.confirm_pay), StepBean.STEP_UNDO);
        stepList.add(bean1);
        stepList.add(bean2);
        stepList.add(bean3);
        mStepProgress .setStepViewTexts(stepList)
                .setTextSize(12)
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(getActivity(), android.R.color.holo_blue_light))
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(getActivity(), R.color.grey))
                .setStepViewComplectedTextColor(ContextCompat.getColor(getActivity(), R.color.grey))
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(getActivity(), R.color.grey));
    }

    @Override
    public void onPickTime(String year, String month, String day) {
        mTvBirthday.setText(year + "-" + month + "-" + day);
    }
}


