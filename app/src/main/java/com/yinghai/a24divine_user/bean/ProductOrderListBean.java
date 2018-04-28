package com.yinghai.a24divine_user.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author Created by：fanson
 *         Created Time: 2017/12/13 11:45
 *         Describe：商品订单列表
 */

public class ProductOrderListBean {


    /**
     * code : 1
     * msg : success
     * data : {"tfOrderList":[{"address":{"addressId":39,"asConsigneeName":"测试","asCountryCode":"86","asCreateTime":"2017-12-06 16:03:31","asDefault":true,"asDetails":"广东省珠海市香洲区","asSex":false,"asStreet":"辽宁省沈阳市沈河区","asTel":"7766","asUpdateTime":"","asUserId":5},"orderList":[{"oAmount":4400,"oAppointmentTime":"","oBusinessId":0,"oCancelTime":"","oCancelType":0,"oCompleteTime":"","oCreateTime":"2017-12-13 12:40:19","oIsOrderTotal":true,"oLogisticsNo":"","oMasterId":2,"oOrderNo":"201712131240195837178","oOrderTotalNo":"2017121312401952923","oOrderType":3,"oPayStatus":1,"oPayTime":"","oPayWay":0,"oPrice":2200,"oProductId":62,"oQty":2,"oStatus":1,"oUpdateTime":"","oUserId":5,"orderId":362,"statusArray":[],"tfBusiness":null,"tfMaster":{"isCollection":false,"isSubscribe":false,"mAccid":0,"mAddress":"珠海","mBargain":1,"mBusinessType":"1","mConstellation":8,"mCountryCode":"86","mCreateTime":"2017-10-30 15:28:03","mDeals":1,"mDeleted":false,"mDeviceId":"99000471207354","mDeviceType":2,"mFollows":15,"mHeadImg":"/file/images/master/137262779531513063693585.png","mIm":"master2","mIntroduction":"","mLabel":"1","mNick":"邱逢生","mPassword":"11","mScore":4.5,"mSex":false,"mStatus":999,"mTel":"13726277953","mToken":"xEszEMWqDdePpPcuOputSNOtkZXW2UD8UGPslFcbY2C8V2MpjT9KIoXBb0dG3SMKz0cgovKcMMWwkC7O9HThLQ==","mUpdateTime":"2017-12-12 15:28:39","masterId":2,"queryUserId":0},"tfOrderAttach":null,"tfProduct":{"imgList":[{"imgTmpId":503,"itAbsolute":"","itAppPath":"/storage/emulated/0/Android/data/com.yinghai.a24divine_master/cache/luban_disk_cache/1513062014604628.png","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itSize":"","itType":1,"itUpdateTime":"","itUrl":"/file/images/product/2/vOcgc_1513062014604628.png"}],"isCollection":false,"pAttribution":"珠海","pCreateTime":"2017-12-12 15:00:13","pDeals":0,"pDelete":false,"pFreeShipping":true,"pHot":0,"pImg":"","pIntroduction":"","pMasterId":2,"pName":"测试","pOffline":false,"pPrice":2200,"pSize":"55588","pTotal":0,"pUpdateTime":"2017-12-13 13:30:47","productId":62,"userId":0},"tfUser":null}],"tAddressId":39,"tAmount":4400,"tCreateTime":"2017-12-13 12:40:19","tOrderNo":"2017121312401952923","tUserId":5,"totalId":66}]}
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
        private List<TfOrderListBean> tfOrderList;

        public List<TfOrderListBean> getTfOrderList() {
            return tfOrderList;
        }

        public void setTfOrderList(List<TfOrderListBean> tfOrderList) {
            this.tfOrderList = tfOrderList;
        }

        public static class TfOrderListBean implements Parcelable{
            /**
             * address : {"addressId":39,"asConsigneeName":"测试","asCountryCode":"86","asCreateTime":"2017-12-06 16:03:31","asDefault":true,"asDetails":"广东省珠海市香洲区","asSex":false,"asStreet":"辽宁省沈阳市沈河区","asTel":"7766","asUpdateTime":"","asUserId":5}
             * orderList : [{"oAmount":4400,"oAppointmentTime":"","oBusinessId":0,"oCancelTime":"","oCancelType":0,"oCompleteTime":"","oCreateTime":"2017-12-13 12:40:19","oIsOrderTotal":true,"oLogisticsNo":"","oMasterId":2,"oOrderNo":"201712131240195837178","oOrderTotalNo":"2017121312401952923","oOrderType":3,"oPayStatus":1,"oPayTime":"","oPayWay":0,"oPrice":2200,"oProductId":62,"oQty":2,"oStatus":1,"oUpdateTime":"","oUserId":5,"orderId":362,"statusArray":[],"tfBusiness":null,"tfMaster":{"isCollection":false,"isSubscribe":false,"mAccid":0,"mAddress":"珠海","mBargain":1,"mBusinessType":"1","mConstellation":8,"mCountryCode":"86","mCreateTime":"2017-10-30 15:28:03","mDeals":1,"mDeleted":false,"mDeviceId":"99000471207354","mDeviceType":2,"mFollows":15,"mHeadImg":"/file/images/master/137262779531513063693585.png","mIm":"master2","mIntroduction":"","mLabel":"1","mNick":"邱逢生","mPassword":"11","mScore":4.5,"mSex":false,"mStatus":999,"mTel":"13726277953","mToken":"xEszEMWqDdePpPcuOputSNOtkZXW2UD8UGPslFcbY2C8V2MpjT9KIoXBb0dG3SMKz0cgovKcMMWwkC7O9HThLQ==","mUpdateTime":"2017-12-12 15:28:39","masterId":2,"queryUserId":0},"tfOrderAttach":null,"tfProduct":{"imgList":[{"imgTmpId":503,"itAbsolute":"","itAppPath":"/storage/emulated/0/Android/data/com.yinghai.a24divine_master/cache/luban_disk_cache/1513062014604628.png","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itSize":"","itType":1,"itUpdateTime":"","itUrl":"/file/images/product/2/vOcgc_1513062014604628.png"}],"isCollection":false,"pAttribution":"珠海","pCreateTime":"2017-12-12 15:00:13","pDeals":0,"pDelete":false,"pFreeShipping":true,"pHot":0,"pImg":"","pIntroduction":"","pMasterId":2,"pName":"测试","pOffline":false,"pPrice":2200,"pSize":"55588","pTotal":0,"pUpdateTime":"2017-12-13 13:30:47","productId":62,"userId":0},"tfUser":null}]
             * tAddressId : 39
             * tAmount : 4400
             * tCreateTime : 2017-12-13 12:40:19
             * tOrderNo : 2017121312401952923
             * tUserId : 5
             * totalId : 66
             */

