package com.yinghai.a24divine_user.module.collect.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.fansonlib.base.BaseHolder;
import com.example.fansonlib.base.adapter.ItemViewDelegate;
import com.example.fansonlib.image.ImageLoaderUtils;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.CollectBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConstAdapter;
import com.yinghai.a24divine_user.utils.StringUtils;
import com.yinghai.a24divine_user.widget.LikeShape;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/10 14:20
 *         Describe：收藏的文章的适配器代理
 */

public class DelegateArticleAdapter implements ItemViewDelegate<CollectBean.DataBean.CollectionBean> {

    /**
     * 用这个接口和父Adapter通讯
     */
    private OnAdapterListener mOnAdapterListener;

    private Context mContext;

    public void setOnAdapterListener(OnAdapterListener listener) {
        mOnAdapterListener = listener;
    }


    public DelegateArticleAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_like_article;
    }

    @Override
    public boolean isForViewType(CollectBean.DataBean.CollectionBean dataBean, int i) {
        return dataBean.getArticle() != null;
    }

    @Override
    public void convert(final BaseHolder holder, final CollectBean.DataBean.CollectionBean bean, final int position) {
        holder.setText(R.id.tv_article_name, bean.getArticle().getATitle());
        holder.setText(R.id.tv_article_describe, StringUtils.replaceBracket(bean.getArticle().getAContent()));
        if (bean.getArticle().getImgList().size() > 0) {
            ImageLoaderUtils.loadImage(mContext, (ImageView) holder.getView(R.id.iv_augur_photo), ConHttp.BASE_URL + bean.getArticle().getImgList().get(0).getItUrl());
        }

        if (bean.getCollectionId() > 0) {
            holder.getView(R.id.iv_article_collect).setSelected(true);
        } else {
            holder.getView(R.id.iv_article_collect).setSelected(false);
        }

        holder.setOnClickListener(R.id.iv_article_collect, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LikeShape likeShape = (LikeShape)view;
                if (likeShape.isLike()) {
                    likeShape.setSelected(false);
                }else{
                    likeShape.setSelected(true);
                    likeShape.startAnimation();
                }
                mOnAdapterListener.clickItem(ConstAdapter.COLLECT_ARTICLE, bean.getArticle().getArticleId(), view.isSelected());
            }
        });

        holder.setOnClickListener(R.id.rootView, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAdapterListener.clickItem(ConstAdapter.CLICK_ARTICLE, bean.getArticle());
            }
        });
    }
}
