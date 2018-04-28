package com.yinghai.a24divine_user.module.order.all.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.fansonlib.base.AppUtils;
import com.example.fansonlib.base.BaseDataAdapter;
import com.example.fansonlib.base.BaseHolder;
import com.example.fansonlib.image.ImageLoaderUtils;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.ProductOrderListBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConstAdapter;
import com.yinghai.a24divine_user.constant.ConstOrderStatus;
import com.yinghai.a24divine_user.module.order.MyObserver;
import com.yinghai.a24divine_user.module.order.SubjectHelper;

import static com.example.fansonlib.utils.notification.MyNotificationUtils.mContext;

/**
 * @author Created by：fanson
 *         Created Time: 2017/12/13 14:09
 *         Describe：我的订单（商品订单的适配器）
 */

public class AllProductOrderAdapter extends BaseDataAdapter<ProductOrderListBean.DataBean.TfOrderListBean> {

    /**
     * 记录当前点击的商品Bean
     */
    private ProductOrderListBean.DataBean.TfOrderListBean mClickProductBean;
    /**
     * 记录当前点击的位置
     */
    private int mClickPosition;
    private OrderStatusObserver mObserver;
    private OnAdapterListener mOnAdapterListener;

    public AllProductOrderAdapter(Context context, OnAdapterListener listener) {
        super(context);
        mOnAdapterListener = listener;
    }

    @Override
    public int getLayoutRes(int i) {
        return R.layout.item_order_product;
    }

    @Override
    public void bindCustomViewHolder(BaseHolder holder, final int position) {
        ProductOrderListBean.DataBean.TfOrderListBean.OrderListBean orderListBeen;
        if (getItem(position).getOrderList().size() > 0) {
            orderListBeen = getItem(position).getOrderList().get(0);

            if (orderListBeen.getTfProduct() != null && orderListBeen.getTfProduct().getImgList() != null && orderListBeen.getTfProduct().getImgList().size() > 0) {
                ImageLoaderUtils.loadImage(mContext, (ImageView) holder.getView(R.id.iv_product_photo),
                        ConHttp.BASE_URL + orderListBeen.getTfProduct().getImgList().get(0).getItUrl());
            }
            if (orderListBeen.getTfProduct() != null) {
                holder.setText(R.id.tv_product_title, orderListBeen.getTfProduct().getPName());
                holder.setText(R.id.tv_product_price, String.format(mContext.getString(R.string.money_unit), orderListBeen.getTfProduct().getPPrice() / 100));
                holder.setText(R.id.tv_address, orderListBeen.getTfProduct().getPAttribution());
            }
        }
        holder.setText(R.id.tv_orderId, String.format(mContext.getString(R.string.order_id), getItem(position).getTotalId()));
        holder.setText(R.id.tv_product_amount, mContext.getString(R.string.amount) + getItem(position).gettQty());
        holder.setText(R.id.tv_price_sum, mContext.getString(R.string.price_sum) + getItem(position).getTAmount() / 100);

        switch (getItem(position).gettStatus()) {
            case ConstOrderStatus.WAITING_PAY:
                holder.setText(R.id.btn_pay, AppUtils.getString(R.string.go_pay));
                break;
            case ConstOrderStatus.HAS_BEEN_PAID:
                holder.setText(R.id.btn_pay, AppUtils.getString(R.string.been_paid));
                break;
            case ConstOrderStatus.HAS_BEEN_CONFIRM:
                holder.setText(R.id.btn_pay, AppUtils.getString(R.string.been_confirm));
                break;
            case ConstOrderStatus.COMPLETED:
                holder.setText(R.id.btn_pay, AppUtils.getString(R.string.been_completed));
                break;
            case ConstOrderStatus.REFUND_ING:
                holder.setText(R.id.btn_pay, AppUtils.getString(R.string.refunding));
                break;
            case ConstOrderStatus.CANCELED:
                holder.setText(R.id.btn_pay, AppUtils.getString(R.string.been_cancel));
                break;
            case ConstOrderStatus.REFUND:
                holder.setText(R.id.btn_pay, AppUtils.getString(R.string.been_refund));
                break;
            default:
                break;
        }

        holder.setOnClickListener(R.id.btn_pay, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getItem(position).gettStatus() == ConstOrderStatus.WAITING_PAY) {
                    mOnAdapterListener.clickItem(ConstAdapter.OPEN_PAY_WINDOW, getItem(position).getTOrderNo(), 3, getItem(position).getTAmount());
                }
            }
        });

        holder.setOnClickListener(R.id.rootView, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAdapterListener.clickItem(ConstAdapter.OPEN_PRODUCT_ORDER_DETAIL, getItem(position));
                mClickPosition = position;
                mClickProductBean = getItem(position);
                if (mObserver == null) {
                    mObserver = new OrderStatusObserver();
                }
                SubjectHelper.attachOneObserver(mObserver);
            }
        });
    }

    /**
     * 观察者，接收订单状态的改变，然后更新
     */
    private class OrderStatusObserver implements MyObserver {
        @Override
        public void update(int status) {
            mClickProductBean.settStatus(status);
            notifyItemChanged(mClickPosition);
        }
    }
}
