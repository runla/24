package com.yinghai.a24divine_user.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by：fanson
 * Created on：2017/9/29 17:30
 * Describe：文章Bean
 */

public class ArticleBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"articleList":[{"aAbortDate":"","aContent":"就好看吧","aCreateTime":"2017-11-10 13:13:54","aImg":"","aMasterId":2,"aPublishDate":"2017-11-10 00:00:00","aReadAmount":0,"aTitle":"1112","aType":"","aUpdateTime":"","aUrl":"","articleId":57,"imgList":[],"isCollection":true,"queryUserId":0}]}
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
        private List<ArticleListBean> articleList;

        public List<ArticleListBean> getArticleList() {
            return articleList;
        }

        public void setArticleList(List<ArticleListBean> articleList) {
            this.articleList = articleList;
        }

        public static class ArticleListBean implements Parcelable{
            /**
             * aAbortDate :
             * aContent : 就好看吧
             * aCreateTime : 2017-11-10 13:13:54
             * aImg :
             * aMasterId : 2
             * aPublishDate : 2017-11-10 00:00:00
             * aReadAmount : 0
             * aTitle : 1112
             * aType :
             * aUpdateTime :
             * aUrl :
             * articleId : 57
             * imgList : []
             * isCollection : true
             * queryUserId : 0
             */

            public ArticleListBean(){}

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
            private MasterDetailBean.DataBean.TfMasterBean tfMaster;

            public MasterDetailBean.DataBean.TfMasterBean getTfMaster() {
                return tfMaster;
            }

            public void setTfMaster(MasterDetailBean.DataBean.TfMasterBean tfMaster) {
                this.tfMaster = tfMaster;
            }

            protected ArticleListBean(Parcel in) {
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

            public static final Creator<ArticleListBean> CREATOR = new Creator<ArticleListBean>() {
                @Override
                public ArticleListBean createFromParcel(Parcel in) {
                    return new ArticleListBean(in);
                }

                @Override
                public ArticleListBean[] newArray(int size) {
                    return new ArticleListBean[size];
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
