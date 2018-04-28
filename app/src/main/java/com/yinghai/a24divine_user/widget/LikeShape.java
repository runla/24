package com.yinghai.a24divine_user.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.yinghai.a24divine_user.R;


public class LikeShape extends View{
    private static final String TAG = "LikeShape";
    public static final int DEFAULT_SIZE = 80;
    public static final int ANIMATION_TIME = 500;
    public static final int FILL = 0;
    public static final int STROCK = 1;
    private Paint mPaint;

    private int mUnSelectedColor;
    private int mSelectedColor;
    private int mCurrentColor;
    private String mShowText;
    private float mTextSize;
    private boolean mLike;
    private float likeSize = 0;
    private float textLocationY;
    private boolean isAnimationRun = false;
    private int textAlpha = 128;
    private int mNormalType;
    private int mStrockWidth;
    private boolean mStartAnimation = false;

    public LikeShape(Context context) {
        this(context,null);
    }

    public LikeShape(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LikeShape(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LikeShape);
        mUnSelectedColor = typedArray.getColor(R.styleable.LikeShape_like_unSelectColor,Color.GRAY);
        mSelectedColor = typedArray.getColor(R.styleable.LikeShape_like_selectColor,Color.RED);
        mLike = typedArray.getBoolean(R.styleable.LikeShape_like_selected,false);
        mTextSize = typedArray.getDimension(R.styleable.LikeShape_like_textSize,20);
        mShowText = typedArray.getString(R.styleable.LikeShape_like_text);
        mNormalType = typedArray.getInt(R.styleable.LikeShape_like_normalTyle,FILL);
        mStrockWidth = (int) typedArray.getDimension(R.styleable.LikeShape_like_strockWidth,dp2px(2));
        typedArray.recycle();
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        if (mNormalType == STROCK){
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(mStrockWidth);
            mUnSelectedColor = mSelectedColor;
            mCurrentColor = mSelectedColor;
        }

        if (mLike) {
            mPaint.setColor(mSelectedColor);
            mPaint.setStyle(Paint.Style.FILL);
            mCurrentColor = mSelectedColor;
        }else{
            mPaint.setColor(mUnSelectedColor);
            mCurrentColor = mUnSelectedColor;
        }

        mPaint.setTextSize(mTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = getViewWidth(widthMeasureSpec);
        int heightSize = getViewHeight(heightMeasureSpec);
        int size = widthSize > heightSize ? widthSize:heightSize;
        likeSize = (float) ((widthSize < heightSize ? widthSize:heightSize) / 1.2);
        setMeasuredDimension(size,size);
        if (mStartAnimation) {
            textLocationY = likeSize / 2;
            startAnimation();
            mStartAnimation = false;
        }
    }

    private int getViewHeight(int heightMeasureSpec){
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode != MeasureSpec.EXACTLY){
            heightSize = DEFAULT_SIZE;
        }
        return heightSize;
    }

    private int getViewWidth(int widthMeasureSpec){
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode != MeasureSpec.EXACTLY) {
            widthSize = DEFAULT_SIZE;
        }
        if (!TextUtils.isEmpty(mShowText)) {
            widthSize = widthSize > mPaint.measureText(mShowText)?widthSize:(int)mPaint.measureText(mShowText);
        }
        return widthSize;
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: "+likeSize);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        mPaint.setColor(mCurrentColor);
        Path path = new Path();
        RectF rectF = new RectF(centerX - likeSize / 2,
                centerY - likeSize / 2,
                centerX,
                centerY);
        path.addArc(rectF, -225, 225);

        RectF rectF1 = new RectF(centerX,
                centerY - likeSize / 2,
                centerX + likeSize / 2,
                centerY);
        path.arcTo(rectF1, -180, 225, false);
        path.lineTo(centerX, centerY + (likeSize * 2 / 5));
        path.close();
        canvas.drawPath(path, mPaint);

        if (!TextUtils.isEmpty(mShowText) && mLike && isAnimationRun) {
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setColor(Color.argb(textAlpha,Color.red(mCurrentColor),Color.green(mCurrentColor),Color.blue(mCurrentColor)));
            canvas.drawText(mShowText, centerX, textLocationY, mPaint);
        }

    }

    public float getLikeSize() {
        return likeSize;
    }

    private void setLikeSize(float likeSize) {
        this.likeSize = likeSize;
        invalidate();
    }

    public float getTextLocationY() {
        return textLocationY;
    }

    public void setTextLocationY(float textLocationY) {
        this.textLocationY = textLocationY;
    }

    public int getTextAlpha() {
        return textAlpha;
    }

    public void setTextAlpha(int textAlpha) {
        this.textAlpha = textAlpha;
    }

    public boolean isLike() {
        return mLike;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        post(new Runnable() {
            @Override
            public void run() {
                textLocationY = getHeight() / 2-5;
            }
        });
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (!selected) {
            mLike = selected;
            mPaint.setColor(mUnSelectedColor);
            mCurrentColor = mUnSelectedColor;
            if (mNormalType == STROCK){
                mPaint.setStyle(Paint.Style.STROKE);
            }
            invalidate();
        } else {
            mLike = selected;
            mPaint.setColor(mSelectedColor);
            mCurrentColor = mSelectedColor;
            mPaint.setStyle(Paint.Style.FILL);
            invalidate();
        }
    }


    public void startAnimation() {
        // 只有 likeSize 的值不为 0 的时候，才能开始动画，否则要等到 likeSize 计算完成才能开始
        if (likeSize == 0){
            mStartAnimation = true;
            return;
        }

        if (isAnimationRun) {
            return;
        }
        ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(this, "likeSize", likeSize, likeSize * 1.3f, likeSize);
        ObjectAnimator translateAnimator = ObjectAnimator.ofFloat(this,"textLocationY",textLocationY,5);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofInt(this,"textAlpha",128,1);

        AnimatorSet set = new AnimatorSet();
        set.play(scaleAnimator).with(translateAnimator).with(alphaAnimator);
        set.setDuration(800);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isAnimationRun = false;
                textLocationY = getHeight() / 2;
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                isAnimationRun = true;
            }
        });
        set.start();



    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getContext().getResources().getDisplayMetrics());
    }
}
