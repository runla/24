package com.yinghai.a24divine_user.module.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.fansonlib.rxbus.MyRxbus2;
import com.example.fansonlib.rxbus.annotation.Subscribe;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseFragment;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.constant.RxBusTag;
import com.yinghai.a24divine_user.databinding.FragmentPersonCenterBinding;
import com.yinghai.a24divine_user.module.collect.MyCollectionActivity;
import com.yinghai.a24divine_user.module.follow.MyFollowActivity;
import com.yinghai.a24divine_user.module.friend.FriendActivity;
import com.yinghai.a24divine_user.module.history.HistoryActivity;
import com.yinghai.a24divine_user.module.login.LoginActivity;
import com.yinghai.a24divine_user.module.login.state.LoginStateManager;
import com.yinghai.a24divine_user.module.message.MessageActivity;
import com.yinghai.a24divine_user.module.order.MyOrderActivity;
import com.yinghai.a24divine_user.module.setting.SettingActivity;
import com.yinghai.a24divine_user.rongIm.MyUnReadMessageListener;
import com.yinghai.a24divine_user.utils.ReboundUtils;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * @author Created by：fanson
 *         Created on：2017/10/24 13:29
 *         Describe：个人中心Fragment
 */

public class PersonFragment extends MyBaseFragment {

