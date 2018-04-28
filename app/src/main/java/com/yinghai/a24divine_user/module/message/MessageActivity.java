package com.yinghai.a24divine_user.module.message;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.fansonlib.callback.LoadMoreListener;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseActivity;
import com.yinghai.a24divine_user.bean.SystemMessageBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConSystemCode;
import com.yinghai.a24divine_user.constant.ConstAdapter;
import com.yinghai.a24divine_user.module.friend.FriendActivity;
import com.yinghai.a24divine_user.utils.ClearImageUtils;
import com.yinghai.a24divine_user.utils.MyStatusBarUtil;

import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/30 17:45
 *         Describe：系统消息的Activity
 */

public class MessageActivity extends MyBaseActivity implements LoadMoreListener, OnAdapterListener, SwipeRefreshLayout.OnRefreshListener {

    private boolean mIsPull = false; //标识是否上拉刷新
    private AutoLoadRecyclerView mRecyclerView;
    private MessageAdapter mAdapter;
    private ImageView mBtnBack;
    public static final String SENDER_ID = "admin";
    private final int MESSAGE_COUNT = 10;   // 获取历史记录的数量
    private HistoryCallback mHistoryCallback;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private FrameLayout mRootView;

    @Override
    protected int getContentView() {
        return R.layout.activity_message;
    }

    @Override
    protected void initView() {
        super.initView();
        initRecyclerView();
        mRootView = findMyViewId(R.id.rootView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mBtnBack = findMyViewId(R.id.btn_back_toolbar);
    }

    private void initRecyclerView() {
        mRecyclerView = findMyViewId(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setOnPauseListenerParams(true, true);
        mRecyclerView.setLoadMoreListener(this);
        mAdapter = new MessageAdapter(this);
        mAdapter.setOnAdapterListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        setToolbarTitle(getString(R.string.system_message));
        MyStatusBarUtil.setDrawableStatus(this, R.drawable.shape_toolbar_bg);
        mHistoryCallback = new HistoryCallback();
        RongIM.getInstance().getLatestMessages(Conversation.ConversationType.SYSTEM, SENDER_ID, MESSAGE_COUNT, mHistoryCallback);
        //获取IM消息
    }

    @Override
    protected void listenEvent() {
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageActivity.this.finish();
                overridePendingTransition(R.anim.scale_out_to_left_top, R.anim.scale_out_to_left_top);
            }
        });
    }

    @Override
    public void onMultiFragment(Object... object) {
        super.onMultiFragment(object);
        switch ((Integer) object[0]) {
            case ConstAdapter.BEEN_READ_MESSAGE:
                //TODO 将消息设置为已读
                break;
            default:
                break;
        }
    }

    @Override
    public void loadMore() {
        int oldestMessageId = mAdapter.getItem(mAdapter.getItemCount() - 1).getMessageId();
        RongIM.getInstance().getHistoryMessages(Conversation.ConversationType.SYSTEM, SENDER_ID, oldestMessageId, MESSAGE_COUNT, mHistoryCallback);

    }

    @Override
    public void clickItem(Object... object) {
        SystemMessageBean systemMessageBean = (SystemMessageBean) object[0];
        switch (Integer.parseInt(systemMessageBean.getCode())) {
            case ConSystemCode.ORDER_PAY_SUCCESS:
            case ConSystemCode.ORDER_CANCEL_SUCCESS:
            case ConSystemCode.ORDER_HAS_CANCEL:
            case ConSystemCode.ORDER_START_SOON:
                break;

            case ConSystemCode.ADD_FIREND:
                FriendActivity.startActivityToDetail(this, FriendActivity.TYPE_DETAIL, Integer.parseInt(systemMessageBean.getData().getUser().getUserId()), true);
                break;

            case ConSystemCode.AGREE_FIREND:
            case ConSystemCode.REJECT_FIREND:
                FriendActivity.startActivityToDetail(this, FriendActivity.TYPE_DETAIL, Integer.parseInt(systemMessageBean.getData().getUser().getUserId()), false);
                break;

            default:
                break;
        }
    }

    /**
     * 停止下拉刷新的动画
     */
    public void stopRefresh() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        mIsPull = true;
        RongIM.getInstance().getLatestMessages(Conversation.ConversationType.SYSTEM, SENDER_ID, MESSAGE_COUNT, mHistoryCallback);
    }

    /**
     * IM 历史记录结果回调
     */
    private class HistoryCallback extends ResultCallback<List<Message>> {
        @Override
        public void onSuccess(List<Message> messages) {
            if (mIsPull) {
                mAdapter.clearList();
                mIsPull = false;
            }
            if (messages.size() == 0 && mAdapter.getItemCount() == 0) {
                showNoDataLayout();
            }

            mAdapter.appendList(messages);
            mRecyclerView.loadFinish(null);
            stopRefresh();
        }

        @Override
        public void onError(RongIMClient.ErrorCode errorCode) {
            ShowToast.singleLong(errorCode.getMessage());
            mRecyclerView.loadFinish(null);
            stopRefresh();
        }
    }

    /**
     * 显示无数据界面
     */
    public void showNoDataLayout() {
        ViewStub viewStub = (ViewStub) findViewById(R.id.vs_no_data);
        if (viewStub != null) {
            viewStub.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            overridePendingTransition(R.anim.scale_in_from_left_bottom, R.anim.scale_out_to_left_top);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ClearImageUtils.recycleBackground(mRootView);
    }
}
