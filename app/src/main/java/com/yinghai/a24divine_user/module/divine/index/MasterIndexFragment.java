package com.yinghai.a24divine_user.module.divine.index;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fansonlib.image.ImageLoaderUtils;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.animation.CubeAnimation;
import com.yinghai.a24divine_user.base.MyBaseMvpFragment;
import com.yinghai.a24divine_user.bean.BusinessBean;
import com.yinghai.a24divine_user.bean.FollowBean;
import com.yinghai.a24divine_user.bean.MasterDetailBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.callback.OnMultiFragmentListener;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.module.divine.DivineActivity;
import com.yinghai.a24divine_user.module.divine.index.presenter.MasterIndexPresenter;
import com.yinghai.a24divine_user.module.login.state.LoginStateManager;
import com.yinghai.a24divine_user.utils.LogUtils;

/**
 * @author Created by：fanson
 *         Created on：2017/11/24 10:25
 *         Description：占卜师主页
 */

public class MasterIndexFragment extends MyBaseMvpFragment<MasterIndexPresenter> implements ContractMasterIndex.IBusinessView, OnAdapterListener {

    private static final String TAG = MasterIndexFragment.class.getSimpleName();
    /**
     * 立体翻转的动画时间
     */
    private static final int ANIM_DURATION = 500;
    /**
     * 预约服务
     */
    private LinearLayout mLinearBookService;
    private OnMultiFragmentListener onMultiFragmentListener;
    private TextView mTvMasterName, mTvDescribe, mTvFollow, mTvDeals, mTvScore, mTvOnline, mTvBusinessType;
    private TextView mTvFansCount, mTvAppbarName, mTvBusinessList;
    private BusinessBean.DataBean mBusinessBean;
    private BusinessAdapter mAdapter;
    private AutoLoadRecyclerView mRecyclerView;
    private View mRetryView = null;
    private int mShowDetailForMasterId;
    private static final String PARAM_MASTER_ID = "MasterId";
    private ImageView mIvBack;

    public static MasterIndexFragment newInstance(Bundle args) {
        MasterIndexFragment fragment = new MasterIndexFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_master_divine_detail;
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
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (enter) {
            return CubeAnimation.create(CubeAnimation.LEFT, true, ANIM_DURATION);
        } else {
            return CubeAnimation.create(CubeAnimation.RIGHT, false, ANIM_DURATION);
        }
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        mTvFollow = findMyViewId(R.id.tv_master_follow);
        mLinearBookService = findMyViewId(R.id.linear_book_service);
        mTvMasterName = findMyViewId(R.id.tv_master_name);
        mTvDescribe = findMyViewId(R.id.tv_master_descripe);
        mTvFansCount = findMyViewId(R.id.tv_master_follow_num);
        mTvAppbarName = findMyViewId(R.id.tv_appbar_name);
        mTvDeals = findMyViewId(R.id.tv_master_deals);
        mTvScore = findMyViewId(R.id.tv_master_score);
        mTvOnline = findMyViewId(R.id.tv_master_online);
        mTvBusinessType = findMyViewId(R.id.tv_master_divine_type);
        mTvBusinessList = findMyViewId(R.id.tv_business_list);
        mIvBack = findMyViewId(R.id.btn_back_toolbar);
        initRecyclerView();
        return rootView;
    }

    @Override
    protected void initToolbarTitle() {
    }

