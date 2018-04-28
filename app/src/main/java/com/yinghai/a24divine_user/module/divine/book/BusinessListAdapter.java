package com.yinghai.a24divine_user.module.divine.book;

import android.content.Context;
import android.view.View;

import com.example.fansonlib.base.BaseDataAdapter;
import com.example.fansonlib.base.BaseHolder;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.BusinessBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;

/**
 * Created by chenjianrun on 2017/10/27.
 * 描述：占卜预约列表 adapter
 */
public class BusinessListAdapter extends BaseDataAdapter<BusinessBean.DataBean.TfBusinessListBean> {

    private OnAdapterListener mListener;

    public void setOnAdapterListener(OnAdapterListener listener){
        mListener = listener;
    }

    public BusinessListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutRes(int i) {
        return R.layout.master_divine_detail_bottom;
    }

    @Override
    public void bindCustomViewHolder(BaseHolder holder, final int position) {
        holder.setText(R.id.tv_divine_type, getItem(position).getBName());
        holder.setText(R.id.tv_divine_descripe, getItem(position).getBIntroduction());
        holder.setText(R.id.tv_divine_price, getItem(position).getBPrice()/100 + context.getString(R.string.price_every));
        holder.setText(R.id.tv_divine_has_num, context.getString(R.string.have_benn) + getItem(position).getBDeals() + context.getString(R.string.consult));
        holder.setOnClickListener(R.id.rootView, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.clickItem(getItem(position));
                }
            }
        });

    }

}
