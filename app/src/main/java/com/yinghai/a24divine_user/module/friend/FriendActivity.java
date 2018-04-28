package com.yinghai.a24divine_user.module.friend;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseActivity;
import com.yinghai.a24divine_user.callback.IBackFragmentListener;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.module.friend.index.FriendIndexFragment;
import com.yinghai.a24divine_user.module.friend.list.FriendListFragment;
import com.yinghai.a24divine_user.utils.MyStatusBarUtil;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/27 14:35
 *         Describe：好友的Activity
 */

public class FriendActivity extends MyBaseActivity implements IBackFragmentListener {

    private ImageView mBtnBack;
    private FriendIndexFragment mFriendIndexFragment;
    private FriendListFragment mFriendListFragment;
    public static int mType = 1; // 标识打开文章列表还是文章详情
    public static final int TYPE_LIST = 1; //列表
    public static final int TYPE_DETAIL = 2; //详情
    private static int mFriendId;
    private static boolean mIsRequest;

    @Override
    protected int getContentView() {
        return R.layout.activity_friend;
    }

    /**
     * 打开好友界面
     *
     * @param activity
     * @param type      类型：打开1列表 or2 详情
     * @param friendId  好友userId
     * @param isRequest 是否是带有好友验证
     */
    public static void startActivityToDetail(Activity activity, int type, int friendId, boolean isRequest) {
        mType = type;
        mIsRequest = isRequest;
        mFriendId = friendId;
        Intent intent = new Intent(activity, FriendActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.scale_in_from_center, R.anim.scale_out);
    }

    @Override
    protected void initView() {
        super.initView();
        mTvTitle = findMyViewId(R.id.tv_title_toolbar);
        mBtnBack = findMyViewId(R.id.btn_back_toolbar);
        if (mType == TYPE_LIST) {
            initFriendListFragment();
        } else {
            initFriendIndexFragment(mFriendId, mIsRequest);
        }
    }

    @Override
    protected void initData() {
        MyStatusBarUtil.setDrawableStatus(this, R.drawable.shape_toolbar_bg);
    }

    @Override
    protected void listenEvent() {
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FriendActivity.this.finish();
                overridePendingTransition(R.anim.scale_in_from_right_bottom, R.anim.scale_out_to_right_bottom);
            }
        });
    }

    private void initFriendListFragment() {
        if (mFriendListFragment == null) {
            mFriendListFragment = new FriendListFragment();
        }
        replaceFragment(R.id.fl_friend_main, mFriendListFragment);
    }

    private void initFriendIndexFragment(int friendId, boolean isRequest) {
        mFriendIndexFragment = FriendIndexFragment.newInstance(friendId, isRequest);
        replaceFragment(R.id.fl_friend_main, mFriendIndexFragment);
    }

    @Override
    public void onMultiFragment(Object... object) {
        super.onMultiFragment(object);
        switch ((Integer) object[0]) {
            case ConFragment.OPEN_USER_VIEW:
                initFriendIndexFragment((Integer) object[1], false);
                break;
            default:
                break;
        }
    }

    @Override
    public void currentFragmentBack(Fragment fragment) {
        mCurrentFragmentList.add(fragment);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            overridePendingTransition(R.anim.scale_in_from_right_bottom, R.anim.scale_out_to_right_bottom);
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
        mFriendListFragment = null;
        mFriendIndexFragment = null;
    }
}
