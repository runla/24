package com.yinghai.a24divine_user.module.login.state;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

import com.example.fansonlib.widget.popupwindow.BasePopup;
import com.yinghai.a24divine_user.R;

/**
 * @author Created by：fanson
 *         Created Time: 2017/12/8 10:58
 *         Describe：用户未登录的情况下，弹出提示登陆的窗口
 */

public class LoginTipWindow extends BasePopup{

    private View popupView;
    private Button mBtnLogin,mBtnCancel;
    private  IToLoginListener mListener;

    public LoginTipWindow(Activity context, IToLoginListener listener) {
        super(context);
        mListener = listener;
        initView();
        initListener();
    }

    private void initView() {
        mBtnCancel = (Button)findViewById(R.id.btn_cancel);
        mBtnLogin = (Button)findViewById(R.id.btn_login);
    }

    private void initListener() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.gotoLogin();
                dismiss();
            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginTipWindow.this.dismiss();
            }
        });
    }

    @Override
    protected Animation initShowAnimation() {
        // true:表示使用Animation的interpolator，false:则是使用自己的
        AnimationSet set = new AnimationSet(false);
        Animation anim = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setInterpolator(new BounceInterpolator());
        anim.setDuration(500);
        set.addAnimation(anim);
        return set;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        popupView =  createPopupById(R.layout.window_login_tip);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        mListener = null;
    }

    interface IToLoginListener{
        /**
         * 跳转到登陆界面
         */
        void gotoLogin();
    }
}