    private static final int REQUEST_SETTING = 110;
    private FragmentPersonCenterBinding mBinding;
    private Badge mFriendBadge, mMessageBadge, mOrderBadge;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_person_center;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_person_center,container,false);
        super.onCreateView(inflater, container, savedInstanceState);
        return mBinding.getRoot();
    }

    private void addAnimation() {
        ReboundUtils reboundUtils = new ReboundUtils();
        reboundUtils.animateViewChain(mBinding.llNote, getResources().getDisplayMetrics().widthPixels, 0);
    }

    @Override
    protected void initData() {
        MyRxbus2.getInstance().register(this);
        mBinding.tvUserName.setText(SharePreferenceHelper.getString(ConstantPreference.S_USER_NAME, getString(R.string.please_login)));
        showUserPhoto(SharePreferenceHelper.getString(ConstantPreference.S_USER_PHOTO, null));
        getUnReadMessage();
    }

    /**
     * 显示用户头像
     *
     * @param imgUrl
     */
    private void showUserPhoto(String imgUrl) {
        if (imgUrl != null) {
            Glide.with(hostActivity).load(ConHttp.BASE_URL + imgUrl).dontAnimate().placeholder(R.mipmap.ic_person).error(R.mipmap.ic_person)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(mBinding.ivUserPhoto);
        }
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mBinding.tvUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBinding.tvUserName.getText().toString().equals(getString(R.string.please_login))) {
                    startMyActivity(LoginActivity.class);
                }
            }
        });

        mBinding.noteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginStateManager.getInstance().onMe(hostActivity)) {
                    startMyActivity(MyOrderActivity.class);
                    hostActivity.overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
                }
            }
        });

        mBinding.noteFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginStateManager.getInstance().onMe(hostActivity)) {
                    startMyActivity(MyFollowActivity.class);
                    hostActivity.overridePendingTransition(R.anim.scale_in_from_right_bottom, R.anim.scale_out);
                }
            }
        });

        mBinding.noteFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginStateManager.getInstance().onMe(hostActivity)) {
                    FriendActivity.startActivityToDetail(hostActivity, FriendActivity.TYPE_LIST, 0, false);
                    hostActivity.overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
                }
            }
        });

        mBinding.noteSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginStateManager.getInstance().onMe(hostActivity)) {
                    startActivityForResult(new Intent(hostActivity, SettingActivity.class), REQUEST_SETTING);
                }
            }
        });

        mBinding.noteCollction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginStateManager.getInstance().onMe(hostActivity)) {
                    startMyActivity(MyCollectionActivity.class);
                    hostActivity.overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
                }
            }
        });

        mBinding.noteHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginStateManager.getInstance().onMe(hostActivity)) {
                    startMyActivity(HistoryActivity.class);
                    hostActivity.overridePendingTransition(R.anim.scale_in_from_left_bottom, R.anim.scale_out_to_left_bottom);
                }
            }
        });

        mBinding.ivMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginStateManager.getInstance().onMe(hostActivity)) {
                    startMyActivity(MessageActivity.class);
                    hostActivity.overridePendingTransition(R.anim.scale_in_from_left_top,R.anim.scale_in_from_left_top);
                }
            }
        });

        mBinding.ivUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginStateManager.getInstance().onMe(hostActivity)) {
                    startActivityForResult(new Intent(hostActivity, SettingActivity.class), REQUEST_SETTING);
                }
            }
        });
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        return null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case SettingActivity.RESULT_PHOTO:
                showUserPhoto(data.getStringExtra(SettingActivity.PHOTO_KEY));
                break;
            case SettingActivity.RESULT_INFO:
                mBinding.tvUserName.setText(data.getStringExtra(SettingActivity.INFO_KEY));
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        addAnimation();
        getUnReadMessage();
    }

    @Override
    protected void initToolbarTitle() {

    }

    /**
     * 设置“我的好友”的未读消息数量
     *
     * @param num 未读消息数量
     */
    public void setFriendBadge(int num) {
        if (mFriendBadge == null) {
            mFriendBadge = new QBadgeView(hostActivity).bindTarget(mBinding.noteFriend).setBadgeNumber(num)
                    .setBadgeBackgroundColor(R.color.primary_light)
                    .setBadgeGravity(Gravity.CENTER | Gravity.BOTTOM)
                    .setGravityOffset(0, 50, true);
        }
        mFriendBadge.setBadgeNumber(num);
    }

    /**
     * 设置“我的订单”的未读消息数量
     *
     * @param num 未读消息数量
     */
    public void setOrderBadge(int num) {
        if (mOrderBadge == null) {
            mOrderBadge = new QBadgeView(hostActivity).bindTarget(mBinding.noteOrder).setBadgeNumber(num)
                    .setBadgeBackgroundColor(R.color.primary_light)
                    .setBadgeGravity(Gravity.CENTER | Gravity.BOTTOM);
        }
        mOrderBadge.setBadgeNumber(num);
    }

    /**
     * 设置“系统消息”的未读消息数量
     *
     * @param num 未读消息数量
     */
    public void setMessageBadge(int num) {
        if (mMessageBadge == null) {
            mMessageBadge = new QBadgeView(hostActivity).bindTarget(mBinding.ivMessage).setBadgeNumber(num)
                    .setBadgeBackgroundColor(R.color.primary_light)
                    .setBadgeGravity(Gravity.END | Gravity.TOP)
                    .setGravityOffset(38, 0, true);
        }
        mMessageBadge.setBadgeNumber(num);
    }

    /**
     * 监听获取聊天+系统消息未读
     */
    private void getUnReadMessage() {
        MyUnReadMessageListener.getUnReadMessage(new RongIMClient.ResultCallback<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                setMessageBadge(integer);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        }, Conversation.ConversationType.SYSTEM);

        MyUnReadMessageListener.getUnReadMessage(new RongIMClient.ResultCallback<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                setFriendBadge(integer);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        }, Conversation.ConversationType.PRIVATE);
    }

    /**
     * 收到IM的系统消息，更新个人页面的消息数量
     */
    @Subscribe(eventTag = RxBusTag.SYSTEM_MESSAGE)
    public void receiverSystemMessage(String num) {
        setMessageBadge(Integer.parseInt(num));
    }


    /**
     * 收到IM的聊天消息
     */
    @Subscribe(eventTag = RxBusTag.CHAT_MESSAGE)
    public void receiverChatMessage(String num) {
        setFriendBadge(Integer.parseInt(num));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MyRxbus2.getInstance().unRegister(this);
    }
}
