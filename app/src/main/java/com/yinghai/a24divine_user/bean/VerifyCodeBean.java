package com.yinghai.a24divine_user.bean;

/**
 * Created by chenjianrun on 2017/11/14.
 * 描述：获取验证码返回的 bean
 */

public class VerifyCodeBean {

    /**
     * code : 1
     * msg : 发送验证成功
     * data : {"sessionId":"4CD416C7C135F934A1FB291E50AD29EB"}
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
         * sessionId : 4CD416C7C135F934A1FB291E50AD29EB
         */

        private String sessionId;

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }
    }
}
