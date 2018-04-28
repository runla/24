package com.yinghai.a24divine_user.bean;

import java.util.List;

/**
 * Created by：fanson
 * Created on：2017/10/19 16:11
 * Describe：大师的日程
 */

public class ScheduleBean {


    /**
     * code : 1
     * msg : success
     * data : {"masterScheduleList":[{"msDate":"2017-10-24","msQty":2},{"msDate":"2017-10-25","msQty":1},{"msDate":"2017-10-26","msQty":3}],"limitNum":10}
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
        /**
         * masterScheduleList : [{"msDate":"2017-10-24","msQty":2},{"msDate":"2017-10-25","msQty":1},{"msDate":"2017-10-26","msQty":3}]
         * limitNum : 10
         */

        private int limitNum;
        private List<MasterScheduleListBean> masterScheduleList;

        public int getLimitNum() {
            return limitNum;
        }

        public void setLimitNum(int limitNum) {
            this.limitNum = limitNum;
        }

        public List<MasterScheduleListBean> getMasterScheduleList() {
            return masterScheduleList;
        }

        public void setMasterScheduleList(List<MasterScheduleListBean> masterScheduleList) {
            this.masterScheduleList = masterScheduleList;
        }

        public static class MasterScheduleListBean {
            /**
             * msDate : 2017-10-24
             * msQty : 2
             */

            private String msDate;
            private int msQty;

            public String getMsDate() {
                return msDate;
            }

            public void setMsDate(String msDate) {
                this.msDate = msDate;
            }

            public int getMsQty() {
                return msQty;
            }

            public void setMsQty(int msQty) {
                this.msQty = msQty;
            }
        }
    }
}
