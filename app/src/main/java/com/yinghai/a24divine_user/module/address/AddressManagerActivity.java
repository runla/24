package com.yinghai.a24divine_user.module.address;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.fansonlib.widget.dialogfragment.DoubleDialog;
import com.example.fansonlib.widget.dialogfragment.base.IConfirmListener;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.SampleApplicationLike;
import com.yinghai.a24divine_user.base.MyBaseActivity;
import com.yinghai.a24divine_user.bean.AddressListBean;
import com.yinghai.a24divine_user.callback.IBackFragmentListener;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.constant.ConstAdapter;
import com.yinghai.a24divine_user.module.address.edit.EditAddressFragment;
import com.yinghai.a24divine_user.module.address.mylist.MyAddressFragment;
import com.yinghai.a24divine_user.utils.MyStatusBarUtil;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/16 18:02
 *         Describe：地址管理
 */

public class AddressManagerActivity extends MyBaseActivity implements IBackFragmentListener {

    private ImageView mBtnBack;
    private MyAddressFragment mMyAddressFragment;
    private EditAddressFragment mEditAddressFragment;
    public static final String ADDRESSID_KEY = "AddressId";
    public static final int RESULT_ADDRESS = 922;

    @Override
    protected int getContentView() {
        return R.layout.activity_address;
    }

    @Override
    protected void initView() {
        super.initView();
        mTvTitle = findMyViewId(R.id.tv_title_toolbar);
        mBtnBack = findMyViewId(R.id.btn_back_toolbar);
        initMyAddressFragment();
    }

    @Override
    protected void initData() {
        MyStatusBarUtil.setDrawableStatus(this, R.drawable.shape_toolbar_bg);
    }

    @Override
    protected void listenEvent() {
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressManagerActivity.this.finish();
                overridePendingTransition(R.anim.scale_in_from_right_bottom, R.anim.scale_out_to_right_bottom);
            }
        });
    }

    private void initMyAddressFragment() {
        if (mMyAddressFragment == null) {
            mMyAddressFragment = new MyAddressFragment();
        }
        replaceFragmentToStack(R.id.fl_main, mMyAddressFragment, MyAddressFragment.class.getSimpleName());
    }

    private void switchEditAddressFragment(AddressListBean.DataBean.AddressBean bean) {
        mEditAddressFragment = EditAddressFragment.newInstance(bean);
        switchFragment(R.id.fl_main, mMyAddressFragment, mEditAddressFragment);
    }


    @Override
    public void onMultiFragment(final Object... object) {
        super.onMultiFragment(object);
        switch ((Integer) object[0]) {
            case ConFragment.OPEN_EDIT_ADDRESS:
                if (object.length > 1) {
                    switchEditAddressFragment((AddressListBean.DataBean.AddressBean) object[1]);
                } else {
                    switchEditAddressFragment(null);
                }
                break;

            case ConstAdapter.RETURN_ADDRESS:
                if (SampleApplicationLike.isShopToAddress){
                    DoubleDialog.newInstance(getString(R.string.confirm_address_is_right)).setConfirmListener(new IConfirmListener() {
                        @Override
                        public void onConfirm() {
                            SampleApplicationLike.isShopToAddress = false;
                            Intent intent = new Intent();
                            intent.putExtra(ADDRESSID_KEY, (Integer) object[1]);
                            setResult(RESULT_ADDRESS, intent);
                            AddressManagerActivity.this.finish();
                        }
                    }).show(getSupportFragmentManager());
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void currentFragmentBack(Fragment fragment) {
        mCurrentFragmentList.add(fragment);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();  //finish当前activity
            overridePendingTransition(R.anim.scale_in_from_right_bottom, R.anim.scale_out_to_right_bottom);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMyAddressFragment = null;
        mEditAddressFragment = null;
    }

}
