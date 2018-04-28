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
import com.yinghai.a24divine_user.utils.StringUtils;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/13 14:09
 *         Describe：历史记录的文章的适配器代理
 */

public class DelegateArticleAdapter implements ItemViewDelegate<HistoryBean.DataBean> {

    /**
     * 用这个接口和父Adapter通讯
     */
    private OnAdapterListener mOnAdapterListener;
    private Context mContext;

    public void setOnAdapterListener(OnAdapterListener listener) {
        mOnAdapterListener = listener;
    }

    public DelegateArticleAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_history_article;
    }

    @Override
    public boolean isForViewType(HistoryBean.DataBean dataBean, int i) {
        return dataBean.getArticle() != null;
    }

    @Override
    public void convert(BaseHolder holder, final HistoryBean.DataBean bean, final int position) {
        holder.setText(R.id.tv_article_name, bean.getArticle().getaTitle());
        holder.setText(R.id.tv_article_describe, StringUtils.replaceBracket(bean.getArticle().getAContent()));
        holder.setText(R.id.tv_article_read_num, bean.getArticle().getAReadAmount() + AppUtils.getAppContext().getString(R.string.read_count));
        if (bean.getArticle() != null&&bean.getArticle().getImgList().size()>0) {
            ImageLoaderUtils.loadCircleImage(mContext, (ImageView) holder.getView(R.id.iv_article_photo),
                    ConHttp.BASE_URL + bean.getArticle().getImgList().get(0).getItUrl());
        }

        holder.setOnClickListener(R.id.rootView, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAdapterListener.clickItem(ConstAdapter.CLICK_ARTICLE, bean.getArticle());
            }
        });
    }
}
