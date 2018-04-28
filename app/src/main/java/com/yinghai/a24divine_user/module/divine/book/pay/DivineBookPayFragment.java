package com.yinghai.a24divine_user.module.divine.book.pay;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fansonlib.bean.StepBean;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.stepprogress.HorizontalStepProgress;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseMvpFragment;
import com.yinghai.a24divine_user.bean.SubmitOrderBean;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.module.divine.DivineActivity;
import com.yinghai.a24divine_user.module.divine.book.pay.mvp.ContractSubmitOrder;
import com.yinghai.a24divine_user.module.divine.book.pay.mvp.SubmitOrderPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by：fanson
 *         Created on：2017/11/1 16:04
 *         Description：预约服务支付的 fragment
 */

public class DivineBookPayFragment extends MyBaseMvpFragment<SubmitOrderPresenter> implements ContractSubmitOrder.IView, PayWaysWindow.PayCallback {

    private Button mConfirmPay;
    private TextView mTvUserName;
    private TextView mTvMasterName;
    private TextView mTvBusinessName;
    private TextView mTvPrice;
    private Bundle mBundle;
    private ImageView mIvBack;
    private    PayWaysWindow payWaysWindow;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_divine_book_pay;
    }

    @Override
    protected SubmitOrderPresenter createPresenter() {
        return new SubmitOrderPresenter(this);
    }

    public static DivineBookPayFragment newInstance(Bundle args) {
        DivineBookPayFragment fragment = new DivineBookPayFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected View initView(View view, Bundle bundle) {
        initStepProgress();
        mIvBack = findMyViewId(R.id.iv_back);
        mConfirmPay = findMyViewId(R.id.btn_confirm_pay);
        mTvUserName = findMyViewId(R.id.tv_user_name);
        mTvMasterName = findMyViewId(R.id.tv_master_name);
        mTvBusinessName = findMyViewId(R.id.tv_divine_business);
        mTvPrice = findMyViewId(R.id.tv_book_price);
        return rootView;
    }

    @Override
    protected void initData() {
        mBundle = getArguments();
        mTvUserName.setText(String.format(getString(R.string.user_name), SharePreferenceHelper.getString(ConstantPreference.S_USER_NAME, "")));
        mTvMasterName.setText(getString(R.string.master_name)+ ((DivineActivity)hostActivity).mMasterName);
        mTvBusinessName.setText(String.format(getString(R.string.divine_service), ((DivineActivity)hostActivity).mBusinessName));
        mTvPrice.setText(String.format(getString(R.string.money_unit),((DivineActivity)hostActivity).mPrice/100));
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mConfirmPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoading();
                mPresenter.onSubmitOrder(((DivineActivity)hostActivity).mMasterId,
                        ((DivineActivity)hostActivity).mBusinessId,
                        mBundle.getInt("SEX"),
                        ((DivineActivity)hostActivity).mAppoitment,
                        mBundle.getString("BIRTHDAY"),
                        mBundle.getString("REMARK"));
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
    protected void initToolbarTitle() {

    }

    private void initStepProgress() {
        HorizontalStepProgress mStepProgress = findMyViewId(R.id.stepProgress);
        List<StepBean> stepList = new ArrayList<>();
        StepBean bean1 = new StepBean(getString(R.string.divine_type2), StepBean.STEP_COMPLETED);
        StepBean bean2 = new StepBean(getString(R.string.edit_info), StepBean.STEP_COMPLETED);
        StepBean bean3 = new StepBean(getString(R.string.confirm_pay), StepBean.STEP_CURRENT);
        stepList.add(bean1);
        stepList.add(bean2);
        stepList.add(bean3);
        mStepProgress.setStepViewTexts(stepList)
                .setTextSize(12)
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(getActivity(), android.R.color.holo_blue_light))
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(getActivity(), R.color.grey))
                .setStepViewComplectedTextColor(ContextCompat.getColor(getActivity(), R.color.grey))
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(getActivity(), R.color.grey))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(getActivity(), R.mipmap.ic_complted))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(getActivity(), R.mipmap.ic_not_complete))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(getActivity(), R.mipmap.ic_ing));
    }


    @Override
    public void showSubmitSuccess(SubmitOrderBean.DataBean bean) {
        hideLoading();
        payWaysWindow = new PayWaysWindow(hostActivity,bean.getTfOrder().getOOrderNo(),1,bean.getTfOrder().getOAmount(),this);
        payWaysWindow.showPopupWindow();
    }

    @Override
    public void showSubmitFailure(String errorMsg) {
        hideLoading();
        ShowToast.singleShort(errorMsg);
    }

    @Override
    public void showPayLoading() {
    }

    @Override
    public void paySuccess() {
        payWaysWindow.dismiss();
        mMultiFragmentListener.onMultiFragment(ConFragment.DIVINE_PAY_SUCCESS);
    }

    @Override
    public void payFailure(int errCode) {
        ShowToast.singleShort("支付失败"+errCode);
    }
}
