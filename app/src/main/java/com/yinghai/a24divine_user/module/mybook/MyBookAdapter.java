package com.yinghai.a24divine_user.module.mybook;

import android.content.Context;

import com.example.fansonlib.base.BaseDataAdapter;
import com.example.fansonlib.base.BaseHolder;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.OrderBean;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/25 17:50
 *         Describe：我的预约的适配器
 */

public class MyBookAdapter extends BaseDataAdapter<OrderBean.DataBean.TfOrderListBean> {


    public MyBookAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutRes(int i) {
        return R.layout.item_order;
    }

    @Override
    public void bindCustomViewHolder(BaseHolder holder, int position) {
//        holder.setText(R.id.tv_augur_name,getItem(position).getAugur());
//        holder.setText(R.id.tv_book_time,getItem(position).getBookTime());
//        holder.setText(R.id.tv_divine_type,getItem(position).getType());
//        holder.setText(R.id.tv_divine_price,String.valueOf(getItem(position).getPrice()));
//        ImageLoaderUtils.loadCircleImage(context, (ImageView) holder.getView(R.id.iv_divine_photo),getItem(position).getUser().getImgUrl());
    }
}
