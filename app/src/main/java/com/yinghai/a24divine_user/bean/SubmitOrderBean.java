package com.yinghai.a24divine_user.bean;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/16 14:06
 *         Describe：提交订单Bean
 */

public class SubmitOrderBean {


    /**
     * code : 1
     * msg : success
     * data : {"tfOrder":{"oAmount":3100,"oAppointmentTime":"2017-11-17 12:00:00","oBusinessId":12,"oCancelTime":"","oCancelType":0,"oCompleteTime":"","oCreateTime":"2017-11-16 15:59:52","oIsOrderTotal":false,"oLogisticsNo":"","oMasterId":1,"oOrderNo":"201711161559525233950","oOrderTotalNo":"","oOrderType":1,"oPayStatus":1,"oPayTime":"","oPayWay":0,"oPrice":3100,"oProductId":0,"oQty":1,"oStatus":1,"oUpdateTime":"","oUserId":5,"orderId":44,"tfBusiness":{"bCreateTime":"2017-10-25 17:22:41","bDeals":0,"bIntroduction":"介绍1下我的业务112111","bMasterId":1,"bName":"我的业2务咯1121","bPrice":3100,"bUpdateTime":"","businessId":12},"tfMaster":{"mAccid":0,"mAddress":"拱北","mBargain":1100,"mBusinessType":"1","mConstellation":2,"mCountryCode":"86","mCreateTime":"2017-10-23 10:09:14","mDeals":1,"mDeleted":false,"mDeviceId":"","mDeviceType":1,"mFollows":1,"mHeadImg":"","mIm":"0","mIntroduction":"","mLabel":"1","mNick":"曾学松","mPassword":"d9b1d7db4cd6e70935368a1efb10e377","mScore":4.5,"mSex":true,"mStatus":1,"mTel":"15919160761","mToken":"","mUpdateTime":"2017-10-25 17:33:27","masterId":1},"tfOrderAttach":{"ahBirthday":"1990-09-08 00:00:00","ahBirthplace":"","ahCountryCode":"","ahCreateTime":"","ahDescribe":"","ahName":"qfs","ahOrderId":44,"ahSex":true,"ahTel":"","attachId":0},"tfProduct":null,"tfUser":null}}
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
         * tfOrder : {"oAmount":3100,"oAppointmentTime":"2017-11-17 12:00:00","oBusinessId":12,"oCancelTime":"","oCancelType":0,"oCompleteTime":"","oCreateTime":"2017-11-16 15:59:52","oIsOrderTotal":false,"oLogisticsNo":"","oMasterId":1,"oOrderNo":"201711161559525233950","oOrderTotalNo":"","oOrderType":1,"oPayStatus":1,"oPayTime":"","oPayWay":0,"oPrice":3100,"oProductId":0,"oQty":1,"oStatus":1,"oUpdateTime":"","oUserId":5,"orderId":44,"tfBusiness":{"bCreateTime":"2017-10-25 17:22:41","bDeals":0,"bIntroduction":"介绍1下我的业务112111","bMasterId":1,"bName":"我的业2务咯1121","bPrice":3100,"bUpdateTime":"","businessId":12},"tfMaster":{"mAccid":0,"mAddress":"拱北","mBargain":1100,"mBusinessType":"1","mConstellation":2,"mCountryCode":"86","mCreateTime":"2017-10-23 10:09:14","mDeals":1,"mDeleted":false,"mDeviceId":"","mDeviceType":1,"mFollows":1,"mHeadImg":"","mIm":"0","mIntroduction":"","mLabel":"1","mNick":"曾学松","mPassword":"d9b1d7db4cd6e70935368a1efb10e377","mScore":4.5,"mSex":true,"mStatus":1,"mTel":"15919160761","mToken":"","mUpdateTime":"2017-10-25 17:33:27","masterId":1},"tfOrderAttach":{"ahBirthday":"1990-09-08 00:00:00","ahBirthplace":"","ahCountryCode":"","ahCreateTime":"","ahDescribe":"","ahName":"qfs","ahOrderId":44,"ahSex":true,"ahTel":"","attachId":0},"tfProduct":null,"tfUser":null}
         */

        private TfOrderBean tfOrder;

        public TfOrderBean getTfOrder() {
            return tfOrder;
        }

