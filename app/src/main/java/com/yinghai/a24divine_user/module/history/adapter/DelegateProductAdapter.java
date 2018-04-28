package com.yinghai.a24divine_user.module.history.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.fansonlib.base.AppUtils;
import com.example.fansonlib.base.BaseHolder;
import com.example.fansonlib.base.adapter.ItemViewDelegate;
import com.example.fansonlib.image.ImageLoaderUtils;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.HistoryBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConstAdapter;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/13 14:03
 *         Describe：历史记录的文章的适配器代理
 */

public class DelegateProductAdapter implements ItemViewDelegate<HistoryBean.DataBean> {

    /**
     * 用这个接口和父Adapter通讯
     */
    private OnAdapterListener mOnAdapterListener;
    private Context mContext;

    public void setOnAdapterListener(OnAdapterListener listener) {
        mOnAdapterListener = listener;
    }

    public DelegateProductAdapter(Context context){
        mContext = context;
    }


    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_history_shop;
    }

    @Override
    public boolean isForViewType(HistoryBean.DataBean dataBean, int i) {
        return dataBean.getProduct() != null;
    }

    @Override
    public void convert(BaseHolder holder, final HistoryBean.DataBean bean, int position) {
        holder.setText(R.id.tv_shopping_name, bean.getProduct().getPName());
        holder.setText(R.id.tv_shopping_address, bean.getProduct().getPAttribution());

        holder.setText(R.id.tv_shop_price, String.format(AppUtils.getString(R.string.money_unit),bean.getProduct().getPPrice()/100));
        holder.setText(R.id.tv_shop_num, bean.getProduct().getPDeals() + AppUtils.getString(R.string.been_paid));
        if (bean.getProduct().isPFreeShipping()) {
            holder.getView(R.id.iv_shop_package).setVisibility(View.VISIBLE);
        } else {
            holder.getView(R.id.iv_shop_package).setVisibility(View.GONE);
        }

        if (bean.getProduct().getImgList().size() > 0) {
            ImageLoaderUtils.loadCircleImage(mContext, (ImageView) holder.getView(R.id.iv_shop_picture),
                    ConHttp.BASE_URL + bean.getProduct().getImgList().get(0).getItUrl());
        }
        holder.setOnClickListener(R.id.rootView, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAdapterListener.clickItem(ConstAdapter.CLICK_PRODUCT, bean.getProduct().getProductId());
            }
        });
    }
}
