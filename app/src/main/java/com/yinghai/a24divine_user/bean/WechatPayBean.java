package com.yinghai.a24divine_user.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/16 17:29
 *         Describe：微信下单返回Bean
 */

public class WechatPayBean {


    /**
     * code : 1
     * msg : success
     * data : {"msg":"操作成功","code":"1","data":{"package":"Sign=WXPay","appid":"wxd74af2b277375f05","sign":"8864CA26683317BFF93C493A5CFEEB5A","partnerid":"1380512402","prepayid":"wx201711161721187e17d1e2ed0955032543","noncestr":"2vxSrrM9xPCZs2vu","timestamp":"1510852886"}}
     */

    private int code;
    private String msg;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * msg : 操作成功
         * code : 1
         * data : {"package":"Sign=WXPay","appid":"wxd74af2b277375f05","sign":"8864CA26683317BFF93C493A5CFEEB5A","partnerid":"1380512402","prepayid":"wx201711161721187e17d1e2ed0955032543","noncestr":"2vxSrrM9xPCZs2vu","timestamp":"1510852886"}
         */

        private String msg;
        private String code;
        private DataBean data;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * package : Sign=WXPay
             * appid : wxd74af2b277375f05
             * sign : 8864CA26683317BFF93C493A5CFEEB5A
             * partnerid : 1380512402
             * prepayid : wx201711161721187e17d1e2ed0955032543
             * noncestr : 2vxSrrM9xPCZs2vu
             * timestamp : 1510852886
             */

            @SerializedName("package")
            private String packageX;
            private String appid;
            private String sign;
            private String partnerid;
            private String prepayid;
            private String noncestr;
            private String timestamp;

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }
        }
    }
}
