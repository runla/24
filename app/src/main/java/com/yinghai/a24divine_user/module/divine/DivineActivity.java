package com.yinghai.a24divine_user.module.divine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;

import com.example.fansonlib.utils.ShowToast;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseActivity;
import com.yinghai.a24divine_user.bean.BusinessBean;
import com.yinghai.a24divine_user.callback.IBackFragmentListener;
import com.yinghai.a24divine_user.callback.OnMultiFragmentListener;
import com.yinghai.a24divine_user.constant.ConFragment;
import com.yinghai.a24divine_user.module.divine.book.BusinessListFragment;
import com.yinghai.a24divine_user.module.divine.book.pay.DivineBookPayFragment;
import com.yinghai.a24divine_user.module.divine.divinelist.DivineFragment;
import com.yinghai.a24divine_user.module.divine.index.MasterIndexFragment;
import com.yinghai.a24divine_user.module.divine.selecttime.SelectTimeFragment;
import com.yinghai.a24divine_user.module.divine.userinfo.UserInfoFragment;
import com.yinghai.a24divine_user.utils.MyStatusBarUtil;

/**
 * Created by chenjianrun on 2017/10/27.
 * 描述：预约占卜师的的主界面，含有的 fragment 有：MasterIndexFragment、DivineBookModel
 */

public class DivineActivity extends MyBaseActivity implements IBackFragmentListener, OnMultiFragmentListener, BusinessListFragment.OnDivineBookListener {
    /**
     * 主界面点击名师占卜进来的Type，此时显示名师列表
     */
    public static final int LIST_TYPE = 0;
    /**
     * 主界面点击推荐名师进来的Type，此时显示此名师的详情
     */
    public static final int MASTER_INDEX_TYPE = 1;

    /*记录下单需要的数据*/
    public int mMasterId;
    public int mBusinessId;
    public String mMasterName;
    public String mBusinessName;
    public int mPrice;
    public String mAppoitment;
    private MasterIndexFragment mMasterIndexFragment;
    private BusinessListFragment mBusinessListFragment;
    private SelectTimeFragment mSelectTimeFragment;
    private DivineFragment mDivineFragment;
    private UserInfoFragment mUserInfoFragment;
    private DivineBookPayFragment mDivineBookPayFragment;
    private static final String PARAM_MASTER_ID = "MasterId";

    /**
     * 打开占卜界面
     *
     * @param activity
     * @param type     0：列表，1：详情
     * @param masterId     大师ID
     */
    public static void startDivineActivity(Activity activity, int type, int masterId) {
        Intent intent = new Intent(activity, DivineActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("TYPE", type);
        if (masterId != 0) {
            bundle.putInt(PARAM_MASTER_ID, masterId);
        }
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.scale_in_from_center, R.anim.scale_out);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_divine;
    }

    @Override
    protected void initView() {
        super.initView();
        init();
    }

    private void init() {
        int mType = LIST_TYPE;
        if (getIntent() != null) {
            mType = getIntent().getIntExtra("TYPE", LIST_TYPE);
        }
        switch (mType) {
            case LIST_TYPE:
                initDivineListFragment();
                break;

            case MASTER_INDEX_TYPE:
                initMasterIndexFragment(getIntent().getIntExtra(PARAM_MASTER_ID,0));
                break;
            default:
                break;
        }
    }

    /**
     * 初始化查看所有占卜师列表
     */
    private void initDivineListFragment() {
        if (mDivineFragment == null) {
            mDivineFragment = new DivineFragment();
        }
        replaceFragmentToStack(R.id.fl_divine_main, mDivineFragment, DivineFragment.class.getSimpleName());
    }

    /**
     * 初始化占卜师详情（可以预约服务的）
     */
    private void initMasterIndexFragment(int masterId) {
        Bundle bundle = new Bundle();
        bundle.putInt(PARAM_MASTER_ID, masterId);
        mMasterIndexFragment = MasterIndexFragment.newInstance(bundle);
        replaceFragmentToStack(R.id.fl_divine_main, mMasterIndexFragment, MasterIndexFragment.class.getSimpleName());
    }

