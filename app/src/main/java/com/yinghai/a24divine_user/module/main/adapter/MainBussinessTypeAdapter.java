package com.yinghai.a24divine_user.module.main.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.fansonlib.base.BaseDataAdapter;
import com.example.fansonlib.base.BaseHolder;
import com.example.fansonlib.image.ImageLoaderUtils;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.BusinessTypeBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;

/**
 * Created by chenjianrun on 2017/10/25.
 * 描述:主界面中显示 名师文章、名师占卜、周边商城的 adapter （就是中间三个图）
 */

public class MainBussinessTypeAdapter extends BaseDataAdapter<BusinessTypeBean> {

    /**
     * 文章
     */
    public static final int ARTICAL = 0;

    /**
     * 名师
     */
    public static final int AUGUR = 1;

    /**
     * 商城
     */
    public static final int SHOP = 2;

    private OnAdapterListener mOnAdapterListener;

    public void setmOnAdapterListener(OnAdapterListener listener) {
        mOnAdapterListener = listener;
    }


    public MainBussinessTypeAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutRes(int i) {
        return R.layout.item_divine_type;
    }

    @Override
    public void bindCustomViewHolder(BaseHolder baseHolder, final int position) {
        ImageLoaderUtils.displayFromDrawable(context, getItem(position).getBgResourseId(), (ImageView) baseHolder.getView(R.id.image_bg));
        ImageLoaderUtils.displayFromDrawable(context, getItem(position).getTypeResourseId(), (ImageView) baseHolder.getView(R.id.image_type));
        baseHolder.setText(R.id.tv_descripe, getItem(position).getDescripe());
        baseHolder.setOnClickListener(R.id.fl_type, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAdapterListener.clickItem(position);
            }
        });
    }
}
