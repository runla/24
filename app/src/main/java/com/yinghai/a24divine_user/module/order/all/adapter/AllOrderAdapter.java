package com.yinghai.a24divine_user.module.order.all.adapter;

import android.content.Context;

import com.example.fansonlib.base.adapter.BaseMultiAdapter;
import com.yinghai.a24divine_user.module.order.MyObserver;
import com.yinghai.a24divine_user.module.order.SubjectHelper;
import com.yinghai.a24divine_user.bean.OrderBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConstAdapter;

/**
 * @author Created by：fanson
 *         Created on：2017/10/24 18:15
 *         Description：全部订单的适配器
 */

public class AllOrderAdapter extends BaseMultiAdapter<OrderBean.DataBean.TfOrderListBean> implements OnAdapterListener {

    /*记录当前点击的占卜Bean*/
    private OrderBean.DataBean.TfOrderListBean mClickDivineBean;
    /*记录当前点击的位置*/
    private int mClickPosition;
    private OrderStatusObserver mObserver;
    private OnAdapterListener mOnAdapterListener;

    public AllOrderAdapter(Context context, OnAdapterListener listener) {
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
            case ConstAdapter.COMPLETE_ORDER:
                mOnAdapterListener.clickItem(ConstAdapter.COMPLETE_ORDER, object[1], object[2]);
                break;
            default:
                break;
        }
    }

    /**
     * 观察者，接收订单状态的改变，然后更新
     * （非静态内部类引用外部类）
     */
    private  class OrderStatusObserver implements MyObserver {
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
