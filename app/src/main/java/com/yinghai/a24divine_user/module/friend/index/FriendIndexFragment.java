package com.yinghai.a24divine_user.module.friend.index;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fansonlib.image.ImageLoaderUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.dialogfragment.ConfirmDialog;
import com.example.fansonlib.widget.dialogfragment.DoubleDialog;
import com.example.fansonlib.widget.dialogfragment.base.IConfirmListener;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseMvpFragment;
import com.yinghai.a24divine_user.bean.PersonInfoBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConstantPreference;
import com.yinghai.a24divine_user.module.friend.index.mvp.ContractFriend;
import com.yinghai.a24divine_user.module.friend.index.mvp.FriendManagerPresenter;
import com.yinghai.a24divine_user.rongIm.ui.ConversationActivity;
import com.yinghai.a24divine_user.utils.ConstellationUtils;
import com.yinghai.a24divine_user.utils.LogUtils;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/27 14:39
 *         Describe：好友主页Fragment
 */

public class FriendIndexFragment extends MyBaseMvpFragment<FriendManagerPresenter> implements ContractFriend.IView {

    private static final String TAG = FriendIndexFragment.class.getSimpleName();
    private TextView mTvName, mTvConstellation;
    private ImageView mIvUserPhoto;
    private Button mBtnChat, mBtnDel;
    private static String FRIEND_ID = "friendId";
    private static String IS_REQUEST = "isRequest";
    private PersonInfoBean.DataBean mFirendBean;
    private boolean mIsFriend = false;

    @Override
    protected void initToolbarTitle() {
        setToolbarTitle(getString(R.string.user_index));
    }


    /**
     * @param friendId id
     * @return
     */
    public static FriendIndexFragment newInstance(int friendId, boolean isRequest) {
        Bundle bundle = new Bundle();
        bundle.putInt(FRIEND_ID, friendId);
        bundle.putBoolean(IS_REQUEST, isRequest);
        FriendIndexFragment indexFragment = new FriendIndexFragment();
        indexFragment.setArguments(bundle);
        return indexFragment;
    }

    @Override
    protected FriendManagerPresenter createPresenter() {
        return new FriendManagerPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_friend_index;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        mIvUserPhoto = findMyViewId(R.id.iv_user_photo);
        mTvName = findMyViewId(R.id.tv_name);
        mBtnChat = findMyViewId(R.id.btn_chat);
        mBtnDel = findMyViewId(R.id.btn_del);
        mTvConstellation = findMyViewId(R.id.tv_constellation);
        showButton();
        return rootView;
    }

    /**
     * 根据数据判断该显示什么button（聊天，添加，删除，同意，拒接）
     */
    private void showButton() {
        //若是好友关系
        if (mIsFriend) {
            mBtnChat.setText(getString(R.string.chat));
            mBtnDel.setText(getString(R.string.delete));
        }
        //收到好友验证
        if (getArguments().getBoolean(IS_REQUEST)) {
            mBtnChat.setText(getString(R.string.agree));
            mBtnDel.setText(getString(R.string.reject));
        }
        //若点击的头像是自己，则不显示按钮
        if (SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, 0) == getArguments().getInt(FRIEND_ID)) {
            mBtnChat.setVisibility(View.GONE);
            mBtnDel.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {
        //判断若点击的是自己，则加载本地数据
        if (SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, 0) == getArguments().getInt(FRIEND_ID)) {
            mTvName.setText(SharePreferenceHelper.getString(ConstantPreference.S_USER_NAME, null));
            showConstellation(SharePreferenceHelper.getInt(ConstantPreference.I_USER_CONSTELLATION, -1));
            ImageLoaderUtils.loadCircleImage(hostActivity,mIvUserPhoto,ConHttp.BASE_URL + SharePreferenceHelper.getString(ConstantPreference.S_USER_PHOTO, ""));
        } else {
            showLoading();
            mPresenter.getFriendInfo(getArguments().getInt(FRIEND_ID));
        }
    }

    private void showConstellation(int constellationId) {
        if (constellationId > 0) {
            mTvConstellation.setText(ConstellationUtils.getString(constellationId));
        } else {
            mTvConstellation.setText(getString(R.string.not_set));
        }
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mBtnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBtnChat.getText().toString().equals(getString(R.string.agree))) {
                    showLoading();
                    mPresenter.friendRequest(getArguments().getInt(FRIEND_ID), 1);
                } else {
                    if (mIsFriend) {
                        ConversationActivity.startPrivateActivity(hostActivity, String.format(getString(R.string.userId_format), getArguments().getInt(FRIEND_ID)), mFirendBean.getTfUser().getUNick());
                    } else {
                        ConfirmDialog.newInstance(getString(R.string.please_add_friend)).show(getFragmentManager());
                    }
                }
            }
        });

        mBtnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBtnDel.getText().toString().equals(getString(R.string.add))) {
                    showLoading();
                    mPresenter.addFriend(getArguments().getInt(FRIEND_ID));
                } else if (mBtnDel.getText().toString().equals(getString(R.string.reject))) {
                    showLoading();
                    mPresenter.friendRequest(getArguments().getInt(FRIEND_ID), 2);
                } else {
                    DoubleDialog.newInstance(getString(R.string.sure_delete)).setConfirmListener(new IConfirmListener() {
                        @Override
                        public void onConfirm() {
                            showLoading();
                            mPresenter.delFriend(getArguments().getInt(FRIEND_ID));
                        }
                    }).show(getFragmentManager());
                }
            }
        });
    }

    @Override
    public void showAddFriendSuccess() {
        ShowToast.singleShort(getString(R.string.request_sent_success));
        hideLoading();
    }

    @Override
    public void showAddFriendFailure(String errorMsg) {
        ShowToast.singleShort(errorMsg);
        hideLoading();
    }

    @Override
    public void showDelFriendSuccess() {
        ShowToast.singleShort(getString(R.string.delete_success));
        mBtnDel.setText(getString(R.string.add));
        mIsFriend = false;
        hideLoading();
    }

    @Override
    public void showDelFriendFailure(String errorMsg) {
        ShowToast.singleShort(errorMsg);
        hideLoading();
    }

    @Override
    public void showFriendRequestSuccess() {
        ShowToast.singleShort(getString(R.string.successful));
        hideLoading();
    }

    @Override
    public void showFriendRequestSFailure(String errorMsg) {
        ShowToast.singleShort(errorMsg);
        hideLoading();
    }

    @Override
    public void showFriendInfoSuccess(PersonInfoBean.DataBean bean) {
        if (!bean.getTfUser().getUImgUrl().isEmpty()) {
            ImageLoaderUtils.loadCircleImage(hostActivity,mIvUserPhoto,ConHttp.BASE_URL + bean.getTfUser().getUImgUrl());
        }
        mTvName.setText(bean.getTfUser().getUNick());
        showConstellation(bean.getTfUser().getUConstellation());
        mFirendBean = bean;
        if (bean.getTfUser().isuIsFriend()) {
            mBtnChat.setText(getString(R.string.chat));
            mBtnDel.setText(getString(R.string.delete));
            mIsFriend = true;
        } else {
            mBtnChat.setText(getString(R.string.chat));
            mBtnDel.setText(getString(R.string.add));
            mIsFriend = false;
        }
        if (getArguments().getBoolean(IS_REQUEST)) {
            mBtnChat.setText(getString(R.string.agree));
            mBtnDel.setText(getString(R.string.reject));
        }
        hideLoading();
    }

    @Override
    public void showFriendInfoFailure(String errorMsg) {
        ShowToast.singleShort(errorMsg);
        hideLoading();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d(TAG, "onDestroyView");
    }
}
