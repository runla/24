package com.yinghai.a24divine_user.module.order.all.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.fansonlib.base.AppUtils;
import com.example.fansonlib.base.BaseHolder;
import com.example.fansonlib.base.adapter.ItemViewDelegate;
import com.example.fansonlib.image.ImageLoaderUtils;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.OrderBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConstAdapter;
import com.yinghai.a24divine_user.constant.ConstOrderStatus;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/15 10:31
 *         Describe：占卜订单的代理适配器
 */

public class DivineOrderAdapter implements ItemViewDelegate<OrderBean.DataBean.TfOrderListBean> {

    private Context mContext;
    private OnAdapterListener mOnAdapterListener;

    public DivineOrderAdapter(Context context, OnAdapterListener listener) {
        mContext = context;
        mOnAdapterListener = listener;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_order;
    }

    @Override
    public boolean isForViewType(OrderBean.DataBean.TfOrderListBean bean, int i) {
        return bean.getTfProduct() == null;
    }

    @Override
    public void convert(BaseHolder holder, final OrderBean.DataBean.TfOrderListBean bean, final int position) {
        if (bean.getTfMaster() != null) {
            ImageLoaderUtils.loadCircleImage(mContext, (ImageView) holder.getView(R.id.iv_divine_photo), ConHttp.BASE_URL + bean.getTfMaster().getMHeadImg());
            holder.setText(R.id.tv_augur_name, bean.getTfMaster().getMNick());
        }
        holder.setText(R.id.tv_divine_price, String.format(mContext.getString(R.string.money_unit), bean.getOPrice() / 100));
        holder.setText(R.id.tv_book_time, bean.getOAppointmentTime());
        holder.setText(R.id.tv_orderId, String.format(mContext.getString(R.string.order_id), bean.getOrderId()));
        if (bean.getTfBusiness() != null) {
            holder.setText(R.id.tv_divine_type, bean.getTfBusiness().getBName());
        }
        switch (bean.getOStatus()) {
            case ConstOrderStatus.WAITING_PAY:
                holder.setText(R.id.btn_status, mContext.getString(R.string.wait_pay));
                break;
            case ConstOrderStatus.HAS_BEEN_PAID:
                holder.setText(R.id.btn_status, mContext.getString(R.string.been_paid));
                break;
            case ConstOrderStatus.HAS_BEEN_CONFIRM:
                holder.setText(R.id.btn_status, AppUtils.getString(R.string.been_confirm));
                break;
            case ConstOrderStatus.ORDER_ING:
                holder.setText(R.id.btn_status, AppUtils.getString(R.string.ing));
                break;
            case ConstOrderStatus.REFUND_ING:
                holder.setText(R.id.btn_status, mContext.getString(R.string.refunding));
                break;
            case ConstOrderStatus.COMPLETED:
                holder.setText(R.id.btn_status, mContext.getString(R.string.completed));
                break;
            case ConstOrderStatus.REFUND:
                holder.setText(R.id.btn_status, mContext.getString(R.string.been_refund));
                break;
            case ConstOrderStatus.CANCELED:
                holder.setText(R.id.btn_status, mContext.getString(R.string.been_cancel));
                break;
            default:
                break;

        }

        holder.setOnClickListener(R.id.btn_status, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bean.getOStatus() == ConstOrderStatus.WAITING_PAY) {
                    mOnAdapterListener.clickItem(ConstAdapter.OPEN_PAY_WINDOW, bean.getOOrderNo(), 1, bean.getOAmount());
                }
            }
        });

        holder.setOnClickListener(R.id.rootView, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAdapterListener.clickItem(ConstAdapter.DIVINE_ORDER_DETAIL, bean, position);
            }
        });
    }

}
