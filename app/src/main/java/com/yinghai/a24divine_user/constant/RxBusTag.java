package com.yinghai.a24divine_user.constant;

/**
 * @author Created by：fanson
 *         Created Time: 2017/12/1 15:04
 *         Describe：Rxbus的标识符
 */

public class RxBusTag {

    /**
     * 收到IM的系统消息，更新个人页面的消息数量
     */
    public static final int SYSTEM_MESSAGE = 900;

    /**
     * 收到IM的聊天消息，更新个人页面的消息数量
     */
    public static final int CHAT_MESSAGE = 901;

    /**
     * 收到IM的（聊天 + 系统）消息，更新个人页面的消息数量
     */
    public static final int CHAT_SYSTEM_MESSAGE = 902;


    /**
     * 收到 IM 单聊消息，并分发发送者的 userId
     */
    public static final int CHAT_PRIVATE_MESSAGE = 903;

    /**
     * 被踢下线的通知
     */
    public static final int KICKED_OFFLINE = 904;

    /**
     * 和大师的视频通话开始
     */
    public static final int VIDEO_START = 905;

    /**
     * 和大师的视频通话结束
     */
    public static final int VIDEO_STOP = 906;
}
