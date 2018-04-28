package com.yinghai.a24divine_user.module.artical.details;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fansonlib.base.BaseDataAdapter;
import com.example.fansonlib.base.BaseHolder;
import com.example.fansonlib.image.ImageLoaderUtils;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.CommentBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConstAdapter;
import com.yinghai.a24divine_user.databinding.ItemArticalCommentBinding;

/**
 * @author  Created by：fanson
 * Created on：2017/10/17 13:19
 * Describe：文章详情界面的评论适配器
 */

public class CommentAdapter extends BaseDataAdapter<CommentBean.DataBean.CommentsBean> {

    private ItemArticalCommentBinding mBinding;
    private OnAdapterListener mOnAdapterListener;

    public void setOnAdapterListener(OnAdapterListener listener) {
        mOnAdapterListener = listener;
    }

    public CommentAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutRes(int i) {
        return 0;
    }

    @Override
    public BaseHolder bindHolder(ViewGroup parent, int viewType) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.item_artical_comment,parent,false);
        return new BaseHolder(mBinding.getRoot());
    }

    @Override
    public void bindCustomViewHolder(BaseHolder holder, final int position) {
        mBinding = DataBindingUtil.getBinding(holder.itemView);
        mBinding.setBean(getItem(position));
        mBinding.executePendingBindings();

        ImageLoaderUtils.loadCircleImage(context, mBinding.ivUserPhoto, ConHttp.BASE_URL + getItem(position).getImg());

        mBinding.ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAdapterListener.clickItem(ConstAdapter.CLICK_COMMENT_ITEM, getItem(position), view);
            }
        });

        mBinding.ivUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAdapterListener.clickItem(ConstAdapter.CLICK_USER_PHOTO, getItem(position).getUserId());
            }
        });
    }

    /**
     * 根据判断commentid，进行删除
     *
     * @param data
     */
    @Override
    public void removeItem(CommentBean.DataBean.CommentsBean data) {
        for (int i = 0; i < mDataList.size(); i++) {
            if (mDataList.get(i).equals(data)) {
                mDataList.remove(i);
                this.notifyItemRemoved(i);
                this.notifyItemRangeChanged(i, this.mDataList.size() - i);
                return;
            }
        }
    }
}
