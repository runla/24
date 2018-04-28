package com.yinghai.a24divine_user.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/13 13:34
 *         Describe：历史记录
 */

public class HistoryBean {


    /**
     * code : 1
     * msg : success
     * data : [{"article":{"aAbortDate":"2017-10-31 00:00:00","aContent":"","aCreateTime":"2017-10-24 13:25:16","aImg":"","aMasterId":1,"aPublishDate":"2017-10-24 00:00:00","aReadAmount":0,"aType":"星座爱情","aUpdateTime":"2017-10-24 13:25:21","aUrl":"http://astro.sina.com.cn/v/ss/2017-10-24/doc-ifymzqpq3748173-p6.shtml","articleId":1},"hArticleId":0,"hCreateTime":"2017-10-24 11:59:09","hMasterId":1,"hProductId":0,"hType":1,"hUserId":1,"historyId":1,"master":{"mAddress":"拱北","mBargain":1,"mBusinessType":"1","mConstellation":2,"mCountryCode":"86","mCreateTime":"2017-10-23 10:09:14","mDeals":1,"mDeleted":false,"mFollows":1,"mHeadImg":"","mIntroduction":"","mLabel":"1","mNick":"曾学松","mPassword":"d9b1d7db4cd6e70935368a1efb10e377","mScore":4.5,"mSex":true,"mStatus":1,"mTel":"15919160761","mUpdateTime":"","masterId":1},"product":{"pAttribution":"浙江金华","pCreateTime":"2017-10-24 13:36:32","pDeals":0,"pDelete":false,"pFreeShipping":true,"pHot":0,"pImg":"","pIntroduction":"","pMasterId":1,"pName":"名木香府老料小叶紫檀满金星手串20毫米男女款满金星老料紫檀手串手链 紫檀Q211款 编号Q211-05","pOffline":false,"pPrice":1800,"pSize":"18-25毫米（18-25mm）","pTotal":10,"pUpdateTime":"2017-10-24 13:36:35","productId":1}}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * article : {"aAbortDate":"2017-10-31 00:00:00","aContent":"","aCreateTime":"2017-10-24 13:25:16","aImg":"","aMasterId":1,"aPublishDate":"2017-10-24 00:00:00","aReadAmount":0,"aType":"星座爱情","aUpdateTime":"2017-10-24 13:25:21","aUrl":"http://astro.sina.com.cn/v/ss/2017-10-24/doc-ifymzqpq3748173-p6.shtml","articleId":1}
         * hArticleId : 0
         * hCreateTime : 2017-10-24 11:59:09
         * hMasterId : 1
         * hProductId : 0
         * hType : 1
         * hUserId : 1
         * historyId : 1
         * master : {"mAddress":"拱北","mBargain":1,"mBusinessType":"1","mConstellation":2,"mCountryCode":"86","mCreateTime":"2017-10-23 10:09:14","mDeals":1,"mDeleted":false,"mFollows":1,"mHeadImg":"","mIntroduction":"","mLabel":"1","mNick":"曾学松","mPassword":"d9b1d7db4cd6e70935368a1efb10e377","mScore":4.5,"mSex":true,"mStatus":1,"mTel":"15919160761","mUpdateTime":"","masterId":1}
         * product : {"pAttribution":"浙江金华","pCreateTime":"2017-10-24 13:36:32","pDeals":0,"pDelete":false,"pFreeShipping":true,"pHot":0,"pImg":"","pIntroduction":"","pMasterId":1,"pName":"名木香府老料小叶紫檀满金星手串20毫米男女款满金星老料紫檀手串手链 紫檀Q211款 编号Q211-05","pOffline":false,"pPrice":1800,"pSize":"18-25毫米（18-25mm）","pTotal":10,"pUpdateTime":"2017-10-24 13:36:35","productId":1}
         */

