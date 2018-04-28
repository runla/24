package com.yinghai.a24divine_user.module.artical.details;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fansonlib.image.ImageLoaderUtils;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.customeditor.IvTextView;
import com.example.fansonlib.widget.customeditor.OnClickImageListener;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.animation.CubeAnimation;
import com.yinghai.a24divine_user.base.MyBaseMvpFragment;
import com.yinghai.a24divine_user.bean.ArticleDetailBean;
import com.yinghai.a24divine_user.bean.CommentBean;
import com.yinghai.a24divine_user.bean.PictureBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConstAdapter;
import com.yinghai.a24divine_user.function.previewImage.PhotoActivity;
import com.yinghai.a24divine_user.module.artical.details.mvp.CommentPresenter;
import com.yinghai.a24divine_user.module.artical.details.mvp.ContractArticleDetails;
import com.yinghai.a24divine_user.module.friend.FriendActivity;
import com.yinghai.a24divine_user.module.login.state.LoginStateManager;
import com.yinghai.a24divine_user.utils.DimensUtils;
import com.yinghai.a24divine_user.utils.LogUtils;
import com.yinghai.a24divine_user.utils.StringUtils;
import com.yinghai.a24divine_user.widget.EditWindow;
import com.yinghai.a24divine_user.widget.MenuWindow;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.yinghai.a24divine_user.SampleApplication.context;
import static com.yinghai.a24divine_user.module.setting.SettingFragment.IMAGE_PICKER;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/27 14:12
 *         Describe：名师文章详情Fragment
 */

