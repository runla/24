package com.yinghai.a24divine_user.module.order.detail.product;

import android.content.Context;

import com.example.fansonlib.base.BaseDataAdapter;
import com.example.fansonlib.base.BaseHolder;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.ProductOrderListBean;

/**
 * @author Created by：fanson
 *         Created Time: 2017/12/13 15:19
 *         Describe：商品订单详情的适配器
 */

public class ProductDetailAdapter extends BaseDataAdapter<ProductOrderListBean.DataBean.TfOrderListBean.OrderListBean>{


    public ProductDetailAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutRes(int i) {
        return R.layout.item_product_order_detail;
    }

    @Override
    public void bindCustomViewHolder(BaseHolder holder, int position) {
        holder.setText(R.id.tv_product_name,getItem(position).getTfProduct().getPName());
        holder.setText(R.id.tv_Product_num,String.valueOf(getItem(position).getOQty()));
        holder.setText(R.id.tv_price,String.valueOf(getItem(position).getTfProduct().getPPrice()/100));
        if (getItem(position).getTfMaster()!=null){
            holder.setText(R.id.tv_name,String.valueOf(getItem(position).getTfMaster().getMNick()));
        }
    }
}
