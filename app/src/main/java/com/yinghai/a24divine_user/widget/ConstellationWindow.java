package com.yinghai.a24divine_user.widget;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;

import com.example.fansonlib.base.BaseDataAdapter;
import com.example.fansonlib.base.BaseHolder;
import com.example.fansonlib.widget.popupwindow.BasePopup;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.ConstellationBean;

import java.util.ArrayList;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/19 15:04
 *         Describe：选择星座的弹窗
 */

public class ConstellationWindow extends BasePopup {

    private View popupView;
    private Context mContext;
    private ConstellationBean mBean;
    private AutoLoadRecyclerView mRecyclerView;
    private DataAdapter mAdapter;
    private IConstellationListener mListener;

    public ConstellationWindow(Activity context, IConstellationListener listener) {
        super(context);
        mListener = listener;
        mContext = context;
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mRecyclerView = (AutoLoadRecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new DataAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
    }


    private void initData() {
        ArrayList<ConstellationBean> list = new ArrayList<>();
        String[] constellationArray = mContext.getResources().getStringArray(R.array.constellation_array);

        for (int i = 0; i < constellationArray.length; i++) {
            mBean = new ConstellationBean();
            mBean.setConstellation(constellationArray[i]);
            list.add(mBean);
        }

        mAdapter.fillList(list);
    }

    private void initListener() {
    }

    @Override
    protected Animation initShowAnimation() {
        // true:表示使用Animation的interpolator，false:则是使用自己的
        AnimationSet set = new AnimationSet(false);
        Animation anim = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setInterpolator(new OvershootInterpolator());
        anim.setDuration(350);
        set.addAnimation(anim);
        return set;
    }

    @Override
    protected Animation initExitAnimation() {
        AnimationSet set = new AnimationSet(false);
        Animation anim = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(300);
        set.addAnimation(anim);
        return set;
    }


    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        popupView = createPopupById(R.layout.window_constellation);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }


    /*******适配器*******/
    private class DataAdapter extends BaseDataAdapter<ConstellationBean> {

        public DataAdapter(Context context) {
            super(context);
        }

        @Override
        public int getLayoutRes(int i) {
            return R.layout.item_constellation_window;
        }

        @Override
        public void bindCustomViewHolder(BaseHolder holder, final int position) {
            holder.setText(R.id.tv_constellation, getItem(position).getConstellation());
            holder.setOnClickListener(R.id.rootView, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.getConstellation(getItem(position).getConstellation());
                    ConstellationWindow.this.dismiss();
                }
            });
        }
    }


    public interface IConstellationListener {

        /**
         * 监听用户选择的星座值
         *
         * @param constellation
         */
        void getConstellation(String constellation);

    }
}
