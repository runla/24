package com.yinghai.a24divine_user.rongIm;

import com.example.fansonlib.base.AppUtils;
import com.example.fansonlib.rxbus.MyRxbus2;
import com.example.fansonlib.utils.notification.MyNotificationUtils;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.constant.NotificationID;
import com.yinghai.a24divine_user.constant.RxBusTag;
import com.yinghai.a24divine_user.module.friend.FriendActivity;
import com.yinghai.a24divine_user.module.message.MessageActivity;

import io.rong.imkit.RongIM;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * Created by chenjianrun on 2017/12/6.
 * 描述：用于监听系统未读消息和单聊未读消息的监听类
 */

public class MyUnReadMessageListener {

    public static void register() {
        systemUnReadMessage();
        privateUnReadMessage();
        unReadMessage();
    }

    public static void getUnReadMessage(RongIMClient.ResultCallback<Integer> callback, Conversation.ConversationType... conversationType) {
        RongIM.getInstance().getUnreadCount(callback, conversationType);
    }

    /**
     * 系统的未读消息
     */
    private static void systemUnReadMessage() {
        RongIM.getInstance().addUnReadMessageCountChangedObserver(new IUnReadMessageObserver() {
            @Override
            public void onCountChanged(int i) {
                if (i > 0) {
                    MyRxbus2.getInstance().send(RxBusTag.SYSTEM_MESSAGE, String.valueOf(i));
                    MyNotificationUtils.buildSimple(NotificationID.SYSTEM_MESSAGE,R.mipmap.app_logo,AppUtils.getString(R.string.app_name),
                            AppUtils.getString(R.string.new_system_message),MyNotificationUtils.buildIntent(MessageActivity.class)).show();
                }
            }
        }, Conversation.ConversationType.SYSTEM);
    }


    /**
     * 单聊的未读消息数量
     */
    private static void privateUnReadMessage() {
        RongIM.getInstance().addUnReadMessageCountChangedObserver(new IUnReadMessageObserver() {
            @Override
            public void onCountChanged(int i) {
                if (i > 0) {
                    MyRxbus2.getInstance().send(RxBusTag.CHAT_MESSAGE, String.valueOf(i));
                    MyNotificationUtils.buildSimple(NotificationID.CHAT_MESSAGE, R.mipmap.app_logo, AppUtils.getString(R.string.app_name),
                            AppUtils.getString(R.string.new_chat_message), MyNotificationUtils.buildIntent(FriendActivity.class)).show();
                }

            }
        }, Conversation.ConversationType.PRIVATE);
    }

    /**
     * （单聊+系统）未读消息数量
     */
    private static void unReadMessage() {
        RongIM.getInstance().addUnReadMessageCountChangedObserver(new IUnReadMessageObserver() {
            @Override
            public void onCountChanged(int i) {
                MyRxbus2.getInstance().send(RxBusTag.CHAT_SYSTEM_MESSAGE, String.valueOf(i));
            }
        }, Conversation.ConversationType.PRIVATE, Conversation.ConversationType.SYSTEM);
    }
}
