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
import com.yinghai.a24divine_user.utils.ConstellationUtils;
import com.yinghai.a24divine_user.widget.LikeShape;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/10 14:17
 *         Describe：收藏的占卜师的适配器代理
 */

public class DelegateMasterAdapter implements ItemViewDelegate<CollectBean.DataBean.CollectionBean> {

    /**
     * 用这个接口和父Adapter通讯
     */
    private OnAdapterListener mOnAdapterListener;

    public void setOnAdapterListener(OnAdapterListener listener) {
        mOnAdapterListener = listener;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_like_divine;
    }

    @Override
    public boolean isForViewType(CollectBean.DataBean.CollectionBean dataBean, int i) {
        return dataBean.getMaster() != null;
    }

    @Override
    public void convert(final BaseHolder holder, final CollectBean.DataBean.CollectionBean bean, final int position) {
        holder.setText(R.id.tv_divine_name,bean.getMaster().getMNick());
        holder.setText(R.id.tv_divine_business,bean.getMaster().getMBusinessType());
        holder.setText(R.id.tv_divine_price,"￥"+String.format("%d",bean.getMaster().getMBargain()/100));
        holder.setText(R.id.tv_divine_label,bean.getMaster().getMLabel());
        holder.setText(R.id.tv_constellation, ConstellationUtils.getString(bean.getMaster().getMConstellation()));
        holder.setText(R.id.tv_divine_score,bean.getMaster().getMScore()+AppUtils.getAppContext().getString(R.string.score));
        holder.setText(R.id.tv_divine_read_num,AppUtils.getAppContext().getString(R.string.have_benn)+bean.getMaster().getMDeals()+AppUtils.getAppContext().getString(R.string.consult));
        ImageLoaderUtils.loadCircleImage(AppUtils.getAppContext().getApplicationContext(), (ImageView) holder.getView(R.id.iv_divine_photo), ConHttp.BASE_URL +bean.getMaster().getMHeadImg());

        if (bean.getCollectionId() > 0) {
            holder.getView(R.id.iv_divine_like).setSelected(true);
        } else {
            holder.getView(R.id.iv_divine_like).setSelected(false);
        }

        holder.setOnClickListener(R.id.iv_divine_like, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LikeShape likeShape = (LikeShape)view;
                if (likeShape.isLike()) {
                    likeShape.setSelected(false);
                }else{
                    likeShape.setSelected(true);
                    likeShape.startAnimation();
                }
                mOnAdapterListener.clickItem(ConstAdapter.COLLECT_MASTER,bean.getMaster().getMasterId(),view.isSelected());
            }
        });

        holder.setOnClickListener(R.id.rootView, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAdapterListener.clickItem(ConstAdapter.CLICK_MASTER,bean.getMaster().getMasterId());
            }
        });
    }
}
