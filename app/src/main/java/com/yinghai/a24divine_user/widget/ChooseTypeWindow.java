package com.yinghai.a24divine_user.widget;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;

import com.example.fansonlib.base.BaseDataAdapter;
import com.example.fansonlib.base.BaseHolder;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.popupwindow.BasePopup;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.BusinessTypeListBean;
import com.yinghai.a24divine_user.module.divine.divinelist.model.ContractBusinessType;
import com.yinghai.a24divine_user.module.divine.divinelist.model.GetBusinessTypeModel;

/**
 * @author Created by：fanson
 *         Created Time: 2017/12/28 15:52
 *         Describe：帅选类型（文章，大师）的窗口
 */

public class ChooseTypeWindow extends BasePopup implements ContractBusinessType.IModel.ITypeCallback {

    private IChooseTypeListener mListener;
    private Context mContext;
    private BusinessTypeAdapter mAdapter;
    private GetBusinessTypeModel mGetBusinessTypeModel;

    private GetBusinessTypeModel getBusinessTypeModel() {
        if (mGetBusinessTypeModel == null) {
            mGetBusinessTypeModel = new GetBusinessTypeModel();
        }
        return mGetBusinessTypeModel;
    }

    public ChooseTypeWindow(Activity context, IChooseTypeListener listener) {
        super(context);
        mContext = context;
        mListener = listener;
        initView();
        initData();
        initListener();
    }

    private void initView() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        AutoLoadRecyclerView mRecyclerView = (AutoLoadRecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setOnPauseListenerParams(true, true);
        mAdapter = new BusinessTypeAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        getBusinessTypeModel().onGetBusinessType(this);
    }


    private void initListener() {
    }

    @Override
    protected Animation initShowAnimation() {
        // true:表示使用Animation的interpolator，false:则是使用自己的
        AnimationSet set = new AnimationSet(false);
        Animation anim = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setInterpolator(new OvershootInterpolator());
        anim.setDuration(350);
        set.addAnimation(anim);
        return set;
    }

    @Override
    protected Animation initExitAnimation() {
        AnimationSet set = new AnimationSet(false);
        Animation anim = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(300);
        set.addAnimation(anim);
        return set;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.window_choose_business_type);
    }

    @Override
    public View initAnimaView() {
        return getPopupWindowView().findViewById(R.id.popup_anima);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (mGetBusinessTypeModel != null) {
            mGetBusinessTypeModel.onDestroy();
        }
        removeListener();
    }

    /**
     * 移除监听
     */
    public void removeListener() {
        mListener = null;
    }

    @Override
    public void onGetBusinessTypeSuccess(BusinessTypeListBean.DataBean bean) {
        BusinessTypeListBean.DataBean.TfBusinessTypeListBean businessTypeListBean = new BusinessTypeListBean.DataBean.TfBusinessTypeListBean();
        businessTypeListBean.setTypeName(mContext.getString(R.string.all));
        businessTypeListBean.setBusinessTypeId(null);
        mAdapter.appendItem(businessTypeListBean);
        if (bean.getTfBusinessTypeList() != null && bean.getTfBusinessTypeList().size() > 0) {
            mAdapter.appendList(bean.getTfBusinessTypeList());
        }
        mListener.onLoadDataCompleted();
    }

    @Override
    public void onGetBusinessTypeFailure(String errorMsg) {
        ShowToast.singleShort(errorMsg);
    }

    /**
     * 帅选类型
     */
    public interface IChooseTypeListener {
        /**
         * 帅选类型的回调
         *
         * @param bean 结果
         */
        void onChooseType(BusinessTypeListBean.DataBean.TfBusinessTypeListBean bean);

        /**
         * 数据加载完毕，通知调用方可以show
         */
        void onLoadDataCompleted();
    }


    /*************适配器*************/
    private class BusinessTypeAdapter extends BaseDataAdapter<BusinessTypeListBean.DataBean.TfBusinessTypeListBean> {


        public BusinessTypeAdapter(Context context) {
            super(context);
        }

        @Override
        public int getLayoutRes(int i) {
            return R.layout.item_choose_business_type;
        }

        @Override
        public void bindCustomViewHolder(BaseHolder holder, final int position) {
            holder.setText(R.id.tv_business, getItem(position).getTypeName());
            holder.setOnClickListener(R.id.rootView, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onChooseType(getItem(position));
                }
            });
        }
    }
}
