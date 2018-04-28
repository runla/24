package com.yinghai.a24divine_user.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/17 11:15
 *         Describe：收货地址Bean
 */

public class AddressListBean {


    /**
     * code : 1
     * msg : success
     * data : {"address":[{"addressId":18,"asConsigneeName":"小朋友","asCountryCode":"86","asCreateTime":"2017-11-16 16:03:02","asDefault":false,"asDetails":"口岸","asStreet":"广东珠海香洲拱北","asTel":"15811670000","asUpdateTime":"","asUserId":1}]}
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
        private List<AddressBean> address;

        public List<AddressBean> getAddress() {
            return address;
        }

        public void setAddress(List<AddressBean> address) {
            this.address = address;
        }

        public static class AddressBean implements Parcelable{
            /**
             * addressId : 18
             * asConsigneeName : 小朋友
             * asCountryCode : 86
             * asCreateTime : 2017-11-16 16:03:02
             * asDefault : false
             * asDetails : 口岸
             * asStreet : 广东珠海香洲拱北
             * asTel : 15811670000
             * asUpdateTime :
             * asUserId : 1
             * asSex : true
             */



            private int addressId;
            private String asConsigneeName;
            private String asCountryCode;
            private String asCreateTime;
            private boolean asDefault;
            private String asDetails;
            private String asStreet;
            private String asTel;
            private String asUpdateTime;
            private int asUserId;
            private boolean asSex;
            private boolean isSeleted; //是否已选中，本地添加


            protected AddressBean(Parcel in) {
                addressId = in.readInt();
                asConsigneeName = in.readString();
                asCountryCode = in.readString();
                asCreateTime = in.readString();
                asDefault = in.readByte() != 0;
                asDetails = in.readString();
                asStreet = in.readString();
                asTel = in.readString();
                asUpdateTime = in.readString();
                asUserId = in.readInt();
                asSex = in.readByte() != 0;
                isSeleted = in.readByte() != 0;
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

            public boolean isAsSex() {
                return asSex;
            }

            public void setAsSex(boolean asSex) {
                this.asSex = asSex;
            }

            public boolean isSeleted() {
                return isSeleted;
            }

            public void setSeleted(boolean seleted) {
                isSeleted = seleted;
            }

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
            public boolean equals(Object obj) {
                if (obj == null) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                if (obj instanceof AddressBean) {
                    AddressBean bean = (AddressBean) obj;
                    if (this.addressId == bean.getAddressId()) {
                        return true;
                    } else {
                        return false;
                    }

                }
                return false;
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
                parcel.writeString(asStreet);
                parcel.writeString(asTel);
                parcel.writeString(asUpdateTime);
                parcel.writeInt(asUserId);
                parcel.writeByte((byte) (asSex ? 1 : 0));
                parcel.writeByte((byte) (isSeleted ? 1 : 0));
            }
        }
    }
}
