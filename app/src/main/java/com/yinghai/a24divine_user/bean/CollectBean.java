package com.yinghai.a24divine_user.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/10 13:49
 *         Describe：收藏
 */

public class CollectBean {

    /**
     * code : 1
     * msg : success
     * data : {"collection":[{"article":{"aAbortDate":"2017-10-31 00:00:00","aContent":"手相）将会是个好丈夫好妻子的最佳人选。","aCreateTime":"2017-10-24 13:29:16","aImg":"","aMasterId":1,"aPublishDate":"2017-10-24 00:00:00","aReadAmount":11,"aTitle":"文章2","aType":"手相","aUpdateTime":"2017-11-23 11:06:07","aUrl":"http://www.sohu.com/a/153427408_509149","articleId":2,"imgList":[],"isCollection":false,"queryUserId":0},"cnArticleId":0,"cnCreateTime":"2017-11-14 18:07:13","cnMasterId":0,"cnProductId":2,"cnType":3,"cnUserId":10,"collectionId":61,"master":{"isCollection":false,"isSubscribe":false,"mAccid":0,"mAddress":"珠海","mBargain":2,"mBusinessType":"1","mConstellation":2,"mCountryCode":"86","mCreateTime":"2017-10-30 15:28:03","mDeals":1,"mDeleted":false,"mDeviceId":"","mDeviceType":0,"mFollows":1,"mHeadImg":"","mIm":"","mIntroduction":"","mLabel":"1","mNick":"生","mPassword":"11","mScore":4.5,"mSex":true,"mStatus":1,"mTel":"13726277953","mToken":"","mUpdateTime":"2017-11-22 16:37:24","masterId":2,"queryUserId":0},"product":{"imgList":[],"isCollection":false,"pAttribution":"浙江金华","pCreateTime":"2017-10-24 13:40:39","pDeals":0,"pDelete":false,"pFreeShipping":true,"pHot":0,"pImg":"","pIntroduction":"佛螺菩提子手串：文玩界的新宠，以其，是不可多得的一种菩提。","pMasterId":2,"pName":"名木香府佛螺菩提子手串 金刚满肉红皮佛珠原籽念珠男女文玩木珠 17mm*13颗","pOffline":false,"pPrice":499,"pSize":"13-17毫米（13-17mm）","pTotal":0,"pUpdateTime":"2017-11-14 15:23:53","productId":2,"userId":0}}]}
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
        private List<CollectionBean> collection;

        public List<CollectionBean> getCollection() {
            return collection;
        }

        public void setCollection(List<CollectionBean> collection) {
            this.collection = collection;
        }

        public static class CollectionBean {
            /**
             * article : {"aAbortDate":"2017-10-31 00:00:00","aContent":"手相）将会是个好丈夫好妻子的最佳人选。","aCreateTime":"2017-10-24 13:29:16","aImg":"","aMasterId":1,"aPublishDate":"2017-10-24 00:00:00","aReadAmount":11,"aTitle":"文章2","aType":"手相","aUpdateTime":"2017-11-23 11:06:07","aUrl":"http://www.sohu.com/a/153427408_509149","articleId":2,"imgList":[],"isCollection":false,"queryUserId":0}
             * cnArticleId : 0
             * cnCreateTime : 2017-11-14 18:07:13
             * cnMasterId : 0
             * cnProductId : 2
             * cnType : 3
             * cnUserId : 10
             * collectionId : 61
             * master : {"isCollection":false,"isSubscribe":false,"mAccid":0,"mAddress":"珠海","mBargain":2,"mBusinessType":"1","mConstellation":2,"mCountryCode":"86","mCreateTime":"2017-10-30 15:28:03","mDeals":1,"mDeleted":false,"mDeviceId":"","mDeviceType":0,"mFollows":1,"mHeadImg":"","mIm":"","mIntroduction":"","mLabel":"1","mNick":"生","mPassword":"11","mScore":4.5,"mSex":true,"mStatus":1,"mTel":"13726277953","mToken":"","mUpdateTime":"2017-11-22 16:37:24","masterId":2,"queryUserId":0}
             * product : {"imgList":[],"isCollection":false,"pAttribution":"浙江金华","pCreateTime":"2017-10-24 13:40:39","pDeals":0,"pDelete":false,"pFreeShipping":true,"pHot":0,"pImg":"","pIntroduction":"佛螺菩提子手串：文玩界的新宠，以其，是不可多得的一种菩提。","pMasterId":2,"pName":"名木香府佛螺菩提子手串 金刚满肉红皮佛珠原籽念珠男女文玩木珠 17mm*13颗","pOffline":false,"pPrice":499,"pSize":"13-17毫米（13-17mm）","pTotal":0,"pUpdateTime":"2017-11-14 15:23:53","productId":2,"userId":0}
             */

            private ArticleBean article;
            private int cnArticleId;
            private String cnCreateTime;
            private int cnMasterId;
            private int cnProductId;
            private int cnType;
            private int cnUserId;
            private int collectionId;
            private MasterBean master;
            private ProductBean product;

            public ArticleBean getArticle() {
                return article;
            }

            public void setArticle(ArticleBean article) {
                this.article = article;
            }

            public int getCnArticleId() {
                return cnArticleId;
            }

            public void setCnArticleId(int cnArticleId) {
                this.cnArticleId = cnArticleId;
            }

            public String getCnCreateTime() {
                return cnCreateTime;
            }

            public void setCnCreateTime(String cnCreateTime) {
                this.cnCreateTime = cnCreateTime;
            }

            public int getCnMasterId() {
                return cnMasterId;
            }

            public void setCnMasterId(int cnMasterId) {
                this.cnMasterId = cnMasterId;
            }

