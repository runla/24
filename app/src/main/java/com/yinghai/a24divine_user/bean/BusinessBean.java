package com.yinghai.a24divine_user.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by chenjianrun on 2017/10/25.
 * 描述：大师业务的Bean
 */

public class BusinessBean {


    /**
     * code : 1
     * msg : success
     * data : {"tfBusinessList":[{"bCreateTime":"2017-10-25 15:32:28","bDeals":0,"bIntroduction":"这是业务介绍","bMasterId":1,"bName":"再来个业务","bPrice":58000,"bUpdateTime":"","businessId":4},{"bCreateTime":"2017-10-25 15:32:51","bDeals":0,"bIntroduction":"这是业务介绍2","bMasterId":1,"bName":"再来个业务1","bPrice":28000,"bUpdateTime":"","businessId":5}],"total":2,"pages":1,"pageSize":10,"pageNum":1}
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

    public static class DataBean implements Parcelable{
        /**
         * tfBusinessList : [{"bCreateTime":"2017-10-25 15:32:28","bDeals":0,"bIntroduction":"这是业务介绍","bMasterId":1,"bName":"再来个业务","bPrice":58000,"bUpdateTime":"","businessId":4},{"bCreateTime":"2017-10-25 15:32:51","bDeals":0,"bIntroduction":"这是业务介绍2","bMasterId":1,"bName":"再来个业务1","bPrice":28000,"bUpdateTime":"","businessId":5}]
         * total : 2
         * pages : 1
         * pageSize : 10
         * pageNum : 1
         */

        private int total;
        private int pages;
        private int pageSize;
        private int pageNum;
        private List<TfBusinessListBean> tfBusinessList;

        protected DataBean(Parcel in) {
            total = in.readInt();
            pages = in.readInt();
            pageSize = in.readInt();
            pageNum = in.readInt();
            tfBusinessList = in.createTypedArrayList(TfBusinessListBean.CREATOR);
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

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

        public List<TfBusinessListBean> getTfBusinessList() {
            return tfBusinessList;
        }

        public void setTfBusinessList(List<TfBusinessListBean> tfBusinessList) {
            this.tfBusinessList = tfBusinessList;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(total);
            parcel.writeInt(pages);
            parcel.writeInt(pageSize);
            parcel.writeInt(pageNum);
            parcel.writeTypedList(tfBusinessList);
        }

        public static class TfBusinessListBean implements Parcelable{
            /**
             * bCreateTime : 2017-10-25 15:32:28
             * bDeals : 0
             * bIntroduction : 这是业务介绍
             * bMasterId : 1
             * bName : 再来个业务
             * bPrice : 58000
             * bUpdateTime :
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

            protected TfBusinessListBean(Parcel in) {
                bCreateTime = in.readString();
                bDeals = in.readInt();
                bIntroduction = in.readString();
                bMasterId = in.readInt();
                bName = in.readString();
                bPrice = in.readInt();
                bUpdateTime = in.readString();
                businessId = in.readInt();
            }

            public static final Creator<TfBusinessListBean> CREATOR = new Creator<TfBusinessListBean>() {
                @Override
                public TfBusinessListBean createFromParcel(Parcel in) {
                    return new TfBusinessListBean(in);
                }

                @Override
                public TfBusinessListBean[] newArray(int size) {
                    return new TfBusinessListBean[size];
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
    }
}
