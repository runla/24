package com.yinghai.a24divine_user.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author Created by：fanson
 * Created on：2017/9/29 11:40
 * Describe：订单Bean
 */

public class OrderBean  {


    /**
     * code : 1
     * msg : success
     * data : {"tfOrderList":[{"oAmount":500,"oAppointmentTime":"2017-10-24 11:37:00","oBusinessId":1,"oCompleteTime":"","oCreateTime":"2017-10-24 14:56:52","oIsOrderTotal":false,"oLogisticsNo":"","oMasterId":1,"oOrderNo":"2017102414561219831100000","oOrderTotalNo":0,"oOrderType":1,"oPayStatus":1,"oPayTime":"","oPayWay":0,"oPrice":500,"oProductId":0,"oQty":1,"oStatus":1,"oUpdateTime":"","oUserId":1,"orderId":7,"tfBusiness":{"bCreateTime":"2017-10-25 15:32:28","bDeals":0,"bIntroduction":"这是业务介绍","bMasterId":1,"bName":"再来个业务","bPrice":58000,"bUpdateTime":"2017-10-25 17:29:10","businessId":4},"tfMaster":{"mAccid":0,"mAddress":"拱北","mBargain":1100,"mBusinessType":"1","mConstellation":2,"mCountryCode":"86","mCreateTime":"2017-10-23 10:09:14","mDeals":1,"mDeleted":false,"mDeviceId":"","mDeviceType":0,"mFollows":1,"mHeadImg":"","mIntroduction":"","mLabel":"1","mNick":"曾学松","mPassword":"d9b1d7db4cd6e70935368a1efb10e377","mScore":4.5,"mSex":true,"mStatus":1,"mTel":"15919160761","mUpdateTime":"2017-10-25 17:33:27","masterId":1},"tfOrderAttach":{"ahBirthday":"2017-10-24 00:00:00","ahBirthplace":"","ahCountryCode":"","ahCreateTime":"","ahDescribe":"","ahName":"nihao","ahOrderId":7,"ahSex":true,"ahTel":"","attachId":0},"tfProduct":{"pAttribution":"浙江金华","pCreateTime":"2017-10-24 13:40:39","pDeals":0,"pDelete":false,"pFreeShipping":true,"pHot":0,"pImg":"","pIntroduction":"","pMasterId":2,"pName":"名木香府佛螺菩提子手串 金刚满肉红皮佛珠原籽念珠男女文玩木珠 17mm*13颗","pOffline":false,"pPrice":499,"pSize":"13-17毫米（13-17mm）","pTotal":7,"pUpdateTime":"2017-10-26 13:42:18","productId":2},"tfUser":{"uAccid":1,"uBirthday":"","uConstellation":9,"uCountryCode":"86","uCreateTime":"2017-10-20 11:34:01","uDeleted":false,"uDeviceId":"","uDeviceType":1,"uNick":"小星星","uPassword":"","uSex":true,"uStatus":0,"uImgUrl":"","uTel":"15811674190","uUpdateTime":"2017-10-20 11:34:06","userId":1}},{"oAmount":1497,"oAppointmentTime":"","oBusinessId":0,"oCompleteTime":"","oCreateTime":"2017-10-26 13:42:18","oIsOrderTotal":true,"oLogisticsNo":"","oMasterId":2,"oOrderNo":"p201710261342171795437","oOrderTotalNo":2,"oOrderType":3,"oPayStatus":1,"oPayTime":"","oPayWay":0,"oPrice":499,"oProductId":2,"oQty":3,"oStatus":1,"oUpdateTime":"","oUserId":1,"orderId":14,"tfBusiness":null,"tfMaster":{"mAccid":0,"mAddress":"珠海","mBargain":1000,"mBusinessType":"1","mConstellation":2,"mCountryCode":"86","mCreateTime":"2017-10-30 15:28:03","mDeals":1,"mDeleted":false,"mDeviceId":"","mDeviceType":0,"mFollows":1,"mHeadImg":"","mIntroduction":"","mLabel":"1","mNick":"QFS","mPassword":"11","mScore":4.5,"mSex":true,"mStatus":1,"mTel":"13726277953","mUpdateTime":"2017-11-02 09:47:36","masterId":2},"tfOrderAttach":null,"tfProduct":{"pAttribution":"浙江金华","pCreateTime":"2017-10-24 13:40:39","pDeals":0,"pDelete":false,"pFreeShipping":true,"pHot":0,"pImg":"","pIntroduction":"","pMasterId":2,"pName":"名木香府佛螺菩提子手串 金刚满肉红皮佛珠原籽念珠男女文玩木珠 17mm*13颗","pOffline":false,"pPrice":499,"pSize":"13-17毫米（13-17mm）","pTotal":7,"pUpdateTime":"2017-10-26 13:42:18","productId":2},"tfUser":{"uAccid":1,"uBirthday":"","uConstellation":9,"uCountryCode":"86","uCreateTime":"2017-10-20 11:34:01","uDeleted":false,"uDeviceId":"","uDeviceType":1,"uNick":"小星星","uPassword":"","uSex":true,"uStatus":0,"uTel":"15811674190","uUpdateTime":"2017-10-20 11:34:06","userId":1}}],"total":4,"pages":4,"pageSize":1,"pageNum":2}
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
         * tfOrderList : [{"oAmount":500,"oAppointmentTime":"2017-10-24 11:37:00","oBusinessId":1,"oCompleteTime":"","oCreateTime":"2017-10-24 14:56:52","oIsOrderTotal":false,"oLogisticsNo":"","oMasterId":1,"oOrderNo":"2017102414561219831100000","oOrderTotalNo":0,"oOrderType":1,"oPayStatus":1,"oPayTime":"","oPayWay":0,"oPrice":500,"oProductId":0,"oQty":1,"oStatus":1,"oUpdateTime":"","oUserId":1,"orderId":7,"tfBusiness":{"bCreateTime":"2017-10-25 15:32:28","bDeals":0,"bIntroduction":"这是业务介绍","bMasterId":1,"bName":"再来个业务","bPrice":58000,"bUpdateTime":"2017-10-25 17:29:10","businessId":4},"tfMaster":{"mAccid":0,"mAddress":"拱北","mBargain":1100,"mBusinessType":"1","mConstellation":2,"mCountryCode":"86","mCreateTime":"2017-10-23 10:09:14","mDeals":1,"mDeleted":false,"mDeviceId":"","mDeviceType":0,"mFollows":1,"mHeadImg":"","mIntroduction":"","mLabel":"1","mNick":"曾学松","mPassword":"d9b1d7db4cd6e70935368a1efb10e377","mScore":4.5,"mSex":true,"mStatus":1,"mTel":"15919160761","mUpdateTime":"2017-10-25 17:33:27","masterId":1},"tfOrderAttach":{"ahBirthday":"2017-10-24 00:00:00","ahBirthplace":"","ahCountryCode":"","ahCreateTime":"","ahDescribe":"","ahName":"nihao","ahOrderId":7,"ahSex":true,"ahTel":"","attachId":0},"tfProduct":{"pAttribution":"浙江金华","pCreateTime":"2017-10-24 13:40:39","pDeals":0,"pDelete":false,"pFreeShipping":true,"pHot":0,"pImg":"","pIntroduction":"","pMasterId":2,"pName":"名木香府佛螺菩提子手串 金刚满肉红皮佛珠原籽念珠男女文玩木珠 17mm*13颗","pOffline":false,"pPrice":499,"pSize":"13-17毫米（13-17mm）","pTotal":7,"pUpdateTime":"2017-10-26 13:42:18","productId":2},"tfUser":{"uAccid":1,"uBirthday":"","uConstellation":9,"uCountryCode":"86","uCreateTime":"2017-10-20 11:34:01","uDeleted":false,"uDeviceId":"","uDeviceType":1,"uNick":"小星星","uPassword":"","uSex":true,"uStatus":0,"uImgUrl":"","uTel":"15811674190","uUpdateTime":"2017-10-20 11:34:06","userId":1}},{"oAmount":1497,"oAppointmentTime":"","oBusinessId":0,"oCompleteTime":"","oCreateTime":"2017-10-26 13:42:18","oIsOrderTotal":true,"oLogisticsNo":"","oMasterId":2,"oOrderNo":"p201710261342171795437","oOrderTotalNo":2,"oOrderType":3,"oPayStatus":1,"oPayTime":"","oPayWay":0,"oPrice":499,"oProductId":2,"oQty":3,"oStatus":1,"oUpdateTime":"","oUserId":1,"orderId":14,"tfBusiness":null,"tfMaster":{"mAccid":0,"mAddress":"珠海","mBargain":1000,"mBusinessType":"1","mConstellation":2,"mCountryCode":"86","mCreateTime":"2017-10-30 15:28:03","mDeals":1,"mDeleted":false,"mDeviceId":"","mDeviceType":0,"mFollows":1,"mHeadImg":"","mIntroduction":"","mLabel":"1","mNick":"QFS","mPassword":"11","mScore":4.5,"mSex":true,"mStatus":1,"mTel":"13726277953","mUpdateTime":"2017-11-02 09:47:36","masterId":2},"tfOrderAttach":null,"tfProduct":{"pAttribution":"浙江金华","pCreateTime":"2017-10-24 13:40:39","pDeals":0,"pDelete":false,"pFreeShipping":true,"pHot":0,"pImg":"","pIntroduction":"","pMasterId":2,"pName":"名木香府佛螺菩提子手串 金刚满肉红皮佛珠原籽念珠男女文玩木珠 17mm*13颗","pOffline":false,"pPrice":499,"pSize":"13-17毫米（13-17mm）","pTotal":7,"pUpdateTime":"2017-10-26 13:42:18","productId":2},"tfUser":{"uAccid":1,"uBirthday":"","uConstellation":9,"uCountryCode":"86","uCreateTime":"2017-10-20 11:34:01","uDeleted":false,"uDeviceId":"","uDeviceType":1,"uNick":"小星星","uPassword":"","uSex":true,"uStatus":0,"uTel":"15811674190","uUpdateTime":"2017-10-20 11:34:06","userId":1}}]
         * total : 4
         * pages : 4
         * pageSize : 1
         * pageNum : 2
         */

