package com.yinghai.a24divine_user.module.order.book;

import android.content.Context;

import com.example.fansonlib.base.adapter.BaseMultiAdapter;
import com.yinghai.a24divine_user.module.order.MyObserver;
import com.yinghai.a24divine_user.module.order.SubjectHelper;
import com.yinghai.a24divine_user.bean.OrderBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConstAdapter;
import com.yinghai.a24divine_user.module.order.all.adapter.DivineOrderAdapter;

/**
 * @author Created by：fanson
 * @date Created on：2017/10/24 18:21
 * @Describe： 未完成订单的适配器
 */

public class BookOrderAdapter extends BaseMultiAdapter<OrderBean.DataBean.TfOrderListBean> implements OnAdapterListener {

    /**
     * 记录当前点击的占卜Bean
     */
    private OrderBean.DataBean.TfOrderListBean mClickDivineBean;
    /**
     * 记录当前点击的位置
     */
    private int mClickPosition;
    private OrderStatusObserver mObserver;
    private OnAdapterListener mOnAdapterListener;

    public BookOrderAdapter(Context context, OnAdapterListener listener) {
        super(context);

        mOnAdapterListener = listener;

        DivineOrderAdapter mDivineOrderAdapter = new DivineOrderAdapter(context, this);

        addItemViewDelegate(mDivineOrderAdapter);

    }

    @Override
    public void clickItem(Object... object) {
        switch ((Integer) object[0]) {
            case ConstAdapter.DIVINE_ORDER_DETAIL:
                mOnAdapterListener.clickItem(ConstAdapter.DIVINE_ORDER_DETAIL, object[1]);
                mClickPosition = (int) object[2];
                mClickDivineBean = (OrderBean.DataBean.TfOrderListBean) object[1];
                if (mObserver == null) {
                    mObserver = new OrderStatusObserver();
                }
                SubjectHelper.attachOneObserver(mObserver);
                break;
            case ConstAdapter.OPEN_PAY_WINDOW:
                mOnAdapterListener.clickItem(ConstAdapter.OPEN_PAY_WINDOW, object[1], object[2], object[3]);
                break;
            default:
                break;
        }
    }

    /**
     * 观察者，接收订单状态的改变，然后更新
     */
    private class OrderStatusObserver implements MyObserver {
        @Override
        public void update(int status) {
            mClickDivineBean.setOStatus(status);
            notifyItemChanged(mClickPosition);
        }
    }

    /**
     * 释放资源
     */
    public void releaseResource(){
        SubjectHelper.detachObserver(mObserver);
        mObserver = null;
        mOnAdapterListener = null;
    }

}
