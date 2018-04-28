package com.yinghai.a24divine_user.module.shop.details;

import android.content.Context;

import com.example.fansonlib.base.BaseDataAdapter;
import com.example.fansonlib.base.BaseHolder;
import com.example.fansonlib.image.ImageLoaderUtils;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.CommentBean;
import com.yinghai.a24divine_user.constant.ConHttp;

import de.hdodenhof.circleimageview.CircleImageView;

/**
* @author Created by：fanson
* Created on：2017/10/27 9:34
* Description：商品评论的适配器
*/

public class CommentsAdapter extends BaseDataAdapter<CommentBean.DataBean.CommentsBean> {

    public CommentsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutRes(int i) {
        return R.layout.item_product_comment;
    }

    @Override
    public void bindCustomViewHolder(BaseHolder baseHolder, int position) {
        CommentBean.DataBean.CommentsBean bean = getItem(position);
        baseHolder.setText(R.id.tv_comment_name,bean.getNick());
        baseHolder.setText(R.id.tv_comment_content,bean.getCtContent());
        ImageLoaderUtils.loadCornerImage(context, (CircleImageView) baseHolder.getView(R.id.image_comment_header), ConHttp.BASE_URL+bean.getImg());
    }
}
