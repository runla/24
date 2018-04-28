package com.yinghai.a24divine_user.rongIm.ui;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseActivity;
import com.yinghai.a24divine_user.rongIm.IMLogin;
import com.yinghai.a24divine_user.utils.MyStatusBarUtil;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * Created by chenjianrun on 2017/11/8.
 * 描述：会话界面
 */

public class ConversationActivity extends MyBaseActivity {

    private TextView mTvTitle;
    private ImageView mBtnBack;

    /**
     * 启动会话界面
     * @param activity
     * @param conversationType
     * @param userId
     * @param title
     */
    public static void startActivity(Activity activity, Conversation.ConversationType conversationType, String userId, String title){
        RongIM.getInstance().startConversation(activity,conversationType, userId, title);
    }

    /**
     * 启动私聊会话界面
     * @param activity
     * @param userId
     * @param title
     */
    public static void startPrivateActivity(Activity activity, String userId, String title){
        RongIM.getInstance().startPrivateChat(activity, userId, title);
    }

    /**
     * 启动聊天室会话界面
     * @param activity
     * @param roomId
     */
    public static void startChatRoom(Activity activity, String roomId){
        RongIM.getInstance().startChatRoomChat(activity,roomId,true);
    }


    @Override
    protected int getContentView() {
        return R.layout.activity_conversation;
    }

    @Override
    protected void initView() {
        super.initView();
        mTvTitle = findMyViewId(R.id.tv_title_toolbar);
        mBtnBack = findMyViewId(R.id.btn_back_toolbar);
    }

    @Override
    protected void initData() {
        MyStatusBarUtil.setDrawableStatus(this, R.drawable.shape_toolbar_bg);
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
                ConversationActivity.this.finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        IMLogin.imCheckLogin(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
