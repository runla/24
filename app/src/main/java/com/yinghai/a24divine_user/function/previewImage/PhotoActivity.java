package com.yinghai.a24divine_user.function.previewImage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.PictureBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片预览页面
 */
public class PhotoActivity extends FragmentActivity {
    private boolean isTransformOut = false;
    //图片的地址
    private ArrayList<PictureBean> imgUrls;
    //当前图片的位置
    private int currentIndex;
    //图片的展示的Fragment
    private List<PhotoFragment> fragments = new ArrayList<>();
    //展示图片的viewPager
    private PhotoViewPager viewPager;
    //显示图片数
    private TextView ltAddDot;
    //顶部的功能栏
    private RelativeLayout mRlHead;
    //回退按钮
    private ImageView mIvBack;
    //删除按钮
    private ImageView mIvDelete;
    //viewPager的适配器
    private PhotoPagerAdapter mAdapter;
    //ViewPager的监听
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    //ResultCode
    public static final int RESULT_CODE_PHOTO = 100;
    //是否需要顶部的功能栏
    public static String  IS_NEED_TOOLBAR ;

    /**
     * 外部启动 PotoActivity（带RequestCode）
     */
    public static void startPotoActivity(Activity activity, ArrayList<PictureBean> imageArray, int index, int requestCode) {
        Intent intent = new Intent(activity, PhotoActivity.class);
        intent.putParcelableArrayListExtra("imagePaths", imageArray);
        intent.putExtra("position", index);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imge_preview);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_FULLSCREEN);
        initDate();
        initView();
        initListener();
    }

    /**
     * 初始化数据
     */
    private void initDate() {
        imgUrls = getIntent().getParcelableArrayListExtra("imagePaths");
        currentIndex = getIntent().getIntExtra("position", -1);
        if (imgUrls != null) {
            for (int i = 0; i < imgUrls.size(); i++) {
                PhotoFragment fragment = new PhotoFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(PhotoFragment.KEY_PATH, imgUrls.get(i).getUrlPicture());
                bundle.putBoolean(PhotoFragment.KEY_TRANS_PHOTO, false);
                fragment.setArguments(bundle);
                fragments.add(fragment);
            }
        } else {
            finish();
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mRlHead = (RelativeLayout) findViewById(R.id.rl_preview_picture_head);
        mIvBack = (ImageView) findViewById(R.id.iv_product_back);
        mIvDelete = (ImageView) findViewById(R.id.iv_product_delete);
        viewPager = (PhotoViewPager) findViewById(R.id.viewPager);
        ltAddDot = (TextView) findViewById(R.id.ltAddDot);
        ltAddDot.setText(currentIndex + 1 + "/" + imgUrls.size());

        if (!getIntent().getBooleanExtra(IS_NEED_TOOLBAR, true)) {
            hideToolbar();
        }

        mAdapter = new PhotoPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //当被选中的时候设置小圆点和当前位置
                if (ltAddDot != null) {
                    ltAddDot.setText((position + 1) + "/" + fragments.size());
                }
                currentIndex = position;
//                viewPager.setCurrentItem(currentIndex, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

        viewPager.addOnPageChangeListener(mOnPageChangeListener);

        viewPager.setCurrentItem(currentIndex);

        viewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                viewPager.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                final PhotoFragment fragment = fragments.get(currentIndex);
                fragment.transformIn();
            }
        });
    }

    private void initListener() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit();
            }
        });

        mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragments.size() <= 1) {
                    transformOut();
                    return;
                }

                fragments.remove(currentIndex);
                imgUrls.remove(currentIndex);
                mAdapter.notifyDataSetChanged();
                if (0 < currentIndex) {
                    viewPager.setCurrentItem(currentIndex, true);
                    mOnPageChangeListener.onPageSelected(currentIndex);
                } else {
                    viewPager.setCurrentItem(0, true);
                    mOnPageChangeListener.onPageSelected(0);
                }
            }
        });
    }

    //退出预览的动画
    public void transformOut() {
        if (isTransformOut) {
            return;
        }
        isTransformOut = true;
        int currentItem = viewPager.getCurrentItem();
        if (currentItem < imgUrls.size()) {
            PhotoFragment fragment = fragments.get(currentItem);
            ltAddDot.setVisibility(View.GONE);
            fragment.changeBg(Color.TRANSPARENT);
            fragment.transformOut(new SmoothImageView.onTransformListener() {
                @Override
                public void onTransformCompleted(SmoothImageView.Status status) {
                    exit();
                }
            });
        } else {
            exit();
        }
    }

    /**
     * 关闭页面
     */
    private void exit() {
        Bundle mBundle = new Bundle();
        mBundle.putParcelableArrayList("imageUrls", imgUrls);
        Intent intent = new Intent();
        intent.putExtras(mBundle);
        this.setResult(RESULT_CODE_PHOTO, intent);
        this.finish();
        overridePendingTransition(0, 0);
    }

    /**
     * 点击图片时，会调用此方法
     * 显示或隐藏顶部功能栏
     */
    public void switchToolbar() {
        if (getIntent().getBooleanExtra(IS_NEED_TOOLBAR, true)) {
            if (mRlHead.isShown()) {
                mRlHead.setVisibility(View.GONE);
            } else {
                mRlHead.setVisibility(View.VISIBLE);
            }
        }else {
            finish();
        }
    }

    /**
     * 隐藏顶部功能栏
     */
    public void hideToolbar() {
        mRlHead.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
//        transformOut();
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (fragments != null) {
            fragments.clear();
            fragments = null;
        }
    }

    /**
     * pager的适配器
     */
    private class PhotoPagerAdapter extends FragmentStatePagerAdapter {

        PhotoPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }

}
