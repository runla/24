package com.yinghai.a24divine_user.module.collect.adapter;

import android.view.View;
import android.widget.ImageView;

import com.example.fansonlib.base.AppUtils;
import com.example.fansonlib.base.BaseHolder;
import com.example.fansonlib.base.adapter.ItemViewDelegate;
import com.example.fansonlib.image.ImageLoaderUtils;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.CollectBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConstAdapter;
import com.yinghai.a24divine_user.widget.LikeShape;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/10 14:20
 *         Describe：收藏的商品的适配器代理
 */

public class DelegateProductAdapter implements ItemViewDelegate<CollectBean.DataBean.CollectionBean> {

    /**
     * 用这个接口和父Adapter通讯
     */
    private OnAdapterListener mOnAdapterListener;

    public void setOnAdapterListener(OnAdapterListener listener) {
        mOnAdapterListener = listener;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_like_shop;
    }

    @Override
    public boolean isForViewType(CollectBean.DataBean.CollectionBean dataBean, int position) {
        return dataBean.getProduct() != null;
    }

    @Override
    public void convert(final BaseHolder holder, final CollectBean.DataBean.CollectionBean bean, final int position) {
        holder.setText(R.id.tv_shopping_name, bean.getProduct().getPName());
        holder.setText(R.id.tv_shopping_address, bean.getProduct().getPAttribution());
        holder.setText(R.id.tv_shop_price, "￥" + String.format("%d",bean.getProduct().getPPrice()/100));
        holder.setText(R.id.tv_shop_num, bean.getProduct().getPDeals() + AppUtils.getAppContext().getString(R.string.been_paid));
        if (bean.getProduct().isPFreeShipping()) {
            holder.getView(R.id.iv_shop_package).setVisibility(View.VISIBLE);
//            holder.setText(R.id.tv_shop_like,AppUtils.getAppContext().getString(R.string.cancel_collect));
        } else {
            holder.getView(R.id.iv_shop_package).setVisibility(View.GONE);
//            holder.setText(R.id.tv_shop_like,AppUtils.getAppContext().getString(R.string.collect));
        }
        if(bean.getProduct().getImgList().size()>0){
            ImageLoaderUtils.loadCircleImage(AppUtils.getAppContext().getApplicationContext(), (ImageView) holder.getView(R.id.iv_shop_picture),
                    ConHttp.BASE_URL + bean.getProduct().getImgList().get(0).getItUrl());
        }


        if (bean.getCollectionId() > 0) {
            holder.getView(R.id.iv_shop_like).setSelected(true);
        } else {
            holder.getView(R.id.iv_shop_like).setSelected(false);
        }
        holder.setOnClickListener(R.id.iv_shop_like, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LikeShape likeShape = (LikeShape)view;
                if (likeShape.isLike()) {
                    likeShape.setSelected(false);
                }else{
                    likeShape.setSelected(true);
                    likeShape.startAnimation();
                }
                mOnAdapterListener.clickItem(ConstAdapter.COLLECT_PRODUCT,bean.getProduct().getProductId(),view.isSelected());
            }
        });

        holder.setOnClickListener(R.id.rootView, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAdapterListener.clickItem(ConstAdapter.CLICK_PRODUCT, bean.getProduct().getProductId());
            }
        });
    }

}
