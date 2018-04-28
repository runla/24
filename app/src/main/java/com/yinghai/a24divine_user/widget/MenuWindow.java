package com.yinghai.a24divine_user.widget;

import android.animation.Animator;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.example.fansonlib.utils.SharePreferenceHelper;
import com.example.fansonlib.widget.popupwindow.BasePopup;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.CommentBean;
import com.yinghai.a24divine_user.constant.ConstantPreference;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/16 11:13
 *         Describe：点击评论列表item的弹出框功能
 */

public class MenuWindow extends BasePopup  {

    private TextView tvDelete,mTvCopy;
    private int mCommentId;
    private CommentBean.DataBean.CommentsBean mBean;

    public MenuWindow(Activity context, OnMenuChooseCallback onMenuChooseCallback, CommentBean.DataBean.CommentsBean bean) {
        super(context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setAutoLocatePopup(true);
        tvDelete = (TextView) findViewById(R.id.tv_del);
        mTvCopy = (TextView)findViewById(R.id.tv_copy);
        View mLine = findViewById(R.id.line);
        mCommentId = bean.getCommentId();
        mBean = bean;
        //评论是自己的，方弹出删除按钮；
        if (SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID, 0) == bean.getUserId()) {
            mLine.setVisibility(View.VISIBLE);
        }else {
            tvDelete.setVisibility(View.GONE);
            mLine.setVisibility(View.GONE);
        }
        this.onMenuChooseCallback = onMenuChooseCallback;
        initListener();
    }

    private void initListener() {
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMenuChooseCallback.delete(mCommentId);
                MenuWindow.this.dismiss();
            }
        });

        mTvCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMenuChooseCallback.copy(mBean.getCtContent());
                MenuWindow.this.dismiss();
            }
        });

    }

    @Override
    protected Animation initShowAnimation() {
        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new DecelerateInterpolator());
        set.addAnimation(getScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0));
        set.addAnimation(getDefaultAlphaAnimation());
        return set;
        //return null;
    }

    @Override
    public Animator initShowAnimator() {
        return null;
    }

    @Override
    public void showPopupWindow(View v) {
        setOffsetX(-(getWidth() - v.getWidth() / 2));
        super.showPopupWindow(v);
    }

    @Override
    public View getClickToDismissView() {
        return null;
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.window_menu);
    }

    @Override
    public View initAnimaView() {
        return getPopupWindowView().findViewById(R.id.popup_contianer);
    }


    @Override
    public void dismiss() {
        super.dismiss();
        onMenuChooseCallback = null;
    }

    private OnMenuChooseCallback onMenuChooseCallback;

    public interface OnMenuChooseCallback {
        /**
         * 選擇刪除
         * @param mCommentId 评论ID
         */
        void delete(int mCommentId);

        /**
         * 复制
         * @param content 评论内容
         */
        void copy(String content);

    }
}

