package com.yinghai.a24divine_user.module.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;

import com.example.fansonlib.rxbus.MyRxbus2;
import com.example.fansonlib.rxbus.annotation.Subscribe;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.dialogfragment.ConfirmDialog;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseActivity;
import com.yinghai.a24divine_user.callback.IBackFragmentListener;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.constant.RxBusTag;
import com.yinghai.a24divine_user.module.shop.shoplist.ShopFragment;
import com.yinghai.a24divine_user.rongIm.MyUnReadMessageListener;
import com.yinghai.a24divine_user.utils.MyStatusBarUtil;

import io.rong.callkit.Constants.BroadcastTag;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * @author Created by：fanson
 *         Created on：2017/11/30 15:50
 *         Description：主界面Activity
 */
public class MainActivity extends MyBaseActivity implements IBackFragmentListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private IndexFragment mIndexFragment;
    private ShopFragment mShopFragment;
    private PersonFragment mPersonFragment;
    private Fragment mCurrentFragment;
    private long exitTime;
    private BottomNavigationView mNavigation;
    private BottomNavigationItemView mItemMe;
    private Badge mItemMeBadge;
    private VideoChatBroadcastReceive mVideoChatBroadcastReceive;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void initView() {
        super.initView();
        initIndexFragment();
        mNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mItemMe = (BottomNavigationItemView) mNavigation.findViewById(R.id.navigation_person);
        MyRxbus2.getInstance().register(this);
        getUnreadMessage();

        // 注册监听视频通话的广播接收器
        mVideoChatBroadcastReceive = new VideoChatBroadcastReceive();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BroadcastTag.VIDEO_START);
        intentFilter.addAction(BroadcastTag.VIDEO_STOP);
        registerReceiver(mVideoChatBroadcastReceive, intentFilter);
    }

    @Override
    protected void setStatus() {
        MyStatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, 0, null);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void listenEvent() {

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_index:
                    switchIndexFragment();
                    return true;
                case R.id.navigation_shop:
                    switchShopFragment();
                    return true;
                case R.id.navigation_person:
                    switchPersonFragment();
                    return true;
                default:
                    break;
            }
            return false;
        }
    };

    /**
     * 初始化并加载主页Fragment
     */
    private void initIndexFragment() {
        if (mIndexFragment == null) {
            mIndexFragment = new IndexFragment();
        }
        mCurrentFragment = mIndexFragment;
        replaceFragmentToStack(R.id.fl_main, mIndexFragment, IndexFragment.class.getSimpleName());
    }

    /**
     * 切换到主页Fragment
     */
    private void switchIndexFragment() {
        //避免重复点击
        if (mCurrentFragment == mIndexFragment) {
            return;
        }
        if (mIndexFragment == null) {
            mIndexFragment = new IndexFragment();
        }
        switchFragmentWithTag(R.id.fl_main, mCurrentFragment, mIndexFragment, IndexFragment.class.getSimpleName());
        mCurrentFragment = mIndexFragment;
    }

    /**
     * 切换到商品Fragment
     */
    private void switchShopFragment() {
        //避免重复点击
        if (mCurrentFragment == mShopFragment) {
            return;
        }
        if (mShopFragment == null) {
            mShopFragment = new ShopFragment();
        }
        switchFragmentWithTag(R.id.fl_main, mCurrentFragment, mShopFragment, ShopFragment.class.getSimpleName());
        mCurrentFragment = mShopFragment;
    }

    /**
     * 切换到个人Fragment
     */
    private void switchPersonFragment() {
        //避免重复点击
        if (mCurrentFragment == mPersonFragment) {
            return;
        }
        if (mPersonFragment == null) {
            mPersonFragment = new PersonFragment();
        }
        switchFragmentWithTag(R.id.fl_main, mCurrentFragment, mPersonFragment, PersonFragment.class.getSimpleName());
        mCurrentFragment = mPersonFragment;
    }

    @Override
    public void currentFragmentBack(Fragment fragment) {

    }

    /**
     * 设置“个人”按钮的未读消息数量
     *
     * @param num 未读消息数量
     */
    private void setItemMeBadge(int num) {
        if (mItemMeBadge == null) {
            mItemMeBadge = new QBadgeView(this).bindTarget(mItemMe)
                    .setBadgeBackgroundColor(R.color.primary_light)
                    .setBadgeGravity(Gravity.END | Gravity.TOP)
                    .setGravityOffset(30, 0, true);
        }
        mItemMeBadge.setBadgeNumber(num);
    }


    @Override
    public void onFragment(Object object) {
        super.onFragment(object);
        switch ((Integer) object) {
            case ConFragment.OPEN_SHOP_FRAGMENT:
                mNavigation.setSelectedItemId(R.id.navigation_shop);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        getUnreadMessage();
    }

    /**
     * 获取未读消息数量
     */
    private void getUnreadMessage() {
        MyUnReadMessageListener.getUnReadMessage(new RongIMClient.ResultCallback<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                setItemMeBadge(integer);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        }, Conversation.ConversationType.SYSTEM, Conversation.ConversationType.PRIVATE);
    }

    /**
     * 收到IM的（聊天+系统）未读消息
     */
    @Subscribe(eventTag = RxBusTag.CHAT_SYSTEM_MESSAGE)
    public void receiverUnReadMessage(String num) {
        setItemMeBadge(Integer.parseInt(num));
    }

    /**
     * 被踢下线
     */
    @Subscribe(eventTag = RxBusTag.KICKED_OFFLINE)
    public void killOfflineByOther() {
        ConfirmDialog.newInstance(getString(R.string.string_account_login_other)).show(getSupportFragmentManager());
    }


    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) < 2000) {
            finish();
        } else {
            exitTime = System.currentTimeMillis();
            ShowToast.Short(getResources().getString(R.string.sure_exit));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyRxbus2.getInstance().unRegister(this);
        unregisterReceiver(mVideoChatBroadcastReceive);
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }


    /**
     * 接收视频聊天状态的广播接收器
     */
    public class VideoChatBroadcastReceive extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String targetId = intent.getStringExtra(BroadcastTag.PARAM_TARGET_ID);
            // 视频聊天开始
            if (TextUtils.equals(action, BroadcastTag.VIDEO_START)) {
//                ConfirmDialog.newInstance("视频通话开始").show(getSupportFragmentManager());
            }

            // 视频聊天结束
            if (TextUtils.equals(action, BroadcastTag.VIDEO_STOP)) {
                // 和大师的通话
                if (targetId.startsWith("master")) {
                    try {
                        ConfirmDialog.newInstance(getString(R.string.to_complete_order)).show(getSupportFragmentManager());
                    } catch (IllegalStateException e) {
                        ShowToast.singleLong(getString(R.string.to_complete_order));
                    }
                }
            }
        }
    }

}
