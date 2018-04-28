package com.yinghai.a24divine_user.module.divine.book;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.fansonlib.bean.StepBean;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;
import com.example.fansonlib.widget.stepprogress.HorizontalStepProgress;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseMvpFragment;
import com.yinghai.a24divine_user.bean.BusinessBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.module.divine.DivineActivity;
import com.yinghai.a24divine_user.module.divine.book.presenter.BusinessListPresenter;

import java.util.ArrayList;
import java.util.List;

/**
* @author Created by：fanson
* Created on：2017/12/15 13:37
* Description：占卜类型预约列表 fragment（选择大师业务）
 * @Param
*/
public class BusinessListFragment extends MyBaseMvpFragment<BusinessListPresenter> implements ContractBusinessList.IView, OnAdapterListener {

    private BusinessListAdapter mAdapter;
    private OnDivineBookListener mOnDivineBookListener;
    private AutoLoadRecyclerView mRecyclerView;
    private View mRetryView;
    private ImageView mIvBack;

    public void setmOnDivineBookListener(OnDivineBookListener mOnDivineBookListener) {
        this.mOnDivineBookListener = mOnDivineBookListener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_business_list;
    }

    public static BusinessListFragment newInstance(Bundle args) {
        BusinessListFragment fragment = new BusinessListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        initRecyclerview();
        initStepProgress();
        mIvBack = findMyViewId(R.id.iv_back);
        return rootView;
    }

    @Override
    protected void initToolbarTitle() {
    }

    @Override
    protected void initData() {
        BusinessBean.DataBean mBean = getArguments().getParcelable("BEAN");
        if (mBean != null && mBean.getTfBusinessList() != null) {
            mAdapter.fillList(mBean.getTfBusinessList());
        }else {
            showLoading();
            mPresenter.getBusinessList(((DivineActivity)hostActivity).mMasterId);
        }
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });
    }

    private void initStepProgress() {
        HorizontalStepProgress mStepProgress = findMyViewId(R.id.stepProgress);
        List<StepBean> stepList = new ArrayList<>();
        StepBean bean1 = new StepBean(getString(R.string.divine_type2), StepBean.STEP_CURRENT);
        StepBean bean2 = new StepBean(getString(R.string.edit_info), StepBean.STEP_UNDO);
        StepBean bean3 = new StepBean(getString(R.string.confirm_pay), StepBean.STEP_UNDO);
        stepList.add(bean1);
        stepList.add(bean2);
        stepList.add(bean3);
        mStepProgress.setStepViewTexts(stepList)
                .setTextSize(12)
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(getActivity(), android.R.color.holo_blue_light))
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(getActivity(), R.color.grey))
                .setStepViewComplectedTextColor(ContextCompat.getColor(getActivity(), R.color.grey))
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(getActivity(), R.color.grey));
    }

    private void initRecyclerview() {
        mRecyclerView = findMyViewId(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(hostActivity));
        mAdapter = new BusinessListAdapter(hostActivity);
        mAdapter.setOnAdapterListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected BusinessListPresenter createPresenter() {
        return new BusinessListPresenter(this);
    }

    @Override
    public void showBusinessListSuccess(BusinessBean.DataBean bean) {
        mRecyclerView.loadFinish(null);
        if (bean.getTfBusinessList() != null && bean.getTfBusinessList().size() > 0) {
            mAdapter.appendList(bean.getTfBusinessList());
        }
        if (mAdapter.getItemCount() == 0) {
            mAdapter.addHeaderView(LayoutInflater.from(getContext()).inflate(R.layout.layout_no_data, mRecyclerView, false));
        }
        hideLoading();

    }

    @Override
    public void showBusinessListFailure(String errMsg) {
        if (mRetryView == null) {
            mRetryView = LayoutInflater.from(getContext()).inflate(R.layout.layout_retry, mRecyclerView, false);
        }
        mAdapter.addHeaderView(mRetryView);
        (mRetryView.findViewById(R.id.td_retry)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoading();
                mPresenter.getBusinessList(((DivineActivity)hostActivity).mMasterId);
            }
        });
        ShowToast.singleShort(errMsg);
        hideLoading();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnDivineBookListener = null;
    }

    /**
     * 点击Adapter item后的回调
     *
     * @param object 序号/Model
     */
    @Override
    public void clickItem(Object... object) {
        if (mOnDivineBookListener != null) {
            mOnDivineBookListener.openInputUserInfoFragment((BusinessBean.DataBean.TfBusinessListBean) object[0]);
        }
    }

    public interface OnDivineBookListener {
        /**
         * 去打开填写用户信息的界面的回调
         *
         * @param bean
         */
        void openInputUserInfoFragment(BusinessBean.DataBean.TfBusinessListBean bean);
    }

}
