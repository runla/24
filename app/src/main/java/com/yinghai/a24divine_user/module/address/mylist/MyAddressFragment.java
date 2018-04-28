package com.yinghai.a24divine_user.module.address.mylist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.fansonlib.callback.LoadMoreListener;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.dialogfragment.DoubleDialog;
import com.example.fansonlib.widget.dialogfragment.base.IConfirmListener;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.BaseMvpSwipeFragment;
import com.yinghai.a24divine_user.bean.AddressListBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.constant.ConstAdapter;
import com.yinghai.a24divine_user.utils.LogUtils;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/17 11:27
 *         Describe：地址列表
 */

public class MyAddressFragment extends BaseMvpSwipeFragment<GetAddressPresenter> implements ContractMyAddress.IView, LoadMoreListener, OnAdapterListener {

    private static final String TAG  = MyAddressFragment.class.getSimpleName();
    private boolean mIsPull = false; //标识是否上拉刷新
    private AutoLoadRecyclerView mRecyclerView;
    private MyAddressAdapter mAdapter;
    private FloatingActionButton mFabAddAdress;

    @Override
    protected void initToolbarTitle() {
        setToolbarTitle(getString(R.string.my_address));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_address;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        mFabAddAdress = findMyViewId(R.id.fab_add_address);
        initRecyclerView();
        return rootView;
    }

    private void initRecyclerView() {
        mRecyclerView = findMyViewId(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(hostActivity));
        mRecyclerView.setOnPauseListenerParams(true, true);
        mRecyclerView.setLoadMoreListener(this);
        mAdapter = new MyAddressAdapter(hostActivity);
        mAdapter.setOnAdapterListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        showLoading();
        getAddressList();
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mFabAddAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMultiFragmentListener.onMultiFragment(ConFragment.OPEN_EDIT_ADDRESS);
            }
        });

        //滚动监听，隐藏发布按钮，防止按钮遮挡住“删除”的按钮
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case 0:
                        LogUtils.d(TAG, "recyclerview已经停止滚动");
                        isSlideToBottom(mRecyclerView);
                        break;
                    case 1:
                        LogUtils.d(TAG, "recyclerview正在被拖拽");
                        break;
                    case 2:
                        LogUtils.d(TAG, "recyclerview正在依靠惯性滚动");
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 判断Recyclerview是否滑到底部
     *
     * @param recyclerView recyclerView
     * @return true or false
     */
    public boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) {
            mFabAddAdress.setVisibility(View.VISIBLE);
            return false;
        }

        if (recyclerView.computeVerticalScrollOffset() != 0 && (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())) {
            mFabAddAdress.setVisibility(View.GONE);
            return true;
        }
        mFabAddAdress.setVisibility(View.VISIBLE);
        return false;
    }

    private void getAddressList() {
        mPresenter.onGetAddress();
    }

    @Override
    protected GetAddressPresenter createPresenter() {
        return new GetAddressPresenter(this);
    }

    @Override
    public void showGetAddressSuccess(AddressListBean bean) {
        if (mIsPull) {
            mAdapter.clearList();
            mIsPull = false;
        }
        mRecyclerView.loadFinish(null);
        if (bean.getData().getAddress() != null && bean.getData().getAddress().size() > 0) {
            mAdapter.appendList(bean.getData().getAddress());
        }
        if (mAdapter.getItemCount() == 0) {
            showNoDataLayout();
        }
        stopRefresh();
        hideLoading();
    }

    @Override
    public void showGetAddressFailure(String errorMsg) {
        ShowToast.singleLong(errorMsg);
        stopRefresh();
        hideLoading();
    }

    @Override
    public void loadMore() {
        getAddressList();
    }

    @Override
    protected void pullRefresh() {
        mIsPull = true;
        mPresenter.resetPage();
        getAddressList();
    }

    @Override
    public void clickItem(final Object... object) {
        switch ((Integer) object[0]) {
            case ConstAdapter.DELETE_ADDRESS:
                DoubleDialog.newInstance(getString(R.string.sure_delete)).setConfirmListener(new IConfirmListener() {
                    @Override
                    public void onConfirm() {
                        showLoading();
                        mPresenter.onDeleteAddress((Integer) object[1]);
                    }
                }).show(getFragmentManager());
                break;

            case ConstAdapter.OPEN_EDIT_ADDRESS:
                mMultiFragmentListener.onMultiFragment(ConFragment.OPEN_EDIT_ADDRESS, object[1]);
                break;

            case ConstAdapter.RETURN_ADDRESS:
                mMultiFragmentListener.onMultiFragment(ConstAdapter.RETURN_ADDRESS,object[1]);
                break;

            default:
                break;
        }
    }

    @Override
    public void showDeleteSuccess(AddressListBean bean) {
        if (bean.getData().getAddress() != null && bean.getData().getAddress().size() > 0) {
            mAdapter.removeItem(bean.getData().getAddress().get(0));
            ShowToast.singleShort(getString(R.string.delete_success));
        }
        hideLoading();
    }

    @Override
    public void showDeleteFailure(String errorMsg) {
        ShowToast.singleShort(errorMsg);
        hideLoading();
    }
}
