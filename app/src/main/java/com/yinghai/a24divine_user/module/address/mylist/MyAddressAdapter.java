package com.yinghai.a24divine_user.module.address.mylist;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fansonlib.base.BaseDataAdapter;
import com.example.fansonlib.base.BaseHolder;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.AddressListBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConstAdapter;
import com.yinghai.a24divine_user.databinding.ItemMyAddressBinding;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/17 11:39
 *         Describe：我的地址的适配器
 */

public class MyAddressAdapter extends BaseDataAdapter<AddressListBean.DataBean.AddressBean> {

    private int mSelectedPosition = -1;
    private OnAdapterListener mOnAdapterListener;
    private ItemMyAddressBinding mBinding;

    public void setOnAdapterListener(OnAdapterListener listener) {
        mOnAdapterListener = listener;
    }

    public MyAddressAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutRes(int i) {
        return 0;
    }

    @Override
    public BaseHolder bindHolder(ViewGroup parent, int viewType) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.item_my_address,parent,false);
        return new BaseHolder(mBinding.getRoot());
    }

    @Override
    public void bindCustomViewHolder(final BaseHolder holder, final int position) {
        mBinding = DataBindingUtil.getBinding(holder.itemView);
        mBinding.setBean(getItem(position));
        mBinding.executePendingBindings();

        for (int i = 0; i < getItemCount(); i++) {
            if (mSelectedPosition == position) {
                mBinding.ivChooose.setPressed(true);
            } else {
                mBinding.ivChooose.setPressed(false);
            }
        }

        mBinding.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //先取消上个item的勾选状态
                if (mSelectedPosition != -1) {
                    mDataList.get(mSelectedPosition).setSeleted(false);
                    notifyItemChanged(mSelectedPosition);
                }
                //设置新Item的勾选状态
                mSelectedPosition = position;
                mDataList.get(mSelectedPosition).setSeleted(true);
                notifyItemChanged(mSelectedPosition);
                mOnAdapterListener.clickItem(ConstAdapter.RETURN_ADDRESS,getItem(position).getAddressId());
            }
        });

        mBinding.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAdapterListener.clickItem(ConstAdapter.OPEN_EDIT_ADDRESS,getItem(position));
            }
        });

        mBinding.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAdapterListener.clickItem(ConstAdapter.DELETE_ADDRESS, getItem(position).getAddressId());
            }
        });
    }

    /**
     * 根据判断id，进行删除
     * @param data
     */
    @Override
    public void removeItem(AddressListBean.DataBean.AddressBean data) {
        for (int i=0;i<mDataList.size();i++){
            if (mDataList.get(i).equals(data)){
                mDataList.remove(i);
                this.notifyItemRemoved(i);
                this.notifyItemRangeChanged(i, this.mDataList.size() - i);
                return;
            }
        }
    }
}
