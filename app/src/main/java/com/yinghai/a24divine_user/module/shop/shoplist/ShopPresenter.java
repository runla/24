package com.yinghai.a24divine_user.module.shop.shoplist;

import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.ProductBean;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/26 10:47
 *         Describe：获取商城商品的P层实现
 */

public class ShopPresenter extends MyBasePresenter<ShopModel,ContractShop.IGetAllProductView> implements ContractShop.IGetAllProductPresenter,
        ContractShop.IGetAllProductModel.IGetAllProductCallback {

    private int mPageNum = 1;

    /**
     * 暂存页，用途：当加载到某页时，突然搜索数据，然后去除搜索，恢复搜索前的数据页码
     */
    private int mTempPageNum;

    public ShopPresenter(ContractShop.IGetAllProductView view){
        attachView(view);
    }

    @Override
    protected ShopModel createModel() {
        return new ShopModel();
    }

    @Override
    public void onGetAllProduct(String productName) {
        mBaseModel.getAllProduct(mPageNum,productName,this);
    }

    @Override
    public void onGetAllProductSuccess(ProductBean bean) {
        if (isViewAttached()){
            getBaseView().showAllProductSuccess(bean);
            mPageNum++;
        }
    }

    @Override
    public void onGetAllProductFailure(String errorMsg) {
        if (isViewAttached()){
            getBaseView().showAllProductFailure(errorMsg);
        }
    }

    /**
     * 上拉刷新时，需要重置页码
     */
    public void resetPage() {
        mPageNum = 1;
    }

    /**
     * 进行搜索，保存暂存的页码
     */
    public void saveTempPageNum(){
        mTempPageNum = mPageNum;
    }

    /**
     * 恢复暂存的页码
     */
    public void setTempPageNum(){
        mPageNum = mTempPageNum;
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }
}
