package com.yinghai.a24divine_user.module.shop.shopcar.mvp;

import com.example.fansonlib.base.AppUtils;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBasePresenter;
import com.yinghai.a24divine_user.bean.NoDataBean;
import com.yinghai.a24divine_user.bean.ShopCarBean;
import com.yinghai.a24divine_user.bean.SubmitShopCarBean;
import com.yinghai.a24divine_user.module.shop.details.addtocar.AddToCarModel;
import com.yinghai.a24divine_user.module.shop.details.addtocar.ContractAddToCar;
import com.yinghai.a24divine_user.module.shop.shopcar.submit.ContractSubmitShopCar;
import com.yinghai.a24divine_user.module.shop.shopcar.submit.SubmitShopCarModel;

import java.util.List;

/**
* @author Created by：fanson
* Created on：2017/11/13 15:41
* Description：购物车的P层
*/

public class ShopCarPresenter extends MyBasePresenter<ShopCarModel, ContractShopCar.IShopCarView> implements ContractShopCar.IShopCarPresenter, ContractShopCar.IShopCarModel.IShopCarCallback, ContractSubmitShopCar.ISubmitModel.ISubmitCallback, ContractSubmitShopCar.ISubmitModel.IDeleteCallback, ContractAddToCar.IAddToCarModel.IAddToCarCallback {

    private int mPageNum = 1;
    private SubmitShopCarModel mSubmitShopCarModel;
    private AddToCarModel mAddToCarModel;

    public ShopCarPresenter(ContractShopCar.IShopCarView view) {
        attachView(view);
    }

    private SubmitShopCarModel getSubmitShopCarModel(){
        if (mSubmitShopCarModel==null){
            mSubmitShopCarModel = new SubmitShopCarModel();
        }
        return mSubmitShopCarModel;
    }

    private AddToCarModel getAddToCarModel()
    {
        if (mAddToCarModel==null){
            mAddToCarModel = new AddToCarModel();
        }
        return mAddToCarModel;
    }

    @Override
    protected ShopCarModel createModel() {
        return new ShopCarModel();
    }

    @Override
    public void onShopCar() {
        mBaseModel.getShopCarSuccess(mPageNum,this);
    }

    @Override
    public void addCar(int productId) {
        getAddToCarModel().addToCar(productId,1,this);
    }

    @Override
    public void onShopCarSuccess(ShopCarBean bean) {
        if (isViewAttached()) {
            getBaseView().showShopCarSuccess(bean);
            mPageNum++;
        }
    }

    @Override
    public void onShopCarFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().showShopCarFailure(errorMsg);
        }
    }

    @Override
    public void onSubmitShopCar(List<ShopCarBean.DataBean> shopCarBeanlist,int addressId) {
        if (shopCarBeanlist == null || shopCarBeanlist.isEmpty()) {
            getBaseView().showShopCarFailure(AppUtils.getAppContext().getString(R.string.have_not_select));
            return;
        }
        getSubmitShopCarModel().submitShopCar(beanListToCarIdString(shopCarBeanlist),beanListToAmount(shopCarBeanlist),addressId,this);
    }

    @Override
    public void onDeleteShopCar(int carId) {
        getSubmitShopCarModel().deleteShopCar(carId,this);
    }

    @Override
    public void onEditShopCar() {

    }

    /**
     * 从List中遍历拼接CarIds
     * @param shopCarBeanlist
     * @return CarIds
     */
    private String beanListToCarIdString(List<ShopCarBean.DataBean> shopCarBeanlist){
        StringBuilder stringBuilder = new StringBuilder();
        for (ShopCarBean.DataBean bean: shopCarBeanlist){
            stringBuilder.append(bean.getCarId()).append(";");
        }
        return stringBuilder.toString();
    }

    /**
     * 从List中遍历拼接商品数量Amount
     * @param shopCarBeanlist
     * @return CarIds
     */
    private String beanListToAmount(List<ShopCarBean.DataBean> shopCarBeanlist){
        StringBuilder stringBuilder = new StringBuilder();
        for (ShopCarBean.DataBean bean: shopCarBeanlist){
            stringBuilder.append(bean.getCQty()).append(";");
        }
        return stringBuilder.toString();
    }

    @Override
    public void onSubmitSuccess(SubmitShopCarBean bean) {
        if (isViewAttached()) {
            getBaseView().showSubmitSuccess(bean);
        }
    }

    @Override
    public void onSubmitFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().showShopCarFailure(errorMsg);
        }
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    @Override
    public void onDeleteSuccess(int carId, NoDataBean bean) {
        if (isViewAttached()) {
            getBaseView().showDeleteSuccess(carId,bean);
        }
    }

    @Override
    public void onDeleteFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().showDeleteFailure(errorMsg);
        }
    }

    @Override
    public void onAddToCarSuccess(NoDataBean bean) {
        if (isViewAttached()) {
            getBaseView().showAddCarSuccess();
        }
    }

    @Override
    public void onAddToCarFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().showAddCarFailure(errorMsg);
        }
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubmitShopCarModel!=null){
            mSubmitShopCarModel.onDestroy();
            mSubmitShopCarModel = null;
        }
        if (mAddToCarModel!=null){
            mAddToCarModel.onDestroy();
            mAddToCarModel = null;
        }
    }

    /**
     * 初始化页码
     */
    public void resetPage(){
        mPageNum = 1;
    }
}

