package com.yinghai.a24divine_user.module.follow;

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
 *         Created Time: 2017/10/25 15:49
 *         Describe：我关注的Activity
 */

public class MyFollowActivity extends MyBaseActivity implements IBackFragmentListener{

    private ImageView mBtnBack;
    private MyFollowFragment mFollowFragment;

    @Override
    protected int getContentView() {
        return R.layout.activity_follow;
    }

    @Override
    protected void initView() {
        super.initView();
        mTvTitle = findMyViewId(R.id.tv_title_toolbar);
        mBtnBack = findMyViewId(R.id.btn_back_toolbar);
        initMyFollowFragment();
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
                MyFollowActivity.this.finish();
                overridePendingTransition(R.anim.scale_in_from_right_bottom,R.anim.scale_out_to_right_bottom);
            }
        });
    }

    private void initMyFollowFragment() {
        if (mFollowFragment == null) {
            mFollowFragment = new MyFollowFragment();
        }
        replaceFragment(R.id.fl_follow_main, mFollowFragment);
    }

    @Override
    public void currentFragmentBack(Fragment fragment) {
        mCurrentFragmentList.add(fragment) ;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            this.finish();
            overridePendingTransition(R.anim.scale_in_from_right_bottom,R.anim.scale_out_to_right_bottom);
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
        mFollowFragment = null;
    }
}