    private void switchToMasterIndexFragment(int masterId) {
        Bundle bundle = new Bundle();
        bundle.putInt(PARAM_MASTER_ID, masterId);
        mMasterIndexFragment = MasterIndexFragment.newInstance(bundle);
        switchFragmentWithTag(R.id.fl_divine_main, getCurrentFragment(), mMasterIndexFragment, MasterIndexFragment.class.getSimpleName());
    }

    private void switchToBusinessListFragment(BusinessBean.DataBean bean) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("BEAN", bean);
        mBusinessListFragment = BusinessListFragment.newInstance(bundle);
        mBusinessListFragment.setmOnDivineBookListener(this);
        switchFragmentWithAnim(R.id.fl_divine_main, getCurrentFragment(), mBusinessListFragment, R.anim.slide_from_top, R.anim.slide_to_bottom);
    }

    private void switchSelectTimeFragment(BusinessBean.DataBean bean) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("BEAN", bean);
        mSelectTimeFragment = SelectTimeFragment.newInstance(bundle);
        switchFragmentWithAnim(R.id.fl_divine_main, getCurrentFragment(), mSelectTimeFragment, R.anim.slide_from_top, R.anim.slide_to_bottom);
    }

    /**
     * 切换至支付界面
     *
     * @param bundle
     */

    private void switchToDivineBookPayFragment(Bundle bundle) {
        mDivineBookPayFragment = DivineBookPayFragment.newInstance(bundle);
        switchFragmentWithTag(R.id.fl_divine_main, getCurrentFragment(), mDivineBookPayFragment, DivineBookPayFragment.class.getSimpleName());
    }

    @Override
    protected void initData() {
        MyStatusBarUtil.setDrawableStatus(this, R.drawable.shape_toolbar_bg);
    }

    @Override
    protected void listenEvent() {

    }

    @Override
    public void currentFragmentBack(Fragment fragment) {
        mCurrentFragmentList.add(fragment);
    }


    @Override
    public void onMultiFragment(Object... object) {
        super.onMultiFragment(object);
        switch ((int) object[0]) {
            case ConFragment.OPEN_SELECT_BOOK_TIME:
                switchSelectTimeFragment((BusinessBean.DataBean) object[1]);
                break;
            case ConFragment.OPEN_MASTER_DIVINE:
                switchToMasterIndexFragment((int) object[1]);
                break;
            case ConFragment.OPEN_DIVINE_TYPE:
                switchToBusinessListFragment((BusinessBean.DataBean) object[1]);
                break;
            case ConFragment.OPEN_DIVINE_BOOK_USER_PAY:
                switchToDivineBookPayFragment((Bundle) object[1]);
                break;
            case ConFragment.DIVINE_PAY_SUCCESS:
                ShowToast.singleLong(getString(R.string.pay_success_to_order_see));
                DivineActivity.this.finish();
                break;
            default:
                break;
        }
    }


    /**
     * 切换至填写预约的用户信息界面
     *
     * @param bean 占卜类型的数据
     */
    @Override
    public void openInputUserInfoFragment(BusinessBean.DataBean.TfBusinessListBean bean) {
        mBusinessId = bean.getBusinessId();
        mBusinessName = bean.getBName();
        mPrice = bean.getBPrice();
        if (getCurrentFragment() == mUserInfoFragment) {
            return;
        }
        if (mUserInfoFragment == null) {
            mUserInfoFragment = new UserInfoFragment();
        }
        switchFragmentWithTag(R.id.fl_divine_main, getCurrentFragment(), mUserInfoFragment, UserInfoFragment.class.getSimpleName());
    }

    @Override
    public void onBackPressed() {
       if (getSupportFragmentManager().getBackStackEntryCount()==1){
            this.finish();
           overridePendingTransition(R.anim.bottom_in, R.anim.scale_out_from_center);
        }else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCurrentFragmentList != null) {
            mCurrentFragmentList.clear();
            mCurrentFragmentList = null;
        }
        mUserInfoFragment = null;
        mDivineFragment = null;
        mBusinessListFragment = null;
        mDivineBookPayFragment = null;
        mMasterIndexFragment = null;
        mSelectTimeFragment = null;
    }

}
