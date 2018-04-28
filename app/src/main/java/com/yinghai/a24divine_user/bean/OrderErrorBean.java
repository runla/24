package com.yinghai.a24divine_user.bean;

/**
 * @author Created by：fanson
 *         Created Time: 2017/12/21 18:21
 *         Describe：订单异常的处理Bean
 */

public class OrderErrorBean {

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

        private int oStatus;

        public int getoStatus() {
            return oStatus;
        }

        public void setoStatus(int oStatus) {
            this.oStatus = oStatus;
        }
    }

}
