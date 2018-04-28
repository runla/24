package com.yinghai.a24divine_user.bean;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/13 13:30
 *         Describe：data数据为空的Bean
 */

public class NoDataBean {

    /**
     * code : 1
     * msg : success
     * data : {}
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
    }

}