        private ArticleBean article;
        private int hArticleId;
        private String hCreateTime;
        private int hMasterId;
        private int hProductId;
        private int hType;
        private int hUserId;
        private int historyId;
        private MasterBean master;
        private ProductBean product;

        public ArticleBean getArticle() {
            return article;
        }

        public void setArticle(ArticleBean article) {
            this.article = article;
        }

        public int getHArticleId() {
            return hArticleId;
        }

        public void setHArticleId(int hArticleId) {
            this.hArticleId = hArticleId;
        }

        public String getHCreateTime() {
            return hCreateTime;
        }

        public void setHCreateTime(String hCreateTime) {
            this.hCreateTime = hCreateTime;
        }

        public int getHMasterId() {
            return hMasterId;
        }

        public void setHMasterId(int hMasterId) {
            this.hMasterId = hMasterId;
        }

        public int getHProductId() {
            return hProductId;
        }

        public void setHProductId(int hProductId) {
            this.hProductId = hProductId;
        }

        public int getHType() {
            return hType;
        }

        public void setHType(int hType) {
            this.hType = hType;
        }

        public int getHUserId() {
            return hUserId;
        }

        public void setHUserId(int hUserId) {
            this.hUserId = hUserId;
        }

        public int getHistoryId() {
            return historyId;
        }

        public void setHistoryId(int historyId) {
            this.historyId = historyId;
        }

        public MasterBean getMaster() {
            return master;
        }

        public void setMaster(MasterBean master) {
            this.master = master;
        }

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public static class ArticleBean {
            /**
             * aAbortDate : 2017-10-31 00:00:00
             * aContent :
             * aCreateTime : 2017-10-24 13:25:16
             * aImg :
             * aTitle : 1112
             * aMasterId : 1
             * aPublishDate : 2017-10-24 00:00:00
             * aReadAmount : 0
             * aType : 星座爱情
             * aUpdateTime : 2017-10-24 13:25:21
             * aUrl : http://astro.sina.com.cn/v/ss/2017-10-24/doc-ifymzqpq3748173-p6.shtml
             * articleId : 1
             */

            private String aAbortDate;
            private String aContent;
            private String aCreateTime;
            private String aTitle;
            private String aImg;
            private int aMasterId;
            private String aPublishDate;
            private int aReadAmount;
            private String aType;
            private String aUpdateTime;
            private String aUrl;
            private int articleId;
            private List<HistoryBean.DataBean.ImgListBean> imgList;

            public List<ImgListBean> getImgList() {
                return imgList;
            }

            public void setImgList(List<ImgListBean> imgList) {
                this.imgList = imgList;
            }

            public String getaTitle() {
                return aTitle;
            }

            public void setaTitle(String aTitle) {
                this.aTitle = aTitle;
            }

            public String getAAbortDate() {
                return aAbortDate;
            }

            public void setAAbortDate(String aAbortDate) {
                this.aAbortDate = aAbortDate;
            }

            public String getAContent() {
                return aContent;
            }

            public void setAContent(String aContent) {
                this.aContent = aContent;
            }

            public String getACreateTime() {
                return aCreateTime;
            }

            public void setACreateTime(String aCreateTime) {
                this.aCreateTime = aCreateTime;
            }

            public String getAImg() {
                return aImg;
            }

            public void setAImg(String aImg) {
                this.aImg = aImg;
            }

            public int getAMasterId() {
                return aMasterId;
            }

            public void setAMasterId(int aMasterId) {
                this.aMasterId = aMasterId;
            }

            public String getAPublishDate() {
                return aPublishDate;
            }

            public void setAPublishDate(String aPublishDate) {
                this.aPublishDate = aPublishDate;
            }

            public int getAReadAmount() {
                return aReadAmount;
            }

            public void setAReadAmount(int aReadAmount) {
                this.aReadAmount = aReadAmount;
            }

            public String getAType() {
                return aType;
            }

            public void setAType(String aType) {
                this.aType = aType;
            }

            public String getAUpdateTime() {
                return aUpdateTime;
            }

            public void setAUpdateTime(String aUpdateTime) {
                this.aUpdateTime = aUpdateTime;
            }

            public String getAUrl() {
                return aUrl;
            }

            public void setAUrl(String aUrl) {
                this.aUrl = aUrl;
            }

            public int getArticleId() {
                return articleId;
            }

            public void setArticleId(int articleId) {
                this.articleId = articleId;
            }
        }

