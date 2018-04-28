package com.yinghai.a24divine_user.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/21 13:38
 *         Describe：占卜师详情Bean
 */

public class MasterDetailBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"tfMaster":{"mAddress":"拱北","mBargain":1,"mBusinessType":"1","mConstellation":2,"mCountryCode":"86","mCreateTime":"2017-10-23 10:09:14","mDeals":1,"mDeleted":false,"mFollows":1,"mHeadImg":"","mIntroduction":"","mLabel":"1","mNick":"曾学松","mPassword":"d9b1d7db4cd6e70935368a1efb10e377","mScore":4.5,"mSex":true,"mStatus":1,"mTel":"15919160761","mUpdateTime":"","masterId":1}}
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
         * tfMaster : {"mAddress":"拱北","mBargain":1,"mBusinessType":"1","mConstellation":2,"mCountryCode":"86","mCreateTime":"2017-10-23 10:09:14","mDeals":1,"mDeleted":false,"mFollows":1,"mHeadImg":"","mIntroduction":"","mLabel":"1","mNick":"曾学松","mPassword":"d9b1d7db4cd6e70935368a1efb10e377","mScore":4.5,"mSex":true,"mStatus":1,"mTel":"15919160761","mUpdateTime":"","masterId":1}
         */

        private TfMasterBean tfMaster;

        public TfMasterBean getTfMaster() {
            return tfMaster;
        }

        public void setTfMaster(TfMasterBean tfMaster) {
            this.tfMaster = tfMaster;
        }

        public static class TfMasterBean implements Parcelable {
            /**
             * mAddress : 拱北
             * mBargain : 1
             * mBusinessType : 1
             * mConstellation : 2
             * mCountryCode : 86
             * mCreateTime : 2017-10-23 10:09:14
             * mDeals : 1
             * mDeleted : false
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
             * mUpdateTime :
             * masterId : 1
              * isSubscribe : true
             */

            private String mAddress;
            private int mBargain;
            private String mBusinessType;
            private int mConstellation;
            private String mCountryCode;
            private String mCreateTime;
            private int mDeals;
            private boolean mDeleted;
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
            private boolean isSubscribe;

            public boolean isSubscribe() {
                return isSubscribe;
            }

            public void setSubscribe(boolean subscribe) {
                isSubscribe = subscribe;
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
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.mAddress);
                dest.writeInt(this.mBargain);
                dest.writeString(this.mBusinessType);
                dest.writeInt(this.mConstellation);
                dest.writeString(this.mCountryCode);
                dest.writeString(this.mCreateTime);
                dest.writeInt(this.mDeals);
                dest.writeByte(this.mDeleted ? (byte) 1 : (byte) 0);
                dest.writeInt(this.mFollows);
                dest.writeString(this.mHeadImg);
                dest.writeString(this.mIntroduction);
                dest.writeString(this.mLabel);
                dest.writeString(this.mNick);
                dest.writeString(this.mPassword);
                dest.writeDouble(this.mScore);
                dest.writeByte(this.mSex ? (byte) 1 : (byte) 0);
                dest.writeInt(this.mStatus);
                dest.writeString(this.mTel);
                dest.writeString(this.mUpdateTime);
                dest.writeInt(this.masterId);
                dest.writeByte(this.isSubscribe ? (byte) 1 : (byte) 0);
            }

            public TfMasterBean() {
            }

            protected TfMasterBean(Parcel in) {
                this.mAddress = in.readString();
                this.mBargain = in.readInt();
                this.mBusinessType = in.readString();
                this.mConstellation = in.readInt();
                this.mCountryCode = in.readString();
                this.mCreateTime = in.readString();
                this.mDeals = in.readInt();
                this.mDeleted = in.readByte() != 0;
                this.mFollows = in.readInt();
                this.mHeadImg = in.readString();
                this.mIntroduction = in.readString();
                this.mLabel = in.readString();
                this.mNick = in.readString();
                this.mPassword = in.readString();
                this.mScore = in.readDouble();
                this.mSex = in.readByte() != 0;
                this.mStatus = in.readInt();
                this.mTel = in.readString();
                this.mUpdateTime = in.readString();
                this.masterId = in.readInt();
                this.isSubscribe = in.readByte() != 0;
            }

            public static final Parcelable.Creator<TfMasterBean> CREATOR = new Parcelable.Creator<TfMasterBean>() {
                @Override
                public TfMasterBean createFromParcel(Parcel source) {
                    return new TfMasterBean(source);
                }

                @Override
                public TfMasterBean[] newArray(int size) {
                    return new TfMasterBean[size];
                }
            };
        }
    }
}
