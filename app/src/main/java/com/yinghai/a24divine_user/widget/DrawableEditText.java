package com.yinghai.a24divine_user.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.Button;

import com.yinghai.a24divine_user.R;

/**
 * Created by chenjianrun on 2017/12/18.
 * 描述：可以设置 drawableLeft、drawableRight、drawableTop、drawableBottom 可以设置大小
 */

public class DrawableEditText extends ClearWriteEditText {
    private int mDrawableLeftSize;
    private int mDrawableRightSize;
    private int mDrawableTopSize;
    private int mDrawableBottomSize;

    private Drawable mDrawableLeft;
    private Drawable mDrawableRight;
    private Drawable mDrawableTop;
    private Drawable mDrawableBottom;
    public DrawableEditText(Context context) {
        this(context, null);
    }

    public DrawableEditText(Context context, AttributeSet attrs) {
        //这里构造方法也很重要，不加这个很多属性不能再XML里面定义
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public DrawableEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DrawableEditTextStyle, defStyleAttr, 0);
        mDrawableLeft = typedArray.getDrawable(R.styleable.DrawableEditTextStyle_myDrawableLeft);
        if (mDrawableLeft != null) {
            mDrawableLeftSize = typedArray.getDimensionPixelSize(R.styleable.DrawableEditTextStyle_myDrawableLeftSize,mDrawableLeft.getMinimumWidth());
            mDrawableLeft.setBounds(0,0,mDrawableLeftSize,mDrawableLeftSize);
        }

        mDrawableRight = typedArray.getDrawable(R.styleable.DrawableEditTextStyle_myDrawableRight);
        if (mDrawableRight != null) {
            mDrawableRightSize = typedArray.getDimensionPixelSize(R.styleable.DrawableEditTextStyle_myDrawableRightSize,mDrawableRight.getMinimumWidth());
            mDrawableRight.setBounds(0,0,mDrawableRightSize,mDrawableRightSize);
        }

        mDrawableTop = typedArray.getDrawable(R.styleable.DrawableEditTextStyle_myDrawableTop);
        if (mDrawableTop != null) {
            mDrawableTopSize = typedArray.getDimensionPixelSize(R.styleable.DrawableEditTextStyle_myDrawableTopSize,mDrawableTop.getMinimumWidth());
            mDrawableTop.setBounds(0,0,mDrawableTopSize,mDrawableTopSize);
        }

        mDrawableBottom = typedArray.getDrawable(R.styleable.DrawableEditTextStyle_myDrawableBottom);
        if (mDrawableBottom != null) {
            mDrawableBottomSize = typedArray.getDimensionPixelSize(R.styleable.DrawableEditTextStyle_myDrawableBottomSize,mDrawableBottom.getMinimumWidth());
            mDrawableBottom.setBounds(0,0,mDrawableBottomSize,mDrawableBottomSize);
        }
        setCompoundDrawables(mDrawableLeft,mDrawableTop,mDrawableRight,mDrawableBottom);
        typedArray.recycle();
    }

    /**
     * 设置RadioButton中icon的大小
     */
    private void initButtonDrawableSize(Button button, int resourceId) {
        final float scale = this.getResources().getDisplayMetrics().density;// 屏幕密度
        int pd = 40;
        Drawable entryOrExit = ContextCompat.getDrawable(getContext(), resourceId);
        entryOrExit.setBounds(0, 0, (int) (pd * scale + 0.5f), (int) (pd * scale + 0.5f));
        button.setCompoundDrawables(null, entryOrExit, null, null);
    }
}
