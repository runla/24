package com.yinghai.a24divine_user.rongIm.ui;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseActivity;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * Created by chenjianrun on 2017/11/8.
 * 描述：会话列表界面（如：私人聊天列表、聊天室列表等界面）
 */

public class SubConversationListActivtiy extends MyBaseActivity {

    private TextView mTvTitle;
    private ImageView mBtnBack;

    /**
     * 启动私人会话的列表
     * @param activity
     */
    public static void startPrivateList(Context activity){
        RongIM.getInstance().startSubConversationList(activity, Conversation.ConversationType.PRIVATE);
    }

    /**
     * 启动聊天室会话的列表
     * @param activity
     */
    public static void startChatRoomList(Context activity){
        RongIM.getInstance().startSubConversationList(activity, Conversation.ConversationType.CHATROOM);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_subconversation_list;
    }

    @Override
    protected void initView() {
        super.initView();
        mTvTitle = findMyViewId(R.id.tv_title_toolbar);
        mBtnBack = findMyViewId(R.id.btn_back_toolbar);
    }

    @Override
    protected void initData() {
        mTvTitle.setText(R.string.chat_record);
    }

    @Override
    protected void listenEvent() {
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubConversationListActivtiy.this.finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
