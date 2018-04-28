package com.yinghai.a24divine_user.module.collect;

import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseActivity;
import com.yinghai.a24divine_user.callback.IBackFragmentListener;
import com.yinghai.a24divine_user.utils.MyStatusBarUtil;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/30 11:38
 *         Describe：我的收藏Activitiy
 */

public class MyCollectionActivity extends MyBaseActivity implements IBackFragmentListener {

    private ImageView mBtnBack;
    private MyCollectFragment mCollectFragment;

    @Override
    protected int getContentView() {
        return R.layout.activity_collect;
    }

    @Override
    protected void initView() {
        super.initView();
        mBtnBack = findMyViewId(R.id.btn_back_toolbar);
        initMyCollectFragment();
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
                MyCollectionActivity.this.finish();
                overridePendingTransition(R.anim.scale_in_from_left_bottom,R.anim.scale_out_to_left_bottom);
            }
        });
    }

    private void initMyCollectFragment() {
        if (mCollectFragment == null) {
            mCollectFragment = new MyCollectFragment();
        }
        replaceFragmentToStack(R.id.fl_collect_main, mCollectFragment, MyCollectFragment.class.getSimpleName());
    }

    @Override
    public void currentFragmentBack(Fragment fragment) {
        mCurrentFragmentList.add(fragment) ;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            this.finish();
            overridePendingTransition(R.anim.scale_in_from_left_bottom,R.anim.scale_out_to_left_bottom);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCurrentFragmentList!=null){
            mCurrentFragmentList.clear();
            mCurrentFragmentList = null;
        }
        mCollectFragment = null;
    }
}