        public static class MasterBean {
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
             * isSubscribe true
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
        }

        public static class ProductBean implements Parcelable {
            /**
             * pAttribution : 浙江金华
             * pCreateTime : 2017-10-24 13:36:32
             * pDeals : 0
             * pDelete : false
             * pFreeShipping : true
             * pHot : 0
             * pImg :
             * pIntroduction :
             * pMasterId : 1
             * pName : 名木香府老料小叶紫檀满金星手串20毫米男女款满金星老料紫檀手串手链 紫檀Q211款 编号Q211-05
             * pOffline : false
             * pPrice : 1800
             * pSize : 18-25毫米（18-25mm）
             * pTotal : 10
             * pUpdateTime : 2017-10-24 13:36:35
             * productId : 1
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
            private List<HistoryBean.DataBean.ImgListBean> imgList;

            protected ProductBean(Parcel in) {
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
                imgList = in.createTypedArrayList(ImgListBean.CREATOR);
            }

            public static final Creator<ProductBean> CREATOR = new Creator<ProductBean>() {
                @Override
                public ProductBean createFromParcel(Parcel in) {
                    return new ProductBean(in);
                }

                @Override
                public ProductBean[] newArray(int size) {
                    return new ProductBean[size];
                }
            };

            public List<ImgListBean> getImgList() {
                return imgList;
            }

            public void setImgList(List<ImgListBean> imgList) {
                this.imgList = imgList;
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


        public static class ImgListBean implements Parcelable {
            /**
             * imgTmpId : 294
             * itAbsolute :
             * itAppPath : /storage/emulated/0/Android/data/com.yinghai.a24divine_master/cache/luban_disk_cache/1511516318208355.png
             * itCreateTime :
             * itIsUser : true
             * itKeyId : 0
             * itMasterId : 0
             * itType : 1
             * itUpdateTime :
             * itUrl : /file/images/product//2/UziyC_1511516318208355.png
             */

            public ImgListBean() {
            }

            private int imgTmpId;
            private String itAbsolute;
            private String itAppPath;
            private String itCreateTime;
            private boolean itIsUser;
            private int itKeyId;
            private int itMasterId;
            private int itType;
            private String itUpdateTime;
            private String itUrl;

            protected ImgListBean(Parcel in) {
                imgTmpId = in.readInt();
                itAbsolute = in.readString();
                itAppPath = in.readString();
                itCreateTime = in.readString();
                itIsUser = in.readByte() != 0;
                itKeyId = in.readInt();
                itMasterId = in.readInt();
                itType = in.readInt();
                itUpdateTime = in.readString();
                itUrl = in.readString();
            }

            public static final Creator<ImgListBean> CREATOR = new Creator<ImgListBean>() {
                @Override
                public ImgListBean createFromParcel(Parcel in) {
                    return new ImgListBean(in);
                }

                @Override
                public ImgListBean[] newArray(int size) {
                    return new ImgListBean[size];
                }
            };

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(imgTmpId);
                parcel.writeString(itAbsolute);
                parcel.writeString(itAppPath);
                parcel.writeString(itCreateTime);
                parcel.writeByte((byte) (itIsUser ? 1 : 0));
                parcel.writeInt(itKeyId);
                parcel.writeInt(itMasterId);
                parcel.writeInt(itType);
                parcel.writeString(itUpdateTime);
                parcel.writeString(itUrl);
            }
        }


    }
}