        private int total;
        private int pages;
        private int pageSize;
        private int pageNum;
        private List<TfOrderListBean> tfOrderList;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public List<TfOrderListBean> getTfOrderList() {
            return tfOrderList;
        }

        public void setTfOrderList(List<TfOrderListBean> tfOrderList) {
            this.tfOrderList = tfOrderList;
        }

        public static class TfOrderListBean implements Parcelable{
            /**
             * oAmount : 500
             * oAppointmentTime : 2017-10-24 11:37:00
             * oBusinessId : 1
             * oCompleteTime :
             * oCreateTime : 2017-10-24 14:56:52
             * oIsOrderTotal : false
             * oLogisticsNo :
             * oMasterId : 1
             * oOrderNo : 2017102414561219831100000
             * oOrderTotalNo : 0
             * oOrderType : 1
             * oPayStatus : 1
             * oPayTime :
             * oPayWay : 0
             * oPrice : 500
             * oProductId : 0
             * oQty : 1
             * oStatus : 1
             * oUpdateTime :
             * oUserId : 1
             * orderId : 7
             * tfBusiness : {"bCreateTime":"2017-10-25 15:32:28","bDeals":0,"bIntroduction":"这是业务介绍","bMasterId":1,"bName":"再来个业务","bPrice":58000,"bUpdateTime":"2017-10-25 17:29:10","businessId":4}
             * tfMaster : {"mAccid":0,"mAddress":"拱北","mBargain":1100,"mBusinessType":"1","mConstellation":2,"mCountryCode":"86","mCreateTime":"2017-10-23 10:09:14","mDeals":1,"mDeleted":false,"mDeviceId":"","mDeviceType":0,"mFollows":1,"mHeadImg":"","mIntroduction":"","mLabel":"1","mNick":"曾学松","mPassword":"d9b1d7db4cd6e70935368a1efb10e377","mScore":4.5,"mSex":true,"mStatus":1,"mTel":"15919160761","mUpdateTime":"2017-10-25 17:33:27","masterId":1}
             * tfOrderAttach : {"ahBirthday":"2017-10-24 00:00:00","ahBirthplace":"","ahCountryCode":"","ahCreateTime":"","ahDescribe":"","ahName":"nihao","ahOrderId":7,"ahSex":true,"ahTel":"","attachId":0}
             * tfProduct : {"pAttribution":"浙江金华","pCreateTime":"2017-10-24 13:40:39","pDeals":0,"pDelete":false,"pFreeShipping":true,"pHot":0,"pImg":"","pIntroduction":"","pMasterId":2,"pName":"名木香府佛螺菩提子手串 金刚满肉红皮佛珠原籽念珠男女文玩木珠 17mm*13颗","pOffline":false,"pPrice":499,"pSize":"13-17毫米（13-17mm）","pTotal":7,"pUpdateTime":"2017-10-26 13:42:18","productId":2}
             * tfUser : {"uAccid":1,"uBirthday":"","uConstellation":9,"uCountryCode":"86","uCreateTime":"2017-10-20 11:34:01","uDeleted":false,"uDeviceId":"","uDeviceType":1,"uNick":"小星星","uPassword":"","uSex":true,"uStatus":0,"uImgUrl":"","uTel":"15811674190","uUpdateTime":"2017-10-20 11:34:06","userId":1}
             */

