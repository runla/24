package com.yinghai.a24divine_user.module.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.fansonlib.utils.DateFormatUtil;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseMvpFragment;
import com.yinghai.a24divine_user.bean.ArticleBean;
import com.yinghai.a24divine_user.bean.BusinessTypeBean;
import com.yinghai.a24divine_user.bean.CollectBean;
import com.yinghai.a24divine_user.bean.LuckBean;
import com.yinghai.a24divine_user.bean.MasterBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.constant.ConstOther;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.module.artical.ArticleActivity;
import com.yinghai.a24divine_user.module.artical.list.ArticleAdapter;
import com.yinghai.a24divine_user.module.divine.DivineActivity;
import com.yinghai.a24divine_user.module.divine.divinelist.DivineAdapter;
import com.yinghai.a24divine_user.module.login.state.LoginStateManager;
import com.yinghai.a24divine_user.module.main.adapter.MainBussinessTypeAdapter;
import com.yinghai.a24divine_user.module.main.presenter.MainPresenter;
import com.yinghai.a24divine_user.utils.ConstellationUtils;
import com.yinghai.a24divine_user.utils.LogUtils;
import com.yinghai.a24divine_user.widget.ConstellationWindow;
import com.yinghai.a24divine_user.widget.NoteView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by：fanson
 *         Created on：2017/10/24 13:16
 *         Describe：主页的Fragment
 */

public class IndexFragment extends MyBaseMvpFragment<MainPresenter> implements ContractMain.IMainView, OnAdapterListener, ArticleAdapter.IAdapterListener, DivineAdapter.IAdapterListener, ConstellationWindow.IConstellationListener {

    private static final String TAG = IndexFragment.class.getSimpleName();
    private MainBussinessTypeAdapter mCenterAdapter;
    private DivineAdapter mDivineAdapter;
    private ArticleAdapter mArticleAdapter;
    private TextView mTvEmotion;            //情感
    private TextView mTvCareer;             //事业
    private TextView mTvConstellation;      //星座
    private TextView mTvLuckNum;
    private TextView mTvLuckColor;
    private TextView mTvSuitable;
    private TextView mTvUnsuitable;
    private NoteView mNoteBook;
    private TextView mTvDate, mTvMonthWeek;
    private AutoLoadRecyclerView mMasterRecycler, mArticleRecycler;
    private View mRetryView;

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this, hostActivity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        LogUtils.d(TAG, "initView");
        mTvEmotion = findMyViewId(R.id.tv_emotion);
        mTvCareer = findMyViewId(R.id.tv_career);
        mTvConstellation = findMyViewId(R.id.tv_constellation);
        mTvLuckNum = findMyViewId(R.id.tv_luck_num);
        mTvLuckColor = findMyViewId(R.id.tv_luck_color);
        mTvSuitable = findMyViewId(R.id.tv_luck_do);
        mTvUnsuitable = findMyViewId(R.id.tv_luck_undo);

