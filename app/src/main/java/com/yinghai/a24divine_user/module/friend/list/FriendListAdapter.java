package com.yinghai.a24divine_user.module.friend.list;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fansonlib.base.BaseDataAdapter;
import com.example.fansonlib.base.BaseHolder;
import com.example.fansonlib.image.ImageLoaderUtils;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.FriendListBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConstAdapter;

import static com.example.fansonlib.base.AppUtils.getString;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/27 15:37
 *         Describe：好友列表的适配器
 */

public class FriendListAdapter extends BaseDataAdapter<FriendListBean.DataBean.FriendBean> {

    private OnAdapterListener mOnAdapterListener;

    public void setOnAdapterListener(OnAdapterListener listener) {
        mOnAdapterListener = listener;
    }

    public void removeListener(){
        mOnAdapterListener = null;
    }

    public FriendListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutRes(int position) {
        return R.layout.item_friend_list;
    }

    @Override
    public void bindCustomViewHolder(BaseHolder holder, final int position) {
        final TextView mTvUnRead = holder.getView(R.id.tv_unread);
        if (getItem(position).getUnreadAmount() > 0) {
            mTvUnRead.setVisibility(View.VISIBLE);
            mTvUnRead.setText(String.valueOf(getItem(position).getUnreadAmount() ));
        }else {
            mTvUnRead.setVisibility(View.GONE);
        }

        holder.setText(R.id.tv_user_name, getItem(position).getUNick());
        ImageLoaderUtils.loadCircleImage(context, (ImageView) holder.getView(R.id.iv_user_photo), ConHttp.BASE_URL + getItem(position).getUImgUrl());

        holder.setOnClickListener(R.id.rootView, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAdapterListener.clickItem(ConstAdapter.CLICK_USER_PHOTO, getItem(position).getUserId());
            }
        });

        holder.setOnClickListener(R.id.btn_chat, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = String.format(getString(R.string.userId_format), getItem(position).getUserId());
                mOnAdapterListener.clickItem(ConstAdapter.OPEN_CHAT_VIEW, userId, getItem(position).getUNick());
                mTvUnRead.setVisibility(View.GONE);
            }
        });
    }

    public void updateMyItem(String userId){
        for (int i = 0; i < getItemCount(); i++) {
            // 找到对应发送消息的人
            if (TextUtils.equals(String.format(getString(R.string.userId_format),getItem(i).getUserId()),userId)) {
                getItem(i).setUnreadAmount(getItem(i).getUnreadAmount()+1);
                notifyItemChanged(i);
                break;
            }
        }
    }

}
