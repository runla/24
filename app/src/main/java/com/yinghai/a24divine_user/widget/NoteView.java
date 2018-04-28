package com.yinghai.a24divine_user.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yinghai.a24divine_user.R;


/**
 * Created by chenjianrun on 2017/9/19.
 */

public class NoteView extends RelativeLayout{
    //================常量=============================
    private static final String TAG = "NoteView";
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int TOP = 2;
    public static final int BOTTOM = 3;

    //===========自定义属性系列开始=======================
    private int mImageHeight;                               //图标的高度
    private int mTextSize;                                  //文本的字体大小,单位是 px
    private int mTextToImage;                               //文本到图标的距离
    private int mMarginVertical;                            //垂直方向的边距
    private int mMarginHorizontal;                          //水平方向的边距
    private String mTextContent;                            //签的内容
    private Drawable mImageSrc;                             //图标资源
    private int mTurn;                                      //签头的方向
    private int mIconColor;                                 //图标的颜色
    private int mTextColor;                                 //文本的颜色
    private int mBackground;                                //背景颜色
    private int mNormalBackground;                          //背景颜色,正常
    private int mPressBackgroup;                            //背景颜色,按下
    private Drawable mBackgroundDrawable;                   //背景颜色


    //================默认值============================
    private int defaultImageHeight = 25;                    //默认的的图标宽高
    private int defaultTextSize = 12;                       //默认的文字大小,单位是 px
    private int defaultTextToImage = 5;                     //默认的图标和文字的间距


    private int mTextLenght;                                //文字的总长度
    private int mNoteMinWidth;                              //签允许最小的宽度
    private int mNoteMinHeight;                             //签允许最小的高度
    private int mNoteRadius;                                //签头的圆角半径
    @IdRes
    private int mImageViewId = 0X1000;                      //ImageView 的 id
    @IdRes
    private int mTextViewId = 0X1001;                       //Textview  的 id



    private int myheight;
    private boolean hasBackground = false;
    public NoteView(Context context) {
        this(context,null);
    }