        mNoteBook = findMyViewId(R.id.note_book);
        mTvDate = findMyViewId(R.id.tv_day);
        mTvMonthWeek = findMyViewId(R.id.tv_month_week);
        initCenterRecyclerView();
        initMasterRecyclerView();
        initArticleRecyclerView();
        return rootView;
    }

    @Override
    protected void initToolbarTitle() {

    }

    private void initMasterRecyclerView() {
        mMasterRecycler = (AutoLoadRecyclerView) rootView.findViewById(R.id.recyclerview_master);
        mMasterRecycler.setNestedScrollingEnabled(false);
        mMasterRecycler.setLayoutManager(new LinearLayoutManager(hostActivity));
        mDivineAdapter = new DivineAdapter(hostActivity);
        mDivineAdapter.setAdapterListener(this);
        mMasterRecycler.setAdapter(mDivineAdapter);
    }

    private void initArticleRecyclerView() {
        mArticleRecycler = (AutoLoadRecyclerView) rootView.findViewById(R.id.recyclerview_article);
        mArticleRecycler.setNestedScrollingEnabled(false);
        mArticleRecycler.setLayoutManager(new LinearLayoutManager(hostActivity));
        mArticleAdapter = new ArticleAdapter(hostActivity);
        mArticleAdapter.setIAdapterListener(this);
        mArticleRecycler.setAdapter(mArticleAdapter);
    }

    private void initCenterRecyclerView() {
        AutoLoadRecyclerView mCenterRecycler = (AutoLoadRecyclerView) rootView.findViewById(R.id.recyclerview_center);
        mCenterRecycler.setHasFixedSize(true);
        mCenterRecycler.setLayoutManager(new LinearLayoutManager(hostActivity, LinearLayoutManager.HORIZONTAL, false));
        mCenterAdapter = new MainBussinessTypeAdapter(hostActivity);
        mCenterAdapter.setmOnAdapterListener(this);
        mCenterRecycler.setAdapter(mCenterAdapter);
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mNoteBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DivineActivity.startDivineActivity(hostActivity, DivineActivity.LIST_TYPE, 0);
            }
        });

        mTvConstellation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ConstellationWindow(hostActivity, IndexFragment.this).showPopupWindow();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d(TAG, "onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d(TAG, "onDestroyView");
    }

    @Override
    protected void initData() {
        List<BusinessTypeBean> list = new ArrayList<>();
        list.add(new BusinessTypeBean(R.mipmap.ic_master_article, R.mipmap.ic_article, getString(R.string.master_article)));
        list.add(new BusinessTypeBean(R.mipmap.ic_master_divine, R.mipmap.ic_argur, getString(R.string.master_divine)));
        list.add(new BusinessTypeBean(R.mipmap.ic_shpping_pic, R.mipmap.ic_shipping, getString(R.string.master_shop)));
        mCenterAdapter.fillList(list);

        if (SharePreferenceHelper.getInt(ConstantPreference.I_USER_CONSTELLATION, 0)>0) {
            mTvConstellation.setText(ConstellationUtils.getString(SharePreferenceHelper.getInt(ConstantPreference.I_USER_CONSTELLATION, -1)));
        }
        mTvDate.setText(String.valueOf(DateFormatUtil.getCurrentDay()));
        mTvMonthWeek.setText(String.valueOf(DateFormatUtil.getCurrentMonth()) + getString(R.string.month) + getString(mPresenter.printDayOfWeek(DateFormatUtil.getDayOfWeek())));

        mPresenter.getMasterDivine();
        mPresenter.getArticle();
        mPresenter.openLuck();
    }

    @Override
    public void clickItem(Object... object) {
        switch ((int) object[0]) {
            case MainBussinessTypeAdapter.ARTICAL:
                startMyActivity(ArticleActivity.class);
                hostActivity.overridePendingTransition(R.anim.scale_in_from_center, R.anim.scale_out);
                break;

            //点击主界面的“名师占卜”
            case MainBussinessTypeAdapter.AUGUR:
                DivineActivity.startDivineActivity(hostActivity, DivineActivity.LIST_TYPE, 0);
                hostActivity.overridePendingTransition(R.anim.scale_in_from_center, R.anim.scale_out);
                break;

            case MainBussinessTypeAdapter.SHOP:
                mFragmentListener.onFragment(ConFragment.OPEN_SHOP_FRAGMENT);
                break;
            default:
                break;
        }
    }

    /**
     * 点击名师文章的回调
     *
     * @param articleId 文章ID
     */
    @Override
    public void onClickArticleCardView(int articleId) {
        ArticleActivity.startActivityToDetail(hostActivity, ArticleActivity.TYPE_DETAIL, articleId);
    }

    @Override
    public void onGetDivineSuccess(MasterBean.DataBean bean) {
        if (bean.getMasterList().size()>0){
            mArticleAdapter.removeHeaderView();
            mDivineAdapter.fillList(bean.getMasterList());
        }else {
            mArticleAdapter.addHeaderView(LayoutInflater.from(getContext()).inflate(R.layout.layout_no_data,mArticleRecycler,false));
        }
        hideLoading();
    }

    @Override
    public void onGetDivineFailure(String errMsg) {
        ShowToast.singleShort(errMsg);
        mArticleAdapter.addHeaderView(LayoutInflater.from(getContext()).inflate(R.layout.layout_no_data, mMasterRecycler, false));
    }

    @Override
    public void onGetArticleFailure(String msg) {
        ShowToast.singleShort(msg);
        hideLoading();
        if (mRetryView == null) {
            mRetryView = LayoutInflater.from(getContext()).inflate(R.layout.layout_retry, mArticleRecycler, false);
        }
        mArticleAdapter.addHeaderView(mRetryView);
        (mRetryView.findViewById(R.id.td_retry)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoading();
                mPresenter.getMasterDivine();
                mPresenter.getArticle();
            }
        });
    }

    @Override
    public void onGetArticleSuccess(ArticleBean.DataBean bean) {
        if (bean.getArticleList().size()>0){
            mArticleAdapter.removeHeaderView();
            mArticleAdapter.fillList(bean.getArticleList());
        }else {
            mArticleAdapter.addHeaderView(LayoutInflater.from(getContext()).inflate(R.layout.layout_no_data,mArticleRecycler,false));
        }
        hideLoading();
    }

    @Override
    public void onGetLuckSuccess(LuckBean luckBean) {
        if (luckBean.getData() == null) {
            ShowToast.singleShort(getString(R.string.no_data));
            return;
        }
        mTvEmotion.setText(luckBean.getData().getLEmotion());
        mTvCareer.setText(luckBean.getData().getLUndertaking());
        mTvLuckNum.setText(String.format(getString(R.string.luck_number), luckBean.getData().getLNumber()));
        mTvLuckColor.setText(String.format(getString(R.string.luck_color), luckBean.getData().getLColor()));
        mTvSuitable.setText(String.format(getString(R.string.luck_do), luckBean.getData().getLSuit()));
        mTvUnsuitable.setText(String.format(getString(R.string.luck_undo), luckBean.getData().getLUnsuitable()));
    }

    @Override
    public void onGetLuckFailure(String errMsg) {
        ShowToast.singleShort(errMsg);
    }

    @Override
    public void showCollectSuccess(CollectBean.DataBean bean, int id) {
        if (bean.getCollection() != null && bean.getCollection().get(0).getArticle() != null) {
            mArticleAdapter.updateLike(id, true);
        } else {
            mDivineAdapter.updateLike(id, true);
        }
        ShowToast.singleShort(getString(R.string.collect_add_success));
        hideLoading();
    }

    @Override
    public void showCollectFailure(String errMsg) {
        ShowToast.singleShort(getString(R.string.collect_add_failure) + errMsg);
        hideLoading();
    }

    @Override
    public void showCancelCollectSuccess(CollectBean.DataBean bean, int id) {
        if (bean.getCollection() != null && bean.getCollection().get(0).getArticle() != null) {
            mArticleAdapter.updateLike(id, false);
        } else {
            mDivineAdapter.updateLike(id, false);
        }
        ShowToast.singleShort(getString(R.string.collect_cancel_success));
        hideLoading();
    }

    @Override
    public void showCancelCollectFailure(String errMsg) {
        ShowToast.singleShort(getString(R.string.collect_cancel_failure) + errMsg);
        hideLoading();
    }

    @Override
    public void onClickDivineRootView(MasterBean.DataBean.MasterListBean bean) {
        // 进入占卜师主界面
        DivineActivity.startDivineActivity(hostActivity, DivineActivity.MASTER_INDEX_TYPE, bean.getMasterId());
    }

    /**
     * 点击收藏大师
     *
     * @param bean
     */
    @Override
    public void onClickLikeDivine(MasterBean.DataBean.MasterListBean bean) {
        if (LoginStateManager.getInstance().onCollect(hostActivity)) {
            showLoading();
            mPresenter.onCollect(ConstOther.MASTER, bean.getMasterId(), bean.isIsCollection());
        }
    }

    /**
     * 收藏名师文章
     *
     * @param bean 数据
     */
    @Override
    public void onClickLikeArticle(ArticleBean.DataBean.ArticleListBean bean) {
        if (LoginStateManager.getInstance().onBookDivine(hostActivity)) {
            showLoading();
            mPresenter.onCollect(ConstOther.ARTICLE, bean.getArticleId(), bean.isIsCollection());
        }
    }

    /**
     * 选择星座后，回调数据回来，去后台请求数据
     *
     * @param constellation 选择的星座
     */
    @Override
    public void getConstellation(String constellation) {
        ShowToast.singleShort(getString(R.string.loading_please_wait));
        mTvConstellation.setText(constellation);
        mPresenter.changeLuck(ConstellationUtils.getCode(constellation));
    }
}
