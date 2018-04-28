package io.rong.callkit.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import io.rong.callkit.R;
import io.rong.callkit.widget.SmoothImageView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ImageShowActivity extends Activity {
    private static final String TAG = "ImageShowActivity";
    private SmoothImageView mSmoothImageView;
    private ConstraintLayout mRootView;
    public static final int TYPE_URI = 1;
    public static final int TYPE_PATH = 2;
    public static void startActivity(Activity activity, Uri uri){
        Intent intent = new Intent(activity,ImageShowActivity.class);
        intent.putExtra("uri",uri);
        intent.putExtra("type",TYPE_URI);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.anim_fade_in,R.anim.anim_fade_out);
    }
    public static void startActivity(Activity activity, String path){
        Intent intent = new Intent(activity,ImageShowActivity.class);
        intent.putExtra("path",path);
        intent.putExtra("type",TYPE_PATH);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.anim_fade_in,R.anim.anim_fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_image_show);

        mSmoothImageView = (SmoothImageView) findViewById(R.id.photoView);
        mRootView = (ConstraintLayout) findViewById(R.id.rootView);
        initData();
        initSmoothImageView();
    }
    private void initData(){
        if (getIntent() == null) {
            return;
        }
        int type = getIntent().getIntExtra("type",1);
        if (type == TYPE_URI){
            Uri uri = getIntent().getParcelableExtra("uri");
            //加载缩略图
            //加载原图
            Glide.with(this)
                    .load(uri)
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.drawable.rc_image_default)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            mSmoothImageView.setImageBitmap(resource);
                        }

                        @Override
                        public void onLoadFailed(Exception e, Drawable errorDrawable) {
                            super.onLoadFailed(e, errorDrawable);
                            mSmoothImageView.setImageDrawable(errorDrawable);
                        }
                    });
        }else{
            String path = getIntent().getStringExtra("path");
            //加载缩略图
            //加载原图
            Glide.with(this)
                    .load(path)
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.drawable.rc_image_error)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            mSmoothImageView.setImageBitmap(resource);
                        }
                    });
        }
    }

    private void initSmoothImageView(){
        mSmoothImageView.setMinimumScale(1f);
        //单击预览图片时的回调
        mSmoothImageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                if (mSmoothImageView.checkMinScale()) {
                    finish();
                    overridePendingTransition(R.anim.anim_fade_out,R.anim.anim_fade_in);
                }
            }
        });

        mSmoothImageView.setTransformOutListener(new SmoothImageView.OnTransformOutListener() {
            @Override
            public void onTransformOut() {
                if (mSmoothImageView.checkMinScale()) {
                    finish();
                    overridePendingTransition(R.anim.fade_out,R.anim.fade_in);
                }
            }
        });

        mSmoothImageView.setAlphaChangeListener(new SmoothImageView.OnAlphaChangeListener() {
            @Override
            public void onAlphaChange(int alpha) {
                mRootView.setBackgroundColor(getColorWithAlpha(alpha / 255f, Color.BLACK));
            }
        });
    }

    public static int getColorWithAlpha(float alpha, int baseColor) {
        int a = Math.min(255, Math.max(0, (int) (alpha * 255))) << 24;
        int rgb = 0x00ffffff & baseColor;
        return a + rgb;
    }
}
