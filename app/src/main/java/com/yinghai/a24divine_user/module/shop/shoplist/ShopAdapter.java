package com.yinghai.a24divine_user.module.shop.shoplist;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.fansonlib.base.BaseDataAdapter;
import com.example.fansonlib.base.BaseHolder;
import com.example.fansonlib.image.ImageLoaderUtils;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.ProductBean;
import com.yinghai.a24divine_user.constant.ConHttp;

/**
 * @author Created by：fanson
 *         Created on：2017/10/26 10:51
 *         Description：商城的适配器
 * @Param
 */

public class ShopAdapter extends BaseDataAdapter<ProductBean.DataBean.ProductListBean> {

    private IShopAdapterListener mIAdapterListener;

    public void setIAdapterListener(IShopAdapterListener listener) {
        mIAdapterListener = listener;
    }

    public ShopAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutRes(int i) {
        return R.layout.item_shop;
    }

    @Override
    public void bindCustomViewHolder(final BaseHolder holder, final int position) {
        if (getItem(position).getImgList().size() > 0) {
            ImageLoaderUtils.loadImage(context, (ImageView) holder.getView(R.id.iv_product), ConHttp.BASE_URL + getItem(position).getImgList().get(0).getItUrl());
        }
        holder.setText(R.id.tv_product_name, getItem(position).getPName());
        holder.setText(R.id.tv_shop_address, getItem(position).getPAttribution());
        holder.setText(R.id.tv_product_price, String.format(context.getString(R.string.money_unit), getItem(position).getPPrice() / 100));
        holder.setText(R.id.tv_buy_num, String.format( context.getString(R.string.sold),getItem(position).getPDeals()));

        if (getItem(position).isPFreeShipping()) {
            holder.getView(R.id.iv_shop_package).setVisibility(View.VISIBLE);
        } else {
            holder.getView(R.id.iv_shop_package).setVisibility(View.GONE);
        }

        holder.setOnClickListener(R.id.rl_shop_item, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIAdapterListener.clickItem(getItem(position));
            }
        });

    }

    /**
     * 商品
     *
     * @param productId 产品ID
     * @param isLike    是否收藏
     */
    public void updateLike(int productId, boolean isLike) {
        for (int i = 0; i < getItemCount(); i++) {
            if (getItem(i).getProductId() == productId) {
                getItem(i).setIsCollection(isLike);
                notifyItemChanged(i);
                break;
            }
        }
    }

    public interface IShopAdapterListener {
        /**
         * 点击Adapter的某项
         *
         * @param bean 数据
         */
        void clickItem(ProductBean.DataBean.ProductListBean bean);

    }

}
