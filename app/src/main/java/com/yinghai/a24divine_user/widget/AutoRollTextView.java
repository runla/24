package com.yinghai.a24divine_user.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/27 12:02
 *         Describe：自动水平滚动的TextView
 */

public class AutoRollTextView extends android.support.v7.widget.AppCompatTextView{

    public AutoRollTextView(Context context) {
        super(context);
    }

    public AutoRollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHorizontallyScrolling(true);
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setMaxLines(1);
        setMarqueeRepeatLimit(1000);
    }

    public AutoRollTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused(){
        return true;
    }

}
