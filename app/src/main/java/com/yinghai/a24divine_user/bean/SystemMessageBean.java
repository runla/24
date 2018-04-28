package com.yinghai.a24divine_user.bean;

/**
 * Created by chenjianrun on 2017/12/4.
 * 描述：系统消息 content 的 bean
 */

public class SystemMessageBean {


    /**
     * code : 2002
     * msg : 2002
     * data : {"order":{"oOrderNo":"201711151434565599598","orderId":34},"user":{"uBirthday":"","uConstellation":7,"uCountryCode":"86","uIm":"user6","uImgUrl":"","uNick":"测试","uSex":true,"uTel":"15919161025"},"time":"2017-12-05 16:59:09"}
     */

    private String code;
    private String msg;
    private DataBean data;
    /**
     * tfOrderTotal : {"tAddressId":0,"tAmount":0,"tCreateTime":null,"tOrderNo":"pt20171218103011101215","tQty":0,"tStatus":0,"tUserId":0,"totalId":121}
     */


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
         * order : {"oOrderNo":"201711151434565599598","orderId":34}
         * user : {"uBirthday":"","uConstellation":7,"uCountryCode":"86","uIm":"user6","uImgUrl":"","uNick":"测试","uSex":true,"uTel":"15919161025"}
         * time : 2017-12-05 16:59:09
         */

        private OrderBean order;
        private UserBean user;
        private String time;
        private TfOrderTotalBean tfOrderTotal;//商品订单的系统推送

        private TfMasterBean tfMaster;

        public TfOrderTotalBean getTfOrderTotal() {
            return tfOrderTotal;
        }

        public void setTfOrderTotal(TfOrderTotalBean tfOrderTotal) {
            this.tfOrderTotal = tfOrderTotal;
        }

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public TfMasterBean getTfMaster() {
            return tfMaster;
        }

        public void setTfMaster(TfMasterBean tfMaster) {
            this.tfMaster = tfMaster;
        }

        public static class OrderBean {
            /**
             * oOrderNo : 201711151434565599598
             * orderId : 34
             */

            private String oOrderNo;
            private int orderId;

            public String getOOrderNo() {
                return oOrderNo;
            }

            public void setOOrderNo(String oOrderNo) {
                this.oOrderNo = oOrderNo;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }
        }

        public static class UserBean {
            /**
             * uBirthday :
             * uConstellation : 7
             * uCountryCode : 86
             * uIm : user6
             * uImgUrl :
             * uNick : 测试
             * uSex : true
             * uTel : 15919161025
             */

            private String uBirthday;
            private int uConstellation;
            private String uCountryCode;
            private String uIm;
            private String uImgUrl;
            private String uNick;
            private boolean uSex;
            private String uTel;
            private String userId;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUBirthday() {
                return uBirthday;
            }

            public void setUBirthday(String uBirthday) {
                this.uBirthday = uBirthday;
            }

            public int getUConstellation() {
                return uConstellation;
            }

            public void setUConstellation(int uConstellation) {
                this.uConstellation = uConstellation;
            }

            public String getUCountryCode() {
                return uCountryCode;
            }

            public void setUCountryCode(String uCountryCode) {
                this.uCountryCode = uCountryCode;
            }

            public String getUIm() {
                return uIm;
            }

            public void setUIm(String uIm) {
                this.uIm = uIm;
            }

            public String getUImgUrl() {
                return uImgUrl;
            }

            public void setUImgUrl(String uImgUrl) {
                this.uImgUrl = uImgUrl;
            }

            public String getUNick() {
                return uNick;
            }

            public void setUNick(String uNick) {
                this.uNick = uNick;
            }

            public boolean isUSex() {
                return uSex;
            }

            public void setUSex(boolean uSex) {
                this.uSex = uSex;
            }

            public String getUTel() {
                return uTel;
            }

            public void setUTel(String uTel) {
                this.uTel = uTel;
            }
        }

        public static class TfMasterBean {
            /**
             * mNick : 逢生大师
             */

            private String mNick;

            public String getMNick() {
                return mNick;
            }

            public void setMNick(String mNick) {
                this.mNick = mNick;
            }
        }
    }

    public static class TfOrderTotalBean {
        /**
         * tAddressId : 0
         * tAmount : 0
         * tCreateTime : null
         * tOrderNo : pt20171218103011101215
         * tQty : 0
         * tStatus : 0
         * tUserId : 0
         * totalId : 121
         */

        private int tAddressId;
        private int tAmount;
        private Object tCreateTime;
        private String tOrderNo;
        private int tQty;
        private int tStatus;
        private int tUserId;
        private int totalId;

        public int getTAddressId() {
            return tAddressId;
        }

        public void setTAddressId(int tAddressId) {
            this.tAddressId = tAddressId;
        }

        public int getTAmount() {
            return tAmount;
        }

        public void setTAmount(int tAmount) {
            this.tAmount = tAmount;
        }

        public Object getTCreateTime() {
            return tCreateTime;
        }

        public void setTCreateTime(Object tCreateTime) {
            this.tCreateTime = tCreateTime;
        }

        public String getTOrderNo() {
            return tOrderNo;
        }

        public void setTOrderNo(String tOrderNo) {
            this.tOrderNo = tOrderNo;
        }

        public int getTQty() {
            return tQty;
        }

        public void setTQty(int tQty) {
            this.tQty = tQty;
        }

        public int getTStatus() {
            return tStatus;
        }

        public void setTStatus(int tStatus) {
            this.tStatus = tStatus;
        }

        public int getTUserId() {
            return tUserId;
        }

        public void setTUserId(int tUserId) {
            this.tUserId = tUserId;
        }

        public int getTotalId() {
            return totalId;
        }

        public void setTotalId(int totalId) {
            this.totalId = totalId;
        }
    }
}
