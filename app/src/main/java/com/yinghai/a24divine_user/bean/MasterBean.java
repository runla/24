package com.yinghai.a24divine_user.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author Created by：fanson
 *         Created Time: 2017/10/30 16:40
 *         Describe：占卜师Bean
 */

public class MasterBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"masterList":[{"mAddress":"拱北","mBargain":1,"mBusinessType":"1","mConstellation":2,"mCountryCode":"86","mCreateTime":"2017-10-23 10:09:14","mDeals":1,"mDeleted":false,"mFollows":1,"mHeadImg":"","mIntroduction":"","mLabel":"1","isCollection":true,"isSubscribe":true,"mNick":"曾学松","mPassword":"d9b1d7db4cd6e70935368a1efb10e377","mScore":4.5,"mSex":true,"mStatus":1,"mTel":"15919160761","mUpdateTime":"","masterId":1}]}
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
        private List<MasterListBean> masterList;

        public List<MasterListBean> getMasterList() {
            return masterList;
        }

        public void setMasterList(List<MasterListBean> masterList) {
            this.masterList = masterList;
        }

        public static class MasterListBean implements Parcelable {
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
             * isCollection : true
             * isSubscribe : true
             * mNick : 曾学松
             * mPassword : d9b1d7db4cd6e70935368a1efb10e377
             * mScore : 4.5
             * mSex : true
             * mStatus : 1
             * mTel : 15919160761
             * mUpdateTime :
             * masterId : 1
             */

            public MasterListBean() {   }

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
            private boolean isCollection;
            private boolean isSubscribe;
            private String mNick;
            private String mPassword;
            private double mScore;
            private boolean mSex;
            private int mStatus;
            private String mTel;
            private String mUpdateTime;
            private int masterId;

            protected MasterListBean(Parcel in) {
                mAddress = in.readString();
                mBargain = in.readInt();
                mBusinessType = in.readString();
                mConstellation = in.readInt();
                mCountryCode = in.readString();
                mCreateTime = in.readString();
                mDeals = in.readInt();
                mDeleted = in.readByte() != 0;
                mFollows = in.readInt();
                mHeadImg = in.readString();
                mIntroduction = in.readString();
                mLabel = in.readString();
                isCollection = in.readByte() != 0;
                isSubscribe = in.readByte() != 0;
                mNick = in.readString();
                mPassword = in.readString();
                mScore = in.readDouble();
                mSex = in.readByte() != 0;
                mStatus = in.readInt();
                mTel = in.readString();
                mUpdateTime = in.readString();
                masterId = in.readInt();
            }

            public static final Creator<MasterListBean> CREATOR = new Creator<MasterListBean>() {
                @Override
                public MasterListBean createFromParcel(Parcel in) {
                    return new MasterListBean(in);
                }

                @Override
                public MasterListBean[] newArray(int size) {
                    return new MasterListBean[size];
                }
            };

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
                parcel.writeString(mAddress);
                parcel.writeInt(mBargain);
                parcel.writeString(mBusinessType);
                parcel.writeInt(mConstellation);
                parcel.writeString(mCountryCode);
                parcel.writeString(mCreateTime);
                parcel.writeInt(mDeals);
                parcel.writeByte((byte) (mDeleted ? 1 : 0));
                parcel.writeInt(mFollows);
                parcel.writeString(mHeadImg);
                parcel.writeString(mIntroduction);
                parcel.writeString(mLabel);
                parcel.writeByte((byte) (isCollection ? 1 : 0));
                parcel.writeByte((byte) (isSubscribe ? 1 : 0));
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
    }
}