            private int oAmount;
            private String oAppointmentTime;
            private int oBusinessId;
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
            private TfProductBean tfProduct;
            private TfUserBean tfUser;

            protected TfOrderListBean(Parcel in) {
                oAmount = in.readInt();
                oAppointmentTime = in.readString();
                oBusinessId = in.readInt();
                oCompleteTime = in.readString();
                oCreateTime = in.readString();
                oIsOrderTotal = in.readByte() != 0;
                oLogisticsNo = in.readString();
                oMasterId = in.readInt();
                oOrderNo = in.readString();
                oOrderTotalNo = in.readString();
                oOrderType = in.readInt();
                oPayStatus = in.readInt();
                oPayTime = in.readString();
                oPayWay = in.readInt();
                oPrice = in.readInt();
                oProductId = in.readInt();
                oQty = in.readInt();
                oStatus = in.readInt();
                oUpdateTime = in.readString();
                oUserId = in.readInt();
                orderId = in.readInt();
                tfBusiness = in.readParcelable(TfBusinessBean.class.getClassLoader());
                tfMaster = in.readParcelable(TfMasterBean.class.getClassLoader());
                tfOrderAttach = in.readParcelable(TfOrderAttachBean.class.getClassLoader());
                tfProduct = in.readParcelable(TfProductBean.class.getClassLoader());
                tfUser = in.readParcelable(TfUserBean.class.getClassLoader());
            }

