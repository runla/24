package com.yinghai.a24divine_user.module.friend.list;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.example.fansonlib.callback.LoadMoreListener;
import com.example.fansonlib.rxbus.MyRxbus2;
import com.example.fansonlib.rxbus.annotation.Subscribe;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.BaseMvpSwipeFragment;
import com.yinghai.a24divine_user.bean.FriendListBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.constant.ConstAdapter;
import com.yinghai.a24divine_user.constant.RxBusTag;
import com.yinghai.a24divine_user.module.friend.list.mvp.ContractFriendList;
import com.yinghai.a24divine_user.module.friend.list.mvp.FriendListPresenter;
import com.yinghai.a24divine_user.rongIm.ui.ConversationActivity;
import com.yinghai.a24divine_user.utils.ClearImageUtils;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/27 15:34
 *         Describe：好友列表Fragment
 */

public class FriendListFragment extends BaseMvpSwipeFragment<FriendListPresenter> implements ContractFriendList.IView, LoadMoreListener, OnAdapterListener {

    /**
     * 标识数据是否已全部加载完毕
     */
    private boolean mIsLoadComplete = false;
    private static final int PAGE_SIZE = 10;
    private FriendListAdapter mAdapter;
    private boolean mIsPull = false;
    private FrameLayout mRootView;
    private AutoLoadRecyclerView mRecyclerView;

    @Override
    protected void pullRefresh() {
        mPresenter.resetPage();
        mIsLoadComplete = false;
        mIsPull = true;
        getDataList();
    }

    @Override
    protected void initToolbarTitle() {
        setToolbarTitle(getString(R.string.friend));
    }

    @Override
    protected FriendListPresenter createPresenter() {
        return new FriendListPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_friend_list;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        initRecyclerView();
        mRootView = findMyViewId(R.id.rootView);
        return rootView;
    }

    private void initRecyclerView() {
        mRecyclerView = findMyViewId(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(hostActivity, 2));
        mRecyclerView.setOnPauseListenerParams(true, true);
        mRecyclerView.setLoadMoreListener(this);
        mAdapter = new FriendListAdapter(hostActivity);
        mAdapter.setOnAdapterListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        showLoading();
        getDataList();
        MyRxbus2.getInstance().register(this);
    }

    @Override
    public void showGetFriendSuccess(FriendListBean.DataBean bean) {
        if (mIsPull) {
            mAdapter.clearList();
            mIsPull = false;
        }
        if (bean.getFriend() != null && bean.getFriend().size() > 0) {
            for (FriendListBean.DataBean.FriendBean friendBean : bean.getFriend()) {
                getUnReadMessage(friendBean);
            }
            if (bean.getFriend().size() < PAGE_SIZE) {
                mIsLoadComplete = true;
            }
        }
        if (bean.getFriend().size() == 0 && mAdapter.getItemCount() == 0) {
            showNoDataLayout();
            mIsLoadComplete = true;
        }
        hideLoading();
        stopRefresh();
    }

    @Override
    public void showGetFriendFailure(String errorMsg) {
        hideLoading();
        ShowToast.singleLong(errorMsg);
    }

    @Override
    public void loadMore() {
        if (!mIsLoadComplete){
            getDataList();
        }
    }

    /**
     * 根据UserId获取未读消息，成功后装入适配器
     */
    private void getUnReadMessage(final FriendListBean.DataBean.FriendBean bean) {
        RongIM.getInstance().getUnreadCount(Conversation.ConversationType.PRIVATE, String.format(getString(R.string.userId_format), bean.getUserId()), new RongIMClient.ResultCallback<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                bean.setUnreadAmount(integer);
                mAdapter.appendItem(bean);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                mAdapter.appendItem(bean);
            }
        });
    }

    /**
     * 收到 IM 有新的到来消息分发,动态更新好友列表的未读消息数
     */
    @Subscribe(eventTag = RxBusTag.CHAT_PRIVATE_MESSAGE)
    public void receiverprivateMessage(String userId) {
        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            // 找到对应发送消息的人
            if (TextUtils.equals(String.format(getString(R.string.userId_format), mAdapter.getItem(i).getUserId()), userId)) {
                mAdapter.getItem(i).setUnreadAmount(mAdapter.getItem(i).getUnreadAmount() + 1);
                mAdapter.notifyItemChanged(i);
                mRecyclerView.smoothMoveToPosition(i / 2);
                break;
            }
        }
    }

    /**
     * 网络获取好友列表
     */
    public void getDataList() {
        mPresenter.getFriendList();
    }

    @Override
    public void clickItem(Object... object) {
        switch ((Integer) object[0]) {
            case ConstAdapter.CLICK_USER_PHOTO:
                mMultiFragmentListener.onMultiFragment(ConFragment.OPEN_USER_VIEW, object[1]);
                break;
            case ConstAdapter.OPEN_CHAT_VIEW:
                ConversationActivity.startPrivateActivity(hostActivity, (String) object[1], (String) object[2]);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MyRxbus2.getInstance().unRegister(this);
        ClearImageUtils.recycleBackground(mRootView);
        mAdapter.removeListener();
    }
}