            private AddressBean address;
            private int tAddressId;
            private int tAmount;
            private String tCreateTime;
            private String tOrderNo;
            private int tUserId;
            private int totalId;
            private int tQty;
            private int tStatus;
            private List<OrderListBean> orderList;

            protected TfOrderListBean(Parcel in) {
                address = in.readParcelable(AddressBean.class.getClassLoader());
                tAddressId = in.readInt();
                tAmount = in.readInt();
                tCreateTime = in.readString();
                tOrderNo = in.readString();
                tUserId = in.readInt();
                totalId = in.readInt();
                tQty = in.readInt();
                tStatus = in.readInt();
                orderList = in.createTypedArrayList(OrderListBean.CREATOR);
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


            public int gettQty() {
                return tQty;
            }

            public void settQty(int tQty) {
                this.tQty = tQty;
            }

            public int gettStatus() {
                return tStatus;
            }

            public void settStatus(int tStatus) {
                this.tStatus = tStatus;
            }

            public AddressBean getAddress() {
                return address;
            }

            public void setAddress(AddressBean address) {
                this.address = address;
            }

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

            public List<OrderListBean> getOrderList() {
                return orderList;
            }

            public void setOrderList(List<OrderListBean> orderList) {
                this.orderList = orderList;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeParcelable(address, i);
                parcel.writeInt(tAddressId);
                parcel.writeInt(tAmount);
                parcel.writeString(tCreateTime);
                parcel.writeString(tOrderNo);
                parcel.writeInt(tUserId);
                parcel.writeInt(totalId);
                parcel.writeInt(tQty);
                parcel.writeInt(tStatus);
                parcel.writeTypedList(orderList);
            }

            public static class AddressBean implements Parcelable{
                /**
                 * addressId : 39
                 * asConsigneeName : 测试
                 * asCountryCode : 86
                 * asCreateTime : 2017-12-06 16:03:31
                 * asDefault : true
                 * asDetails : 广东省珠海市香洲区
                 * asSex : false
                 * asStreet : 辽宁省沈阳市沈河区
                 * asTel : 7766
                 * asUpdateTime :
                 * asUserId : 5
                 */

                private int addressId;
                private String asConsigneeName;
                private String asCountryCode;
                private String asCreateTime;
                private boolean asDefault;
                private String asDetails;
                private boolean asSex;
                private String asStreet;
                private String asTel;
                private String asUpdateTime;
                private int asUserId;

                protected AddressBean(Parcel in) {
                    addressId = in.readInt();
                    asConsigneeName = in.readString();
                    asCountryCode = in.readString();
                    asCreateTime = in.readString();
                    asDefault = in.readByte() != 0;
                    asDetails = in.readString();
                    asSex = in.readByte() != 0;
                    asStreet = in.readString();
                    asTel = in.readString();
                    asUpdateTime = in.readString();
                    asUserId = in.readInt();
                }

                public static final Creator<AddressBean> CREATOR = new Creator<AddressBean>() {
                    @Override
                    public AddressBean createFromParcel(Parcel in) {
                        return new AddressBean(in);
                    }

                    @Override
                    public AddressBean[] newArray(int size) {
                        return new AddressBean[size];
                    }
                };

                public int getAddressId() {
                    return addressId;
                }

                public void setAddressId(int addressId) {
                    this.addressId = addressId;
                }

                public String getAsConsigneeName() {
                    return asConsigneeName;
                }

                public void setAsConsigneeName(String asConsigneeName) {
                    this.asConsigneeName = asConsigneeName;
                }

                public String getAsCountryCode() {
                    return asCountryCode;
                }

                public void setAsCountryCode(String asCountryCode) {
                    this.asCountryCode = asCountryCode;
                }

                public String getAsCreateTime() {
                    return asCreateTime;
                }

                public void setAsCreateTime(String asCreateTime) {
                    this.asCreateTime = asCreateTime;
                }

                public boolean isAsDefault() {
                    return asDefault;
                }

                public void setAsDefault(boolean asDefault) {
                    this.asDefault = asDefault;
                }

                public String getAsDetails() {
                    return asDetails;
                }

                public void setAsDetails(String asDetails) {
                    this.asDetails = asDetails;
                }

                public boolean isAsSex() {
                    return asSex;
                }

                public void setAsSex(boolean asSex) {
                    this.asSex = asSex;
                }

                public String getAsStreet() {
                    return asStreet;
                }

                public void setAsStreet(String asStreet) {
                    this.asStreet = asStreet;
                }

                public String getAsTel() {
                    return asTel;
                }

                public void setAsTel(String asTel) {
                    this.asTel = asTel;
                }

                public String getAsUpdateTime() {
                    return asUpdateTime;
                }

                public void setAsUpdateTime(String asUpdateTime) {
                    this.asUpdateTime = asUpdateTime;
                }

                public int getAsUserId() {
                    return asUserId;
                }

                public void setAsUserId(int asUserId) {
                    this.asUserId = asUserId;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel parcel, int i) {
                    parcel.writeInt(addressId);
                    parcel.writeString(asConsigneeName);
                    parcel.writeString(asCountryCode);
                    parcel.writeString(asCreateTime);
                    parcel.writeByte((byte) (asDefault ? 1 : 0));
                    parcel.writeString(asDetails);
                    parcel.writeByte((byte) (asSex ? 1 : 0));
                    parcel.writeString(asStreet);
                    parcel.writeString(asTel);
                    parcel.writeString(asUpdateTime);
                    parcel.writeInt(asUserId);
                }
            }

            public static class OrderListBean implements Parcelable{
                /**
                 * oAmount : 4400
                 * oAppointmentTime :
                 * oBusinessId : 0
                 * oCancelTime :
                 * oCancelType : 0
                 * oCompleteTime :
                 * oCreateTime : 2017-12-13 12:40:19
                 * oIsOrderTotal : true
                 * oLogisticsNo :
                 * oMasterId : 2
                 * oOrderNo : 201712131240195837178
                 * oOrderTotalNo : 2017121312401952923
                 * oOrderType : 3
                 * oPayStatus : 1
                 * oPayTime :
                 * oPayWay : 0
                 * oPrice : 2200
                 * oProductId : 62
                 * oQty : 2
                 * oStatus : 1
                 * oUpdateTime :
                 * oUserId : 5
                 * orderId : 362
                 * statusArray : []
                 * tfBusiness : null
                 * tfMaster : {"isCollection":false,"isSubscribe":false,"mAccid":0,"mAddress":"珠海","mBargain":1,"mBusinessType":"1","mConstellation":8,"mCountryCode":"86","mCreateTime":"2017-10-30 15:28:03","mDeals":1,"mDeleted":false,"mDeviceId":"99000471207354","mDeviceType":2,"mFollows":15,"mHeadImg":"/file/images/master/137262779531513063693585.png","mIm":"master2","mIntroduction":"","mLabel":"1","mNick":"邱逢生","mPassword":"11","mScore":4.5,"mSex":false,"mStatus":999,"mTel":"13726277953","mToken":"xEszEMWqDdePpPcuOputSNOtkZXW2UD8UGPslFcbY2C8V2MpjT9KIoXBb0dG3SMKz0cgovKcMMWwkC7O9HThLQ==","mUpdateTime":"2017-12-12 15:28:39","masterId":2,"queryUserId":0}
                 * tfOrderAttach : null
                 * tfProduct : {"imgList":[{"imgTmpId":503,"itAbsolute":"","itAppPath":"/storage/emulated/0/Android/data/com.yinghai.a24divine_master/cache/luban_disk_cache/1513062014604628.png","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itSize":"","itType":1,"itUpdateTime":"","itUrl":"/file/images/product/2/vOcgc_1513062014604628.png"}],"isCollection":false,"pAttribution":"珠海","pCreateTime":"2017-12-12 15:00:13","pDeals":0,"pDelete":false,"pFreeShipping":true,"pHot":0,"pImg":"","pIntroduction":"","pMasterId":2,"pName":"测试","pOffline":false,"pPrice":2200,"pSize":"55588","pTotal":0,"pUpdateTime":"2017-12-13 13:30:47","productId":62,"userId":0}
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
                private Object tfBusiness;
                private TfMasterBean tfMaster;
                private Object tfOrderAttach;
                private TfProductBean tfProduct;
                private Object tfUser;
                private List<?> statusArray;

                protected OrderListBean(Parcel in) {
                    oAmount = in.readInt();
                    oAppointmentTime = in.readString();
                    oBusinessId = in.readInt();
                    oCancelTime = in.readString();
                    oCancelType = in.readInt();
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
                    tfMaster = in.readParcelable(TfMasterBean.class.getClassLoader());
                    tfProduct = in.readParcelable(TfProductBean.class.getClassLoader());
                }

                public static final Creator<OrderListBean> CREATOR = new Creator<OrderListBean>() {
                    @Override
                    public OrderListBean createFromParcel(Parcel in) {
                        return new OrderListBean(in);
                    }

                    @Override
                    public OrderListBean[] newArray(int size) {
                        return new OrderListBean[size];
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

                public Object getTfBusiness() {
                    return tfBusiness;
                }

                public void setTfBusiness(Object tfBusiness) {
                    this.tfBusiness = tfBusiness;
                }

                public TfMasterBean getTfMaster() {
                    return tfMaster;
                }

                public void setTfMaster(TfMasterBean tfMaster) {
                    this.tfMaster = tfMaster;
                }

                public Object getTfOrderAttach() {
                    return tfOrderAttach;
                }

                public void setTfOrderAttach(Object tfOrderAttach) {
                    this.tfOrderAttach = tfOrderAttach;
                }

                public TfProductBean getTfProduct() {
                    return tfProduct;
                }

                public void setTfProduct(TfProductBean tfProduct) {
                    this.tfProduct = tfProduct;
                }

                public Object getTfUser() {
                    return tfUser;
                }

                public void setTfUser(Object tfUser) {
                    this.tfUser = tfUser;
                }

                public List<?> getStatusArray() {
                    return statusArray;
                }

                public void setStatusArray(List<?> statusArray) {
                    this.statusArray = statusArray;
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
                    parcel.writeString(oCancelTime);
                    parcel.writeInt(oCancelType);
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
                    parcel.writeParcelable(tfMaster, i);
                    parcel.writeParcelable(tfProduct, i);
                }

                public static class TfMasterBean implements Parcelable{
                    /**
                     * isCollection : false
                     * isSubscribe : false
                     * mAccid : 0
                     * mAddress : 珠海
                     * mBargain : 1
                     * mBusinessType : 1
                     * mConstellation : 8
                     * mCountryCode : 86
                     * mCreateTime : 2017-10-30 15:28:03
                     * mDeals : 1
                     * mDeleted : false
                     * mDeviceId : 99000471207354
                     * mDeviceType : 2
                     * mFollows : 15
                     * mHeadImg : /file/images/master/137262779531513063693585.png
                     * mIm : master2
                     * mIntroduction :
                     * mLabel : 1
                     * mNick : 邱逢生
                     * mPassword : 11
                     * mScore : 4.5
                     * mSex : false
                     * mStatus : 999
                     * mTel : 13726277953
                     * mToken : xEszEMWqDdePpPcuOputSNOtkZXW2UD8UGPslFcbY2C8V2MpjT9KIoXBb0dG3SMKz0cgovKcMMWwkC7O9HThLQ==
                     * mUpdateTime : 2017-12-12 15:28:39
                     * masterId : 2
                     * queryUserId : 0
                     */

                    private boolean isCollection;
                    private boolean isSubscribe;
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
                    private int queryUserId;

                    protected TfMasterBean(Parcel in) {
                        isCollection = in.readByte() != 0;
                        isSubscribe = in.readByte() != 0;
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
                        mIm = in.readString();
                        mIntroduction = in.readString();
                        mLabel = in.readString();
                        mNick = in.readString();
                        mPassword = in.readString();
                        mScore = in.readDouble();
                        mSex = in.readByte() != 0;
                        mStatus = in.readInt();
                        mTel = in.readString();
                        mToken = in.readString();
                        mUpdateTime = in.readString();
                        masterId = in.readInt();
                        queryUserId = in.readInt();
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

                    public boolean isIsCollection() {
                        return isCollection;
                    }

                    public void setIsCollection(boolean isCollection) {
                        this.isCollection = isCollection;
                    }

                    public boolean isIsSubscribe() {
                        return isSubscribe;
                    }

                    public void setIsSubscribe(boolean isSubscribe) {
                        this.isSubscribe = isSubscribe;
                    }

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

                    public int getQueryUserId() {
                        return queryUserId;
                    }

                    public void setQueryUserId(int queryUserId) {
                        this.queryUserId = queryUserId;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel parcel, int i) {
                        parcel.writeByte((byte) (isCollection ? 1 : 0));
                        parcel.writeByte((byte) (isSubscribe ? 1 : 0));
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
                        parcel.writeString(mIm);
                        parcel.writeString(mIntroduction);
                        parcel.writeString(mLabel);
                        parcel.writeString(mNick);
                        parcel.writeString(mPassword);
                        parcel.writeDouble(mScore);
                        parcel.writeByte((byte) (mSex ? 1 : 0));
                        parcel.writeInt(mStatus);
                        parcel.writeString(mTel);
                        parcel.writeString(mToken);
                        parcel.writeString(mUpdateTime);
                        parcel.writeInt(masterId);
                        parcel.writeInt(queryUserId);
                    }
                }

                public static class TfProductBean implements Parcelable{
                    /**
                     * imgList : [{"imgTmpId":503,"itAbsolute":"","itAppPath":"/storage/emulated/0/Android/data/com.yinghai.a24divine_master/cache/luban_disk_cache/1513062014604628.png","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itSize":"","itType":1,"itUpdateTime":"","itUrl":"/file/images/product/2/vOcgc_1513062014604628.png"}]
                     * isCollection : false
                     * pAttribution : 珠海
                     * pCreateTime : 2017-12-12 15:00:13
                     * pDeals : 0
                     * pDelete : false
                     * pFreeShipping : true
                     * pHot : 0
                     * pImg :
                     * pIntroduction :
                     * pMasterId : 2
                     * pName : 测试
                     * pOffline : false
                     * pPrice : 2200
                     * pSize : 55588
                     * pTotal : 0
                     * pUpdateTime : 2017-12-13 13:30:47
                     * productId : 62
                     * userId : 0
                     */

                    private boolean isCollection;
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
                    private int userId;
                    private List<ImgListBean> imgList;

                    protected TfProductBean(Parcel in) {
                        isCollection = in.readByte() != 0;
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
                        userId = in.readInt();
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

                    public boolean isIsCollection() {
                        return isCollection;
                    }

                    public void setIsCollection(boolean isCollection) {
                        this.isCollection = isCollection;
                    }

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

                    public int getUserId() {
                        return userId;
                    }

                    public void setUserId(int userId) {
                        this.userId = userId;
                    }

                    public List<ImgListBean> getImgList() {
                        return imgList;
                    }

                    public void setImgList(List<ImgListBean> imgList) {
                        this.imgList = imgList;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel parcel, int i) {
                        parcel.writeByte((byte) (isCollection ? 1 : 0));
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
                        parcel.writeInt(userId);
                    }

                    public static class ImgListBean {
                        /**
                         * imgTmpId : 503
                         * itAbsolute :
                         * itAppPath : /storage/emulated/0/Android/data/com.yinghai.a24divine_master/cache/luban_disk_cache/1513062014604628.png
                         * itCreateTime :
                         * itIsUser : true
                         * itKeyId : 0
                         * itMasterId : 0
                         * itSize :
                         * itType : 1
                         * itUpdateTime :
                         * itUrl : /file/images/product/2/vOcgc_1513062014604628.png
                         */

                        private int imgTmpId;
                        private String itAbsolute;
                        private String itAppPath;
                        private String itCreateTime;
                        private boolean itIsUser;
                        private int itKeyId;
                        private int itMasterId;
                        private String itSize;
                        private int itType;
                        private String itUpdateTime;
                        private String itUrl;

                        public int getImgTmpId() {
                            return imgTmpId;
                        }

                        public void setImgTmpId(int imgTmpId) {
                            this.imgTmpId = imgTmpId;
                        }

                        public String getItAbsolute() {
                            return itAbsolute;
                        }

                        public void setItAbsolute(String itAbsolute) {
                            this.itAbsolute = itAbsolute;
                        }

                        public String getItAppPath() {
                            return itAppPath;
                        }

                        public void setItAppPath(String itAppPath) {
                            this.itAppPath = itAppPath;
                        }

                        public String getItCreateTime() {
                            return itCreateTime;
                        }

                        public void setItCreateTime(String itCreateTime) {
                            this.itCreateTime = itCreateTime;
                        }

                        public boolean isItIsUser() {
                            return itIsUser;
                        }

                        public void setItIsUser(boolean itIsUser) {
                            this.itIsUser = itIsUser;
                        }

                        public int getItKeyId() {
                            return itKeyId;
                        }

                        public void setItKeyId(int itKeyId) {
                            this.itKeyId = itKeyId;
                        }

                        public int getItMasterId() {
                            return itMasterId;
                        }

                        public void setItMasterId(int itMasterId) {
                            this.itMasterId = itMasterId;
                        }

                        public String getItSize() {
                            return itSize;
                        }

                        public void setItSize(String itSize) {
                            this.itSize = itSize;
                        }

                        public int getItType() {
                            return itType;
                        }

                        public void setItType(int itType) {
                            this.itType = itType;
                        }

                        public String getItUpdateTime() {
                            return itUpdateTime;
                        }

                        public void setItUpdateTime(String itUpdateTime) {
                            this.itUpdateTime = itUpdateTime;
                        }

                        public String getItUrl() {
                            return itUrl;
                        }

                        public void setItUrl(String itUrl) {
                            this.itUrl = itUrl;
                        }
                    }
                }
            }
        }
    }
}
