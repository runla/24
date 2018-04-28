package com.yinghai.a24divine_user.module.splash;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.fansonlib.image.ImageLoaderUtils;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.base.MyBaseActivity;
import com.yinghai.a24divine_user.module.main.MainActivity;
import com.yinghai.a24divine_user.service.CheckVersionService;
import com.yinghai.a24divine_user.utils.ClearImageUtils;
import com.yinghai.a24divine_user.utils.MyStatusBarUtil;

/**
 * @author Created by：fanson
 *         Created Time: 2017/12/12 18:13
 *         Describe：欢迎界面
 */

public class SplashActivity extends MyBaseActivity {

    private ImageView mIvBg;

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        super.initView();
        mIvBg = findMyViewId(R.id.iv_bg);
    }

    @Override
    protected void setStatus() {
        MyStatusBarUtil.setTransparent(this);
    }

    @Override
    protected void initData() {
        try {
            ImageLoaderUtils.loadImage(this,mIvBg,R.mipmap.ic_slash_bg);
        } catch (NullPointerException e) {
            e.printStackTrace();
            mIvBg.setImageDrawable(ContextCompat.getDrawable(this,R.mipmap.ic_slash_bg));
        }

        ScaleAnimation mAnimation = new ScaleAnimation(1f, 1.2f, 1f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_PARENT, 0.3f);
        mAnimation.setDuration(2 * 1000);
        //动画执行完，view不回到原位
        mAnimation.setFillAfter(true);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startService(new Intent( SplashActivity.this, CheckVersionService.class));
                startMyActivity(MainActivity.class);
                SplashActivity.this.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }



        });
        mIvBg.startAnimation(mAnimation);
    }

    @Override
    protected void listenEvent() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ClearImageUtils.recycleBackgroundBitmap(mIvBg);
    }
}
