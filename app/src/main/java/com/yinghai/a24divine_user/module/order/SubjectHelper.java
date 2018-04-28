package com.yinghai.a24divine_user.module.order;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by：fanson
 *         Created Time: 2017/12/22 12:10
 *         Describe：观察者管理类（注册，注销，发布），所有的观察者对象引用列表在此
 */

public class SubjectHelper {

    /**
     * 将一个被观察者对象和多个观察者对象绑定起来
     */
    private static List<MyObserver> observers = new ArrayList<>();

    /**
     * 添加观察者，我们可能需要各种各样的观察者
     *
     * @param observer 观察者对象
     */
    public static void attachObserver(MyObserver observer) {
        if (null == observer) {
            throw new NullPointerException();
        }
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * 添加唯一的观察者，若有其他观察者都清除
     * @param observer 观察者对象
     */
    public static void attachOneObserver(MyObserver observer) {
        if (null == observer) {
            throw new NullPointerException();
        }
        if (observers.size() > 0) {
            observers.clear();
        }
        observers.add(observer);
    }

    /**
     * 移除观察者
     *
     * @param observer 观察者对象
     */
    public static void detachObserver(MyObserver observer) {
        if (null == observer) {
            return;
        }
        if (observers.contains(observer)) {
            observers.remove(observer);
        }
    }

    /**
     * 发布消息，更新订单状态
     *
     * @param orderStatus 订单状态值
     */
    public static void notifyObserverUpdate(int orderStatus) {
        for (MyObserver observer : observers) {
            observer.update(orderStatus);
        }
    }

}