    public NoteView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NoteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.NoteView, defStyleAttr, 0);

        mImageHeight = typedArray.getDimensionPixelOffset(R.styleable.NoteView_note_iamgeHeight,dp2px(context,defaultImageHeight));
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.NoteView_note_textSize,sp2px(context,defaultTextSize));
        mMarginVertical = typedArray.getDimensionPixelOffset(R.styleable.NoteView_note_marginVertical,mImageHeight/2);
        mMarginHorizontal = typedArray.getDimensionPixelOffset(R.styleable.NoteView_note_marginHorizontal,mImageHeight/2);
        mTextToImage = typedArray.getDimensionPixelOffset(R.styleable.NoteView_note_textToImage,dp2px(context,defaultTextToImage));
        mTextContent = typedArray.getString(R.styleable.NoteView_note_textContent);
        mImageSrc = typedArray.getDrawable(R.styleable.NoteView_note_imageSrc);
        mTurn = typedArray.getInt(R.styleable.NoteView_note_turn,0);
        mNormalBackground = typedArray.getColor(R.styleable.NoteView_note_background,Color.WHITE);
        mPressBackgroup = typedArray.getColor(R.styleable.NoteView_note_background,ContextCompat.getColor(getContext(),R.color.grey_light2));
        mBackground = mNormalBackground;
        mBackgroundDrawable = typedArray.getDrawable(R.styleable.NoteView_note_backgroundDrawable);
        mTextColor = typedArray.getColor(R.styleable.NoteView_note_textColor,Color.BLACK);
        mIconColor = typedArray.getColor(R.styleable.NoteView_note_iconColor,0);
        typedArray.recycle();

        RoundRectShape roundRectShape = new RoundRectShape(getRectRadius(), null, null);
        ShapeDrawable drawable=new ShapeDrawable(roundRectShape);
        setBackground(drawable);
        hasBackground = true;



        createImageView();
        createTextView();
        computeLenght();
    }

    /**
     * 用来计算文字长度、最小宽度、最小高度
     */
    private void computeLenght(){
        mTextLenght = getTextViewLenght();
        int maxWidth = Math.max(mImageHeight,mTextLenght/mTextContent.length());
        if (mTurn == TOP || mTurn == BOTTOM){
            //签头向上或向下
            //最小高度 = 文字长度 + 图标长度 + 垂直间距 + 文字图标间距
            mNoteMinHeight =  mTextLenght + mImageHeight + mMarginVertical * 4 + mTextToImage;
            //最小宽度 = （文字高度和图标间的最大值）+ 水平边距
            mNoteMinWidth = maxWidth + mMarginHorizontal * 2;
        }else{
            //签头向左或向右
            //最小高度 = （文字高度和图标间的最大值）+ 水平边距
            mNoteMinHeight = maxWidth + mMarginVertical * 2;
            //最小宽度 = 文字长度 + 图标长度 + 垂直间距 + 文字图标间距
            mNoteMinWidth =  mTextLenght + mImageHeight + mMarginHorizontal * 4 +mTextToImage;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(getRequestMeasureSpec(widthMeasureSpec,mNoteMinWidth), getRequestMeasureSpec(heightMeasureSpec,mNoteMinHeight));
    }

    /**
     * 用来计算符合要求的 MeasureSpec（宽高）
     * @param measureSpec
     * @param requestMinLength      需求限制的最小长度（业务需求，保证能够完整显示图标和文字的长度）
     * @return
     */
    private int getRequestMeasureSpec(int measureSpec, int requestMinLength){
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int mReturnSize;

        if (mode == MeasureSpec.EXACTLY){
            mReturnSize = size;
        }else{
            mReturnSize = requestMinLength;
        }
        return MeasureSpec.makeMeasureSpec(mReturnSize,mode);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBackgroundDrawable==null) {
            RoundRectShape roundRectShape = new RoundRectShape(getRectRadius(), null, null);
            ShapeDrawable drawable=new ShapeDrawable(roundRectShape);
            drawable.getPaint().setColor(mBackground);
            drawable.getPaint().setAntiAlias(true);
            drawable.getPaint().setStyle(Paint.Style.FILL);//描边
            setBackground(drawable);
        }else{
            setBackground(mBackgroundDrawable);
        }


        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mBackground = mPressBackgroup;
                invalidate();
                break;
            case MotionEvent.ACTION_OUTSIDE:
                mBackground = mNormalBackground;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                mBackground = mNormalBackground;
                invalidate();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 获取矩形的圆角半径（onDraw 之后调用）
     * @return   返回的数组表示矩形 （01 左上  23右上 45右下 67左下） 圆角半径
     */
    private float[] getRectRadius(){
        float radius;
        if (mTurn == TOP || mTurn == BOTTOM) {
            radius = getWidth() / 2;
        }else{
            radius = getHeight() / 2;
        }
        switch (mTurn){
            case LEFT:
                return new float[]{radius,radius,0,0,0,0,radius,radius};

            case TOP:
                return new float[]{radius,radius,radius,radius,0,0,0,0};

            case RIGHT:
                return new float[]{0,0,radius,radius,radius,radius,0,0};

            case BOTTOM:
                return new float[]{0,0,0,0,radius,radius,radius,radius};

            default:
                break;
        }
        return null;
    }

    private void createImageView(){
        LayoutParams layoutParams = new LayoutParams(mImageHeight,mImageHeight);
        switch (mTurn){
            case LEFT:
                layoutParams.setMargins(mMarginHorizontal,mMarginVertical,mTextToImage,mMarginVertical);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
                break;

            case TOP:
                layoutParams.setMargins(mMarginHorizontal,mMarginVertical,mMarginHorizontal,mTextToImage);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                break;

            case RIGHT:
                layoutParams.setMargins(mTextToImage,mMarginVertical,mMarginHorizontal,mMarginVertical);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
                break;

            case BOTTOM:
                layoutParams.setMargins(mMarginHorizontal,mTextToImage,mMarginHorizontal,mMarginVertical);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                break;

            default:
                break;
        }

        ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageDrawable(mImageSrc);
        imageView.setId(mImageViewId);
        if (mIconColor != 0) {
            DrawableCompat.setTint(imageView.getDrawable(),mIconColor);
        }
        addView(imageView);
    }

    private void createTextView(){
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        switch (mTurn){
            case LEFT:
                layoutParams.setMargins(0,mMarginVertical,mMarginHorizontal,mMarginVertical);
                layoutParams.addRule(RelativeLayout.RIGHT_OF,mImageViewId);
                layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
                break;

            case TOP:
                layoutParams.setMargins(mMarginHorizontal,0,mMarginHorizontal,mMarginVertical);
                layoutParams.addRule(RelativeLayout.BELOW,mImageViewId);
                layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                break;

            case RIGHT:
                layoutParams.setMargins(mMarginHorizontal,mMarginVertical,0,mMarginVertical);
                layoutParams.addRule(RelativeLayout.LEFT_OF,mImageViewId);
                layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
                break;

            case BOTTOM:
                layoutParams.setMargins(mMarginHorizontal,mMarginVertical,mMarginHorizontal,0);
                layoutParams.addRule(RelativeLayout.ABOVE,mImageViewId);
                layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                break;

            default:
                break;
        }

        TextView textView = new TextView(getContext());
        textView.setLayoutParams(layoutParams);
        textView.setText(mTextContent);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,mTextSize);
        textView.setGravity(Gravity.CENTER);
        textView.setId(mTextViewId);
        textView.setTextColor(mTextColor);
        if (mTurn == TOP || mTurn == BOTTOM){
            textView.setEms(1);
        }else{
            textView.setLines(1);
        }
        addView(textView);
    }

    /**
     * 获取textview 中字符串显示的像素长度
     * @return
     */
    private int getTextViewLenght(){
        TextView textView = new TextView(getContext());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,mTextSize);
        TextPaint textPaint = textView.getPaint();
        return (int) textPaint.measureText(mTextContent);
    }


//    @Override
//    protected void onAttachedToWindow() {
//        super.onAttachedToWindow();
//        final ViewTreeObserver viewTreeObserver = getViewTreeObserver();
//        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                viewTreeObserver.removeOnGlobalLayoutListener(this);
//                ValueAnimator valueAnimator = ValueAnimator.ofInt(mMarginVertical,getHeight());
//                valueAnimator.setDuration(2000);
//                //时间插器，值的变化速率
//                valueAnimator.setInterpolator(new CycleInterpolator(2));
//                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
//
//                        int height = (Integer) animation.getAnimatedValue();
//                        getLayoutParams().height = height;
//                        requestLayout();
//                        Log.d(TAG, "height: "+height);
//                    }
//                });
//                valueAnimator.start();
//            }
//        });
//    }


    public int getMyheight() {
        return myheight;
    }

    public void setMyheight(int myheight) {
        this.myheight = myheight;
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        layoutParams.height = myheight;
        setLayoutParams(layoutParams);
        invalidate();
    }


    private int dp2px(Context context, float dpValue) {
        final float densityScale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * densityScale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
