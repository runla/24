package com.yinghai.a24divine_user.module.collect.adapter;

import android.content.Context;

import com.example.fansonlib.base.adapter.BaseMultiAdapter;
import com.yinghai.a24divine_user.bean.CollectBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConstAdapter;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/30 11:48
 *         Describe：我的收藏的适配器
 */

public class CollectionAdapter extends BaseMultiAdapter<CollectBean.DataBean.CollectionBean> implements OnAdapterListener {

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

    public CollectionAdapter(Context context) {
        super(context);

        mProductAdapter = new DelegateProductAdapter();
        mProductAdapter.setOnAdapterListener(this);
        addItemViewDelegate(mProductAdapter);

        mArticleAdapter = new DelegateArticleAdapter(context);
        mArticleAdapter.setOnAdapterListener(this);
        addItemViewDelegate(mArticleAdapter);

        mMasterAdapter = new DelegateMasterAdapter();
        mMasterAdapter.setOnAdapterListener(this);
        addItemViewDelegate(mMasterAdapter);

    }

    @Override
    public void clickItem(Object... object) {
        switch ((int) object[0]) {
            case ConstAdapter.CLICK_PRODUCT:
                mOnAdapterListener.clickItem(ConstAdapter.CLICK_PRODUCT, object[1]);
                break;
            case ConstAdapter.COLLECT_PRODUCT:
                mOnAdapterListener.clickItem(ConstAdapter.COLLECT_PRODUCT, object[1], object[2]);
                break;
            case ConstAdapter.CLICK_MASTER:
                mOnAdapterListener.clickItem(ConstAdapter.CLICK_MASTER, object[1]);
                break;
            case ConstAdapter.COLLECT_MASTER:
                mOnAdapterListener.clickItem(ConstAdapter.COLLECT_MASTER, object[1], object[2]);
                break;
            case ConstAdapter.CLICK_ARTICLE:
                mOnAdapterListener.clickItem(ConstAdapter.CLICK_ARTICLE, object[1]);
                break;
            case ConstAdapter.COLLECT_ARTICLE:
                mOnAdapterListener.clickItem(ConstAdapter.COLLECT_ARTICLE, object[1], object[2]);
                break;
            default:
                break;
        }
    }
}