        public void setTfOrder(TfOrderBean tfOrder) {
            this.tfOrder = tfOrder;
        }

        public static class TfOrderBean {
            /**
             * oAmount : 3100
             * oAppointmentTime : 2017-11-17 12:00:00
             * oBusinessId : 12
             * oCancelTime :
             * oCancelType : 0
             * oCompleteTime :
             * oCreateTime : 2017-11-16 15:59:52
             * oIsOrderTotal : false
             * oLogisticsNo :
             * oMasterId : 1
             * oOrderNo : 201711161559525233950
             * oOrderTotalNo :
             * oOrderType : 1
             * oPayStatus : 1
             * oPayTime :
             * oPayWay : 0
             * oPrice : 3100
             * oProductId : 0
             * oQty : 1
             * oStatus : 1
             * oUpdateTime :
             * oUserId : 5
             * orderId : 44
             * tfBusiness : {"bCreateTime":"2017-10-25 17:22:41","bDeals":0,"bIntroduction":"介绍1下我的业务112111","bMasterId":1,"bName":"我的业2务咯1121","bPrice":3100,"bUpdateTime":"","businessId":12}
             * tfMaster : {"mAccid":0,"mAddress":"拱北","mBargain":1100,"mBusinessType":"1","mConstellation":2,"mCountryCode":"86","mCreateTime":"2017-10-23 10:09:14","mDeals":1,"mDeleted":false,"mDeviceId":"","mDeviceType":1,"mFollows":1,"mHeadImg":"","mIm":"0","mIntroduction":"","mLabel":"1","mNick":"曾学松","mPassword":"d9b1d7db4cd6e70935368a1efb10e377","mScore":4.5,"mSex":true,"mStatus":1,"mTel":"15919160761","mToken":"","mUpdateTime":"2017-10-25 17:33:27","masterId":1}
             * tfOrderAttach : {"ahBirthday":"1990-09-08 00:00:00","ahBirthplace":"","ahCountryCode":"","ahCreateTime":"","ahDescribe":"","ahName":"qfs","ahOrderId":44,"ahSex":true,"ahTel":"","attachId":0}
             * tfProduct : null
             * tfUser : null
             */

            private int oAmount;
            private String oAppointmentTime;
            private int oBusinessId;
            private String oCancelTime;
            private int oCancelType;
            private String oCompleteTime;
            private String oCreateTime;
            private boolean oIsOrderTotal;
            private String oLogisticsNo;
            private int oMasterId;
            private String oOrderNo;
            private String oOrderTotalNo;
            private int oOrderType;
            private int oPayStatus;
            private String oPayTime;
            private int oPayWay;
            private int oPrice;
            private int oProductId;
            private int oQty;
            private int oStatus;
            private String oUpdateTime;
            private int oUserId;
            private int orderId;
            private TfBusinessBean tfBusiness;
            private TfMasterBean tfMaster;
            private TfOrderAttachBean tfOrderAttach;
            private Object tfProduct;
            private Object tfUser;

            public int getOAmount() {
                return oAmount;
            }

            public void setOAmount(int oAmount) {
                this.oAmount = oAmount;
            }

            public String getOAppointmentTime() {
                return oAppointmentTime;
            }

            public void setOAppointmentTime(String oAppointmentTime) {
                this.oAppointmentTime = oAppointmentTime;
            }

            public int getOBusinessId() {
                return oBusinessId;
            }

            public void setOBusinessId(int oBusinessId) {
                this.oBusinessId = oBusinessId;
            }

            public String getOCancelTime() {
                return oCancelTime;
            }

            public void setOCancelTime(String oCancelTime) {
                this.oCancelTime = oCancelTime;
            }

            public int getOCancelType() {
                return oCancelType;
            }

            public void setOCancelType(int oCancelType) {
                this.oCancelType = oCancelType;
            }

            public String getOCompleteTime() {
                return oCompleteTime;
            }

            public void setOCompleteTime(String oCompleteTime) {
                this.oCompleteTime = oCompleteTime;
            }

            public String getOCreateTime() {
                return oCreateTime;
            }

            public void setOCreateTime(String oCreateTime) {
                this.oCreateTime = oCreateTime;
            }

            public boolean isOIsOrderTotal() {
                return oIsOrderTotal;
            }

