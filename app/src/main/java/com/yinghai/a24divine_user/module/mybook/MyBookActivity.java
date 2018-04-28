package com.yinghai.a24divine_user.module.mybook;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseActivity;
import com.yinghai.a24divine_user.callback.IBackFragmentListener;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/25 17:37
 *         Describe：我的预约订单Activity
 */

public class MyBookActivity extends MyBaseActivity implements IBackFragmentListener{

    private ImageView mBtnBack;
    private MyBookFragment mBookFragment;


    @Override
    protected int getContentView() {
        return R.layout.activity_book;
    }

    @Override
    protected void initView() {
        super.initView();
        mBtnBack = findMyViewId(R.id.btn_back_toolbar);
        initMyBookFragment();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void listenEvent() {
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyBookActivity.this.finish();
            }
        });
    }

    private void initMyBookFragment() {
        if (mBookFragment == null) {
            mBookFragment = new MyBookFragment();
        }
        replaceFragmentToStack(R.id.fl_book_main, mBookFragment, MyBookFragment.class.getSimpleName());
    }

    @Override
    public void currentFragmentBack(Fragment fragment) {
        mCurrentFragmentList.add(fragment) ;
    }
}

