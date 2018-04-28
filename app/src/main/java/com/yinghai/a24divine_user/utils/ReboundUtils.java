package com.yinghai.a24divine_user.utils;

import android.view.View;
import android.view.ViewGroup;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringChain;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;

import java.util.List;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/27 11:05
 *         Describe：FaceBook开源动画的工具类
 */

public class ReboundUtils {

    private SpringSystem mSpringSystem;

    /**
     * 动画时间
     */
    private static final int DEFAULT_DURATION = 300;

    /**
     * 拉力系数
     */
    private static final int DEFAULT_TENSION = 40;
    /**
     * 摩擦力系数
     */
    private static final int DEFAULT_FRICTION = 5;

    private static final int DEFAULT_MAIN_TENSION = 40;
    private static final int DEFAULT_MAIN_FRICTION = 6;
    private static final int DEFAULT_ATTACHMENT_TENSION = 70;
    private static final int DEFAULT_ATTACHMENT_FRICTION = 6;

    private static final int SPRING_INDEX = 1; //设置连锁动画哪个先开始

    public ReboundUtils() {
        mSpringSystem = SpringSystem.create();
    }

    /**
     * 弹簧动画
     *
     * @param v    动画View
     * @param from
     * @param to
     */
    public void animateViewDirection(final View v, float from, float to) {
        Spring spring = mSpringSystem.createSpring();
        spring.setCurrentValue(from);
        spring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(DEFAULT_TENSION, DEFAULT_FRICTION));
        spring.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                v.setTranslationY((float) spring.getCurrentValue());
            }
        });
        spring.setEndValue(to);
    }

    /**
     * 链锁动画
     * @param viewGroup
     * @param from
     * @param to
     */
    public void animateViewChain(ViewGroup viewGroup, float from, float to) {

        SpringChain springChain = SpringChain.create(DEFAULT_MAIN_TENSION, DEFAULT_MAIN_FRICTION, DEFAULT_ATTACHMENT_TENSION, DEFAULT_ATTACHMENT_FRICTION);

        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View view = viewGroup.getChildAt(i);

            springChain.addSpring(new SimpleSpringListener() {
                @Override
                public void onSpringUpdate(Spring spring) {
                    view.setTranslationY((float) spring.getCurrentValue());
                }
            });
        }

        List<Spring> springs = springChain.getAllSprings();
        for (int i = 0; i < springs.size(); i++) {
            springs.get(i).setCurrentValue(from);
        }

        springChain.setControlSpringIndex(0).getControlSpring().setEndValue(to);
    }

}
