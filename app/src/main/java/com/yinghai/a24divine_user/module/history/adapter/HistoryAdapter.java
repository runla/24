package com.yinghai.a24divine_user.module.history.adapter;

import android.content.Context;

import com.example.fansonlib.base.adapter.BaseMultiAdapter;
import com.yinghai.a24divine_user.bean.HistoryBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConstAdapter;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/13 13:54
 *         Describe：历史记录的适配器
 */

public class HistoryAdapter extends BaseMultiAdapter<HistoryBean.DataBean> implements OnAdapterListener {

    /**
     * 用这个接口和Fragment通讯
     */
    private OnAdapterListener mOnAdapterListener;
    private DelegateProductAdapter mProductAdapter;
    private DelegateArticleAdapter mArticleAdapter;
    private DelegateMasterAdapter mMasterAdapter;

    public void setOnAdapterListener(OnAdapterListener listener) {
        mOnAdapterListener = listener;
    }

    public HistoryAdapter(Context context) {
        super(context);

        mProductAdapter = new DelegateProductAdapter(context);
        mProductAdapter.setOnAdapterListener(this);
        addItemViewDelegate(mProductAdapter);

        mArticleAdapter = new DelegateArticleAdapter(context);
        mArticleAdapter.setOnAdapterListener(this);
        addItemViewDelegate(mArticleAdapter);

        mMasterAdapter = new DelegateMasterAdapter(context);
        mMasterAdapter.setOnAdapterListener(this);
        addItemViewDelegate(mMasterAdapter);
    }

    @Override
    public void clickItem(Object... object) {
        switch ((int) object[0]) {
            case ConstAdapter.CLICK_PRODUCT:
                mOnAdapterListener.clickItem(ConstAdapter.CLICK_PRODUCT, object[1]);
                break;
            case ConstAdapter.CLICK_MASTER:
                mOnAdapterListener.clickItem(ConstAdapter.CLICK_MASTER, object[1]);
                break;
            case ConstAdapter.CLICK_ARTICLE:
                mOnAdapterListener.clickItem(ConstAdapter.CLICK_ARTICLE, object[1]);
                break;
            default:
                break;
        }
    }
}
