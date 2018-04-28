package com.yinghai.a24divine_user.bean;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/13 16:26
 *         Describe：提交购物车返回的Bean
 */

public class SubmitShopCarBean {


    /**
     * code : 1
     * msg : success
     * data : {"tAddressId":58,"tAmount":23,"tCreateTime":"2017-12-14 14:00:43","tOrderNo":"pt20171214140043163875","tParentId":0,"tPayNo":"","tQty":2,"tStatus":1,"tUserId":16,"totalId":104}
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
         * tAddressId : 58
         * tAmount : 23
         * tCreateTime : 2017-12-14 14:00:43
         * tOrderNo : pt20171214140043163875
         * tParentId : 0
         * tPayNo :
         * tQty : 2
         * tStatus : 1
         * tUserId : 16
         * totalId : 104
         */

        private int tAddressId;
        private int tAmount;
        private String tCreateTime;
        private String tOrderNo;
        private int tParentId;
        private String tPayNo;
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

        public String getTCreateTime() {
            return tCreateTime;
        }

        public void setTCreateTime(String tCreateTime) {
            this.tCreateTime = tCreateTime;
        }

        public String getTOrderNo() {
            return tOrderNo;
        }

        public void setTOrderNo(String tOrderNo) {
            this.tOrderNo = tOrderNo;
        }

        public int getTParentId() {
            return tParentId;
        }

        public void setTParentId(int tParentId) {
            this.tParentId = tParentId;
        }

        public String getTPayNo() {
            return tPayNo;
        }

        public void setTPayNo(String tPayNo) {
            this.tPayNo = tPayNo;
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
