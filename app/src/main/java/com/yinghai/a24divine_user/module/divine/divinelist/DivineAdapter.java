package com.yinghai.a24divine_user.module.divine.divinelist;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.fansonlib.base.BaseDataAdapter;
import com.example.fansonlib.base.BaseHolder;
import com.example.fansonlib.image.ImageLoaderUtils;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.MasterBean;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.utils.ConstellationUtils;
import com.yinghai.a24divine_user.widget.LikeShape;

/**
 * Created by chenjianrun on 2017/10/25.
 * 描述：名师占卜列表的 adapter
 */

public class DivineAdapter extends BaseDataAdapter<MasterBean.DataBean.MasterListBean> {
    private IAdapterListener mListener;
    private int mClickCollectId = 0;
    public void setAdapterListener(IAdapterListener mIAdapterListener) {
        this.mListener = mIAdapterListener;
    }

    public DivineAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutRes(int i) {
        return R.layout.item_master_divine;
    }

    @Override
    public void bindCustomViewHolder(BaseHolder holder, final int position) {
        holder.setText(R.id.tv_divine_name, getItem(position).getMNick());
        holder.setText(R.id.tv_divine_descripe, getItem(position).getMIntroduction());
        holder.setText(R.id.tv_divine_read_num, context.getString(R.string.month_have) + getItem(position).getMFollows() + context.getString(R.string.consult));
        holder.setText(R.id.tv_divine_price, "￥" + String.format("%d", getItem(position).getMBargain() / 100));
        holder.setText(R.id.tv_constellation, ConstellationUtils.getString(getItem(position).getMConstellation()));
        holder.setText(R.id.tv_divine_lable, getItem(position).getMLabel());
        holder.setText(R.id.tv_divine_business, getItem(position).getMBusinessType());
        holder.setText(R.id.tv_divine_score, getItem(position).getMScore() + context.getString(R.string.score));
        ImageLoaderUtils.loadCircleImage(context, (ImageView) holder.getView(R.id.iv_divine_photo), ConHttp.BASE_URL + getItem(position).getMHeadImg());

        if (getItem(position).isIsCollection()) {
            holder.getView(R.id.iv_divine_like).setSelected(true);
            if (mClickCollectId != 0
                    && mClickCollectId == getItem(position).getMasterId()) {
                ((LikeShape)holder.getView(R.id.iv_divine_like)).startAnimation();
                mClickCollectId = 0;
            }
        } else {
            holder.getView(R.id.iv_divine_like).setSelected(false);
        }

        holder.setOnClickListener(R.id.iv_divine_like, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LikeShape likeShape = (LikeShape)view;
                mListener.onClickLikeDivine(getItem(position));
            }
        });

        holder.setOnClickListener(R.id.rootView, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onClickDivineRootView(getItem(position));
                }
            }
        });

    }

    /**
     * 更新红心的显示
     *
     * @param masterId 大师ID
     * @param isLike   是否收藏
     */
    public void updateLike(int masterId, boolean isLike) {
        for (int i = 0; i < getItemCount(); i++) {
            if (getItem(i).getMasterId() == masterId) {
                getItem(i).setIsCollection(isLike);
                mClickCollectId = masterId;
                notifyItemChanged(i);
                break;
            }
        }
    }

    public interface IAdapterListener {
        /**
         * 点击根部局
         */
        void onClickDivineRootView(MasterBean.DataBean.MasterListBean bean);

        /**
         * 收藏名师占卜
         *
         * @param bean      Bean
         */
        void onClickLikeDivine(MasterBean.DataBean.MasterListBean bean);
    }
}
