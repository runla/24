package com.yinghai.a24divine_user.module.follow;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fansonlib.base.BaseDataAdapter;
import com.example.fansonlib.base.BaseHolder;
import com.example.fansonlib.image.ImageLoaderUtils;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.FollowBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConstAdapter;
import com.yinghai.a24divine_user.databinding.ItemFollowBinding;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/25 16:22
 *         Describe：我关注的适配器
 */

public class FollowAdapter extends BaseDataAdapter<FollowBean.DataBean.SubscribesBean> {

    private FollowAdapterCallback mCallback;
    private ItemFollowBinding mBinding;
    private OnAdapterListener mOnAdapterListener;

    public void setOnAdapterListener(OnAdapterListener listener) {
        mOnAdapterListener = listener;
    }

    public FollowAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutRes(int i) {
        return 0;
    }

    @Override
    public BaseHolder bindHolder(ViewGroup parent, int viewType) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.item_follow,parent,false);
        return new BaseHolder(mBinding.getRoot());
    }

    @Override
    public void bindCustomViewHolder(BaseHolder holder, final int position) {
        mBinding = DataBindingUtil.getBinding(holder.itemView);
        mBinding.setBean(getItem(position));
        mBinding.executePendingBindings();

        ImageLoaderUtils.loadCircleImage(context, mBinding.ivAugurPhoto, ConHttp.BASE_URL + getItem(position).getImg());

        mBinding.btnCancelFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCallback != null) {
                    mCallback.onClickCancel(getItem(position));
                }
            }
        });

        mBinding.rootView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAdapterListener.clickItem(ConstAdapter.OPEN_MASTER_INDEX, getItem(position).getSMasterId());
            }
        });
    }

    /**
     * 注册回调
     * @param mCallback
     */
    public void setCallback(FollowAdapterCallback mCallback) {
        this.mCallback = mCallback;
    }

    interface FollowAdapterCallback {
        /**
         * 点击取消关注
         * @param dataBean 数据对象
         */
        void onClickCancel(FollowBean.DataBean.SubscribesBean dataBean);
    }
}