    private void initRecyclerView() {
        mRecyclerView = findMyViewId(R.id.recyclerview);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setOnPauseListenerParams(true, true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(hostActivity));
        mAdapter = new BusinessAdapter(hostActivity);
        mAdapter.setmOnAdapterListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        showLoading();
        mShowDetailForMasterId = getArguments().getInt(PARAM_MASTER_ID);
        mPresenter.getMasterBusiness(mShowDetailForMasterId);
        mPresenter.getMasterDetail(mShowDetailForMasterId);
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mLinearBookService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginStateManager.getInstance().onBookDivine(hostActivity)) {
                    onMultiFragmentListener.onMultiFragment(ConFragment.OPEN_SELECT_BOOK_TIME, mBusinessBean);
                }
            }
        });

        mTvFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTvFollow.getText().toString().equals(getString(R.string.follow))) {
                    mPresenter.addFollow(mShowDetailForMasterId);
                } else {
                    mPresenter.cancelFollow(mShowDetailForMasterId);
                }
            }
        });

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() <= 1) {
                    hostActivity.finish();
                    hostActivity.overridePendingTransition(R.anim.bottom_in, R.anim.rotate_big_to_small);
                } else {
                    getFragmentManager().popBackStackImmediate();
                }
            }
        });
    }

    @Override
    protected MasterIndexPresenter createPresenter() {
        return new MasterIndexPresenter(this, hostActivity);
    }

    @Override
    public void showGetBusinessSuccess(BusinessBean.DataBean bean) {
        mTvBusinessList.setText(R.string.business_list);
        mBusinessBean = bean;
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
    public void showBusinessFailure(String errMsg) {
        mTvBusinessList.setText(R.string.business_list);
        if (mRetryView == null) {
            mRetryView = LayoutInflater.from(getContext()).inflate(R.layout.layout_retry, mRecyclerView, false);
        }
        mAdapter.addHeaderView(mRetryView);
        (mRetryView.findViewById(R.id.td_retry)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoading();
                mPresenter.getMasterBusiness(mShowDetailForMasterId);
            }
        });
        ShowToast.singleShort(errMsg);
        hideLoading();
    }

    @Override
    public void showFollowSuccess(FollowBean.DataBean bean) {
        mTvFollow.setText(getString(R.string.cancel_follow));
        hideLoading();
    }

    @Override
    public void showFollowFailuer(String errMsg) {
        ShowToast.singleShort(errMsg);
        hideLoading();
    }

    @Override
    public void showCancelFollowSuccess() {
        mTvFollow.setText(getString(R.string.follow));
        hideLoading();
    }

    @Override
    public void showCancelFollowFailuer(String errMsg) {
        ShowToast.singleShort(errMsg);
        hideLoading();
    }

    @Override
    public void clickItem(Object... object) {

    }

    @Override
    public void showMasterDetailSuccess(MasterDetailBean.DataBean bean) {
        hideLoading();
        mTvAppbarName.setText(bean.getTfMaster().getMNick());
        mTvMasterName.setText(bean.getTfMaster().getMNick());
        mTvDescribe.setText(bean.getTfMaster().getMIntroduction());
        mTvOnline.setText(bean.getTfMaster().getMStatus() == 1 ? getString(R.string.online) : getString(R.string.offline));
        mTvFansCount.setText(String.format(getString(R.string.fans), bean.getTfMaster().getMFollows()));
        mTvDeals.setText(String.format(getString(R.string.deals), bean.getTfMaster().getMDeals()));
        mTvScore.setText(String.valueOf(bean.getTfMaster().getMScore()));
        ImageLoaderUtils.loadCircleImage(hostActivity, (ImageView) findMyViewId(R.id.iv_master_photo), ConHttp.BASE_URL + bean.getTfMaster().getMHeadImg());
        if (bean.getTfMaster().isSubscribe()) {
            mTvFollow.setText(getString(R.string.cancel_follow));
        }
        if (TextUtils.isEmpty(bean.getTfMaster().getMBusinessType())) {
            mTvBusinessType.setText(getString(R.string.no_data));
        } else {
            mTvBusinessType.setText(bean.getTfMaster().getMBusinessType());
        }
        ((DivineActivity) hostActivity).mMasterId = bean.getTfMaster().getMasterId();
        ((DivineActivity) hostActivity).mMasterName = bean.getTfMaster().getMNick();
    }

    @Override
    public void showMasterDetailFailuer(String errMsg) {
        ShowToast.singleShort(errMsg);
        hideLoading();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d(TAG, "onDestroyView");
    }
}
