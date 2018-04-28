package com.yinghai.a24divine_user.module.history;

import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseActivity;
import com.yinghai.a24divine_user.callback.IBackFragmentListener;
import com.yinghai.a24divine_user.utils.MyStatusBarUtil;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/30 12:00
 *         Describe：历史记录Activity
 */

public class HistoryActivity extends MyBaseActivity implements IBackFragmentListener {

    private ImageView mBtnBack;
    private HistoryFragment mHistoryFragment;
    private RelativeLayout mRlToolbar;

    @Override
    protected int getContentView() {
        return R.layout.activity_history;
    }

    @Override
    protected void initView() {
        super.initView();
        mBtnBack = findMyViewId(R.id.btn_back_toolbar);
        mRlToolbar = findMyViewId(R.id.rl_toolbar);
        initHistoryFragment();
        MyStatusBarUtil.setTranslucentForImageView(this,5,null);
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
                HistoryActivity.this.finish();
                overridePendingTransition(R.anim.scale_in_from_left_bottom,R.anim.scale_out_to_left_bottom);
            }
        });
    }

    private void initHistoryFragment() {
        if (mHistoryFragment == null) {
            mHistoryFragment = new HistoryFragment();
        }
        replaceFragmentToStack(R.id.fl_history_main, mHistoryFragment, HistoryFragment.class.getSimpleName());
    }

    @Override
    public void currentFragmentBack(Fragment fragment) {
        mCurrentFragmentList.add(fragment) ;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            this.finish();  //finish当前activity
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
    }
}
