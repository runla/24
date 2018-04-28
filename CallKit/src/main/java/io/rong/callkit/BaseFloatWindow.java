package io.rong.callkit;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Created by chenjianrun on 2018/1/11.
 */

public abstract class BaseFloatWindow extends FrameLayout {
    protected final Context mContext;
    protected WindowManager mWindowManager;
    private WindowManager.LayoutParams layoutParams;
    private float lastX, lastY;
    int oldOffsetX, oldOffsetY;
    int tag = 0;
    public BaseFloatWindow(Context context) {
        this(context, null);
    }

    public BaseFloatWindow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseFloatWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    protected void showView(View view) {
        showView(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    protected void showView(View view, int width, int height) {
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        //TYPE_TOAST仅适用于4.4+系统，假如要支持更低版本使用TYPE_SYSTEM_ALERT(需要在manifest中声明权限)
        int type;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < 24) {
            type = WindowManager.LayoutParams.TYPE_TOAST;
        } else {
            type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        //设置type.系统提示型窗口，一般都在应用程序窗口之上.
        layoutParams = new WindowManager.LayoutParams(type);

        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.flags |= WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        //layoutParams.flags |= WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS; //no limit适用于超出屏幕的情况，若添加此flag需要增加边界检测逻辑
        layoutParams.width = width;
        layoutParams.height = height;
        layoutParams.x = mContext.getResources().getDisplayMetrics().widthPixels;
        layoutParams.y = mContext.getResources().getDisplayMetrics().heightPixels;
        layoutParams.format = PixelFormat.TRANSLUCENT;
        mWindowManager.addView(view, layoutParams);
    }

    protected void hideView() {
        if (null != mWindowManager){
            mWindowManager.removeViewImmediate(this);
        }
        mWindowManager = null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        float x = event.getX();
        float y = event.getY();
        if (tag == 0) {
            oldOffsetX = layoutParams.x;
            oldOffsetY = layoutParams.y;
        }
        if (action == MotionEvent.ACTION_DOWN) {
            lastX = x;
            lastY = y;
        } else if (action == MotionEvent.ACTION_MOVE) {
            // 减小偏移量,防止过度抖动
            layoutParams.x += (int) (x - lastX) / 3;
            layoutParams.y += (int) (y - lastY) / 3;
            tag = 1;
            mWindowManager.updateViewLayout(this, layoutParams);
        } else if (action == MotionEvent.ACTION_UP) {
            int newOffsetX = layoutParams.x;
            int newOffsetY = layoutParams.y;
            if (Math.abs(oldOffsetX - newOffsetX) <= 20 && Math.abs(oldOffsetY - newOffsetY) <= 20) {
//                Toast.makeText(mContext,"双击", Toast.LENGTH_LONG).show();
                onClickToResume();
            } else {
                tag = 0;
            }
        }
        return true;
//        return mGestureDetector.onTouchEvent(event);
    }

    public abstract void onClickToResume();

}
