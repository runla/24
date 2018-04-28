package com.yinghai.a24divine_user.module.artical.list;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.fansonlib.callback.LoadMoreListener;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;
import com.example.fansonlib.widget.textview.TextViewDrawable;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.animation.CubeAnimation;
import com.yinghai.a24divine_user.base.BaseMvpSwipeFragment;
import com.yinghai.a24divine_user.bean.ArticleBean;
import com.yinghai.a24divine_user.bean.BusinessTypeListBean;
import com.yinghai.a24divine_user.module.artical.list.presenter.ArticlePresenter;
import com.yinghai.a24divine_user.module.login.state.LoginStateManager;
import com.yinghai.a24divine_user.widget.ChooseTypeWindow;

import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/27 14:11
 *         Describe：名师文章列表Fragmnent
 */

public class ArticleFragment extends BaseMvpSwipeFragment<ArticlePresenter> implements ContractArticle.IArticleView, ArticleAdapter.IAdapterListener, LoadMoreListener
        , ChooseTypeWindow.IChooseTypeListener{

    /**
     * 已选的业务类型
     */
    private String mChooseBusinessType = null;

    /**
     * 标识数据是否已全部加载完毕
     */
    private boolean mIsLoadComplete = false;

    /**
     * 请求所有大师的文章的标识
     */
    private static final int ALL_MASTER = 0;
    /**
     * 立体翻转的动画时间
     */
    private static final int ANIM_DURATION = 500;
    private static final int PAGE_SIZE = 10;
    private ArticleAdapter mAdapter;
    private AutoLoadRecyclerView mRecyclerView;
    private boolean mIsPull = false; //标识是否上拉刷新
    private IOpenDetailsListener mIOpenDetailsListener;
    private TextViewDrawable mTdChooseType;
    private ChooseTypeWindow mChooseTypeWindow;
    private ImageView mIvBack;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mIOpenDetailsListener = (IOpenDetailsListener) hostActivity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mIOpenDetailsListener = null;
    }

    @Override
    protected ArticlePresenter createPresenter() {
        return new ArticlePresenter(this, hostActivity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_artical;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (enter) {
            return CubeAnimation.create(CubeAnimation.RIGHT, true, ANIM_DURATION);
        } else {
            return CubeAnimation.create(CubeAnimation.LEFT, false, ANIM_DURATION);
        }
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        initRecyclerView();
        mIvBack = findMyViewId(R.id.btn_back_toolbar);
        mTdChooseType = findMyViewId(R.id.td_choose);
        mTdChooseType.setText(getString(R.string.all));
        return rootView;
    }

    private void initRecyclerView() {
        mRecyclerView = findMyViewId(R.id.rv_article);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(hostActivity));
        mRecyclerView.setOnPauseListenerParams(true, true);
        mRecyclerView.setLoadMoreListener(this);
        mAdapter = new ArticleAdapter(hostActivity);
        mAdapter.setIAdapterListener(this);

        SlideInBottomAnimationAdapter slideAdapter = new SlideInBottomAnimationAdapter(mAdapter);
        slideAdapter.setDuration(1000);
        slideAdapter.setFirstOnly(false);
        mRecyclerView.setAdapter(slideAdapter);
    }

    @Override
    protected void initToolbarTitle() {
    }

    @Override
    protected void initData() {
        showLoading();
        getArticle(mChooseBusinessType);
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mTdChooseType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mChooseTypeWindow == null) {
                    mChooseTypeWindow = new ChooseTypeWindow(hostActivity, ArticleFragment.this);
                }
                if (mChooseTypeWindow.isShowing()) {
                    mChooseTypeWindow.dismiss();
                }else {
                    mChooseTypeWindow.showPopupWindow();
                }
            }
        });

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hostActivity.finish();
                hostActivity.overridePendingTransition(R.anim.bottom_in, R.anim.scale_out_from_center);
            }
        });
    }

    /**
     * 网络获取大师文章
     * @param articleType 文章类型，null为所有
     */
    private void getArticle(String articleType) {
        mPresenter.onGetArticleList(ALL_MASTER, articleType, PAGE_SIZE);
    }

    /**
     * 点击帅选类型后的触发
     *
     * @param businessType 类型
     */
    public void changeBusinessType(String businessType) {
        showLoading();
        mChooseBusinessType = businessType;
        mIsLoadComplete = false;
        mAdapter.clearList();
        mPresenter.resetPageNum();
        getArticle(mChooseBusinessType);
    }

    @Override
    protected void pullRefresh() {
        mIsPull = true;
        mIsLoadComplete = false;
        mPresenter.resetPageNum();
        getArticle(mChooseBusinessType);
    }

    @Override
    public void loadMore() {
        if (!mIsLoadComplete){
            getArticle(mChooseBusinessType);
        }
    }

    @Override
    public void onClickArticleCardView(int articleId) {
        mIOpenDetailsListener.onLookComments(articleId);
    }

    /**
     * 点击收藏文章
     *
     * @param bean 数据
     */
    @Override
    public void onClickLikeArticle(ArticleBean.DataBean.ArticleListBean bean) {
        if (LoginStateManager.getInstance().onCollect(hostActivity)) {
            showLoading();
            mPresenter.onCollectArticle(bean.getArticleId(), bean.isIsCollection());
        }
    }

    @Override
    public void showArticleFailure(String msg) {
        ShowToast.singleShort(msg);
        stopRefresh();
        hideLoading();
    }

    @Override
    public void showArticleSuccess(ArticleBean.DataBean bean) {
        if (mIsPull) {
            mAdapter.clearList();
            mIsPull = false;
        }
        mRecyclerView.loadFinish(null);
        if (bean.getArticleList() != null && bean.getArticleList().size() > 0) {
            hideNoDataLayout();
            mAdapter.appendList(bean.getArticleList());
            if (bean.getArticleList().size() < PAGE_SIZE) {
                mIsLoadComplete = true;
            }
        }
        if (mAdapter.getItemCount() == 0) {
            showNoDataLayout();
            mIsLoadComplete = true;
        }
        stopRefresh();
        hideLoading();
    }

    @Override
    public void showCollectSuccess(int id) {
        mAdapter.updateLike(id, true);
        ShowToast.singleShort(getString(R.string.collect_add_success));
        hideLoading();
    }

    @Override
    public void showCollectFailure(String errorMsg) {
        ShowToast.singleShort((R.string.collect_add_failure) + errorMsg);
        hideLoading();
    }

    @Override
    public void showCancelCollectSuccess(int id) {
        mAdapter.updateLike(id, false);
        ShowToast.singleShort(getString(R.string.collect_cancel_success));
        hideLoading();
    }

    @Override
    public void showCancelCollectFailure(String errorMsg) {
        ShowToast.singleShort((R.string.collect_cancel_failure) + errorMsg);
        hideLoading();
    }

    /**
     * 帅选类型的回调
     *
     * @param bean 结果
     */
    @Override
    public void onChooseType(BusinessTypeListBean.DataBean.TfBusinessTypeListBean bean) {
        mChooseTypeWindow.dismiss();
        mChooseTypeWindow = null;
        mTdChooseType.setText(bean.getTypeName());
        changeBusinessType(bean.getBusinessTypeId());
    }

    @Override
    public void onLoadDataCompleted() {
        mChooseTypeWindow.showPopupWindow();
    }

    /**
     * 监听打开详情页
     */
    public interface IOpenDetailsListener {
        /**
         * 监听详情页的评论
         * @param articleId 文章ID
         */
        void onLookComments(int articleId);
    }
}
