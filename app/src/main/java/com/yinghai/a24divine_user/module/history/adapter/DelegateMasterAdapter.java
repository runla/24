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
import com.yinghai.a24divine_user.utils.ConstellationUtils;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/13 14:10
 *         Describe：历史记录的占卜师的适配器代理
 */

public class DelegateMasterAdapter implements ItemViewDelegate<HistoryBean.DataBean> {

    /**
     * 用这个接口和父Adapter通讯
     */
    private OnAdapterListener mOnAdapterListener;
    private Context mContext;

    public void setOnAdapterListener(OnAdapterListener listener) {
        mOnAdapterListener = listener;
    }

    public DelegateMasterAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_history_divine;
    }

    @Override
    public boolean isForViewType(HistoryBean.DataBean dataBean, int i) {
        return dataBean.getMaster() != null;
    }

    @Override
    public void convert(BaseHolder holder, final HistoryBean.DataBean bean, final int position) {
        holder.setText(R.id.tv_divine_name,bean.getMaster().getMNick());
        holder.setText(R.id.tv_divine_business,bean.getMaster().getMBusinessType());
        holder.setText(R.id.tv_divine_price,String.format(AppUtils.getString(R.string.money_unit),bean.getMaster().getMBargain()/100));
        holder.setText(R.id.tv_constellation, ConstellationUtils.getString(bean.getMaster().getMConstellation()));
        holder.setText(R.id.tv_divine_score,bean.getMaster().getMScore()+ AppUtils.getAppContext().getString(R.string.score));
        holder.setText(R.id.tv_divine_read_num,AppUtils.getAppContext().getString(R.string.have_benn)+bean.getMaster().getMDeals()+AppUtils.getAppContext().getString(R.string.consult));
        ImageLoaderUtils.loadCircleImage(mContext, (ImageView) holder.getView(R.id.iv_divine_photo), ConHttp.BASE_URL +bean.getMaster().getMHeadImg());

        holder.setOnClickListener(R.id.rootView, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAdapterListener.clickItem(ConstAdapter.CLICK_MASTER,bean.getMaster().getMasterId());
            }
        });
    }
}
