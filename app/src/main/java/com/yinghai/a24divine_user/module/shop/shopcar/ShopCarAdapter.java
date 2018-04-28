package com.yinghai.a24divine_user.module.shop.shopcar;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.fansonlib.base.AppUtils;
import com.example.fansonlib.base.BaseDataAdapter;
import com.example.fansonlib.base.BaseHolder;
import com.example.fansonlib.image.ImageLoaderUtils;
import com.example.fansonlib.utils.ShowToast;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.ShopCarBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConstAdapter;
import com.yinghai.a24divine_user.widget.ShopAmountView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by：fanson
 *         Created on：2017/11/13 16:17
 *         Description：购物车的适配器
 */

public class ShopCarAdapter extends BaseDataAdapter<ShopCarBean.DataBean> {

    private static final String TAG = ShopCarAdapter.class.getSimpleName();
    /**
     * 选择了的商品List
     */
    private List<ShopCarBean.DataBean> mSelectedShopBeanList;

    private OnAdapterListener mOnAdapterListener;

    public void setOnAdapterListener(OnAdapterListener listener) {
        mOnAdapterListener = listener;
    }

    public ShopCarAdapter(Context context) {
        super(context);
        mSelectedShopBeanList = new ArrayList<>();
    }

    @Override
    public int getLayoutRes(int i) {
        return R.layout.item_shop_edit;
    }

    @Override
    public void bindCustomViewHolder(final BaseHolder baseHolder, final int position) {
        if (getItem(position).getProduct() == null) {
            return;
        }
        final ShopAmountView shopAmountView = baseHolder.getView(R.id.shop_amount);
        baseHolder.setText(R.id.tv_product_name, getItem(position).getProduct().getPName());
        baseHolder.setText(R.id.tv_shop_address, getItem(position).getProduct().getPAttribution());
        baseHolder.setText(R.id.tv_product_price, String.format(context.getString(R.string.money_unit), getItem(position).getProduct().getPPrice() / 100));
        if (getItem(position).getProduct().isPFreeShipping()) {
            baseHolder.getView(R.id.iv_shop_package).setVisibility(View.VISIBLE);
        } else {
            baseHolder.getView(R.id.iv_shop_package).setVisibility(View.GONE);
        }

        if (getItem(position).getProduct().getImgList().size() > 0) {
            ImageLoaderUtils.loadImage(AppUtils.getAppContext().getApplicationContext(), (ImageView) baseHolder.getView(R.id.iv_product),
                    ConHttp.BASE_URL + getItem(position).getProduct().getImgList().get(0).getItUrl());
        }

        final ImageView dotSelect = baseHolder.getView(R.id.dot_select);
        if (mSelectedShopBeanList.contains(getItem(position))) {
            dotSelect.setSelected(true);
        } else {
            dotSelect.setSelected(false);
        }

        final int goodStorageCount = getItem(position).getProduct().getPTotal();

        // 进行库存判断
        if (goodStorageCount <= 0) {
            baseHolder.setVisible(R.id.shop_has_over, true);
            baseHolder.setVisible(R.id.shop_amount, false);
        } else {
            baseHolder.setVisible(R.id.shop_has_over, false);
            baseHolder.setVisible(R.id.shop_amount, true);
            shopAmountView.setGoodStorage(goodStorageCount);
            shopAmountView.setAmount(getItem(position).getCQty());

            //增减数量的监听
            shopAmountView.setOnAmountChangeListener(new ShopAmountView.OnAmountChangeListener() {
                @Override
                public void onAmountChange(View view, int amount) {
                    if (getItem(position) != null) {
                        getItem(position).setCQty(amount);
                        for (ShopCarBean.DataBean bean : mSelectedShopBeanList) {
                            if (bean == getItem(position)) {
                                bean.setCQty(amount);
                            }
                        }
                        calTotalFee();
                    }
                }
            });
        }

        //选中某个商品item
        dotSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (goodStorageCount <= 0) {
                    ShowToast.singleLong(context.getString(R.string.shop_has_over));
                    return;
                }
                boolean isContains = false;
                for (ShopCarBean.DataBean bean : mSelectedShopBeanList) {
                    if (bean == getItem(position)) {
                        mSelectedShopBeanList.remove(getItem(position));
                        dotSelect.setSelected(false);
                        isContains = true;
                        break;
                    }
                }
                if (!isContains) {
                    mSelectedShopBeanList.add(getItem(position));
                    dotSelect.setSelected(true);
                }
                calTotalFee();
            }
        });

        baseHolder.setOnClickListener(R.id.iv_del, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAdapterListener.clickItem(ConstAdapter.DELETE_SHOP_CAR, getItem(position).getCarId());
            }
        });
    }

    /**
     * 计算总费用
     */
    private void calTotalFee() {
        int mTotalFee = 0;
        for (int i = 0; i < mSelectedShopBeanList.size(); i++) {
            mTotalFee = mTotalFee + mSelectedShopBeanList.get(i).getCQty() * (mSelectedShopBeanList.get(i).getProduct().getPPrice() / 100);
        }
        mOnAdapterListener.clickItem(ConstAdapter.SHOP_CAR_FEE, mTotalFee);
    }

    /**
     * 选择全部
     *
     * @param isSelectAll
     */
    public void selectAll(boolean isSelectAll) {
        if (isSelectAll) {
            mSelectedShopBeanList.clear();
            for (ShopCarBean.DataBean bean : mDataList) {
                if (bean.getProduct().getPTotal() > 0) {
                    mSelectedShopBeanList.add(bean);
                }
            }
        } else {
            mSelectedShopBeanList.clear();
        }
        calTotalFee();
        this.notifyDataSetChanged();
    }

    /**
     * 根据判断carid，进行删除
     *
     * @param carId
     */
    @Override
    public void removeItem(int carId) {
        for (int i = 0; i < mDataList.size(); i++) {
            if (mDataList.get(i).getCarId() == carId) {
                mSelectedShopBeanList.remove(mDataList.get(i));
                mDataList.remove(i);
                this.notifyItemRemoved(i);
                this.notifyItemRangeChanged(i, this.mDataList.size() - i);
                calTotalFee();
                return;
            }
        }
    }

    /**
     * 获取被选中的项
     *
     * @return 所有勾选的商品
     */
    public List<ShopCarBean.DataBean> getSelectedShopList() {
        return mSelectedShopBeanList;
    }

}
