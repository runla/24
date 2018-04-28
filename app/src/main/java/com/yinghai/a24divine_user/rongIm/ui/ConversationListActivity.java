package com.yinghai.a24divine_user.rongIm.ui;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseActivity;
import com.yinghai.a24divine_user.rongIm.IMLogin;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * Created by chenjianrun on 2017/11/8.
 * 描述：显示聚合会话列表界面
 */

public class ConversationListActivity extends MyBaseActivity {

    private TextView mTvTitle;
    private ImageView mBtnBack;
    /**
     * 启动会话列表界面
     * @param activity
     */
    public static void startActivity(Activity activity){
        Map<String, Boolean> supportedConversation = new HashMap<>();
        supportedConversation.put(Conversation.ConversationType.PRIVATE.getName(), true);
        supportedConversation.put(Conversation.ConversationType.GROUP.getName(), true);
        supportedConversation.put(Conversation.ConversationType.CHATROOM.getName(), true);
        supportedConversation.put(Conversation.ConversationType.DISCUSSION.getName(), true);
        RongIM.getInstance().startConversationList(activity,supportedConversation);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_conversation_list;
    }

    @Override
    protected void initView() {
        super.initView();
        mTvTitle = findMyViewId(R.id.tv_title_toolbar);
        mBtnBack = findMyViewId(R.id.btn_back_toolbar);
    }

    @Override
    protected void initData() {
        String userId = getIntent().getData().getQueryParameter("targetId");
        String title = getIntent().getData().getQueryParameter("title");
        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }else{
            mTvTitle.setText(userId);
        }

    }

    @Override
    protected void listenEvent() {
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConversationListActivity.this.finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        IMLogin.imCheckLogin(null);
    }
}
