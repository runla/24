package com.yinghai.a24divine_user.module.order;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;

import com.example.fansonlib.widget.popupwindow.BasePopup;
import com.example.fansonlib.widget.textview.TextViewDrawable;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.utils.DimensUtils;

/**
 * @author Created by：fanson
 *         Created Time: 2017/12/20 11:17
 *         Describe：帅选订单类型的窗口
 */

public class ChooseOrderTypeWindow extends BasePopup{

    private TextViewDrawable mTdDivineType,mTdProductType;
    private IChooseTypeListener mListener;
    /**
     * 占卜类型
     */
    public static final int TYPE_DIVINE = 0;
    /**
     * 商品类型
     */
    public static final int TYPE_PRODUCT = 1;

    /**
     * 移除监听
     */
    public void removeListener(){
        mListener = null;
    }

    public ChooseOrderTypeWindow(Activity context, IChooseTypeListener listener) {
        super(context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setAutoLocatePopup(true);
        setDismissWhenTouchOutside(true);
        mListener = listener;
        initView();
        initListener();
    }

    private void initView() {
        mTdDivineType = (TextViewDrawable) findViewById(R.id.td_type_divine);
        mTdProductType = (TextViewDrawable) findViewById(R.id.td_type_product);
    }


    private void initListener() {
        mTdDivineType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onChooseType(TYPE_DIVINE);
                dismiss();
            }
        });

        mTdProductType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onChooseType(TYPE_PRODUCT);
                dismiss();
            }
        });
    }

    @Override
    protected Animation initShowAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0f, 0f, -DimensUtils.dipToPx(getContext(), 350f), 0);
        translateAnimation.setDuration(450);
        translateAnimation.setInterpolator(new OvershootInterpolator(1));
        return translateAnimation;
    }

    @Override
    protected Animation initExitAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0f, 0f, 0, -DimensUtils.dipToPx(getContext(), 350f));
        translateAnimation.setDuration(450);
        translateAnimation.setInterpolator(new OvershootInterpolator(-4));
        return translateAnimation;
    }

    @Override
    public View getClickToDismissView() {
        return null;
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.window_choose_order_type);
    }

    @Override
    public View initAnimaView() {
        return getPopupWindowView().findViewById(R.id.popup_anima);
    }


    /**
     * 帅选类型
     */
    public interface IChooseTypeListener{
        /**
         * 帅选类型的回调
         * @param type 结果
         */
        void onChooseType(int type);
    }
}