            public static final Creator<TfOrderListBean> CREATOR = new Creator<TfOrderListBean>() {
                @Override
                public TfOrderListBean createFromParcel(Parcel in) {
                    return new TfOrderListBean(in);
                }

                @Override
                public TfOrderListBean[] newArray(int size) {
                    return new TfOrderListBean[size];
                }
            };

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

            public TfProductBean getTfProduct() {
                return tfProduct;
            }

            public void setTfProduct(TfProductBean tfProduct) {
                this.tfProduct = tfProduct;
            }

            public TfUserBean getTfUser() {
                return tfUser;
            }

            public void setTfUser(TfUserBean tfUser) {
                this.tfUser = tfUser;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(oAmount);
                parcel.writeString(oAppointmentTime);
                parcel.writeInt(oBusinessId);
                parcel.writeString(oCompleteTime);
                parcel.writeString(oCreateTime);
                parcel.writeByte((byte) (oIsOrderTotal ? 1 : 0));
                parcel.writeString(oLogisticsNo);
                parcel.writeInt(oMasterId);
                parcel.writeString(oOrderNo);
                parcel.writeString(oOrderTotalNo);
                parcel.writeInt(oOrderType);
                parcel.writeInt(oPayStatus);
                parcel.writeString(oPayTime);
                parcel.writeInt(oPayWay);
                parcel.writeInt(oPrice);
                parcel.writeInt(oProductId);
                parcel.writeInt(oQty);
                parcel.writeInt(oStatus);
                parcel.writeString(oUpdateTime);
                parcel.writeInt(oUserId);
                parcel.writeInt(orderId);
                parcel.writeParcelable(tfBusiness, i);
                parcel.writeParcelable(tfMaster, i);
                parcel.writeParcelable(tfOrderAttach, i);
                parcel.writeParcelable(tfProduct, i);
                parcel.writeParcelable(tfUser, i);
            }

