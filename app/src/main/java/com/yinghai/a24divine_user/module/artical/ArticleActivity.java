package com.yinghai.a24divine_user.module.artical;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;

import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseActivity;
import com.yinghai.a24divine_user.callback.IBackFragmentListener;
import com.yinghai.a24divine_user.callback.OnFragmentListener;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.module.artical.details.DetailFragment;
import com.yinghai.a24divine_user.module.artical.list.ArticleFragment;
import com.yinghai.a24divine_user.utils.MyStatusBarUtil;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/27 14:10
 *         Describe：名师文章Activity
 */

public class ArticleActivity extends MyBaseActivity implements OnFragmentListener, IBackFragmentListener, ArticleFragment.IOpenDetailsListener {
    public static final String ARTICLE_ID = "ArticleId";
    public static int mType; // 标识打开文章列表还是文章详情
    public static final int TYPE_LIST = 1; //列表
    public static final int TYPE_DETAIL = 2; //详情
    private int mArticleId;
    private ArticleFragment mArticleFragment;
    private DetailFragment mDetailsFragment;

    /**
     * 打开文章详情
     *
     * @param activity
     * @param type      类型：打开1文章列表 or2 文章详情
     * @param articleId 文章ID
     */
    public static void startActivityToDetail(Activity activity, int type, int articleId) {
        Intent intent = new Intent(activity, ArticleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("TYPE", type);
        bundle.putInt(ARTICLE_ID, articleId);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.scale_in_from_center, R.anim.scale_out);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_article;
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        MyStatusBarUtil.setDrawableStatus(this, R.drawable.shape_toolbar_bg);
        if (getIntent().getExtras() != null) {
            mArticleId = getIntent().getExtras().getInt(ARTICLE_ID);
        }
        initFragment();
    }

    @Override
    protected void listenEvent() {

    }

    @Override
    public void onFragment(Object object) {
        super.onFragment(object);
        switch ((int) object) {
            case ConFragment.FRAGMENT_BACK:
                if (mArticleFragment != null) {
                    switchFragmentWithTag(R.id.fl_article, mDetailsFragment, mArticleFragment, ArticleFragment.class.getSimpleName());
                } else {
                    finish();
                    overridePendingTransition(R.anim.bottom_in, R.anim.scale_out_from_center);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 根据类型，判断加载文章列表or文章详情
     */
    private void initFragment() {
        if (getIntent().getIntExtra("TYPE", TYPE_LIST) == TYPE_LIST) {
            initArticleFragment();
        } else {
            initDetailsFragment(mArticleId);
        }
    }

    /**
     * 加载文章列表Fragment
     */
    private void initArticleFragment() {
        if (mArticleFragment == null) {
            mArticleFragment = new ArticleFragment();
        }
        replaceFragmentToStack(R.id.fl_article, mArticleFragment, ArticleFragment.class.getSimpleName());
    }

    /**
     * 初始化并加载详情界面
     */
    private void initDetailsFragment(int needShowArticleId) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARTICLE_ID, needShowArticleId);
        mDetailsFragment = DetailFragment.newInstance(bundle);
        if (mArticleFragment != null) {
            switchFragmentWithTag(R.id.fl_article, mArticleFragment, mDetailsFragment, DetailFragment.class.getSimpleName());
        } else {
            replaceFragmentToStack(R.id.fl_article, mDetailsFragment, ArticleFragment.class.getSimpleName());
        }
    }

    @Override
    public void currentFragmentBack(Fragment fragment) {
        mCurrentFragmentList.add(fragment);
    }

    @Override
    public void onLookComments(int articleId) {
        initDetailsFragment(articleId);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            this.finish();
            overridePendingTransition(R.anim.bottom_in, R.anim.scale_out_from_center);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCurrentFragmentList != null) {
            mCurrentFragmentList.clear();
            mCurrentFragmentList = null;
        }
        mArticleFragment = null;
        mDetailsFragment = null;
    }


}
