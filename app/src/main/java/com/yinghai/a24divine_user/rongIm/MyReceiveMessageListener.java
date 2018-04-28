package com.yinghai.a24divine_user.rongIm;

import com.example.fansonlib.rxbus.MyRxbus2;
import com.yinghai.a24divine_user.constant.RxBusTag;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.message.FileMessage;
import io.rong.message.GroupNotificationMessage;
import io.rong.message.ImageMessage;
import io.rong.message.LocationMessage;
import io.rong.message.RichContentMessage;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;

/**
 * Created by chenjianrun on 2017/12/4.
 * 描述：IM 消息接收类
 */

public class MyReceiveMessageListener implements RongIMClient.OnReceiveMessageListener{
    @Override
    public boolean onReceived(Message message, int i) {
        MessageContent messageContent = message.getContent();
        // 系统消息
        /*if (message.getConversationType().equals(Conversation.ConversationType.SYSTEM)) {
            handleSystemMessage(messageContent);
        }*/

        // 接收单聊消息，并分发
        if (message.getConversationType().equals(Conversation.ConversationType.PRIVATE)) {
            if (message.getSenderUserId().startsWith("master") || message.getTargetId().startsWith("master")){
                // 更新内存中消息的已读状态
                message.getReceivedStatus().setRead();
                // 更新数据库中消息的状态
                RongIM.getInstance().setMessageReceivedStatus(message.getMessageId(), message.getReceivedStatus(),null);
                return false;
            }
            MyRxbus2.getInstance().send(RxBusTag.CHAT_PRIVATE_MESSAGE,String.valueOf(message.getSenderUserId()));
        }

        return false;
    }


    /**
     * 处理单聊消息
     */
    private void handlePrivateMessage(MessageContent messageContent){
        MyRxbus2.getInstance().send(RxBusTag.CHAT_MESSAGE);

        // 其他会话消息
        if (messageContent instanceof TextMessage) {
            //文本消息
            TextMessage textMessage = (TextMessage) messageContent;

        } else if (messageContent instanceof ImageMessage) {
            //图片消息
            ImageMessage imageMessage = (ImageMessage) messageContent;

        } else if (messageContent instanceof VoiceMessage) {
            //语音消息
            VoiceMessage voiceMessage = (VoiceMessage) messageContent;

        } else if (messageContent instanceof RichContentMessage) {
            //图文消息
            RichContentMessage richContentMessage = (RichContentMessage) messageContent;

        } else if (messageContent instanceof LocationMessage){
            //位置消息
            LocationMessage locationMessage = (LocationMessage) messageContent;

        }else if (messageContent instanceof FileMessage){
            //文件消息
            FileMessage fileMessage = (FileMessage) messageContent;

        }else if (messageContent instanceof GroupNotificationMessage){
            //群消息
            GroupNotificationMessage groupNotificationMessage = (GroupNotificationMessage) messageContent;

        }else{

        }
    }
    /**
     * 处理系统消息
     */
    private void handleSystemMessage(MessageContent messageContent){
//        if (messageContent instanceof ContactNotificationMessage) {
//            //文本消息
//            String textContent = ((ContactNotificationMessage) messageContent).getOperation();
//            SystemMessageBean systemMessageBean = new Gson().fromJson(textContent,SystemMessageBean.class);
//            MyRxbus2.getInstance().send(RxBusTag.SYSTEM_MESSAGE);
//
//
//        }
        if (messageContent instanceof TextMessage) {
            //文本消息
            MyRxbus2.getInstance().send(RxBusTag.SYSTEM_MESSAGE);
        }

    }
}