            public static class TfBusinessBean implements Parcelable{
                /**
                 * bCreateTime : 2017-10-25 15:32:28
                 * bDeals : 0
                 * bIntroduction : 这是业务介绍
                 * bMasterId : 1
                 * bName : 再来个业务
                 * bPrice : 58000
                 * bUpdateTime : 2017-10-25 17:29:10
                 * businessId : 4
                 */

                private String bCreateTime;
                private int bDeals;
                private String bIntroduction;
                private int bMasterId;
                private String bName;
                private int bPrice;
                private String bUpdateTime;
                private int businessId;

                protected TfBusinessBean(Parcel in) {
                    bCreateTime = in.readString();
                    bDeals = in.readInt();
                    bIntroduction = in.readString();
                    bMasterId = in.readInt();
                    bName = in.readString();
                    bPrice = in.readInt();
                    bUpdateTime = in.readString();
                    businessId = in.readInt();
                }

                public static final Creator<TfBusinessBean> CREATOR = new Creator<TfBusinessBean>() {
                    @Override
                    public TfBusinessBean createFromParcel(Parcel in) {
                        return new TfBusinessBean(in);
                    }

                    @Override
                    public TfBusinessBean[] newArray(int size) {
                        return new TfBusinessBean[size];
                    }
                };

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

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel parcel, int i) {
                    parcel.writeString(bCreateTime);
                    parcel.writeInt(bDeals);
                    parcel.writeString(bIntroduction);
                    parcel.writeInt(bMasterId);
                    parcel.writeString(bName);
                    parcel.writeInt(bPrice);
                    parcel.writeString(bUpdateTime);
                    parcel.writeInt(businessId);
                }
            }

            public static class TfMasterBean implements Parcelable{
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
                 * mDeviceType : 0
                 * mFollows : 1
                 * mHeadImg :
                 * mIntroduction :
                 * mLabel : 1
                 * mNick : 曾学松
                 * mPassword : d9b1d7db4cd6e70935368a1efb10e377
                 * mScore : 4.5
                 * mSex : true
                 * mStatus : 1
                 * mTel : 15919160761
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
                private String mIntroduction;
                private String mLabel;
                private String mNick;
                private String mPassword;
                private double mScore;
                private boolean mSex;
                private int mStatus;
                private String mTel;
                private String mUpdateTime;
                private int masterId;

                protected TfMasterBean(Parcel in) {
                    mAccid = in.readInt();
                    mAddress = in.readString();
                    mBargain = in.readInt();
                    mBusinessType = in.readString();
                    mConstellation = in.readInt();
                    mCountryCode = in.readString();
                    mCreateTime = in.readString();
                    mDeals = in.readInt();
                    mDeleted = in.readByte() != 0;
                    mDeviceId = in.readString();
                    mDeviceType = in.readInt();
                    mFollows = in.readInt();
                    mHeadImg = in.readString();
                    mIntroduction = in.readString();
                    mLabel = in.readString();
                    mNick = in.readString();
                    mPassword = in.readString();
                    mScore = in.readDouble();
                    mSex = in.readByte() != 0;
                    mStatus = in.readInt();
                    mTel = in.readString();
                    mUpdateTime = in.readString();
                    masterId = in.readInt();
                }