            public int getCnProductId() {
                return cnProductId;
            }

            public void setCnProductId(int cnProductId) {
                this.cnProductId = cnProductId;
            }

            public int getCnType() {
                return cnType;
            }

            public void setCnType(int cnType) {
                this.cnType = cnType;
            }

            public int getCnUserId() {
                return cnUserId;
            }

            public void setCnUserId(int cnUserId) {
                this.cnUserId = cnUserId;
            }

            public int getCollectionId() {
                return collectionId;
            }

            public void setCollectionId(int collectionId) {
                this.collectionId = collectionId;
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

            public static class ArticleBean implements Parcelable{
                /**
                 * aAbortDate : 2017-10-31 00:00:00
                 * aContent : 手相）将会是个好丈夫好妻子的最佳人选。
                 * aCreateTime : 2017-10-24 13:29:16
                 * aImg :
                 * aMasterId : 1
                 * aPublishDate : 2017-10-24 00:00:00
                 * aReadAmount : 11
                 * aTitle : 文章2
                 * aType : 手相
                 * aUpdateTime : 2017-11-23 11:06:07
                 * aUrl : http://www.sohu.com/a/153427408_509149
                 * articleId : 2
                 * imgList : []
                 * isCollection : false
                 * queryUserId : 0
                 */

                private String aAbortDate;
                private String aContent;
                private String aCreateTime;
                private String aImg;
                private int aMasterId;
                private String aPublishDate;
                private int aReadAmount;
                private String aTitle;
                private String aType;
                private String aUpdateTime;
                private String aUrl;
                private int articleId;
                private boolean isCollection;
                private int queryUserId;
                private List<ImgListBean> imgList;

                protected ArticleBean(Parcel in) {
                    aAbortDate = in.readString();
                    aContent = in.readString();
                    aCreateTime = in.readString();
                    aImg = in.readString();
                    aMasterId = in.readInt();
                    aPublishDate = in.readString();
                    aReadAmount = in.readInt();
                    aTitle = in.readString();
                    aType = in.readString();
                    aUpdateTime = in.readString();
                    aUrl = in.readString();
                    articleId = in.readInt();
                    isCollection = in.readByte() != 0;
                    queryUserId = in.readInt();
                    imgList = in.createTypedArrayList(ImgListBean.CREATOR);
                }

                public static final Creator<ArticleBean> CREATOR = new Creator<ArticleBean>() {
                    @Override
                    public ArticleBean createFromParcel(Parcel in) {
                        return new ArticleBean(in);
                    }

                    @Override
                    public ArticleBean[] newArray(int size) {
                        return new ArticleBean[size];
                    }
                };

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

                public String getATitle() {
                    return aTitle;
                }

                public void setATitle(String aTitle) {
                    this.aTitle = aTitle;
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

                public boolean isIsCollection() {
                    return isCollection;
                }

                public void setIsCollection(boolean isCollection) {
                    this.isCollection = isCollection;
                }

                public int getQueryUserId() {
                    return queryUserId;
                }

                public void setQueryUserId(int queryUserId) {
                    this.queryUserId = queryUserId;
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
                    parcel.writeString(aAbortDate);
                    parcel.writeString(aContent);
                    parcel.writeString(aCreateTime);
                    parcel.writeString(aImg);
                    parcel.writeInt(aMasterId);
                    parcel.writeString(aPublishDate);
                    parcel.writeInt(aReadAmount);
                    parcel.writeString(aTitle);
                    parcel.writeString(aType);
                    parcel.writeString(aUpdateTime);
                    parcel.writeString(aUrl);
                    parcel.writeInt(articleId);
                    parcel.writeByte((byte) (isCollection ? 1 : 0));
                    parcel.writeInt(queryUserId);
                    parcel.writeTypedList(imgList);
                }
            }

            public static class MasterBean {
                /**
                 * isCollection : false
                 * isSubscribe : false
                 * mAccid : 0
                 * mAddress : 珠海
                 * mBargain : 2
                 * mBusinessType : 1
                 * mConstellation : 2
                 * mCountryCode : 86
                 * mCreateTime : 2017-10-30 15:28:03
                 * mDeals : 1
                 * mDeleted : false
                 * mDeviceId :
                 * mDeviceType : 0
                 * mFollows : 1
                 * mHeadImg :
                 * mIm :
                 * mIntroduction :
                 * mLabel : 1
                 * mNick : 生
                 * mPassword : 11
                 * mScore : 4.5
                 * mSex : true
                 * mStatus : 1
                 * mTel : 13726277953
                 * mToken :
                 * mUpdateTime : 2017-11-22 16:37:24
                 * masterId : 2
                 * queryUserId : 0
                 * isSubscribe true
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
            }

            public static class ProductBean implements Parcelable{
                /**
                 * imgList : []
                 * isCollection : false
                 * pAttribution : 浙江金华
                 * pCreateTime : 2017-10-24 13:40:39
                 * pDeals : 0
                 * pDelete : false
                 * pFreeShipping : true
                 * pHot : 0
                 * pImg :
                 * pIntroduction : 佛螺菩提子手串：文玩界的新宠，以其，是不可多得的一种菩提。
                 * pMasterId : 2
                 * pName : 名木香府佛螺菩提子手串 金刚满肉红皮佛珠原籽念珠男女文玩木珠 17mm*13颗
                 * pOffline : false
                 * pPrice : 499
                 * pSize : 13-17毫米（13-17mm）
                 * pTotal : 0
                 * pUpdateTime : 2017-11-14 15:23:53
                 * productId : 2
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

                protected ProductBean(Parcel in) {
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

                public  ImgListBean(){}

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
}
