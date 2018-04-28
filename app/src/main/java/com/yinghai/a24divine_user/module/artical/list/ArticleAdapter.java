package com.yinghai.a24divine_user.module.artical.list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fansonlib.base.BaseDataAdapter;
import com.example.fansonlib.base.BaseHolder;
import com.example.fansonlib.image.ImageLoaderUtils;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.ArticleBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.databinding.ItemMasterArticleBinding;
import com.yinghai.a24divine_user.utils.StringUtils;

/**
 * @author Created by：fanson
 *         Created on：2017/10/27 15:11
 *         Description：文章的适配器
 */

public class ArticleAdapter extends BaseDataAdapter<ArticleBean.DataBean.ArticleListBean> {

    private ItemMasterArticleBinding mBinding;
    private IAdapterListener mListener;
    private int mClickCollectId = 0;

    public void setIAdapterListener(IAdapterListener listener) {
        mListener = listener;
    }

    public ArticleAdapter(Context context) {
        super(context);
    }


    @Override
    public int getLayoutRes(int position) {
        return 0;
    }

    @Override
    public BaseHolder bindHolder(ViewGroup parent, int viewType) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_master_article, parent, false);
        return new BaseHolder(mBinding.getRoot());
    }

    @Override
    public void bindCustomViewHolder(BaseHolder holder, final int position) {
        mBinding = DataBindingUtil.getBinding(holder.itemView);
        mBinding.setBean(getItem(position));
        mBinding.executePendingBindings();

        mBinding.tvArticleDescribe.setText(StringUtils.replaceBracket(getItem(position).getAContent()));
        if (getItem(position).getImgList().size() > 0) {
            ImageLoaderUtils.loadCircleImage(context, mBinding.ivAugurPhoto, ConHttp.BASE_URL + getItem(position).getImgList().get(0).getItUrl());
        } else {
            ImageLoaderUtils.loadCircleImage(context, mBinding.ivAugurPhoto, null);
        }

        if (getItem(position).isIsCollection()) {
            mBinding.ivArticleLike.setSelected(true);
            if (mClickCollectId != 0 && mClickCollectId == getItem(position).getArticleId()) {
                mBinding.ivArticleLike.startAnimation();
                mClickCollectId = 0;
            }
        } else {
            mBinding.ivArticleLike.setSelected(false);
        }

        mBinding.ivArticleLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClickLikeArticle(getItem(position));
            }
        });

        mBinding.rlArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClickArticleCardView(getItem(position).getArticleId());
            }
        });
    }


    /**
     * 更新红心的显示
     *
     * @param articleId 文章ID
     * @param isLike    是否收藏
     */
    public void updateLike(int articleId, boolean isLike) {
        for (int i = 0; i < getItemCount(); i++) {
            if (getItem(i).getArticleId() == articleId) {
                getItem(i).setIsCollection(isLike);
                mClickCollectId = articleId;
                notifyItemChanged(i);
                break;
            }
        }
    }


    public interface IAdapterListener {

        /**
         * 点击文章Item
         *
         * @param articleId 文章ID
         */
        void onClickArticleCardView(int articleId);

        /**
         * 点击收藏文章
         *
         * @param bean 数据
         */
        void onClickLikeArticle(ArticleBean.DataBean.ArticleListBean bean);
    }
}