            public void setOIsOrderTotal(boolean oIsOrderTotal) {
                this.oIsOrderTotal = oIsOrderTotal;
            }

            public String getOLogisticsNo() {
                return oLogisticsNo;
            }

            public void setOLogisticsNo(String oLogisticsNo) {
                this.oLogisticsNo = oLogisticsNo;
            }

            public int getOMasterId() {
                return oMasterId;
            }

            public void setOMasterId(int oMasterId) {
                this.oMasterId = oMasterId;
            }

            public String getOOrderNo() {
                return oOrderNo;
            }

            public void setOOrderNo(String oOrderNo) {
                this.oOrderNo = oOrderNo;
            }

            public String getOOrderTotalNo() {
                return oOrderTotalNo;
            }

            public void setOOrderTotalNo(String oOrderTotalNo) {
                this.oOrderTotalNo = oOrderTotalNo;
            }

            public int getOOrderType() {
                return oOrderType;
            }

            public void setOOrderType(int oOrderType) {
                this.oOrderType = oOrderType;
            }

            public int getOPayStatus() {
                return oPayStatus;
            }

            public void setOPayStatus(int oPayStatus) {
                this.oPayStatus = oPayStatus;
            }

            public String getOPayTime() {
                return oPayTime;
            }

            public void setOPayTime(String oPayTime) {
                this.oPayTime = oPayTime;
            }

            public int getOPayWay() {
                return oPayWay;
            }

            public void setOPayWay(int oPayWay) {
                this.oPayWay = oPayWay;
            }

            public int getOPrice() {
                return oPrice;
            }

            public void setOPrice(int oPrice) {
                this.oPrice = oPrice;
            }

            public int getOProductId() {
                return oProductId;
            }

            public void setOProductId(int oProductId) {
                this.oProductId = oProductId;
            }

            public int getOQty() {
                return oQty;
            }

            public void setOQty(int oQty) {
                this.oQty = oQty;
            }

            public int getOStatus() {
                return oStatus;
            }

            public void setOStatus(int oStatus) {
                this.oStatus = oStatus;
            }

            public String getOUpdateTime() {
                return oUpdateTime;
            }

            public void setOUpdateTime(String oUpdateTime) {
                this.oUpdateTime = oUpdateTime;
            }

            public int getOUserId() {
                return oUserId;
            }

