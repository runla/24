package com.yinghai.a24divine_user.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.yinghai.a24divine_user.BR;

import java.util.List;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/25 16:36
 *         Describe：
 */

public class FollowBean {

    /**
     * code : 1
     * msg : success
     * data : {"subscribes":[{"img":"","nick":"曾学松","sCreateTime":"2017-11-08","sMasterId":1,"sUserId":5,"subscribeId":2},{"img":"","nick":"生","sCreateTime":"2017-11-08","sMasterId":2,"sUserId":5,"subscribeId":3}]}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<SubscribesBean> subscribes;

        public List<SubscribesBean> getSubscribes() {
            return subscribes;
        }

        public void setSubscribes(List<SubscribesBean> subscribes) {
            this.subscribes = subscribes;
        }

        public static class SubscribesBean extends BaseObservable{
            /**
             * img :
             * nick : 曾学松
             * sCreateTime : 2017-11-08
             * sMasterId : 1
             * sUserId : 5
             * subscribeId : 2
             */

            private String img;
            private String nick;
            private String sCreateTime;
            private int sMasterId;
            private int sUserId;
            private int subscribeId;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            @Bindable
            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
                notifyPropertyChanged(BR.nick);
            }

            public String getSCreateTime() {
                return sCreateTime;
            }

            public void setSCreateTime(String sCreateTime) {
                this.sCreateTime = sCreateTime;
            }

            public int getSMasterId() {
                return sMasterId;
            }

            public void setSMasterId(int sMasterId) {
                this.sMasterId = sMasterId;
            }

            public int getSUserId() {
                return sUserId;
            }

            public void setSUserId(int sUserId) {
                this.sUserId = sUserId;
            }

            public int getSubscribeId() {
                return subscribeId;
            }

            public void setSubscribeId(int subscribeId) {
                this.subscribeId = subscribeId;
            }
        }
    }
}