                public static final Creator<TfMasterBean> CREATOR = new Creator<TfMasterBean>() {
                    @Override
                    public TfMasterBean createFromParcel(Parcel in) {
                        return new TfMasterBean(in);
                    }

                    @Override
                    public TfMasterBean[] newArray(int size) {
                        return new TfMasterBean[size];
                    }
                };

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

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel parcel, int i) {
                    parcel.writeInt(mAccid);
                    parcel.writeString(mAddress);
                    parcel.writeInt(mBargain);
                    parcel.writeString(mBusinessType);
                    parcel.writeInt(mConstellation);
                    parcel.writeString(mCountryCode);
                    parcel.writeString(mCreateTime);
                    parcel.writeInt(mDeals);
                    parcel.writeByte((byte) (mDeleted ? 1 : 0));
                    parcel.writeString(mDeviceId);
                    parcel.writeInt(mDeviceType);
                    parcel.writeInt(mFollows);
                    parcel.writeString(mHeadImg);
                    parcel.writeString(mIntroduction);
                    parcel.writeString(mLabel);
                    parcel.writeString(mNick);
                    parcel.writeString(mPassword);
                    parcel.writeDouble(mScore);
                    parcel.writeByte((byte) (mSex ? 1 : 0));
                    parcel.writeInt(mStatus);
                    parcel.writeString(mTel);
                    parcel.writeString(mUpdateTime);
                    parcel.writeInt(masterId);
                }
            }

            public static class TfOrderAttachBean implements Parcelable{
                /**
                 * ahBirthday : 2017-10-24 00:00:00
                 * ahBirthplace :
                 * ahCountryCode :
                 * ahCreateTime :
                 * ahDescribe :
                 * ahName : nihao
                 * ahOrderId : 7
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

                protected TfOrderAttachBean(Parcel in) {
                    ahBirthday = in.readString();
                    ahBirthplace = in.readString();
                    ahCountryCode = in.readString();
                    ahCreateTime = in.readString();
                    ahDescribe = in.readString();
                    ahName = in.readString();
                    ahOrderId = in.readInt();
                    ahSex = in.readByte() != 0;
                    ahTel = in.readString();
                    attachId = in.readInt();
                }

                public static final Creator<TfOrderAttachBean> CREATOR = new Creator<TfOrderAttachBean>() {
                    @Override
                    public TfOrderAttachBean createFromParcel(Parcel in) {
                        return new TfOrderAttachBean(in);
                    }

                    @Override
                    public TfOrderAttachBean[] newArray(int size) {
                        return new TfOrderAttachBean[size];
                    }
                };

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

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel parcel, int i) {
                    parcel.writeString(ahBirthday);
                    parcel.writeString(ahBirthplace);
                    parcel.writeString(ahCountryCode);
                    parcel.writeString(ahCreateTime);
                    parcel.writeString(ahDescribe);
                    parcel.writeString(ahName);
                    parcel.writeInt(ahOrderId);
                    parcel.writeByte((byte) (ahSex ? 1 : 0));
                    parcel.writeString(ahTel);
                    parcel.writeInt(attachId);
                }
            }

            public static class TfProductBean implements Parcelable{
                /**
                 * pAttribution : 浙江金华
                 * pCreateTime : 2017-10-24 13:40:39
                 * pDeals : 0
                 * pDelete : false
                 * pFreeShipping : true
                 * pHot : 0
                 * pImg :
                 * pIntroduction :
                 * pMasterId : 2
                 * pName : 名木香府佛螺菩提子手串 金刚满肉红皮佛珠原籽念珠男女文玩木珠 17mm*13颗
                 * pOffline : false
                 * pPrice : 499
                 * pSize : 13-17毫米（13-17mm）
                 * pTotal : 7
                 * pUpdateTime : 2017-10-26 13:42:18
                 * productId : 2
                 */

                private String pAttribution;
                private String pCreateTime;
                private int pDeals;
                private boolean pDelete;
                private boolean pFreeShipping;
                private int pHot;
                private String pImg;
                private String pIntroduction;
                private int pMasterId;
                private String pName;
                private boolean pOffline;
                private int pPrice;
                private String pSize;
                private int pTotal;
                private String pUpdateTime;
                private int productId;
                private List<ProductBean.DataBean.ProductListBean.ImgListBean> imgList;

                public List<ProductBean.DataBean.ProductListBean.ImgListBean> getImgList() {
                    return imgList;
                }

                public void setImgList(List<ProductBean.DataBean.ProductListBean.ImgListBean> imgList) {
                    this.imgList = imgList;
                }

                protected TfProductBean(Parcel in) {
                    pAttribution = in.readString();
                    pCreateTime = in.readString();
                    pDeals = in.readInt();
                    pDelete = in.readByte() != 0;
                    pFreeShipping = in.readByte() != 0;
                    pHot = in.readInt();
                    pImg = in.readString();
                    pIntroduction = in.readString();
                    pMasterId = in.readInt();
                    pName = in.readString();
                    pOffline = in.readByte() != 0;
                    pPrice = in.readInt();
                    pSize = in.readString();
                    pTotal = in.readInt();
                    pUpdateTime = in.readString();
                    productId = in.readInt();
                    imgList = in.createTypedArrayList(ProductBean.DataBean.ProductListBean.ImgListBean.CREATOR);
                }

                public static final Creator<TfProductBean> CREATOR = new Creator<TfProductBean>() {
                    @Override
                    public TfProductBean createFromParcel(Parcel in) {
                        return new TfProductBean(in);
                    }

                    @Override
                    public TfProductBean[] newArray(int size) {
                        return new TfProductBean[size];
                    }
                };

                public String getPAttribution() {
                    return pAttribution;
                }

                public void setPAttribution(String pAttribution) {
                    this.pAttribution = pAttribution;
                }

                public String getPCreateTime() {
                    return pCreateTime;
                }

                public void setPCreateTime(String pCreateTime) {
                    this.pCreateTime = pCreateTime;
                }

                public int getPDeals() {
                    return pDeals;
                }

                public void setPDeals(int pDeals) {
                    this.pDeals = pDeals;
                }

                public boolean isPDelete() {
                    return pDelete;
                }

                public void setPDelete(boolean pDelete) {
                    this.pDelete = pDelete;
                }

                public boolean isPFreeShipping() {
                    return pFreeShipping;
                }

                public void setPFreeShipping(boolean pFreeShipping) {
                    this.pFreeShipping = pFreeShipping;
                }

                public int getPHot() {
                    return pHot;
                }

                public void setPHot(int pHot) {
                    this.pHot = pHot;
                }

                public String getPImg() {
                    return pImg;
                }

                public void setPImg(String pImg) {
                    this.pImg = pImg;
                }

                public String getPIntroduction() {
                    return pIntroduction;
                }

                public void setPIntroduction(String pIntroduction) {
                    this.pIntroduction = pIntroduction;
                }

                public int getPMasterId() {
                    return pMasterId;
                }

                public void setPMasterId(int pMasterId) {
                    this.pMasterId = pMasterId;
                }

                public String getPName() {
                    return pName;
                }

                public void setPName(String pName) {
                    this.pName = pName;
                }

                public boolean isPOffline() {
                    return pOffline;
                }

                public void setPOffline(boolean pOffline) {
                    this.pOffline = pOffline;
                }

                public int getPPrice() {
                    return pPrice;
                }

                public void setPPrice(int pPrice) {
                    this.pPrice = pPrice;
                }

                public String getPSize() {
                    return pSize;
                }

                public void setPSize(String pSize) {
                    this.pSize = pSize;
                }

                public int getPTotal() {
                    return pTotal;
                }

                public void setPTotal(int pTotal) {
                    this.pTotal = pTotal;
                }

                public String getPUpdateTime() {
                    return pUpdateTime;
                }

                public void setPUpdateTime(String pUpdateTime) {
                    this.pUpdateTime = pUpdateTime;
                }

                public int getProductId() {
                    return productId;
                }

                public void setProductId(int productId) {
                    this.productId = productId;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel parcel, int i) {
                    parcel.writeString(pAttribution);
                    parcel.writeString(pCreateTime);
                    parcel.writeInt(pDeals);
                    parcel.writeByte((byte) (pDelete ? 1 : 0));
                    parcel.writeByte((byte) (pFreeShipping ? 1 : 0));
                    parcel.writeInt(pHot);
                    parcel.writeString(pImg);
                    parcel.writeString(pIntroduction);
                    parcel.writeInt(pMasterId);
                    parcel.writeString(pName);
                    parcel.writeByte((byte) (pOffline ? 1 : 0));
                    parcel.writeInt(pPrice);
                    parcel.writeString(pSize);
                    parcel.writeInt(pTotal);
                    parcel.writeString(pUpdateTime);
                    parcel.writeInt(productId);
                    parcel.writeTypedList(imgList);
                }
            }

            public static class TfUserBean implements Parcelable{
                /**
                 * uAccid : 1
                 * uBirthday :
                 * uConstellation : 9
                 * uCountryCode : 86
                 * uCreateTime : 2017-10-20 11:34:01
                 * uDeleted : false
                 * uDeviceId :
                 * uDeviceType : 1
                 * uNick : 小星星
                 * uPassword :
                 * uSex : true
                 * uStatus : 0
                 * uImgUrl :
                 * uTel : 15811674190
                 * uUpdateTime : 2017-10-20 11:34:06
                 * userId : 1
                 */

                private int uAccid;
                private String uBirthday;
                private int uConstellation;
                private String uCountryCode;
                private String uCreateTime;
                private boolean uDeleted;
                private String uDeviceId;
                private int uDeviceType;
                private String uNick;
                private String uPassword;
                private boolean uSex;
                private int uStatus;
                private String uImgUrl;
                private String uTel;
                private String uUpdateTime;
                private int userId;

                protected TfUserBean(Parcel in) {
                    uAccid = in.readInt();
                    uBirthday = in.readString();
                    uConstellation = in.readInt();
                    uCountryCode = in.readString();
                    uCreateTime = in.readString();
                    uDeleted = in.readByte() != 0;
                    uDeviceId = in.readString();
                    uDeviceType = in.readInt();
                    uNick = in.readString();
                    uPassword = in.readString();
                    uSex = in.readByte() != 0;
                    uStatus = in.readInt();
                    uImgUrl = in.readString();
                    uTel = in.readString();
                    uUpdateTime = in.readString();
                    userId = in.readInt();
                }

                public static final Creator<TfUserBean> CREATOR = new Creator<TfUserBean>() {
                    @Override
                    public TfUserBean createFromParcel(Parcel in) {
                        return new TfUserBean(in);
                    }

                    @Override
                    public TfUserBean[] newArray(int size) {
                        return new TfUserBean[size];
                    }
                };

                public int getUAccid() {
                    return uAccid;
                }

                public void setUAccid(int uAccid) {
                    this.uAccid = uAccid;
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

                public String getUCreateTime() {
                    return uCreateTime;
                }

                public void setUCreateTime(String uCreateTime) {
                    this.uCreateTime = uCreateTime;
                }

                public boolean isUDeleted() {
                    return uDeleted;
                }

                public void setUDeleted(boolean uDeleted) {
                    this.uDeleted = uDeleted;
                }

                public String getUDeviceId() {
                    return uDeviceId;
                }

                public void setUDeviceId(String uDeviceId) {
                    this.uDeviceId = uDeviceId;
                }

                public int getUDeviceType() {
                    return uDeviceType;
                }

                public void setUDeviceType(int uDeviceType) {
                    this.uDeviceType = uDeviceType;
                }

                public String getUNick() {
                    return uNick;
                }

                public void setUNick(String uNick) {
                    this.uNick = uNick;
                }

                public String getUPassword() {
                    return uPassword;
                }

                public void setUPassword(String uPassword) {
                    this.uPassword = uPassword;
                }

                public boolean isUSex() {
                    return uSex;
                }

                public void setUSex(boolean uSex) {
                    this.uSex = uSex;
                }

                public int getUStatus() {
                    return uStatus;
                }

                public void setUStatus(int uStatus) {
                    this.uStatus = uStatus;
                }

                public String getUImgUrl() {
                    return uImgUrl;
                }

                public void setUImgUrl(String uImgUrl) {
                    this.uImgUrl = uImgUrl;
                }

                public String getUTel() {
                    return uTel;
                }

                public void setUTel(String uTel) {
                    this.uTel = uTel;
                }

                public String getUUpdateTime() {
                    return uUpdateTime;
                }

                public void setUUpdateTime(String uUpdateTime) {
                    this.uUpdateTime = uUpdateTime;
                }

                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel parcel, int i) {
                    parcel.writeInt(uAccid);
                    parcel.writeString(uBirthday);
                    parcel.writeInt(uConstellation);
                    parcel.writeString(uCountryCode);
                    parcel.writeString(uCreateTime);
                    parcel.writeByte((byte) (uDeleted ? 1 : 0));
                    parcel.writeString(uDeviceId);
                    parcel.writeInt(uDeviceType);
                    parcel.writeString(uNick);
                    parcel.writeString(uPassword);
                    parcel.writeByte((byte) (uSex ? 1 : 0));
                    parcel.writeInt(uStatus);
                    parcel.writeString(uImgUrl);
                    parcel.writeString(uTel);
                    parcel.writeString(uUpdateTime);
                    parcel.writeInt(userId);
                }
            }
        }
    }
}