            public void setOUserId(int oUserId) {
                this.oUserId = oUserId;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public TfBusinessBean getTfBusiness() {
                return tfBusiness;
            }

            public void setTfBusiness(TfBusinessBean tfBusiness) {
                this.tfBusiness = tfBusiness;
            }

            public TfMasterBean getTfMaster() {
                return tfMaster;
            }

            public void setTfMaster(TfMasterBean tfMaster) {
                this.tfMaster = tfMaster;
            }

            public TfOrderAttachBean getTfOrderAttach() {
                return tfOrderAttach;
            }

            public void setTfOrderAttach(TfOrderAttachBean tfOrderAttach) {
                this.tfOrderAttach = tfOrderAttach;
            }

            public Object getTfProduct() {
                return tfProduct;
            }

            public void setTfProduct(Object tfProduct) {
                this.tfProduct = tfProduct;
            }

            public Object getTfUser() {
                return tfUser;
            }

            public void setTfUser(Object tfUser) {
                this.tfUser = tfUser;
            }

            public static class TfBusinessBean {
                /**
                 * bCreateTime : 2017-10-25 17:22:41
                 * bDeals : 0
                 * bIntroduction : 介绍1下我的业务112111
                 * bMasterId : 1
                 * bName : 我的业2务咯1121
                 * bPrice : 3100
                 * bUpdateTime :
                 * businessId : 12
                 */

                private String bCreateTime;
                private int bDeals;
                private String bIntroduction;
                private int bMasterId;
                private String bName;
                private int bPrice;
                private String bUpdateTime;
                private int businessId;

                public String getBCreateTime() {
                    return bCreateTime;
                }

                public void setBCreateTime(String bCreateTime) {
                    this.bCreateTime = bCreateTime;
                }

                public int getBDeals() {
                    return bDeals;
                }

                public void setBDeals(int bDeals) {
                    this.bDeals = bDeals;
                }

                public String getBIntroduction() {
                    return bIntroduction;
                }

                public void setBIntroduction(String bIntroduction) {
                    this.bIntroduction = bIntroduction;
                }

                public int getBMasterId() {
                    return bMasterId;
                }

                public void setBMasterId(int bMasterId) {
                    this.bMasterId = bMasterId;
                }

                public String getBName() {
                    return bName;
                }

                public void setBName(String bName) {
                    this.bName = bName;
                }

                public int getBPrice() {
                    return bPrice;
                }

                public void setBPrice(int bPrice) {
                    this.bPrice = bPrice;
                }

                public String getBUpdateTime() {
                    return bUpdateTime;
                }

                public void setBUpdateTime(String bUpdateTime) {
                    this.bUpdateTime = bUpdateTime;
                }

                public int getBusinessId() {
                    return businessId;
                }

                public void setBusinessId(int businessId) {
                    this.businessId = businessId;
                }
            }

            public static class TfMasterBean {
                /**
                 * mAccid : 0
                 * mAddress : 拱北
                 * mBargain : 1100
                 * mBusinessType : 1
                 * mConstellation : 2
                 * mCountryCode : 86
                 * mCreateTime : 2017-10-23 10:09:14
                 * mDeals : 1
                 * mDeleted : false
                 * mDeviceId :
                 * mDeviceType : 1
                 * mFollows : 1
                 * mHeadImg :
                 * mIm : 0
                 * mIntroduction :
                 * mLabel : 1
                 * mNick : 曾学松
                 * mPassword : d9b1d7db4cd6e70935368a1efb10e377
                 * mScore : 4.5
                 * mSex : true
                 * mStatus : 1
                 * mTel : 15919160761
                 * mToken :
                 * mUpdateTime : 2017-10-25 17:33:27
                 * masterId : 1
                 */

                private int mAccid;
                private String mAddress;
                private int mBargain;
                private String mBusinessType;
                private int mConstellation;
                private String mCountryCode;
                private String mCreateTime;
                private int mDeals;
                private boolean mDeleted;
                private String mDeviceId;
                private int mDeviceType;
                private int mFollows;
                private String mHeadImg;
                private String mIm;
                private String mIntroduction;
                private String mLabel;
                private String mNick;
                private String mPassword;
                private double mScore;
                private boolean mSex;
                private int mStatus;
                private String mTel;
                private String mToken;
                private String mUpdateTime;
                private int masterId;

                public int getMAccid() {
                    return mAccid;
                }

                public void setMAccid(int mAccid) {
                    this.mAccid = mAccid;
                }

                public String getMAddress() {
                    return mAddress;
                }

                public void setMAddress(String mAddress) {
                    this.mAddress = mAddress;
                }

                public int getMBargain() {
                    return mBargain;
                }

                public void setMBargain(int mBargain) {
                    this.mBargain = mBargain;
                }

                public String getMBusinessType() {
                    return mBusinessType;
                }

                public void setMBusinessType(String mBusinessType) {
                    this.mBusinessType = mBusinessType;
                }

                public int getMConstellation() {
                    return mConstellation;
                }

                public void setMConstellation(int mConstellation) {
                    this.mConstellation = mConstellation;
                }

                public String getMCountryCode() {
                    return mCountryCode;
                }

                public void setMCountryCode(String mCountryCode) {
                    this.mCountryCode = mCountryCode;
                }

                public String getMCreateTime() {
                    return mCreateTime;
                }

                public void setMCreateTime(String mCreateTime) {
                    this.mCreateTime = mCreateTime;
                }

                public int getMDeals() {
                    return mDeals;
                }

                public void setMDeals(int mDeals) {
                    this.mDeals = mDeals;
                }

                public boolean isMDeleted() {
                    return mDeleted;
                }

                public void setMDeleted(boolean mDeleted) {
                    this.mDeleted = mDeleted;
                }

                public String getMDeviceId() {
                    return mDeviceId;
                }

                public void setMDeviceId(String mDeviceId) {
                    this.mDeviceId = mDeviceId;
                }

                public int getMDeviceType() {
                    return mDeviceType;
                }

                public void setMDeviceType(int mDeviceType) {
                    this.mDeviceType = mDeviceType;
                }

                public int getMFollows() {
                    return mFollows;
                }

                public void setMFollows(int mFollows) {
                    this.mFollows = mFollows;
                }

                public String getMHeadImg() {
                    return mHeadImg;
                }

                public void setMHeadImg(String mHeadImg) {
                    this.mHeadImg = mHeadImg;
                }

                public String getMIm() {
                    return mIm;
                }

                public void setMIm(String mIm) {
                    this.mIm = mIm;
                }

                public String getMIntroduction() {
                    return mIntroduction;
                }

                public void setMIntroduction(String mIntroduction) {
                    this.mIntroduction = mIntroduction;
                }

                public String getMLabel() {
                    return mLabel;
                }

                public void setMLabel(String mLabel) {
                    this.mLabel = mLabel;
                }

                public String getMNick() {
                    return mNick;
                }

                public void setMNick(String mNick) {
                    this.mNick = mNick;
                }

                public String getMPassword() {
                    return mPassword;
                }

                public void setMPassword(String mPassword) {
                    this.mPassword = mPassword;
                }

                public double getMScore() {
                    return mScore;
                }

                public void setMScore(double mScore) {
                    this.mScore = mScore;
                }

                public boolean isMSex() {
                    return mSex;
                }

                public void setMSex(boolean mSex) {
                    this.mSex = mSex;
                }

                public int getMStatus() {
                    return mStatus;
                }

                public void setMStatus(int mStatus) {
                    this.mStatus = mStatus;
                }

                public String getMTel() {
                    return mTel;
                }

                public void setMTel(String mTel) {
                    this.mTel = mTel;
                }

                public String getMToken() {
                    return mToken;
                }

                public void setMToken(String mToken) {
                    this.mToken = mToken;
                }

                public String getMUpdateTime() {
                    return mUpdateTime;
                }

                public void setMUpdateTime(String mUpdateTime) {
                    this.mUpdateTime = mUpdateTime;
                }

                public int getMasterId() {
                    return masterId;
                }

                public void setMasterId(int masterId) {
                    this.masterId = masterId;
                }
            }

            public static class TfOrderAttachBean {
                /**
                 * ahBirthday : 1990-09-08 00:00:00
                 * ahBirthplace :
                 * ahCountryCode :
                 * ahCreateTime :
                 * ahDescribe :
                 * ahName : qfs
                 * ahOrderId : 44
                 * ahSex : true
                 * ahTel :
                 * attachId : 0
                 */

                private String ahBirthday;
                private String ahBirthplace;
                private String ahCountryCode;
                private String ahCreateTime;
                private String ahDescribe;
                private String ahName;
                private int ahOrderId;
                private boolean ahSex;
                private String ahTel;
                private int attachId;

                public String getAhBirthday() {
                    return ahBirthday;
                }

                public void setAhBirthday(String ahBirthday) {
                    this.ahBirthday = ahBirthday;
                }

                public String getAhBirthplace() {
                    return ahBirthplace;
                }

                public void setAhBirthplace(String ahBirthplace) {
                    this.ahBirthplace = ahBirthplace;
                }

                public String getAhCountryCode() {
                    return ahCountryCode;
                }

                public void setAhCountryCode(String ahCountryCode) {
                    this.ahCountryCode = ahCountryCode;
                }

                public String getAhCreateTime() {
                    return ahCreateTime;
                }

                public void setAhCreateTime(String ahCreateTime) {
                    this.ahCreateTime = ahCreateTime;
                }

                public String getAhDescribe() {
                    return ahDescribe;
                }

                public void setAhDescribe(String ahDescribe) {
                    this.ahDescribe = ahDescribe;
                }

                public String getAhName() {
                    return ahName;
                }

                public void setAhName(String ahName) {
                    this.ahName = ahName;
                }

                public int getAhOrderId() {
                    return ahOrderId;
                }

                public void setAhOrderId(int ahOrderId) {
                    this.ahOrderId = ahOrderId;
                }

                public boolean isAhSex() {
                    return ahSex;
                }

                public void setAhSex(boolean ahSex) {
                    this.ahSex = ahSex;
                }

                public String getAhTel() {
                    return ahTel;
                }

                public void setAhTel(String ahTel) {
                    this.ahTel = ahTel;
                }

                public int getAttachId() {
                    return attachId;
                }

                public void setAttachId(int attachId) {
                    this.attachId = attachId;
                }
            }
        }
    }
}