public class DetailFragment extends MyBaseMvpFragment<CommentPresenter> implements ContractArticleDetails.IView, EditWindow.IEditListener, OnAdapterListener,
        MenuWindow.OnMenuChooseCallback, OnClickImageListener {

    private static final String TAG = DetailFragment.class.getSimpleName();
    private static final String ARTICLE_ID = "ArticleId";
    private CommentAdapter mAdapter;
    private TextView mTvCommentCount;
    private IvTextView mTvContent;
    private AutoLoadRecyclerView mRecyclerView;
    private FloatingActionButton mFabPublish;
    private EditWindow mEditWindow;
    private NestedScrollView mNestedScrollView;
    private AnimatorSet mAnimatorSet;
    /**
     * 存储选中的图片
     */
    private ArrayList<PictureBean> mPictureList;
    /**
     * 立体翻转的动画时间
     */
    private static final int ANIM_DURATION = 500;
    private static final int COMMENT_TYPE_ARTICLE = 1;

    private TextView mTvAuthorName;
    private TextView mTvArticlePublishTime;
    private TextView mTvArticleTitle;
    private ImageView mIvAuthorHeader;

    private ImageView mIvBack;             // 标题栏上返回按钮
    private TextView mTvToolbarTitle;             // 标题栏上文章的名称

    /**
     * Fab按钮是否显示中
     */
    private boolean mFabIsShowing = true;
    /**
     * 根据传入的文章ID进行详情加载
     */
    private int mShowDetailForArticleId;
    private static final int SHOW_TITLE_IN_TOOLBAR_HEIGHT = 40;
    private static final int HIDE_TITLE_IN_TOOLBAR_HEIGHT = 10;
    private float mScrollY = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_artical_details;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (enter) {
            return CubeAnimation.create(CubeAnimation.LEFT, true, ANIM_DURATION);
        } else {
            return CubeAnimation.create(CubeAnimation.RIGHT, false, ANIM_DURATION);
        }
    }

    public static DetailFragment newInstance(Bundle args) {
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view,bundle);
        mFabPublish = findMyViewId(R.id.fab_publish);
        mTvContent = findMyViewId(R.id.tv_article_detail);
        mTvContent.setOnClickImageListener(this);
        mTvCommentCount = findMyViewId(R.id.tv_comments_count);
        mNestedScrollView = findMyViewId(R.id.scrollView);
        mTvAuthorName = findMyViewId(R.id.iv_author_name);
        mTvArticlePublishTime = findMyViewId(R.id.tv_publish_time);
        mTvArticleTitle = findMyViewId(R.id.tv_article_title);
        mIvAuthorHeader = findMyViewId(R.id.iv_author_header);

        mIvBack = findMyViewId(R.id.iv_back);
        mTvToolbarTitle = findMyViewId(R.id.tv_title_toolbar);

        initRecyclerView();
        return rootView;
    }

    @Override
    protected void initData() {
        mPictureList = new ArrayList<>();
        mTvCommentCount.setText(getString(R.string.loading_comments));
        mShowDetailForArticleId = getArguments().getInt(ARTICLE_ID);
        mPresenter.onComments(COMMENT_TYPE_ARTICLE, mShowDetailForArticleId);
        mPresenter.onDetails(mShowDetailForArticleId);
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mFabPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginStateManager.getInstance().onComment(hostActivity)) {
                    mEditWindow = new EditWindow();
                    mEditWindow.setListener(DetailFragment.this);
                    mEditWindow.setHintContent(getString(R.string.please_input_comment));
                    mEditWindow.setOutCancel(true).show(getFragmentManager());
                }
            }
        });

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFragmentListener != null) {
                    mFragmentListener.onFragment(ConFragment.FRAGMENT_BACK);
                }
            }
        });

        //滚动监听，隐藏评论按钮，防止按钮遮挡住“更多”的按钮
        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    // 向下滑动
                    hideFabAnim();
                    setTitleAuthorInfo(scrollY - oldScrollY);
                }

                if (scrollY < oldScrollY) {
                    // 向上滑动
                    showFabAnim();
                    setTitleAuthorInfo(scrollY - oldScrollY);
                }

                if (scrollY == 0) {
                    // 顶部
                }

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    // 底部
                }
            }
        });
    }


    /**
     * 设置标题栏的作者信息显示和隐藏
     */
    private void setTitleAuthorInfo(float scrollY) {
        if (mAnimatorSet==null){
            mAnimatorSet = new AnimatorSet();
        }
        mScrollY = mScrollY + scrollY;
        if (DimensUtils.pxToDip(hostActivity, mScrollY) >= SHOW_TITLE_IN_TOOLBAR_HEIGHT) {
            if (!mTvToolbarTitle.isShown()) {
                mTvToolbarTitle.setVisibility(View.VISIBLE);
                mTvArticleTitle.setVisibility(View.INVISIBLE);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(mTvToolbarTitle, "scaleY", 0, 1);
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(mTvToolbarTitle, "scaleX", 0, 1);
                mAnimatorSet.playTogether(scaleX, scaleY);
                mAnimatorSet.start();
            }
        } else {
            if (!mTvArticleTitle.isShown() && (DimensUtils.pxToDip(hostActivity, mScrollY) <= HIDE_TITLE_IN_TOOLBAR_HEIGHT)) {
                mTvArticleTitle.setVisibility(View.VISIBLE);
                mTvToolbarTitle.setVisibility(View.GONE);
                ObjectAnimator scaleTopY = ObjectAnimator.ofFloat(mTvArticleTitle, "scaleY", 1, 0);
                ObjectAnimator scaleTopX = ObjectAnimator.ofFloat(mTvArticleTitle, "scaleX", 1, 0);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(mTvArticleTitle, "scaleY", 0, 1);
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(mTvArticleTitle, "scaleX", 0, 1);
                mAnimatorSet.playTogether(scaleTopY, scaleTopX, scaleX, scaleY);
                mAnimatorSet.start();
            }
        }
    }

    private void initRecyclerView() {
        mRecyclerView = findMyViewId(R.id.rv_comments);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(hostActivity));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setOnPauseListenerParams(true, true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(hostActivity, DividerItemDecoration.VERTICAL));
        mAdapter = new CommentAdapter(hostActivity);
        mAdapter.setOnAdapterListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initToolbarTitle() {
    }

    @Override
    protected CommentPresenter createPresenter() {
        return new CommentPresenter(this);
    }

    private void deleteComment(int commentId) {
        showLoading();
        mPresenter.onDeleteComments(commentId);
    }

    @Override
    public void showCommentsFailure(String msg) {
        ShowToast.singleShort(msg);
    }

    @Override
    public void showCommentsSuccess(CommentBean.DataBean bean) {
        mRecyclerView.loadFinish(null);
        if (bean.getComments() != null && bean.getComments().size() > 0) {
            mAdapter.appendList(bean.getComments());
            mTvCommentCount.setText(getString(R.string.comment));
        }
        if (mAdapter.getItemCount() == 0) {
            mTvCommentCount.setText(getString(R.string.no_comment));
        }
        hideLoading();
    }

    @Override
    public void showPublishFailure(String msg) {
        ShowToast.singleShort(msg);
        hideLoading();
    }

    @Override
    public void showPublishSuccess(CommentBean.DataBean bean) {
        if (mEditWindow != null) {
            mEditWindow.dismiss();
        }
        if (bean.getComments() != null) {
            mAdapter.proposeList(bean.getComments());
            mAdapter.notifyDataSetChanged();
        }
        ShowToast.singleShort(getString(R.string.publish_success));
        hideLoading();
    }

    @Override
    public void showDeleteFailure(String msg) {
        ShowToast.singleShort(msg);
        hideLoading();
    }

    @Override
    public void showDeleteSuccess(CommentBean.DataBean bean) {
        if (bean.getComments() != null && bean.getComments().size() > 0) {
            mAdapter.removeItem(bean.getComments().get(0));
            ShowToast.singleShort(getString(R.string.delete_success));
        }
        hideLoading();
    }

    /**
     * 返回监听的填写内容
     *
     * @param content 内容
     */
    @Override
    public void getEditContent(String content) {
        showLoading();
        mPresenter.onPublishComments(COMMENT_TYPE_ARTICLE, mShowDetailForArticleId, content);
    }

    @Override
    public void clickItem(Object... object) {
        switch ((Integer) object[0]) {
            case ConstAdapter.CLICK_COMMENT_ITEM:
                MenuWindow mMenuWindow = new MenuWindow(hostActivity, this, (CommentBean.DataBean.CommentsBean) object[1]);
                mMenuWindow.showPopupWindow((View) object[2]);
                break;
            case ConstAdapter.CLICK_USER_PHOTO:
                if (LoginStateManager.getInstance().onLookUserInfo(hostActivity)) {
                    FriendActivity.startActivityToDetail(hostActivity, FriendActivity.TYPE_DETAIL, (Integer) object[1], false);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 异步方式显示数据
     *
     * @param html
     */
    private void showDataSync(final String html) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                showEditData(e, html);
            }
        })
                .subscribeOn(Schedulers.io())//生产事件在io
                .observeOn(AndroidSchedulers.mainThread())//消费事件在UI线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        LogUtils.d(TAG, "解析错误：图片不存在或已损坏");
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String text) {
                        if (text.contains("<img") && text.contains("src=")) {
                            String imagePath = ConHttp.BASE_URL + StringUtils.getImgSrc(text);
                            mTvContent.addImageViewAtIndex(mTvContent.getLastIndex(), imagePath);
                        } else {
                            mTvContent.addTextViewAtIndex(mTvContent.getLastIndex(), text);
                        }
                    }
                });
    }

    /**
     * 显示数据
     *
     * @param html
     */
    private void showEditData(ObservableEmitter<? super String> subscriber, String html) {
        try {
            List<String> textList = StringUtils.cutStringByImgTag(html);
            for (int i = 0; i < textList.size(); i++) {
                String text = textList.get(i);
                subscriber.onNext(text);
            }
            subscriber.onComplete();
        } catch (Exception e) {
            e.printStackTrace();
            subscriber.onError(e);
        }
    }

    /**
     * 点击菜单的删除键
     *
     * @param mCommentId
     */
    @Override
    public void delete(int mCommentId) {
        deleteComment(mCommentId);
    }

    /**
     * 点击菜单的复制
     */
    @Override
    public void copy(String content) {
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("copy", content);
        cmb.setPrimaryClip(clip);
        ShowToast.singleLong(getString(R.string.copy_success));
    }

    @Override
    public void showArticleDetailSuccess(ArticleDetailBean.DataBean bean) {
        LogUtils.d(TAG, "获取文章详情成功");
        mTvArticlePublishTime.setText(StringUtils.subStartString(10, bean.getTfArticle().getAPublishDate()));
        mTvArticleTitle.setText(bean.getTfArticle().getATitle());
        mTvToolbarTitle.setText(bean.getTfArticle().getATitle());

        if (bean.getTfArticle().getTfMaster() != null) {
            mTvAuthorName.setText(bean.getTfArticle().getTfMaster().getMNick());
            ImageLoaderUtils.loadCircleImage(hostActivity, mIvAuthorHeader, ConHttp.BASE_URL + bean.getTfArticle().getTfMaster().getMHeadImg());
        } else {
            mTvAuthorName.setText(R.string.no_name);
            ImageLoaderUtils.displayFromDrawable(hostActivity, R.mipmap.app_logo, mIvAuthorHeader);
        }

        showDataSync(bean.getTfArticle().getAContent());
    }

    @Override
    public void showArticleDetailFailure(String errorMsg) {
        ShowToast.singleShort(errorMsg);
        hideLoading();
    }

    /**
     * 点击文章里的图片
     *
     * @param imgUrl
     */
    @Override
    public void onClickImg(String imgUrl) {
        LogUtils.d(TAG, "Your click image url : " + imgUrl);
        mPictureList.clear();
        PictureBean pictureBean = new PictureBean();
        pictureBean.setUrlPicture(imgUrl);
        mPictureList.add(pictureBean);
        Intent intent = new Intent(hostActivity, PhotoActivity.class);
        intent.putParcelableArrayListExtra("imagePaths", mPictureList);
        intent.putExtra("position", 0);
        intent.putExtra(PhotoActivity.IS_NEED_TOOLBAR, false);
        startActivityForResult(intent, IMAGE_PICKER);
    }

    /**
     * 隐藏评论按钮的动画
     */
    private void hideFabAnim() {
        if (mFabIsShowing) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(mFabPublish, "translationY", 0, DimensUtils.dipToPx(hostActivity, 60));
            animator.setDuration(200);
            animator.start();
            mFabIsShowing = false;
        }

    }

    /**
     * 显示评论按钮的动画
     */
    private void showFabAnim() {
        if (!mFabIsShowing) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(mFabPublish, "translationY", DimensUtils.dipToPx(hostActivity, 60), 0);
            animator.setDuration(200);
            animator.start();
            mFabIsShowing = true;
        }
    }


}
